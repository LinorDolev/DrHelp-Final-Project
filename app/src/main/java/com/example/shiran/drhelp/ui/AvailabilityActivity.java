package com.example.shiran.drhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.shiran.drhelp.R;
import com.example.shiran.drhelp.entities.User;
import com.example.shiran.drhelp.services.FirebaseUserService;
import com.example.shiran.drhelp.services.UserService;

public class AvailabilityActivity extends AppCompatActivity {

    private SwitchCompat switchCompat_status;
    private TextView textView_status;
    private MaterialButton answerCall_button;
    private Button shiftBoard_button;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        initStatusReferences();
        userService = FirebaseUserService.getInstance(getApplicationContext());
        checkIfTheresACall();

        switchCompat_status.setOnCheckedChangeListener(this::onSwitchButtonPressed);
        answerCall_button.setOnClickListener(view -> onAnswerCallButtonPressed());
        shiftBoard_button.setOnClickListener(view -> onShiftBoardButtonPressed());
    }

    private void checkIfTheresACall() {
        User me = userService.getCurrentUser();
        /*if(userService.checkIfItsForMe(me)) {
            Log.d("callDebug:", "available activity - true");
            answerCall_button.setVisibility(View.VISIBLE);
        } else {
            answerCall_button.setVisibility(View.VISIBLE);
            Log.d("callDebug:", "available activity - false");
        }*/
        answerCall_button.setVisibility(View.VISIBLE);

    }

    private void onShiftBoardButtonPressed() {
        Intent intent = new Intent(getApplicationContext(), WeeklyShiftActivity.class);
        startActivity(intent);
    }

    private void onAnswerCallButtonPressed() {
        Intent intent = new Intent(getApplicationContext(), VideoChatActivity.class);
        startActivity(intent);
    }

    private void onSwitchButtonPressed(CompoundButton compoundButton, boolean isChecked) {
        userService.setUserStatus(isChecked);
        if (isChecked) {
            textView_status.setText("You are available to translate now");
        } else {
            textView_status.setText("You are unavailable to translate now");
        }
        Log.d("available", "" + isChecked);
    }

    private void initStatusReferences() {
        switchCompat_status = findViewById(R.id.status_switch);
        textView_status = findViewById(R.id.user_status_textView);
        answerCall_button = findViewById(R.id.receive_call_button_translator);
        shiftBoard_button = findViewById(R.id.shift_board_button);
    }
}
