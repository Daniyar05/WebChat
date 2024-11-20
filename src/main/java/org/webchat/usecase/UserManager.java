package org.webchat.usecase;

import org.webchat.domain.User;
import org.webchat.repository.UserMoodsRepo;

import java.util.*;
import java.util.stream.Collectors;

public class UserManager {

    private static UserMoodsRepo userMoodsRepo = new UserMoodsRepo();


    private static final Random random = new Random();

    public static boolean addUser(String userId, String mood) {
        return userMoodsRepo.addUser(userId, mood);
    }

    public static String getRandomUserBasedOnMood(String desiredMood) {
        List<String> filteredUsers = userMoodsRepo.getUsersId(desiredMood);
        if(filteredUsers == null || filteredUsers.isEmpty()){
            return null;
        }
        int index = random.nextInt(filteredUsers.size());

        return filteredUsers.get(index);
    }

    public static boolean removeUser(String userId) {
        return userMoodsRepo.removeUser(userId);
    }
}
