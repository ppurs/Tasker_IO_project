package com.example.project_tasker;

public class StructuralElement  {
    protected String name;
    protected String description;

    public StructuralElement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public StructuralElement() {
        name = "";
        description = "";
    }

    void editName( String name ){
        this.name = name;
    }

    void editDescription( String description ){
        this.description = description;
    }

    String getName() { return name; }
    String getDescription() { return description; }

}
