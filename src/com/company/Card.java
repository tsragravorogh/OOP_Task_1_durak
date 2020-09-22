package com.company;

import java.util.Arrays;
import java.util.Random;

public class Card implements Comparable{
    private final String rank;
    private final String suit;
    private final String color;

    private Random random = new Random();
    public Card() {
        int randomRankIndex = random.nextInt(9);
        rank = DescriptionCards.ranks[randomRankIndex];

        int randomSuitIndex = random.nextInt(4);
        suit = DescriptionCards.suits[randomRankIndex];

        color = DescriptionCards.colors.get(suit);
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    Card(String r, String s){
        if (Arrays.asList(DescriptionCards.ranks).contains(r) && Arrays.asList(DescriptionCards.suits).contains(s)) {
            rank = r;
            suit = s;
            color = DescriptionCards.colors.get(suit);
        } else {
            throw new IllegalArgumentException("Wrong with rank or suit");
        }
    }
}
