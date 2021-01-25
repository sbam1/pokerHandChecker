package com.mygame.poker.rule;

import com.mygame.poker.model.PokerHandRankType;

import java.util.HashMap;
import java.util.Map;

import static com.mygame.poker.model.PokerHandRankType.FLUSH;
import static com.mygame.poker.model.PokerHandRankType.FOUR_OF_A_KIND;
import static com.mygame.poker.model.PokerHandRankType.FULL_HOUSE;
import static com.mygame.poker.model.PokerHandRankType.HIGH_CARD;
import static com.mygame.poker.model.PokerHandRankType.PAIR;
import static com.mygame.poker.model.PokerHandRankType.STRAIGHT;
import static com.mygame.poker.model.PokerHandRankType.STRAIGHT_FLUSH;
import static com.mygame.poker.model.PokerHandRankType.THREE_OF_A_KIND;
import static com.mygame.poker.model.PokerHandRankType.TWO_PAIRS;

public class PokerHandRankingRuleFactory {
    public static final Map<PokerHandRankType, PokerHandRankingRule> pokerRankRules = new HashMap<>();

    static {
        pokerRankRules.put(HIGH_CARD, new HighCard());
        pokerRankRules.put(TWO_PAIRS, new TwoPairs());
        pokerRankRules.put(THREE_OF_A_KIND, new ThreeOfAKind());
        pokerRankRules.put(FLUSH, new Flush());
        pokerRankRules.put(FULL_HOUSE, new FullHouse());
        pokerRankRules.put(FOUR_OF_A_KIND, new FourOfKind());
        pokerRankRules.put(PAIR, new Pair());
        pokerRankRules.put(STRAIGHT, new Straight());
        pokerRankRules.put(STRAIGHT_FLUSH, new StraightFlush());

    }

    public static void registerRankingRule(PokerHandRankType type, PokerHandRankingRule newRule) {
        pokerRankRules.put(type, newRule);

    }

    public static PokerHandRankingRule getPokerHandRankingRule(PokerHandRankType pokerHandRankType) {
        return pokerRankRules.get(pokerHandRankType);

    }

}
