package model;

import java.awt.*;

// Represents a planet having a 2D position, velocity, name and color
public class Planet {
    public int mass;           // planet mass[kg]
    public double xpos;             // x position[meters]
    public double ypos;             // y position[meters]
    public double xvel;             // x velocity[meters / second]
    public double yvel;             // y velocity[meters / second]
    public double netForceX;            // net force felt by planet in x direction[N]
    public double netForceY;            // net force felt by planet in y direction[N]
    public String name;
    public Color color;

    public double deltaT = 60;       // time step [seconds]
    public double coefficient = centralMass * gravitationalConstant;



    // constructor
    /* EFFECTS: creates a new planet at given position with given velocity, with color and name
                and zero initial net force
    */
    public Planet(double x, double y, double xv, double yv, String name, Color color) {
        this.xpos = x;
        this.ypos = y;
        this.xvel = xv;
        this.yvel = yv;
        this.name = name;
        this.color = color;
        this.netForceX = 0;
        this.netForceY = netForceX;
    }

    // TODO !!
    // EFFECTS: returns planet name
    public String getName() {
        return "FINISH!"; // stub
    }

    // TODO!!
    // EFFECTS: returns x position coordinate
    public double getXPosition() {
        return 0; //stub
    }

    // TODO!!
    // EFFECTS: returns y position coordinate
    public double getYPosition() {
        return 0; //stub
    }

    // TODO !!
    // EFFECTS: returns x component of velocity
    public double getXVelocity() {
        return 0; // stub
    }

    // TODO !!
    // EFFECTS: returns y component of velocity
    public double getYVelocity() {
        return 0; // stub
    }

    // TODO !!
    // EFFECTS: calculates and returns orbital period in years
    public double getPeriod() {
        return 0; // stub
    }

    // TODO !!
    // MODIFIES: this
    // EFFECTS: updates planet position coordinates
    public void updatePosition(double x, double y, double xv, double yv) {
        // stub
    }

    // TODO !!
    // MODIFIES: this
    // EFFECTS: updates planet velocity components
    public void updateVelocity(double xv, double yv, double dxv, double dyv) {
        // stub
    }
}
