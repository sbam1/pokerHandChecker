package com.mygame.poker.rule;

import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;

import java.util.Collections;
import java.util.Map;

import static com.mygame.poker.util.RankingRuleUtil.isFlush;
import static com.mygame.poker.util.RankingRuleUtil.isStraight;

public class StraightFlush implements PokerHandRankingRule {

    /**
     * Find straight flush exist or not
     *      if only one player has straight flush - player with straight flush win
     *      if both has straight flush - higher straight flush win
     *      if both has same straight flush - its TIE
     * No pair in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        //sort cards in descending order:
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        boolean playerOneSFStatus = hasStraightFlush(player1);
        boolean playerTwoSFStatus = hasStraightFlush(player2);

        if(playerOneSFStatus && playerTwoSFStatus) {
            modelObject.put("RESULT", true);
            int value = player1.getCards().get(0).getNumber().getWeight() - player2.getCards().get(0).getNumber().getWeight();
            if(value > 0) {
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "Straight Flush: " +player1.getCards().toString());

            } else if(value < 0) {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Straight Flush: " + player2.getCards().toString());
            } else {
                modelObject.put("WINNER", null);
                modelObject.put("TIE", true);
            }
        } else if(playerOneSFStatus) {
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Straight Flush from: " + player1.getCards().toString());

        } else if(playerTwoSFStatus) {
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Straight Flush: " + player2.getCards().toString());
        }  else {
            modelObject.put("RESULT", false);
        }

        return modelObject;
    }



    private boolean hasStraightFlush(PokerPlayer player) {
        if(isStraight(player) && isFlush(player)) {
            return true;
        }
        return false;
    }

}
