package com.example.project_tasker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class CardsActivity extends AppCompatActivity {

    private static RecyclerView recViewCards;
    private FloatingActionButton fabAddCard;
    private int parentProjectIndex;
    private Project parentProject;
    private int parentCategoryIndex;
    private Category parentCategory;
    private TextView textName;
    private TextView textDescription;
    private MemoryManager memoryManager;

    @Override
    protected void onRestart() {
        super.onRestart();
        this.setTitle( "Category: " + parentCategory.getName() );
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( parentCategory.getColor() ));

        if ( textName != null && textDescription != null ) {
            textName.setText( parentCategory.getName() );
            textDescription.setText( parentCategory.getDescription() );
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
        setContentView(R.layout.activity_cards);

        memoryManager = new MemoryManager();

        parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        parentProject = MainActivity.app.projects.get(parentProjectIndex);

        parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);
        this.setTitle( "Category: " + parentCategory.getName());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable( parentCategory.getColor() ));

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

                try {
                    memoryManager.saveDataToInternalStorage(CardsActivity.this);
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

        final Dialog dialog = new Dialog( CardsActivity.this );
        dialog.setContentView(R.layout.dialog_details);
        dialog.getWindow().setLayout( CardsActivity.this.getWindow().peekDecorView().getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT );

        textName = (TextView) dialog.findViewById(R.id.txtTitleName);
        textName.setText( parentCategory.getName() );
        textDescription = (TextView) dialog.findViewById(R.id.txtDescription );
        textDescription.setText( parentCategory.getDescription() );

        ImageButton editButton = (ImageButton) dialog.findViewById(R.id.imageButton );

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( dialog.getContext(), EditCategoryActivity.class );
                intent.putExtra( "parentProjectIndex", parentProjectIndex );
                intent.putExtra( "parentCategoryIndex", parentCategoryIndex );
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}