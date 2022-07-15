package io.lord.world;


import io.lord.world.exceptions.DoorLockedException;
import io.lord.world.exceptions.WorldException;
import io.lord.world.exceptions.WrongKeyException;
import io.lord.world.items.Key;

public class Door implements RoomConnector{

    private Room first;
    private Room second;
    private boolean isLocked;
    private Key key;

    public Door() {
        this.isLocked = false;
    }

    public Door(boolean isLocked, Key key) {
        this.isLocked = isLocked;
        this.key = key;
    }

    @Override
    public Room goToNextRoom(Room currentRoom) throws WorldException {
        if(this.isLocked) {
            throw new DoorLockedException();
        }
        if(first.equals(currentRoom)) {
            return second;
        } else {                                         
            return first;
        }
    }

    @Override
    public void addRoom(Room room) throws WorldException{
        if(first == null) {
            this.first = room;
        } else if(second == null) {
            this.second = room;
        } else {
            throw new WorldException("ERROR: This door already have two rooms!!!");
        }
    }

    public void unlockDoor(Key key) throws WrongKeyException {
        if(this.key.equals(key)){
            isLocked = false;
        } else {
            throw new WrongKeyException();
        }
    }
}
