package com.codecool.battleofcards.player.computer.hardai;

import com.codecool.battleofcards.card.Pile;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.codecool.battleofcards.player.computer.ComputerPlayer;

public class HardAI extends ComputerPlayer {
    private static List<String> hardAINames;
    private static Random generator;
    private static final int CARD_STATS_NUM = 4;
    private static KnowledgeBase knowledgeBase;

    static {
        hardAINames = new ArrayList<>(Arrays.asList("Hardly Harry",
                                                    "BrAIny",
                                                    "HAR'ol D"));
        generator = new Random();
        knowledgeBase = new KnowledgeBase();
    }

    public HardAI(Pile cards) {
        super(hardAINames.remove(generator.nextInt(hardAINames.size())), cards);
    }

    @Override
    public int getChoice() {
        return knowledgeBase.getBestAttributeOfCard(getCards().peekTopCard());
    }
}
