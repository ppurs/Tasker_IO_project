package com.example.project_tasker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

class Card extends StructuralElement {
    ArrayList<Task> tasks;

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

    boolean addTask( String taskName, String taskDescription )
    {
        boolean temp = validation( taskName );

        if( temp )
            tasks.add( new Task( taskName, taskDescription ) );

        return temp;
    }

    void deleteTask( int index ){
        tasks.remove( index );
    }

    void sortTasksByPriority()
    {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task2.getPriority() - task1.getPriority();
            }
        });
    }
}
