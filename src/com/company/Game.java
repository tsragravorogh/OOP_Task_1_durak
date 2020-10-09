package com.company;

import java.util.*;

public class Game {
//    deck, map(pl, cards), koz, ections(qu)(who, who, atack, ref) -> result
//    play() {
//
//    } - gameservice

    private Suit trump; // козырь
    private CyclicLinkedList<Player> players; // игроки в игре
    private List<Card> cards; // карты в game
    private Map<Player, List<Card>> playerToCardsMap; // игроки
    private List<Round> stepList;
    private Stack<Card> playDeck; // стартовая колода


    public CyclicLinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(CyclicLinkedList<Player> players) {
        this.players = players;
    }

    public void putCards(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setColoda(Stack<Card> cards) {
        playDeck.addAll(cards);
    }

    public Stack<Card> getColoda() {
        return playDeck;
    }

    public Stack<Card> getPlayDeck() {
        return playDeck;
    }

    public Map<Player, List<Card>> getPlayerToCardsMap() {
        return playerToCardsMap;
    }

    public void setPlayerToCardsMap(Map<Player, List<Card>> playerToCardsMap) {
        this.playerToCardsMap = playerToCardsMap;
    }
}
