package persistence;

import model.Planet;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testParsePlanetsFile1() {
        try {
            List<Planet> planets = Reader.readPlanets(new File("./data/testPlanetFile1.txt"));
            Planet testPlanet = planets.get(0);
            assertEquals(4, testPlanet.getMass());
            assertEquals(2, testPlanet.getXPosition());
            assertEquals(1, testPlanet.getYPosition());
            assertEquals(2, testPlanet.getXVelocity());
            assertEquals(1, testPlanet.getYVelocity());
            assertEquals(4, testPlanet.getXForce());
            assertEquals(4, testPlanet.getYForce());
            assertEquals("me", testPlanet.getName());
            Color testColor = new Color(255, 0, 0);
            assertEquals(testColor, testPlanet.getColor());
        } catch (IOException e) {
            fail("IOException should not be thrown");
        }
    }
}
