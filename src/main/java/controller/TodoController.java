/*Liz Mahoney
 *11/20/17
 *TodoController.java
 */

package controller;
import model.Todo;
import model.TodoModel;
import view.TodoView;

import java.util.List;
import java.util.UUID;

/**
 *
 * This class is the controller that intercepts any
 * change with data between the view and model
 * @author Liz Mahoney
 * @version 1.0
 */
public class TodoController {

    private TodoModel todo;

    /**
     * Reads the TodoModel class.
     */
    public TodoController () {
        todo = new TodoModel();
    }

    /**
     * Adds a new message to the list.
     *
     * @param message a new message.
     */
    public void addTask(final String message) {
        todo.addTodo(message);
    }

    /**
     * This method retrieves the list of tasks
     *
     * @return a list of tasks
     */
    public List<Todo> getTaskList(){

        List<Todo> taskList = todo.getTodoList();

        return taskList;
    }

    /**
     * This method removes a task
     *
     * @param findID the id of the task
     */
    public void removeTask(UUID findID){
        todo.deleteTodo(findID);
    }

    /**
     * This method updates the message
     *
     * @param uuid the id of the task
     * @param message the task message
     */
    public void updateTask(UUID uuid, String message){
        todo.updateTodo(uuid, message);
    }

    public int sizeToDoList(){
        return todo.size();
    }

    /**
     * Adds an observer to the set of observers for this object,
     * provided that it is not the same as some observer already
     * in the set.The order in which notifications will be
     * delivered to multiple observers is not specified.
     *
     *  @param view the TodoView Class observer
     */
    public void addObserver(TodoView view){
        todo.addObserver(view);
    }



}
