package com.example.project_tasker;

import java.util.ArrayList;

class Category extends StructuralElement {
    ArrayList<Card> cards;
    Project parentProject;
    int color;

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
}
