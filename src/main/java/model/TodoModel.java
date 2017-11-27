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
    public void updateTodo(final int id){

        for(Todo uuid : todo) {
            if(uuid.getId()==id ) {
                todo.remove(id);
            }
        }
    }

    //delete a task
    public void deleteTodo(final int id){

        for(Todo uuid : todo){
            try{
                if(uuid.getId()== id){
                    todo.remove(id);
                }
                else {
                    throw new MissingRecordException();
                }
            }
            catch(MissingRecordException e) {
                e.printStackTrace();
            }
        }
    }

    //size of the list
    public int size(){
        return todo.size();
    }



        private class MissingRecordException extends Throwable {


        }
}
