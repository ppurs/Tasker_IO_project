package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class AddProjectActivity extends AppCompatActivity {

    private EditText edtTextProjectName;
    private EditText edtTextProjectDescription;
    private Button btnAddProjectConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new project" );
        setContentView(R.layout.activity_add_project);

        edtTextProjectName = findViewById(R.id.edtTextProjectName);
        edtTextProjectDescription = findViewById(R.id.edtTextProjectDescription);
        btnAddProjectConfirm = findViewById(R.id.btnAddProjectConfirm);

        btnAddProjectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !MainActivity.app.addProject( edtTextProjectName.getText().toString(), edtTextProjectDescription.getText().toString() )) {
                    Toast toast = Toast. makeText(getApplicationContext(),"Project with this name already exists.",Toast.LENGTH_SHORT );
                    toast.show();

                    return;
                }

                finish();
            }
        });
    }
}