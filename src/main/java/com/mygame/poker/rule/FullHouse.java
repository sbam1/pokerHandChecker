package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FullHouse implements PokerHandRankingRule {

    /**
     * Find Full House exist of not
     *      if only one player has FullHouse - player with Full House wins
     *      if both has Full House - higher Full House wins
     * No Full House in both players return without result
     */

    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {
        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        FullHouseResult fullHouseResult1 = hasFullHouse(player1);
        FullHouseResult fullHouseResult2 = hasFullHouse(player2);

        if(fullHouseResult1.fullHouse && fullHouseResult2.fullHouse) {
            modelObject.put("RESULT", true);

            //higher full house wins.
            int weight = fullHouseResult1.threeOfAKindCard.getNumber().getWeight() -
                    fullHouseResult2.threeOfAKindCard.getNumber().getWeight();

            if(weight > 0) {
                //player one wins
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "Full House: " + fullHouseResult1.threeOfAKindCard.getNumber().getName()
                + " Over " + fullHouseResult1.pairCard.getNumber().getName());
            } else {
                //player two wins.
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Full House: " + fullHouseResult2.threeOfAKindCard.getNumber().getName()
                        + " Over " + fullHouseResult2.pairCard.getNumber().getName());
            }
        }
        else if(fullHouseResult1.fullHouse) {
            //playerOne wins.
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Full House: " + fullHouseResult1.threeOfAKindCard.getNumber().getName()
                    + " Over " + fullHouseResult1.pairCard.getNumber().getName());
        }
        else if(fullHouseResult2.fullHouse) {
            //playerTwo wins.
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Full House: " + fullHouseResult2.threeOfAKindCard.getNumber().getName()
                    + " Over " + fullHouseResult2.pairCard.getNumber().getName());
        }
        else {
            //rule not applicable
            modelObject.put("RESULT", false);
        }
        return modelObject;
    }


    private FullHouseResult hasFullHouse(PokerPlayer player) {
        FullHouseResult fullHouseResult = new FullHouseResult();

        Map<CardNumber, List<Card>> listMap = player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()));

        if(listMap.size() == 2) {
            listMap.forEach((key, value) -> {
                if (value.size() == 3) {
                    fullHouseResult.threeOfAKindCard = value.get(0);
                }
                if (value.size() == 2) {
                    fullHouseResult.pairCard = value.get(0);
                }
            });
        }

        if(fullHouseResult.pairCard != null && fullHouseResult.threeOfAKindCard != null) {
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
