package com.codecool.battleofcards.player.computer.hardai;

import com.codecool.battleofcards.card.Card;
import com.codecool.battleofcards.card.CardAttribute;
import com.codecool.battleofcards.reader.FileReader;
import com.codecool.battleofcards.card.comparator.CardComparatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.lang.ClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import java.util.Collections;


public class KnowledgeBase{

    private static CardComparatorFactory comparatorFactory;
    private static Map<Card, CardStrenghts> cardsStrenghts;
    private int deckSize =34;
    private static final int CARD_STATS_NUM = 4;
    private static final String FILESOURCE = "statistics.csv";

    public KnowledgeBase(){
        //due to specification restrictions about static methods
        if(cardsStrenghts == null){
            initializeBase(); // ... we want to be sure we will not run this more than once
        }
    }

    public int getBestAttributeOfCard(Card card){
        return cardsStrenghts.get(card).getBestAttributeNumber();
    }

    private void initializeBase(){
        comparatorFactory = new CardComparatorFactory();
        cardsStrenghts = new HashMap<Card, CardStrenghts>();
        List<Card> allCards = createCards();

        for(Card card : allCards)
            cardsStrenghts.put(card, new CardStrenghts());

        deckSize = allCards.size();
        initializeCardsStrenghts(allCards);

        for (int i = 1; i <= CARD_STATS_NUM; i++)
            generateAttributeStrenghts(i, allCards);

    }


    private void initializeCardsStrenghts(List<Card> cards){

        for(Card card : cards)
            cardsStrenghts.put(card, new CardStrenghts());
    //    deckSize = cards.size();

    }

    private void generateAttributeStrenghts(int attributeNumber, List<Card> cards){

        Collections.sort(cards, comparatorFactory.getCardComparator(attributeNumber));

        for(int i = 0; i < cards.size(); i++){
            cardsStrenghts.get(cards.get(i)).setAttribute(attributeNumber, (i));
        }
    }

    private double calculatePercentage(int chunk, int size){
        return ((size - (chunk+1)) * 100) / size;
    }


    private List<Card> createCards(){
        FileReader fileReader = new FileReader();
        String[] statisticsList = fileReader.readerFromFile(FILESOURCE).split("\n");
        List<Card> cards = new ArrayList<>();
        for (String line : statisticsList){
            String[] cardStatistic = line.split(",");

                String name = cardStatistic[0];

                double firstValue = Double.valueOf(cardStatistic[1]);
                CardAttribute firstAttribute = new CardAttribute("Population density",
                                                                 "Density (people/km^2)",
                                                                 firstValue);

                double secondValue = Double.valueOf(cardStatistic[2]);
                CardAttribute secondAttribute = new CardAttribute("Population size",
                                                                  "Population (mln)",
                                                                  secondValue);

                double thirdValue = Double.valueOf(cardStatistic[3]);
                CardAttribute thirdAttribute = new CardAttribute("Gross Domestic Product",
                                                                 "GDP (bln €)",
                                                                 thirdValue);

                double fourthValue = Double.valueOf(cardStatistic[4]);
                CardAttribute fourthAttribute = new CardAttribute("Country's area",
                                                                  "Area (km^2)",
                                                                  fourthValue);

                cards.add(new Card( name,
                                    firstAttribute,
                                    secondAttribute,
                                    thirdAttribute,
                                    fourthAttribute));
                }
        return cards;
    }
}
