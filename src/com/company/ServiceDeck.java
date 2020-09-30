package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public enum ServiceDeck {

    INSTANCE;

    private ArrayList<Card> cards;
    private Stack<Card> playDeck;
    private Stack<Card> doneDeck;


    ServiceDeck() {

    }

    public void takeDeck() {
        playDeck = new Stack<>();
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                playDeck.push(new Card(suit, face));
            }
        }
    }

    public void shuffle() {
        playDeck.clear();
        playDeck.addAll(cards);
        Collections.shuffle(playDeck);
    }

    public List<Card> getStartsCard() {
        ArrayList<Card> cardsToOnePlayer = new ArrayList(52);
        if (playDeck.size() != 0) {
            for (int count = 0; count < 6; count++) {
                cardsToOnePlayer.add(playDeck.pop());
            }
        }
        return cardsToOnePlayer;
    }

    public Card pop() {
        if (playDeck.isEmpty()) {
            return null;
        }
        return playDeck.remove(0);
    }


    public void push(Card card) {
        playDeck.add(card);
    }
}
