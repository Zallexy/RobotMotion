package com.ingrachen.robot.scene;

import com.ingrachen.robot.exception.FileNotValidException;
import com.ingrachen.robot.exception.LineReadingException;
import com.ingrachen.robot.exception.OutOfBoundsException;
import com.ingrachen.robot.model.bound.ZoneBound;
import com.ingrachen.robot.model.zone.IZone;
import com.ingrachen.robot.model.machine.Machine;
import com.ingrachen.robot.model.position.Direction;
import com.ingrachen.robot.model.position.Position;
import com.ingrachen.robot.util.FileUtil;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Hacene Ingrachen
 */
public abstract class AbstractScene {

    private String commandSequenceRegex;
    private String positionRegex;
    private ZoneBound bounds;
    private String allowedExtension;
    private String path;


    /**
     * Abstract Scene Builder
     * @param path Path to txt file
     * @param allowedExtension The allowed file extension
     * @param positionRegex The regular expression for machine position
     * @param commandSequenceRegex The regular expression for the command sequence
     */
    public AbstractScene(String path, String allowedExtension, String positionRegex,String commandSequenceRegex){
        this.path = path;
        this.allowedExtension = allowedExtension;
        this.commandSequenceRegex = commandSequenceRegex;
        this.positionRegex = positionRegex;
    }


    /**
     * @param line the Position data from the file
     * @return {@link Position}
     * @throws LineReadingException Data reading exception
     */
    public Position getPosition(String line) throws LineReadingException {
        Pattern pattern = Pattern.compile(positionRegex);
        Matcher matcher = pattern.matcher(line);
        Position pos;
        if(matcher.matches()){
            String[] part = line.split(" ");
            int x = Integer.parseInt(part[0]);
            int y = Integer.parseInt(part[1]);
            Direction dir = Direction.getDirectionForName(part[2].charAt(0));
            pos = new Position(x, y, dir);
        } else{
            throw new LineReadingException(line);
        }
        return pos;
    }

    /**
     * @param line the Command data from the file
     * @return text sequence of {@link com.ingrachen.robot.action.Command} values
     * @throws LineReadingException data reading exception
     */
    public String getCommandSequence(String line) throws  LineReadingException{
        Pattern pattern = Pattern.compile(commandSequenceRegex);
        Matcher matcher = pattern.matcher(line);
        StringBuilder commandSequence = new StringBuilder();
        if(matcher.matches()){
            commandSequence.append(line);
        } else {
            throw new LineReadingException(line);
        }
        return commandSequence.toString();
    }

    /**
     * Builds the zone from the file data
     * @return {@link IZone}
     */
    public IZone buildZone(){
        IZone zone = null;
        try{
            List<String> lines = FileUtil.readSceneFile(path, allowedExtension);
            if(!lines.isEmpty()) {
                try {
                    zone = initZone(lines.get(0));
                    for (int i = 1; i < lines.size(); i++) {
                        String positionLine = lines.get(i);
                        String commandSequenceLine = lines.get(++i);
                        Machine machine = buildMachine(positionLine, this.bounds);
                        String commandSequence = getCommandSequence(commandSequenceLine);
                        zone.addMachine(machine, commandSequence);
                    }
                } catch (LineReadingException | OutOfBoundsException e){
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Empty file or data can't be read");
            }
        } catch (FileNotValidException e) {
            System.out.println(e.getMessage());
        }

        return zone;
    }

    /**
     * @param line The Zone data from the file
     * @return {@link IZone}
     * @throws LineReadingException Data reading exception
     */
    public abstract IZone initZone(String line) throws LineReadingException;

    /**
     * @param positionLine The machine position data  from the file
     * @param bounds The limits of the machine motion
     * @return {@link Machine}
     * @throws LineReadingException Date reading exception
     * @throws OutOfBoundsException Position is out of bounds exception
     */
    public abstract Machine buildMachine(String positionLine, ZoneBound bounds) throws LineReadingException, OutOfBoundsException;

    public void setBounds(ZoneBound bounds){
        this.bounds = bounds;
    }

}
