package com.company;

import java.util.*;

public class GameService {
    private List<Player> players = new ArrayList<>();

    public void play(Game g, int playersCount) throws Exception {
        initGameWithPlayers(g, playersCount);
        initGameWithCards(g);
        initGameWithPlayersNCards(g);
        //deal cards
        while (!isDeckEmpty(g) || isGameAlive(g)) {

            getSourcePlayer(); // ходящий
            getTargetPlayer(); // отбивающий
            Round r = new Round();

            getFirstCard();
            Fight f = buildFight();

        }
    }

    private getPlayer

    private void getSourcePlayer() {
        g.getPlayer();


        // взять игрока из бесконечного цикла ???
        // взять верхнюю карту в отсортированном массиве
        // положить ее на стол - в game
        // пройти по всем картам всех игроков и выложить на стол все карты, какие лежат на столе
    }

    private void getTargetPlayer() {
        // взять следующего игрока из бесконечного цикла
        // взять карты   со стола, проверить все карты на возможность побить
        // если можно побить - бить, если нет то тянуть  -> записать результат в какую то сессию
    }

    private boolean isGameAlive(Game g) {
        return g.getPlayers().size() != 0;
    }

    private boolean isDeckEmpty(Game g) {
        return g.getPlayDeck().size() != 0;
    }

    public void initGameWithPlayers(Game g, int playersCount) {
        if(playersCount > 1 && playersCount <= 7) {
            for(int i = 0; i < playersCount; i++) {
                g.getPlayers().addLast(new Player());
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

        for(int i = 0; i < players.size; i++) {
            Stack<Card> cardsToPlayer = new Stack<>();
            for (int j = 0; j < 6; j++) {
                cardsToPlayer.push(cards.pop());
            }
            playerCards.put(players.get(i), cardsToPlayer);
        }
    }
    //getFirstCard
}
