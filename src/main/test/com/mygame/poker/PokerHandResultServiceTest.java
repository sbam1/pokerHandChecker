package com.mygame.poker;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class PokerHandResultServiceTest {

    private PokerHandResultService service;

    @Before
    public void setup() {
        service = new PokerHandResultService();
    }

    @Test
    public void testForStraightFlush() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.JACK);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.EIGHT);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

    @Test
    public void testForFourOfAKind() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.JACK);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }


    @Test
    public void testFullHouse() {

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
        service.displayResult(Arrays.asList(pokerHand1));

    }



    @Test
    public void testFlush() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.JACK);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.EIGHT);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

    @Test
    public void testStraight() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.KING);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.KING);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card14 = new Card(CardCategory.DIAMONDS, CardNumber.SEVEN);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.EIGHT);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

    @Test
    public void testThreeOfAkKind() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.TEN);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.NINE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.SPADES, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

    @Test
    public void testTwoPair() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card14 = new Card(CardCategory.SPADES, CardNumber.SEVEN);
        Card card15 = new Card(CardCategory.SPADES, CardNumber.TEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

    @Test
    public void testPair() {

        Card card1 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.ACE);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.KING);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.DIAMONDS, CardNumber.ACE);
        Card card14 = new Card(CardCategory.SPADES, CardNumber.SEVEN);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.EIGHT);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

    @Test
    public void testHighCard() {

        Card card1 = new Card(CardCategory.HEARTS, CardNumber.TEN);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.ACE);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.KING);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card13 = new Card(CardCategory.DIAMONDS, CardNumber.ACE);
        Card card14 = new Card(CardCategory.SPADES, CardNumber.SEVEN);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.EIGHT);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        PokerHand pokerHand1 = new PokerHand(playerOne, playerTwo);
        service.displayResult(Arrays.asList(pokerHand1));

    }

}