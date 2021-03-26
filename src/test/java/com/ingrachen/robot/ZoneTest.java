package com.ingrachen.robot;

import com.ingrachen.robot.model.machine.Machine;
import com.ingrachen.robot.model.zone.RectangleZone;
import com.ingrachen.robot.model.zone.Zone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayName("Testing Zone And Machines")
public class ZoneTest {

    @Mock
    private Machine machine1;

    @Mock
    private Machine machine2;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMachine(){
        Zone zone = new RectangleZone();
        zone.addMachine(machine1, "ADDAGAAAD");
        Assertions.assertEquals(1, zone.getMachines().size());
    }

    @Test
    public void testAddMachines(){
        Zone zone = new RectangleZone();
        zone.addMachine(machine1, "ADDAGAAAD");
        zone.addMachine(machine2, "ADAD");
        Assertions.assertEquals(2, zone.getMachines().size());
    }


}
