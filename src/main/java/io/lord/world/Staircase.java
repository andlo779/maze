package io.lord.world;

import io.lord.world.exceptions.WorldException;

public class Staircase implements RoomConnector{

    @Override
    public Room goToNextRoom(Room currentRoom) throws WorldException {
        return null;
    }

    @Override
    public void addRoom(Room room) throws WorldException {

    }
}
