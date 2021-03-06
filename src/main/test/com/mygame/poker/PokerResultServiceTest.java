package com.mygame.poker;


import com.mygame.poker.model.Card;
import com.mygame.poker.model.CardCategory;
import com.mygame.poker.model.CardNumber;
import com.mygame.poker.model.PokerPlayer;
import com.mygame.poker.model.PokerTable;
import com.mygame.poker.service.PokerResultService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class PokerResultServiceTest {

    private PokerResultService service;

    @Before
    public void setup() {
        service = new PokerResultService();
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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

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

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

    }

    @Test
    public void testFullHouseWithThreePlayers() {

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

        Card card21 = new Card(CardCategory.SPADES, CardNumber.TEN);
        Card card22 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card23 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card24 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card25 = new Card(CardCategory.SPADES, CardNumber.JACK);


        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerThree = new PokerPlayer("playerThree", Arrays.asList(card11, card12, card13, card14, card15));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card21, card22, card23, card24, card25));

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo, playerThree));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

    }

    @Test
    public void testFullHouseWithFourPlayers() {

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

        Card card21 = new Card(CardCategory.SPADES, CardNumber.TEN);
        Card card22 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card23 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card24 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card25 = new Card(CardCategory.SPADES, CardNumber.JACK);


        Card card31 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card32 = new Card(CardCategory.CLUBS, CardNumber.SIX);
        Card card33 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card34 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);
        Card card35 = new Card(CardCategory.CLUBS, CardNumber.EIGHT);


        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerThree = new PokerPlayer("playerThree", Arrays.asList(card11, card12, card13, card14, card15));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card21, card22, card23, card24, card25));
        PokerPlayer playerFour = new PokerPlayer("playerFour", Arrays.asList(card31, card32, card33, card34, card35));

        PokerTable pokerTable1 = new PokerTable(Arrays.asList(playerOne, playerTwo, playerThree, playerFour));
        service.checkPokerTablesResult(Arrays.asList(pokerTable1));

    }

}