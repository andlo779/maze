package io.lord;

import io.lord.world.Room;
import lombok.Getter;

@Getter
public class CommandResult {

    private Room currentRoom;
    private String message;
    private boolean isError = false;
    private boolean exitGame = false;


    public CommandResult(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public CommandResult(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public CommandResult(boolean exitGame) {
        this.exitGame = exitGame;
    }

    public boolean isExitGame() {
        return exitGame;
    }
}
