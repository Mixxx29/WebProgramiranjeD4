package rs.raf.webprogramiranjed4.service;

import com.google.gson.Gson;
import rs.raf.webprogramiranjed4.model.Day;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "MenuServlet", value = "/menu")
public class MenuServlet extends HttpServlet {

    private List<Day> menu;

    public void init() {
        menu = new ArrayList<>(); // Initialize menu

        // Load days
        for (Day.DayName type : Day.DayName.values()) {
            try {
                menu.add(new Day(type));
            } catch (RuntimeException e) {

            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json"); // Set content type

        PrintWriter out = response.getWriter(); // Get response stream
        out.println(new Gson().toJson(menu)); // Send menu
    }
}