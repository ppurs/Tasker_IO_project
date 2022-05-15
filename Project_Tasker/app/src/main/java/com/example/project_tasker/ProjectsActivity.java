package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView recViewProjects;
    private FloatingActionButton fabAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recViewProjects = findViewById(R.id.recViewProjects);

        ProjectsRecViewAdapter projectsAdapter = new ProjectsRecViewAdapter(this);
        projectsAdapter.setProjects( MainActivity.app.projects );

        recViewProjects.setAdapter(projectsAdapter);
        recViewProjects.setLayoutManager(new LinearLayoutManager(this));

        fabAddProject = findViewById(R.id.fabAddProject);

        fabAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity.app.addProject(ProjectsActivity.this);
                //jakos to przeniesc do app.addProject() bo na razie to nie wiem po co ona jest XDDDDD

                //chyba sie nie da xDDDDD, ale inne rzeczy wleciały tam wiec git
                Intent intent = new Intent(ProjectsActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });
    }
}