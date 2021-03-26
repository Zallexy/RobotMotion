package com.ingrachen.robot.model.machine;


import com.ingrachen.robot.model.bound.ZoneBound;
import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;

/**
 * @author Hacene Ingrachen
 */
public class Mower extends Machine {

    public Mower(){super();}

    public Mower(Position pos, ZoneBound bounds){
        super(pos, bounds);
    }

    /**
     * Turns the machine 90° to the left
     */
    @Override
    public void turnLeft(){
        if(this.pos.getDir() == Direction.N) {
            this.pos.setDir(Direction.W);
        } else if(this.pos.getDir() == Direction.W) {
            this.pos.setDir(Direction.S);
        } else if(this.pos.getDir() == Direction.S) {
            this.pos.setDir(Direction.E);
        } else if(this.pos.getDir() == Direction.E) {
            this.pos.setDir(Direction.N);
        }
    }

    /**
     * Turns the machine 90° to the right
     */
    @Override
    public void turnRight(){
        if(this.pos.getDir() == Direction.N) {
            this.pos.setDir(Direction.E);
        } else if(this.pos.getDir() == Direction.E) {
            this.pos.setDir(Direction.S);
        } else if(this.pos.getDir() == Direction.S) {
            this.pos.setDir(Direction.W);
        } else if(this.pos.getDir() == Direction.W) {
            this.pos.setDir(Direction.N);
        }
    }

    /**
     * moves one position in straight line
     */
    @Override
    public void goStraight(){
        if(this.pos.getDir() == Direction.N && validNorthCoordinate(false) ) {
            this.pos.progress(Direction.N);
        } else if(this.pos.getDir() == Direction.E && validEastCoordinate(false) ) {
            this.pos.progress(Direction.E);
        } else if(this.pos.getDir() == Direction.S && validSouthCoordinate(false) ) {
            this.pos.progress(Direction.S);
        } else if(this.pos.getDir() == Direction.W && validWestCoordinate(false) ) {
            this.pos.progress(Direction.W);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean validEastCoordinate(boolean initialPosition){
        return initialPosition ? this.pos.getX() <= this.bounds.getEastBound() : this.pos.getX() < this.bounds.getEastBound();
    }

    /**
     * {@inheritDoc}
     */
    public boolean validWestCoordinate(boolean initialPosition){
        return initialPosition ?  this.pos.getX() >= this.bounds.getWestBound() :  this.pos.getX() > this.bounds.getWestBound();
    }

    /**
     * {@inheritDoc}
     */
    public boolean validNorthCoordinate(boolean initialPosition){
        return initialPosition ?  this.pos.getY() <= this.bounds.getNorthBound() : this.pos.getY() < this.bounds.getNorthBound();
    }

    /**
     * {@inheritDoc}
     */
    public boolean validSouthCoordinate(boolean initialPosition){
        return initialPosition ?  this.pos.getY() >= this.bounds.getSouthBound() : this.pos.getY() > this.bounds.getSouthBound();
    }

    /**
     * @return Final position of the mower after motion
     */
    @Override
    public String toString(){
        return this.pos.getX() + " " + this.pos.getY() + " " + this.pos.getDir().toString();
    }
}

