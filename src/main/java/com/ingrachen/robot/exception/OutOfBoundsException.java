package com.ingrachen.robot.exception;

public class OutOfBoundsException extends Exception {

    public OutOfBoundsException(String line){
        super("Error : Trying to put the machine in a position " + line + " that is out of the bounds") ;
    }
}
