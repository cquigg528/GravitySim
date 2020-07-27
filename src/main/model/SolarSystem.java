package model;


import java.awt.*;
import java.util.ArrayList;

// Represents a collection of Planets that comprise a solar system
public class SolarSystem {
    public static double centralMass = 1.98847 * Math.pow(10, 30);
    public static double gravitationalConstant = 6.67408 * Math.pow(10, -11);
    public ArrayList<Planet> solarSystem;

    // constructor
    // EFFECTS: creates new empty solar system
    public SolarSystem() {
        solarSystem = new ArrayList<>();
    }

    // TODO !!
    // MODIFIES: this
    // EFFECTS: adds planet to system
    public void addPlanet(Planet testPlanet2) {
        // stub
    }
}
