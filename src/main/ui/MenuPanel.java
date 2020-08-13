package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.SolarSystem;

import static ui.GravityApp.*;

// Represents the panel in which the menu buttons are displayed
public class MenuPanel extends JPanel implements ActionListener {

    private JButton newSystemButton;
    private JButton loadSystemFromFileButton;
    private JButton loadSystemFromProgramButton;
    private JButton evolveButton;
    private JButton inspectButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JPanel menuArea = new JPanel();
    private GravityApp gravityApp;
    private AddPlanetPanel addPlanetPanel;
    private InspectSystemPanel inspectSystemPanel;
    private DeletePlanetPanel deletePlanetPanel;

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
            addPlanet();
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
            showDeletePlanetPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new panel for user input, can add multiple planets to solar system
    private void addPlanet() {
        if (gravityApp.solarSystem == null) {
            gravityApp.solarSystem = new SolarSystem();
        }

        addPlanetPanel = new AddPlanetPanel();

        if (AddPlanetPanel.getOption() == JOptionPane.YES_OPTION) {
            addPlanet();
        }
    }

    // EFFECTS: loads solar system saved in systems.txt
    private void loadFromFile() {
        loadSystemsFromFile();
    }

    // EFFECTS: loads solar system hard-coded in program
    private void loadStored() {
        loadCentauriSystem();
    }

    // EFFECTS: calls helper to create and run new simulation window
    private void evolveSystem() {
        evolve();
    }

    // MODIFIES: this
    // EFFECTS: creates new window to show list of current planets
    private void showInspectSystemPanel() {
        inspectSystemPanel = new InspectSystemPanel();
    }

    // EFFECTS: calls helper method to save current solar system to systems.txt
    private void saveSystem() {
        GravityApp.showSaveSystemPanel();
    }

    // MODIFIES: this
    // EFFECTS: opens new window for user to enter name of planet they wish to delete
    private void showDeletePlanetPanel() {
        deletePlanetPanel = new DeletePlanetPanel();
    }
}