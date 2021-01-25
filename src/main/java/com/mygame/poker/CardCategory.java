package com.mygame.poker;

public enum CardCategory {
    CLUBS("Clubs","C"),
    DIAMONDS("Diamonds","D"),
    HEARTS("Hearts","H"),
    SPADES("Spades","S");

    private String name;
    private String symbol;


    CardCategory(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
