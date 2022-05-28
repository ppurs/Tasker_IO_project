package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CardsActivity extends AppCompatActivity {

    private static RecyclerView recViewCards;
    private FloatingActionButton fabAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);

        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        Category parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);
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
}