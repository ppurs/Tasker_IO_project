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

    @Test
    public void deleteProject_removeFirst() {
        appToTests.addProject( "firstProject", "description" );

        appToTests.deleteProject( 0 );
        assertTrue( appToTests.projects.isEmpty() );
    }

    @Test
    public void deleteProject_removeFromMiddle() {
        appToTests.addProject( "firstProject", "description" );
        appToTests.addProject( "secondProject", "description" );
        appToTests.addProject( "thirdProject", "description" );

        appToTests.deleteProject( 1 );
        assertEquals(  appToTests.projects.size(), 2 );
        assertEquals( appToTests.projects.get( 0 ).getName(), "firstProject" );
        assertEquals( appToTests.projects.get( 1 ).getName(), "thirdProject" );
    }

    @Test
    public void deleteProject_removeLast() {
        appToTests.addProject( "firstProject", "description" );
        appToTests.addProject( "secondProject", "description" );

        appToTests.deleteProject( appToTests.projects.size() - 1 );
        assertEquals( appToTests.projects.size(), 1 );
        assertEquals( appToTests.projects.get( 0 ).getName(), "firstProject");
    }


}
