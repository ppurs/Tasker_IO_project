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
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new category" );
        setContentView(R.layout.activity_add_category);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);

        memoryManager = new MemoryManager();

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

//                try {
//                    saveNewCategoryInInternalStorage(edtTextCategoryName.getText().toString(), edtTextCategoryDescription.getText().toString(),parentProject.getName());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                try {
                    memoryManager.saveDataToInternalStorage(AddCategoryActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
              
                CategoriesActivity.getRecViewCategories().getAdapter().notifyDataSetChanged();
                finish();
            }
        });
    }

    void saveDataToInternalStorage() throws IOException
    {
        //TODO dodac wszystko poza name i desc do pliku appData.txt
        //TODO jak description to \n to nie dodawac kolejnego \n
        FileOutputStream fileOutputStream = openFileOutput("appData.txt", MODE_PRIVATE);
        StringBuilder textToSave = new StringBuilder();

        for( Project project : MainActivity.app.projects )
        {
            textToSave.append("<project>\n<name>\n").append(project.getName()).append("\n</name>\n<desc>\n").append(project.getDescription()).append("\n</desc>\n");

            for( Category category : project.categories )
            {
                textToSave.append("<cat>\n<name>\n").append(category.getName()).append("\n</name>\n<desc>\n").append(category.getDescription()).append("\n</desc>\n");

                for( Card card : category.cards )
                {
                    textToSave.append("<card>\n<name>\n").append(card.getName()).append("\n</name>\n<desc>\n").append(card.getDescription()).append("\n</desc>\n");

                    for( Task task : card.tasks )
                    {
                        textToSave.append("<task>\n<name>\n").append(task.getName()).append("\n</name>\n<desc>\n").append(task.getDescription()).append("\n</desc>\n</task>\n");
                    }

                    textToSave.append("</card>\n");
                }

                textToSave.append("</cat>\n");
            }

            textToSave.append("</project>\n");
        }

        fileOutputStream.write(textToSave.toString().getBytes());
        fileOutputStream.close();
    }
}
