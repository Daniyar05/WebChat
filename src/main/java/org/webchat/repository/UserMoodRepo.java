package org.webchat.repository;

import java.util.List;

public interface UserMoodRepo {
    boolean addUserMood(String userId, String mood);

    List<String> getUsersId(String desiredMood, String userById);

    boolean removeUser(String userId);
}
