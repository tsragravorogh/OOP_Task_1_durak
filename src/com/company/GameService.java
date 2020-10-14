package com.company;

import java.util.*;

public class GameService {
    private List<Player> players = new ArrayList<>();
    //private Iterator it = (Iterator) new CyclicLinkedList<Player>();
    private Integer countSource = -1;
    private Integer countTarget = 0;

    public void play(Game g, int playersCount) throws Exception {
        initGameWithPlayers(g, playersCount);
        initGameWithCards(g);
        initGameWithPlayersNCards(g);
        //deal cards
        while (!isDeckEmpty(g) || isGameAlive(g)) {

            getSourcePlayer(g); // ходящий
            getTargetPlayer(g); // отбивающий
            Round r = new Round();
//            r.setSource();
//            r.setTarget();

            //Fight f = buildFight();

        }
    }

    private Player getNextSourcePlayer() {
        if (countSource == players.size() - 1) {
            countSource = -1;
        }
        countSource++;
        return players.get(countSource);
    }

    private Player getNextTargetPlayer() {
        if (countTarget == players.size() - 1) {
            countTarget = -1;
        }
        countTarget++;
        return players.get(countTarget);
    }

    private void getSourcePlayer(Game g) {
        Player sourcePlayer = getNextSourcePlayer();
        List<Card> sourcePlayerCards = sort(g.getPlayerToCardsMap().get(sourcePlayer)); // переписать сорт
        g.putCards(sourcePlayerCards.get(0)); // кладу карту на стол
        sourcePlayerCards.remove(sourcePlayerCards.get(0));
        putSimilarCards(g); // игроки подкидывают карты
        if(sourcePlayerCards.size() == 0) {
            players.remove(sourcePlayer);
            g.removePlayerMap(sourcePlayer);
        }
        // взять игрока из бесконечного цикла ??? +
        // взять верхнюю карту в отсортированном массиве +
        // положить ее на стол - в game
        // пройти по всем картам всех игроков и выложить на стол все карты, какие лежат на столе
    }

    private void getTargetPlayer(Game g) {
        Player targetPlayer = getNextTargetPlayer();
        List<Card> targetPlayerCard = sort(g.getPlayerToCardsMap().get(targetPlayer)); // карты игрока
        List<Card> cardsToDefine = g.getCards(); // карты, которые нужно побить
        Iterator<Card> cardIterator = cardsToDefine.iterator();
        Iterator<Card> targetPlayerCardIterator = targetPlayerCard.iterator();

        while (cardIterator.hasNext()) {
            Card nextCard = cardIterator.next();
            while (targetPlayerCardIterator.hasNext()) {
                Card playerCard = targetPlayerCardIterator.next();
                if (isBeat(nextCard, playerCard)) {
                    cardsToDefine.remove(playerCard);  // бьет карту
                    targetPlayerCard.remove(nextCard); // удаляется(убирается) карта которую побили
                } else {
                    targetPlayerCard.add(nextCard);
                }
            }
        }

        g.clearCardsOnDeck(); // игрок либо отбивается, либо нет -> карт на столе нет

        // взять следующего игрока из бесконечного цикла
        // взять карты   со стола, проверить все карты на возможность побить
        // если можно побить - бить, если нет то тянуть  -> записать результат в какую то сессию
    }

    private boolean isBeat(Card firstCard, Card secondCard) { // wrong
        return (firstCard.getSuit().equals(secondCard.getSuit())
                && Integer.parseInt(firstCard.getFace().getValue()) > Integer.parseInt(secondCard.getFace().getValue()));
    }

    private void putSimilarCards(Game g) {
        if(g.getPlayers().size() == 2) {
            return;
        }

        // <6, проверить количество карт у отбивающего
        List<Card> cards = g.getCards();
        Map<Player, List<Card>> playerToCardsMap = g.getPlayerToCardsMap();

        for (Map.Entry<Player, List<Card>> entry : playerToCardsMap.entrySet()) {
            for (Card playerCard : entry.getValue()) {
                for (Card card : cards) {
                    if (card.getFace() == playerCard.getFace()) {
                        g.putCards(playerCard); // лучше использовать стек, удобнее удалять
                    }
                }
            }
        }
    }

    private List<Card> sort(List<Card> cards) {
        return cards;
    }

    private boolean isGameAlive(Game g) {
        return g.getPlayers().size() != 0;
    }

    private boolean isDeckEmpty(Game g) {
        return g.getPlayDeck().size() != 0;
    }

    public void initGameWithPlayers(Game g, int playersCount) {
        CyclicLinkedList<Player> players = new CyclicLinkedList<>();
        if (playersCount > 1 && playersCount <= 7) {
            for (int i = 0; i < playersCount; i++) {
                //players.addLast(new Player());
            }
        }
        g.setPlayers(players);
    }

    public void initGameWithCards(Game g) {
        Stack<Card> playDeck = new Stack<>();
        for (Suit suit : Suit.items) {
            for (Face face : Face.items) {
                playDeck.push(new Card(suit, face));
            }
        }
        shuffle(playDeck);
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
        HashMap<Player, Stack<Card>> playerCards = new HashMap<>();
        CyclicLinkedList<Player> players = g.getPlayers();
        Stack<Card> cards = g.getColoda();

        for (int i = 0; i < players.size; i++) {
            Stack<Card> cardsToPlayer = new Stack<>();
            for (int j = 0; j < 6; j++) {
                cardsToPlayer.push(cards.pop());
            }
            //playerCards.put(players.get(i), cardsToPlayer);
        }
    }

}
