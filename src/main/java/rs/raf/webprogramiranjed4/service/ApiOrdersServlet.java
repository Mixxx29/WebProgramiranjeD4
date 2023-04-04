package rs.raf.webprogramiranjed4.service;

import com.google.gson.Gson;
import rs.raf.webprogramiranjed4.model.Day;
import rs.raf.webprogramiranjed4.utils.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "ApiOrdersServlet", value = "/api/orders")
public class ApiOrdersServlet extends HttpServlet {

    private Map<String, Map<String, Integer>> orders;

    @Override
    public void init() {
        orders = new LinkedHashMap<>();
        for (Day.DayName dayName : Day.DayName.values()) {
            try {
                Day day = new Day(dayName);
                Map<String, Integer> dayMap = new LinkedHashMap<>();
                for (String meal : day.getMeals()) {
                    dayMap.put(meal, 0);
                }
                orders.put(dayName.name().toLowerCase(), dayMap);
            } catch (RuntimeException e) {

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validate user
        if (request.getParameter("password") == null ||
                !SessionManager.validatePassword(request.getParameter("password"))) {
            response.setStatus(401);
            return;
        }

        response.setContentType("application/json"); // Set content type

        PrintWriter out = response.getWriter(); // Get response stream
        out.println(new Gson().toJson(orders)); // Send menu
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); // Set content type

        if (SessionManager.getSession(request).getAttribute("order") == null) {
            Enumeration<String> dayNames =  request.getParameterNames();
            Map<String, String> order = new LinkedHashMap<>();
            while (dayNames.hasMoreElements()) {
                String dayName = dayNames.nextElement();
                String meal = request.getParameter(dayName);
                if (meal.equals("Nothing")) {
                    order.put(dayName, "Nothing");
                    continue;
                }
                order.put(dayName, meal);
                int quantity = orders.get(dayName).get(meal);
                orders.get(dayName).put(meal, quantity + 1);
            }

            SessionManager.getSession(request).setAttribute("order", order);
        }

        response.sendRedirect("/");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionManager.resetSessions();

        // Empty orders
        for (Day.DayName dayName : Day.DayName.values()) {
            for (String meal : orders.get(dayName.name().toLowerCase()).keySet()) {
                orders.get(dayName.name().toLowerCase()).put(meal, 0);
            }
        }

        response.setStatus(200);
    }
}
