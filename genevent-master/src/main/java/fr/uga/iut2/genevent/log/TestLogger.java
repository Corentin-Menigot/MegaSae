package fr.uga.iut2.genevent.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestLogger {

    private static final Logger logger = Logger.getLogger(TestLogger.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            // Configure the logger to write to a file
            fileHandler = new FileHandler("test-log.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize logger file handler.", e);
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
