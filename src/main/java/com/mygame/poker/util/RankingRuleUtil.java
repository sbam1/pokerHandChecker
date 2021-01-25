package com.mygame.poker.util;

import com.mygame.poker.Card;
import com.mygame.poker.CardCategory;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerPlayer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RankingRuleUtil {

    private RankingRuleUtil() {
    }


    //ACE, 2, 3, 4 ,5 not considering Straight for now.
    public static boolean isStraight(PokerPlayer player) {
        List<Card> cards = player.getCards();
        for(int i= 1; i < cards.size(); i++) {
            if(cards.get(i).getNumber().getWeight()+ 1 != cards.get(i-1).getNumber().getWeight()){
                return false;
            }
        }
        return true;
    }

    public static boolean isFlush(PokerPlayer player) {
        Map<CardCategory, CardNumber> numberMap = player.getCards().stream().collect(Collectors
                .toMap(Card::getCardCategory, Card::getNumber, (a, b) -> b));
        if(numberMap.size() == 1) {
            return true;
        }

        return false;
    }

    public static int compareTwoCards(Card cardOne, Card cardTwo) {
        return cardOne.getNumber().getWeight() - cardTwo.getNumber().getWeight();
    }
}
