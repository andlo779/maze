package io.lord.world;


import io.lord.world.exceptions.ItemNotFoundException;
import io.lord.world.exceptions.NoCapacityInItemContainerException;
import io.lord.world.items.Item;

import java.util.List;

public class Player {

    private final String name;
    private final ItemContainer backpack;

    public Player(String name) {
        this.name = name;
        this.backpack = new ItemContainer();
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your name is ").append(name).append(".").append(backpack.describe());
        return sb.toString();
    }

    public void addItem(Item item) throws NoCapacityInItemContainerException {
        backpack.addItem(item);
    }

    public Item getItem(List<String> itemKeyWords) throws ItemNotFoundException {
        return backpack.getItem(itemKeyWords);
    }

    public String getName() {
        return name;
    }
}
