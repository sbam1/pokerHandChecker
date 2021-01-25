package com.mygame.poker.model;

import java.util.ArrayList;
import java.util.List;

public class PokerTable {
    private PokerPlayer player1;
    private PokerPlayer player2;

    private List<PokerPlayer> pokerPlayers = new ArrayList<>();

    public PokerTable() {
    }

    public PokerTable(List<PokerPlayer> players) {
        this.pokerPlayers = players;
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

    public List<PokerPlayer> getPokerPlayers() {
        return pokerPlayers;
    }

    public void setPokerPlayers(List<PokerPlayer> pokerPlayers) {
        this.pokerPlayers = pokerPlayers;
    }
}
