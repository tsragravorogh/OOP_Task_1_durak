package com.company;

import java.util.ArrayList;

public class Hang {
    private ArrayList<Card> cards;

    public Hang(){
        cards = new ArrayList<Card>();
    }

    public void add(Card c) {
        cards.add(c);
    }

    public void remove(Card c) {
        cards.remove(c);
    }

    public int size() {
        return cards.size();
    }
}
