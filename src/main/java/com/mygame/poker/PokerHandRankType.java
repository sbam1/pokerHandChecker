package com.mygame.poker;

public enum PokerHandRankType {
    STRAIGHT_FLUSH("Straight flush", 1),
    FOUR_OF_A_KIND("Four of a kind", 2),
    FULL_HOUSE("Full House", 3),
    FLUSH("Flush", 4),
    STRAIGHT("Straight", 5),
    THREE_OF_A_KIND("hree of a Kind: ", 6),
    TWO_PAIRS("Two Pairs", 7),
    PAIR("Pair", 8),
    HIGH_CARD("High Card", 9);

    private String name;
    private int rank;

    PokerHandRankType(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
