package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ServiceHand {

    private ArrayList<Card> cards;
    private Stack<Card> deck;

    ServiceHand() {

    }

    public List<Card> getStartsCard() {
        ArrayList hang = new ArrayList(52);
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
