package com.codecool.battleofcards.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.codecool.battleofcards.card.Card;
import com.codecool.battleofcards.player.*;
import com.codecool.battleofcards.reader.*;

public class GameView {
    FileReader fileReader = new FileReader();

    public void displayLine(String lineContent) {
        System.out.println(lineContent);
    }

    public void displayBlock(StringBuilder block) {
        displayLine(block.toString());
    }

    public void displayEmptyLine() {
        System.out.println();
    }

    public void displayEmptyLines(int numberOfEmptyLines) {
        for (int i = 0; i < numberOfEmptyLines; i++) {
            displayEmptyLine();
        }
    }

    public void displayInputPrompt(String inputPrompt) {
        displayInline(inputPrompt + ": ");
    }

    public void displayInline(String text) {
        System.out.print(text);
    }

    public void clearScreen() {
        displayLine("\033[H\033[2J");
    }

    public void displayCard(Card card) {
        StringBuilder cardToPrint = new StringBuilder();
        String[] cardSplitted = card.toString().split("\n");

        for (int i = 0; i < 5; i++) {
            if (i != 0) {
                cardToPrint.append(i);
            } else {
                cardToPrint.append(" ");
            }
            cardToPrint.append("|");
            cardToPrint.append(centeredString(cardSplitted[i]));
            cardToPrint.append("|");
            cardToPrint.append("\n");
        }

        displayBlock(cardToPrint);
    }

    public void displayTable(List<Card> cards) {
        List<List<String>> allCardsSplitted = new ArrayList<>();
        for (Card card : cards) {
            List<String> cardSplitted = Arrays.asList(card.toString().split("\n"));
            allCardsSplitted.add(cardSplitted);
        }

        StringBuilder table = new StringBuilder();
        for (int i = 0; i <= 4; i++) {
            if (i != 0) {
                table.append(i);
            } else {
                table.append(" ");
            }

            table.append("|");
            for (List<String> card : allCardsSplitted) {
                table.append(centeredString(card.get(i)));
                table.append("|");
            }
            table.append("\n");
        }

        displayBlock(table);
    }

    public void displayHeader(List<Player> players) {
        StringBuilder names = new StringBuilder();
        names.append("  ");
        for(Player player : players) {
            names.append(centeredString(player.getName()+ " - Cards left: " + player.getNumOfCards()) + " ");          
        }
        displayBlock(names);
        displayEmptyLine();
    }

    public void displayPlayerDecision(Player player, String attributeLabel) {
        String playerDecision = String.format("%s choosed %s", player.getName(),
                                                               attributeLabel.toLowerCase());
        displayLine(playerDecision);
    }

    public String centeredString(String text) {
        int widthColumn = 35;
        int padSize = widthColumn - text.length();
        int padStart = text.length() + padSize / 2;
        text = String.format("%" + padStart + "s", text);
        text = String.format("%-" + widthColumn  + "s", text);

        return text;
    }

    public void waitForAction() {
        displayInputPrompt("Press enter to continue");
        Scanner reader = new Scanner(System.in);
        reader.nextLine();
    }

    public void displayFirstScreen() {
        clearScreen();
        displayLine(fileReader.readerFromFile("start_screen.txt"));
        waitForAction();
    }

    public void displayEndGame(Player player) {
        displayLine("GAME ENDED");
        displayLine(String.format("The winner is %s with %d cards", player.getName(),
                                                                    player.getNumOfCards()));
    }
}