package com.example.project_tasker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class EditProjectActivity extends AppCompatActivity {
    private EditText edtTextProjectName;
    private EditText edtTextProjectDescription;
    private Button btnEditProjectConfirm;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Edit project" );
        setContentView(R.layout.activity_edit_project);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project project = MainActivity.app.projects.get( parentProjectIndex );

        memoryManager = new MemoryManager();

        edtTextProjectName = findViewById(R.id.edtTextProjectName);
        edtTextProjectName.setText( project.getName() );

        edtTextProjectDescription = findViewById(R.id.edtTextProjectDescription);
        edtTextProjectDescription.setText( project.getDescription() );

        btnEditProjectConfirm = findViewById(R.id.btnEditProjectConfirm);

        btnEditProjectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( edtTextProjectName.getText().toString().equals( "" ) ) {
                    Toast. makeText(getApplicationContext(),"Project name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !MainActivity.app.validation( edtTextProjectName.getText().toString() ) && !edtTextProjectName.getText().toString().equals( project.getName() ) ) {
                    Toast. makeText(getApplicationContext(),"Project with this name already exists.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                project.editName( edtTextProjectName.getText().toString() );
                project.editDescription( edtTextProjectDescription.getText().toString() );

                try {
                    memoryManager.saveDataToInternalStorage(EditProjectActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ProjectsActivity.getRecViewProjects().getAdapter().notifyDataSetChanged();

                finish();

            }
        });
    }
}
