package com.example.project_tasker;

public class StructuralElement {
    protected String name;
    protected String description;

    public StructuralElement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public StructuralElement()
    {
        name = "";
        description = "";
    }

    void editName(){}
    void editDescription(){}

    String getName() { return name; }
    String getDescription() { return description; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
