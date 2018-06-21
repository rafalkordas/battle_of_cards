package com.codecool.battleofcards.player.computer;

import com.codecool.battleofcards.player.Player;
import com.codecool.battleofcards.card.Pile;

public abstract class ComputerPlayer extends Player {
    public ComputerPlayer(String name, Pile cards) {
        super(name, cards);
    }

    public abstract int getChoice();
}
