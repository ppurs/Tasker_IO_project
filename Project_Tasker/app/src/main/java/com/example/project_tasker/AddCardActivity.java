package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class AddCardActivity extends AppCompatActivity {

    private EditText edtTextCardName;
    private EditText edtTextCardDescription;
    private Button btnAddCardConfirm;
    private MemoryManager memoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new card" );
        setContentView(R.layout.activity_add_card);

        int parentCategoryIndex = (int) getIntent().getExtras().get("parentCategoryIndex");
        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");

        Category parentCategory = MainActivity.app.projects.get(parentProjectIndex).categories.get(parentCategoryIndex);

        memoryManager = new MemoryManager();

        edtTextCardName = findViewById(R.id.edtTextCardName);
        edtTextCardDescription = findViewById(R.id.edtTextCardDescription);
        btnAddCardConfirm = findViewById(R.id.btnAddCardConfirm);

        btnAddCardConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtTextCardName.getText().toString().equals( "" ) ) {
                    Toast.makeText(getApplicationContext(),"Card name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !parentCategory.addCard( edtTextCardName.getText().toString(), edtTextCardDescription.getText().toString() )) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Card with this name already exists.",Toast.LENGTH_SHORT );
                    toast.show();

                    return;
                }

                try {
                    memoryManager.saveDataToInternalStorage(AddCardActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                CardsActivity.getRecViewCards().getAdapter().notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Card added successfully",Toast.LENGTH_SHORT ).show();
                finish();
            }
        });
    }
}