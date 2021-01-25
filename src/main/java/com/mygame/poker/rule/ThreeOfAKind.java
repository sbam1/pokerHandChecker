package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.PokerHand;
import com.mygame.poker.PokerPlayer;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ThreeOfAKind implements PokerHandRankingRule {


    /**
     * Find Three of a kind exist of not
     *      if only one player has Three of a kind - player with Three of a kind wins
     *      if both has Three of a kind - higher Three of a kind wins
     * No Full House in both players return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerHand pokerHand = (PokerHand) modelObject.get("POKER_HAND");
        PokerPlayer player1 = pokerHand.getPlayer1();
        PokerPlayer player2 = pokerHand.getPlayer2();

        Optional<Card> cardOne = hasThreeOfAKind(player1);
        Optional<Card> cardTwo = hasThreeOfAKind(player2);

        modelObject.put("RESULT", true);
        if(cardOne.isPresent() && cardTwo.isPresent()) {

            //higher four of kind wins
            int weight = cardOne.get().getNumber().getWeight() - cardTwo.get().getNumber().getWeight();

            if(weight > 0) {
                modelObject.put("WINNER", player1);
                modelObject.put("REASON", "Three Of A Kind: " + cardOne.get().getNumber().getName());
            }
            else {
                modelObject.put("WINNER", player2);
                modelObject.put("REASON", "Three Of A Kind: " + cardTwo.get().getNumber().getName());
            }

        }
        else if(cardOne.isPresent()) {
            modelObject.put("WINNER", player1);
            modelObject.put("REASON", "Three Of A Kind: " + cardOne.get().getNumber().getName());
        }
        else if(cardTwo.isPresent()) {
            modelObject.put("WINNER", player2);
            modelObject.put("REASON", "Three Of A Kind: " + cardTwo.get().getNumber().getName());
        }
        else {
            //rule not applicable.
            modelObject.put("RESULT", false);
        }
        return modelObject;
    }

    private Optional<Card> hasThreeOfAKind(PokerPlayer player) {

        return player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()))
                .values().stream().filter(cards -> cards.size() == 3)
                .map(cards -> cards.get(0)).findAny();
    }
}
