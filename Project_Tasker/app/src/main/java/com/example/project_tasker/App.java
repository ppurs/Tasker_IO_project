package com.example.project_tasker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class App {
    ArrayList<Project> projects;

    public App()
    {
        projects = new ArrayList<>();
    }

    boolean addProject(String projectName, String projectDescription ) {
        for (Project temp : projects) {
            if (temp.name.equals( projectName ) ) {
                return false;
                }
            }

        projects.add( new Project( projectName, projectDescription, null));
        return true;
    }


    void deleteProject(){}
    void sendNotifications(){}
}
