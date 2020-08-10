package ui;

import model.SolarSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;

// The panel in which the simulation is rendered
public class SimPanel extends JPanel {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    private GravityApp gravityApp;
    private JFrame frame;

    // Constructs a panel to run the application
    // EFFECTS: sets size and background color of panel,
    // updates this with the game to be displayed
    public SimPanel() {
        frame = new JFrame("Gravity");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setBackground(Color.black);
        frame.setVisible(true);
        //this.gravityApp = gravityApp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGravity(g);
    }

    // Draws the gravity app
    // MODIFIES: g
    // EFFECTS: the gravity app is drawn onto the Graphics object g
    private void drawGravity(Graphics g) {
        gravityApp.draw(g);
    }
}
