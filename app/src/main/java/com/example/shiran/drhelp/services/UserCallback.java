package com.example.shiran.drhelp.services;

import com.example.shiran.drhelp.entities.User;

public interface UserCallback {
    void getUserCallBack(User user);

    void getIsCallForMeCallback(boolean result);
}
