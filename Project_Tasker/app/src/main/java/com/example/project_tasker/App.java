package com.example.project_tasker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    boolean editName() { return true; }
    void deleteProject(){}
    void sendNotifications(){}
}
