package com.ingrachen.robot;

import com.ingrachen.robot.action.Command;
import com.ingrachen.robot.model.bound.RectangleZoneBound;
import com.ingrachen.robot.model.machine.Mower;
import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@DisplayName("Testing Machine Position And Motion")
public class MowerTest {

    @Mock
    private RectangleZoneBound bounds;

    @Spy
    private Position position;

    @InjectMocks
    private Mower mower;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPositionOutsideInitialBoundsEast(){
        position = new Position(6,6, Direction.N);
        mower.setPos(position);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        Assertions.assertFalse(mower.isPositionInsideBounds(true));
    }

    @Test
    public void testPositionOutsideInitialBoundsWest(){
        position = new Position(0,0, Direction.N);
        mower.setPos(position);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(1);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        Assertions.assertFalse(mower.isPositionInsideBounds(true));
    }

    @Test
    public void testPositionOutsideInitialBoundsNorth(){
        position = new Position(0,6, Direction.N);
        mower.setPos(position);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        Assertions.assertFalse(mower.isPositionInsideBounds(true));
    }

    @Test
    public void testPositionOutsideInitialBoundsSouth(){
        position = new Position(0,0, Direction.N);
        mower.setPos(position);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(1);
        Assertions.assertFalse(mower.isPositionInsideBounds(true));
    }

    @Test
    public void testPositionInsideInitialBounds(){
        position = new Position(3,3, Direction.N);
        mower.setPos(position);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        Assertions.assertTrue(mower.isPositionInsideBounds(true));
    }


    @Test
    public void testPositionAfterMoveNorth() {
        position  = new Position(3,3, Direction.N);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        //doCallRealMethod().when(position).progress(isA(Direction.class));
        mower.move(Command.A);
        Assertions.assertEquals("3 4 N", mower.toString());
    }

    @Test
    public void testPositionAfterMoveEast() {
        position  = new Position(2,1, Direction.W);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.D);
        mower.move(Command.D);
        mower.move(Command.A);
        Assertions.assertEquals("3 1 E", mower.toString());
    }

    @Test
    public void testPositionAfterMoveWest() {
        position  = new Position(1, 1, Direction.N);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.G);
        mower.move(Command.A);
        Assertions.assertEquals("0 1 W", mower.toString());
    }

    @Test
    public void testPositionAfterMoveSouth() {
        position  = new Position(5,5, Direction.W);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.G);
        mower.move(Command.A);
        Assertions.assertEquals("5 4 S", mower.toString());
    }

    @Test
    public void testPositionAfterMoveSequence() {
        position  = new Position(0,0, Direction.N);
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(5);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.A);
        mower.move(Command.D);
        mower.move(Command.A);
        mower.move(Command.G);
        mower.move(Command.A);
        mower.move(Command.D);
        mower.move(Command.A);//(2 2 E)
        mower.move(Command.G);
        mower.move(Command.A);//(2 3 N)
        Assertions.assertEquals("2 3 N", mower.toString());
    }

    @Test
    public void testPositionAfterMoveBeyondBoundsEast() {
        position  = new Position(3,3, Direction.E);
        when(bounds.getEastBound()).thenReturn(4);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(4);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        Assertions.assertEquals("4 3 E", mower.toString());
    }

    @Test
    public void testPositionAfterMoveBeyondBoundsWest() {
        position  = new Position(2,3, Direction.W);
        when(bounds.getEastBound()).thenReturn(4);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(4);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        Assertions.assertEquals("0 3 W", mower.toString());
    }

    @Test
    public void testPositionAfterMoveBeyondBoundsNorth() {
        position  = new Position(4,4, Direction.N);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        Assertions.assertEquals("4 6 N", mower.toString());
    }

    @Test
    public void testPositionAfterMoveBeyondBoundsSouth() {
        position  = new Position(2,1, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        mower.move(Command.A);
        Assertions.assertEquals("2 0 S", mower.toString());
    }

    @Test
    public void testIsValidInitialPosition() {
        position  = new Position(5,1, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertTrue(mower.isPositionInsideBounds(true));
    }

    @Test
    public void testInvalidEastCoordinate(){
        position  = new Position(7,1, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validEastCoordinate(true));
    }

    @Test
    public void testInvalidWestCoordinate(){
        position  = new Position(-1 ,1, Direction.W);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validWestCoordinate(true));
    }

    @Test
    public void testInvalidSouthCoordinate(){
        position  = new Position(3,-1, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validSouthCoordinate(true));
    }

    @Test
    public void testInvalidNorthCoordinate(){
        position  = new Position(7,8, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validNorthCoordinate(true));
    }

    @Test
    public void testInvalidFutureEastCoordinate(){
        position  = new Position(6,1, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validEastCoordinate(false));
    }

    @Test
    public void testInvalidFutureWestCoordinate(){
        position  = new Position(0 ,1, Direction.W);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validWestCoordinate(false));
    }

    @Test
    public void testInvalidFutureSouthCoordinate(){
        position  = new Position(3,0, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validSouthCoordinate(false));
    }

    @Test
    public void testInvalidFutureNorthCoordinate(){
        position  = new Position(7,6, Direction.S);
        when(bounds.getEastBound()).thenReturn(6);
        when(bounds.getWestBound()).thenReturn(0);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        mower.setPos(position);
        Assertions.assertFalse(mower.validNorthCoordinate(false));
    }
}