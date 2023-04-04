package rs.raf.webprogramiranjed4.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public enum SessionManager {
    INSTANCE;

    private List<String> invalidated;

    private SessionManager() {
        invalidated = new ArrayList<>();
    }

    public static HttpSession getSession(HttpServletRequest request) {
        if (!INSTANCE.invalidated.contains(request.getSession().getId())) {
            request.getSession().invalidate();
            INSTANCE.invalidated.add(request.getSession().getId());
        }
        return request.getSession();
    }

    public static void resetSessions() {
        INSTANCE.invalidated.clear();
    }

    public static boolean validatePassword(String password) {
        // Open file stream
        InputStream is = INSTANCE.getClass()
                .getClassLoader()
                .getResourceAsStream("password.txt");

        if (is == null) throw new RuntimeException("File 'password.txt' doesn't exist!");

        // Convert file to list
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.equals(password)) return true;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading 'password.txt' file!");
        }

        return false;
    }
}
