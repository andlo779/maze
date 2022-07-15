package io.lord.world;

import io.lord.world.exceptions.NoDoorException;
import io.lord.world.exceptions.WorldException;
import io.lord.world.exceptions.WrongKeyException;
import io.lord.world.items.Item;
import io.lord.world.items.Key;

import java.util.List;
import java.util.UUID;

public class Room {

    private final String ID;
    private final String description;
    private boolean isRoomDescribed = false;
    private final ItemContainer items;

    private Door north;
    private Door south;
    private Door east;
    private Door west;

    public Room(String description, List<Item> items, Door north, Door south, Door east, Door west) throws WorldException{
        this.description = description;
        this.items = new ItemContainer(items);
        if(north != null) {
            this.north = north;
            north.addRoom(this);
        }
        if(south != null) {
            this.south = south;
            south.addRoom(this);
        }
        if(east != null) {
            this.east = east;
            east.addRoom(this);
        }
        if(west != null) {
            this.west = west;
            west.addRoom(this);
        }

        this.ID = UUID.randomUUID().toString();
    }

    public Room navigateToOtherRoom(Direction direction) throws WorldException {
        Room nextRoom = null;
        switch (direction) {
            case NORTH:
                nextRoom = navigateToOtherRoom(north);
                break;
            case SOUTH:
                nextRoom = navigateToOtherRoom(south);
                break;
            case EAST:
                nextRoom = navigateToOtherRoom(east);
                break;
            case WEST:
                nextRoom = navigateToOtherRoom(west);
                break;
            default:
                //ToDo: error handling with exceptions.....
        }
        return nextRoom;
    }

    private Room navigateToOtherRoom(Door door) throws WorldException{
        if(door != null) {
            return door.goToNextRoom(this);
        }
        throw new NoDoorException("No door to go through");
    }

    public void unlockDoor(Key key, Direction direction) throws WrongKeyException {
        assert direction != null;
        assert key != null;
        switch (direction) {
            case NORTH:
                this.north.unlockDoor(key);
                break;
            case SOUTH:
                this.south.unlockDoor(key);
                break;
            case EAST:
                this.east.unlockDoor(key);
                break;
            case WEST:
                this.west.unlockDoor(key);
                break;
            default:
                //ToDo add error handling....
        }
    }


    public boolean equals(Room otherRoom) {
        return this.ID.equals(otherRoom.ID);
    }

    public String describeRoom() {
        StringBuilder sb = new StringBuilder();
        sb.append(description);
        if(!items.isEmpty()) {
            sb.append("\nThere are several items in here: ").append(items.describe());
        }
        return sb.toString();
    }

    public String describeRoomWhenEntering() {
        if(!isRoomDescribed) {
            this.isRoomDescribed = true;
            return describeRoom();
        }
        return null;
    }
}
