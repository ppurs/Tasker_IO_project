package com.example.project_tasker;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemoryManager
{
    synchronized void saveDataToInternalStorage(Context context) throws IOException
    {
        FileOutputStream fileOutputStream = context.openFileOutput("appData.txt", context.MODE_PRIVATE);
        StringBuilder textToSave = new StringBuilder();

        for( Project project : MainActivity.app.projects )
        {
            textToSave.append("<project>\n<name>\n").append(project.getName()).append("\n</name>\n<desc>\n").append(project.getDescription()).append("\n</desc>\n");

            for( Category category : project.categories )
            {
                textToSave.append("<cat>\n<name>\n").append(category.getName()).append("\n</name>\n<desc>\n").append(category.getDescription()).append("\n</desc>\n");

                for( Card card : category.cards )
                {
                    textToSave.append("<card>\n<name>\n").append(card.getName()).append("\n</name>\n<desc>\n").append(card.getDescription()).append("\n</desc>\n");

                    for( Task task : card.tasks )
                    {
                        textToSave.append("<task>\n<name>\n").append(task.getName()).append("\n</name>\n<desc>\n").append(task.getDescription()).append("\n</desc>\n</task>\n");
                    }

                    textToSave.append("</card>\n");
                }

                textToSave.append("</cat>\n");
            }

            textToSave.append("</project>\n");
        }

        fileOutputStream.write(textToSave.toString().getBytes());
        fileOutputStream.close();
    }

    synchronized void loadDataFromInternalStorage(Context context) throws IOException
    {
        FileInputStream fileInputStream = context.openFileInput("appData.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();

        String line;
        String name;
        String description;

        Project currProject = null;
        Category currCategory = null;
        Card currCard = null;

        while ((line = bufferedReader.readLine()) != null)
        {
            if (line.equals("<project>"))
            {
                bufferedReader.readLine();
                while (!(line = bufferedReader.readLine()).equals("</name>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                name = stringBuffer.toString();
                stringBuffer.setLength(0);

                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</desc>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                description = stringBuffer.toString();
                stringBuffer.setLength(0);

                currProject = new Project( name, description );
                MainActivity.app.projects.add( currProject );
            }

            else if (line.equals("<cat>"))
            {
                bufferedReader.readLine();
                while (!(line = bufferedReader.readLine()).equals("</name>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                name = stringBuffer.toString();
                stringBuffer.setLength(0);

                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</desc>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                description = stringBuffer.toString();
                stringBuffer.setLength(0);

                currCategory = new Category( name, description );
                currProject.categories.add( currCategory );
            }

            else if (line.equals("<card>"))
            {
                bufferedReader.readLine();
                while (!(line = bufferedReader.readLine()).equals("</name>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                name = stringBuffer.toString();
                stringBuffer.setLength(0);

                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</desc>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                description = stringBuffer.toString();
                stringBuffer.setLength(0);

                currCard = new Card( name, description );
                currCategory.cards.add( currCard );
            }

            else if (line.equals("<task>"))
            {
                bufferedReader.readLine();
                while (!(line = bufferedReader.readLine()).equals("</name>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                name = stringBuffer.toString();
                stringBuffer.setLength(0);

                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</desc>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                description = stringBuffer.toString();
                stringBuffer.setLength(0);

                currCard.tasks.add( new Task(name, description) );
            }
        }
    }

    static public void deleteNewLines(StringBuffer sb)
    {
        while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '\n')
        {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    void displayFileContents(String filename, Context context) throws IOException {
        FileInputStream fileInputStream = context.openFileInput(filename);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();

        String line;

        while ((line = bufferedReader.readLine()) != null)
        {
            stringBuffer.append(line + "\n");
        }

        System.out.println("PLIK\n" + stringBuffer.toString());
    }
}
