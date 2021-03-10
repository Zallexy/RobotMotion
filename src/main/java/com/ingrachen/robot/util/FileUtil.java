package com.ingrachen.robot.util;


import com.ingrachen.robot.exception.FileNotValidException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Config class for the the scene
 * @author Hacene INGRACHEN
 */
public class FileUtil {
    /**
     *
     * @param pathToFile
     * @return list of the lines in the file
     */
    public static  List<String> readSceneFile(String pathToFile, String allowedExtension) throws FileNotValidException {
        File file = new File(pathToFile);
        List<String> lines = new ArrayList<>();
        if(isValid(file, allowedExtension)) {
            try (Stream<String> stream = Files.lines(Paths.get(pathToFile), Charset.defaultCharset())) {
                lines =  stream.collect(Collectors.toList());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else{
            throw new FileNotValidException("File is not a valid txt format");
        }
        return lines;
    }


    public  static boolean isValid(File file, String allowedExtension){
        String filename = file.getName();
        Optional<String> ext = Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
        return allowedExtension.equals(ext.get());
    }

}