package com.ingrachen.robot.scene;


import com.ingrachen.robot.action.Command;
import com.ingrachen.robot.exception.LineReadingException;
import com.ingrachen.robot.exception.OutOfBoundsException;
import com.ingrachen.robot.model.bound.ZoneBound;
import com.ingrachen.robot.model.bound.RectangleZoneBound;
import com.ingrachen.robot.model.zone.IZone;
import com.ingrachen.robot.model.zone.RectangleZone;
import com.ingrachen.robot.model.machine.Machine;
import com.ingrachen.robot.model.machine.Mower;
import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SceneBuilder extends AbstractScene {

    /* Any number of digits followed by whitespace followed by any number of digits*/
    public static final String ZONE_REGEX = "^\\d+[ ]\\d+$";
    /* Any number of digits followed by whitespace followed by any number of digits followed by whitespace followed by One direction character */
    public static final String POSITION_REGEX = "^\\d+[ ]\\d+[ ]" + Direction.regularExpression() + "$";
    /* Any number of command characters */
    public static final String COMMAND_SEQUENCE_REGEX = Command.regularExpression() + "+";

    public static final String ALLOWED_EXTENSION = "txt";

    public static final String PATH_TO_FILE = "src/main/resources/init_file/scene_builder_file.txt";


    public SceneBuilder(){
        super(PATH_TO_FILE, ALLOWED_EXTENSION, POSITION_REGEX, COMMAND_SEQUENCE_REGEX);
    }


    @Override
    public IZone initZone(String line) throws LineReadingException{
        Pattern pattern = Pattern.compile(ZONE_REGEX);
        Matcher matcher = pattern.matcher(line);
        IZone zone;
        if(matcher.matches()){
            String[] part = line.split(" ");
            /* Scalability : this method can be used to create and return any zone shape
             *  we will need to put and get the shape of the zone we want from the text file if we want to
             *  handle different kind of zones like rectangular, circular or triangular zones
             *  For example, in the first line of the txt file, we can have "T 5 5" For a Triangle zone  of size 6x6
             * */
             // set zone size from coordinates (add 1 because coordinate axes start at 0)
            zone = new RectangleZone(Integer.parseInt(part[0]) + 1,Integer.parseInt(part[1]) + 1);
            /* Set the bounds dimensions same as zone dimensions */
            setBounds(new RectangleZoneBound((RectangleZone) zone));
        } else {
            throw new LineReadingException(line);
        }
        return zone;
    }

    @Override
    public Machine buildMachine(String positionLine, ZoneBound bounds) throws LineReadingException, OutOfBoundsException {
        Position pos = getPosition(positionLine);
        /* Scalability : this method can be used to create and return any machine type, the machine type can be defined in the input txt file,
         * we will need to put and get this information from the text file if we want to handle different kind of machines
         * like CleanerRobot, snow remover, etc.
         *  For example in positionLine, we can have "V 3 4 E" -for a Machine of type Vaccuum in position (3,4) East
         *  in java :
         *          if(MachineType == MType.V) machine = new Vaccuum(pos, bounds);
         *           else if(MachineType == MType.M) machine = new Mower(pos, bounds);
         *           return machine;
         * */
        Machine machine = new Mower(pos, bounds);
        if(machine.isPositionInsideBounds(true)){
            return machine;
        } else {
            throw new OutOfBoundsException(positionLine);
        }
    }
}
