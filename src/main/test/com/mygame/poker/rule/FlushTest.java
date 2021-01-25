package com.mygame.poker.rule;


import com.mygame.poker.model.Card;
import com.mygame.poker.model.CardCategory;
import com.mygame.poker.model.CardNumber;
import com.mygame.poker.model.PokerPlayer;
import com.mygame.poker.model.PokerTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FlushTest {

    private Flush subject;

    @Before
    public void setup() {
        subject = new Flush();
    }


    @Test
    public void shouldWinPlayerWithFlush() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithSingleFlush().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithSingleFlush().getPokerPlayers().get(1));

        subject.executeRule(input);
        Assert.assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
        Assert.assertTrue((Boolean) input.get("RESULT"));
    }

    @Test
    public void shouldWinPlayerWithHigherFlush() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithFlushWithBothPlayers().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithFlushWithBothPlayers().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertEquals("playerTwo", ((PokerPlayer) input.get("WINNER")).getPlayerName());
        Assert.assertTrue((Boolean) input.get("RESULT"));
    }

    @Test
    public void ruleShouldTieIfBothHasSameWeightedFlush() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithFlushWithBothPlayersWithSameWeight().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithFlushWithBothPlayersWithSameWeight().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertTrue((Boolean) input.get("TIE"));
        assertNull((input.get("WINNER")));
        Assert.assertTrue((Boolean) input.get("RESULT"));
    }


    @Test
    public void ruleShouldNotDecideTheWinnerIfFlushNotAvailable() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithoutFlush().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithoutFlush().getPokerPlayers().get(1));

        subject.executeRule(input);
        Assert.assertNull(input.get("WINNER"));
        Assert.assertFalse((Boolean) input.get("RESULT"));
    }


    private PokerTable createPokerHandWithSingleFlush() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card3, card4, card5, card1, card2));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card14, card15, card11, card12, card13));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithFlushWithBothPlayers() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.SEVEN);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.JACK);
        Card card15 = new Card(CardCategory.HEARTS, CardNumber.TEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithFlushWithBothPlayersWithSameWeight() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TEN);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.TEN);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.FOUR);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.FIVE);
        Card card15 = new Card(CardCategory.HEARTS, CardNumber.SIX);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }


    private PokerTable createPokerHandWithoutFlush() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.FOUR);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.FIVE);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.FOUR);
        Card card14 = new Card(CardCategory.HEARTS, CardNumber.FIVE);
        Card card15 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

}