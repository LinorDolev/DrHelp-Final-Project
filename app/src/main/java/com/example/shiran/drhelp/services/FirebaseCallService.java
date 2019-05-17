/*
package com.example.shiran.drhelp.services;

import android.app.Activity;

import com.example.shiran.drhelp.entities.Call;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseCallService implements CallService {

    private static CallService instance = new FirebaseCallService();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference callsDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    public FirebaseCallService() {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.callsDatabaseReference = firebaseDatabase.getReference("calls");
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
    }

    public static CallService getInstance() {
        return instance;
    }

    @Override
    public void callToUser(Call call, Activity activity) {
        addCallToFirebase(call);
    }

    private void addCallToFirebase(Call call) {
        callsDatabaseReference.child(call.getDoctorId()).setValue(call);
    }
}
*/
