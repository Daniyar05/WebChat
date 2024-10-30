package org.webchat.utils;

public class PasswordHasher {
    public static String getHashPassword(String password){
        return String.valueOf(password.hashCode());
    }
}
