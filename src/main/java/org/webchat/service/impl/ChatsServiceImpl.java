package org.webchat.service.impl;

import lombok.RequiredArgsConstructor;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.dto.ChatDto;
import org.webchat.dto.MessageDto;
import org.webchat.mapper.ChatMapper;
import org.webchat.mapper.MessageMapper;
import org.webchat.mapper.UserMapper;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.repository.Impl.UsersRepoImpl;
import org.webchat.service.ChatsService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChatsServiceImpl implements ChatsService {

    private final ChatRepoImpl chatRepository;
    private final UsersRepoImpl usersRepository;

    private final ChatMapper chatMapper;
    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    @Override
    public Optional<ChatDto> findChatById(String chatId) {
        return chatRepository.getChat(chatId).map(chatMapper::toDto);
    }

    @Override
    public boolean isUserInChat(String userId, String chatId) {
        return chatRepository.hasUserById(userId,chatId);
    }

    @Override
    public List<String> findAllChatsByUserId(String userId) {
        Optional<User> user = usersRepository.getUser(userId);
        if (user.isEmpty()){return List.of();}
        return user.get().getIdChats();
    }


    @Override
    public List<MessageDto> findAllMessagesInChat(String chatId) {
        Optional<Chat> chat = chatRepository.getChat(chatId);
        if (chat.isEmpty()){return List.of();}
        return chat.get().getHistory().stream()
                .map(messageMapper::toDto)
                .toList();
    }

    @Override
    public boolean sendNewMessage(String chatId, MessageDto dto) {
        Optional<User> user = usersRepository.getUser(dto.getAuthorUsername());
        if (user.isEmpty()){return false;}
        return chatRepository.addMessage(chatId, new Message(user.get(), dto.getContent()));
    }
}
