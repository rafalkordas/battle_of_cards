package com.codecool.battleofcards;

import com.codecool.battleofcards.game.Game;
import com.codecool.battleofcards.game.GameInitializer;

public class Main {
    public static void main(String[] args) {
        String cardsFile = args[0];
        GameInitializer gameInit = new GameInitializer(cardsFile);

        Game game = gameInit.initializeGame();

        game.play();
    }
}
