package org.webchat.API;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.Chat;
import org.webchat.usecase.Root;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ChatForAjaxServlet", value = "/api/get-messages-json")
public class ChatForAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatId = request.getParameter("chatId");
        Optional<Chat> chatOptional = Root.chatRepo.getChat(chatId);
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(chat.getHistory()));

        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


}
