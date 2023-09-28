package test;

import org.junit.Test;
import utils.FileUtil;

import java.io.File;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void outputFile() {
    }

    @Test
    public void correctingOutput() {
        FileUtil.CorrectingOutput(new File("src/output/Exercises.txt"), new File("src/output/Answers.txt"));
    }
}