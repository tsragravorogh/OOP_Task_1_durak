package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<Card>();

        ArrayList<Card> allCards = new ArrayList<Card>();
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                allCards.add(new Card(suit, face));
                Collections.shuffle(allCards);
            }
        }

        for (Card card : allCards) {
            cards.push(card);
        }
    }

    public Card draw() {
        if (!isEmpty()) {
            return cards.pop();
        } else {
            return null;
        }
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Card pop(){
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.empty();
    }

    public int size() {
        return cards.size();
    }

}
