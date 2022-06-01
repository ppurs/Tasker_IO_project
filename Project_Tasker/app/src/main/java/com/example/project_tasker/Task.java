package com.example.project_tasker;

class Task extends StructuralElement {
    Card parentCard;
    boolean status = false;
    int priority = 1;
    Date deadline;

    public Task(){}

    public Task(String taskName, String taskDescription)
    {
        super(taskName, taskDescription);
        deadline = new Date();
    }

    void changeStatus() {
        status = !status;
    }

    boolean getStatus() { return status; }

    void setStatus(boolean st) { status = st; }

    void setPriority( int priority ) {
        this.priority = priority;
    }

    int getPriority() { return priority; }

    void setDeadline( Date date ) {
        deadline = date;
    }

    Date getDeadline() { return deadline; }
}
