package com.ingrachen.robot.model.position;

/**
 * @author Hacene Ingrachen
 */
public class Position {

    private int x, y;
    private Direction dir;

    public Position(){}

    public Position(int x, int y, Direction dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    /**
     * Moves the position in a given direction
     * @param dir {@link Direction}
     */
    public void progress(Direction dir){
        switch(dir){
            case N:
                y++;
                break;
            case S:
                y--;
                break;
            case E:
                x++;
                break;
            case W:
                x--;
                break;
            default:
                break;
        }
    }
}
