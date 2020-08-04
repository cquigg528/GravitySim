package persistence;

import model.Planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./Gravity/data/testAccounts.txt";
    private Writer testWriter;
    private Planet testPlanet1;
    private Planet testPlanet2;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        testPlanet1 = new Planet(400, 2, 1, 32.2, 43.2,
                23, 27, "geg", Color.blue);
        testPlanet2 = new Planet(0.1, 9, 10, 0.001, 232,
                400, 12, "gp", Color.black);
        testWriter.write(testPlanet1);
        testWriter.write(testPlanet2);
        testWriter.close();
    }

    @Test
    void testWritePlanet1() {
        try {
            List<Planet> planets = Reader.readPlanets(new File(TEST_FILE));
            Planet testPlanet1 = planets.get(0);
            assertEquals(400, testPlanet1.getMass());
            assertEquals(2, testPlanet1.getXPosition());
            assertEquals(1, testPlanet1.getYPosition());
            assertEquals(32.2, testPlanet1.getXVelocity());
            assertEquals(43.2, testPlanet1.getYVelocity());
            assertEquals(23, testPlanet1.getXForce());
            assertEquals(27, testPlanet1.getYForce());
            assertEquals("geg", testPlanet1.getName());
            Color testColor1 = new Color(0, 0, 255);
            assertEquals(testColor1, testPlanet1.getColor());

        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }

    @Test
    void testWritePlanet2() {
        testWriter.write(testPlanet2);
        testWriter.close();

        try {
            List<Planet> planets = Reader.readPlanets(new File(TEST_FILE));
            Planet testPlanet2 = planets.get(1);
            assertEquals(0.1, testPlanet2.getMass());
            assertEquals(9, testPlanet2.getXPosition());
            assertEquals(10, testPlanet2.getYPosition());
            assertEquals(0.001, testPlanet2.getXVelocity());
            assertEquals(232, testPlanet2.getYVelocity());
            assertEquals(400, testPlanet2.getXForce());
            assertEquals(12, testPlanet2.getYForce());
            assertEquals("gp", testPlanet2.getName());
            Color testColor2 = new Color(0, 0, 0);
            assertEquals(testColor2, testPlanet2.getColor());

        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }
}
