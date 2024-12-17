package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.webchat.domain.User;
import org.webchat.repository.ChatsRepo;
import org.webchat.repository.UserRepo;
import org.webchat.usecase.Root;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!((UserRepo) getServletContext().getAttribute("usersRepo")).hasUsername(username)){
            User newUser = new User(UUID.randomUUID().toString(), username);
            ((UserRepo) getServletContext().getAttribute("usersRepo")).addUser(newUser, password);
            request.getSession().setAttribute("userId", newUser.getId());
            request.getSession().setAttribute("username", newUser.getUsername());

            response.sendRedirect(request.getContextPath() + "/profile");
            ((Logger) getServletContext().getAttribute("log")).info("User-> id={} auth", newUser.getId());
            return;
        }
        request.getRequestDispatcher("/registration.jsp").forward(request, response);

    }
}
