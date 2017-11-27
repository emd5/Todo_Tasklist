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
    private final int id;

    public Todo(final String taskMessage, final int id) {
        this.taskMessage = taskMessage;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public UUID setId(){
        UUID id = UUID.randomUUID();
        return id;
    }

    public String getTask(){
        return taskMessage;
    }

}
