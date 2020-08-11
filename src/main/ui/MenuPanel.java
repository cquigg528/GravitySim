package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Planet;
import model.SolarSystem;

import static ui.GravityApp.*;

// Represents the panel in which the menu buttons are displayed
public class MenuPanel extends JPanel implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JTextField massField;
    private JTextField positionFieldX;
    private JTextField positionFieldY;
    private JTextField velocityFieldX;
    private JTextField velocityFieldY;
    private JTextField nameField;
    private JTextField colorField;
    private JButton newSystemButton;
    private JButton loadSystemFromFileButton;
    private JButton loadSystemFromProgramButton;
    private JButton evolveButton;
    private JButton inspectButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JPanel menuArea = new JPanel();
    private GravityApp gravityApp;


    // Constructor
    // EFFECTS: Adds all buttons and associated ActionListeners to menuPanel
    public MenuPanel(GravityApp gravityApp) {
        menuArea.setLayout(new FlowLayout());
        add(menuArea, BorderLayout.NORTH);
        newSystemButton = new JButton("new");
        menuArea.add(newSystemButton);
        newSystemButton.addActionListener(this);

        loadSystemFromFileButton = new JButton("load file");
        menuArea.add(loadSystemFromFileButton);
        loadSystemFromFileButton.addActionListener(this);

        loadSystemFromProgramButton = new JButton("load stored");
        menuArea.add(loadSystemFromProgramButton);
        loadSystemFromProgramButton.addActionListener(this);

        evolveButton = new JButton("evolve");
        menuArea.add(evolveButton);
        evolveButton.addActionListener(this);

        inspectButton = new JButton("inspect");
        menuArea.add(inspectButton);
        inspectButton.addActionListener(this);

        saveButton = new JButton("save");
        menuArea.add(saveButton);
        saveButton.addActionListener(this);

        deleteButton = new JButton("delete a planet");
        menuArea.add(deleteButton);
        deleteButton.addActionListener(this);

    }

    // MODIFIES: this
    // EFFECTS: determine how the menuPanel responds to a button press
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newSystemButton) {
            newSystem();
        } else if (e.getSource() == loadSystemFromFileButton) {
            loadFromFile();
        } else if (e.getSource() == loadSystemFromProgramButton) {
            loadStored();
        } else if (e.getSource() == evolveButton) {
            evolveSystem();
        } else if (e.getSource() == inspectButton) {
            showInspectSystemPanel();
        } else if (e.getSource() == saveButton) {
            saveSystem();
        } else if (e.getSource() == deleteButton) {
            deletePlanet();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new panel for user input, can add multiple planets to solar system
    private void newSystem() {
        if (gravityApp.solarSystem == null) {
            gravityApp.solarSystem = new SolarSystem();
        }

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 1, 1));

        panel = initializeFields(panel);

        int option = JOptionPane.showConfirmDialog(
                frame, panel, "Add a planet", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        double massInput = Double.parseDouble(massField.getText());
        double xposInput = Double.parseDouble(positionFieldY.getText());
        double yposInput = Double.parseDouble(positionFieldY.getText());
        double xvelInput = Double.parseDouble(velocityFieldY.getText());
        double yvelInput = Double.parseDouble(velocityFieldY.getText());
        String nameInput = nameField.getText();
        Color colorInput = gravityApp.handleColor(colorField.getText());


        gravityApp.addAPlanet(new Planet(massInput, xposInput, yposInput, xvelInput, yvelInput, nameInput, colorInput));

        if (option == JOptionPane.YES_OPTION) {
            newSystem();
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method to add fields and labels to the add planet window
    private JPanel initializeFields(JPanel panel) {
        massField = new JTextField(10);
        positionFieldX = new JTextField(4);
        positionFieldY = new JTextField(3);
        velocityFieldX = new JTextField(5);
        velocityFieldY = new JTextField(5);
        nameField = new JTextField(30);
        colorField = new JTextField(10);

        panel.add(new JLabel("Mass(kg)"));
        panel.add(massField);

        panel.add(new JLabel("X Position[0-1000]"));
        panel.add(positionFieldX);

        panel.add(new JLabel("Y Position[0-800]"));
        panel.add(positionFieldY);

        panel.add(new JLabel("X velocity(m/s)"));
        panel.add(velocityFieldX);

        panel.add(new JLabel("Y Velocity(m/s)"));
        panel.add(velocityFieldY);

        panel.add(new JLabel("Name"));
        panel.add(nameField);

        panel.add(new JLabel("Color"));
        panel.add(colorField);

        panel.add(new JLabel("Add another planet?"));

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: loads solar system saved in systems.txt
    private void loadFromFile() {
        loadSystemsFromFile();
    }

    // MODIFIES: this
    // EFFECTS: loads solar system hard-coded in program
    private void loadStored() {
        loadCentauriSystem();
    }

    // MODIFIES: this
    // EFFECTS: calls helper to create and run new simulation window
    private void evolveSystem() {
        evolve();
    }

    // MODIFIES: this
    // EFFECTS: creates new window to show list of current planets
    private void showInspectSystemPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Solar System Bodies"));
        int numPlanets = gravityApp.solarSystem.getNumPlanets();
        for (int i = 0; i < numPlanets; i++) {
            panel.add(new JLabel("Name: " + gravityApp.solarSystem.getPlanetName(i)));
            panel.add(new JLabel("Mass: " + gravityApp.solarSystem.getPlanet(i).getMass() + " kg"));
            panel.add(new JLabel("Color: " + gravityApp.solarSystem.getPlanet(i).getColor().toString()));
            panel.add(Box.createVerticalStrut(6));
        }
        JOptionPane.showMessageDialog(frame, panel);
    }

    // EFFECTS: calls helper method to save current solar system to systems.txt
    private void saveSystem() {
        save();
    }

    // MODIFIES: this
    // EFFECTS: opens new window for user to enter name of planet they wish to delete
    private void deletePlanet() {
        panel = new JPanel();
        panel.add(new JLabel("Enter planet name to delete: "));
        JTextField deleteField = new JTextField(30);
        panel.add(deleteField);

        int option = JOptionPane.showConfirmDialog(
                frame, panel, "Delete a planet", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);

        String nameToDelete = deleteField.getText();

        GravityApp.solarSystem.removePlanet(nameToDelete);

    }
}