package rs.raf.webprogramiranjed4.service;

import rs.raf.webprogramiranjed4.utils.HTMLFile;
import rs.raf.webprogramiranjed4.utils.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AppOrdersServlet", value = "/orders")
public class AppOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validate user
        if (request.getParameter("password") == null ||
                !SessionManager.validatePassword(request.getParameter("password"))) {
            response.setStatus(401);
            return;
        }

        response.setContentType("text/html"); // Set content type
        response.getWriter().println(new HTMLFile("orders.html")); // Return orders page
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
