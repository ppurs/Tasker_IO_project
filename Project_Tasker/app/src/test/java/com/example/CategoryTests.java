package com.example.project_tasker;
import org.junit.*;
import static org.junit.Assert.*;


public class CategoryTests {
    private Category category;

    @Before
    public void setUp () {
        category = new Category();
    }

    @Test
    public void createCategory(){
        assertNotNull( category );
    }

    @Test
    public void isNameEmptyAfterCreate() {
        assertEquals( category.name, "" );
    }

    @Test
    public void isDescriptionEmptyAfterCreate() {
        assertEquals( category.name, "" );
    }

   /* @Test
    public void setColor_Test() {
        category.setColor( 9d6292 );
        assertEquals( category.color, #9d6292);
    }*/ // ???????????????
}
