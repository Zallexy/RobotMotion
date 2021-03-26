package com.ingrachen.robot.model.bound;

/**
 *  @author Hacene Ingrachen
 *
 * Interface of 4 bounds, we can define more complexe  bounds to handle non rectangular shapes
 */
public interface ZoneBound {

    int getEastBound();

    int getWestBound();

    int getNorthBound();

    int getSouthBound();

}
