package model;


import java.util.ArrayList;
import java.util.List;

// Represents a collection of Planets that comprise a solar system
public class SolarSystem {


    public static ArrayList<Planet> solarSystem;

    // constructor
    // EFFECTS: creates new empty solar system
    public SolarSystem() {
        solarSystem = new ArrayList<>();
    }

    // EFFECTS: returns number of planets in solar system
    public int getNumPlanets() {
        return solarSystem.size();
    }

    // MODIFIES: this
    // EFFECTS: adds planet to system
    public void addPlanet(Planet planet) {
        solarSystem.add(planet);
    }

    // REQUIRES: non-empty solar system
    // EFFECTS: returns name of Planet at index
    public String getPlanetName(int index) {
        return solarSystem.get(index).getName();
    }

    // REQUIRES: non-empty solar system
    // EFFECTS: returns Planet at index
    public Planet getPlanet(int index) {
        return solarSystem.get(index);
    }

    // MODIFIES: this
    // EFFECTS: updates force components for all bodies in SolarSystem
    public void updateForces() {
        for (Planet body1 : solarSystem) {
            for (Planet body2 : solarSystem) {
                if (!body1.equals(body2)) {
                    body1.updateForce(body2);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: updates velocity components for all bodies in SolarSystem
    public void updateVelocities() {
        for (Planet body : solarSystem) {
            body.updateVelocity();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates position coordinates for all bodies in SolarSystem
    public void updatePositions() {
        for (Planet body : solarSystem) {
            body.updatePosition();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates orbital parameters for all planets in the solar system in correct order
    public void move() {
        updateForces();
        updateVelocities();
        updatePositions();
    }

    // MODIFIES: this
    // EFFECTS: removes planet with matching name from solar system
    public static void removePlanet(String name) {
        List<Planet> removedList = new ArrayList<>();
        for (Planet planet : solarSystem) {
            if (name.equals(planet.getName())) {
                removedList.add(planet);
            }
        }
        solarSystem.removeAll(removedList);
    }
}
