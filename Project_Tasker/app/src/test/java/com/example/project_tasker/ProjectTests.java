package com.example.project_tasker;
import org.junit.*;
import static org.junit.Assert.*;

public class ProjectTests {
    private Project projectToTests;

    @Before
    public void setUp () {
       projectToTests = new Project();
    }

    @Test
    public void createProject(){
        assertNotNull( projectToTests );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( projectToTests.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( projectToTests.name, "" );
    }

    @Test
    public void projectIsEmptyAfterCreate() { assertTrue( projectToTests.categories.isEmpty() ); }

    @Test
    public void addCategory_ToEmptyProject(){
        assertTrue( projectToTests.addCategory( "firstCat", "description" ) );
        assertEquals( projectToTests.categories.get( 0 ).name, "firstCat" );
    }

    @Test
    public void validation_UniqueName() {
        projectToTests.addCategory( "Unique", "desc");
        assertTrue( projectToTests.validation( "otherUnique" ) );
    }

    @Test
    public void validation_NOTUniqueName() {
        projectToTests.addCategory( "notUnique", "desc");
        assertFalse( projectToTests.validation( "notUnique" ) );
    }

    @Test
    public void addCategory_UniqueName() {
        projectToTests.addCategory( "uniqueCat", "desc");
        projectToTests.addCategory( "otherUniqueCat", "desc");
        assertEquals( projectToTests.categories.get( projectToTests.categories.size() - 1  ).name, "otherUniqueCat" );
    }

    @Test
    public void addCategory_NOTUniqueName() {
        projectToTests.addCategory( "notUniqueCat", "desc");
        projectToTests.addCategory( "tempCat", "desc");
        projectToTests.addCategory( "notUniqueCat", "desc");
        assertNotEquals( projectToTests.categories.get( projectToTests.categories.size() - 1  ).name, "notUniqueCat" );
    }


}
