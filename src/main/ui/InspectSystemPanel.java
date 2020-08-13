package ui;

import javax.swing.*;

import static ui.GravityApp.solarSystem;

public class InspectSystemPanel extends JFrame {

    private JPanel panel;

    // constructor
    // EFFECTS: creates panel for user to inspect current planets in the solar system
    protected InspectSystemPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Solar System Bodies"));
        int numPlanets = solarSystem.getNumPlanets();
        for (int i = 0; i < numPlanets; i++) {
            panel.add(new JLabel("Name: " + solarSystem.getPlanetName(i)));
            panel.add(new JLabel("Mass: " + solarSystem.getPlanet(i).getMass() + " kg"));
            panel.add(new JLabel("Color: " + solarSystem.getPlanet(i).getColor().toString()));
            panel.add(Box.createVerticalStrut(6));
        }
        JOptionPane.showMessageDialog(this, panel);
    }
}
