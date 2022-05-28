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

public class CardsActivity extends AppCompatActivity {

    private static RecyclerView recViewCards;
    private FloatingActionButton fabAddCard;
    private int parentProjectIndex;
    private Project parentProject;
    private int parentCategoryIndex;
    private Category parentCategory;

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
        setContentView(R.layout.activity_cards);

        parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        parentProject = MainActivity.app.projects.get(parentProjectIndex);

        parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);
        this.setTitle( "Project: " + parentProject.getName() + ", Category: " + parentCategory.getName());

        recViewCards = findViewById(R.id.recViewCards);

        CardsRecViewAdapter cardsAdapter = new CardsRecViewAdapter(this, parentProjectIndex, parentCategoryIndex);
        cardsAdapter.setCards( parentCategory.cards );

        recViewCards.setAdapter(cardsAdapter);
        recViewCards.setLayoutManager(new GridLayoutManager(this, 2));

        fabAddCard = findViewById(R.id.fabAddCard);

        fabAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardsActivity.this, AddCardActivity.class);
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
                startActivity(intent);
            }
        });
    }

    public static RecyclerView getRecViewCards() {
        return recViewCards;
    }

    public void showAlertDialogDelete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete category");
        builder.setMessage("Are you sure you want to delete this category?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                parentProject.deleteCategory( parentCategoryIndex );
                CategoriesActivity.getRecViewCategories().getAdapter().notifyDataSetChanged();

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