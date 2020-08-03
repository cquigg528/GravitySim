package persistence;

import java.io.PrintWriter;

// Code below is based on the code provided from the UBC CPSC210 sample program TellerApp on github,
// commit f7fa8a0

// Represents data that can be saved to a file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);

}
