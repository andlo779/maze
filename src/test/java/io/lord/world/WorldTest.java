package io.lord.world;

import io.lord.world.exceptions.DoorLockedException;
import io.lord.world.exceptions.WorldException;
import io.lord.world.items.Key;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void testGoBetweenTwoRooms() throws WorldException {
        //GIVEN
        Door door = new Door();
        Room currentRoom = new Room("First room", Collections.emptyList(), door, null, null, null);
        Room nextRoom = new Room("Second room", Collections.emptyList(), null, door, null, null);

        //WHEN
        final Room result = currentRoom.navigateToOtherRoom(Direction.NORTH);

        //THEN
        assertFalse(result.equals(currentRoom));
        assertTrue(result.equals(nextRoom));
    }

    @Test
    public void testGoBetweenTwoRoomsWithLockedDoor() {
        //GIVEN
        Key key = new Key("key");
        Door door = new Door(true, key);
        Room currentRoom = new Room("First room", Collections.emptyList(), door, null, null, null);
        Room nextRoom = new Room("Second room", Collections.emptyList(), null, door, null, null);

        //WHEN + THEN
        assertThrows(DoorLockedException.class, () -> currentRoom.navigateToOtherRoom(Direction.NORTH));
    }

    @Test
    public void testGoBetweenTwoRoomsWithLockedDoorThenUnlockDoor() throws WorldException {
        //GIVEN
        Key key = new Key("key");
        Door door = new Door(true, key);
        Room currentRoom = new Room("First room", Collections.emptyList(), door, null, null, null);
        Room nextRoom = new Room("Second room", Collections.emptyList(), null, door, null, null);

        //WHEN
        currentRoom.unlockDoor(key, Direction.NORTH);
        final Room result = currentRoom.navigateToOtherRoom(Direction.NORTH);

        //THEN
        assertFalse(result.equals(currentRoom));
        assertTrue(result.equals(nextRoom));
    }

}