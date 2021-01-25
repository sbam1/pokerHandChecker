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

public class StraightFlushTest {

    private StraightFlush subject;

    @Before
    public void setUp(){
        subject = new StraightFlush();
    }

    @Test
    public void shouldWinPlayerWithStraightFlush(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_TABLE", createPokerHandWithSingleStraightFlush());
        subject.executeRule(input);
        Assert.assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithHigherStraightFlush(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_TABLE", createPokerHandWithStraightFlushWithBothPlayers());
        subject.executeRule(input);
        assertEquals("playerTwo", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldTieWhenBothHasSamePairAndSameWeightedOtherCards(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_TABLE", createPokerHandWithStraightFlushWithBothPlayersWithSameWeight());
        subject.executeRule(input);
        assertTrue((Boolean) input.get("TIE"));
        assertNull((input.get("WINNER")));
    }


    @Test
    public void ruleShouldNotDecideTheWinnerIfNoStraightFlush(){
        Map<String, Object> input = new HashMap<>();
        input.put("POKER_TABLE", createPokerHandWithoutStraightFlush());
        subject.executeRule(input);
        Assert.assertNull(input.get("WINNER"));
        Assert.assertFalse((Boolean) input.get("RESULT"));
    }


    private PokerTable createPokerHandWithSingleStraightFlush() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card3, card4, card5, card1, card2));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card14, card15, card11, card12, card13));

        return new PokerTable(playerOne, playerTwo);
    }

    private PokerTable createPokerHandWithStraightFlushWithBothPlayers() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.KING);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card15 = new Card(CardCategory.HEARTS, CardNumber.TEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(playerOne, playerTwo);
    }

    private PokerTable createPokerHandWithStraightFlushWithBothPlayersWithSameWeight() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.FOUR);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.FIVE);
        Card card15 = new Card(CardCategory.HEARTS, CardNumber.SIX);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(playerOne, playerTwo);
    }


    private PokerTable createPokerHandWithoutStraightFlush() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.FOUR);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.FIVE);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(playerOne, playerTwo);
    }


}