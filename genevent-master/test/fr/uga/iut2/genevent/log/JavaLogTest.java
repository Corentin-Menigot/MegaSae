package fr.uga.iut2.genevent.log;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class JavaLogTest {

    private final ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(consoleOutput));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void log() {
        JavaLog.log(Level.INFO, "Test log message");

        // Vérifier la sortie de la console
        assertTrue(consoleOutput.toString().contains("Test log message"));

        // Vérifier le fichier de log
        assertLogFileContains("Test log message");
    }

    @Test
    void testLogWithParam() {
        JavaLog.log(Level.INFO, "Test log message with param: {0}", "param1");

        // Vérifier la sortie de la console
        assertTrue(consoleOutput.toString().contains("Test log message with param: param1"));

        // Vérifier le fichier de log
        assertLogFileContains("Test log message with param: param1");
    }

    @Test
    void testLogWithParams() {
        JavaLog.log(Level.INFO, "Test log message with params: {0} and {1}", new Object[]{"param1", "param2"});

        // Vérifier la sortie de la console
        assertTrue(consoleOutput.toString().contains("Test log message with params: param1 and param2"));

        // Vérifier le fichier de log
        assertLogFileContains("Test log message with params: param1 and param2");
    }

    @Test
    void testLogWithThrowable() {
        Throwable throwable = new RuntimeException("Test exception");
        JavaLog.log(Level.SEVERE, "Test log message with exception", throwable);

        // Vérifier la sortie de la console
        assertTrue(consoleOutput.toString().contains("Test log message with exception"));
        assertTrue(consoleOutput.toString().contains("Test exception"));

        // Vérifier le fichier de log
        assertLogFileContains("Test log message with exception");
        assertLogFileContains("Test exception");
    }

    private void assertLogFileContains(String content) {
        File logFile = new File("genevent.log");
        try (FileInputStream fis = new FileInputStream(logFile)) {
            byte[] data = new byte[(int) logFile.length()];
            fis.read(data);
            String fileContent = new String(data);
            assertTrue(fileContent.contains(content));
        } catch (IOException e) {
            fail("Erreur lors de la lecture du fichier de log");
        }
    }
}
