package org.webchat.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webchat.config.ConfigurationChat;
import org.webchat.db.DatabaseConnection;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.repository.Impl.UsersRepoImpl;
import org.webchat.servlets.LoginServlet;

public class Root {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    public static ChatRepoImpl chatRepo = new ChatRepoImpl(databaseConnection);
    public static UsersRepoImpl usersRepo = new UsersRepoImpl(databaseConnection);

}
