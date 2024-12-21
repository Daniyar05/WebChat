package org.webchat.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final List<String> PROTECTED_URIS = List.of("/main", "/logout");
    private static final List<String> NOTAUTH_URIS = List.of("/login", "/registration", "/greeting.jsp", "/CSS/greeting.css", "/", "/login.jsp","/registration.jsp");

    private static final String PROTECTED_REDIRECT = "greeting.jsp";

    public static final String AUTHORIZATION = "userId";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String path = uri.substring(request.getContextPath().length());

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

    private boolean isNotAuthUri(String uri) {
        return NOTAUTH_URIS.contains(uri);
    }

    protected boolean isUserAuth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return false;

        String userId = (String) session.getAttribute(AUTHORIZATION);
        return userId != null;
    }
}
