package ui;

import model.Planet;
import persistence.Writer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static ui.GravityApp.solarSystem;
import static ui.GravityApp.SYSTEMS_FILE;

public class SaveSystemPanel extends JFrame {
    private Writer writer;
    private JPanel panel;

    // constructor
    // EFFECTS: displays new window indicating the whether the solar system was successfully saved
    protected SaveSystemPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        int i = 0;
        try {
            writer = new Writer(new File(SYSTEMS_FILE));
            while (i < solarSystem.getNumPlanets()) {
                Planet planet = solarSystem.getPlanet(i);
                writer.write(planet);
                i++;
            }
            writer.close();
            panel.add(new JLabel("System saved to " + SYSTEMS_FILE));
            JOptionPane.showMessageDialog(this, panel);
        } catch (FileNotFoundException e) {
            panel.add(new JLabel("Unable to save to " + SYSTEMS_FILE));
            JOptionPane.showMessageDialog(this, panel);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
