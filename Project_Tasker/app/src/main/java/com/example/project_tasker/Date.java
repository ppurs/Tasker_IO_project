package com.example.project_tasker;

import androidx.annotation.NonNull;

class Date {
    int day;
    int month;
    int year;

    public Date()
    {
        day = 11;
        month = 4;
        year = 2001;
    }

    public Date ( int day, int month, int year ) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(day) + " " + Integer.toString(month) + " " + Integer.toString(year);
    }

    void setDay(int day ){}
    int getDay(){ return day; }
    void setMonth( int month ) {}
    int getMonth() { return month; }
    void setYear( int year ){}
    int getYear() { return year; }
}
