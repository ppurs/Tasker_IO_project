package com.example.project_tasker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText edtTextCategoryName;
    private EditText edtTextCategoryDescription;
    private Button btnAddCategoryConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new category" );
        setContentView(R.layout.activity_add_category);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);

        edtTextCategoryName = findViewById(R.id.edtTextCategoryName);
        edtTextCategoryDescription = findViewById(R.id.edtTextCategoryDescription);
        btnAddCategoryConfirm = findViewById(R.id.btnAddCategoryConfirm);

        btnAddCategoryConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( !parentProject.addCategory( edtTextCategoryName.getText().toString(), edtTextCategoryDescription.getText().toString() )) {
                    Toast toast = Toast. makeText(getApplicationContext(),"Category with this name already exists.",Toast.LENGTH_SHORT );
                    toast.show();

                    return;
                }

                finish();
            }
        });
    }

}
