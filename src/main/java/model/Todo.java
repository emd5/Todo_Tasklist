package model;/*
 *Liz Mahoney
 *11/25/17
 *Todo.java
 */

import java.util.*;


/**
 * @author Liz Mahoney
 * @version 1.0
 */
public class Todo {

    private final String taskMessage;
    private UUID id;

    //a task that already has been generated
    public Todo(final String taskMessage, final UUID id) {
        this.taskMessage = taskMessage;
        this.id = id;
    }

    public UUID getId(){

        return id;
    }

    public String getTask(){

        return taskMessage;
    }

}
