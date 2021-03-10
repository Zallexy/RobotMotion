package com.ingrachen.robot.model.zone;

import com.ingrachen.robot.model.machine.Machine;

import java.util.Map;

public interface IZone {

    void addMachine(Machine machine, String commandSequence);

    Map<Machine,String> getMachines();

    void setMachines(Map<Machine, String> machines);
}
