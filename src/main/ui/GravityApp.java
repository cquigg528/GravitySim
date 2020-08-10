package ui;

import model.Planet;
import model.SolarSystem;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

// Console interaction for Gravity application
public class GravityApp extends JFrame {
    private static final String SYSTEMS_FILE = "./data/systems.txt";
    private static final int INTERVAL_RUN = 20;            // interval between updated calculations
    protected static SolarSystem solarSystem;

    private static SimPanel simPanel;
    private MenuPanel menu;
    private static Timer timer;

    // constructor
    // EFFECTS: runs gravity application
    public GravityApp() {
        super("Gravity Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setLocationRelativeTo(null);
        //simPanel = new SimPanel(this);
        menu = new MenuPanel(this);
        //add(simPanel);
        add(menu, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }

    public static void draw(Graphics g) {
        int numLoops = solarSystem.getNumPlanets();
        for (int i = 0; i < numLoops; i++) {
            solarSystem.getPlanet(i).drawPlanet(g);
        }
    }

    protected static void save() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        int i = 0;
        try {
            Writer writer = new Writer(new File(SYSTEMS_FILE));
            while (i < solarSystem.getNumPlanets()) {
                Planet planet = solarSystem.getPlanet(i);
                writer.write(planet);
                i++;
            }
            writer.close();
            panel.add(new JLabel("System saved to " + SYSTEMS_FILE));
            JOptionPane.showMessageDialog(frame, panel);
        } catch (FileNotFoundException e) {
            panel.add(new JLabel("Unable to save to " + SYSTEMS_FILE));
            JOptionPane.showMessageDialog(frame, panel);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads solar systems from SYSTEMS_FILE, if that file exists,
    // otherwise only loaded system will be Centauri, which is hard coded
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
    // EFFECTS: evolves system and prints changing parameters to the screen for INTERVAL_STOP ms
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
