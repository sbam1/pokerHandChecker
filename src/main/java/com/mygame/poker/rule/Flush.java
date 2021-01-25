package com.mygame.poker.rule;

import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;
import com.mygame.poker.util.RankingRuleUtil;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mygame.poker.util.RankingRuleUtil.compareTwoCards;


public class Flush implements PokerHandRankingRule {

    /**
     * Find Flush exist of not
     *      if only one player Flush - player with Flush wins
     *      if both has Flush - higher Flush wins
     *      if both has Flush with same weight - its TIE
     * No Four Of A Kind in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        //sort cards in descending order:
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        modelObject.put("RESULT", true);

        boolean flushOne = RankingRuleUtil.isFlush(player1);
        boolean flushTwo = RankingRuleUtil.isFlush(player2);
        if(flushOne && flushTwo){
            // check for higher flush
            compareTwoPlayersCards(modelObject, player1, player2);
        }
        else if(flushOne) {
            //player one wins.
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Flush: " + player1.getCards().toString());
        }
        else if(flushTwo) {
            //player two wins
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Flush: " + player2.getCards().toString());
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
                modelObject.put("REASON", "Flush " + player1.getCards().toString());
                return;
            } else if(result < 0) {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Flush: " + player2.getCards().toString());
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
