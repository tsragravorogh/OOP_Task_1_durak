package com.tsragravorogh.Services;


import com.tsragravorogh.Elements.Card;
import com.tsragravorogh.Elements.Face;
import com.tsragravorogh.Elements.Player;
import com.tsragravorogh.Elements.Suit;
import com.tsragravorogh.Filds.Fight;
import com.tsragravorogh.Filds.Game;
import com.tsragravorogh.Filds.Round;
import com.tsragravorogh.Utils.CyclicLinkedList;

import java.util.*;

public class GameService {

    public void play(Game g, int playersCount) throws Exception {
        initGameWithPlayers(g, playersCount); // players
        initGameWithCards(g);
        initGameWithPlayersNCards(g);

        Player source = null;
        Player target;
        boolean isFirstRound = true;
        boolean isPickedUp = false;

        while (isGameAlive(g)) {
            if (isFirstRound) {
                source = getSourcePlayerFirstTime(g);
                target = getTargetPlayer(g, source);
                isFirstRound = false;
            } else {
                source = getSourcePlayer(g, isPickedUp, source); //определили ходящего
                System.out.println(g.isPlayerExist(source) + " EXIST PLAYER OR NOT");
                System.out.println(source + " атакует");
                target = getTargetPlayer(g, source); //определили отбивающего
                System.out.println(g.isPlayerExist(target) + " EXIST PLAYER OR NOT");
                System.out.println(target + " защищается");


            }
            //System.out.println(source + ", " + target);
            Round r = new Round();
            r.setSource(source);
            r.setTarget(target);
            source = sourcePlayer(g, source, target);
            if(source == null) {
                System.out.println("Проиграл " + target);
                break;
            }
            System.out.println("Игрок которого вернул атакующий после атаки " + source);
            isPickedUp = targetPlayerNew(g, target);
            if (isPickedUp) {
                System.out.println("Потянул");
            } else {
                System.out.println("Отбился");
            }
            if(g.getColoda().size() != 0) getNeedCard(g);
            System.out.println(g.getPlayers());
            System.out.println();
        }

    }

    private void getNeedCard(Game g) {
        Map<Player, List<Card>> playerCards = g.getPlayerToCardsMap();

        Stack<Card> cards = g.getColoda();

        for (Map.Entry<Player, List<Card>> entry : playerCards.entrySet()) {

            List<Card> newCards = new ArrayList<>();
            int size = entry.getValue().size();

            while (size < 6 && cards.size() != 0) {
                newCards.add(cards.pop());
                size++;

            }
            entry.getValue().addAll(newCards);

        }
        g.setColoda(cards);
        g.setPlayerToCardsMap(playerCards);
    }

    private Player getSourcePlayerFirstTime(Game g) {
        int maxRang = g.getTrump().getFace().getRank();
        int minRang = g.getTrump().getFace().getRank();
        int count = 0;
        CyclicLinkedList<Player> list = g.getPlayers();
        if (g.getTrump().getFace().getRank() > 10) {
            for (int i = 0; i < g.getPlayers().size(); i++) {
                Player player = list.get(i);
                for (Card c : g.getPlayerToCardsMap().get(player)) {
                    if ((c.getSuit() == g.getTrump().getSuit()) && (c.getFace().getRank() > maxRang)) {
                        maxRang = c.getFace().getRank();
                        count = i;
                    }
                }
            }
        } else {
            for (int i = 0; i < g.getPlayers().size(); i++) {
                Player player = list.get(i);
                for (Card c : g.getPlayerToCardsMap().get(player)) {
                    if ((c.getSuit() == g.getTrump().getSuit()) && (c.getFace().getRank() < minRang)) {
                        minRang = c.getFace().getRank();
                        count = i;
                    }
                }
            }
        }
        return g.getPlayers().get(count);
    }

    private Player getSourcePlayer(Game g, boolean isPickedUp, Player playerBefore) {
        System.out.println("когда выбирали source" + g.getPlayers().toString());
        Player player;
        if (!isPickedUp) {
            player = g.getNext(playerBefore);
        } else {
            player = g.getNext(g.getNext(playerBefore));
        }
        return player;
    }

    private Player getTargetPlayer(Game g, Player sourcePlayer) {
        return g.getNext(sourcePlayer);
    }

