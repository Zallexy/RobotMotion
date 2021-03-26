package com.ingrachen.robot;

import com.ingrachen.robot.exception.LineReadingException;
import com.ingrachen.robot.exception.OutOfBoundsException;
import com.ingrachen.robot.model.bound.RectangleZoneBound;
import com.ingrachen.robot.model.machine.Machine;
import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;
import com.ingrachen.robot.model.zone.RectangleZone;
import com.ingrachen.robot.scene.AbstractScene;
import com.ingrachen.robot.scene.SceneBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
@DisplayName("Testing Building Scene")
public class SceneBuilderTest {

    //private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Spy
    private SceneBuilder builder;

    @Mock
    private RectangleZoneBound bounds;

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outputStreamCaptor));
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({"0 1 N"})
    public void testGetPositionFromLineN(String line) throws LineReadingException {
        Position pos = builder.getPosition(line);
        Assertions.assertNotNull(pos);
        Assertions.assertEquals(Direction.N, pos.getDir());
        Assertions.assertEquals(0,  pos.getX());
        Assertions.assertEquals(1,  pos.getY());
    }

    @ParameterizedTest
    @CsvSource({"0 1 W"})
    public void testGetPositionFromLineW(String line) throws LineReadingException {
        Position pos = builder.getPosition(line);
        Assertions.assertEquals(Direction.W, pos.getDir());
    }

    @ParameterizedTest
    @CsvSource({"0 1 S"})
    public void testGetPositionFromLineS(String line) throws LineReadingException {
        Position pos = builder.getPosition(line);
        Assertions.assertEquals(Direction.S, pos.getDir());
    }

    @ParameterizedTest
    @CsvSource({"0 1 E"})
    public void testGetPositionFromLineE(String line) throws LineReadingException {
        Position pos = builder.getPosition(line);
        Assertions.assertEquals(Direction.E, pos.getDir());
    }

    @ParameterizedTest
    @CsvSource({"09N"})
    public void testGetPositionFromLineException(String line)  {
        Assertions.assertThrows(LineReadingException.class, () -> builder.getPosition(line));
    }

    @ParameterizedTest
    @CsvSource({"0 9N"})
    public void testGetPositionFromLineExceptionSpace1(String line)  {
        Assertions.assertThrows(LineReadingException.class, () -> builder.getPosition(line));
    }

    @ParameterizedTest
    @CsvSource({"09 N"})
    public void testGetPositionFromLineExceptionSpace2(String line)  {
        Assertions.assertThrows(LineReadingException.class, () -> builder.getPosition(line));
    }

    @ParameterizedTest
    @CsvSource({"0 9 F"})
    public void testGetPositionFromLineExceptionWrongDirection(String line)  {
        Assertions.assertThrows(LineReadingException.class, () -> builder.getPosition(line));
    }

    @ParameterizedTest
    @CsvSource({"AAGAD"})
    public void testGetCommandSequenceFromLine(String line) throws LineReadingException {
        String seq = builder.getCommandSequence(line);
        Assertions.assertEquals("AAGAD", seq);
    }

    @ParameterizedTest
    @CsvSource({"A AGAD"})
    public void testGetCommandSequenceFromLineWithSpace(String line)  {
        Assertions.assertThrows(LineReadingException.class, () -> builder.getCommandSequence(line));
    }

    @ParameterizedTest
    @CsvSource({"ABGAD"})
    public void testGetCommandSequenceFromLineWrongCommand(String line)  {
        Assertions.assertThrows(LineReadingException.class, () -> builder.getCommandSequence(line));
    }

    @ParameterizedTest
    @CsvSource({"5 6"})
    public void testInitZone(String line) throws LineReadingException {
        RectangleZone zone = (RectangleZone) builder.initZone(line);
        Assertions.assertNotNull(zone);
        Assertions.assertEquals(6, zone.getWidth());
        Assertions.assertEquals(7, zone.getLength() );
    }

    @ParameterizedTest
    @CsvSource({"56"})
    public void testInitZoneReadingReadingException(String line) {
        Assertions.assertThrows(LineReadingException.class, () -> builder.initZone(line));
    }

    @Test
    public void testBuildZone() {
        AbstractScene builder = new SceneBuilder("src/test/resources/init_file/scene_builder_test_file.txt", "txt", "^\\d+[ ]\\d+[ ][N|S|W|E]$", "[A|G|D]+");
        RectangleZone zone = (RectangleZone)builder.buildZone();
        Assertions.assertEquals(6, zone.getLength());
        Assertions.assertEquals(6, zone.getWidth());
        Assertions.assertEquals(2, zone.getMachines().size());
    }

    @Test
    public void testBuildZoneReadingException() {
        AbstractScene builder = new SceneBuilder("src/test/resources/init_file/scene_builder_test_file_read_error.txt", "txt", "^\\d+[ ]\\d+[ ][N|S|W|E]$", "[A|G|D]+");
        Assertions.assertNull(builder.buildZone());
    }

    @Test
    public void testBuildZoneFileNotValidException() {
        AbstractScene builder = new SceneBuilder("src/test/resources/init_file/scene_builder_test_file_valid_error.json", "txt", "^\\d+[ ]\\d+[ ][N|S|W|E]$", "[A|G|D]+");
        Assertions.assertNull(builder.buildZone());
        Assertions.assertEquals("File is not a valid txt format", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testBuildZoneOutOfBoundException() {
        AbstractScene builder = new SceneBuilder("src/test/resources/init_file/scene_builder_test_file_out_of_bound_error.txt", "txt", "^\\d+[ ]\\d+[ ][N|S|W|E]$", "[A|G|D]+");
        Assertions.assertNotNull(builder.buildZone());
        Assertions.assertEquals("Error : Trying to put the machine in a position 7 7 N that is out of the bounds", outputStreamCaptor.toString().trim());
    }


    @ParameterizedTest
    @CsvSource({"3 4 N"})
    public void testBuildMAchine(String positionLine) throws LineReadingException, OutOfBoundsException {
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(1);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        Machine machine = builder.buildMachine(positionLine, bounds);
        Assertions.assertEquals(5, machine.getBounds().getEastBound());
        Assertions.assertEquals(1, machine.getBounds().getWestBound());
        Assertions.assertEquals(6, machine.getBounds().getNorthBound());
        Assertions.assertEquals(0, machine.getBounds().getSouthBound());
        Assertions.assertEquals(4, machine.getPos().getY());
        Assertions.assertEquals(3, machine.getPos().getX());
        Assertions.assertEquals(Direction.N, machine.getPos().getDir());
    }

    @ParameterizedTest
    @CsvSource({"8 8 N"})
    public void testBuildMachineOutOfBoundException(String positionLine) {
        when(bounds.getEastBound()).thenReturn(5);
        when(bounds.getWestBound()).thenReturn(1);
        when(bounds.getNorthBound()).thenReturn(6);
        when(bounds.getSouthBound()).thenReturn(0);
        Assertions.assertThrows(OutOfBoundsException.class, () -> builder.buildMachine(positionLine, bounds));
    }


}
