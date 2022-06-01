package com.example.project_tasker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class EditCardActivity extends AppCompatActivity {
    private EditText edtTextCardName;
    private EditText edtTextCardDescription;
    private Button btnEditCardConfirm;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Edit card" );
        setContentView(R.layout.activity_edit_card);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project project = MainActivity.app.projects.get( parentProjectIndex );

        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        Category category = project.categories.get( parentCategoryIndex );

        int parentCardIndex = (int) getIntent().getExtras().get("parentCardIndex");
        Card card = category.cards.get( parentCardIndex );

        memoryManager = new MemoryManager();

        edtTextCardName = findViewById(R.id.edtTextCardName);
        edtTextCardName.setText( card.getName() );

        edtTextCardDescription = findViewById(R.id.edtTextCardDescription);
        edtTextCardDescription.setText( card.getDescription() );

        btnEditCardConfirm = findViewById(R.id.btnEditCardConfirm);

        btnEditCardConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( edtTextCardName.getText().toString().equals( "" ) ) {
                    Toast.makeText(getApplicationContext(),"Card name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !category.validation( edtTextCardName.getText().toString() ) && !edtTextCardName.getText().toString().equals( card.getName() ) ) {
                    Toast.makeText(getApplicationContext(),"Card with this name already exists.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                card.editName( edtTextCardName.getText().toString() );
                card.editDescription( edtTextCardDescription.getText().toString() );

                try {
                    memoryManager.saveDataToInternalStorage(EditCardActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                CardsActivity.getRecViewCards().getAdapter().notifyDataSetChanged();

                finish();
            }
        });
    }
}
