package com.company;

import java.util.Arrays;
import java.util.Random;

public class Card{
    private Suit suit;
    private Face face;

    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
    }

    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }

    //    private final String rank;
//    private final String suit;
//    private final String color;
//
//    private Random random = new Random();
//    public Card() {
//        int randomRankIndex = random.nextInt(9);
//        rank = DescriptionCards.ranks[randomRankIndex];
//
//        int randomSuitIndex = random.nextInt(4);
//        suit = DescriptionCards.suits[randomRankIndex];
//
//        color = DescriptionCards.colors.get(suit);
//    }
//
//
//    @Override
//    public int compareTo(Object o) {
//        Card secondCard = (Card) o;
//
//        int valueFirstCard = DescriptionCards.values.get(rank);
//        int valueSecondCard = DescriptionCards.values.get(secondCard.rank);
//
//        return valueFirstCard - valueSecondCard;
//    }
//
//    public boolean sameSuit(Object o) {
//        Card secondCard = (Card) o;
//
//        String firstCardSuit = this.suit;
//        String secondCardSuit = secondCard.suit;
//
//        return firstCardSuit.equals(secondCardSuit);
//    }
//
//    Card(String r, String s){
//        if (Arrays.asList(DescriptionCards.ranks).contains(r) && Arrays.asList(DescriptionCards.suits).contains(s)) {
//            rank = r;
//            suit = s;
//            color = DescriptionCards.colors.get(suit);
//        } else {
//            throw new IllegalArgumentException("Wrong with rank or suit");
//        }
//    }
}
