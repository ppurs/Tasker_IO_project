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



    /*@Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString( name );
        out.writeString( description );
    }

    private StructuralElement(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<StructuralElement> CREATOR
            = new Parcelable.Creator<StructuralElement>() {

        @Override
        public StructuralElement createFromParcel(Parcel in) {
            return new StructuralElement(in);
        }

        @Override
        public StructuralElement[] newArray(int size) {
            return new StructuralElement[size];
        }
    };
*/
}
