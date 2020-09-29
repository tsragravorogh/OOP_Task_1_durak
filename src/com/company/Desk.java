package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public enum  Desk {

    INSTANCE;

    private List<Card> cards;
    private List<Card> playDeck;

    Desk() {
        cards = new ArrayList<>(52);
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                cards.add(new Card(suit, face));
            }
        }
        playDeck = new ArrayList<>(cards);
    }

    public void shuffle() {
        playDeck.clear();
        playDeck.addAll(cards);
        Collections.shuffle(playDeck);
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





//    private Stack<Card> cards;
//
//    public Desk() {
//        cards = new Stack<Card>();
//
//        ArrayList<Card> allCards = new ArrayList<Card>();
//        for (String rank : DescriptionCards.ranks) {
//            for (String suit : DescriptionCards.suits) {
//                allCards.add(new Card(rank, suit));
//                Collections.shuffle(allCards);
//            }
//        }
//
//        for (Card card : allCards) {
//            cards.push(card);
//        }
//    }
}
