package com.codecool.battleofcards.card.comparator;

import com.codecool.battleofcards.card.Card;
import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    private final int attributeNumber;

    public CardComparator(int attributeNumber) {
        this.attributeNumber = attributeNumber;
    }

    @Override
    public int compare(Card card, Card otherCard) {
        if (card.getAttributeValue(this.attributeNumber) > otherCard.getAttributeValue(this.attributeNumber)) {
            return 1;
        } else if (card.getAttributeValue(this.attributeNumber) < otherCard.getAttributeValue(this.attributeNumber)) {
            return -1;
        } else {
            return 0;
        }
    }
}
