package ui;

import model.Planet;
import model.SolarSystem;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

// Solar system simulator GUI
public class GravityApp extends JFrame {
    protected static final String SYSTEMS_FILE = "./data/systems.txt";
    private static final int INTERVAL_RUN = 20;            // interval between updated calculations
    protected static SolarSystem solarSystem;

    private static SimPanel simPanel;
    private static Timer timer;
    private static SaveSystemPanel saveSystemPanel;
    private MenuPanel menu;


    // constructor
    // EFFECTS: creates frame with MenuPanel
    public GravityApp() {
        super("Gravity Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setLocationRelativeTo(null);
        menu = new MenuPanel(this);
        add(menu, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }

    // MODIFIES: g
    // EFFECTS: draws each planet onto g
    public static void draw(Graphics g) {
        int numLoops = solarSystem.getNumPlanets();
        for (int i = 0; i < numLoops; i++) {
            Color savedCol = g.getColor();
            g.setColor(solarSystem.getPlanet(i).getColor());
            g.fillOval((int)solarSystem.getPlanet(i).getXPosition(),
                    (int)solarSystem.getPlanet(i).getYPosition(),
                    15,
                    15);
            g.setColor(savedCol);
        }
    }

    // EFFECTS: saves all planets in solarSystem to systems.txt
    protected static void showSaveSystemPanel() {
        saveSystemPanel = new SaveSystemPanel();
    }

    // MODIFIES: this
    // EFFECTS: loads solar systems from SYSTEMS_FILE, if that file exists
    protected static void loadSystemsFromFile() {
        try {
            List<Planet> planets = Reader.readPlanets(new File(SYSTEMS_FILE));
            solarSystem = new SolarSystem();
            for (Planet planet : planets) {
                solarSystem.addPlanet(planet);
            }
        } catch (IOException e) {
            System.out.println(SYSTEMS_FILE + " not found");
        }
    }


    // MODIFIES: Planet object and this
    // EFFECTS: adds a user-defined Planet to this
    protected static void addAPlanet(Planet planet) {
        if (solarSystem == null) {
            solarSystem = new SolarSystem();
        }
        solarSystem.addPlanet(planet);
    }

    // EFFECTS: returns a Color that matches user input, or default to magenta
    protected static Color handleColor(String color) {
        if (color.equals("blue")) {
            return Color.blue;
        } else if (color.equals("gray")) {
            return Color.gray;
        } else if (color.equals("green")) {
            return Color.green;
        } else if (color.equals("orange")) {
            return Color.orange;
        } else if (color.equals("pink")) {
            return Color.pink;
        } else if (color.equals("red")) {
            return Color.red;
        } else if (color.equals("white")) {
            return Color.white;
        } else if (color.equals("yellow")) {
            return Color.yellow;
        } else {
            return Color.magenta;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads this with 3 planets
    protected static void loadCentauriSystem() {
        solarSystem = new SolarSystem();
        Planet centauriA
                = new Planet((2.2 * Math.pow(10, 8)),
                400, 400, 0, 0, "Centauri A", Color.yellow);
        Planet centauriB
                = new Planet((0.5 * Math.pow(10, 10)),
                500, 600, 0, 0, "Centauri b", Color.blue);
        Planet proximaCentauri
                = new Planet((0.1 * Math.pow(10, 5)),
                800, 150, 0, 0, "Proxima Centauri", Color.pink);

        solarSystem.addPlanet(centauriA);
        solarSystem.addPlanet(centauriB);
        solarSystem.addPlanet(proximaCentauri);
    }

    // MODIFIES: this
    // EFFECTS: updates system and draws graphics at INTERVAL_RUN intervals
    public static void evolve() {
        simPanel = new SimPanel();
        timer = new Timer(INTERVAL_RUN, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                solarSystem.move();
                simPanel.repaint();
            }
        }
        );
        timer.start();
    }
}
