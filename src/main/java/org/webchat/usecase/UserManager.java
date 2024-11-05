package org.webchat.usecase;

import org.webchat.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserManager {

    private static final Map<String, User> users = new HashMap<>();
    private static final Random random = new Random();

    public static void addUser(User user) {
        users.put(user.getId(), user);
    }

    public static User getRandomUserBasedOnMood(String desiredMood) {
        List<User> filteredUsers = users.values().stream()
                .filter(user -> desiredMood.equals(user.getMood()))
                .collect(Collectors.toList());

        if (filteredUsers.isEmpty()) {
            return null;
        }

        int index = random.nextInt(filteredUsers.size());
        return filteredUsers.get(index);
    }

    public static void removeUser(String userId) {
        users.remove(userId);
    }
}
