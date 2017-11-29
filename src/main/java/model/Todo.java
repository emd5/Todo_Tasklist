/*Liz Mahoney
 *11/25/17
 *todo.java
 */

package model;
import java.util.*;

/**
 * This class is a representation of the Todo task
 * with an associated ID.
 *
 * @author Liz Mahoney
 * @version 1.0
 */
public class Todo {

    private final String taskMessage;
    private UUID id;

    /**
     * This method excepts a new message and an id
     *
     * @param newTaskMessage a new message for the to do list
     * @param id a unique identifier for the message
     */
    public Todo(final String newTaskMessage, final UUID id) {
        this.taskMessage = newTaskMessage;
        this.id = id;
    }

    /**
     * This method grabs a UUID
     *
     * @return a universal unique identifier
     */
    public UUID getId(){
        return id;
    }

    /**
     * This method grabs a task
     *
     * @return the task message
     */
    public String getTask(){

        return taskMessage;
    }

}
