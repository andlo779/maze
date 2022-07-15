package io.lord;

import io.lord.world.Direction;
import io.lord.world.Player;
import io.lord.world.Room;
import io.lord.world.exceptions.NoDoorException;
import io.lord.world.exceptions.WorldException;

public class InputHandler {

    private static final String HELP_MENU = "You will have to write commands in the terminal to control your player. There are lots of things you can do, like navigate around,\ninteract with things. Try to use common words to control your player.";
    private static final String MOVEMENT_ERROR = "You are trying to move somewhere I don't understand. Please try to use common words of direction.";

    public CommandResult handle(Command command, Room currentRoom, Player player) throws WorldException {
        if(command.hasNext()) {
            return handleFirstCommand(command, currentRoom, player);
        }
        return new CommandResult(getUnknownCommandError(command.getOriginalCommand()), true);
    }

    private CommandResult handleFirstCommand(Command command, Room currentRoom, Player player) throws WorldException{
        switch (command.getNext()) {
            case "walk":
            case "move":
            case "go":
                return handleMovementCommand(command, currentRoom);
            case "describe":
                return handleDescribeCommand(command, currentRoom, player);
            case "use":
                return handleUseCommand(command, player);
            case "help":
                return new CommandResult(HELP_MENU, false);
            case "quit":
            case "exit":
                return new CommandResult(true);
            default:
                return new CommandResult(getUnknownCommandError(command.getOriginalCommand()), true);
        }
    }

    private CommandResult handleUseCommand(Command command, Player player) {
        //ToDo: better concept on how to handle items: use, pick up and drop items.
        switch (command.getNext()) {
        }

        return new CommandResult(getUnknownCommandError(command.getOriginalCommand()), true);
    }

    private CommandResult handleMovementCommand(Command command, Room currentRoom) throws WorldException {
        String direction = command.getNext();
        try {
            switch(direction) {
                case "front":
                case "north":
                    return new CommandResult(currentRoom.navigateToOtherRoom(Direction.NORTH));
                case "back":
                case "south":
                    return new CommandResult(currentRoom.navigateToOtherRoom(Direction.SOUTH));
                case "right":
                case "east":
                    return new CommandResult(currentRoom.navigateToOtherRoom(Direction.EAST));
                case "left":
                case "west":
                    return new CommandResult(currentRoom.navigateToOtherRoom(Direction.WEST));
                default :
                    return new CommandResult(MOVEMENT_ERROR, true);
            }
        } catch (NoDoorException ex) {
            throw new NoDoorException("There is no door to go open at " + direction.toLowerCase() + "in this room.");
        }
    }


    private CommandResult handleDescribeCommand(Command command, Room currentRoom, Player player) {
        switch (command.getNext()) {
            case "room":
                ;
                return new CommandResult(currentRoom.describeRoom(), false);
            case "me":
            case "player":
                return new CommandResult(player.describe(), false);
            default:
                return new CommandResult(getUnknownCommandError(command.getOriginalCommand()), true);
        }
    }

    private String getUnknownCommandError(String command) {
        return "\"" + command + "\" is and unknown command, please try something else. If you don't know what to do you can always ask for help.";
    }
}
