package org.webchat.usecase;

import org.webchat.db.DatabaseConnection;
import org.webchat.repository.Impl.UserMoodsRepoImpl;

import java.util.*;

public class UserManager {

    public UserManager(UserMoodsRepoImpl userMoodsRepo) {
        this.userMoodsRepo = userMoodsRepo;
    }

    private final UserMoodsRepoImpl userMoodsRepo;


    private final Random random = new Random();

    public boolean addUser(String userId, String mood) {
        return userMoodsRepo.addUserMood(userId, mood);
    }

    public String getRandomUserBasedOnMood(String desiredMood, String userById) {
        List<String> filteredUsers = userMoodsRepo.getUsersId(desiredMood, userById);
        if(filteredUsers == null || filteredUsers.isEmpty()){
            return null;
        }
        int index = random.nextInt(filteredUsers.size());

        return filteredUsers.get(index);
    }

    public boolean removeUser(String userId) {
        return userMoodsRepo.removeUser(userId);
    }
}
