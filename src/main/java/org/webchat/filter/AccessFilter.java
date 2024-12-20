package org.webchat.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.User;
import org.webchat.repository.ChatRepo;
import org.webchat.repository.UserRepo;

import java.io.IOException;

@WebFilter("/*")
public class AccessFilter implements Filter {

    private ChatRepo chatRepo;
    private UserRepo userRepo;

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext context = filterConfig.getServletContext();
        chatRepo = (ChatRepo) context.getAttribute("chatRepo");
        userRepo = (UserRepo) context.getAttribute("usersRepo");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String path = uri.substring(request.getContextPath().length());
        System.out.println(uri);
        if (!isUserAuth(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (isAuthorizedAccess(request, path)) {
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        }
    }

    private boolean isAuthorizedAccess(HttpServletRequest request, String path) {
        User user = getCurrentUser(request);
        if (path.startsWith("/websocket")) {
            return isWebSocketAccessAllowed(user);
        } else if (path.startsWith("/chat")) {
            String chatId = extractChatIdFromPath(request);
            return user != null && chatId != null && chatRepo.findAllUsersInChat(chatId).contains(user.getId());
        } else if (path.startsWith("/avatars")) {
            String chatId = extractChatIdFromPath(request);
            if (chatId == null) return false;
            return user != null && (chatRepo.findAllUsersInChat(chatId).contains(user.getId()) || userRepo.getUser(chatId).isPresent());
        }
        return true;
    }

    private boolean isWebSocketAccessAllowed(User currentUser) {
        return currentUser != null;
    }

    private String extractChatIdFromPath(HttpServletRequest request) {
        String query = request.getQueryString();
        if (query != null && query.contains("ID_CHAT=")) {
            return query.split("ID_CHAT=")[1];
        }
        else if (query != null && query.contains("chatId=")) {
            return query.split("chatId=")[1];
        }
        else if (query != null && query.contains("userId=")) {
            return query.split("userId=")[1];
        }
        return null;
    }

    private boolean isUserAuth(HttpServletRequest request) {
        return new AuthFilter().isUserAuth(request);
    }

    private User getCurrentUser(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(AuthFilter.AUTHORIZATION);
        return userId != null ? userRepo.getUser(userId).orElse(null) : null;
    }
}
