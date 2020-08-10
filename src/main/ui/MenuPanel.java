package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Planet;

import static ui.GravityApp.*;

// Represents the panel in which the scoreboard is displayed
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

    private GravityApp gravityApp;



    public MenuPanel(GravityApp gravityApp) {
        JPanel menuArea = new JPanel();
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newSystemButton)) {
            newSystem();
        } else if (e.getSource().equals(loadSystemFromFileButton)) {
            loadFromFile();
        } else if (e.getSource().equals(loadSystemFromProgramButton)) {
            loadStored();
        } else if (e.getSource().equals(evolveButton)) {
            evolveSystem();
        } else if (e.getSource().equals(inspectButton)) {
            showInspectSystemPanel();
        } else if (e.getSource().equals(saveButton)) {
            saveSystem();
        }
    }

    private void newSystem() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 1, 1));

        massField = new JTextField(10);
        positionFieldX = new JTextField(4);
        positionFieldY = new JTextField(3);
        velocityFieldX = new JTextField(5);
        velocityFieldY = new JTextField(5);
        nameField = new JTextField(30);
        colorField = new JTextField(10);

        initializeFields();

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

    private void initializeFields() {
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
    }

    private void loadFromFile() {
        gravityApp.loadSystemsFromFile();
    }

    private void loadStored() {
        loadCentauriSystem();
    }

    private void evolveSystem() {
        // TODO!!!!
        evolve();

    }

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

    private void saveSystem() {
        save();
    }






}