package com.company;

public class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Player - ");
        sb.append(name);
        return sb.toString();
    }
}

