package com.mygame.poker.rule;

import com.mygame.poker.PokerTable;
import com.mygame.poker.PokerPlayer;
import com.mygame.poker.util.RankingRuleUtil;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mygame.poker.util.RankingRuleUtil.compareTwoCards;

public class Straight implements PokerHandRankingRule {


    /**
     * Find Straight exist of not
     *      if only one player Straight - player with Straight wins
     *      if both has Straight - higher Straight wins
     *      if both has Straight with same weight - its TIE
     * No Four Of A Kind in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerTable pokerTable = (PokerTable) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        //sort cards in descending order:
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        modelObject.put("RESULT", true);

        boolean straightOne = RankingRuleUtil.isStraight(player1);
        boolean straightTwo = RankingRuleUtil.isStraight(player2);
        if(straightOne && straightTwo) {
            //compare straights
            compareTwoPlayersCards(modelObject, player1, player2);
        }
        else if(straightOne) {
            //player one wins
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Straight: " + player1.getCards().toString());
        }
        else if(straightTwo) {
            //player two wins
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "straight: " + player2.getCards().toString());
        } else {
            modelObject.put("RESULT", false);
        }

        return modelObject;
    }

    private void compareTwoPlayersCards(Map<String, Object> modelObject, PokerPlayer player1, PokerPlayer player2) {
        AtomicInteger index = new AtomicInteger();

        player1.getCards().forEach(it -> {
            int result = compareTwoCards(it, player2.getCards().get(index.get()));
            if(result > 0) {
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "Straight " + player1.getCards().toString());
                return;
            } else if(result < 0) {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Straight: " + player2.getCards().toString());
                return;
            }
            index.incrementAndGet();
        });

        if(null == modelObject.get("WINNER")) {
            modelObject.put("WINNER", null);
            modelObject.put("TIE", true);
        }
    }
}
