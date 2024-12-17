package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.webchat.domain.User;
import org.webchat.usecase.Root;

import java.io.IOException;
import java.util.Optional;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> user = Root.usersRepo.getUser(username,password);
        if (user.isPresent()) {

            request.getSession().setAttribute("userId", user.get().getId());
            request.getSession().setAttribute("username", user.get().getUsername());

            response.sendRedirect(request.getContextPath() + "/profile");
            ((Logger) getServletContext().getAttribute("log")).info("User-> id={} auth", user.get().getId());
            return;
        }
        response.sendRedirect("login.jsp?error=true");

    }
}
