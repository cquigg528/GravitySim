package ui;

import javax.swing.*;

import static ui.GravityApp.solarSystem;

public class DeletePlanetPanel extends JFrame {

    private JPanel panel;

    // constructor
    // EFFECTS: shows new window for user to type in the name of a planet they wish to delete
    protected DeletePlanetPanel() {
        panel = new JPanel();
        panel.add(new JLabel("Enter planet name to delete: "));
        JTextField deleteField = new JTextField(30);
        panel.add(deleteField);

        int option = JOptionPane.showConfirmDialog(
                this, panel, "Delete a planet", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);

        String nameToDelete = deleteField.getText();

        solarSystem.removePlanet(nameToDelete);
    }
}
