package com.example.shiran.drhelp.services.observables;

import android.arch.core.util.Function;

import com.example.shiran.drhelp.entities.User;
import com.example.shiran.drhelp.services.UserService;
import com.example.shiran.drhelp.services.observers.UserLoginObserver;
import com.example.shiran.drhelp.services.observers.UserRegistrationObserver;
import com.example.shiran.drhelp.services.observers.UserResetPasswordObserver;

import java.util.List;

public abstract class UserServiceObservable implements UserService {
    private UserRegistrationObserver userRegistrationObserver;
    private UserLoginObserver userLoginObserver;
    private UserResetPasswordObserver userResetPasswordObserver;

    @Override
    public void setUserRegistrationObserver(UserRegistrationObserver observer) {
        this.userRegistrationObserver = observer;
    }

    protected void publishAboutRegistration(Function<UserRegistrationObserver, Void> function) {
        function.apply(userRegistrationObserver);
    }

    @Override
    public void setUserLoginObserver(UserLoginObserver userLoginObserver) {
        this.userLoginObserver = userLoginObserver;
    }

    protected void publishAboutLoggingIn(Function<UserLoginObserver, Void> function) {
        function.apply(userLoginObserver);
    }

    @Override
    public void setUserResetPasswordObserver(UserResetPasswordObserver userResetPasswordObserver) {
        this.userResetPasswordObserver = userResetPasswordObserver;
    }

    protected void publishAboutResetPassword(Function<UserResetPasswordObserver, Void> function) {
        function.apply(userResetPasswordObserver);
    }

    public abstract void getAllAvailableUsers(Function<List<User>, Void> onAvailableUsersArrived);

}