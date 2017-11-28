/*Liz Mahoney
 *11/20/17
 *TodoModel.java
 */
package model;

import com.google.common.collect.Lists;
import io.JSONExporter;
import io.JSONImporter;

import java.util.*;

/**
 * This class supports the CRUD operations
 *
 * @author Liz Mahoney
 * @version 1.0
 */
public class TodoModel{

    private static JSONExporter exporter;
    private static JSONImporter importer;

    private static Map<UUID,Todo> todoMap;

    public TodoModel() {
        exporter = new JSONExporter();
        importer = new JSONImporter();
    }

    //add tasks list object
    public void addTodo(final String task){

        final List<Todo> todos = Lists.newArrayList(todoMap.values());
        UUID newID = createNewUUID();
        todoMap.put(newID, new Todo(task, newID));
        exporter.exportTaskList(todos);


    }

    //get all todos
    public List<Todo> getTodoList(){

        todoMap = new HashMap<>();
        List<Todo> getTaskList = importer.importTaskList();

        for(Todo todo: getTaskList){
            todoMap.put(todo.getId(), todo);
        }

        return getTaskList;
    }

    //update a task
    public void updateTodo(final UUID id, final String newMessage){

        try{
            if(todoMap.containsKey(id)){

                todoMap.replace(id, new Todo(newMessage, id));
            }
            else {
                throw new MissingRecordException("No Task found");
            }
        }
        catch(MissingRecordException e) {
            e.printStackTrace();
        }
    }

    private UUID createNewUUID(){

        UUID newId = UUID.randomUUID();

        return newId;
    }

    //delete a task
    public void deleteTodo(final UUID id){
        try{
            if(todoMap.containsKey(id)){
                todoMap.remove(id);
            }
            else {
                throw new MissingRecordException("No Task found");
            }
        }
        catch(MissingRecordException e) {
            e.printStackTrace();
        }
    }

    //size of the list
    public int size() {
        return todoMap.size();
    }

        private class MissingRecordException extends Exception {

            public MissingRecordException(String message){
                super(message);
            }
        }
}
