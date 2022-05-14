package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProjectActivity extends AppCompatActivity {

    private EditText edtTextProjectName;
    private EditText edtTextProjectDescription;
    private Button btnAddProjectConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        edtTextProjectName = findViewById(R.id.edtTextProjectName);
        edtTextProjectDescription = findViewById(R.id.edtTextProjectDescription);
        btnAddProjectConfirm = findViewById(R.id.btnAddProjectConfirm);

        btnAddProjectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sprawdzenie unikalnosci

                MainActivity.app.projects.add( new Project(edtTextProjectName.getText().toString(),
                                               edtTextProjectDescription.getText().toString(), null));
                finish();
            }
        });
    }
}