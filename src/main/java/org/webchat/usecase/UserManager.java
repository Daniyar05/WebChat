package org.webchat.usecase;

import org.webchat.db.DatabaseConnection;
import org.webchat.repository.Impl.UserMoodsRepoImpl;

import java.util.*;

public class UserManager {

    private UserMoodsRepoImpl userMoodsRepo;

    public UserManager(DatabaseConnection databaseConnection) {
        this.userMoodsRepo = new UserMoodsRepoImpl(databaseConnection);
    }

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
