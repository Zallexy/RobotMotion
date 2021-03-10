package com.ingrachen.robot.model.bound;

import com.ingrachen.robot.model.zone.RectangleZone;

/**
 * Class can be used to set bounds for the machines. The machines shouldn't  be able to move outside the bounds
 * thus we can define a bound smaller than the zone if we want the machine to operate in only a part of the zone
 */
public class RectangleZoneBound implements ZoneBound {

    int eastBound, westBound, northBound, southBound;

    public RectangleZoneBound() {
    }

    /* Use this constructor to set bounds same as zone */
    public RectangleZoneBound(RectangleZone zone) {
        /* -1 to get biggest coordinates from size*/
        this.eastBound = zone.getWidth() -1;
        this.westBound = 0;
        this.northBound = zone.getLength() -1;
        this.southBound = 0;
    }

    /* Use this constructor to set bounds if different than zone
     * the bounds should be smaller than the zone limits
    *  this will limit the machine to move and work only on small part of the zone*/
    public RectangleZoneBound(int eastBound, int westBound, int northBound, int southBound) {
        this.eastBound = eastBound;
        this.westBound = westBound;
        this.northBound = northBound;
        this.southBound = southBound;
    }


    @Override
    public int getEastBound() {
        return eastBound;
    }

    @Override
    public int getWestBound() {
        return westBound;
    }

    @Override
    public int getNorthBound() {
        return northBound;
    }

    @Override
    public int getSouthBound() {
        return southBound;
    }
}
