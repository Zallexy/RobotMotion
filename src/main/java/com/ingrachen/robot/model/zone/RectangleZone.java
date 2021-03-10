package com.ingrachen.robot.model.zone;

/**
 * Concrete zone of rectangular shape
 */
public class RectangleZone extends Zone {

    private int width, length;

    public RectangleZone(){}

    public RectangleZone(int width, int length){
        super();
        this.width = width;
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
