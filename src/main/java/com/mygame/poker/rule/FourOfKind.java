package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FourOfKind implements PokerHandRankingRule {

    /**
     * Find Four Of A Kind exist of not
     *      if only one player has Four Of A Kind - player with Four Of A Kind wins
     *      if both has Four Of A Kind - higher Four Of A Kind wins
     * No Four Of A Kind in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {
        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        Optional<Card> cardOne = hasFourOfKind(player1);
        Optional<Card> cardTwo = hasFourOfKind(player2);

        if(cardOne.isPresent() && cardTwo.isPresent()) {
            modelObject.put("RESULT", true);

            //higher four of kind wins
            int weight = cardOne.get().getNumber().getWeight() - cardTwo.get().getNumber().getWeight();

            if(weight > 0) {
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "Four Of A Kind: " + cardOne.get().getNumber().getName());
            }
            else {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Four Of A Kind: " + cardTwo.get().getNumber().getName());
            }

        }
        else if(cardOne.isPresent()) {
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Four Of A Kind: " + cardOne.get().getNumber().getName());
        }
        else if(cardTwo.isPresent()) {
            modelObject.put("RESULT", true);
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Four Of A Kind:" + cardTwo.get().getNumber().getName());
        }
        else {
            //rule not applicable.
            modelObject.put("RESULT", false);
        }
        return modelObject;
    }

    private Optional<Card> hasFourOfKind(PokerPlayer player) {

       return player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()))
                .values().stream().filter(cards -> cards.size() == 4)
                .map(cards -> cards.get(0)).findAny();
    }
}
