package com.mygame.poker.model;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private CardCategory cardCategory;
    private CardNumber number;

    public Card() {
    }

    public Card(CardCategory cardCategory, CardNumber number) {
        this.cardCategory = cardCategory;
        this.number = number;
    }

    public CardCategory getCardCategory() {
        return cardCategory;
    }

    public void setCardCategory(CardCategory cardCategory) {
        this.cardCategory = cardCategory;
    }

    public CardNumber getNumber() {
        return number;
    }

    public void setNumber(CardNumber number) {
        this.number = number;
    }

    @Override
    public int compareTo(Card card) {
        return card.getNumber().getWeight() - this.getNumber().getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardCategory == card.cardCategory &&
                number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardCategory, number);
    }

    @Override
    public String toString() {
        return "{" + cardCategory.getName() + ", " + number.getName() + '}';
    }
}
