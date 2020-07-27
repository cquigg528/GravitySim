package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static model.SolarSystem.gravitationalConstant;
import static org.junit.jupiter.api.Assertions.*;

// A class to test the methods implemented in Planet
class PlanetTest {
    public Planet testPlanet1;
    public Planet testPlanet2;
    public String testPlanetName1 = "test1";
    public String testPlanetName2 = "test2";
    public Color testPlanetColor = Color.red;
    ;

    PlanetTest() {

    }

    @BeforeEach
    public void setup() {
        testPlanet1 = new Planet((6 * Math.pow(10, 24)), 0, 0, 1000, 1000, testPlanetName1, testPlanetColor);
        testPlanet2 = new Planet((6 * Math.pow(10, 12)), 50, 50, 500, 500, testPlanetName2, testPlanetColor);
    }

    @Test
    public void testGetName() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet1.getName(), testPlanetName1);
        assertEquals(testPlanet2.getName(), testPlanetName2);
    }

    @Test
    public void testGetXPosition() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet1.getXPosition(), 0);
        assertEquals(testPlanet2.getXPosition(), 50);

    }

    @Test
    public void testGetYPosition() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet1.getYPosition(), 0);
        assertEquals(testPlanet2.getYPosition(), 50);

    }

    @Test
    public void testSeparation() {
        assertEquals(testPlanet1.separation(testPlanet2), Math.sqrt(5000));
    }

    @Test
    public void testGetXVelocity() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet1.getXVelocity(), 1000);
        assertEquals(testPlanet2.getXVelocity(), 500);

    }

    @Test
    public void testGetYVelocity() {
        // setup is already complete

        // call method to test, check outcome
        assertEquals(testPlanet1.getYVelocity(), 1000);
        assertEquals(testPlanet2.getYVelocity(), 500);

    }

    @Test
    public void testUpdateForce() {
        // setup is already complete

        // test system with two planets
        // calculate expected
        double radius = testPlanet1.separation(testPlanet2);
        double massProduct = testPlanet1.getMass() * testPlanet2.getMass();
        double rx = testPlanet2.getXPosition() - testPlanet1.getXPosition();
        double ry = testPlanet2.getYPosition() - testPlanet1.getYPosition();
        double xforce = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * rx;
        double yforce = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * ry;

        // call method to test
        testPlanet1.updateForce(testPlanet2);

        // check outcome
        assertEquals(testPlanet1.getXForce(), xforce);
        assertEquals(testPlanet1.getYForce(), yforce);
    }

    @Test
    public void testUpdateVelocity() {
        // setup is already complete

        // test outcome with new planet, no net force
        testPlanet1.updateVelocity();

        assertEquals(testPlanet1.getXVelocity(), 1000);
        assertEquals(testPlanet1.getYVelocity(), 1000);

        // test system with two planets
        // calculate expected
        double radius = testPlanet1.separation(testPlanet2);
        double massProduct = testPlanet1.getMass() * testPlanet2.getMass();
        double rx = testPlanet2.getXPosition() - testPlanet1.getXPosition();
        double ry = testPlanet2.getYPosition() - testPlanet1.getYPosition();
        double xforce = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * rx;
        double yforce = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * ry;
        testPlanet1.updateForce(testPlanet2);

        // call method to test
        testPlanet1.updateVelocity();

        // check outcome
        assertEquals(testPlanet1.getXVelocity(), 1000 + (xforce / testPlanet1.getMass()));
        assertEquals(testPlanet1.getYVelocity(), 1000 + (yforce / testPlanet1.getMass()));
    }

    @Test
    public void testUpdatePosition() {
        // setup is already complete

        // now check correct outcome after velocity updates
        testPlanet1.updateForce(testPlanet2);
        testPlanet1.updateVelocity();
        testPlanet1.updatePosition();

        assertEquals(testPlanet1.getXPosition(), 0 + testPlanet1.getXVelocity());
        assertEquals(testPlanet1.getYPosition(), 0 + testPlanet1.getYVelocity());
    }
}