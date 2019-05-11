package com.example.shiran.drhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiran.drhelp.R;
import com.example.shiran.drhelp.entities.Doctor;
import com.example.shiran.drhelp.entities.Translator;
import com.example.shiran.drhelp.entities.User;
import com.example.shiran.drhelp.services.FirebaseUserService;
import com.example.shiran.drhelp.services.UserService;
import com.example.shiran.drhelp.services.observers.UserLoginObserver;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements UserLoginObserver {

    private TextInputEditText editText_userEmail;
    private TextInputEditText editText_userPassword;
    private TextView textView_forgotMyPassword;
    private TextView textView_toRegister;
    private MaterialButton button_login;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLoginReferences();

        userService = FirebaseUserService.getInstance();
        userService.setUserLoginObserver(this);

        textView_toRegister.setOnClickListener(this::onNeedToRegisterPressed);
        button_login.setOnClickListener(this::onLoginButtonPressed);
        textView_forgotMyPassword.setOnClickListener(this::onForgotMyPasswordButtonPressed);

    }

    private void initLoginReferences() {
        editText_userEmail = findViewById(R.id.email_editText_login);
        editText_userPassword = findViewById(R.id.password_editText_login);
        textView_forgotMyPassword = findViewById(R.id.link_forgot_password);
        textView_toRegister = findViewById(R.id.link_to_register);
        button_login = findViewById(R.id.btn_login);
    }

    private void onNeedToRegisterPressed(View view) {
        Intent intent_toRegister = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent_toRegister);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void onLoginButtonPressed(View view) {
        String email = editText_userEmail.getText().toString().trim();
        String password = editText_userPassword.getText().toString().trim();

        if (!isValidForm(email, password))
            return;

        userService.loginUser(email, password, this);
    }

    private void onForgotMyPasswordButtonPressed(View view) {
        Intent intent_toResetPassword = new Intent(getApplicationContext(), ResetPasswordActivity.class);
        startActivity(intent_toResetPassword);
    }

    private boolean isValidForm(String email, String password) {
        if(TextUtils.isEmpty(email)){
            editText_userEmail.setError("Email Required.");
            return false;
        }
        if(TextUtils.isEmpty(password)){
            editText_userPassword.setError("Password Required.");
            return false;
        }
        return true;
    }

    @Override
    public void onLoginSucceed(User user) {
        if(user instanceof Doctor){
            Gson gson = new Gson();
            String userId = user.getId();
            userService.getAllAvailableUsers(userList -> {
                Log.d("current user id", userId);

                Intent intent = new Intent(getApplicationContext(), ContactsActivity.class);
                intent.putExtra("usersList", gson.toJson(userList.toArray(new User[0])));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                return null;
            });
        }else if(user instanceof Translator){
            Intent intent = new Intent(getApplicationContext(), AvailabilityActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        Log.d("login:", "Authentication succeeded.");
        finish();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(LoginActivity.this, "Authentication failed.\n"
                        + "check your email and password or sign up"
                , Toast.LENGTH_SHORT).show();
        Log.d("login:", "Authentication failed.");
    }

}
