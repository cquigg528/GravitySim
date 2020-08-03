package persistence;

import model.Planet;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Code below is based on the code provided from the UBC CPSC210 sample program TellerApp on github,
// commit f7fa8a0

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ".";
    private static Color color;

    // EFFECT: returns a list of planets parsed from file;
    // throws IOException if an exception is raised when opening / reading from file
    public static List<Planet> readPlanets(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of planets parsed from list of strings
    // where each string contains data for one account
    private static List<Planet> parseContent(List<String> fileContent) {
        List<Planet> planets = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            planets.add(parsePlanet(lineComponents));
        }

        return planets;
    }

    // EFFECTS: returns list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 9 where element 0 represents the mass of the planet,
    // element 1 represents the x coordinate, element 2 represents the y coordinate, element
    // 3 represents the x velocity, element 4 represents the y velocity, element 5 represents
    // the net force on the planet in the x direction, element 6 represents the net force on the
    // planet in the y direction, element 7 represents the planets name, and element 8 represents
    // the planets color
    // EFFECTS: returns a planet constructed from components
    private static Planet parsePlanet(List<String> components) {
        double mass = Double.parseDouble(components.get(0));
        double xpos = Double.parseDouble(components.get(1));
        double ypos = Double.parseDouble(components.get(2));
        double xvel = Double.parseDouble(components.get(3));
        double yvel = Double.parseDouble(components.get(4));
        double forceX = Double.parseDouble(components.get(5));
        double forceY = Double.parseDouble(components.get(6));
        String name = components.get(7);

        // parse and assign saved color
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(components.get(8));
        if (m.find()) {
            int red = Integer.parseInt(m.group(0));
            int green = Integer.parseInt(m.group(1));
            int blue = Integer.parseInt(m.group(2));
            color = new Color(red, green, blue);
        }
        return new Planet(mass, xpos, ypos, xvel, yvel, forceX, forceY, name, color);
    }















}
