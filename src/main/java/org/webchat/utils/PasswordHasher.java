package org.webchat.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHasher {
    public static String getHashPassword(String password){
        return BCrypt.withDefaults().hashToString(12,password.toCharArray());
    }

    public static boolean isTruePassword(String password, String hashPassword){
        return BCrypt.verifyer().verify(password.toCharArray(), hashPassword).verified;
    }

}
