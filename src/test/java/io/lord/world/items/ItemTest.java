package io.lord.world.items;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemTest {

    @Test
    public void testIsAMatchShouldResultTrue() {
        //GIVEN
        final Item item = new Item("A blue knife. The blade is broken.", 1);

        //WHEN
        final boolean result = item.isAMatch(List.of("Blue", "KNIFE"));

        //THEN
        assertTrue(result);
    }

    @Test
    public void testIsAMatchShouldResultFalse() {
        //GIVEN
        final Item item = new Item("A blue knife. The blade is broken.",1 );

        //WHEN
        final boolean result = item.isAMatch(List.of("green", "KNIFE"));

        //THEN
        assertFalse(result);
    }

}