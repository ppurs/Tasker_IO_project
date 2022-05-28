package com.example.project_tasker;

import java.util.ArrayList;

class Category extends StructuralElement {
    ArrayList<Card> cards;
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

    boolean addCard( String cardName, String cardDescription )
    {
        boolean temp = validation( cardName );

        if( temp )
            cards.add( new Card( cardName, cardDescription ) );

        return temp;

    }
    void deleteCard( int index ){
        cards.remove( index );
    }

    void setColor( int color ) { this.color = color; }

    int getColor () { return color; }

}
