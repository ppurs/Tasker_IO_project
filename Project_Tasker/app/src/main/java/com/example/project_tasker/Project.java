package com.example.project_tasker;

import java.util.ArrayList;

class Project extends StructuralElement {
    ArrayList<Category> categories;

    public Project() {
        categories = new ArrayList<>();
    }

    public Project(String name, String description ) {
        super(name, description);
        this.categories = new ArrayList<>();
    }

    boolean validation( String categoryName ) {
        for ( Category temp : categories) {
            if (temp.name.equals( categoryName ) ) {
                return false;
            }
        }
        return true;
    }

    boolean addCategory( String categoryName, String categoryDescription, int color ) {
        boolean temp = validation( categoryName );

        if( temp )
            categories.add( new Category( categoryName, categoryDescription ) );
            categories.get( categories.size() - 1 ).setColor( color );

        return temp;
    }

    void deleteCategory( int index ){
        categories.remove( index );
    }
    void showStatistics() {}

}
