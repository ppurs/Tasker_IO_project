package com.example.project_tasker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CardTests {
    private Card cardToTests;

    @Before
    public void setUp () {
        cardToTests = new Card();
    }

    @Test
    public void createCard(){
        assertNotNull( cardToTests );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( cardToTests.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( cardToTests.name, "" );
    }

    @Test
    public void cardIsEmpty() { assertTrue( cardToTests.tasks.isEmpty() ); }

    @Test
    public void addTaskToEmptyCard(){
        assertTrue( cardToTests.addTask( "firstTask", "description" ) );
        assertEquals( cardToTests.tasks.get( 0 ).name, "firstTask" );
    }

    @Test
    public void addTaskWithUniqueName() {
        cardToTests.addTask( "Unique", "desc");
        assertTrue( cardToTests.addTask( "otherUnique", "desc" ) );
    }

    @Test
    public void addTaskWithNOTUniqueName() {
        cardToTests.addTask( "notUnique", "desc");
        assertFalse( cardToTests.addTask( "notUnique", "desc" ) );
    }



}
