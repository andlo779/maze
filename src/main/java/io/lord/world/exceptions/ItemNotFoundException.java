package io.lord.world.exceptions;

public class ItemNotFoundException extends WorldException{

    public ItemNotFoundException(String description) {
        super("No item found with following description: " + description);
    }
}
