package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProjectsActivity extends AppCompatActivity {

    private static RecyclerView recViewProjects;
    private FloatingActionButton fabAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Projects");

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
                if (MainActivity.app.projects.size() < MainActivity.app.MAX_PROJECTS_COUNT)
                {
                    Intent intent = new Intent(ProjectsActivity.this, AddProjectActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Limit of the number of created projects (" + MainActivity.app.MAX_PROJECTS_COUNT + ") reached",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static RecyclerView getRecViewProjects() {
        return recViewProjects;
    }
}