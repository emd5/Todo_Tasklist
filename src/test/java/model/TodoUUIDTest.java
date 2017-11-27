package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodoUUIDTest {

    @Test
    public void testTodoMap(){

    Todo newTask = new Todo("Ride a bike");
   // newTask.addTask();


    Assertions.assertEquals(newTask.getId(), true);


    }


}