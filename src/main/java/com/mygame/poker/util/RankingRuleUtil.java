package com.mygame.poker.util;

import com.mygame.poker.exception.InvalidInputException;
import com.mygame.poker.model.Card;
import com.mygame.poker.model.CardCategory;
import com.mygame.poker.model.CardNumber;
import com.mygame.poker.model.PokerPlayer;
import com.mygame.poker.model.PokerTable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingRuleUtil {

    private RankingRuleUtil() {
    }


    //ACE, 2, 3, 4 ,5 not considering Straight for now.
    public static boolean isStraight(PokerPlayer player) {
        List<Card> cards = player.getCards();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getNumber().getWeight() + 1 != cards.get(i - 1).getNumber().getWeight()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFlush(PokerPlayer player) {
        Map<CardCategory, CardNumber> numberMap = player.getCards().stream().collect(Collectors
                .toMap(Card::getCardCategory, Card::getNumber, (a, b) -> b));
        if (numberMap.size() == 1) {
            return true;
        }

        return false;
    }

    public static int compareTwoCards(Card cardOne, Card cardTwo) {
        return cardOne.getNumber().getWeight() - cardTwo.getNumber().getWeight();
    }

    public static void validatePokerTable(PokerTable pokerTable) {
        if (pokerTable.getPokerPlayers().size() < 2) {
            throw new InvalidInputException("must be more than 1 players in one table");
        }
        if (pokerTable.getPokerPlayers().size() > 5) {
            throw new InvalidInputException("too many players");
        }
    }
}
