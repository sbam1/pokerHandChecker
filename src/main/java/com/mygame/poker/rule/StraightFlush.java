package com.mygame.poker.rule;

import com.mygame.poker.model.PokerPlayer;

import java.util.Collections;
import java.util.Map;

import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;
import static com.mygame.poker.util.RankingRuleUtil.isFlush;
import static com.mygame.poker.util.RankingRuleUtil.isStraight;

public class StraightFlush implements PokerHandRankingRule {

    /**
     * Find straight flush exist or not
     * if only one player has straight flush - player with straight flush win
     * if both has straight flush - higher straight flush win
     * if both has same straight flush - its TIE
     * No pair in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerPlayer player1 = (PokerPlayer) modelObject.get("playerOne");
        PokerPlayer player2 = (PokerPlayer) modelObject.get("playerTwo");

        //sort cards in descending order:
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        boolean playerOneSFStatus = hasStraightFlush(player1);
        boolean playerTwoSFStatus = hasStraightFlush(player2);

        modelObject.put(RESULT, true);

        if (playerOneSFStatus && playerTwoSFStatus) {

            int value = player1.getCards().get(0).getNumber().getWeight() - player2.getCards().get(0).getNumber().getWeight();
            if (value > 0) {
                setStatus(modelObject, player1);

            } else if (value < 0) {
                setStatus(modelObject, player2);
            } else {
                modelObject.put(WINNER, null);
                modelObject.put(TIE, true);
            }
        } else if (playerOneSFStatus) {
            setStatus(modelObject, player1);

        } else if (playerTwoSFStatus) {
            setStatus(modelObject, player2);
        } else {
            modelObject.put(RESULT, false);
        }

        return modelObject;
    }


    private boolean hasStraightFlush(PokerPlayer player) {
        if (isStraight(player) && isFlush(player)) {
            return true;
        }
        return false;
    }


    private void setStatus(Map<String, Object> modelObject, PokerPlayer player) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Straight Flush: " + player.getCards().toString());
    }

}
