package com.example.project_tasker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksActivity extends AppCompatActivity {

    private static RecyclerView recViewTasks;
    private FloatingActionButton fabAddTask;
    private int parentProjectIndex;
    private Project parentProject;
    private int parentCategoryIndex;
    private Category parentCategory;
    private int parentCardIndex;
    private Card parentCard;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayHomeAsUpEnabled( false );
        }

        getMenuInflater().inflate(R.menu.cards_tasks_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        parentProject = MainActivity.app.projects.get(parentProjectIndex);

        parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);

        parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        parentCard = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex);

        this.setTitle( parentProject.getName() + ", " + parentCategory.getName() + ", " + parentCard.getName());

        recViewTasks = findViewById(R.id.recViewTasks);

        TasksRecViewAdapter tasksAdapter = new TasksRecViewAdapter(this, parentProjectIndex, parentCategoryIndex, parentCardIndex);
        tasksAdapter.setTasks( parentCard.tasks );

        recViewTasks.setAdapter(tasksAdapter);
        recViewTasks.setLayoutManager(new LinearLayoutManager(this));

        fabAddTask = findViewById(R.id.fabAddTask);

        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksActivity.this, AddTaskActivity.class);
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", parentCardIndex );
                startActivity(intent);
            }
        });
    }

    public static RecyclerView getRecViewTasks() {
        return recViewTasks;
    }

    public void showAlertDialogDelete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete card");
        builder.setMessage("Are you sure you want to delete this card?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                parentCategory.deleteCard( parentCardIndex );
                CardsActivity.getRecViewCards().getAdapter().notifyDataSetChanged();

                finish();

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.ic_delete:
                showAlertDialogDelete( this.findViewById(android.R.id.content) );
                return true;
            case R.id.ic_info:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}