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
    public void addProject_ToEmptyApp(){
        appToTests.addProject( "firstProject", "description" );
        assertEquals( appToTests.projects.get( 0 ).name, "firstProject" );
    }

    @Test
    public void validation_UniqueName() {
        appToTests.addProject( "Unique", "desc");
        assertTrue( appToTests.validation( "otherUnique" ) );
    }

    @Test
    public void validation_NOTUniqueName() {
        appToTests.addProject( "notUnique", "desc");
        assertFalse( appToTests.validation( "notUnique" ) );
    }

    @Test
    public void addProject_UniqueName() {
        appToTests.addProject( "uniqueProject", "desc");
        appToTests.addProject( "otherUniqueProject", "desc");
        assertEquals( appToTests.projects.get( appToTests.projects.size() - 1  ).name, "otherUniqueProject" );
    }

    @Test
    public void addProject_NOTUniqueName() {
        appToTests.addProject( "notUniqueProject", "desc");
        appToTests.addProject( "tempProject", "desc");
        appToTests.addProject( "notUniqueProject", "desc");
        assertNotEquals( appToTests.projects.get( appToTests.projects.size() - 1  ).name, "notUniqueProject" );
    }


}
