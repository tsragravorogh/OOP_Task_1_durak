package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class GameService {

    public void play(Game g) {
        initGameWithPlayers();
        initGameWithCards();
        //deal cards
        while (!isDeckEmpty(g) || isGameAlive(g)) {

            getSourcePlayer(); // ходящий
            getTarfgetPlayer(); // отбивающий
            Round r = new Round();

            getFirstCard();
            Fight f = buildFight();

        }
    }

    private boolean isGameAlive(Game g) {

    }

    private boolean isDeckEmpty(Game g) {
        return true;
    }

    public void initGameWithPlayers(Game g, int playersCount) {
        if(playersCount > 1 && playersCount <= 7) {
            for(int i = 0; i < playersCount; i++) {

            }
        }
    }

    public void initGameWithCards(Game g) {
        Stack<Card> playDeck = new Stack<>();
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                playDeck.push(new Card(suit, face));
            }
        }
        g.setColoda(playDeck);
    }

    private void shuffle() {
        playDeck.clear();
        playDeck.addAll(cards);
        Collections.shuffle(playDeck);
    }

    public boolean pp(Player player, Game game) {

    }

    boolean isEmpty(Game g)  {

    }
    //getFirstCard


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

    public void pushToDoneDeck(Card card){
        doneDeck.push(card);
    }
}
