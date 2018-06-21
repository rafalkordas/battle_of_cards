package com.codecool.battleofcards.player.computer.hardai;

import java.util.Arrays;
import java.util.Collections;

public class CardStrenghts{

    private int firstAttribute, secondAttribute, thirdAttribute, fourthAttribute;

    public int getBestAttributeNumber(){

        int bestAttributeValue = Collections.max(
                                      Arrays.asList(firstAttribute,
                                                    secondAttribute,
                                                    thirdAttribute,
                                                    fourthAttribute));

        if(bestAttributeValue == firstAttribute)
            return 1;
            else if(bestAttributeValue == secondAttribute)
                return 2;
                else if(bestAttributeValue == thirdAttribute)
                    return 3;
                    else
                        return 4;
    }

    public void setAttribute(int number, int attribute){

        switch (number){

            case 1:
                this.firstAttribute = attribute;
                break;

            case 2:
                this.secondAttribute = attribute;
                break;

            case 3:
                this.thirdAttribute = attribute;
                break;

            case 4:
                this.fourthAttribute = attribute;
                break;
        }
    }
    public String toString(){
        return (                "1. " + firstAttribute +
                                "\n2. " + secondAttribute +
                                "\n3. " + thirdAttribute +
                                "\n4. " + fourthAttribute  );}
}
