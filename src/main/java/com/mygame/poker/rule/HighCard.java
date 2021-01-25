package com.mygame.poker.rule;

import com.mygame.poker.PokerTable;
import com.mygame.poker.PokerPlayer;

import java.util.Collections;
import java.util.Map;

import static com.mygame.poker.util.RankingRuleUtil.compareTwoCards;

public class HighCard implements PokerHandRankingRule {

    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {
        PokerTable pokerTable = (PokerTable) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        modelObject.put("RESULT", true);

        for(int i= 0; i< player1.getCards().size(); i ++) {
            int result = compareTwoCards(player1.getCards().get(i), player2.getCards().get(i));
            if(result > 0) {
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "High Card: " + player1.getCards().toString());
                break;
            } else if(result < 0) {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "High Card: " + player2.getCards().toString());
                break;
            }
        }

        if(null ==  modelObject.get("WINNER")) {
            modelObject.put("WINNER", null);
            modelObject.put("TIE", true);
        }

        return modelObject;
    }
}
