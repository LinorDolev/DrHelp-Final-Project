package com.example.shiran.drhelp.services;

import android.content.Context;
import android.util.Log;

import com.example.shiran.drhelp.entities.User;
import com.example.shiran.drhelp.services.observables.NotificationServiceObservable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FirebaseNotificationService extends NotificationServiceObservable {

    /**
     * Singleton
     **/
    //private static NotificationService instance = new FirebaseNotificationService(Context context);
    private FirebaseAuth firebaseAuth;
    private UserService userService;
    private static FirebaseNotificationService instance;

    private FirebaseNotificationService(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        userService = FirebaseUserService.getInstance(context);
    }

    public static NotificationService getInstance(Context context) {

        if(instance == null) {
            return instance = new FirebaseNotificationService(context) {
            };
        }
        return instance;
    }

    @Override
    public void call(User toUser) {
        String currentUserId = firebaseAuth.getCurrentUser().getUid();
        User fromUser = userService.getCurrentUser();
        Map<String, Object> notificationMessage = new HashMap<>();
        notificationMessage.put("message", fromUser.getFirstName() + " "
                + fromUser.getLastName()
                + " is calling you on DrHelp");
        notificationMessage.put("from", currentUserId);

        Log.d("SHIRAN_DEBUG", "firebaseNotificationService");
        String receiverId = toUser.getId();

        FirebaseFirestore.getInstance().collection("users/" + receiverId + "/Notifications/")
                .add(notificationMessage)
                .addOnSuccessListener((documentReference) -> fireNotficationSent())
                .addOnFailureListener(this::fireNotficationFailed);
    }
}
