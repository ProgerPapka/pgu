package controller;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class GeneratorTimestampTest {

    private static GeneratorTimestamp generator;

    @BeforeClass
    public static void init(){
        generator = new GeneratorTimestamp();
    }

    @Test
    public void generateTimestamp() {
        String time = generator.generateTimestamp();
        assertTrue(!time.isEmpty());
    }
}