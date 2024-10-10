package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.TestLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    private static final Logger logger = TestLogger.getLogger();

    @BeforeEach
    void setUp() {
        logger.info("Initialisation des tests.");
    }

    @AfterEach
    void tearDown() {
        logger.info("Nettoyage après le test.");
    }

    @Test
    void getEmail() {
        logger.info("Test de la méthode getEmail.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            assertEquals("test@example.com", user.getEmail());
            logger.info("Test getEmail passé.");
        } catch (AssertionError e) {
            logger.severe("Test getEmail échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getNom() {
        logger.info("Test de la méthode getNom.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            assertEquals("Doe", user.getNom());
            logger.info("Test getNom passé.");
        } catch (AssertionError e) {
            logger.severe("Test getNom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getMotDePasse() {
        logger.info("Test de la méthode getMotDePasse.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            assertEquals("password123", user.getMotDePasse());
            logger.info("Test getMotDePasse passé.");
        } catch (AssertionError e) {
            logger.severe("Test getMotDePasse échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setNom() {
        logger.info("Test de la méthode setNom.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            user.setNom("Smith");
            assertEquals("Smith", user.getNom());
            logger.info("Test setNom passé.");
        } catch (AssertionError e) {
            logger.severe("Test setNom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrenom() {
        logger.info("Test de la méthode getPrenom.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            assertEquals("John", user.getPrenom());
            logger.info("Test getPrenom passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrenom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setPrenom() {
        logger.info("Test de la méthode setPrenom.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            user.setPrenom("Jane");
            assertEquals("Jane", user.getPrenom());
            logger.info("Test setPrenom passé.");
        } catch (AssertionError e) {
            logger.severe("Test setPrenom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void ajouteEvenementAdministre() {
        logger.info("Test de la méthode ajouteEvenementAdministre.");
        try {
            Utilisateur user = new Utilisateur("test@example.com", "Doe", "John", "password123");
            Evenement event = new Evenement("Event1", LocalDate.now(), LocalDate.now().plusDays(1), "Location", 100.0f, 50.0f, 75.0f, 200.0f, 20, 30);
            user.ajouteEvenementAdministre(event);
            Map<String, Evenement> evenements = user.getEvenementsAdministres();
            assertTrue(evenements.containsKey("Event1"));
            logger.info("Test ajouteEvenementAdministre passé.");
        } catch (AssertionError e) {
            logger.severe("Test ajouteEvenementAdministre échoué : " + e.getMessage());
            throw e;
        }
    }
}
