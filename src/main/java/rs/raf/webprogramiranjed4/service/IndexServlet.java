package rs.raf.webprogramiranjed4.service;

import rs.raf.webprogramiranjed4.utils.HTMLFile;
import rs.raf.webprogramiranjed4.utils.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/index.html")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); // Set content type

        if (SessionManager.getSession(request).getAttribute("order") == null) {
            response.getWriter().println(new HTMLFile("index.html"));
        } else {
            response.getWriter().println(new HTMLFile("session.html"));
        }
    }

}
