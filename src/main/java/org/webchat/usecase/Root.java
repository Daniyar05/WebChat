package org.webchat.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webchat.config.ConfigurationChat;
import org.webchat.db.DatabaseConnection;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.repository.Impl.UserMoodsRepoImpl;
import org.webchat.repository.Impl.UsersRepoImpl;
import org.webchat.servlets.LoginServlet;

public class Root {
    public static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    public static final ConfigurationChat configurationChat = ConfigurationChat.getConfig();
    public static ChatRepoImpl chatRepo = new ChatRepoImpl(databaseConnection);
    public static UserMoodsRepoImpl userMoodsRepo = new UserMoodsRepoImpl(databaseConnection);
    public static UsersRepoImpl usersRepo = new UsersRepoImpl(databaseConnection);
    public static UserManager userManager = new UserManager(databaseConnection);
}
