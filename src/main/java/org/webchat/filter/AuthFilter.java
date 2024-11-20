package org.webchat.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.webchat.domain.User;
import org.webchat.repository.UsersRepoImpl;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final List<String> PROTECTED_URIS = List.of("/main", "/logout");
    private static final List<String> NOTAUTH_URIS = List.of("/login", "/registration", "/greeting.jsp", "/CSS/greeting.css", "/", "/login.jsp","/registration.jsp");

    private static final String PROTECTED_REDIRECT = "greeting.jsp";
    private static final String NOTAUTH_REDIRECT = "/main";

    public static final String AUTHORIZATION = "userId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String path = uri.substring(request.getContextPath().length());

//        System.out.println(path);
        if(isUserAuth(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (isNotAuthUri(path)){
                filterChain.doFilter(servletRequest, servletResponse);
            } else{
                response.sendRedirect(PROTECTED_REDIRECT);
            }
        }
    }

    private boolean isProtectedUri(String uri) {
        return PROTECTED_URIS.contains(uri);
    }

    private boolean isNotAuthUri(String uri) {
        return NOTAUTH_URIS.contains(uri);
    }

    private boolean isUserAuth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return false;

        String userId = (String) session.getAttribute(AUTHORIZATION);
        //TODO Запрашиваем или нет и бд корректность данных
        return userId != null; //&& UsersRepoImpl.getUser(userId).isPresent()
    }
}
