package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddProjectActivity extends AppCompatActivity {

    private EditText edtTextProjectName;
    private EditText edtTextProjectDescription;
    private Button btnAddProjectConfirm;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new project" );
        setContentView(R.layout.activity_add_project);

        memoryManager = new MemoryManager();

        edtTextProjectName = findViewById(R.id.edtTextProjectName);
        edtTextProjectDescription = findViewById(R.id.edtTextProjectDescription);
        btnAddProjectConfirm = findViewById(R.id.btnAddProjectConfirm);

        btnAddProjectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( edtTextProjectName.getText().toString().equals( "" ) ) {
                    Toast. makeText(getApplicationContext(),"Project name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !MainActivity.app.addProject( edtTextProjectName.getText().toString(), edtTextProjectDescription.getText().toString() )) {
                    Toast. makeText(getApplicationContext(),"Project with this name already exists.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                try {
                    memoryManager.saveDataToInternalStorage(AddProjectActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ProjectsActivity.getRecViewProjects().getAdapter().notifyDataSetChanged();
                finish();
            }
        });
    }
}