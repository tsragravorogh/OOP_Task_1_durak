package com.company;

import java.util.*;

public class Game {

    private Card trump; // козырь
    private CyclicLinkedList<Player> players; // игроки в игре
    private List<Card> desk = new ArrayList<>(); // карты в game
    private Map<Player, List<Card>> playerToCardsMap; // игроки
    private Stack<Card> playDeck; // стартовая колода
    private List<List<Fight>> rounds = new ArrayList<>();



    public CyclicLinkedList<Player> getPlayers() { //
        return players;
    }

    public void setPlayers(CyclicLinkedList<Player> players) { //
        this.players = players;
    }

    public void setCards(List<Card> cards) {
        this.desk = cards;
    }

    public void putCards(List<Card> cards) {
        desk.addAll(cards);
    }

    public void putCard(Card card) {
        desk.add(card);
    }

    public List<Card> getCards() {
        return desk;
    }

    public void clearCardsOnDeck() {
        desk.clear();
    }

    public void setColoda(Stack<Card> cards) {
        this.playDeck = cards;
    }

    public Stack<Card> getColoda() {
        return playDeck;
    }

    public Stack<Card> getPlayDeck() {
        return playDeck;
    }

    public void removePlayerMap(Player playerToRemove) {
        playerToCardsMap.remove(playerToRemove);
    }

    public Map<Player, List<Card>> getPlayerToCardsMap() {
        return playerToCardsMap;
    }

    public void setPlayerToCardsMap(Map<Player, List<Card>> playerToCardsMap) {
        this.playerToCardsMap = playerToCardsMap;
    }

    public void removeCardsInPlayer(Player player, List<Card> cards) {
        this.playerToCardsMap.get(player).removeAll(cards);
    }

    public Card getTrump() {
        return trump;
    }

    public void setTrump(Card trump) {
        this.trump = trump;
    }

    public List<List<Fight>> getRounds() {
        return rounds;
    }

    public void setRound(List<Fight> round) {
        rounds.add(round);
    }
}
