package com.example.project_tasker;

import java.util.ArrayList;


class App {
    ArrayList<Project> projects;
    final int MAX_PROJECTS_COUNT = 20;
    private static App instance = null;

    private App()
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

    public static App getInstance()
    {
        if (instance == null)
        {
            instance = new App();
        }

        return instance;
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
