package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CustomLogger {
    private static FileHandler fileHandler = null;

    public static void init(){
        try {
            fileHandler = new FileHandler("logs.log", false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fileHandler.setFormatter(new SimpleFormatter());
        l.addHandler(fileHandler);
        l.setLevel(Level.CONFIG);
    }
}
