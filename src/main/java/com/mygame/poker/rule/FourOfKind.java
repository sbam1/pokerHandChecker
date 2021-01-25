package com.mygame.poker.rule;

import com.mygame.poker.model.Card;
import com.mygame.poker.model.PokerPlayer;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.WINNER;
import static java.util.stream.Collectors.toList;

public class FourOfKind implements PokerHandRankingRule {

    /**
     * Find Four Of A Kind exist of not
     * if only one player has Four Of A Kind - player with Four Of A Kind wins
     * if both has Four Of A Kind - higher Four Of A Kind wins
     * No Four Of A Kind in both player return without result
     */
    @Override
    public Map<String, Object> executeRule(Map<String, Object> modelObject) {

        PokerPlayer player1 = (PokerPlayer) modelObject.get("playerOne");
        PokerPlayer player2 = (PokerPlayer) modelObject.get("playerTwo");

        Optional<Card> cardOne = hasFourOfKind(player1);
        Optional<Card> cardTwo = hasFourOfKind(player2);

        modelObject.put(RESULT, true);

        if (cardOne.isPresent() && cardTwo.isPresent()) {
            //higher four of kind wins
            int weight = cardOne.get().getNumber().getWeight() - cardTwo.get().getNumber().getWeight();

            if (weight > 0) {
                setStatus(modelObject, player1, cardOne.get());
            } else {
                setStatus(modelObject, player2, cardTwo.get());
            }

        } else if (cardOne.isPresent()) {
            setStatus(modelObject, player1, cardOne.get());
        } else if (cardTwo.isPresent()) {
            setStatus(modelObject, player2, cardTwo.get());
        } else {
            //rule not applicable.
            modelObject.put(RESULT, false);
        }
        return modelObject;
    }

    private void setStatus(Map<String, Object> modelObject, PokerPlayer player, Card lastCard) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Four Of A Kind: " + lastCard.getNumber().getName());
    }

    private Optional<Card> hasFourOfKind(PokerPlayer player) {

        return player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()))
                .values().stream().filter(cards -> cards.size() == 4)
                .map(cards -> cards.get(0)).findAny();
    }
}
