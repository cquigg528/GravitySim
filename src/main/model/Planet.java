package model;

import java.awt.*;
import java.util.ArrayList;

import static model.SolarSystem.centralMass;
import static model.SolarSystem.gravitationalConstant;

// Represents a planet having a 2D position, velocity, name and color
public class Planet {
    public double mass;             // planet mass[kg]
    public double xpos;             // x position[meters]
    public double ypos;             // y position[meters]
    public double xvel;             // x velocity[meters / second]
    public double yvel;             // y velocity[meters / second]
    public double netForceX;        // net force felt by planet in x direction[N]
    public double netForceY;        // net force felt by planet in y direction[N]
    public String name;
    public Color color;

    public double deltaT = 60;       // time step [seconds]
    public static double coefficient = centralMass * gravitationalConstant;


    // constructor
    /* EFFECTS: creates a new planet at given position with given velocity, with color and name
                and zero initial net force
    */
    public Planet(double m, double x, double y, double xv, double yv, String name, Color color) {
        this.mass = m;
        this.xpos = x;
        this.ypos = y;
        this.xvel = xv;
        this.yvel = yv;
        this.name = name;
        this.color = color;
        this.netForceX = 0;
        this.netForceY = netForceX;
    }

    // EFFECTS: returns planet name
    public String getName() {
        return name;
    }

    // EFFECTS: returns planet mass
    public double getMass() {
        return mass;
    }

    // EFFECTS: returns x position coordinate
    public double getXPosition() {
        return xpos;
    }

    // EFFECTS: returns y position coordinate
    public double getYPosition() {
        return ypos;
    }

    // EFFECTS: returns magnitude of separation vector with another planet
    public double separation(Planet p) {
        double deltaX = xpos - p.getXPosition();
        double deltaY = ypos - p.getYPosition();
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    // EFFECTS: returns x component of velocity
    public double getXVelocity() {
        return xvel;
    }

    // EFFECTS: returns y component of velocity
    public double getYVelocity() {
        return yvel;
    }

    // EFFECTS: returns x component of net force on the planet
    public double getXForce() {
        return netForceX;
    }

    // EFFECTS: returns y component of net force on the planet
    public double getYForce() {
        return netForceY;
    }

    // REQUIRES: body is not null
    // MODIFIES: this
    // EFFECTS: calculates net force components resulting from other planets in the solar system
    public void updateForce(Planet body) {
        double radius = this.separation(body);
        double massProduct = this.getMass() * body.getMass();
        double radiusXComponent = body.getXPosition() - this.getXPosition();
        double radiusYComponent = body.getYPosition() - this.getYPosition();
        double fx = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * radiusXComponent;
        double fy = gravitationalConstant * (massProduct / Math.pow(radius, 2)) * radiusYComponent;
        this.netForceX += fx;
        this.netForceY += fy;
    }

    // MODIFIES: this
    // EFFECTS: updates planet velocity components
    public void updateVelocity() {
        this.xvel += this.netForceX / this.mass;
        this.yvel += this.netForceY / this.mass;
    }

    // MODIFIES: this
    // EFFECTS: updates planet position coordinates
    public void updatePosition() {
        this.xpos += this.xvel;
        this.ypos += this.yvel;
    }
}
