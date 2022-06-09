package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnProjects;
    public static App app;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MemoryManager memoryManager = new MemoryManager();
        app = App.getInstance();

        try {
            memoryManager.displayFileContents("appData.txt", this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            memoryManager.loadDataFromInternalStorage(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        btnProjects = findViewById(R.id.btnProjects);

        btnProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}