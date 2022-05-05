package com.example.project_tasker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardTests {
    private Card card;

    @Before
    public void setUp () {
        card = new Card();
    }

    @Test
    public void createCard(){
        assertNotNull( card );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( card.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( card.name, "" );
    }



}
