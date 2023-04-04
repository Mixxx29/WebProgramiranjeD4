package rs.raf.webprogramiranjed4.utils;

import java.io.*;

public class HTMLFile {

    private String filePath;

    public HTMLFile(String filename) {
        this.filePath = "C:/Users/mihaj/IdeaProjects/WebProgramiranjeD4/src/main/webapp/html/" + filename;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return builder.toString();
    }
}
