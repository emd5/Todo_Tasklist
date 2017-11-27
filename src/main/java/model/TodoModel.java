/*Liz Mahoney
 *11/20/17
 *TodoModel.java
 */
package model;

import java.util.*;

/**
 * This class supports the CRUD operations
 *
 * @author Liz Mahoney
 * @version 1.0
 */
public class TodoModel{

    private static List<Todo> todo;

    public TodoModel(){
        todo = new ArrayList<>();
    }

    //add tasks list object
    public void addTodoList(final Todo tasks){
        todo.add(tasks);
    }

    //get unmodafiable list
    public Collection<Todo> getTodoList(){

        return Collections.unmodifiableCollection(todo);
    }

    //update a task
    public void updateTodo(final UUID id, final String newMessage){

        try{
            for(Todo uuid : todo) {
                if(uuid.getId()==id ) {
                    todo.remove(id);

                    Todo newTask = new Todo(newMessage, getUUID());
                    todo.add(newTask);
                }
                else {
                    throw new MissingRecordException();
                }
            }
        }
        catch(MissingRecordException e) {
            e.printStackTrace();
        }
    }

    private UUID getUUID(){
        UUID newId = UUID.randomUUID();
        return newId;
    }

    //delete a task
    public void deleteTodo(final UUID id){
        try {
            for(Todo uuid : todo) {
                if(uuid.getId() == id) {
                    todo.remove(id);
                }
                else {
                    throw new MissingRecordException();
                }
            }
        }
        catch(MissingRecordException e) {
            e.printStackTrace();
        }
    }

    //size of the list
    public int size(){
        return todo.size();
    }

        private class MissingRecordException extends Throwable {


        }
}
