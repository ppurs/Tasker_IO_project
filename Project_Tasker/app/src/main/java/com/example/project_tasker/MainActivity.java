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

//        try {
//            saveNewProjectInInternalStorage("projekt", "opis");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            saveNewProjectInInternalStorage("projekt2", "opis2");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

    void loadDataFromInternalStorage() throws IOException
    {
        File projectsDir = getFilesDir();
        File[] projects = projectsDir.listFiles();

        String projectName;
        String categoryName;
        String cardName;
        String taskName;

        String projectPath;
        String categoryPath;
        String cardPath;
        String taskPath;

        String line;
        String name;
        String description;
        String color;
        String status;
        String priority;
        String deadline;

        Project currProject;
        Category currCategory;
        Card currCard;

        for( File project : projects )
        {
            projectName = project.getName();
            projectPath = getFilesDir() + "/" + projectName;

            FileInputStream fileInputStream = new FileInputStream(projectPath + "/data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while (!(line = bufferedReader.readLine()).equals("</name>"))
            {
                stringBuffer.append(line + "\n");
            }

            //TODO stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            name = stringBuffer.toString();
            stringBuffer.delete(0, stringBuffer.length());

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + "\n");
            }

            description = stringBuffer.toString();

            currProject = new Project(name, description);
            app.projects.add(currProject);

            File[] categories = project.listFiles();

            for ( File category : categories )
            {
                if ( category.isDirectory() )
                {
                    categoryName = category.getName();
                    categoryPath = projectPath + "/" + categoryName;

                    fileInputStream = new FileInputStream(categoryPath + "/data.txt");
                    inputStreamReader = new InputStreamReader(fileInputStream);

                    bufferedReader = new BufferedReader(inputStreamReader);
                    stringBuffer = new StringBuffer();

                    while (!(line = bufferedReader.readLine()).equals("</name>"))
                    {
                        stringBuffer.append(line + "\n");
                    }

                    name = stringBuffer.toString();
                    stringBuffer.delete(0, stringBuffer.length());

                    while (!(line = bufferedReader.readLine()).equals("</desc>"))
                    {
                        stringBuffer.append(line + "\n");
                    }

                    description = stringBuffer.toString();
                    stringBuffer.delete(0, stringBuffer.length());

                    while ((line = bufferedReader.readLine()) != null)
                    {
                        stringBuffer.append(line + "\n");
                    }

                    color = stringBuffer.toString();

                    //TODO dodac ten kolor
                    currCategory = new Category(name, description);
                    currProject.categories.add(currCategory);

                    File[] cards = category.listFiles();

                    for ( File card : cards )
                    {
                        if ( card.isDirectory() )
                        {
                            cardName = card.getName();
                            cardPath = categoryPath + "/" + cardName;

                            fileInputStream = new FileInputStream(cardPath + "/data.txt");
                            inputStreamReader = new InputStreamReader(fileInputStream);

                            bufferedReader = new BufferedReader(inputStreamReader);
                            stringBuffer = new StringBuffer();

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

                            currCard = new Card(name, description);
                            currCategory.cards.add(currCard);

                            File[] tasks = card.listFiles();

                            for ( File task : tasks )
                            {
                                if ( task.isDirectory() )
                                {
                                    taskName = task.getName();
                                    taskPath = cardPath + "/" + taskName;

                                    fileInputStream = new FileInputStream(taskPath + "/data.txt");
                                    inputStreamReader = new InputStreamReader(fileInputStream);

                                    bufferedReader = new BufferedReader(inputStreamReader);
                                    stringBuffer = new StringBuffer();

                                    while (!(line = bufferedReader.readLine()).equals("</name>"))
                                    {
                                        stringBuffer.append(line + "\n");
                                    }

                                    name = stringBuffer.toString();
                                    stringBuffer.delete(0, stringBuffer.length());

                                    while (!(line = bufferedReader.readLine()).equals("</desc>"))
                                    {
                                        stringBuffer.append(line + "\n");
                                    }

                                    description = stringBuffer.toString();
                                    stringBuffer.delete(0, stringBuffer.length());

                                    while (!(line = bufferedReader.readLine()).equals("</status>"))
                                    {
                                        stringBuffer.append(line + "\n");
                                    }

                                    status = stringBuffer.toString();
                                    stringBuffer.delete(0, stringBuffer.length());

                                    while (!(line = bufferedReader.readLine()).equals("</priority>"))
                                    {
                                        stringBuffer.append(line + "\n");
                                    }

                                    priority = stringBuffer.toString();
                                    stringBuffer.delete(0, stringBuffer.length());

                                    while ((line = bufferedReader.readLine()) != null)
                                    {
                                        stringBuffer.append(line + "\n");
                                    }

                                    deadline = stringBuffer.toString();

                                    //TODO dodaj status, priorytet, deadline
                                    currCard.tasks.add(new Task(name, description));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    void saveNewProjectInInternalStorage(String name, String description) throws IOException
//    {
//        File newDir = new File(getFilesDir(), name);
//        newDir.mkdirs();
//
//        File data = new File(newDir, "data.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(data);
//
//        String textToSave = name + "\n</name>\n" + description;
//        fileOutputStream.write(textToSave.getBytes());
//        fileOutputStream.close();
//    }


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