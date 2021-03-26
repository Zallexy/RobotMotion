package com.ingrachen.robot;

import com.ingrachen.robot.action.Command;
import com.ingrachen.robot.scene.AbstractScene;
import com.ingrachen.robot.scene.SceneBuilder;
import com.ingrachen.robot.model.zone.IZone;
import com.ingrachen.robot.model.machine.Machine;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class RobotApplication {

    public static void main(String[] args){
        /* can create any zone of any Shape type */
        AbstractScene sceneBuilder = new SceneBuilder();
        IZone zone = sceneBuilder.buildZone();
        if(zone != null){
            /* there can be any type of machines in the created zone*/
            Map<Machine, String> machines = zone.getMachines();
            // execute corresponding command sequence on every machine
            machines.forEach((k, v) -> v.chars().mapToObj(i -> (char) i).forEach(c -> k.move(Command.getCommandForName(c))));
            // print final state(position and direction) of the machines
            machines.keySet().iterator().forEachRemaining(System.out::println);
            //machines.keySet().forEach(System.out::println);
        }
    }
}
