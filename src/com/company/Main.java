package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
//        Game g = new Game();
//        GameService svc = new GameService();
//        svc.play(g, 6);
        CyclicLinkedList<Player> list = new CyclicLinkedList();
        list.addLast(new Player("1"));
        list.addLast(new Player("2"));
        list.addLast(new Player("3"));

        for (int i = 0; i < 5; i++) {
            System.out.println(list.getNext(list.get(i)));
        }
	// write your code here
    }
}
