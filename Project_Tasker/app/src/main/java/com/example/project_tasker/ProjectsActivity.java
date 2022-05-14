package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView recViewProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recViewProjects = findViewById(R.id.recViewProjects);

        // TESTOWANIE

        ArrayList< Project > projects = new ArrayList<>();
        projects.add( new Project( "projekt1", "To jest teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
                                                        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeestowy opis", null));
        projects.add( new Project( "projekt2", "To jest testowy opis", null));
        projects.add( new Project( "projekt3", "To jest testowy opis", null));
        projects.add( new Project( "projekt4", "To jest testowy opis", null));
        projects.add( new Project( "projekt5", "To jest testowy opis", null));
        projects.add( new Project( "projekt6", "To jest testowy opis", null));
        projects.add( new Project( "projekt7", "To jest testowy opis", null));
        projects.add( new Project( "projekt8", "To jest testowy opis", null));

        // TESTOWANIE

        ProjectsRecViewAdapter projectsAdapter = new ProjectsRecViewAdapter(this);
        projectsAdapter.setProjects( projects );

        recViewProjects.setAdapter(projectsAdapter);
        recViewProjects.setLayoutManager(new LinearLayoutManager(this));
    }
}