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

public class ThreeOfAKindTest {

    private ThreeOfAKind subject;

    @Before
    public void setup() {
        subject = new ThreeOfAKind();
    }


    @Test
    public void shouldWinPlayerWithThreeOfAKind() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithSingleThreeOfAKind().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithSingleThreeOfAKind().getPokerPlayers().get(1));

        subject.executeRule(input);

        assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void shouldWinPlayerWithHigherThreeOfAKind() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithThreeOfAKindWithBothPlayers().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithThreeOfAKindWithBothPlayers().getPokerPlayers().get(1));

        subject.executeRule(input);

        assertEquals("playerOne", ((PokerPlayer) input.get("WINNER")).getPlayerName());
    }

    @Test
    public void ruleShouldNotDecideTheWinnerIfThreeOfAKindNotPresent() {
        Map<String, Object> input = new HashMap<>();
        input.put("playerOne", createPokerHandWithoutThreeOfAKind().getPokerPlayers().get(0));
        input.put("playerTwo", createPokerHandWithoutThreeOfAKind().getPokerPlayers().get(1));

        subject.executeRule(input);
        Assert.assertNull(input.get("WINNER"));
        Assert.assertFalse((Boolean) input.get("RESULT"));
    }


    private PokerTable createPokerHandWithSingleThreeOfAKind() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card3 = new Card(CardCategory.DIAMONDS, CardNumber.ACE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.SEVEN);
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

    private PokerTable createPokerHandWithThreeOfAKindWithBothPlayers() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.HEARTS, CardNumber.ACE);
        Card card3 = new Card(CardCategory.DIAMONDS, CardNumber.ACE);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.TEN);
        Card card5 = new Card(CardCategory.CLUBS, CardNumber.SIX);

        Card card11 = new Card(CardCategory.HEARTS, CardNumber.KING);
        Card card12 = new Card(CardCategory.CLUBS, CardNumber.KING);
        Card card13 = new Card(CardCategory.DIAMONDS, CardNumber.KING);
        Card card14 = new Card(CardCategory.SPADES, CardNumber.JACK);
        Card card15 = new Card(CardCategory.HEARTS, CardNumber.TEN);

        PokerPlayer playerTwo = new PokerPlayer("playerOne", Arrays.asList(card1, card2, card3, card4, card5));
        PokerPlayer playerOne = new PokerPlayer("playerTwo", Arrays.asList(card11, card12, card13, card14, card15));

        return new PokerTable(Arrays.asList(playerOne, playerTwo));
    }

    private PokerTable createPokerHandWithoutThreeOfAKind() {
        Card card1 = new Card(CardCategory.CLUBS, CardNumber.ACE);
        Card card2 = new Card(CardCategory.HEARTS, CardNumber.KING);
        Card card3 = new Card(CardCategory.DIAMONDS, CardNumber.QUEEN);
        Card card4 = new Card(CardCategory.SPADES, CardNumber.ACE);
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

}