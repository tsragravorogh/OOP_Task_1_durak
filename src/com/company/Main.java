package com.company;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Game g = new Game();

        GameService svc = new GameService();
        svc.play(g, 6);

//        svc.initGameWithCards(g);
//        svc.initGameWithPlayers(g, 6);
//        svc.initGameWithPlayersNCards(g);
//        CyclicLinkedList<Player> list = g.getPlayers();

//        List<Card> players = g.getPlayerToCardsMap().get(list.get(1));
//        System.out.println(players);
//        players = svc.sort(players);
//        System.out.println(players);
//        svc.play(g, 6);
//        CyclicLinkedList<Player> list = new CyclicLinkedList();
//        list.addLast(new Player("1"));
//        list.addLast(new Player("2"));
//        list.addLast(new Player("3"));
//
//
//        System.out.println(list.toString());
//
//        Player player = list.get(1);
//        list.removePlayer(player);
//
//
//        System.out.println(list.toString());
//
//        System.out.println(list.findPlayerBeforeByIndex(1));
//
//        Player player1 = list.findPlayerBeforeByIndex(1);
//
//        System.out.println(list.indexByPlayer(player1));


        // write your code here
    }
}
