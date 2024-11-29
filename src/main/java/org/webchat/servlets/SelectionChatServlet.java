package org.webchat.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webchat.usecase.CreateChatForTwoUser;
import org.webchat.usecase.Root;

import java.io.IOException;

@WebServlet(name = "SelectionChatServlet", value = "/select-chat")
public class SelectionChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/select-chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mood = request.getParameter("mood");
        String idUser = (String) request.getSession().getAttribute("userId");
        String suitableUserId = getSuitableUser(mood, idUser);
        addSuitableUser(idUser, mood);
        if (suitableUserId == null){
            request.setAttribute("notFoundException", true);
            request.getRequestDispatcher("/select-chat.jsp?").forward(request, response);
            return;
        }
        String idNewChat = CreateChatForTwoUser.createChat(suitableUserId, idUser);
        request.getRequestDispatcher("/chat?ID_CHAT=" + idNewChat).forward(request, response);
    }

    private String getSuitableUser(String mood, String userById) {
        return Root.userManager.getRandomUserBasedOnMood(mood, userById);
    }


    private boolean addSuitableUser(String userId, String mood){
        return Root.userManager.addUser(userId, mood);
    }
}