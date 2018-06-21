package com.codecool.battleofcards.card;

import com.codecool.battleofcards.reader.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Shuffleable {
    private List<Card> cards;
    FileReader fileReader = new FileReader();

    public Deck(String filename){
        this.cards = new ArrayList<>();
        createCards(filename);
    }

    private void createCards(String filename){
        String[] statisticsList = fileReader.readerFromFile(filename).split("\n");
        
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

                this.cards.add(new Card(name,
                                        firstAttribute,
                                        secondAttribute,
                                        thirdAttribute,
                                        fourthAttribute));
        }
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    public List<Pile> deal(int numOfPlayers) {
        List<Pile> piles = new ArrayList<>();

        int playerPileSize = this.cards.size() / numOfPlayers;
        int nextPileStartPos = 0;
        int nextPileEndPos = playerPileSize;
        
        for (int i = 0; i < numOfPlayers; i++) {
            piles.add(new Pile(this.cards.subList(nextPileStartPos, nextPileEndPos)));
            nextPileStartPos += playerPileSize;
            nextPileEndPos += playerPileSize;
        }

        return piles;
    }
}