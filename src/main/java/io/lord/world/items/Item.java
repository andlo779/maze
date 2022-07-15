package io.lord.world.items;

import java.util.List;
import java.util.UUID;

public class Item {

    private final String ID;
    private final String description;
    private final int size;

    public Item(String description, int size) {
        this.description = description;
        this.size = size;
        this.ID = UUID.randomUUID().toString();
    }

    public String describe() {
        return this.description;
    }

    public String getId() {
        return this.ID;
    }

    public int getSize() {
        return this.size;
    }

    public boolean equals(Item other) {
        return this.ID.equals(other.ID);
    }

    public boolean isAMatch(List<String> keyWords) {
        final String s = description.toLowerCase();

        for (String keyWord : keyWords) {
            if (!s.contains(keyWord.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
