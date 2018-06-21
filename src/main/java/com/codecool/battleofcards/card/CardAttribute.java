package com.codecool.battleofcards.card;

public class CardAttribute {
    private final String LABEL;
    private final String ANNOTATED_LABEL;
    private final double VALUE;

    public CardAttribute(String label, String annotatedLabel, double value) {
        this.LABEL = label;
        this.ANNOTATED_LABEL = annotatedLabel;
        this.VALUE = value;
    }

    public String getLabel() {
        return this.LABEL;
    }

    public String getAnnotatedLabel() {
        return this.ANNOTATED_LABEL;
    }

    public double getValue() {
        return this.VALUE;
    }
}