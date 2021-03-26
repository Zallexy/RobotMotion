package com.ingrachen.robot.exception;

/**
 * @author Hacene Ingrachen
 */
public class LineReadingException extends Exception {

    public LineReadingException(String line){
        super("Error Reading Line " + line) ;
    }
}