    private Player sourcePlayer(Game g, Player sourcePlayer, Player targetPlayer) {
        Player beforeSource = g.beforePlayer(sourcePlayer);
        boolean isEnd = g.getPlayers().size() == 2;
        System.out.println("Игорок которого вернет атакующий если удалится " + beforeSource);
        List<Card> sourcePlayerCards = sortCards(g.getPlayerToCardsMap().get(sourcePlayer));// sort
        System.out.println("Карты которые есть у атакующего " + sourcePlayerCards);

        g.putCard(sourcePlayerCards.get(0));
        sourcePlayerCards.remove(sourcePlayerCards.get(0));

        if (sourcePlayerCards.size() == 0) {
            System.out.println("Удалился атакующий " + sourcePlayer);
            g.removePlayerMap(sourcePlayer);
            g.removePlayer(sourcePlayer);
            if(isEnd) return null;
            return beforeSource;
        }
        putSimilarCards(g, targetPlayer);
        System.out.println("Игроки подкинули.");
        if(!g.isPlayerExist(sourcePlayer) && g.isPlayerExist(beforeSource)) { // зацикливается
            System.out.println("136");
            return beforeSource;
        }

        else {
            System.out.println("ВЕРНУЛИ ТОГО КТО СУЩЕСТВУЕТ");
        }
        System.out.println("Карты которые есть у атакующего после атаки " + sourcePlayerCards);
        return sourcePlayer;
    }

    private boolean targetPlayerNew(Game g, Player targetPlayer) {
        List<Card> targetPlayerCard = sortCards(g.getPlayerToCardsMap().get(targetPlayer)); // карты отбиваюищего
        System.out.println("Карты которые есть у защищищаюегося " + targetPlayerCard);
        List<Card> cardsToDefine = sortCards(g.getCards()); // карты которые надо отбить
        List<Card> allCardsToDefine = cardsToDefine;
        List<Fight> fights = new ArrayList<>();
        Iterator<Card> targetPlayerCardIterator = targetPlayerCard.iterator();
        Iterator<Card> cardsToDefineIterator = cardsToDefine.iterator();

        while (cardsToDefineIterator.hasNext()) {
            Card cardToDefine = cardsToDefineIterator.next();

            boolean isBeat = false;
            while (targetPlayerCardIterator.hasNext()) {
                Card playerCard = targetPlayerCardIterator.next();

                if(isBeat(cardToDefine, playerCard)) {
                    targetPlayerCardIterator.remove(); // отправили карту которую надо побить в бито
                    isBeat = true;
                }
            }
            if(isBeat) {
                cardsToDefineIterator.remove(); // отправили карту игрока в бито, если он ее побил
            }
        }
        if (cardsToDefine.size() != 0) {
            g.getPlayerToCardsMap().get(targetPlayer).addAll(allCardsToDefine); // если не смог отбить хотя бы одну карту забирает все
            g.clearCardsOnDeck(); // все карты на столе чтобы потянуть игрок забрал и их теперь нет на столе
            return true;
        } else {
            g.removeCardsInPlayer(targetPlayer, allCardsToDefine);
            if(targetPlayerCard.size() == 0) {
                System.out.println("Удалился защищающийся " + targetPlayer);
                g.removePlayer(targetPlayer);
                g.getPlayerToCardsMap().remove(targetPlayer);
            }
            return false;
        }
    }

    private boolean targetPlayer(Game g, Player targetPlayer) {
        List<Card> targetPlayerCard = sortCards(g.getPlayerToCardsMap().get(targetPlayer));
        List<Card> cardsToDefine = sortCards(g.getCards());


        Map<Player, List<Card>> players = g.getPlayerToCardsMap();
        List<Card> cards = new ArrayList<>(g.getPlayerToCardsMap().get(targetPlayer));
        System.out.println("Карты которые есть у защищищаюегося " + cards);
        List<Card> toDelete = new ArrayList<>();

        Iterator<Card> targetPlayerCardIterator = targetPlayerCard.iterator();
        Iterator<Card> cardIterator = cardsToDefine.iterator();

        List<Fight> fights = new ArrayList<>();

        while (cardIterator.hasNext()) {
            Card nextCard = cardIterator.next();
            boolean down = false;
            while (targetPlayerCardIterator.hasNext()) {
                Card playerCard = targetPlayerCardIterator.next();
                if (isBeat(nextCard, playerCard)) {
                    targetPlayerCardIterator.remove();
                    cards.remove(playerCard);
                    toDelete.add(playerCard);
//                    players.get(targetPlayer).remove(playerCard);
                    down = true;
                    fights.add(new Fight(nextCard, playerCard));
                }
            }
            cardIterator.remove();

            if (!down) {
                targetPlayerCard.addAll(cardsToDefine);
                fights.add(new Fight(null, null));
                players.get(targetPlayer).addAll(cardsToDefine); //WRONG
                g.setPlayerToCardsMap(players);
                //g.setRound(fights);
                g.clearCardsOnDeck();
                return true;
            }
        }
        if(cards.size() == 0) {
            System.out.println("Удалился защищающийся " + targetPlayer);
            g.getPlayerToCardsMap().remove(targetPlayer);
            g.removePlayerMap(targetPlayer);
            g.removePlayer(targetPlayer);
            //g.setRound(fights);
            g.clearCardsOnDeck();
            return false;
        }
        players.get(targetPlayer).removeAll(toDelete);

        g.setPlayerToCardsMap(players);
        //g.setRound(fights);
        g.clearCardsOnDeck();
        return false;
    }

