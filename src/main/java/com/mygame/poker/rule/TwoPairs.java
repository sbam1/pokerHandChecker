package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerPlayer;
import com.mygame.poker.PokerTable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mygame.poker.util.Constants.POKER_TABLE;
import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;
import static com.mygame.poker.util.RankingRuleUtil.compareTwoCards;
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

        PokerTable pokerTable = (PokerTable) modelObject.get(POKER_TABLE);
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());

        TwoPairResult playerOneResult = getTwoPairResult(player1);
        TwoPairResult playerTwoResult = getTwoPairResult(player2);

        modelObject.put(RESULT, true);

        if(playerOneResult.twoPairs && playerTwoResult.twoPairs){
            bothPlayerGotTwoPairs(modelObject, playerOneResult, playerTwoResult);

        }
        else if (playerOneResult.twoPairs){
            setStatus(modelObject, playerOneResult, player1);

        }else if(playerTwoResult.twoPairs){
            setStatus(modelObject, playerTwoResult, player2);

        }
        else {
            //rule not applicable
            modelObject.put(RESULT, false);
        }

        return modelObject;
    }

    private void bothPlayerGotTwoPairs(Map<String, Object> modelObject, TwoPairResult playerOneResult, TwoPairResult playerTwoResult) {
        PokerTable pokerTable = (PokerTable) modelObject.get(POKER_TABLE);
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        int higherCardWeightDiff = compareTwoCards(playerOneResult.higherPairCard, playerTwoResult.higherPairCard);
        int lowerCardWeightDiff = compareTwoCards(playerOneResult.lowerPairCard, playerTwoResult.lowerPairCard);

        if(higherCardWeightDiff > 0) {
            setStatus(modelObject, playerOneResult, player1);
        }

        else  if(higherCardWeightDiff < 0) {
            setStatus(modelObject, playerTwoResult, player2);
        }

        else  if(lowerCardWeightDiff > 0) {
            setStatus(modelObject, playerOneResult, player1);
        }

        else  if(lowerCardWeightDiff < 0) {
            setStatus(modelObject, playerTwoResult, player2);
        }
        else {
            //both got same pair now need to check last card.
            int weight = playerOneResult.otherCard.getNumber().getWeight() -
                    playerTwoResult.otherCard.getNumber().getWeight();
            if(weight > 0) {
                setStatus(modelObject, playerOneResult, player1);

            } else if(weight < 0) {
                setStatus(modelObject, playerTwoResult, player2);
            }

            if(null ==  modelObject.get(WINNER)) {
                modelObject.put(WINNER, null);
                modelObject.put(TIE, true);
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

    private void setStatus(Map<String, Object> modelObject, TwoPairResult result, PokerPlayer player) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Two Pair of:  " + result.higherPairCard.getNumber().getName() +
                " And " + result.lowerPairCard.getNumber().getName());
    }

    static class TwoPairResult {
        private Card higherPairCard;
        private Card lowerPairCard;
        private Card otherCard;
        boolean twoPairs;
    }
}
