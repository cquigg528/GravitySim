package ui;

import model.SolarSystem;

import java.util.Scanner;

// Solar system simulator
public class GravityApp {
    private Scanner input;
    private SolarSystem solarSystem;

    // constructor
    // EFFECTS: runs gravity application
    public GravityApp(SolarSystem system) {
        runGravity();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGravity() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    private void displayMenu() {

    }

    private void processCommand(String command) {
    }


}
