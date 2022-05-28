package com.example.project_tasker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CategoriesActivity extends AppCompatActivity {

    static RecyclerView recViewCategories;
    private FloatingActionButton fabAddCategory;
    private int parentProjectIndex;
    private MenuItem mItem;

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

        parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);
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

    public void showAlertDialogDelete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete project");
        builder.setMessage("Are you sure you want to delete this project?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.app.deleteProject( parentProjectIndex );
                Intent intent = new Intent( CategoriesActivity.this, ProjectsActivity.class );
                startActivity( intent );
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
            case R.id.ic_stats:
                //costam cos tam
                return true;
            case R.id.ic_info:
                //costam cos tam
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}