package com.example.project_tasker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTests {
    private Task taskToTests;

    @Before
    public void setUp () {
        taskToTests = new Task();
    }

    @Test
    public void createTask(){
        assertNotNull( taskToTests );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( taskToTests.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( taskToTests.name, "" );
    }

    @Test
    public void isPriorityEqualOneAfterCreate() {
        assertEquals( taskToTests.getPriority(), 1 );
    }

    @Test
    public void isTaskNotDoneAfterCreate() {
        assertFalse( taskToTests.getStatus() );
    }

    @Test
    public void changePriority() {
        taskToTests.setPriority( 1 );
        assertEquals( taskToTests.getPriority(), 1 );

        taskToTests.setPriority( 2 );
        assertEquals( taskToTests.getPriority(), 2 );

        taskToTests.setPriority( 3 );
        assertEquals( taskToTests.getPriority(), 3 );

        taskToTests.setPriority( 4 );
        assertEquals( taskToTests.getPriority(), 4 );

        taskToTests.setPriority( 5 );
        assertEquals( taskToTests.getPriority(), 5 );
    }

    @Test
    public void changeStatus_fromNotDoneToDone() {
        taskToTests.status = false;
        taskToTests.changeStatus();
        assertTrue( taskToTests.getStatus() );
    }

    @Test
    public void changeStatus_fromDoneToNotDone() {
        taskToTests.status = true;
        taskToTests.changeStatus();
        assertFalse( taskToTests.getStatus() );
    }

}
