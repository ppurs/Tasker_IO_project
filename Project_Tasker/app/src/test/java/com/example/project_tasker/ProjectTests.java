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
    public void projectIsEmpty() { assertTrue( projectToTests.categories.isEmpty() ); }

    @Test
    public void addCategoryToEmptyProject(){
        assertTrue( projectToTests.addCategory( "firstCat", "description" ) );
        assertEquals( projectToTests.categories.get( 0 ).name, "firstCat" );
    }

    @Test
    public void addCategoryWithUniqueName() {
        projectToTests.addCategory( "Unique", "desc");
        assertTrue( projectToTests.addCategory( "otherUnique", "desc" ) );
    }

    @Test
    public void addCategoryWithNOTUniqueName() {
        projectToTests.addCategory( "notUnique", "desc");
        assertFalse( projectToTests.addCategory( "notUnique", "desc") );
    }


}
