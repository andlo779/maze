package io.lord;

import io.lord.world.Door;
import io.lord.world.Player;
import io.lord.world.Room;
import io.lord.world.exceptions.WorldException;
import io.lord.world.items.Item;

import java.util.List;
import java.util.Scanner;

//ToDo: Refactoring all print out to one class(place...).
//ToDo: Create way to say game and load saved game.....
//When does the game end? Is there an exit to the maze? Add introduction story to player (or maybe the player have to find out the story he/she wakes up in the maze with no memory of how she got there..... Player should be gender neutral)
//ToDo: Add some intro and exit ASCII graphics

public class Game {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private Player player;
    private Room currentRoom;
    private boolean currentRoomUpdated;

    public Game() {
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }

    public void run() {
        assert player != null;
        assert currentRoom != null;

        outputHandler.printMessage(StringMessages.WHAT_NEXT_MESSAGE);

        boolean shouldRun = true;
        while(shouldRun) {
            if(currentRoomUpdated) {
                //ToDo: This logic need to change, always prints null....
                outputHandler.printMessage(currentRoom.describeRoomWhenEntering());
            }
            final String input = getInput();
            try {
                final CommandResult result = inputHandler.handle(new Command(input), currentRoom, player);
                shouldRun = processCommandResult(result);
            } catch (WorldException e) {
                outputHandler.printMessage(e.getMessage());
            }
        }
        outputHandler.printMessage(StringMessages.GOODBYE_MESSAGE);
    }

    private String getInput() {
        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }

    private boolean processCommandResult(CommandResult result) {
        if(result.getMessage() != null) {
            outputHandler.printMessage(result.getMessage());
        }
        if(result.getCurrentRoom() != null){
            setCurrentRoom(result.getCurrentRoom());
        }
        return !result.isExitGame();
    }

    public void setUpGame() {
        outputHandler.printMessage(StringMessages.WELCOME_MESSAGE);
        final String input = getInput();
        this.player = new Player(input);


        try {
            //ToDo: Move to world creator/generator....(Maybe based on difficulty level chosen by player = size of maze + number of items?) Also handle errors when generating new world....
            Door door = new Door();
            setCurrentRoom(new Room("A dark room. No light.", List.of(new Item("Knife", 1), new Item("Lamp", 1)), door, null, null, null));
            new Room("You made it to the second room.", null, null, door, null, null);
        } catch (WorldException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentRoom(Room room) {
        this.currentRoom = room;
        this.currentRoomUpdated = true;
    }

}
