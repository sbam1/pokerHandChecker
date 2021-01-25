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

public class TwoPairsTest {

    private TwoPairs subject;

    @Before
    public void setup() {
        subject = new TwoPairs();
    }

    @Test
    public void shouldWinPlayerWithTwoPair() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithTwoPair().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithTwoPair().getPokerPlayers().get(1));

        subject.executeRule(input);
        Assert.assertEquals("playerTwo", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithHigherPairs() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithPairWithBothButOneHigherPairThanOther().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithPairWithBothButOneHigherPairThanOther().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithSamePairWithOtherHigherCard() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithSameTwoPairWithBoth().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithSameTwoPairWithBoth().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldTieWhenBothHasSamePairAndSameWeightedOtherCards() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithSameTwoPairTie().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithSameTwoPairTie().getPokerPlayers().get(1));

        subject.executeRule(input);
        assertTrue((Boolean) input.get("TIE"));
        assertNull((input.get("WINNER")));
    }

    private PokerTable createPokerHandWithTwoPair() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.TWO);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SEVEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.DIAMONDS, CardNumber.TWO);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithPairWithBothButOneHigherPairThanOther() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.HEARTS, CardNumber.NINE);
        Card card3 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.JACK);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.JACK);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithSameTwoPairWithBoth() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.DIAMONDS, CardNumber.THREE);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.THREE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);

        Card card11 = new Card(CardCategory.CLUBS, CardNumber.THREE);
        Card card12 = new Card(CardCategory.HEARTS, CardNumber.THREE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.TWO);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithSameTwoPairTie() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.CLUBS, CardNumber.NINE);
        Card card3 = new Card(CardCategory.SPADES, CardNumber.NINE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.QUEEN);
        Card card5 = new Card(CardCategory.HEARTS, CardNumber.QUEEN);

        Card card12 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card13 = new Card(CardCategory.HEARTS, CardNumber.NINE);
        Card card11 = new Card(CardCategory.DIAMONDS, CardNumber.NINE);
        Card card14 = new Card(CardCategory.CLUBS, CardNumber.QUEEN);
        Card card15 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);

        PokerPlayer playerOne = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerTwo = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

}