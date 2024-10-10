package fr.uga.iut2.genevent.log;

import java.io.IOException;
import java.util.logging.*;

public class JavaLog {
    private static final Logger LOGGER =
            Logger.getLogger(JavaLog.class.getName());

    static {
        try {

            ConsoleHandler logJava = new ConsoleHandler();
            logJava.setLevel(Level.ALL);
            LOGGER.addHandler(logJava);

            // File handler
            FileHandler fileJava = new FileHandler("genevent.log", true);
            fileJava.setLevel(Level.ALL);
            fileJava.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileJava);

            // Set global logger level
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la configuration du gestionnaire de fichiers", e);
        }
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static void log(Level level, String message, Object param) {
        LOGGER.log(level, message, param);
    }

    public static void log(Level level, String message, Object[] params) {
        LOGGER.log(level, message, params);
    }

    public static void log(Level level, String message, Throwable thrown) {
        LOGGER.log(level, message, thrown);
    }
}
