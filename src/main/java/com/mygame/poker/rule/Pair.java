package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerTable;
import com.mygame.poker.PokerPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.mygame.poker.util.Constants.POKER_TABLE;
import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.TIE;
import static com.mygame.poker.util.Constants.WINNER;
import static com.mygame.poker.util.RankingRuleUtil.compareTwoCards;
import static java.util.stream.Collectors.toList;

public class Pair implements PokerHandRankingRule {

    /**
     * Find pair exist or not
     *      if only one player has pair - player with pair win
     *      if both has pair - higher pair win
     *      if both has same pair - player with higher card win
     *      if both has same pair as well as other cards with same weight then its tie
     * No pair in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerTable pokerTable = (PokerTable) modelObject.get(POKER_TABLE);
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        PairResult playerOneResult = getPairResult(player1);
        PairResult playerTwoResult = getPairResult(player2);

        if(playerOneResult.hasPair && playerTwoResult.hasPair){
            bothPlayerGotPair(modelObject, playerOneResult, playerTwoResult);

        }
        else if (playerOneResult.hasPair){
            setStatus(modelObject, player1, playerOneResult.pairCard, null);

        }else if(playerTwoResult.hasPair){
            setStatus(modelObject, player2, playerTwoResult.pairCard, null);
        }
        else {
            //rule not applicable
            modelObject.put(RESULT, false);
        }

        return modelObject;
    }

    private void bothPlayerGotPair(Map<String, Object> modelObject, PairResult playerOneResult, PairResult playerTwoResult) {
        modelObject.put(RESULT, true);

        PokerTable pokerTable = (PokerTable) modelObject.get(POKER_TABLE);
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        int higherPair = compareTwoCards(playerOneResult.pairCard, playerTwoResult.pairCard);
        if(higherPair > 0) {
            setStatus(modelObject, player1, playerOneResult.pairCard, null);

        } else  if(higherPair < 0) {
            setStatus(modelObject, player2, playerTwoResult.pairCard, null);
        } else {
            //both player has same pair.
            // player with higher card wins
            Collections.sort(playerOneResult.otherCards);
            Collections.sort(playerTwoResult.otherCards);

            AtomicInteger index = new AtomicInteger();

            for(int i = 0; i < playerOneResult.otherCards.size(); i++) {
                int result = compareTwoCards(playerOneResult.otherCards.get(i), playerTwoResult.otherCards.get(i));
                if(result > 0) {
                    setStatus(modelObject, player1, playerOneResult.pairCard, playerOneResult.otherCards.get(i));
                    return;
                } else if(result < 0) {
                    setStatus(modelObject, player2, playerTwoResult.pairCard, playerTwoResult.otherCards.get(i));
                    return;
                }
                index.incrementAndGet();
            }

            if(null ==  modelObject.get(WINNER)) {
                modelObject.put(WINNER, null);
                modelObject.put(TIE, true);
            }
        }
    }

    private PairResult getPairResult(PokerPlayer player) {
        Map<CardNumber, List<Card>> listMap = player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()));

        boolean hasPair = listMap.entrySet().stream().anyMatch(it -> it.getValue().size() == 2);
        PairResult pairResult = new PairResult();
        pairResult.hasPair = hasPair;

        if(hasPair) {
            listMap.forEach((key, value) -> {
                if(value.size() == 2) {
                    pairResult.pairCard = value.get(0);
                }else {
                    pairResult.otherCards.add(value.get(0));
                }
            });
        }
        return pairResult;
    }

    static class PairResult {
        private Card pairCard;
        private List<Card> otherCards = new ArrayList<>();
        boolean hasPair;
    }

    private void setStatus(Map<String, Object> modelObject, PokerPlayer player, Card pairCard, Card highCard) {
        StringBuilder reason = new StringBuilder("Pair of " +pairCard.getNumber().getName());
        if(null != highCard) {
            reason.append(" And With High Card: ").append(highCard.getNumber().getName());
        }

        modelObject.put(WINNER, player);
        modelObject.put(REASON, reason.toString());
    }
}
