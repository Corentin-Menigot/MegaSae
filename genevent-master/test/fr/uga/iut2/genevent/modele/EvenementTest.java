package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.TestLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class EvenementTest {
    private static final Logger logger = TestLogger.getLogger();
    private Evenement evenement;

    @BeforeEach
    void setUp() {
        logger.info("Initialisation des tests.");
        // Création d'un nouvel événement pour les tests
        evenement = new Evenement("Festival", LocalDate.of(2024, 6, 20), LocalDate.of(2024, 6, 22), "Paris", 10.0f, 20.0f, 30.0f, 40.0f, 10, 10);
    }

    @AfterEach
    void tearDown() {
        logger.info("Nettoyage après le test.");
    }

    @Test
    void getCommercants() {
        logger.info("Test de la méthode getCommercants.");
        try {
            // Vérifie que la liste des commerçants n'est pas nulle et est initialement vide
            assertNotNull(evenement.getCommercants());
            assertEquals(0, evenement.getCommercants().size());
            logger.info("Test getCommercants passé.");
        } catch (AssertionError e) {
            logger.severe("Test getCommercants échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getDisposition() {
        logger.info("Test de la méthode getDisposition.");
        try {
            // Initialisation de la disposition et vérification de sa taille
            evenement.initialisationDisposition(5, 5);
            Emplacement[][] disposition = evenement.getDisposition();
            assertNotNull(disposition);
            assertEquals(5, disposition.length);
            assertEquals(5, disposition[0].length);
            logger.info("Test getDisposition passé.");
        } catch (AssertionError e) {
            logger.severe("Test getDisposition échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getLargeur() {
        logger.info("Test de la méthode getLargeur.");
        try {
            // Vérifie que la largeur de l'événement est correcte
            assertEquals(10, evenement.getLargeur());
            logger.info("Test getLargeur passé.");
        } catch (AssertionError e) {
            logger.severe("Test getLargeur échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getLongueur() {
        logger.info("Test de la méthode getLongueur.");
        try {
            // Vérifie que la longueur de l'événement est correcte
            assertEquals(10, evenement.getLongueur());
            logger.info("Test getLongueur passé.");
        } catch (AssertionError e) {
            logger.severe("Test getLongueur échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrixEau() {
        logger.info("Test de la méthode getPrixEau.");
        try {
            // Vérifie que le prix de l'eau est correct
            assertEquals(10.0f, evenement.getPrixEau());
            logger.info("Test getPrixEau passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrixEau échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrixElectricite() {
        logger.info("Test de la méthode getPrixElectricite.");
        try {
            // Vérifie que le prix de l'électricité est correct
            assertEquals(30.0f, evenement.getPrixElectricite());
            logger.info("Test getPrixElectricite passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrixElectricite échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrixGaz() {
        logger.info("Test de la méthode getPrixGaz.");
        try {
            // Vérifie que le prix du gaz est correct
            assertEquals(20.0f, evenement.getPrixGaz());
            logger.info("Test getPrixGaz passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrixGaz échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrixEmplacement() {
        logger.info("Test de la méthode getPrixEmplacement.");
        try {
            // Vérifie que le prix de l'emplacement est correct
            assertEquals(40.0f, evenement.getPrixEmplacement());
            logger.info("Test getPrixEmplacement passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrixEmplacement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getNomEvenement() {
        logger.info("Test de la méthode getNomEvenement.");
        try {
            // Vérifie que le nom de l'événement est correct
            assertEquals("Festival", evenement.getNomEvenement());
            logger.info("Test getNomEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test getNomEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getLieuEvenement() {
        logger.info("Test de la méthode getLieuEvenement.");
        try {
            // Vérifie que le lieu de l'événement est correct
            assertEquals("Paris", evenement.getLieuEvenement());
            logger.info("Test getLieuEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test getLieuEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getDateDebut() {
        logger.info("Test de la méthode getDateDebut.");
        try {
            // Vérifie que la date de début de l'événement est correcte
            assertEquals(LocalDate.of(2024, 6, 20), evenement.getDateDebut());
            logger.info("Test getDateDebut passé.");
        } catch (AssertionError e) {
            logger.severe("Test getDateDebut échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getDateFin() {
        logger.info("Test de la méthode getDateFin.");
        try {
            // Vérifie que la date de fin de l'événement est correcte
            assertEquals(LocalDate.of(2024, 6, 22), evenement.getDateFin());
            logger.info("Test getDateFin passé.");
        } catch (AssertionError e) {
            logger.severe("Test getDateFin échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void ajouterCommercant() {
        logger.info("Test de la méthode ajouterCommercant.");
        try {
            // Ajoute un commerçant à l'événement et vérifie son ajout
            evenement.ajouterCommercant("John", "Doe");
            HashMap<String, Commercant> commercants = evenement.getCommercants();
            assertEquals(1, commercants.size());
            Commercant commercant = commercants.values().iterator().next();
            assertEquals("John", commercant.getPrenom());
            assertEquals("Doe", commercant.getNom());
            logger.info("Test ajouterCommercant passé.");
        } catch (AssertionError e) {
            logger.severe("Test ajouterCommercant échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void supprimerCommercant() {
        logger.info("Test de la méthode supprimerCommercant.");
        try {
            // Ajoute et supprime un commerçant de l'événement et vérifie sa suppression
            evenement.ajouterCommercant("John", "Doe");
            String id = evenement.getCommercants().values().iterator().next().getId();
            evenement.supprimerCommercant(id);
            assertEquals(0, evenement.getCommercants().size());
            logger.info("Test supprimerCommercant passé.");
        } catch (AssertionError e) {
            logger.severe("Test supprimerCommercant échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void initialisationDisposition() {
        logger.info("Test de la méthode initialisationDisposition.");
        try {
            // Initialise la disposition et vérifie sa taille
            evenement.initialisationDisposition(5, 5);
            Emplacement[][] disposition = evenement.getDisposition();
            assertNotNull(disposition);
            assertEquals(5, disposition.length);
            assertEquals(5, disposition[0].length);
            logger.info("Test initialisationDisposition passé.");
        } catch (AssertionError e) {
            logger.severe("Test initialisationDisposition échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setDisposition() {
        logger.info("Test de la méthode setDisposition.");
        try {
            // Modifie la disposition et vérifie la mise à jour
            Emplacement[][] newDisposition = new Emplacement[3][3];
            evenement.setDisposition(newDisposition);
            assertEquals(newDisposition, evenement.getDisposition());
            logger.info("Test setDisposition passé.");
        } catch (AssertionError e) {
            logger.severe("Test setDisposition échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testToString() {
        logger.info("Test de la méthode toString.");
        try {
            // Vérifie que la méthode toString retourne le bon format
            assertEquals("Festival", evenement.toString());
            logger.info("Test toString passé.");
        } catch (AssertionError e) {
            logger.severe("Test toString échoué : " + e.getMessage());
            throw e;
        }
    }
}
