package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.User;
import org.webchat.repository.UsersRepoImpl;

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(UUID.randomUUID().toString(), username, email, String.valueOf(password.hashCode()));
        UsersRepoImpl.addUser(newUser);

        response.setContentType("text/html");
        response.getWriter().println("<h2>Регистрация успешна!</h2>");
        response.getWriter().println("<p>Username: " + username + "</p>");
        response.getWriter().println("<p>Email: " + email + "</p>");
    }
}
