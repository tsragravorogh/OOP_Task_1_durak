package com.tsragravorogh.Filds;

import com.tsragravorogh.Elements.Card;

public class Fight {
    private Card down;
    private Card up;

    public Fight(Card down, Card up) {
        this.down = down;
        this.up = up;
    }

    public void setDown(Card down) {
        this.down = down;
    }

    public void setUp(Card up) {
        this.up = up;
    }
}
