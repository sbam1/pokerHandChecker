package com.mygame.poker;

import com.mygame.poker.rule.PokerHandRankingRule;
import com.mygame.poker.rule.PokerHandRankingRuleFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHandResultService {

    public void checkPokerTablesResult(List<PokerTable> pokerTables) {
        pokerTables.forEach(this::check);
    }


    private void check(PokerTable pokerTable) {
        Map<String, Object> modelObject = new HashMap<>();
        modelObject.put("POKER_HAND", pokerTable);
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
                System.out.println(modelObject.get("WINNER") + " wins. - with " + modelObject.get("REASON"));
                break;
            }
        }
    }

}
