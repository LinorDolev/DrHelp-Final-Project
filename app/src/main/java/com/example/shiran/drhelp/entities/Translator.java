package com.example.shiran.drhelp.entities;

import android.widget.DatePicker;
import android.widget.TimePicker;

//import com.example.shiran.drhelp.entities.shifts.ShiftTime;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Translator extends User {

//    private Map<Date, ShiftTime> shiftsBoard;

    public Translator(){
//        setShiftsBoard(new HashMap<>());
    }

    public Translator(String id, String firstName, String lastName, String email,
                      String password, Boolean available, String language) {

        super(id, firstName, lastName, email, password, available, language);
//        setShiftsBoard(new HashMap<>());
    }

    /*public Translator(String id, String firstName, String lastName, String email,
                      String password, Boolean available, String language,
                      Map<Date, ShiftTime> shiftBoard) {

        super(id, firstName, lastName, email, password, available, language);
        setShiftsBoard(shiftBoard);
    }*/

   /* public Map<Date, ShiftTime> getShiftsBoard() {
        return shiftsBoard;
    }*/

    /*public void setShiftsBoard(Map<Date, ShiftTime> shiftsBoard) {
        this.shiftsBoard = shiftsBoard;
    }*/
}
