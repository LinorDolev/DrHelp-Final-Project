package com.example.shiran.drhelp.entities;

public class Call {
    private String DoctorName;
    private String DoctorId;
    private String TranslatorName;
    private String translatorId;
    private String date;
    private boolean callIsOn;

    public Call() { }

    public Call(String doctorName, String doctorId, String translatorName, String translatorId, String  date, boolean callIsOn) {
        DoctorName = doctorName;
        DoctorId = doctorId;
        TranslatorName = translatorName;
        this.translatorId = translatorId;
        this.date = date;
        this.callIsOn = callIsOn;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getTranslatorName() {
        return TranslatorName;
    }

    public void setTranslatorName(String translatorName) {
        TranslatorName = translatorName;
    }

    public String getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(String translatorId) {
        this.translatorId = translatorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCallIsOn() {
        return callIsOn;
    }

    public void setCallIsOn(boolean callIsOn) {
        this.callIsOn = callIsOn;
    }

    @Override
    public String toString() {
        return "Call{" +
                "DoctorName='" + DoctorName + '\'' +
                ", TranslatorName='" + TranslatorName + '\'' +
                ", translatorId='" + translatorId + '\'' +
                ", date=" + date +
                ", callIsOn=" + callIsOn +
                '}';
    }
}
