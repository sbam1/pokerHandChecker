package com.mygame.poker;

import com.mygame.poker.rule.PokerHandRankingRule;
import com.mygame.poker.rule.PokerHandRankingRuleFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHandResultService {

    public void displayResult(List<PokerHand> pokerHands) {
        pokerHands.forEach(this::check);
    }


    private void check(PokerHand pokerHand) {
        Map<String, Object> modelObject = new HashMap<>();
        modelObject.put("POKER_HAND", pokerHand);
        modelObject.put("RESULT", false);
        modelObject.put("REASON", null);
        modelObject.put("WINNER", null);
        modelObject.put("TIE", false);
        for(PokerHandRankType type: PokerHandRankType.values()) {

            PokerHandRankingRule pokerHandRankingRule = PokerHandRankingRuleFactory.getPokerHandRankingRule(type);
            pokerHandRankingRule.executeRule(modelObject);
            if((boolean) modelObject.get("TIE")) {
                System.out.println("TIE");
            }
            if ((boolean) modelObject.get("RESULT") && !(boolean) modelObject.get("TIE")) {
                //White wins. - with high card: Ace
                System.out.println(modelObject.get("WINNER") + " wins. - with " + modelObject.get("REASON"));
                break;
            }
        }


//        PokerHandResultRule result = calculatePokerHandResult(pokerHand.getPlayer1());
    }

}
