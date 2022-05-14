package com.example.project_tasker;

import java.util.ArrayList;

class Project extends StructuralElement {
    ArrayList<Category> categories;

    public Project() {}

    public Project(String name, String description, ArrayList<Category> categories) {
        super(name, description);
        this.categories = categories;
    }

    boolean addCategory( String categoryName, String categoryDescription /*kolor trzeba ogarnac*/ ) { return true; }
    void deleteCategory() {}
    void showStatistics() {}
}
