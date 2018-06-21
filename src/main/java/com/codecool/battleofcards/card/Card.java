package com.codecool.battleofcards.card;

public class Card {
    private final String NAME;
    private final CardAttribute FIRST_ATTRIBUTE;
    private final CardAttribute SECOND_ATTRIBUTE;
    private final CardAttribute THIRD_ATTRIBUTE;
    private final CardAttribute FOURTH_ATTRIBUTE;

    public Card(String name, CardAttribute... cardAttributes) {
        this.NAME = name;
        this.FIRST_ATTRIBUTE = cardAttributes[0];
        this.SECOND_ATTRIBUTE = cardAttributes[1];
        this.THIRD_ATTRIBUTE = cardAttributes[2];
        this.FOURTH_ATTRIBUTE = cardAttributes[3];
    }

    public String getName() {
        return this.NAME;
    }

    public double getAttributeValue(int attributeNumber) {
        return chooseAttribute(attributeNumber).getValue();
    }

    public String getAttributeLabel(int attributeNumber) {
        return chooseAttribute(attributeNumber).getLabel();
    }

    private CardAttribute chooseAttribute(int attributeNumber) {
        CardAttribute choosedAttribute;
        switch (attributeNumber) {
            case 1:
                choosedAttribute = this.FIRST_ATTRIBUTE;
                break;
            case 2:
                choosedAttribute = this.SECOND_ATTRIBUTE;
                break;
            case 3:
                choosedAttribute = this.THIRD_ATTRIBUTE;
                break;
            case 4:
                choosedAttribute = this.FOURTH_ATTRIBUTE;
                break;
            default:
                choosedAttribute = null;
        }

        return choosedAttribute;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s: %.2f\n%s: %.2f\n%s: %.2f\n%s: %.2f", this.NAME,
                                                                           this.FIRST_ATTRIBUTE.getAnnotatedLabel(),
                                                                           this.FIRST_ATTRIBUTE.getValue(),
                                                                           this.SECOND_ATTRIBUTE.getAnnotatedLabel(),
                                                                           this.SECOND_ATTRIBUTE.getValue(),
                                                                           this.THIRD_ATTRIBUTE.getAnnotatedLabel(),
                                                                           this.THIRD_ATTRIBUTE.getValue(),
                                                                           this.FOURTH_ATTRIBUTE.getAnnotatedLabel(),
                                                                           this.FOURTH_ATTRIBUTE.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
         if (!this.NAME.equals(other.NAME))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((NAME == null) ? 0 : NAME.hashCode());
        return result;
    }


}
