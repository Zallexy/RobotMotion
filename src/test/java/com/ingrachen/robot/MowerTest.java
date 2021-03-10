package com.ingrachen.robot;

import com.ingrachen.robot.action.Command;
import com.ingrachen.robot.model.bound.RectangleZoneBound;
import com.ingrachen.robot.model.bound.ZoneBound;
import com.ingrachen.robot.model.machine.Machine;
import com.ingrachen.robot.model.machine.Mower;
import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ContextConfiguration
@DisplayName("Mower tests and assertions")
public class MowerTest {

    @Mock
    private RectangleZoneBound bounds;
    @Mock
    private Position position;
    @InjectMocks
    private Mower mower;

    @BeforeAll
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testBounds(){
        Machine mower = new Mower();
        mower.setBounds(new RectangleZoneBound(5,0,5,0));
        mower.setPos(new Position(0,0, Direction.N));
        //mower.move(Command.A);
        Assertions.assertTrue(mower.isPositionInsideBounds(true));
    }

    @Test
    public void testMotion2(){
        Machine mower = new Mower();
        mower.setBounds(new RectangleZoneBound(5,0,5,0));
        mower.setPos(new Position(5,5, Direction.N));
        mower.move(Command.A);
        Assertions.assertFalse(mower.isPositionInsideBounds(false));
    }

  @Test
  public void testMotion(){
      Machine mower = new Mower();
      mower.setBounds(new RectangleZoneBound(5,0,5,0));
      mower.setPos(new Position(0,0, Direction.N));
      mower.move(Command.A);
      //Assertions.assertArrayEquals();
      assertThat(mower.toString(), is("0 1 N"));
  }

}