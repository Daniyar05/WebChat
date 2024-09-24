package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.utils.ChatsLaunch;
import org.webchat.domain.User;
import org.webchat.repository.UsersRepo;

import java.io.IOException;

@WebServlet(name = "ListChatsServlet", value = "/list-chats")
public class ListChatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        User thisUser = UsersRepo.getUser(request.getSession().getId());
        if (thisUser == null) {
            thisUser = new User(request.getSession().getId());
            UsersRepo.addUser(thisUser);
        }
        request.setAttribute("chats", ChatsLaunch.getChat(thisUser.getIdChats()));
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
