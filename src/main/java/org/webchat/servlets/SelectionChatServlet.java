package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.domain.User;
import org.webchat.usecase.CreateChatForTwoUser;
import org.webchat.usecase.UserManager;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "SelectionChatServlet", value = "/select-chat")
public class SelectionChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/select-chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mood = request.getParameter("mood");
        String suitableUserId = getSuitableUser(mood);
        System.out.println(suitableUserId);
        if (suitableUserId == null){
            request.setAttribute("notFoundException", true);
            request.getRequestDispatcher("/select-chat.jsp?").forward(request, response);
            return;
        }
        String idNewChat = CreateChatForTwoUser.createChat(suitableUserId, (String) request.getAttribute("userId"));
        request.getRequestDispatcher("/chat?ID_CHAT=" + idNewChat).forward(request, response);
    }

    private String getSuitableUser(String mood) {
        User user = UserManager.getRandomUserBasedOnMood(mood);
        if (user == null) {
            return null;
        }
        return user.getId();
    }
}
