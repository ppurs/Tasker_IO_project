package com.example.project_tasker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksActivity extends AppCompatActivity {

    private static RecyclerView recViewTasks;
    private FloatingActionButton fabAddTask;

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

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);

        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        Category parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);

        int parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        Card parentCard = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.ic_delete:
                return true;
            case R.id.ic_info:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}