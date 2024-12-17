package org.webchat.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webchat.db.DatabaseConnection;
import org.webchat.mapper.ChatMapper;
import org.webchat.mapper.MessageMapper;
import org.webchat.mapper.UserMapper;
import org.webchat.mapper.impl.ChatMapperImpl;
import org.webchat.mapper.impl.MessageMapperImpl;
import org.webchat.mapper.impl.UserMapperImpl;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.repository.Impl.UserMoodsRepoImpl;
import org.webchat.repository.Impl.UsersRepoImpl;
import org.webchat.service.ChatsService;
import org.webchat.service.FileService;
import org.webchat.service.impl.ChatService;
import org.webchat.service.impl.ChatsServiceImpl;
import org.webchat.service.impl.FileServiceImpl;
import org.webchat.servlets.LoginServlet;
import org.webchat.usecase.ChatsLaunch;
import org.webchat.usecase.CreateChatForTwoUser;
import org.webchat.usecase.UserManager;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@WebListener
public class MainServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        final Logger log = LoggerFactory.getLogger(LoginServlet.class);
        context.setAttribute("log", log);

        final DatabaseConnection databaseConnection = new DatabaseConnection();
        context.setAttribute("databaseConnection", databaseConnection);

        final ConfigurationChat configurationChat = ConfigurationChat.getConfig();
        context.setAttribute("configurationChat", configurationChat);

        ChatRepoImpl chatRepo = new ChatRepoImpl(databaseConnection);
        context.setAttribute("chatRepo", chatRepo);

        UserMoodsRepoImpl userMoodsRepo = new UserMoodsRepoImpl(databaseConnection);
        context.setAttribute("userMoodsRepo", userMoodsRepo);

        UsersRepoImpl usersRepo = new UsersRepoImpl(databaseConnection);
        context.setAttribute("usersRepo", usersRepo);

        UserManager userManager = new UserManager(databaseConnection);
        context.setAttribute("userManager", userManager);

        FileService fileService = new FileServiceImpl();
        context.setAttribute("fileService", fileService);

        UserMapper userMapper = new UserMapperImpl();
        context.setAttribute("userMapper", userMapper);

        ChatService chatService = new ChatService();
        context.setAttribute("chatService", chatService);


        ChatMapper chatMapper = new ChatMapperImpl();
        MessageMapper messageMapper = new MessageMapperImpl();

        ChatsService chatsService = new ChatsServiceImpl(chatRepo, usersRepo,
                chatMapper,
                userMapper,
                messageMapper);
        context.setAttribute("chatsService", chatsService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("-=-=-=-=-=-=-=-=- CONTEXT DESTROYED -==-=-=-=-=-=-=-=-=");
    }

}
