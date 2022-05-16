package com.example.project_tasker;

class Task extends StructuralElement {
    Card parentCard;
    boolean status = false;
    int priority = 1;
    Date deadline;

    public Task(){}

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

    /*@Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeValue( status );
        out.writeInt(priority);
        out.writeValue( deadline );

    }

    private Task(Parcel in) {
        this.status = (boolean) in.readValue( null );
        this.priority = in.readInt();
        this.deadline =  (Date) in.readValue( null );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Task> CREATOR
            = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };*/
}