    private boolean isBeat(Card card, Card playerCard) {
        return card.getSuit() == playerCard.getSuit()
                && card.getFace().getRank() < playerCard.getFace().getRank();
    }


    private void putSimilarCards(Game g, Player target) {
        if (g.getPlayers().size() == 2) {
            return;
        }

        int count = Math.min(g.getPlayerToCardsMap().get(target).size(), 6);

        List<Card> cards = g.getCards();
        Map<Player, List<Card>> playerToCardsMap = g.getPlayerToCardsMap();

        List<Card> cardsToDesk = new ArrayList<>(cards);

        for (Map.Entry<Player, List<Card>> entry : playerToCardsMap.entrySet()) {
            if (target.equals(entry.getKey())) {
                continue;
            }
            List<Card> list = entry.getValue();
            Iterator<Card> playerCardIterator = list.iterator();

            for (Card card : cards) {
                while (playerCardIterator.hasNext()) {
                    Card playerCard = playerCardIterator.next();
                    if (card.getFace().getRank() == playerCard.getFace().getRank() && count != 0) {

                        cardsToDesk.add(playerCard);
                        playerCardIterator.remove();
                        count--;
                    }
                }
            }
        }
        Iterator<Map.Entry<Player, List<Card>>> iterator = playerToCardsMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Player, List<Card>> entry = iterator.next();
            if(entry.getValue().size() == 0) {
                iterator.remove();
                g.removePlayer(entry.getKey());
                System.out.println(g.getPlayers().toString() + "ПОСЛЕ УДАЛЕНИЕМ");
                System.out.println("Удален игрок " + entry.getKey() + ", когда подкидывал");
            }
        }
        //System.out.println(mapToString(playerToCardsMap) + "ИГРОКИ И КАРТЫ ПОСЛЕ ПОДКИДЫВАНИЯ");
        g.setPlayerToCardsMap(playerToCardsMap);
        g.setCards(cardsToDesk);
    }


    public List<Card> sortCards(List<Card> cards) {
        Collections.sort(cards, Comparator.comparingInt(o -> o.getFace().getRank()));
        return cards;
    }

    private boolean isGameAlive(Game g) {
        if(g.getPlayers().size() == 1) System.out.println("Проиграл " + g.getPlayers().get(0));
        return g.getPlayers().size() != 1;
    }

    public void initGameWithPlayers(Game g, int playersCount) {
        CyclicLinkedList<Player> players = new CyclicLinkedList<>();
        if (playersCount > 1 && playersCount <= 7) {
            for (int i = 1; i <= playersCount; i++) {
                players.addLast(new Player("Player: " + i + "."));
            }
        }
        g.setPlayers(players);
        System.out.println(players.toString());
    }

    public void initGameWithCards(Game g) {
        Stack<Card> deck = new Stack<>();
        Stack<Card> playDeck = new Stack<>();
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                deck.push(new Card(suit, face));
            }
        }
        shuffle(deck);
        Card trump = deck.remove(35);
        g.setTrump(trump);
        playDeck.push(trump);
        for (Card card : deck) {
            playDeck.push(card);
        }
        g.setColoda(playDeck);

    }

    private void shuffle(Stack<Card> cards) {
        List<Card> cardsTmp = new ArrayList<>();
        cardsTmp.addAll(cards);
        Collections.shuffle(cardsTmp);
        cards.removeAllElements();
        cards.addAll(cardsTmp);
    }

    public void initGameWithPlayersNCards(Game g) throws Exception {
        Map<Player, List<Card>> playerCards = new HashMap<>();
        CyclicLinkedList<Player> players = g.getPlayers();
        Stack<Card> cards = g.getColoda();

        for (int i = 0; i < players.size(); i++) {
            Stack<Card> cardsToPlayer = new Stack<>();
            for (int j = 0; j < 6; j++) {
                cardsToPlayer.push(cards.pop());
            }
            System.out.println(players.get(i).toString());
            playerCards.put(players.get(i), cardsToPlayer);

        }

        g.setPlayerToCardsMap(playerCards);
        g.setColoda(cards);

    }

    private String mapToString(Map<Player, List<Card>> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Player, List<Card>> pair : map.entrySet()) {
            Player player = pair.getKey();
            sb.append(player);
            sb.append(" -> ");
            for (Card card : pair.getValue()) {
                sb.append(card).append(", ");
            }
        }

        return sb.toString();
    }
}
