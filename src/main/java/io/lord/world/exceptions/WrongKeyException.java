package io.lord.world.exceptions;

public class WrongKeyException extends WorldException{

    public WrongKeyException() {
        super("The key you used does not fir this door.");
    }
}
