package com.mygame.poker;

import java.util.Arrays;

public class PokerHandCheckerApplication {

    public static void main(String[] args) {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        PokerHandResultService pokerHandResultService = new PokerHandResultService();
        pokerHandResultService.displayResult(Arrays.asList(pokerHand1));

    }


}
