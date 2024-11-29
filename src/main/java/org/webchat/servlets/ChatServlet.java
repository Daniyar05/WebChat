package org.webchat.servlets;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.usecase.Root;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ChatServlet", value = "/chat")

public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ajax = "AjaxRequest".equals(request.getHeader("Type-Request"));
        String idChat = request.getParameter("ID_CHAT");
        Optional<Chat> chatOptional= Root.chatRepo.getChat(idChat);

        if (chatOptional.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (ajax){
            Chat chat = chatOptional.get();
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(chat.getHistory()));

        } else {
            response.setContentType("text/html");

            Chat thisChat = chatOptional.get();
            String userId = (String) request.getSession().getAttribute("userId");
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/profile");
                return;
            }
            Optional<User> thisUser = Root.usersRepo.getUser(userId);
            if (thisUser.isPresent() && thisUser.get().getIdChats().contains(idChat)) {
                request.setAttribute("chat", thisChat);
                request.setAttribute("messagesJson", new Gson().toJson(thisChat.getHistory()));
                request.getRequestDispatcher("/chat.jsp").forward(request, response);

                return;
            }
            response.sendRedirect(request.getContextPath() + "/list-chats");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorId = ((String) request.getSession().getAttribute("userId"));
        Optional<User> user = Root.usersRepo.getUser(authorId);

        String content = request.getParameter("content");

        if (authorId != null && content != null && !content.trim().isEmpty() && user.isPresent()) {
            Message newMessage = new Message(user.get(), content);
            Root.chatRepo.addMessage(request.getParameter("ID_CHAT"), newMessage);
            response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + request.getParameter("ID_CHAT"));
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idChat = request.getParameter("chatId");
        Root.chatRepo.deleteChat(idChat);
        response.sendRedirect(request.getContextPath()+"/list-chats");
    }
}
