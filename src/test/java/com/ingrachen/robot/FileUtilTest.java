package com.ingrachen.robot;

import com.ingrachen.robot.exception.FileNotValidException;
import com.ingrachen.robot.util.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
@DisplayName("Testing File Utility Class")
public class FileUtilTest {

    @Mock
    private File file;

    private String testFilePath;

    @BeforeEach
    public void init() {
        testFilePath = "src/test/resources/init_file/scene_builder_test_file.txt";
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsValidTxtFile(){
        when(file.getName()).thenReturn("myFile.txt");
        when(file.getPath()).thenReturn("files/myFile.txt");
        when(file.isAbsolute()).thenReturn(Boolean.FALSE);
        when(file.isDirectory()).thenReturn(Boolean.FALSE);
        when(file.isFile()).thenReturn(Boolean.TRUE);
        Assertions.assertTrue(FileUtil.isValid(file, "txt"));
    }

    @Test
    public void testIsValidPdfFile(){
        when(file.getName()).thenReturn("my_file.pdf");
        when(file.getPath()).thenReturn("files/my_file.pdf");
        when(file.isAbsolute()).thenReturn(Boolean.FALSE);
        when(file.isDirectory()).thenReturn(Boolean.FALSE);
        when(file.isFile()).thenReturn(Boolean.TRUE);
        Assertions.assertTrue(FileUtil.isValid(file, "pdf"));
    }

    @Test
    public void testReadSceneFile() throws FileNotValidException {
        List<String> lines = FileUtil.readSceneFile(testFilePath, "txt");
        Assertions.assertTrue(lines != null && lines.size() > 0);
    }

    @Test
    public void testReadSceneFileException() {
        when(file.getName()).thenReturn("my_file.pdf");
        when(file.getPath()).thenReturn("files/my_file.pdf");
        when(file.isAbsolute()).thenReturn(Boolean.FALSE);
        when(file.isDirectory()).thenReturn(Boolean.FALSE);
        when(file.isFile()).thenReturn(Boolean.TRUE);
        Assertions.assertThrows(FileNotValidException.class, () -> FileUtil.readSceneFile(file.getPath(), "txt"));
    }

    @Test
    public void testReadSceneFileContent() throws FileNotValidException {
        List<String> lines = FileUtil.readSceneFile(testFilePath, "txt");
        Assertions.assertEquals("5 5", lines.get(0));
        Assertions.assertEquals("0 0 N", lines.get(1));
        Assertions.assertEquals("ADA", lines.get(2));
        Assertions.assertEquals("0 0 S", lines.get(3));
        Assertions.assertEquals("GAAGAA", lines.get(4));
    }

    @Test
    public void testReadSceneFileContentInitialPositionLine() throws FileNotValidException {
        List<String> lines = FileUtil.readSceneFile(testFilePath, "txt");
        Assertions.assertEquals( "5 5", lines.get(0));
    }

    @Test
    public void testReadSceneFileContentPositionLine() throws FileNotValidException {
        List<String> lines = FileUtil.readSceneFile(testFilePath, "txt");
        Assertions.assertEquals( "0 0 N", lines.get(1));
    }

    @Test
    public void testReadSceneFileContentCommandLine() throws FileNotValidException {
        List<String> lines = FileUtil.readSceneFile(testFilePath, "txt");
        Assertions.assertEquals( "ADA", lines.get(2));
    }

}
