package com.example.project_tasker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class EditTaskActivity extends AppCompatActivity {
    private EditText edtTextTaskName;
    private EditText edtTextTaskDescription;
    private Button btnEditTaskConfirm;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Edit task" );
        setContentView(R.layout.activity_edit_task);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project project = MainActivity.app.projects.get( parentProjectIndex );

        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        Category category = project.categories.get( parentCategoryIndex );

        int parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        Card card = category.cards.get( parentCardIndex );

        int parentTaskIndex = (int) getIntent().getExtras().get("parentTaskIndex");
        Task task = card.tasks.get( parentTaskIndex );

        memoryManager = new MemoryManager();

        edtTextTaskName = findViewById(R.id.edtTextTaskName);
        edtTextTaskName.setText( task.getName() );

        edtTextTaskDescription = findViewById(R.id.edtTextTaskDescription);
        edtTextTaskDescription.setText( task.getDescription() );

        btnEditTaskConfirm = findViewById(R.id.btnEditTaskConfirm);

        btnEditTaskConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtTextTaskName.getText().toString().equals( "" ) ) {
                    Toast.makeText(getApplicationContext(),"Task name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !card.validation( edtTextTaskName.getText().toString() ) && !edtTextTaskName.getText().toString().equals( task.getName() ) ) {
                    Toast.makeText(getApplicationContext(),"Task with this name already exists.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                task.editName( edtTextTaskName.getText().toString() );
                task.editDescription( edtTextTaskDescription.getText().toString() );

                try {
                    memoryManager.saveDataToInternalStorage(EditTaskActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TasksActivity.getRecViewTasks().getAdapter().notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Task edited successfully",Toast.LENGTH_SHORT ).show();
                finish();
            }
        });
    }
}
