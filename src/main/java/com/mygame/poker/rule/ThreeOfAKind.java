package com.mygame.poker.rule;

import com.mygame.poker.Card;
import com.mygame.poker.PokerPlayer;
import com.mygame.poker.PokerTable;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mygame.poker.util.Constants.POKER_TABLE;
import static com.mygame.poker.util.Constants.REASON;
import static com.mygame.poker.util.Constants.RESULT;
import static com.mygame.poker.util.Constants.WINNER;
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

        PokerTable pokerTable = (PokerTable) modelObject.get(POKER_TABLE);
        PokerPlayer player1 = pokerTable.getPlayer1();
        PokerPlayer player2 = pokerTable.getPlayer2();

        Optional<Card> cardOne = hasThreeOfAKind(player1);
        Optional<Card> cardTwo = hasThreeOfAKind(player2);

        modelObject.put(RESULT, true);
        if(cardOne.isPresent() && cardTwo.isPresent()) {

            //higher four of kind wins
            int weight = cardOne.get().getNumber().getWeight() - cardTwo.get().getNumber().getWeight();

            if(weight > 0) {
                setStatus(modelObject, player1, cardOne.get());
            }
            else {
                setStatus(modelObject, player2, cardTwo.get());
            }

        }
        else if(cardOne.isPresent()) {
            setStatus(modelObject, player1, cardOne.get());
        }
        else if(cardTwo.isPresent()) {
            setStatus(modelObject, player2, cardTwo.get());
        }
        else {
            //rule not applicable.
            modelObject.put(RESULT, false);
        }
        return modelObject;
    }

    private Optional<Card> hasThreeOfAKind(PokerPlayer player) {

        return player.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, toList()))
                .values().stream().filter(cards -> cards.size() == 3)
                .map(cards -> cards.get(0)).findAny();
    }

    private void setStatus(Map<String, Object> modelObject, PokerPlayer player, Card card) {
        modelObject.put(WINNER, player);
        modelObject.put(REASON, "Three Of A Kind: " + card.getNumber().getName());
    }
}
