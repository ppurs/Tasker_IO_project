package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private EditText edtTextTaskName;
    private EditText edtTextTaskDescription;
    private Button btnAddTaskConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new task" );
        setContentView(R.layout.activity_add_task);

        int parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");

        Card parentCard = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex);

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

                TasksActivity.getRecViewTasks().getAdapter().notifyDataSetChanged();
                finish();
            }
        });

    }
}