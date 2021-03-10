package com.ingrachen.robot.model.position;

import java.util.Arrays;
import java.util.StringJoiner;

public enum Direction {
    N('N'), W('W'), E('E'), S('S');

    private char name;

    Direction(char name) {
        this.name = name;
    }

    /**
     * Converts char to Direction
     * @param name
     * @return cmd
     */
    public static Direction getDirectionForName(final char name) {
        return Arrays.stream(Direction.values()).filter(d -> d.name == name).findAny().orElse(null);
    }

    /**
     * builds regular expression dynamically
     * @return regular expression for the possible directions
     */
    public static String regularExpression(){
        StringJoiner joinDirectionNames = new StringJoiner("|", "[", "]");
        Arrays.stream(Direction.values()).forEach(d -> joinDirectionNames.add(String.valueOf(d.name)));
        return joinDirectionNames.toString();
    }

}
