package com.mygame.poker;

import java.util.ArrayList;
import java.util.List;

public class PokerPlayer {

    private String playerName;
    private List<Card> cards =  new ArrayList<>(5);

    public PokerPlayer() {
    }

    public PokerPlayer(String playerName, List<Card> cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString(){
        return playerName;
    }
}
