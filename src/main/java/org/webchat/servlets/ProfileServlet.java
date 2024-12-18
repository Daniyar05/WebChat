package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.webchat.repository.UserRepo;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId") == null){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newUsername = request.getParameter("name");
        String userId = (String) request.getSession().getAttribute("userId");
        if (((UserRepo) getServletContext().getAttribute("usersRepo")).replaceUsername(userId, newUsername)) {
            request.getSession().setAttribute("username", newUsername);
        }
        else {
            request.setAttribute("error", true);
        }
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
