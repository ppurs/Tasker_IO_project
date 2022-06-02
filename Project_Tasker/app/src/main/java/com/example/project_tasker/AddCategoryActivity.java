package com.example.project_tasker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText edtTextCategoryName;
    private EditText edtTextCategoryDescription;
    private Button btnAddCategoryConfirm;
    private Button btnSetColor;
    private MemoryManager memoryManager;
    private int colorChanged;

    public void showColorPicker(View view) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View promptView = layoutInflater.inflate(R.layout.dialog_color_picker, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose one color");
        builder.setNegativeButton("Default", new DialogInterface.OnClickListener()  {
            public void onClick(DialogInterface dialog, int which) {
                btnSetColor.setBackgroundColor(0);
            }
        });
        builder.setPositiveButton("Cancel", null);

        final AlertDialog dialog = builder.create();

        ImageButton oneColor = promptView.findViewById(R.id.imageButton1);
        oneColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.one_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton twoColor = promptView.findViewById(R.id.imageButton2);
        twoColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.two_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton threeColor = promptView.findViewById(R.id.imageButton3);
        threeColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.three_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton fourColor = promptView.findViewById(R.id.imageButton4);
        fourColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.four_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton fiveColor = promptView.findViewById(R.id.imageButton5);
        fiveColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.five_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton sixColor = promptView.findViewById(R.id.imageButton6);
        sixColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.six_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton sevenColor = promptView.findViewById(R.id.imageButton7);
        sevenColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.seven_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton eightColor = promptView.findViewById(R.id.imageButton8);
        eightColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.eight_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton nineColor = promptView.findViewById(R.id.imageButton9);
        nineColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.nine_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton tenColor = promptView.findViewById(R.id.imageButton10);
        tenColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.ten_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton elevenColor = promptView.findViewById(R.id.imageButton11);
        elevenColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.eleven_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton twelveColor = promptView.findViewById(R.id.imageButton12);
        twelveColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.twelve_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton thirteenColor = promptView.findViewById(R.id.imageButton13);
        thirteenColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.thirteen_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton fourteenColor = promptView.findViewById(R.id.imageButton14);
        fourteenColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.fourteen_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        ImageButton fifteenColor = promptView.findViewById(R.id.imageButton15);
        fifteenColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorChanged = getResources().getColor(R.color.fifteen_button_picker);
                btnSetColor.setBackgroundColor(colorChanged);
                dialog.cancel();
            }
        });

        dialog.setView(promptView);
        dialog.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Add new category" );
        setContentView(R.layout.activity_add_category);

        int parentProjectIndex = (int) getIntent().getExtras().get("parentProjectIndex");
        Project parentProject = MainActivity.app.projects.get(parentProjectIndex);

        memoryManager = new MemoryManager();

        edtTextCategoryName = findViewById(R.id.edtTextCategoryName);
        edtTextCategoryDescription = findViewById(R.id.edtTextCategoryDescription);
        btnAddCategoryConfirm = findViewById(R.id.btnAddCategoryConfirm);
        colorChanged = getResources().getColor(R.color.purple_500);

        btnSetColor = findViewById(R.id.btnSetColor);

        btnSetColor.setOnClickListener(new View.OnClickListener() {
            public void onClick( View view) {
                showColorPicker( findViewById( android.R.id.content ) );
            }
        });

        btnAddCategoryConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ( edtTextCategoryName.getText().toString().equals( "" ) ) {
                    Toast. makeText(getApplicationContext(),"Category name is required.",Toast.LENGTH_SHORT ).show();

                    return;
                }

                if ( !parentProject.addCategory( edtTextCategoryName.getText().toString(), edtTextCategoryDescription.getText().toString(), colorChanged )) {
                    Toast toast = Toast. makeText(getApplicationContext(),"Category with this name already exists.",Toast.LENGTH_SHORT );
                    toast.show();

                    return;
                }

                try {
                    memoryManager.saveDataToInternalStorage(AddCategoryActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
              
                CategoriesActivity.getRecViewCategories().getAdapter().notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Category added successfully",Toast.LENGTH_SHORT ).show();
                finish();
            }
        });
    }
}
