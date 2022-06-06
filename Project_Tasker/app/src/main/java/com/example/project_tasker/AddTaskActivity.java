package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class AddTaskActivity extends AppCompatActivity {

    private EditText edtTextTaskName;
    private EditText edtTextTaskDescription;
    private Button btnAddTaskConfirm;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new task" );
        setContentView(R.layout.activity_add_task);

        int parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");

        Card parentCard = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex);

        memoryManager = new MemoryManager();

        edtTextTaskName = findViewById(R.id.edtTextTaskName);
        edtTextTaskDescription = findViewById(R.id.edtTextTaskDescription);
        btnAddTaskConfirm = findViewById(R.id.btnAddTaskConfirm);

        btnAddTaskConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtTextTaskName.getText().toString().equals( "" ) ) {
                    Toast.makeText(getApplicationContext(),"Task name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !parentCard.addTask( edtTextTaskName.getText().toString(), edtTextTaskDescription.getText().toString() )) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Task with this name already exists.",Toast.LENGTH_SHORT );
                    toast.show();

                    return;
                }

                try {
                    memoryManager.saveDataToInternalStorage(AddTaskActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                parentCard.sortTasksByPriority();

                TasksActivity.getRecViewTasks().getAdapter().notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Task added successfully",Toast.LENGTH_SHORT ).show();
                finish();
            }
        });
    }
}