package com.example.project_tasker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
                if ( edtTextCategoryName.getText().toString().equals( "" ) ) {
                    Toast. makeText(getApplicationContext(),"Category name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !parentProject.addCategory( edtTextCategoryName.getText().toString(), edtTextCategoryDescription.getText().toString() )) {
                    Toast toast = Toast. makeText(getApplicationContext(),"Category with this name already exists.",Toast.LENGTH_SHORT );
                    toast.show();

                    return;
                }

                try {
                    saveNewCategoryInInternalStorage(edtTextCategoryName.getText().toString(), edtTextCategoryDescription.getText().toString(),parentProject.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
              
                CategoriesActivity.getRecViewCategories().getAdapter().notifyDataSetChanged();
                finish();
            }
        });
    }

    void saveNewCategoryInInternalStorage(String name, String description, String projectName) throws IOException
    {
        //TODO odnajdz prawidlowy folder

//        File projectDir = getFilesDir();
//        projectDir = new File(projectDir, projectName);
//        File newDir = new File(projectDir, name);
//        newDir.mkdirs();
//
//        System.out.println("jajco\n\n" + newDir.toString() + "\n\n" + projectDir.toString());
//
//        File data = new File(newDir, "data.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(data);
//
//        String textToSave = name + "\n</name>\n" + description;
//        fileOutputStream.write(textToSave.getBytes());
//        fileOutputStream.close();
    }
}
