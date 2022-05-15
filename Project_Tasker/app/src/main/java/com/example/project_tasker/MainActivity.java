package com.example.project_tasker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    private Button btnProjects;
    public static App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = new App();
        try
        {
            loadDataFromInternalStorage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        btnProjects = findViewById(R.id.btnProjects);

        btnProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean fileExist(String fileName)
    {
        File file = getFileStreamPath(fileName);
        return file.exists();
    }

    void loadDataFromInternalStorage() throws IOException {
        File myDir = getFilesDir();
        File projectsDir = new File(myDir, "projects");

        if (!fileExist("projects"))
        {
            projectsDir.mkdirs();
        }
        else
        {
            File[] projects = projectsDir.listFiles();

            for( File project : projects )
            {
                FileInputStream fileInputStream = openFileInput(project.getAbsolutePath().toString() + "/data.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();

                String line;
                String name = "";
                String description = "";

                while (!(line = bufferedReader.readLine()).equals("</name>"))
                {
                    stringBuffer.append(line + "\n");
                }

                name = stringBuffer.toString();
                stringBuffer.delete(0, stringBuffer.length());

                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuffer.append(line + "\n");
                }

                description = stringBuffer.toString();

                app.projects.add(new Project(name, description));
            }
        }
    }

    // nie moge tego nigdzie wywolac
    void saveNewProjectInInternalStorage(String name, String description) throws IOException
    {
        FileOutputStream fileOutputStream = openFileOutput("projects/" + name + "/data.txt", MODE_PRIVATE);
        String textToSave = name + "\n</name>\n" + description;

        fileOutputStream.write(textToSave.getBytes());
        fileOutputStream.close();
    }


//    void loadDataFromInternalStorage()
//    {
//        try
//        {
//            FileInputStream fileInputStream = openFileInput("appData.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            StringBuffer stringBuffer = new StringBuffer();
//
//            String line;
//            String name;
//            String description;
//
//            while ((line = bufferedReader.readLine()) != null)
//            {
//                if (line.equals("<project>"))
//                {
//                    name = "";
//                    description = "";
//
//                    bufferedReader.readLine();
//                    while (!(line = bufferedReader.readLine()).equals("</name>"))
//                    {
//                        stringBuffer.append(line + "\n");
//                    }
//
//                    name = stringBuffer.toString();
//                    stringBuffer.delete(0, stringBuffer.length());
//
//                    bufferedReader.readLine();
//
//                    while (!(line = bufferedReader.readLine()).equals("</desc>"))
//                    {
//                        stringBuffer.append(line + "\n");
//                    }
//
//                    description = stringBuffer.toString();
//                    stringBuffer.delete(0, stringBuffer.length());
//
//                    MainActivity.app.projects.add( new Project( name, description, null ) );
//                }
//            }
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}