package ui;

import model.Planet;

import javax.swing.*;
import java.awt.*;

import static ui.GravityApp.*;

public class AddPlanetPanel extends JFrame {
    private JPanel panel;

    private JTextField massField;
    private JTextField positionFieldX;
    private JTextField positionFieldY;
    private JTextField velocityFieldX;
    private JTextField velocityFieldY;
    private JTextField nameField;
    private JTextField colorField;
    protected static int option;


    // constructor
    // EFFECTS: creates new panel for this, sets up user input to enter new planet fields
    protected AddPlanetPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 1, 1));

        panel = initializeFields(panel);

        option = JOptionPane.showConfirmDialog(
                this, panel, "Add a planet", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        double massInput = Double.parseDouble(massField.getText());
        double xposInput = Double.parseDouble(positionFieldY.getText());
        double yposInput = Double.parseDouble(positionFieldY.getText());
        double xvelInput = Double.parseDouble(velocityFieldY.getText());
        double yvelInput = Double.parseDouble(velocityFieldY.getText());
        String nameInput = nameField.getText();
        Color colorInput = handleColor(colorField.getText());



        addAPlanet(new Planet(massInput, xposInput, yposInput, xvelInput, yvelInput, nameInput, colorInput));
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

    // EFFECTS: returns result of button click
    protected static int getOption() {
        return option;
    }
}
