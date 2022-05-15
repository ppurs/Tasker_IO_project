package com.example.project_tasker;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class StructuralElementTests {

    @Test
    public void editNameTest() {
        StructuralElement test = new StructuralElement();
        test.editName( "change" );

        assertEquals( test.getName(), "change" );
    }

    @Test
    public void editDescriptionTest() {
        StructuralElement test = new StructuralElement();
        test.editDescription( "changedDescription" );

        assertEquals( test.getDescription(), "changedDescription" );
    }

}
