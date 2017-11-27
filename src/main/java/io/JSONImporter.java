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

/**
 * @author Liz Mahoney
 * @version 1.0
 */
public class JSONImporter {

    public boolean importTaskList(TodoModel todoList){
        FileReader fileReader = null;
        Gson gson = new Gson();
        try {
            fileReader = new FileReader("todo.json");
            Todo[] task = gson.fromJson(fileReader, Todo[].class);

            for(int i = 0; i<task.length; i++){
                todoList.addTodoList(task[i]);
            }
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
        return false;
    }

}
