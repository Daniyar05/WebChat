package org.webchat.config;

import com.mongodb.MongoClient;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webchat.db.DatabaseConnection;
import org.webchat.mapper.UserMapper;
import org.webchat.mapper.impl.UserMapperImpl;
import org.webchat.repository.DAO.ChatDAO;
import org.webchat.repository.DAO.Impl.ChatDAOImpl;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.repository.Impl.MongoFileRepositoryImpl;
import org.webchat.repository.Impl.UserMoodsRepoImpl;
import org.webchat.repository.Impl.UsersRepoImpl;
import org.webchat.repository.MongoFileRepository;
import org.webchat.repository.UserRepo;
import org.webchat.service.FileService;
import org.webchat.service.impl.ChatService;
import org.webchat.service.impl.FileServiceImpl;
import org.webchat.service.impl.MongoFileService;
import org.webchat.servlets.LoginServlet;
import org.webchat.usecase.ChatCleaner;
import org.webchat.usecase.UserManager;

import java.sql.Timestamp;

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


        ChatDAO chatDAO = new ChatDAOImpl(databaseConnection);
        context.setAttribute("chatDAO", chatDAO);


        ChatRepoImpl chatRepo = new ChatRepoImpl(chatDAO);
        context.setAttribute("chatRepo", chatRepo);

        UserMoodsRepoImpl userMoodsRepo = new UserMoodsRepoImpl(databaseConnection);
        context.setAttribute("userMoodsRepo", userMoodsRepo);

        UserRepo usersRepo = new UsersRepoImpl(databaseConnection);
        context.setAttribute("usersRepo", usersRepo);

        UserManager userManager = new UserManager(userMoodsRepo);
        context.setAttribute("userManager", userManager);

//        FileService fileService = new FileServiceImpl();
//        context.setAttribute("fileService", fileService);

        UserMapper userMapper = new UserMapperImpl(usersRepo);
        context.setAttribute("userMapper", userMapper);

        ChatService chatService = new ChatService(chatRepo, usersRepo);
        context.setAttribute("chatService", chatService);

        ChatCleaner chatCleaner = new ChatCleaner(databaseConnection, log);
        chatCleaner.start(1000*60);

        MongoClient mongoClient = databaseConnection.getMongoClient();
        context.setAttribute("mongoClient", mongoClient);

        MongoFileRepository mongoFileRepository = new MongoFileRepositoryImpl(mongoClient);
        FileService fileService = new MongoFileService(mongoFileRepository,"0");
        context.setAttribute("fileService", fileService);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("-=-=-=-=-=-=-=-=- CONTEXT DESTROYED -==-=-=-=-=-=-=-=-=");
    }


}
