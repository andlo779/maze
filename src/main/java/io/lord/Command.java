package io.lord;

import java.util.Arrays;
import java.util.List;

public class Command {

    private final String originalCommand;
    private final List<String> commands;
    private int currentCommand = 0;


    public Command(String input) {
        this.originalCommand = input;
        this.commands = Arrays.asList(input.toLowerCase().split(" "));
    }

    public boolean hasNext() {
        return this.currentCommand < commands.size();
    }

    public String getNext() {
        if(hasNext()) {
            return this.commands.get(this.currentCommand++);
        }
        return "";
    }

    public String getOriginalCommand() {
        return this.originalCommand;
    }
}
