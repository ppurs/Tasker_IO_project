package com.example.project_tasker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTests {
    private App appToTests;

    @Before
    public void setUp () {
        appToTests = new App();
    }

    @Test
    public void createApp(){
        assertNotNull( appToTests );
    }

    @Test
    public void appIsEmpty() { assertTrue( appToTests.projects.isEmpty() ); }

    @Test
    public void addProjectToEmptyApp(){
        assertTrue( appToTests.addProject( "firstProject", "description" ) );
        assertEquals( appToTests.projects.get( 0 ).name, "firstProject" );
    }

    @Test
    public void addProjectWithUniqueName() {
        appToTests.addProject( "Unique", "desc");
        assertTrue( appToTests.addProject( "otherUnique", "desc" ) );
    }

    @Test
    public void addProjectWithNOTUniqueName() {
        appToTests.addProject( "notUnique", "desc");
        assertFalse( appToTests.addProject( "notUnique", "desc") );
    }

}
