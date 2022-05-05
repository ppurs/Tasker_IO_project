package com.example.project_tasker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTests {
    private Task task;

    @Before
    public void setUp () {
        task = new Task();
    }

    @Test
    public void createTask(){
        assertNotNull( task );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( task.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( task.name, "" );
    }

    @Test
    public void isPriorityEqualOneAfterCreate() {
        assertEquals( task.priority, 1 );
    }

    @Test
    public void isTaskNotDoneAfterCreate() {
        assertFalse( task.status );
    }

    @Test
    public void changePriority() {
        task.setPriority( 1 );
        assertEquals( task.priority, 1 );

        task.setPriority( 2 );
        assertEquals( task.priority, 2 );

        task.setPriority( 3 );
        assertEquals( task.priority, 3 );

        task.setPriority( 4 );
        assertEquals( task.priority, 4 );

        task.setPriority( 5 );
        assertEquals( task.priority, 5 );
    }

    @Test
    public void changeStatus_fromNotDoneToDone() {
        task.status = false;
        task.changeStatus();
        assertTrue( task.status );
    }

    @Test
    public void changeStatus_fromDoneToNotDone() {
        task.status = true;
        task.changeStatus();
        assertFalse( task.status );
    }

}
