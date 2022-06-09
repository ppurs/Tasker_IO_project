package com.example.project_tasker;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTests2
{
    private App appToTests = App.getInstance();

    @Test
    public void createApp(){
        assertNotNull( appToTests );
    }

    @Test
    public void appIsEmpty() { assertTrue( appToTests.projects.isEmpty() ); }
}
