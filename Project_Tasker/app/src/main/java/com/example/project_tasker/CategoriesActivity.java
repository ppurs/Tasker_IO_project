package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.parceler.Parcels;

public class CategoriesActivity extends AppCompatActivity {

    static RecyclerView recViewCategories;
    private FloatingActionButton fabAddCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);
        this.setTitle( "Project: " + parentProject.getName() );

        recViewCategories = findViewById(R.id.recViewCategories);
        CategoriesRecViewAdapter categoriesAdapter = new CategoriesRecViewAdapter(this );
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
}