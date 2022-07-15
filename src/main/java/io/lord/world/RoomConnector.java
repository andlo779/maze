package io.lord.world;

import io.lord.world.exceptions.WorldException;

public interface RoomConnector {

    Room goToNextRoom(Room currentRoom) throws WorldException;
    void addRoom(Room room) throws WorldException;
}
