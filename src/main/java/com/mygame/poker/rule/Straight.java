package com.mygame.poker.rule;

import com.mygame.poker.model.PokerPlayer;
import com.mygame.poker.util.RankingRuleUtil;

import java.util.Collections;
import java.util.Map;

import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;
import static com.mygame.poker.util.RankingRuleUtil.compareTwoCards;

public class Straight implements PokerHandRankingRule {


    /**
     * Find Straight exist of not
     * if only one player Straight - player with Straight wins
     * if both has Straight - higher Straight wins
     * if both has Straight with same weight - its TIE
     * No Four Of A Kind in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerPlayer player1 = (PokerPlayer) modelObject.get("playerOne");
        PokerPlayer player2 = (PokerPlayer) modelObject.get("playerTwo");

        //sort cards in descending order:
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        modelObject.put(RESULT, true);

        boolean straightOne = RankingRuleUtil.isStraight(player1);
        boolean straightTwo = RankingRuleUtil.isStraight(player2);
        if (straightOne && straightTwo) {
            //compare straights
            compareTwoPlayersCards(modelObject, player1, player2);
        } else if (straightOne) {
            //player one wins
            setStatus(modelObject, player1);
        } else if (straightTwo) {
            //player two wins
            setStatus(modelObject, player2);
        } else {
            modelObject.put(RESULT, false);
        }

        return modelObject;
    }

    private void compareTwoPlayersCards(Map<String, Object> modelObject, PokerPlayer player1, PokerPlayer player2) {

        for (int i = 0; i < player1.getCards().size(); i++) {
            int result = compareTwoCards(player1.getCards().get(i), player2.getCards().get(i));
            if (result > 0) {
                setStatus(modelObject, player1);
                break;
            } else if (result < 0) {
                setStatus(modelObject, player2);
                break;
            }
        }

        if (null == modelObject.get(WINNER)) {
            modelObject.put(WINNER, null);
            modelObject.put(TIE, true);
        }
    }

    private void setStatus(Map<String, Object> modelObject, PokerPlayer player) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Straight: " + player.getCards().toString());
    }
}
