package com.example.project_tasker;

class Task extends StructuralElement {
    Card parentCard;
    boolean status = false;
    int priority = 1;
    Date deadline;

    void changeStatus() {
        status = !status;
    }

    boolean getStatus() { return status; }

    void setPriority( int priority ) {
        this.priority = priority;
    }

    int getPriority() { return priority; }

    void setDeadline( Date date ) {
        deadline = date;
    }

    Date getDeadline() { return deadline; }
}
