package org.webchat.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.repository.ChatsRepo;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.service.impl.ChatService;
import org.webchat.usecase.Root;

import java.io.IOException;

@WebServlet(name = "EditChatServlet", value = "/edit-chat")
public class EditChatServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { // rename chat
        String newUsername = request.getParameter("name");
        String idChat = request.getParameter("chatId");

        ((ChatsRepo) getServletContext().getAttribute("chatRepo")).renameChat(idChat, newUsername);
        response.sendRedirect(request.getContextPath() + "/chat?ID_CHAT=" + idChat);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException { // delete chat
        String idChat = request.getHeader("chatId");
        ((ChatsRepo) getServletContext().getAttribute("chatRepo")).deleteChat(idChat);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}


