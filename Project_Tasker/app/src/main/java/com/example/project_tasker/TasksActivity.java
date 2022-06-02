package com.example.project_tasker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class TasksActivity extends AppCompatActivity {

    private static RecyclerView recViewTasks;
    private FloatingActionButton fabAddTask;
    private int parentProjectIndex;
    private Project parentProject;
    private int parentCategoryIndex;
    private Category parentCategory;
    private int parentCardIndex;
    private Card parentCard;
    private TextView textName;
    private TextView textDescription;
    private MemoryManager memoryManager;
    private Task currTask;
    private int taskIndex;
    private TextView textNameTask;
    private TextView textDescriptionTask;

    @Override
    protected void onRestart() {
        super.onRestart();
        this.setTitle( parentCard.getName() );
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( parentCategory.getColor() ));

        if ( textName != null && textDescription != null ) {
            textName.setText( parentCard.getName() );
            textDescription.setText( parentCard.getDescription() );
        }

        if ( textNameTask != null && textDescriptionTask != null ) {
            textNameTask.setText( currTask.getName() );
            textDescriptionTask.setText( currTask.getDescription() );
        }
    }

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

        memoryManager = new MemoryManager();

        parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        parentProject = MainActivity.app.projects.get(parentProjectIndex);

        parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);

        parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        parentCard = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex);

        this.setTitle( parentProject.getName() + ", " + parentCategory.getName() + ", " + parentCard.getName());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( parentCategory.getColor() ));


        recViewTasks = findViewById(R.id.recViewTasks);

        TasksRecViewAdapter tasksAdapter = new TasksRecViewAdapter(this, parentProjectIndex, parentCategoryIndex, parentCardIndex, this );
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

                try {
                    memoryManager.saveDataToInternalStorage(TasksActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                finish();

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showAlertDialogDetails(View view ) {

        final Dialog dialog = new Dialog( TasksActivity.this );
        dialog.setContentView(R.layout.dialog_details);
        dialog.getWindow().setLayout( TasksActivity.this.getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

        textName = (TextView) dialog.findViewById(R.id.txtTitleName);
        textName.setText( parentCard.getName() );
        textDescription = (TextView) dialog.findViewById(R.id.txtDescription );
        textDescription.setText( parentCard.getDescription() );

        ImageButton editButton = (ImageButton) dialog.findViewById(R.id.imageButton );

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( dialog.getContext(), EditCardActivity.class );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", parentCardIndex );
                startActivity( intent );
            }
        });

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.ic_delete:
                showAlertDialogDelete( this.findViewById(android.R.id.content) );
                return true;
            case R.id.ic_info:
                showAlertDialogDetails( this.findViewById(android.R.id.content) );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAlertDialogDeleteTask(View view, Dialog prevDialog) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Delete task");
        builder.setMessage("Are you sure you want to delete this task?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex).deleteTask( taskIndex );
                dialog.cancel();
                prevDialog.cancel();

               try {
                    memoryManager.saveDataToInternalStorage(getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TasksActivity.getRecViewTasks().getAdapter().notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showTaskDetails( View view, int taskIndex ) {
        this.taskIndex = taskIndex;
        currTask = parentCard.tasks.get( taskIndex );

        final Dialog dialog = new Dialog( this );
        dialog.setContentView(R.layout.dialog_task_details);
        dialog.getWindow().setLayout( getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

        Spinner spinner = (Spinner) dialog.findViewById( R.id.spinnerPriority );
        Integer[] priorities = { 1, 2, 3, 4, 5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(dialog.getContext(), R.layout.custom_dropdown_list, priorities );
        spinner.setSelection( currTask.getPriority() - 1  );

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                currTask.setPriority((int) (spinner.getSelectedItem() ));
                setid();

                try {
                    memoryManager.saveDataToInternalStorage( getApplicationContext() );
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex).cards.get(parentCardIndex).sortTasksByPriority();
                TasksActivity.getRecViewTasks().getAdapter().notifyDataSetChanged();
            }

            private void setid() {
                spinner.setSelection( currTask.getPriority() - 1  );


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                spinner.setSelection( currTask.getPriority() - 1 );
            }
        });

        spinner.setAdapter(adapter);
        spinner.setSelection( currTask.getPriority() - 1 );

        textNameTask = (TextView) dialog.findViewById(R.id.txtTitleName);
        textNameTask.setText( currTask.getName() );
        textDescriptionTask = (TextView) dialog.findViewById(R.id.txtDescription );
        textDescriptionTask.setText( currTask.getDescription() );

        ImageButton editButton = (ImageButton) dialog.findViewById(R.id.btnEditTask );

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( dialog.getContext(), EditTaskActivity.class );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                intent.putExtra( "parentCardIndex", parentCardIndex );
                intent.putExtra( "parentTaskIndex", taskIndex );
                dialog.getContext().startActivity( intent );

            }
        });

        ImageButton deleteButton = (ImageButton) dialog.findViewById(R.id.btnDeleteTask );

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogDeleteTask( getRecViewTasks(), dialog );
            }
        });

        dialog.show();
    }
}