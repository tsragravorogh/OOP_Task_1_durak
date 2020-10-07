package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        Game g = new Game();
        GameService svc = new GameService();
        svc.play(g, 6);

	// write your code here
    }
}
