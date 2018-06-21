package com.codecool.battleofcards.game;

import com.codecool.battleofcards.card.Card;
import com.codecool.battleofcards.card.comparator.CardComparatorFactory;
import com.codecool.battleofcards.player.*;
import com.codecool.battleofcards.display.GameView;

import java.util.*;

public class Game {
    private final GameView gameView;

    private final List<Player> players;
    private Player activePlayer;

    private final CardComparatorFactory cardComparators;
    private final Table table;

    private boolean isActive;

    public Game(List<Player> players) {
        this.gameView = new GameView();
        this.players = players;
        this.cardComparators = new CardComparatorFactory();
        this.table = new Table();
        this.isActive = true;
    
    }

    public void play() {
        this.gameView.clearScreen();
        this.activePlayer = this.players.get(0);
        while (this.isActive) {
            gameView.clearScreen();
            handleRound();
            this.gameView.waitForAction();
        }
    }

    private void handleRound() {
        // Active player peeks his top card
        this.gameView.displayLine("Current player: " + this.activePlayer.getName());
        this.gameView.displayEmptyLine();
        Card activePlayerTopCard = this.activePlayer.getCards().peekTopCard();
        showCard(activePlayerTopCard);
        this.gameView.displayEmptyLine();

        // Active player chooses comparison attribute
        int playerChoice = getChoiceFromActivePlayer();
        table.setCardComparator(cardComparators.getCardComparator(playerChoice));
        this.gameView.clearScreen();

        // Players top cards are gathered
        List<Card> roundCards = new ArrayList<>();
        for (Player player : this.players) {
            roundCards.add(player.getCards().peekTopCard());
        }

        // Player decision is displayed
        this.gameView.displayPlayerDecision(this.activePlayer, activePlayerTopCard.getAttributeLabel(playerChoice));

        // Round is being resolved
        table.resolveRound(roundCards);
        if (table.isRoundResolved()) {
            Player winner = findWinningCardOwner();
            takePlayersTopCards();
            winner.addCards(table.getRoundTrophy());
            this.gameView.displayLine(String.format("Winning card is %s, so the winner is %s", table.getWinningCard().getName(),
                                                                                               winner.getName()));
            this.gameView.displayEmptyLine();
            this.activePlayer = winner;
        }

        // Players top cards are showed
        this.gameView.displayHeader(this.players);
        showCards(roundCards);

        checkIfWon();
        if (!isActive) {
            this.gameView.displayEndGame(this.getWinner());
        }
    }

    private int getChoiceFromActivePlayer() {
        while (true) {
            try {
                this.gameView.displayInputPrompt("Select comparison attribute");
                return this.activePlayer.getChoice();
            } catch (IllegalArgumentException e) {
                this.gameView.displayLine("It's not a valid number");
            }
        }
    }

    private void takePlayersTopCards() {
        for (Player player : this.players) {
            player.getCards().getTopCard();
        }
    }

    private void showCard(Card card) {
         this.gameView.displayCard(card);
    }

    private void showCards(List<Card> cards) {
        this.gameView.displayTable(cards);
    }

    private void checkIfWon() {
        for (Player player : players) {
            if (player.getNumOfCards() == 0) {
                this.isActive = false;
            }
        }
    }

    private Player findWinningCardOwner() {
        for (Player player : this.players) {
            if (player.hasCard(table.getWinningCard())) {
                return player;
            }
        }
        return null;
    }

    private Player getWinner() {
        Comparator<Player> numOfCardComparator = Comparator.comparing(Player::getNumOfCards);
        return Collections.max(this.players, numOfCardComparator);
    }
}