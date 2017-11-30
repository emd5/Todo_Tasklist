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
public class TodoModel extends Observable{

    private static JSONExporter exporter;
    private static JSONImporter importer;

    private static Map<UUID,Todo> todoMap;

    public TodoModel() {
        exporter = new JSONExporter();
        importer = new JSONImporter();
    }

    /**
     * This method adds a new task
     *
     * @param task a new message
     */
    public void addTodo(final String task){

        UUID newID = createNewUUID();
        todoMap.put(newID, new Todo(task, newID));

        final List<Todo> todos = Lists.newArrayList(todoMap.values());
        exporter.exportTaskList(todos);

        this.setChanged();
        this.notifyObservers(todos);
    }

    /**
     * This method grabs a list of task.
     *
     *  @return the tasklist
     */
    public List<Todo> getTodoList(){

        todoMap = new HashMap<>();
        List<Todo> getTaskList = importer.importTaskList();

        for(Todo todo: getTaskList){
            todoMap.put(todo.getId(), todo);
        }

        return getTaskList;
    }

    /**
     * This method updates a todotask
     *
     * @param id the id of the message
     * @param newMessage a new updated message
     */
    public void updateTodo(final UUID id, final String newMessage){
        final List<Todo> todos = Lists.newArrayList(todoMap.values());
        try{
            if(todoMap.containsKey(id)){

                todoMap.replace(id, new Todo(newMessage, id));
                exporter.exportTaskList(todos);

                this.setChanged();
                this.notifyObservers(todos);
            }
            else {
                throw new MissingRecordException("No Task found");
            }
        }
        catch(MissingRecordException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deletes a task
     *
     * @param id the UUID of the message
     */
    public void deleteTodo(final UUID id){

        final List<Todo> todos = Lists.newArrayList(todoMap.values());

        try{
            if(todoMap.containsKey(id)){
                todoMap.remove(id);
                this.setChanged();
                this.notifyObservers(todos);
                exporter.exportTaskList(todos);
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

   public int size() {

        return todoMap.size();
    }

    private class MissingRecordException extends Exception {

        public MissingRecordException(String message){

            super(message);
        }
    }
}
