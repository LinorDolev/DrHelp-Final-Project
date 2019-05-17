package com.example.shiran.drhelp.services;

import android.app.Activity;
import android.arch.core.util.Function;

import com.example.shiran.drhelp.entities.forms.RegistrationForm;
import com.example.shiran.drhelp.entities.User;
import com.example.shiran.drhelp.entities.forms.ShiftForm;
import com.example.shiran.drhelp.services.observers.UserLoginObserver;
import com.example.shiran.drhelp.services.observers.UserRegistrationObserver;
import com.example.shiran.drhelp.services.observers.UserResetPasswordObserver;

import java.util.List;

public interface UserService {

    void registerUser(RegistrationForm registrationForm, Activity activity, Boolean isDoctor);

    void setUserRegistrationObserver(UserRegistrationObserver userRegistrationObserver);

    void loginUser(String email, String password, Activity activity);

    void setUserLoginObserver(UserLoginObserver userLoginObserver);

    void resetPassword(String email);

    void getAllAvailableUsers(Function<List<User>, Void> onAvailableUsersArrived);

    void logout(User user);

    void setUserResetPasswordObserver(UserResetPasswordObserver userResetPasswordObserver);

    String getUserStatus();

    void setUserStatus(boolean available);

    int getNumberOfTranslators();

    int getNumberOfDoctors();

    // List<User> getAllAvailableUsers(String id);

    String getCurrentUserId();

    String getCurrentUserName();

    User getUserById(String id);

    User getUserByEmail(String email);

    User getCurrentUser();

    void setUserShifts(String shifts);

    void shiftRegistration(ShiftForm shiftForm);

    void callToUser(User toUser);

    void setCallIsOn();

    boolean checkIfItsForMe(User user);
}
