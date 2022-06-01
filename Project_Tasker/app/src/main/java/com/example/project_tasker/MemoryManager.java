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
                textToSave.append("<cat>\n<name>\n").append(category.getName()).append("\n</name>\n<desc>\n").append(category.getDescription()).append("\n</desc>\n<color>\n")
                          .append(Integer.toString(category.getColor())).append("\n</color>\n");

                for( Card card : category.cards )
                {
                    textToSave.append("<card>\n<name>\n").append(card.getName()).append("\n</name>\n<desc>\n").append(card.getDescription()).append("\n</desc>\n");

                    for( Task task : card.tasks )
                    {
                        textToSave.append("<task>\n<name>\n").append(task.getName()).append("\n</name>\n<desc>\n").append(task.getDescription()).append("\n</desc>\n<status>\n");
                        if (!task.getStatus())
                        {
                            textToSave.append("0");
                        }
                        else
                        {
                            textToSave.append("1");
                        }
                        textToSave.append("\n</status>\n<priority>\n").append(Integer.toString(task.getPriority())).append("\n</priority>\n<date>\n").append(task.getDeadline().toString())
                                  .append("\n</date>\n</task>\n");
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
        String color;
        String status;
        String priority;
        String date;

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

                bufferedReader.readLine();
                while (!(line = bufferedReader.readLine()).equals("</color>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                color = stringBuffer.toString();
                stringBuffer.setLength(0);

                currCategory = new Category( name, description );
                currCategory.setColor(Integer.parseInt(color));
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
                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</status>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                status = stringBuffer.toString();
                stringBuffer.setLength(0);
                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</priority>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                priority = stringBuffer.toString();
                stringBuffer.setLength(0);
                bufferedReader.readLine();

                while (!(line = bufferedReader.readLine()).equals("</date>"))
                {
                    stringBuffer.append(line + "\n");
                }

                deleteNewLines(stringBuffer);
                date = stringBuffer.toString();
                stringBuffer.setLength(0);

                Task newTask = new Task(name, description);
                newTask.setPriority(Integer.parseInt(priority));
                newTask.setStatus(status.equals("1"));

                String[] dateStr = date.split("\\s+");

                Date deadline = newTask.getDeadline();
                deadline.setDay(Integer.parseInt(dateStr[0]));
                deadline.setMonth(Integer.parseInt(dateStr[1]));
                deadline.setYear(Integer.parseInt(dateStr[2]));

                currCard.tasks.add( newTask );
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
