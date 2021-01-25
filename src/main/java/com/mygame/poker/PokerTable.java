package com.mygame.poker;

public class PokerTable {
    private PokerPlayer player1;
    private PokerPlayer player2;

    public PokerTable() {
    }

    public PokerTable(PokerPlayer player1, PokerPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PokerPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(PokerPlayer player1) {
        this.player1 = player1;
    }

    public PokerPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(PokerPlayer player2) {
        this.player2 = player2;
    }
}
