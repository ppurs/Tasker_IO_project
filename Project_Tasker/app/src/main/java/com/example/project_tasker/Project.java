package com.example.project_tasker;

import java.util.ArrayList;

class Project extends StructuralElement {
    ArrayList<Category> categories;

    public Project(String name, String description, ArrayList<Category> categories) {
        super(name, description);
        this.categories = categories;
    }

    void addCategory() {}
    void deleteCategory() {}
    void showStatistics() {}
}
