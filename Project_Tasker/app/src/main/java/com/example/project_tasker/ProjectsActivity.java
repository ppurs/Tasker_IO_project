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
        this.setTitle("Projects");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recViewProjects = findViewById(R.id.recViewProjects);

        loadFromInternalStorage();

        ProjectsRecViewAdapter projectsAdapter = new ProjectsRecViewAdapter(this);
        projectsAdapter.setProjects( MainActivity.app.projects );

        recViewProjects.setAdapter(projectsAdapter);
        recViewProjects.setLayoutManager(new LinearLayoutManager(this));

        fabAddProject = findViewById(R.id.fabAddProject);

        fabAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectsActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    void loadFromInternalStorage()
    {
        try
        {
            FileInputStream fileInputStream = openFileInput("appData.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String line;
            String name;
            String description;

            while ((line = bufferedReader.readLine()) != null)
            {
                if (line.equals("<project>"))
                {
                    name = "";
                    description = "";

                    bufferedReader.readLine();
                    while (!(line = bufferedReader.readLine()).equals("</name>"))
                    {
                        stringBuffer.append(line + "\n");
                    }

                    name = stringBuffer.toString();
                    stringBuffer.delete(0, stringBuffer.length());

                    bufferedReader.readLine();

                    while (!(line = bufferedReader.readLine()).equals("</desc>"))
                    {
                        stringBuffer.append(line + "\n");
                    }

                    description = stringBuffer.toString();
                    stringBuffer.delete(0, stringBuffer.length());

                    MainActivity.app.projects.add( new Project( name, description ) );
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}