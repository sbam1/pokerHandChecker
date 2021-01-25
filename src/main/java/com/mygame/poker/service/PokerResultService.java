package com.mygame.poker.service;

import com.mygame.poker.model.PokerHandRankType;
import com.mygame.poker.model.PokerPlayer;
import com.mygame.poker.model.PokerTable;
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
import static com.mygame.poker.util.RankingRuleUtil.validatePokerTable;

public class PokerResultService {

    public void checkPokerTablesResult(List<PokerTable> pokerTables) {
        pokerTables.forEach(this::check);
    }


    private void check(PokerTable pokerTable) {

        validatePokerTable(pokerTable);

        Map<String, Object> modelObject = new HashMap<>();
        modelObject.put(POKER_TABLE, pokerTable);
        modelObject.put(RESULT, false);
        modelObject.put(REASON, null);
        modelObject.put(WINNER, null);
        modelObject.put(TIE, false);

        for (PokerHandRankType type : PokerHandRankType.values()) {

            PokerHandRankingRule pokerHandRankingRule = PokerHandRankingRuleFactory.getPokerHandRankingRule(type);

            modelObject.put("playerOne", pokerTable.getPokerPlayers().get(0));
            modelObject.put("playerTwo", pokerTable.getPokerPlayers().get(1));
            pokerHandRankingRule.executeRule(modelObject);

            //for future enhancement if we add more players per poker table
            if (pokerTable.getPokerPlayers().size() > 2) {
                for (int i = 2; i < pokerTable.getPokerPlayers().size(); i++) {
                    if ((boolean) modelObject.get(RESULT)) {
                        PokerPlayer winner = (PokerPlayer) modelObject.get(WINNER);
                        if (null != winner) {
                            modelObject.put("playerOne", winner);
                        }
                    }
                    modelObject.put("playerTwo", pokerTable.getPokerPlayers().get(i));
                    pokerHandRankingRule.executeRule(modelObject);
                }
            }
            pokerHandRankingRule.executeRule(modelObject);
            if ((boolean) modelObject.get(TIE)) {
                System.out.println(TIE);
            }
            if ((boolean) modelObject.get(RESULT) && !(boolean) modelObject.get(TIE)) {
                System.out.println(modelObject.get(WINNER) + " wins. - with " + modelObject.get(REASON));
                break;
            }
        }
    }

}
