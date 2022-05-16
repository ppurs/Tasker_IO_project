package com.example.project_tasker;
import org.junit.*;
import static org.junit.Assert.*;


public class CategoryTests {
    private Category categoryToTests;

    @Before
    public void setUp () {
        categoryToTests = new Category();
    }

    @Test
    public void createCategory(){
        assertNotNull( categoryToTests );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( categoryToTests.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( categoryToTests.name, "" );
    }

    @Test
    public void categoryIsEmptyAfterCreate() { assertTrue( categoryToTests.cards.isEmpty() ); }

    @Test
    public void addCard_ToEmptyCategory(){
        assertTrue( categoryToTests.addCard( "firstCard", "description" ) );
        assertEquals( categoryToTests.cards.get( 0 ).name, "firstCard" );
    }

    @Test
    public void validation_UniqueName() {
        categoryToTests.addCard( "Unique", "desc");
        assertTrue( categoryToTests.validation( "otherUnique" ) );
    }

    @Test
    public void validation_NOTUniqueName() {
        categoryToTests.addCard( "notUnique", "desc");
        assertFalse( categoryToTests.validation( "notUnique" ) );
    }

    @Test
    public void addCard_UniqueName() {
        categoryToTests.addCard( "uniqueCard", "desc");
        categoryToTests.addCard( "otherUniqueCard", "desc");
        assertEquals( categoryToTests.cards.get( categoryToTests.cards.size() - 1  ).name, "otherUniqueCard" );
    }

    @Test
    public void addCard_NOTUniqueName() {
        categoryToTests.addCard( "notUniqueCard", "desc");
        categoryToTests.addCard( "tempCard", "desc");
        categoryToTests.addCard( "notUniqueCard", "desc");
        assertNotEquals( categoryToTests.cards.get( categoryToTests.cards.size() - 1  ).name, "notUniqueCard" );
    }

    @Test
    public void setColor_Test() {
        categoryToTests.setColor( 0x9d6292 );
        assertEquals( categoryToTests.getColor(), 0x9d6292);
    }

}
