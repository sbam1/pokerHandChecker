package com.mygame.poker.rule;

import com.mygame.poker.PokerPlayer;
import com.mygame.poker.PokerTable;
import com.mygame.poker.util.RankingRuleUtil;

import java.util.Collections;
import java.util.Map;

import static com.mygame.poker.util.Constants.POKER_TABLE;
import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;
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

        PokerTable pokerTable = (PokerTable) modelObject.get(POKER_TABLE);
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        //sort cards in descending order:
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        modelObject.put(RESULT, true);

        boolean flushOne = RankingRuleUtil.isFlush(player1);
        boolean flushTwo = RankingRuleUtil.isFlush(player2);
        if(flushOne && flushTwo){
            // check for higher flush
            compareTwoPlayersCards(modelObject, player1, player2);
        }
        else if(flushOne) {
            //player one wins.
            setStatus(modelObject, player1);
        }
        else if(flushTwo) {
            //player two wins
           setStatus(modelObject, player2);
        } else {
            modelObject.put(RESULT, false);
        }
        return modelObject;
    }


    private void compareTwoPlayersCards(Map<String, Object> modelObject, PokerPlayer player1, PokerPlayer player2) {
        for(int i = 0; i < player1.getCards().size(); i++) {
            int result = compareTwoCards(player1.getCards().get(i), player2.getCards().get(i));
            if(result > 0) {
                setStatus(modelObject, player1);
                break;
            } else if(result < 0) {
                setStatus(modelObject, player2);
                break;
            }
        }

        if(null == modelObject.get(WINNER)) {
            modelObject.put(WINNER, null);
            modelObject.put(TIE, true);
        }
    }

    private void setStatus(Map<String, Object> modelObject, PokerPlayer player) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Flush: " + player.getCards().toString());
    }

}
