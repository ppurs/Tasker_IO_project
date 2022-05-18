package com.example.project_tasker;

import java.util.ArrayList;


class App {
    ArrayList<Project> projects;

    public App()
    {
        projects = new ArrayList<>();
    }

    boolean validation( String projectName ) {
        for (Project temp : projects) {
            if (temp.name.equals( projectName ) ) {
                return false;
            }
        }
        return true;
    }

    boolean addProject(String projectName, String projectDescription ) {
        boolean temp = validation( projectName );

        if( temp )
            projects.add( new Project( projectName, projectDescription ));

        return temp;
    }

    void deleteProject( int index ){
        projects.remove( index );
    }

    void sendNotifications(){}
}
