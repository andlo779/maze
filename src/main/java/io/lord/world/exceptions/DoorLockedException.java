package io.lord.world.exceptions;

public class DoorLockedException extends WorldException{

    public DoorLockedException() {
        super("This door is locked, try to unlock it with a key.");
    }

    public String getMessage() {
        return super.getMessage();
    }
}
