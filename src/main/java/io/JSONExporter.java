/*Liz Mahoney
 *11/27/17
 *JSONExporter.java
 */

package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Todo;
import model.TodoModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class accepts a list of todo task and
 * @author Liz Mahoney
 * @version 1.0
 */
public class JSONExporter {

    /**
     * This method exports the todotask list to
     * convert a collection of car parts to a JSON file.
     *
     * @param toDoList a collection of todo tasks.
     * @return Returns false if there are no records to write,
     * otherwise true
     */
    public boolean exportTaskList(List<Todo> toDoList){
        try{
            FileWriter fileWriter = new FileWriter("todo.json");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Todo[] todoArray = new Todo[toDoList.size()];

            int counter = 0;
            for(Todo task: toDoList){
                todoArray[counter++] = task;
            }

            String stringJson = gson.toJson(todoArray);
            fileWriter.write(stringJson);
            fileWriter.close();
            return true;
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
