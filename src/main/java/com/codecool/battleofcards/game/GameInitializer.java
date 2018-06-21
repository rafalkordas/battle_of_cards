package com.codecool.battleofcards.game;

import com.codecool.battleofcards.card.*;
import com.codecool.battleofcards.display.GameView;
import com.codecool.battleofcards.player.*;
import com.codecool.battleofcards.player.computer.*;
import com.codecool.battleofcards.player.computer.hardai.HardAI;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class GameInitializer {
    private final String FILE_NAME;
    private Deck deck;
    private List<Player> players;
    private List<Pile> piles;
    private int playersNumber;

    private GameView view = new GameView();
    private Scanner input;


    public GameInitializer(String fileName) {
        FILE_NAME = fileName;
        this.input = new Scanner(System.in);
    }

    public Game initializeGame() {
        this.view.displayFirstScreen();
        this.view.clearScreen();
        this.playersNumber = this.askNumberOfPlayers();
        this.createDeck();
        this.shuffleCards();
        this.dealCards();
        this.createPlayers();

        return new Game(this.players);
    }

    private void createDeck() {
        this.deck = new Deck(FILE_NAME);
    }

    private String askGameMode() {
        view.displayInputPrompt("Choose game mode [PvP/PvC]");
        return input.nextLine().toLowerCase();
    }

    private int askNumberOfPlayers() {
        while (true) {
            try {
                view.displayInputPrompt("Select players number [2-4]");
                return getChoice();
            } catch (IllegalArgumentException e) {
                view.displayLine("It's not a valid number");
            }
        }
    }

    public int getChoice() {
        Scanner reader = new Scanner(System.in);
        int userChoice = Integer.parseInt(reader.nextLine());

        if (userChoice < 2 || userChoice > 4) {
            throw new IllegalArgumentException("It's not a valid number");
        }
        return userChoice;
    }

    private String askForName() {
        view.displayInputPrompt("Enter your name");
        return input.nextLine();
    }

    private String askLevel() {
        view.displayInputPrompt("Choose level normal or hard [n/h]");
        return input.nextLine().toLowerCase();
    }

    private void createHumanPlayer(int playersNumber) {
        for(int i = 0; i < playersNumber; i++) {
            Player player = new HumanPlayer(askForName(), piles.get(i));
            players.add(player);
        }
    }

    private void createHumanPlayer() {
        createHumanPlayer(this.playersNumber);
    }

    private void createComputerPlayer(int i) {
        Player computerPlayer;
        String level = this.askLevel();
        switch(level) {
            case "n":
                computerPlayer = new NormalAI(piles.get(i));
                players.add(computerPlayer);
                break;
            case "h":
                computerPlayer = new HardAI(piles.get(i));
                players.add(computerPlayer);
        }
    }

    private void createPlayers() {
        players = new ArrayList<>();

        boolean isNotValid = true;

        while (isNotValid) {
            try {
                String mode = askGameMode();
                switch(mode) {
                    case "pvp":
                        isNotValid = false;
                        createHumanPlayer();
                        break;
                    case "pvc":
                        isNotValid = false;
                        int humanNumber = playersNumber - 1;
                        createHumanPlayer(humanNumber);
                        int pileNumber = humanNumber;
                        createComputerPlayer(pileNumber);
                        break;
                    default:
                        throw new IllegalArgumentException("There's no such option");
                }
            } catch (IllegalArgumentException e) {
                view.displayLine(e.getMessage());
            }
        }
    }

    private void shuffleCards() {
        this.deck.shuffle();
    }

    private void dealCards() {
        this.piles = this.deck.deal(playersNumber);
    }
}
