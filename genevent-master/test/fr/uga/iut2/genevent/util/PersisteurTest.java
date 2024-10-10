package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.log.TestLogger;
import fr.uga.iut2.genevent.modele.GenEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class PersisteurTest {

    private static final Logger logger = TestLogger.getLogger();
    private static final String PERSISTENCE_FILE_PATH = "persistence/genevent.bdd";

    /**
     * Nettoyage avant chaque test.
     */
    @BeforeEach
    void setUp() {
        logger.info("Nettoyage avant le test.");
        File persistenceFile = new File(PERSISTENCE_FILE_PATH);
        if (persistenceFile.exists()) {
            persistenceFile.delete();
        }
    }

    /**
     * Nettoyage après chaque test.
     */
    @AfterEach
    void tearDown() {
        logger.info("Nettoyage après le test.");
        File persistenceFile = new File(PERSISTENCE_FILE_PATH);
        if (persistenceFile.exists()) {
            persistenceFile.delete();
        }
    }

    /**
     * Test de la méthode sauverEtat.
     */
    @Test
    void sauverEtat() {
        logger.info("Test de la méthode sauverEtat.");
        try {
            GenEvent genEvent = new GenEvent();
            Persisteur.sauverEtat(genEvent);

            File persistenceFile = new File(PERSISTENCE_FILE_PATH);
            assertTrue(persistenceFile.exists(), "Le fichier de persistance doit exister après la sauvegarde.");
            logger.info("Test sauverEtat passé.");
        } catch (IOException e) {
            logger.severe("Test sauverEtat échoué : " + e.getMessage());
            fail("Exception during test: " + e.getMessage());
        }
    }

    /**
     * Test de la méthode lireEtat.
     */
    @Test
    void lireEtat() {
        logger.info("Test de la méthode lireEtat.");
        try {
            GenEvent genEvent = new GenEvent();
            Persisteur.sauverEtat(genEvent);

            GenEvent restoredGenEvent = Persisteur.lireEtat();
            assertNotNull(restoredGenEvent, "L'instance restaurée ne doit pas être nulle.");
            logger.info("Test lireEtat passé.");
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Test lireEtat échoué : " + e.getMessage());
            fail("Exception during test: " + e.getMessage());
        }
    }

    /**
     * Test de la méthode lireEtat quand le fichier de persistance n'existe pas.
     */
    @Test
    void lireEtatFichierInexistant() {
        logger.info("Test de la méthode lireEtat avec un fichier de persistance inexistant.");
        try {
            GenEvent restoredGenEvent = Persisteur.lireEtat();
            assertNotNull(restoredGenEvent, "L'instance restaurée ne doit pas être nulle.");
            assertTrue(restoredGenEvent.getEvenements().isEmpty(), "L'instance restaurée doit être vide.");
            logger.info("Test lireEtatFichierInexistant passé.");
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Test lireEtatFichierInexistant échoué : " + e.getMessage());
            fail("Exception during test: " + e.getMessage());
        }
    }
}
