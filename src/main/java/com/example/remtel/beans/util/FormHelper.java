package com.example.remtel.beans.util;

import com.example.remtel.beans.User;

public abstract class FormHelper {
    public static String getUserName(User user){
        return user != null ? user.getUsername() : "<none>";
    }
}
