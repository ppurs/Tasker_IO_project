package com.example.project_tasker;

import java.util.ArrayList;

class Card extends StructuralElement {
    ArrayList<Task> tasks;
    Category parentCategory;

    public Card(){}

    boolean validation( String taskName ) {
        for (Task temp : tasks) {
            if (temp.name.equals( taskName ) ) {
                return false;
            }
        }
        return true;
    }

    //w jaki sposob ustalamy priorytet? w oknie dodawania taska czy inaczej?
    boolean addTask( String taskName, String taskDescription ) { return true; }

    void deleteTask(){}

/*
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeTypedList( tasks );
    }

    private Card(Parcel in) {
        this.tasks = in.createTypedArrayList( Task.CREATOR );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Card> CREATOR
            = new Parcelable.Creator<Card>() {

        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };*/
}
