package com.ingrachen.robot.model.zone;


import com.ingrachen.robot.model.machine.Machine;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Hacene Ingrachen
 * this class, can be used to create un any shape of zones for the machines
 */
public abstract class Zone implements IZone {

    private Map<Machine,String> machines;

    public Zone(){
        machines = new LinkedHashMap<>();
    }

    public void addMachine(Machine machine, String commandSequence){
        machines.put(machine, commandSequence);
    }

    public Map<Machine,String> getMachines(){
        return machines;
    }

    public void setMachines(Map<Machine, String> machines) {
        this.machines = machines;
    }
}
