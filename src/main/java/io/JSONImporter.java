/*
 *Liz Mahoney
 *11/27/17
 *JSONImporter.java
 */

package io;

import com.google.gson.Gson;
import model.Todo;
import model.TodoModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Liz Mahoney
 * @version 1.0
 */
public class JSONImporter {

    public List<Todo> importTaskList(){

        List<Todo> todoList = null;
        FileReader fileReader = null;
        Gson gson = new Gson();
        try {
            fileReader = new FileReader("todo.json");
            Todo[] task = gson.fromJson(fileReader, Todo[].class);

            todoList = Arrays.asList(task);

        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(fileReader != null){
                    fileReader.close();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return todoList;
    }

}
