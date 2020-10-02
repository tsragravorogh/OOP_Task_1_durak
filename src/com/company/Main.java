package com.company;

public class Main {

    public static void main(String[] args) {
        Game g = new Game();
        GameService svc = new GameService();
        svc.initGameWithPlayers(g, 6);
        svc.initGameWithCards(g);
        svc.play(g);

	// write your code here
    }
}
