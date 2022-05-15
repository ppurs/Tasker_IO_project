package com.example.project_tasker;

import java.util.ArrayList;

class Card extends StructuralElement {
    ArrayList<Task> tasks;
    Category parentCategory;

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
}
