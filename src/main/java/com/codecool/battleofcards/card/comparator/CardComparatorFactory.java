package com.codecool.battleofcards.card.comparator;

import com.codecool.battleofcards.card.Card;

import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class CardComparatorFactory {
    private final Map<Integer, Comparator<Card>> comparators;

    public CardComparatorFactory() {
        this.comparators = new HashMap<>();
    }

    public Comparator<Card> getCardComparator(int attributeNumber) {
        if (!this.comparators.containsKey(attributeNumber)) {
            this.comparators.put(attributeNumber, new CardComparator(attributeNumber));
        }
        return this.comparators.get(attributeNumber);
    }
}
