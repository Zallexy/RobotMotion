package com.ingrachen.robot.model.machine;


import com.ingrachen.robot.action.Command;

/**
 *  @author Hacene Ingrachen
 *
 * interface defining the motion possibilities of the machines
 */
public interface Motion {

    void goStraight();

    void turnLeft();

    void turnRight();

    /**
     *  Default, that moves the machine in  zone
     *  can be overridden to define a different behavior
     *  @param cmd  {@link Command}
     */
    default void move(Command cmd){
        switch(cmd) {
            case A:
                goStraight();
                break;
            case G:
                turnLeft();
                break;
            case D:
                turnRight();
                break;
            default:
                break;
        }
    }
}
