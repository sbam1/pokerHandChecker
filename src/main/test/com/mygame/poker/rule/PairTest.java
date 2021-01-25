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

public class PairTest {

    private Pair subject;

    @Before
    public void setUp() {
        subject = new Pair();
    }

    @Test
    public void shouldWinPlayerWithPair() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithSinglePair().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithSinglePair().getPokerPlayers().get(1));

        subject.executeRule(input);
        Assert.assertEquals("playerTwo", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithHigherPair() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithPairWithBothButOneHigherPairThanOther().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithPairWithBothButOneHigherPairThanOther().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithSamePairWithOtherHigherCard() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithPairWithBoth().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithPairWithBoth().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldTieWhenBothHasSamePairAndSameWeightedOtherCards() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithSamePairTie().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithSamePairTie().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertTrue((Boolean) input.get("TIE"));
        assertNull((input.get("WINNER")));
    }

    private PokerTable createPokerHandWithSinglePair() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);
        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithPairWithBothButOneHigherPairThanOther() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);
        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithPairWithBoth() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithSamePairTie() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);

        Card card12 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card11 = new Card(CardCategory.DIAMONDS, CardNumber.NINE);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

}