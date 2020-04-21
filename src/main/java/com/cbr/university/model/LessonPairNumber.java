package com.cbr.university.model;

public enum LessonPairNumber {
    FIRST_PAIR("8:30"), SECOND_PAIR("11:00"), THIRD_PAIR("14:00"), FOURTH_PAIR("16:30");

    private String pairStartTime;

    private LessonPairNumber(String pairStartTime) {
        this.pairStartTime = pairStartTime;
    }

    public String getPairStartTime() {
        return pairStartTime;
    }
}
