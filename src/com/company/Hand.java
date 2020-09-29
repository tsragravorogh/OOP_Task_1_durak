package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hand {

    private ArrayList<Card> cards;
    private Deck deck = new Deck();

    Hand() {

    }

    public List<Card> getStartsCard() {
        List<Card> hang = new ArrayList();
        if (deck.size() != 0) {
            for (int count = 0; count < 6; count++) {
                hang.add(deck.pop());
            }
        }
        return hang;
    }

    public List<Card> drawCard(List<Card> cards) {
        while (cards.size() != 6) {
            cards.add(deck.pop());
        }
        return cards;
    }


}
