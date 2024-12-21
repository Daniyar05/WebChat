package org.webchat.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.service.impl.ChatService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ChatServlet", value = "/chat")
public class ChatServlet extends HttpServlet {
    private ChatService chatService;

    @Override
    public void init() throws ServletException {
        chatService = (ChatService) getServletContext().getAttribute("chatService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatId = request.getParameter("ID_CHAT");
        boolean isAjaxRequest = "AjaxRequest".equals(request.getHeader("X-Type-Request"));
        if (chatId == null || chatId.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (isAjaxRequest) {
            handleAjaxRequest(request, response, chatId);
        } else {
            handleHtmlRequest(request, response, chatId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = (String) request.getSession().getAttribute("userId");
        String chatId = request.getParameter("ID_CHAT");
        String content = request.getParameter("content");

        if (chatService.addMessage(userId, chatId, content)) {
            response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + chatId);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String chatId = request.getParameter("chatId");

        if (chatService.deleteChat(chatId)) {
            response.sendRedirect(request.getContextPath() + "/list-chats");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


    private void handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, String chatId) throws IOException {
        try {
            int offset = request.getParameter("offset") != null ? Integer.parseInt(request.getParameter("offset")) : 0;
            int limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 20;

            List<Message> messages = chatService.getMessages(chatId, offset, limit);
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(messages));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void handleHtmlRequest(HttpServletRequest request, HttpServletResponse response, String chatId) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (!chatService.canUserAccessChat(userId, chatId)) {
            response.sendRedirect(request.getContextPath() + "/list-chats");
            return;
        }

        Optional<Chat> chatOptional = chatService.getChatById(chatId);
        if (chatOptional.isPresent()) {
            request.setAttribute("chat", chatOptional.get());
            request.setAttribute("username", request.getSession().getAttribute("username"));
            request.getRequestDispatcher("/chat.jsp").forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
