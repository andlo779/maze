package io.lord.world.exceptions;

public class NoCapacityInItemContainerException extends WorldException{

    public NoCapacityInItemContainerException() {
        super("The backpack is full.");
    }
}
