package ui;

import model.SolarSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;

import static ui.GravityApp.draw;

// The panel in which the simulation is rendered
public class SimPanel extends JPanel {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    private GravityApp gravityApp;
    private JFrame frame;

    // Constructs a panel to run the application
    // EFFECTS: sets size and background color of panel, adds this to a new frame
    public SimPanel() {
        frame = new JFrame("Gravity");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setVisible(true);
        frame.add(this);
        frame.pack();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setVisible(true);
        //this.gravityApp = gravityApp;
    }

    // MODIFIES: g
    // EFFECTS: assigns Graphics, calls helper method to render planets
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGravity(g);
    }

    // MODIFIES: g
    // EFFECTS: the gravity app is drawn onto the Graphics object g
    private void drawGravity(Graphics g) {
        draw(g);
    }
}
