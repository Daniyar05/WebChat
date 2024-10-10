package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.User;
import org.webchat.repository.UsersRepoImpl;

import java.io.IOException;
@WebServlet(name = "AddChatServlet", value = "/add-chat")
public class AddChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("add-chat.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersRepoImpl.addUserChat(((User) request.getSession().getAttribute("user")).getId(), request.getParameter("chatId"));
        response.sendRedirect(request.getContextPath() + "/list-chats");

    }
}
