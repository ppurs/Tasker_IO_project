package com.example.project_tasker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class CategoriesActivity extends AppCompatActivity {

    private static RecyclerView recViewCategories;
    private FloatingActionButton fabAddCategory;
    private int parentProjectIndex;
    private Project parentProject;
    private TextView textName;
    private TextView textDescription;
    private TextView txtStatsProjectName;
    private TextView txtStatsTotalTasks;
    private TextView txtStatsTotalCards;
    private TextView txtStatsTotalCategories;
    private TextView txtStatsTasksFinished;
    private TextView txtStatsTasksUnfinished;
    private TextView txtStatsPercentageOfCompletion;
    private MemoryManager memoryManager;

    @Override
    protected void onRestart() {
        super.onRestart();
        this.setTitle( "Project: " + parentProject.getName() );

        if ( textName != null && textDescription != null ) {
            textName.setText( parentProject.getName() );
            textDescription.setText( parentProject.getDescription() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayHomeAsUpEnabled( false );
        }

        getMenuInflater().inflate(R.menu.categories_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        memoryManager = new MemoryManager();

        parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        parentProject = MainActivity.app.projects.get(parentProjectIndex);
        this.setTitle( "Project: " + parentProject.getName() );


        recViewCategories = findViewById(R.id.recViewCategories);
        CategoriesRecViewAdapter categoriesAdapter = new CategoriesRecViewAdapter(this, parentProjectIndex);
        categoriesAdapter.setCategories( parentProject.categories );

        recViewCategories.setAdapter(categoriesAdapter);
        recViewCategories.setLayoutManager(new LinearLayoutManager(this));

        fabAddCategory = findViewById(R.id.fabAddCategory);

        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( CategoriesActivity.this, AddCategoryActivity.class );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                startActivity( intent );
            }
        });
    }

    public static RecyclerView getRecViewCategories() {
        return recViewCategories;
    }

    public void showAlertDialogDelete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete project");
        builder.setMessage("Are you sure you want to delete this project?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.app.deleteProject( parentProjectIndex );
                ProjectsActivity.getRecViewProjects().getAdapter().notifyDataSetChanged();

                try {
                    memoryManager.saveDataToInternalStorage(CategoriesActivity.this);
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

                final Dialog dialog = new Dialog( CategoriesActivity.this );
                dialog.setContentView(R.layout.dialog_details);
                dialog.getWindow().setLayout( CategoriesActivity.this.getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

                textName = (TextView) dialog.findViewById(R.id.txtTitleName);
                textName.setText( parentProject.getName() );
                textDescription = (TextView) dialog.findViewById(R.id.txtDescription );
                textDescription.setText( parentProject.getDescription() );

                ImageButton editButton = (ImageButton) dialog.findViewById(R.id.imageButton );

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( dialog.getContext(), EditProjectActivity.class );
                        intent.putExtra( "parentProjectIndex", parentProjectIndex );
                        startActivity( intent );
                    }
                });

                dialog.show();
    }

    public void showDialogStatistics(View view)
    {
        Project project = MainActivity.app.projects.get(parentProjectIndex);

        final Dialog dialog = new Dialog( CategoriesActivity.this );
        dialog.setContentView(R.layout.statistics);
        dialog.getWindow().setLayout( CategoriesActivity.this.getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

        txtStatsProjectName = dialog.findViewById(R.id.txtStatsProjectName);
        txtStatsTotalTasks = dialog.findViewById(R.id.txtStatsTotalTasks);
        txtStatsTotalCards = dialog.findViewById(R.id.txtStatsTotalCards);
        txtStatsTotalCategories = dialog.findViewById(R.id.txtStatsTotalCategories);
        txtStatsTasksFinished = dialog.findViewById(R.id.txtStatsTasksFinished);
        txtStatsTasksUnfinished = dialog.findViewById(R.id.txtStatsTasksUnfinished);
        txtStatsPercentageOfCompletion = dialog.findViewById(R.id.txtStatsPercentageOfCompletion);

        int[] stats = project.generateStatistics();

        String percentageOfCompletion = "N/A";

        if (stats[0] > 0)
        {
            percentageOfCompletion = Double.toString(Math.round(10000.0 * (double) stats[3] / (double) stats[0]) / 100.0) + "%";
        }

        txtStatsProjectName.setText("Statistics for project: " + project.getName());
        txtStatsTotalTasks.setText("Total tasks: " + stats[0]);
        txtStatsTotalCards.setText("Total cards: " + stats[1]);
        txtStatsTotalCategories.setText("Total categories: " + stats[2]);
        txtStatsTasksFinished.setText("Tasks finished: " + stats[3]);
        txtStatsTasksUnfinished.setText("Tasks unfinished: " + stats[4]);
        txtStatsPercentageOfCompletion.setText("Percentage of completion: " + percentageOfCompletion);

        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.ic_delete:
                showAlertDialogDelete( this.findViewById(android.R.id.content) );
                return true;
            case R.id.ic_stats:
                showDialogStatistics( this.findViewById(android.R.id.content) );
                return true;
            case R.id.ic_info:
                showAlertDialogDetails( this.findViewById(android.R.id.content) );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}