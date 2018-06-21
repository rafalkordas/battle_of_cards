package com.codecool.battleofcards.card;

import java.lang.Comparable;

import java.util.LinkedList;
import java.util.List;

public class Pile implements Comparable<Pile> {
    private LinkedList<Card> cards;
    
    public Pile(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public int getCardsNumber() {
        return this.cards.size();
    }

    public void addCards(List<Card> newCards) {
       cards.addAll(newCards);
    }

    public Card peekTopCard() {
        return cards.peek();
    }

    public Card getTopCard() {
        return cards.poll();
    }

    public boolean containsCard(Card card) {
        return this.cards.contains(card);
    }

    @Override
    public int compareTo(Pile pile) {
        if(this.getCardsNumber() == pile.getCardsNumber()) {
            return 0;
        } else if(this.getCardsNumber() < pile.getCardsNumber()) {
            return -1;
        }
        return 1;
    }
}
