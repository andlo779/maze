import io.lord.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    public void hasNext() {
        //GIVEN
        Command command = new Command("This is a test");

        //WHEN
        final boolean result = command.hasNext();

        //THEN
        assertTrue(result);
    }

    @Test
    public void getNext() {
        //GIVEN
        Command command = new Command("This is a test");

        //WHEN
        final String result = command.getNext();

        //THEN
        assertEquals("this", result);
    }

    @Test
    public void originalCommand() {
        //GIVEN
        final String string = "This is a test";
        Command command = new Command(string);

        //WHEN
        final String result = command.getOriginalCommand();

        //THEN
        assertEquals(string, result);
    }
}