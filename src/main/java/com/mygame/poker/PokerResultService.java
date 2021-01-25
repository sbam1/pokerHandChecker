package com.mygame.poker;

import com.mygame.poker.rule.PokerHandRankingRule;
import com.mygame.poker.rule.PokerHandRankingRuleFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mygame.poker.util.Constants.POKER_TABLE;
import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;

public class PokerResultService {

    public void checkPokerTablesResult(List<PokerTable> pokerTables) {
        pokerTables.forEach(this::check);
    }


    private void check(PokerTable pokerTable) {
        Map<String, Object> modelObject = new HashMap<>();
        modelObject.put(POKER_TABLE, pokerTable);
        modelObject.put(RESULT, false);
        modelObject.put(REASON, null);
        modelObject.put(WINNER, null);
        modelObject.put(TIE, false);

        for(PokerHandRankType type: PokerHandRankType.values()) {

            PokerHandRankingRule pokerHandRankingRule = PokerHandRankingRuleFactory.getPokerHandRankingRule(type);
            pokerHandRankingRule.executeRule(modelObject);
            if((boolean) modelObject.get(TIE)) {
                System.out.println(TIE);
            }
            if ((boolean) modelObject.get(RESULT) && !(boolean) modelObject.get(TIE)) {
                System.out.println(modelObject.get(WINNER) + " wins. - with " + modelObject.get(REASON));
                break;
            }
        }
    }

}
