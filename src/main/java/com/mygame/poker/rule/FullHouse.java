package com.mygame.poker.rule;

import com.mygame.poker.model.Card;
import com.mygame.poker.model.CardNumber;
import com.mygame.poker.model.PokerPlayer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.WINNER;
import static java.util.stream.Collectors.toList;

public class FullHouse implements PokerHandRankingRule {

    /**
     * Find Full House exist of not
     * if only one player has FullHouse - player with Full House wins
     * if both has Full House - higher Full House wins
     * No Full House in both players return without result
     */

    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerPlayer player1 = (PokerPlayer) modelObject.get("playerOne");
        PokerPlayer player2 = (PokerPlayer) modelObject.get("playerTwo");

        FullHouseResult fullHouseResult1 = hasFullHouse(player1);
        FullHouseResult fullHouseResult2 = hasFullHouse(player2);

        modelObject.put(RESULT, true);
        if (fullHouseResult1.fullHouse && fullHouseResult2.fullHouse) {

            //higher full house wins.
            int weight = fullHouseResult1.threeOfAKindCard.getNumber().getWeight() - fullHouseResult2.threeOfAKindCard.getNumber().getWeight();

            if (weight > 0) {
                //player one wins
                setStatus(modelObject, player1, fullHouseResult1);
            } else {
                //player two wins.
                setStatus(modelObject, player2, fullHouseResult2);
            }
        } else if (fullHouseResult1.fullHouse) {
            //playerOne wins.
            setStatus(modelObject, player1, fullHouseResult1);
        } else if (fullHouseResult2.fullHouse) {
            //playerTwo wins.
            setStatus(modelObject, player2, fullHouseResult2);
        } else {
            //rule not applicable
            modelObject.put(RESULT, false);
        }
        return modelObject;
    }

    private void setStatus(Map<String, Object> modelObject, PokerPlayer player, FullHouseResult fullHouseResult) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Full House: " + fullHouseResult.threeOfAKindCard.getNumber().getName()
                + " Over " + fullHouseResult.pairCard.getNumber().getName());
    }


    private FullHouseResult hasFullHouse(PokerPlayer player) {
        FullHouseResult fullHouseResult = new FullHouseResult();

        Map<CardNumber, List<Card>> listMap = player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()));

        if (listMap.size() == 2) {
            listMap.forEach((key, value) -> {
                if (value.size() == 3) {
                    fullHouseResult.threeOfAKindCard = value.get(0);
                }
                if (value.size() == 2) {
                    fullHouseResult.pairCard = value.get(0);
                }
            });
        }

        if (fullHouseResult.pairCard != null && fullHouseResult.threeOfAKindCard != null) {
            fullHouseResult.fullHouse = true;
        }


        return fullHouseResult;

    }

    private static class FullHouseResult {
        private Card threeOfAKindCard;
        private Card pairCard;
        private boolean fullHouse;

    }
}
