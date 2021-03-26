package com.ingrachen.robot;

import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Position ")
public class PositionTest {

    @Test
    public void testProgressNorth(){
        Position posi = new Position(6,0, Direction.N);
        posi.progress(Direction.N);
        Assertions.assertEquals(1, posi.getY());
    }

    @Test
    public void testProgressSouth(){
        Position posi = new Position(6,2, Direction.N);
        posi.progress(Direction.S);
        Assertions.assertEquals(1, posi.getY());
    }

    @Test
    public void testProgressWest(){
        Position posi = new Position(6,2, Direction.N);
        posi.progress(Direction.W);
        Assertions.assertEquals(5, posi.getX());
    }

    @Test
    public void testProgressEast(){
        Position posi = new Position(4,2, Direction.N);
        posi.progress(Direction.E);
        Assertions.assertEquals(5, posi.getX());
    }
}
