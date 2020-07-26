package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

// A class to test the methods implemented in Planet
class PlanetTest {
    private Planet testPlanet;
    private String testPlanetName = "test";
    private Color testPlanetColor = Color.red;

    PlanetTest() {

    }

    @BeforeEach
    public void setup() {
        testPlanet = new Planet(0,0,1000,1000,"test", testPlanetColor);
    }

    @Test
    public void testGetName() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet.getName(), testPlanetName);
    }

    @Test
    public void testGetXPosition() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet.getXPosition(), 0);
    }

    @Test
    public void testGetYPosition() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet.getYPosition(), 0);
    }

    @Test
    public void testGetXVelocity() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet.getXVelocity(), 1000);
    }

    @Test
    public void testGetYVelocity() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet.getYVelocity(), 1000);
    }

    @Test
    // TODO!!
    public void testUpdatePosition() {
        // setup is already complete

        // call method to test
        testPlanet.updatePosition(0, 0, 1000, 1000);

        // check outcome


    }
}