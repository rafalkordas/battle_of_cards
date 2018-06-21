package com.codecool.battleofcards.player;

import com.codecool.battleofcards.card.Pile;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, Pile cards) {
        super(name, cards);
    }
    
    @Override
    public int getChoice() {
        Scanner reader = new Scanner(System.in);
        int userChoice = Integer.parseInt(reader.nextLine());

        if (userChoice < 1 || userChoice > 4) {
            throw new IllegalArgumentException("It's not a valid number");
        }

        return userChoice;
    }
}