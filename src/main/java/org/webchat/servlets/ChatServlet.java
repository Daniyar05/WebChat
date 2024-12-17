package org.webchat.servlets;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.webchat.config.ConfigurationChat;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.ChatsRepo;
import org.webchat.service.impl.ChatService;
import org.webchat.usecase.Root;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ChatServlet", value = "/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAjaxRequest = "AjaxRequest".equals(request.getHeader("X-Type-Request"));
        String chatId = request.getParameter("ID_CHAT");

        if (chatId == null || chatId.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<Chat> chatOptional = ((ChatService) getServletContext().getAttribute("chatService")).getChatById(chatId);
        if (chatOptional.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (isAjaxRequest) {
            handleAjaxRequest(request, response, ((ChatService) getServletContext().getAttribute("chatService")), chatId);
        } else {
            handleHtmlRequest(request, response, ((ChatService) getServletContext().getAttribute("chatService")), chatId);
        }
    }
    private void handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, ChatService chatService, String chatId) throws IOException {
        int offset = 0;
        int limit = 20;

        try {
            if (request.getParameter("offset") != null) {
                offset = Integer.parseInt(request.getParameter("offset"));
            }
            if (request.getParameter("limit") != null) {
                limit = Integer.parseInt(request.getParameter("limit"));
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Message> messages = chatService.getMessages(chatId, offset, limit);

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(messages));
    }

    private void handleHtmlRequest(HttpServletRequest request, HttpServletResponse response, ChatService chatService, String chatId) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }

        if (!chatService.isUserInChat(userId, chatId)) {
            response.sendRedirect(request.getContextPath() + "/list-chats");
            return;
        }

        Optional<Chat> chatOptional = chatService.getChatById(chatId);
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();
            request.setAttribute("chat", chat);
            request.setAttribute("messagesJson", new Gson().toJson(chatService.getMessages(chatId, 0, ((ConfigurationChat) getServletContext().getAttribute("configurationChat")).getLIMIT_MESSAGE())));
            request.getRequestDispatcher("/chat.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorId = ((String) request.getSession().getAttribute("userId"));
        Optional<User> user = Root.usersRepo.getUser(authorId);

        String content = request.getParameter("content");

        if (authorId != null && content != null && !content.trim().isEmpty() && user.isPresent()) {
            Message newMessage = new Message(user.get(), content);
            ((ChatService) getServletContext().getAttribute("chatService")).addMessage(request.getParameter("ID_CHAT"), newMessage);

            response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + request.getParameter("ID_CHAT"));
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idChat = request.getParameter("chatId");
        ((ChatService) getServletContext().getAttribute("chatService")).deleteChat(idChat);
        response.sendRedirect(request.getContextPath()+"/list-chats");
    }
}
