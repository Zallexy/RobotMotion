package com.ingrachen.robot;

import com.ingrachen.robot.action.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing Command Enum")
public class CommandTest {

    @Test
    public void testGetCommandForNameA(){
        Assertions.assertEquals(Command.A, Command.getCommandForName('A'));
    }

    @Test
    public void testGetCommandForNameG(){
        Assertions.assertEquals(Command.G, Command.getCommandForName('G'));
    }

    @Test
    public void testGetCommandForNameD(){
        Assertions.assertEquals(Command.D, Command.getCommandForName('D'));
    }

    @Test
    public void testRegularExpression(){
        Assertions.assertEquals("[A|G|D]", Command.regularExpression());
    }
}
