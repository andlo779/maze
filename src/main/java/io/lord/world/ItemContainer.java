package io.lord.world;

import io.lord.world.exceptions.ItemNotFoundException;
import io.lord.world.exceptions.NoCapacityInItemContainerException;
import io.lord.world.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ToDo: refactor to more generic item container to support crates in rooms + add logic to room to handle containers. (Maybe abstract so we can have better error handeling in the different containers. Type of containers: Backpack, Crate, ???)

public class ItemContainer {

    private final Map<String, Item> items;
    private final int capacity;
    private int occupiedCapacity;

    public ItemContainer() {
        this.capacity = 20;
        this.occupiedCapacity = 0;
        this.items = new HashMap<>();
    }

    public ItemContainer(List<Item> items) {
        this();
        this.addItems(items);
    }

    public void addItem(Item item) throws NoCapacityInItemContainerException {
        if(hasSpace(item)) {
            throw new NoCapacityInItemContainerException();
        }
        this.occupiedCapacity = occupiedCapacity + item.getSize();
        this.items.put(item.getId(), item);
    }

    public void addItems(List<Item> items) {
        if(items != null) {
            items.forEach(item -> {
                try {
                    addItem(item);
                } catch (NoCapacityInItemContainerException e) {
                    //ToDo: Not cool, should probably forward the exception
                    //ToDo: Or should only add the items if they fit, calculate the total weight before trying to add the items and verify that it will fit....
                }
            });
        }
    }

    public Item getItem(List<String> itemKeyWords) throws ItemNotFoundException {
        List<String> itemIds = new ArrayList<>();
        items.forEach((itemId, item) -> {
            if(item.isAMatch(itemKeyWords)) {
                itemIds.add(itemId);
            }
        });

        if(itemIds.size() == 1) {
            return items.get(itemIds.get(0));
        } else {
            StringBuilder sb = new StringBuilder();
            itemKeyWords.forEach(keyWord -> sb.append(keyWord).append(" "));
            throw new ItemNotFoundException(sb.toString().trim());
        }
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        if(!this.isEmpty()) {
            sb.append(" You are carrying these items in your backpack: ");
            //ToDo: better formatting for list of items....
            items.forEach((itemId, item) -> sb.append(item.describe()).append(" "));
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }

    private boolean hasSpace(Item item) {
        return occupiedCapacity + item.getSize() <= capacity;
    }

}
