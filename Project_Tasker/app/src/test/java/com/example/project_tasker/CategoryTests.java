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
    public void addCardToEmptyCategory(){
        assertTrue( categoryToTests.addCard( "firstCard", "description" ) );
        assertEquals( categoryToTests.cards.get( 0 ).name, "firstCard" );
    }

    @Test
    public void addCardWithUniqueName() {
        categoryToTests.addCard( "Unique", "desc");
        assertTrue( categoryToTests.addCard( "otherUnique", "desc" ) );
    }

    @Test
    public void addCardWithNOTUniqueName() {
        categoryToTests.addCard( "notUnique", "desc");
        assertFalse( categoryToTests.addCard( "notUnique", "desc") );
    }

   /* @Test
    public void setColor_Test() {
        category.setColor( 9d6292 );
        assertEquals( category.color, #9d6292);
    }*/ // ???????????????
}
