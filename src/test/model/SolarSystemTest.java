package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

// A class to test the methods implemented in SolarSystem
public class SolarSystemTest {
    private static final double DELTA = 0;  // acceptable error due to calculations with doubles
    private Planet testPlanet1separate;
    private Planet testPlanet2separate;
    private Planet testPlanet3separate;
    private Planet testPlanet1;
    private Planet testPlanet2;
    private Planet testPlanet3;
    private SolarSystem testSolarSystem;

    SolarSystemTest() {
    }

    @BeforeEach
    public void setup() {
        // establish some identical planets to test SolarSystem methods against
        testPlanet1separate
                = new Planet((2 * Math.pow(10, 8)), 200, 200, 800, 500, "test1", Color.blue);
        testPlanet2separate
                = new Planet((3 * Math.pow(10, 8)), 500, 100, 80, 50, "test2", Color.red);
        testPlanet3separate
                = new Planet((6 * Math.pow(10, 8)), 900, 50, 8, 5, "test3", Color.green);

        testPlanet1 = new Planet((2 * Math.pow(10, 8)), 200, 200, 800, 500, "test1", Color.blue);
        testPlanet2 = new Planet((3 * Math.pow(10, 8)), 500, 100, 80, 50, "test2", Color.red);
        testPlanet3 = new Planet((6 * Math.pow(10, 8)), 900, 50, 8, 5, "test3", Color.green);
        testSolarSystem = new SolarSystem();
        testSolarSystem.addPlanet(testPlanet1);
        testSolarSystem.addPlanet(testPlanet2);
        testSolarSystem.addPlanet(testPlanet3);
    }

    @Test
    public void testGetNumPlanets() {
        assertEquals(3, testSolarSystem.getNumPlanets());
        SolarSystem emptySolarSystem = new SolarSystem();
        assertEquals(0, emptySolarSystem.getNumPlanets());
    }

    @Test
    public void testAddPlanet() {
        Planet testPlanet = new Planet(500, 100, 100, 100, 100, "test", Color.black);

        // call method to test and check outcome
        testSolarSystem.addPlanet(testPlanet);
        assertEquals(testSolarSystem.getPlanetName(3), "test");
    }

    @Test
    public void testGetPlanetName() {
        // call method to test and check outcome
        String test1 = testSolarSystem.getPlanetName(0);
        String test3 = testSolarSystem.getPlanetName(2);
        assertEquals(test1, "test1");
        assertEquals(test3, "test3");
    }

    @Test
    public void testGetPlanet() {
        assertEquals(testPlanet1, testSolarSystem.getPlanet(0));
        assertEquals(testPlanet2, testSolarSystem.getPlanet(1));
        assertEquals(testPlanet3, testSolarSystem.getPlanet(2));
    }

    @Test
    public void testUpdateForces() {
        // calculate all force interactions in the test system individually on identical Planets
        testPlanet1separate.updateForce(testPlanet2separate);
        testPlanet1separate.updateForce(testPlanet3separate);

        testPlanet2separate.updateForce(testPlanet1separate);
        testPlanet2separate.updateForce(testPlanet3separate);

        testPlanet3separate.updateForce(testPlanet1separate);
        testPlanet3separate.updateForce(testPlanet2separate);

        // call method to test
        testSolarSystem.updateForces();

        // check outcomes
        assertEquals(testPlanet1.getXForce(), testPlanet1separate.getXForce());
        assertEquals(testPlanet1.getYForce(), testPlanet1separate.getYForce());

        assertEquals(testPlanet2.getXForce(), testPlanet2separate.getXForce());
        assertEquals(testPlanet2.getYForce(), testPlanet2separate.getYForce());

        assertEquals(testPlanet3.getXForce(), testPlanet3separate.getXForce());
        assertEquals(testPlanet3.getYForce(), testPlanet3separate.getYForce());
    }

