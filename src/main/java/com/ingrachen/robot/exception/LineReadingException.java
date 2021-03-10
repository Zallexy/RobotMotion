package com.ingrachen.robot.exception;

public class LineReadingException extends Exception {

    public LineReadingException(String line){
        super("Error Reading Line " + line) ;
    }
}
