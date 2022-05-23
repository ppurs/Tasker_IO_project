package com.example.project_tasker;

import java.util.ArrayList;

class Card extends StructuralElement {
    ArrayList<Task> tasks;
    //Category parentCategory;

    public Card(){
        tasks = new ArrayList<>();
    }

    public Card( String cardName, String cardDescription ) {
        super( cardName, cardDescription);
        tasks = new ArrayList<>();
    }

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

    void deleteTask( int index ){
        tasks.remove( index );
    }

}
