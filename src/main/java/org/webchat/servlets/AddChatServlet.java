package org.webchat.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.repository.UserRepo;

import java.io.IOException;
@WebServlet(name = "AddChatServlet", value = "/add-chat")
public class AddChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("add-chat.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ((UserRepo) getServletContext().getAttribute("usersRepo")).addUserChat((String) request.getSession().getAttribute("userId"), request.getParameter("chatId"));
        response.sendRedirect(request.getContextPath() + "/list-chats");

    }
}