    @Test
    public void testUpdateVelocities() {
        // complete setup
        testPlanet1separate.updateForce(testPlanet2separate);
        testPlanet1separate.updateForce(testPlanet3separate);

        testPlanet2separate.updateForce(testPlanet1separate);
        testPlanet2separate.updateForce(testPlanet3separate);

        testPlanet3separate.updateForce(testPlanet1separate);
        testPlanet3separate.updateForce(testPlanet2separate);

        // update velocities individually to test against
        testPlanet1separate.updateVelocity();
        testPlanet2separate.updateVelocity();
        testPlanet3separate.updateVelocity();

        // call method to test
        testSolarSystem.updateForces();
        testSolarSystem.updateVelocities();

        // verify outcomes
        assertEquals(testPlanet1.getXVelocity(), testPlanet1separate.getXVelocity(), DELTA);
        assertEquals(testPlanet1.getYVelocity(), testPlanet1separate.getYVelocity(), DELTA);

        assertEquals(testPlanet2.getXVelocity(), testPlanet2separate.getXVelocity(), DELTA);
        assertEquals(testPlanet2.getYVelocity(), testPlanet2separate.getYVelocity(), DELTA);

        assertEquals(testPlanet3.getXVelocity(), testPlanet3separate.getXVelocity(), DELTA);
        assertEquals(testPlanet3.getYVelocity(), testPlanet3separate.getYVelocity(), DELTA);
    }

    @Test
    public void testUpdatePositions() {
        // complete setup
        testPlanet1separate.updateForce(testPlanet2separate);
        testPlanet1separate.updateForce(testPlanet3separate);

        testPlanet2separate.updateForce(testPlanet1separate);
        testPlanet2separate.updateForce(testPlanet3separate);

        testPlanet3separate.updateForce(testPlanet1separate);
        testPlanet3separate.updateForce(testPlanet2separate);

        testPlanet1separate.updateVelocity();
        testPlanet2separate.updateVelocity();
        testPlanet3separate.updateVelocity();

        // update positions individually to test against
        testPlanet1separate.updatePosition();
        testPlanet2separate.updatePosition();
        testPlanet3separate.updatePosition();

        // call method to test
        testSolarSystem.updateForces();
        testSolarSystem.updateVelocities();
        testSolarSystem.updatePositions();

        // verify outcomes
        assertEquals(testPlanet1.getXPosition(), testPlanet1separate.getXPosition(), DELTA);
        assertEquals(testPlanet1.getYPosition(), testPlanet1separate.getYPosition(), DELTA);

        assertEquals(testPlanet2.getXPosition(), testPlanet2separate.getXPosition(), DELTA);
        assertEquals(testPlanet2.getYPosition(), testPlanet2separate.getYPosition(), DELTA);

        assertEquals(testPlanet3.getXPosition(), testPlanet3separate.getXPosition(), DELTA);
        assertEquals(testPlanet3.getYPosition(), testPlanet3separate.getYPosition(), DELTA);
    }

    @Test
    public void testRemovePlanet() {
        assertTrue(testSolarSystem.getNumPlanets() == 3);

        // check only removes right name
        testSolarSystem.removePlanet("tes2");
        assertTrue(testSolarSystem.getNumPlanets() == 3);

        // check removes one
        testSolarSystem.removePlanet("test1");
        assertTrue(testSolarSystem.getNumPlanets() == 2);

        // check removes another, and right name
        testSolarSystem.removePlanet("test2");
        assertTrue(testSolarSystem.getNumPlanets() == 1);

        assertTrue(testSolarSystem.getPlanetName(0) == "test3");
    }

    @Test
    public void testMove() {
        // move separate but identical planets to test against
        testPlanet1separate.updateForce(testPlanet2separate);
        testPlanet1separate.updateForce(testPlanet3separate);

        testPlanet2separate.updateForce(testPlanet1separate);
        testPlanet2separate.updateForce(testPlanet3separate);

        testPlanet3separate.updateForce(testPlanet1separate);
        testPlanet3separate.updateForce(testPlanet2separate);

        testPlanet1separate.updateVelocity();
        testPlanet2separate.updateVelocity();
        testPlanet3separate.updateVelocity();

        // update positions individually to test against
        testPlanet1separate.updatePosition();
        testPlanet2separate.updatePosition();
        testPlanet3separate.updatePosition();

        // call method to test
        testSolarSystem.move();

        // verify outcomes
        assertEquals(testPlanet1.getXPosition(), testPlanet1separate.getXPosition(), DELTA);
        assertEquals(testPlanet1.getYPosition(), testPlanet1separate.getYPosition(), DELTA);

        assertEquals(testPlanet2.getXPosition(), testPlanet2separate.getXPosition(), DELTA);
        assertEquals(testPlanet2.getYPosition(), testPlanet2separate.getYPosition(), DELTA);

        assertEquals(testPlanet3.getXPosition(), testPlanet3separate.getXPosition(), DELTA);
        assertEquals(testPlanet3.getYPosition(), testPlanet3separate.getYPosition(), DELTA);


    }
}
