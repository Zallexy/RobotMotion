package com.ingrachen.robot.action;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author Hacene Ingrachen
 */
public enum Command {
    A('A'),
    G('G'),
    D('D');

    private char name;

    Command(char name) {
        this.name = name;
    }

    /**
     * Converts char to Command
     * @param name char name of the command
     * @return cmd the Command
     */
    public static Command getCommandForName(final char name) {
        return Arrays.stream(Command.values()).filter(c -> c.name == name).findAny().orElse(null);
    }

    /**
     * builds regular expression dynamically
     * @return regular expression for the possible commands
     */
    public static String regularExpression(){
        StringJoiner joinCommandNames = new StringJoiner("|", "[", "]");
        Arrays.stream(Command.values()).forEach(c -> joinCommandNames.add(String.valueOf(c.name)));
        return joinCommandNames.toString();
    }

}
