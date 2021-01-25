package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.CardNumber;
import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        PairResult playerOneResult = getPairResult(player1);
        PairResult playerTwoResult = getPairResult(player2);

        if(playerOneResult.hasPair && playerTwoResult.hasPair){
            bothPlayerGotPair(modelObject, playerOneResult, playerTwoResult);

        }
        else if (playerOneResult.hasPair){
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Pair of " + playerOneResult.getPairCard().getNumber().getName());

        }else if(playerTwoResult.hasPair){
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Pair of " + playerTwoResult.getPairCard().getNumber().getName());
        }
        else {
            //rule not applicable
            modelObject.put("RESULT", false);
        }

        return modelObject;
    }

    private void bothPlayerGotPair(Map<String, Object> modelObject, PairResult playerOneResult, PairResult playerTwoResult) {
        modelObject.put("RESULT", true);

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        if(playerOneResult.pairCard.getNumber().getWeight() > playerTwoResult.getPairCard().getNumber().getWeight()) {
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Pair of " + playerOneResult.getPairCard().getNumber().getName());

        } else  if(playerTwoResult.pairCard.getNumber().getWeight() > playerOneResult.getPairCard().getNumber().getWeight()) {
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Pair of " + playerTwoResult.getPairCard().getNumber().getName());
        } else {
            // player with higher card wins
            Collections.sort(playerOneResult.otherCards);
            Collections.sort(playerTwoResult.otherCards);

            AtomicInteger index = new AtomicInteger();

            playerOneResult.otherCards.forEach(it -> {
                int result = compareTwoCards(it, playerTwoResult.otherCards.get(index.get()));
                if(result > 0) {
                    modelObject.put("WINNER", player1);
                    modelObject.put("REASON", "Pair of " + it.getNumber().getName()
                            + "And With High Card: " + playerTwoResult.otherCards.get(index.get()).getNumber().getName());
                    return;
                } else if(result < 0) {
                    modelObject.put("WINNER", player2);
                    modelObject.put("REASON", "Pair of " + playerTwoResult.getPairCard().getNumber().getName()
                    + "And With High Card: " + playerTwoResult.otherCards.get(index.get()).getNumber().getName());
                    return;
                }
                index.incrementAndGet();
            });

            if(null ==  modelObject.get("WINNER")) {
                modelObject.put("WINNER", null);
                modelObject.put("TIE", true);
            }
        }
    }

    private PairResult getPairResult(PokerPlayer player) {
        Map<CardNumber, List<Card>> listMap = player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()));

        boolean hasPair = listMap.entrySet().stream().anyMatch(it -> it.getValue().size() == 2);
        PairResult pairResult = new PairResult();
        pairResult.setHasPair(hasPair);

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

        public Card getPairCard() {
            return pairCard;
        }

        public void setPairCard(Card pairCard) {
            this.pairCard = pairCard;
        }

        public List<Card> getOtherCards() {
            return otherCards;
        }

        public void setOtherCards(List<Card> otherCards) {
            this.otherCards = otherCards;
        }

        public boolean isHasPair() {
            return hasPair;
        }

        public void setHasPair(boolean hasPair) {
            this.hasPair = hasPair;
        }
    }
}
