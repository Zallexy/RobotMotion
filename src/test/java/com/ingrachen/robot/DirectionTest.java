package com.ingrachen.robot;

import com.ingrachen.robot.model.position.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Direction Enum")
public class DirectionTest {


    @Test
    public void testGetDirectionForNameE(){
        Assertions.assertEquals(Direction.E, Direction.getDirectionForName('E'));
    }

    @Test
    public void testGetDirectionForNameW(){
        Assertions.assertEquals(Direction.W, Direction.getDirectionForName('W'));
    }

    @Test
    public void testGetDirectionForNameS(){
        Assertions.assertEquals(Direction.S, Direction.getDirectionForName('S'));
    }

    @Test
    public void testGetDirectionForNameN(){
        Assertions.assertEquals(Direction.N, Direction.getDirectionForName('N'));
    }

    @Test
    public void testRegularExpression(){
        Assertions.assertEquals("[N|W|E|S]", Direction.regularExpression());
    }

}
