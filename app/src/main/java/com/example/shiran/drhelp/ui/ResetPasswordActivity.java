package com.example.shiran.drhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shiran.drhelp.R;
import com.example.shiran.drhelp.services.FirebaseUserService;
import com.example.shiran.drhelp.services.UserService;
import com.example.shiran.drhelp.services.observers.UserResetPasswordObserver;

public class ResetPasswordActivity extends AppCompatActivity implements UserResetPasswordObserver {

    private TextInputEditText editText_userEmail;
    private MaterialButton button_resetPassword;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initResetPasswordReferences();

        userService = FirebaseUserService.getInstance(getApplicationContext());
        userService.setUserResetPasswordObserver(this);

        button_resetPassword.setOnClickListener(this::onResetPasswordButtonPressed);
    }

    private void onResetPasswordButtonPressed(View view) {
        String email = editText_userEmail.getText().toString().trim();
        if(!isValidField(email)){
            return;
        }
        userService.resetPassword(email);
    }

    private void initResetPasswordReferences() {
        editText_userEmail = findViewById(R.id.email_editText_resetPassword);
        button_resetPassword = findViewById(R.id.btn_resetPassword);
    }

    private boolean isValidField(String email) {
        if(TextUtils.isEmpty(email)){
            editText_userEmail.setError("Email Required.");
            return false;
        }
        return true;
    }

    @Override
    public void onResetPasswordSucceed() {
        Toast.makeText(ResetPasswordActivity.this,
                "We have sent you instructions to reset your password!",
                Toast.LENGTH_SHORT).show();
        Intent intent_toLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent_toLogin);
        Log.d("Reset-Password:", "Reset Password succeeded.");
        finish();
    }

    @Override
    public void onResetPasswordFailed() {
        Toast.makeText(ResetPasswordActivity.this,
                "Reset Password failed.\n",
                Toast.LENGTH_SHORT).show();
        Log.d("Reset-Password:", "Reset Password failed.");
    }
}
