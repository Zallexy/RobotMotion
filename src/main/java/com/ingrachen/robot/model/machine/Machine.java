package com.ingrachen.robot.model.machine;


import com.ingrachen.robot.model.bound.ZoneBound;
import com.ingrachen.robot.model.position.Position;

/**
 *  @author Hacene Ingrachen
 *
 * This abstract class be used to create machine child classes like Vaccuum, CleanerRobot, SnowRemover, etc.
 */
public abstract class Machine implements Motion {

    protected Position pos;
    protected ZoneBound bounds;

    public Machine(){}

    public Machine(Position pos, ZoneBound bounds){
        this.pos = pos;
        this.bounds = bounds;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public ZoneBound getBounds() {
        return bounds;
    }

    public void setBounds(ZoneBound bounds) {
        this.bounds = bounds;
    }

    /**
     * @param initialPosition the initial position of the machine
     * @return boolean value
     */
    abstract boolean validEastCoordinate(boolean initialPosition);

    /**
     * @param initialPosition the initial position of the machine
     * @return boolean value
     */
    abstract boolean validWestCoordinate(boolean initialPosition);

    /**
     * @param initialPosition the initial position of the machine
     * @return boolean value
     */
    abstract boolean validNorthCoordinate(boolean initialPosition);

    /**
     * @param initialPosition the initial position of the machine
     * @return boolean value
     */
    abstract boolean validSouthCoordinate(boolean initialPosition);

    /**
     *  Checks if initial position of machine is inside the fixed bounds
     *  checks if future position to move to is valid (not out of the defined bounds)
     * @param initialPosition the initial position of the machine
     * @return boolean value
     */
    public boolean isPositionInsideBounds(boolean initialPosition){
        return validEastCoordinate(initialPosition) && validWestCoordinate(initialPosition) && validNorthCoordinate(initialPosition) && validSouthCoordinate(initialPosition);
    }
}
