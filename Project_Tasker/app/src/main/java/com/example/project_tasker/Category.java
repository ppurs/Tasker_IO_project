package com.example.project_tasker;

import java.util.ArrayList;


class Category extends StructuralElement {
    ArrayList<Card> cards;
    //Project parentProject;
    int color;

    public Category() {
        cards = new ArrayList<>();
    }

    public Category( String categoryName, String categoryDescription ) {
        super( categoryName, categoryDescription);
        cards = new ArrayList<Card>();
    }


    boolean validation( String cardName ) {
        for ( Card temp : cards) {
            if (temp.name.equals( cardName ) ) {
                return false;
            }
        }
        return true;
    }

    boolean addCard( String cardName, String cardDescription ){ return true; }
    void deleteCard(){}

    void setColor( int color ) { this.color = color; }

    int getColor () { return color; }

/*
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeTypedList( cards );
    }

    private Category(Parcel in) {
        this.cards = in.createTypedArrayList( Card.CREATOR );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Category> CREATOR
            = new Parcelable.Creator<Category>() {

        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };*/
}
