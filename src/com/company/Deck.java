package com.company;

import java.util.Stack;

public class Deck {
    Stack<Card> playDeck;
    Stack<Card> doneDeck;


    public Stack<Card> getDeck() {
        return playDeck;
    }

    public Stack<Card> getDeckOfDone() {
        return doneDeck;
    }
}
