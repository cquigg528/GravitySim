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
import java.util.Scanner;

// Console interaction for Gravity application
public class GravityApp extends JFrame {
    private static final String SYSTEMS_FILE = "./data/systems.txt";
    private static final int INTERVAL_RUN = 20;            // interval between updated calculations
    private Scanner input;
    protected static SolarSystem solarSystem;

    private SimPanel simPanel;
    private MenuPanel menu;
    private Timer timer;

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

        //runGravity();
    }

    // MODIFIES: this and a Planet object
    // EFFECTS: processes user input
//    private void runGravity() {
//        boolean keepGoing = true;
//        String command = null;
//        input = new Scanner(System.in);
//
//        loadSystemsFromFile();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("quit")) {
//                promptSaveQuery();
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\n See you later!");
//    }

    public void draw(Graphics g) {
        int numLoops = solarSystem.getNumPlanets();
        for (int i = 0; i < numLoops; i++) {
            solarSystem.getPlanet(i).drawPlanet(g);
        }
    }

    // EFFECTS: saves state of solar system to SYSTEMS_FILE
//    private void promptSaveQuery() {
//        System.out.println("\n Would you like to save the solar system you created? [y/n]");
//        String command = input.next();
//        command = command.toLowerCase();
//
//        if (command.equals("y")) {
//            save();
//        }
//    }

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
    protected void loadSystemsFromFile() {
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

    // EFFECTS: prints menu options to the console
//    private void displayMenu() {
//        System.out.println("\n Welcome to the Solar System Simulator!");
//        System.out.println("\n Please select from the following options:");
//        System.out.println("\t new -> create a new solar system");
//        System.out.println("\t load -> load a saved solar system");
//        System.out.println("\t quit -> quit application");
//    }

    // MODIFIES: this and a Planet object
    // EFFECTS: either directs the user to make a new body for the solar system, or load a system stored in the program
//    private void processCommand(String command) {
//        if (command.equals("new")) {
//            newSystem();
//        } else if (command.equals("load")) {
//            loadSystem();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }

    // MODIFIES: this and Planet object
    // EFFECT: creates a new Planet and adds it to this, add as many as the user wishes
//    private void newSystem() {
//        boolean keepAdding = true;
//        solarSystem = new SolarSystem();
//
//        while (keepAdding) {
//            //String name = addAPlanet();
//            //System.out.println("\n " + name + " has been added to your Solar System!");
//            System.out.println("Would you like to add another celestial body? [y/n] ");
//            String addAnother = input.next();
//            addAnother = addAnother.toLowerCase();
//            if (addAnother.equals("n")) {
//                keepAdding = false;
//                handleSolarSystem();
//            }
//        }
//    }

    // MODIFIES: Planet object and this
    // EFFECTS: adds a user-defined Planet to this
    protected static void addAPlanet(Planet planet) {
//        // retrieve parameters
//        System.out.println("Enter body name:");
//        String name = input.next();
//        System.out.println("\n Enter mass [kg]: ");
//        double mass = input.nextDouble();
//        System.out.println("\n Enter initial x coordinate [m]: ");
//        double xpos = input.nextDouble();
//        System.out.println("\n Enter initial y coordinate [m]: ");
//        double ypos = input.nextDouble();
//        System.out.println("\n Enter initial x velocity [m/s]: ");
//        double xvel = input.nextDouble();
//        System.out.println("\n Enter initial y velocity [m/s]: ");
//        double yvel = input.nextDouble();
//        System.out.println("\n Enter desired color: ");
//        String color = input.next();
//        Color desiredColor = handleColor(color.toLowerCase());
//
//        // add planet to solar system
//        Planet planetToAdd = new Planet(mass, xpos, ypos, xvel, yvel, name, desiredColor);
        if (solarSystem == (null)) {
            solarSystem = new SolarSystem();
        }
        solarSystem.addPlanet(planet);
//        return name;
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
    // EFFECTS: loads solar system with Planets
//    private void loadSystem() {
//        System.out.println("\n Current solar systems on file:");
//        System.out.println("\t - Centauri \n");
//        System.out.println("\n Enter 'centauri' for the Centauri system, 'load' for the last saved system,"
//                + " or 'back' to return to main menu");
//        String command = input.next();
//        command = command.toLowerCase();
//        if (command.equals("load")) {
//            handleSolarSystem();
//        } else if (command.equals("centauri")) {
//            loadCentauriSystem();
//            handleSolarSystem();
//        } else if (command.equals("back")) {
//            runGravity();
//        }
//    }

    protected static void loadCentauriSystem() {
        solarSystem = new SolarSystem();
        Planet centauriA
                = new Planet((2.2 * Math.pow(10, 30)),
                200, 10, 10000, 10000, "Centauri A", Color.yellow);
        Planet centauriB
                = new Planet((0.5 * Math.pow(10, 30)),
                500, 600, -10000, -10000, "Centauri b", Color.blue);
        Planet proximaCentauri
                = new Planet((0.1 * Math.pow(10, 29)),
                300, 150, -1500, 8000, "Proxima Centauri", Color.pink);

        solarSystem.addPlanet(centauriA);
        solarSystem.addPlanet(centauriB);
        solarSystem.addPlanet(proximaCentauri);
    }

    // MODIFIES: this
    // EFFECTS: either prints Planets in system or evolves system
//    private void handleSolarSystem() {
//        System.out.println("\n What would you like to do with your Solar System?");
//        System.out.println("\t inspect -> View list of bodies in your system");
//        System.out.println("\t evolve -> Watch your system move");
//        System.out.println("\t back -> return to main menu");
//        String command = input.next();
//        command = command.toLowerCase();
//        if (command.equals("inspect")) {
//            printPlanetList();
//            handleSolarSystem();
//        } else if (command.equals("evolve")) {
//            evolve();
//            handleSolarSystem();
//        } else if (command.equals("back")) {
//            runGravity();
//        } else {
//            System.out.println("\n Command not recognized...");
//        }
//    }

    // EFFECTS: prints names of Planets in the solar system
//    public void printPlanetList() {
//        System.out.println("\n Your solar system contains:");
//        int numLoops = solarSystem.getNumPlanets();
//        for (int index = 0; index < numLoops; index++) {
//            String name = solarSystem.getPlanetName(index);
//            System.out.println("\t -" + name);
//        }
//    }

    // MODIFIES: this
    // EFFECTS: evolves system and prints changing parameters to the screen for INTERVAL_STOP ms
    public void evolve() {
        timer = new Timer(INTERVAL_RUN, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                solarSystem.updateForces();
                solarSystem.updateVelocities();
                solarSystem.updatePositions();
                simPanel.repaint();
            }
        }
        );
        timer.start();
    }

    // EFFECTS: prints Planet name, coordinates, and speed for all planets in solar system
//    public void printPlanetMotion() {
//        int numLoops = solarSystem.getNumPlanets();
//        for (int index = 0; index < numLoops; index++) {
//            String name = solarSystem.getPlanetName(index);
//            double xpos = solarSystem.getPlanet(index).getXPosition();
//            double ypos = solarSystem.getPlanet(index).getYPosition();
//            double velocityX = solarSystem.getPlanet(index).getXVelocity();
//            double velocityY = solarSystem.getPlanet(index).getYVelocity();
//            double velocity = Math.sqrt((Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
//            System.out.println(name + " Position (" + xpos + ", " + ypos + ")");
//            System.out.println("with speed " + velocity + " meters per second");
//        }
//    }
}
