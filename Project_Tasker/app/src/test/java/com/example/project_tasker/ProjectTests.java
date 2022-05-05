package com.example.project_tasker;
import org.junit.*;
import static org.junit.Assert.*;

public class ProjectTests {
    private Project project;

    @Before
    public void setUp () {
       project = new Project();
    }

    @Test
    public void createProject(){
        assertNotNull( project );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( project.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( project.name, "" );
    }


}
