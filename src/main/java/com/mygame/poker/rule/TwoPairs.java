package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TwoPairs implements PokerHandRankingRule {
    /**
     * Find two pairs exist or not
     *      if only one player has two paisr - player with two pairs win
     *      if both has two pairs - higher pair win
     *      if both has same two pairs - player with higher card win
     *      if both has same two pairs as well as other cards with same weight then its tie
     * No pair in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        TwoPairResult playerOneResult = getTwoPairResult(player1);
        TwoPairResult playerTwoResult = getTwoPairResult(player2);

        if(playerOneResult.twoPairs && playerTwoResult.twoPairs){
            bothPlayerGotTwoPairs(modelObject, playerOneResult, playerTwoResult);

        }
        else if (playerOneResult.twoPairs){
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Two Pair of:  " + playerOneResult.higherPairCard.getNumber().getName() +
                    " And " + playerOneResult.lowerPairCard.getNumber().getName());

        }else if(playerTwoResult.twoPairs){
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Two Pair of:  " + playerTwoResult.higherPairCard.getNumber().getName() +
                    " And " + playerTwoResult.lowerPairCard.getNumber().getName());

        }
        else {
            //rule not applicable
            modelObject.put("RESULT", false);
        }

        return modelObject;
    }

    private void bothPlayerGotTwoPairs(Map<String, Object> modelObject, TwoPairResult playerOneResult, TwoPairResult playerTwoResult) {
        modelObject.put("RESULT", true);

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        if(playerOneResult.higherPairCard.getNumber().getWeight() > playerTwoResult.higherPairCard.getNumber().getWeight()) {
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Two Pair of:  " + playerOneResult.higherPairCard.getNumber().getName() +
                    " And " + playerOneResult.lowerPairCard.getNumber().getName());

        } else  if(playerTwoResult.higherPairCard.getNumber().getWeight() > playerOneResult.higherPairCard.getNumber().getWeight()) {
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Two Pair of:  " + playerTwoResult.higherPairCard.getNumber().getName() +
                    " And " + playerTwoResult.lowerPairCard.getNumber().getName());
        } else  if(playerOneResult.lowerPairCard.getNumber().getWeight() > playerTwoResult.lowerPairCard.getNumber().getWeight()) {
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Two Pair of:  " + playerOneResult.higherPairCard.getNumber().getName() +
                    " And " + playerOneResult.lowerPairCard.getNumber().getName());

        } else  if(playerTwoResult.lowerPairCard.getNumber().getWeight() > playerOneResult.lowerPairCard.getNumber().getWeight()) {
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Two Pair of:  " + playerTwoResult.higherPairCard.getNumber().getName() +
                    " And " + playerTwoResult.lowerPairCard.getNumber().getName());
        } else {
            int weight = playerOneResult.otherCard.getNumber().getWeight() -
                    playerTwoResult.otherCard.getNumber().getWeight();
            if(weight > 0) {
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "Two Pair of:  " + playerOneResult.higherPairCard.getNumber().getName() +
                        " And " + playerOneResult.lowerPairCard.getNumber().getName());

            } else if(weight < 0) {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Two Pair of:  " + playerTwoResult.higherPairCard.getNumber().getName() +
                        " And " + playerTwoResult.lowerPairCard.getNumber().getName());
            }

            if(null ==  modelObject.get("WINNER")) {
                modelObject.put("WINNER", null);
                modelObject.put("TIE", true);
            }
        }
    }

    private TwoPairResult getTwoPairResult(PokerPlayer player) {
        LinkedHashMap<CardNumber, List<Card>> listMap = player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, LinkedHashMap::new, toList()));

        long count = listMap.entrySet().stream().filter(it -> it.getValue().size() == 2).count();

        TwoPairResult twoPairResult = new TwoPairResult();
        if(count == 2) {
            twoPairResult.twoPairs = true;
            listMap.forEach((key, value) -> {
                if(value.size() == 2) {
                    if(null == twoPairResult.higherPairCard) {
                        twoPairResult.higherPairCard = value.get(0);
                    } else {
                        twoPairResult.lowerPairCard = value.get(0);
                    }
                }else {
                    twoPairResult.otherCard = value.get(0);
                }
            });
        }
        return twoPairResult;
    }

    static class TwoPairResult {
        private Card higherPairCard;
        private Card lowerPairCard;
        private Card otherCard;
        boolean twoPairs;
    }
}
