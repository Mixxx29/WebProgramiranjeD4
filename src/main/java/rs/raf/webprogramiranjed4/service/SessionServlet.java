package rs.raf.webprogramiranjed4.service;

import com.google.gson.Gson;
import rs.raf.webprogramiranjed4.utils.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@WebServlet(name = "OrderServlet", value = "/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SessionManager.getSession(request).getAttribute("order") != null) {
            HashMap<String, String> order = new HashMap<>();
            order = (LinkedHashMap<String, String>) request.getSession().getAttribute("order");
            String json = new Gson().toJson(order);

            response.setContentLength(json.length());
            response.getWriter().println(json);
            return;
        }
        response.setContentLength(2);
        response.getWriter().println("{}");
    }
}
