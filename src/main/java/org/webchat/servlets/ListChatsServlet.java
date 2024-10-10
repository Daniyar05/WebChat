package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.User;
import org.webchat.repository.UsersRepoImpl;
import org.webchat.utils.ChatsLaunch;

import java.io.IOException;

@WebServlet(name = "ListChatsServlet", value = "/list-chats")
public class ListChatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        User thisUser = (User) request.getSession().getAttribute("user");
        if (thisUser==null) {
            response.sendRedirect(request.getContextPath() + "/profile");
            return;
        }
        User user = UsersRepoImpl.getUser(thisUser.getId()).get();
        //TODO сделать токены для регистрации и логирование пользователя
        request.setAttribute("chats", ChatsLaunch.getChat(user.getIdChats()));
        request.setAttribute("user_id", thisUser.getId());

        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
