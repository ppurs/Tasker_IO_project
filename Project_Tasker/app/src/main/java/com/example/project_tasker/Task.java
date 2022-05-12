package com.example.project_tasker;

class Task extends StructuralElement {
    Card parentCard;
    boolean status = false;
    int priority = 1;
    Date deadline;

    void changeStatus() {}
    boolean getStatus() { return status; }
    void setPriority( int priority ) {}
    int getPriority() { return priority; }
    void setDeadline() {}
    Date getDeadline() { return deadline; }
}
