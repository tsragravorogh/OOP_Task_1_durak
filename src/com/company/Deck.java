package com.company;

import java.util.Stack;

public class Deck {
    private Stack<Card> playDeck;
    private Stack<Card> doneDeck;
    private Stack<Card> deskDack;

    public Stack<Card> getDeck() {
        return playDeck;
    }

    public Stack<Card> getDeckOfDone() {
        return doneDeck;
    }

    public Stack<Card> getDeskDack() {
        return deskDack;
    }
}
