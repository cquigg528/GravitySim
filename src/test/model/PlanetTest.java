package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;
import java.util.ArrayList;

import static model.Planet.coefficient;
import static model.SolarSystem.gravitationalConstant;
import static org.junit.jupiter.api.Assertions.*;

// A class to test the methods implemented in Planet
class PlanetTest {
    private Planet testPlanet1;
    private Planet testPlanet2;
    private String testPlanetName1 = "test1";
    private String testPlanetName2 = "test2";
    private Color testPlanetColor = Color.red;
    private ArrayList<Planet> testSystem0 = new ArrayList<>();
    private ArrayList<Planet> testSystem = new ArrayList<>();

    PlanetTest() {

    }

    @BeforeEach
    public void setup() {
        testPlanet1 = new Planet((6 * Math.pow(10, 24)),0,0, 1000,1000, testPlanetName1, testPlanetColor);
        testPlanet2 = new Planet((6 * Math.pow(10, 12)),50,50,500,500, testPlanetName2, testPlanetColor);
        testSystem.add(testPlanet1);
        testSystem.add(testPlanet2);

        // one planet only
        testSystem0.add(testPlanet1);
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

        // test a system with only one planet
        ArrayList<Planet> testSystem0 = new ArrayList<>();
        testSystem0.add(testPlanet1);
        testPlanet1.updateForce(testSystem0);
        assertEquals(testPlanet1.getXForce(), 0);
        assertEquals(testPlanet1.getYForce(), 0);

        // test system with two planets
        // calculate expected
        double radius = testPlanet1.separation(testPlanet2);
        double massProduct = testPlanet1.getMass() * testPlanet2.getMass();
        double rx = testPlanet2.getXPosition() - testPlanet1.getXPosition();
        double ry = testPlanet2.getYPosition() - testPlanet1.getYPosition();
        double xforce = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * rx;
        double yforce = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * ry;

        // call method to test
        testPlanet1.updateForce(testSystem);

        // check outcome
        assertEquals(testPlanet1.getXForce(), xforce);
        assertEquals(testPlanet1.getYForce(), yforce);

        // repeat above but with a new planet added, call on planet 2
        Planet testPlanet3
                = new Planet((5 * Math.pow(10, 8)), 250, 850, 90, 10000, "test3", testPlanetColor);
        testSystem.add(testPlanet3);

        double radius2 = testPlanet2.separation(testPlanet3);
        double massProduct2 = testPlanet2.getMass() * testPlanet3.getMass();
        double rx2 = testPlanet3.getXPosition() - testPlanet2.getXPosition();
        double ry2 = testPlanet3.getYPosition() - testPlanet2.getYPosition();
        double xforce2 = gravitationalConstant * (massProduct2 / Math.pow(radius2, 2)) * rx2;
        double yforce2 = gravitationalConstant * (massProduct2 / Math.pow(radius2, 2)) * ry2;

        // call method to test
        testPlanet2.updateForce(testSystem);

        // check outcome, note the direction vectors from first calculation will be reversed
        assertEquals(testPlanet2.getXForce(), xforce2 - xforce);
        assertEquals(testPlanet2.getYForce(), yforce2 - yforce);
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
        testPlanet1.updateForce(testSystem);

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
        testPlanet1.updateForce(testSystem);
        testPlanet1.updateVelocity();
        testPlanet1.updatePosition();

        assertEquals(testPlanet1.getXPosition(), 0 + testPlanet1.getXVelocity());
        assertEquals(testPlanet1.getYPosition(), 0 + testPlanet1.getYVelocity());
    }
}