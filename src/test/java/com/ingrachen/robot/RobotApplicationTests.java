package com.ingrachen.robot;

import com.ingrachen.robot.action.Command;
import com.ingrachen.robot.model.machine.Machine;
import com.ingrachen.robot.model.zone.RectangleZone;
import com.ingrachen.robot.scene.AbstractScene;
import com.ingrachen.robot.scene.SceneBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ContextConfiguration
@DisplayName("Integration Tests")
class RobotApplicationTests {

    @Test
    public void testBuildZoneAndMoveMcahines() {
        AbstractScene builder = new SceneBuilder("src/test/resources/init_file/scene_builder_test_file.txt", "txt", "^\\d+[ ]\\d+[ ][N|S|W|E]$", "[A|G|D]+");
        RectangleZone zone = (RectangleZone)builder.buildZone();
        Map<Machine, String> machines = zone.getMachines();
        List<Map.Entry<Machine, String>> list = new ArrayList<>(machines.entrySet());
        Map.Entry<Machine, String> firstMachine = list.get(0);
        firstMachine.getValue().chars().mapToObj(i -> (char) i).forEach(c -> firstMachine.getKey().move(Command.getCommandForName(c)));
        Assertions.assertEquals("1 1 E", firstMachine.getKey().toString());

        Map.Entry<Machine, String> secondMachine = list.get(1);
        secondMachine.getValue().chars().mapToObj(i -> (char) i).forEach(c -> secondMachine.getKey().move(Command.getCommandForName(c)));
        Assertions.assertEquals("2 2 N", secondMachine.getKey().toString());
    }
}
