package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Desk {
    private Stack<Card> cards;

    public Desk() {
        cards = new Stack<Card>();

        ArrayList<Card> allCards = new ArrayList<Card>();
        for (String rank : DescriptionCards.ranks) {
            for (String suit : DescriptionCards.suits) {
                allCards.add(new Card(rank, suit));
                Collections.shuffle(allCards);
            }
        }

        for (Card card : allCards) {
            cards.push(card);
        }
    }
}
