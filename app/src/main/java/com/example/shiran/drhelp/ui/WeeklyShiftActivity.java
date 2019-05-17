package com.example.shiran.drhelp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shiran.drhelp.R;
import com.example.shiran.drhelp.entities.forms.ShiftForm;
import com.example.shiran.drhelp.services.FirebaseUserService;
import com.example.shiran.drhelp.services.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class WeeklyShiftActivity extends AppCompatActivity {
    private static final String MORNING = "morning";
    private static final String EVENING = "evening";

    private TextView[] weeklyDays_textView = new TextView[6];
    private RadioGroup[] weeklyDays_radioGroup = new RadioGroup[6];
    private String[] weeklyDays_shifts = new String[6];
    private MaterialButton shiftConfirmation_button;
    private int shiftCount = 0;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_shift);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        userService = new FirebaseUserService(getApplicationContext());

        initWeeklyReferences();
        setWeeklyDays(calendar, dateFormat);

        setShiftDays();
        shiftConfirmation_button.setOnClickListener(this::onShiftConfirmationButtonPressed);
    }

    private void setShiftDays() {
        weeklyDays_radioGroup[0].setOnCheckedChangeListener(this::onSundayPressed);
        weeklyDays_radioGroup[1].setOnCheckedChangeListener(this::onMondayPressed);
        weeklyDays_radioGroup[2].setOnCheckedChangeListener(this::onTuesdayPressed);
        weeklyDays_radioGroup[3].setOnCheckedChangeListener(this::onWednesdayPressed);
        weeklyDays_radioGroup[4].setOnCheckedChangeListener(this::onThursdayPressed);
        weeklyDays_radioGroup[5].setOnCheckedChangeListener(this::onFridayPressed);
    }

    private void onSundayPressed(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (i == R.id.sunday_morn)
                weeklyDays_shifts[0] = weeklyDays_textView[0].getText() + " " + MORNING;
            else weeklyDays_shifts[0] = weeklyDays_textView[0].getText() + " " + EVENING;
            shiftCount++;
            Log.d("shiftDebug", "1 - " + shiftCount);
        }
    }

    private void onMondayPressed(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (i == R.id.monday_morn)
                weeklyDays_shifts[1] = weeklyDays_textView[1].getText() + " " + MORNING;
            else weeklyDays_shifts[1] = weeklyDays_textView[1].getText() + " " + EVENING;
            shiftCount++;
            Log.d("shiftDebug", "2 - " + shiftCount);
        }
    }

    private void onTuesdayPressed(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (i == R.id.tuesday_morn)
                weeklyDays_shifts[2] = weeklyDays_textView[2].getText() + " " + MORNING;
            else weeklyDays_shifts[2] = weeklyDays_textView[2].getText() + " " + EVENING;
            shiftCount++;
            Log.d("shiftDebug", "3 - " + shiftCount);
        }
    }

    private void onWednesdayPressed(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (i == R.id.wednesday_morn)
                weeklyDays_shifts[3] = weeklyDays_textView[3].getText() + " " + MORNING;
            else weeklyDays_shifts[3] = weeklyDays_textView[3].getText() + " " + EVENING;
            shiftCount++;
            Log.d("shiftDebug", "4 - " + shiftCount);
        }
    }

    private void onThursdayPressed(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (i == R.id.thursday_morn)
                weeklyDays_shifts[4] = weeklyDays_textView[4].getText() + " " + MORNING;
            else weeklyDays_shifts[4] = weeklyDays_textView[4].getText() + " " + EVENING;
            shiftCount++;
            Log.d("shiftDebug", "5 - " + shiftCount);
        }
    }

    private void onFridayPressed(RadioGroup radioGroup, int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (i == R.id.friday_morn)
                weeklyDays_shifts[5] = weeklyDays_textView[5].getText() + " " + MORNING;
            else weeklyDays_shifts[5] = weeklyDays_textView[5].getText() + " " + EVENING;
            shiftCount++;
            Log.d("shiftDebug", "6 - " + shiftCount);
        }
    }

    private void onShiftConfirmationButtonPressed(View view) {
        Log.d("shiftDebug", "7 - " + shiftCount);

        if (shiftCount < 3)
            Toast.makeText(WeeklyShiftActivity.this, "You must choose at least 3 shifts", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(WeeklyShiftActivity.this, "Your shifts board updated", Toast.LENGTH_SHORT).show();

            ShiftForm shiftForm = new ShiftForm(weeklyDays_shifts);
            Log.d("shiftDebug", shiftForm.toString());
            userService.shiftRegistration(shiftForm);
            Intent intent = new Intent(getApplicationContext(), AvailabilityActivity.class);
            startActivity(intent);
        }
    }

    private void initWeeklyReferences() {
        weeklyDays_textView[0] = findViewById(R.id.sun_text_view);
        weeklyDays_textView[1] = findViewById(R.id.mon_text_view);
        weeklyDays_textView[2] = findViewById(R.id.tue_text_view);
        weeklyDays_textView[3] = findViewById(R.id.wed_text_view);
        weeklyDays_textView[4] = findViewById(R.id.thu_text_view);
        weeklyDays_textView[5] = findViewById(R.id.fri_text_view);

        weeklyDays_radioGroup[0] = findViewById(R.id.sun_group);
        weeklyDays_radioGroup[1] = findViewById(R.id.mon_group);
        weeklyDays_radioGroup[2] = findViewById(R.id.tue_group);
        weeklyDays_radioGroup[3] = findViewById(R.id.wed_group);
        weeklyDays_radioGroup[4] = findViewById(R.id.thu_group);
        weeklyDays_radioGroup[5] = findViewById(R.id.fri_group);

        shiftConfirmation_button = findViewById(R.id.shifts_confirmation);
    }

    private void setWeeklyDays(Calendar calendar, DateFormat dateFormat) {
        for (int i = 0; i < 6; i++) {
            weeklyDays_textView[i].setText(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
    }
}


