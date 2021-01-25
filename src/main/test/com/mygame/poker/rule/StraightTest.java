package com.mygame.poker.rule;


import com.mygame.poker.Card;
import com.mygame.poker.CardCategory;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerTable;
import com.mygame.poker.PokerPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class StraightTest {
    private Straight subject;

    @Before
    public void setup() {
        subject = new Straight();
    }


    @Test
    public void shouldWinPlayerWithStraightFlush(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_HAND", createPokerHandWithSingleStraight());
        subject.executeRule(input);
        Assert.assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithHigherStraightFlush(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_HAND", createPokerHandWithStraightWithBothPlayers());
        subject.executeRule(input);
        assertEquals("playerTwo", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldTieWhenBothHasSamePairAndSameWeightedOtherCards(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_HAND", createPokerHandWithStraightWithBothPlayersWithSameWeight());
        subject.executeRule(input);
        assertTrue((Boolean) input.get("TIE"));
        assertNull((input.get("WINNER")));
    }


    @Test
    public void ruleShouldNotDecideTheWinnerIfNoStraightFlush(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_HAND", createPokerHandWithoutStraight());
        subject.executeRule(input);
        Assert.assertNull(input.get("WINNER"));
        Assert.assertFalse((Boolean) input.get("RESULT"));
    }


    private PokerTable createPokerHandWithSingleStraight() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.SPADES, CardNumber.THREE);
        Card card3 = new Card(CardCategory.HEARTS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.DIAMONDS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.DIAMONDS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.SPADES, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card3, card4, card5, card1, card2));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card14, card15, card11, card12, card13));

        return new PokerTable(playerOne, playerTwo);
    }

    private PokerTable createPokerHandWithStraightWithBothPlayers() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.KING);
        Card card13 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card15 = new Card(CardCategory.SPADES, CardNumber.TEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(playerOne, playerTwo);
    }

    private PokerTable createPokerHandWithStraightWithBothPlayersWithSameWeight() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.DIAMONDS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.FOUR);
        Card card14 = new Card(CardCategory.DIAMONDS, CardNumber.FIVE);
        Card card15 = new Card(CardCategory.HEARTS, CardNumber.SIX);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(playerOne, playerTwo);
    }


    private PokerTable createPokerHandWithoutStraight() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.HEARTS, CardNumber.NINE);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.SPADES, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TEN);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(playerOne, playerTwo);
    }



}