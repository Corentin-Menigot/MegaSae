package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.TestLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class EmplacementTest {

    private static final Logger logger = TestLogger.getLogger();

    private Emplacement emplacement;
    private Commercant commercant;

    @BeforeEach
    void setUp() {
        logger.info("Initialisation de l'environnement de test.");
        emplacement = new Emplacement();
        commercant = new Commercant("John", "Doe", null);
    }

    @AfterEach
    void tearDown() {
        logger.info("Nettoyage après le test.");
    }

    @Test
    void getElec() {
        logger.info("Test de la méthode getElec.");
        try {
            emplacement.setElectricite(true);
            assertTrue(emplacement.getElec());
            emplacement.setElectricite(false);
            assertFalse(emplacement.getElec());
            logger.info("Test getElec passé.");
        } catch (AssertionError e) {
            logger.severe("Test getElec échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getEau() {
        logger.info("Test de la méthode getEau.");
        try {
            emplacement.setEau(true);
            assertTrue(emplacement.getEau());
            emplacement.setEau(false);
            assertFalse(emplacement.getEau());
            logger.info("Test getEau passé.");
        } catch (AssertionError e) {
            logger.severe("Test getEau échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getGaz() {
        logger.info("Test de la méthode getGaz.");
        try {
            emplacement.setGaz(true);
            assertTrue(emplacement.getGaz());
            emplacement.setGaz(false);
            assertFalse(emplacement.getGaz());
            logger.info("Test getGaz passé.");
        } catch (AssertionError e) {
            logger.severe("Test getGaz échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getType() {
        logger.info("Test de la méthode getType.");
        try {
            emplacement.setType("chemin");
            assertEquals("chemin", emplacement.getType());
            emplacement.setType("toilette");
            assertEquals("toilette", emplacement.getType());
            logger.info("Test getType passé.");
        } catch (AssertionError e) {
            logger.severe("Test getType échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getCommercant() {
        logger.info("Test de la méthode getCommercant.");
        try {
            emplacement.setCommercant(commercant);
            assertEquals(commercant, emplacement.getCommercant());
            logger.info("Test getCommercant passé.");
        } catch (AssertionError e) {
            logger.severe("Test getCommercant échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setElectricite() {
        logger.info("Test de la méthode setElectricite.");
        try {
            emplacement.setElectricite(true);
            assertTrue(emplacement.getElec());
            emplacement.setElectricite(false);
            assertFalse(emplacement.getElec());
            logger.info("Test setElectricite passé.");
        } catch (AssertionError e) {
            logger.severe("Test setElectricite échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setEau() {
        logger.info("Test de la méthode setEau.");
        try {
            emplacement.setEau(true);
            assertTrue(emplacement.getEau());
            emplacement.setEau(false);
            assertFalse(emplacement.getEau());
            logger.info("Test setEau passé.");
        } catch (AssertionError e) {
            logger.severe("Test setEau échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setGaz() {
        logger.info("Test de la méthode setGaz.");
        try {
            emplacement.setGaz(true);
            assertTrue(emplacement.getGaz());
            emplacement.setGaz(false);
            assertFalse(emplacement.getGaz());
            logger.info("Test setGaz passé.");
        } catch (AssertionError e) {
            logger.severe("Test setGaz échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setType() {
        logger.info("Test de la méthode setType.");
        try {
            emplacement.setType("chemin");
            assertEquals("chemin", emplacement.getType());
            emplacement.setType("toilette");
            assertEquals("toilette", emplacement.getType());
            logger.info("Test setType passé.");
        } catch (AssertionError e) {
            logger.severe("Test setType échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setCommercant() {
        logger.info("Test de la méthode setCommercant.");
        try {
            emplacement.setCommercant(commercant);
            assertEquals(commercant, emplacement.getCommercant());
            assertTrue(commercant.getEmplacements().contains(emplacement));
            logger.info("Test setCommercant passé.");
        } catch (AssertionError e) {
            logger.severe("Test setCommercant échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void deleteCommercant() {
        logger.info("Test de la méthode deleteCommercant.");
        try {
            emplacement.setCommercant(commercant);
            emplacement.deleteCommercant();
            assertNull(emplacement.getCommercant());
            assertFalse(commercant.getEmplacements().contains(emplacement));
            logger.info("Test deleteCommercant passé.");
        } catch (AssertionError e) {
            logger.severe("Test deleteCommercant échoué : " + e.getMessage());
            throw e;
        }
    }
}
