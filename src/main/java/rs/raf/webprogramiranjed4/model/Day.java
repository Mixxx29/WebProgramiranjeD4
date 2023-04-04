package rs.raf.webprogramiranjed4.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day {

    private DayName name;

    private List<String> meals;

    public enum DayName {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY
    }

    public Day(DayName name) {
        this.name = name;
        this.meals = new ArrayList<>();

        String filename = name.name().toLowerCase() + ".txt";

        // Open file stream
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream(filename);

        if (is == null) throw new RuntimeException("File '" + filename + "' doesn't exist!");

        // Convert file to list
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String s;
            while ((s = reader.readLine()) != null) {
                meals.add(s);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading '" + filename + "' file!");
        }
    }

    public DayName getName() {
        return name;
    }

    public List<String> getMeals() {
        return meals;
    }
}
