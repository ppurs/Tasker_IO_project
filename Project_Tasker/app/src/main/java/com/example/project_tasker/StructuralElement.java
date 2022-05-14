package com.example.project_tasker;

public class StructuralElement {

    public StructuralElement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public StructuralElement()
    {
        name = "";
        description = "";
    }

    protected String name ="";
    protected String description = "";

    void editName(){}
    void editDescription(){}

    String getName() { return name; }
    String getDescription() { return description; }
}
