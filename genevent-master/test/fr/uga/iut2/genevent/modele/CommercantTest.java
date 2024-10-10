package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.TestLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CommercantTest {

    private static final Logger logger = TestLogger.getLogger();

    private Commercant commercant;
    private Evenement evenement;

    @BeforeEach
    void setUp() {
        logger.info("Initialisation de l'environnement de test.");
        // Création d'un nouvel événement pour les tests
        evenement = new Evenement("Event1", LocalDate.now(), LocalDate.now().plusDays(1), "Lieu1", 10, 20, 30, 100, 5, 5);
        // Création d'un nouveau commerçant associé à l'événement
        commercant = new Commercant("John", "Doe", evenement);
    }

    @AfterEach
    void tearDown() {
        logger.info("Nettoyage après le test.");
    }

    @Test
    void getId() {
        logger.info("Test de la méthode getId.");
        try {
            // Vérifie que l'ID du commerçant n'est pas nul et a une longueur de 8 caractères
            assertNotNull(commercant.getId());
            assertEquals(8, commercant.getId().length());
            logger.info("Test getId passé.");
        } catch (AssertionError e) {
            logger.severe("Test getId échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getEvenement() {
        logger.info("Test de la méthode getEvenement.");
        try {
            // Vérifie que l'événement associé au commerçant est correct
            assertEquals(evenement, commercant.getEvenement());
            logger.info("Test getEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test getEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getEmplacements() {
        logger.info("Test de la méthode getEmplacements.");
        try {
            // Vérifie que la liste des emplacements du commerçant est initialement vide
            assertTrue(commercant.getEmplacements().isEmpty());
            logger.info("Test getEmplacements passé.");
        } catch (AssertionError e) {
            logger.severe("Test getEmplacements échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setPrenom() {
        logger.info("Test de la méthode setPrenom.");
        try {
            // Modifie le prénom du commerçant et vérifie la mise à jour
            commercant.setPrenom("Jane");
            assertEquals("Jane", commercant.getPrenom());
            logger.info("Test setPrenom passé.");
        } catch (AssertionError e) {
            logger.severe("Test setPrenom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setNom() {
        logger.info("Test de la méthode setNom.");
        try {
            // Modifie le nom du commerçant et vérifie la mise à jour
            commercant.setNom("Smith");
            assertEquals("Smith", commercant.getNom());
            logger.info("Test setNom passé.");
        } catch (AssertionError e) {
            logger.severe("Test setNom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void setEvenement() {
        logger.info("Test de la méthode setEvenement.");
        try {
            // Crée un nouvel événement et l'associe au commerçant, puis vérifie la mise à jour
            Evenement newEvenement = new Evenement("Event2", LocalDate.now(), LocalDate.now().plusDays(2), "Lieu2", 15, 25, 35, 200, 6, 6);
            commercant.setEvenement(newEvenement);
            assertEquals(newEvenement, commercant.getEvenement());
            logger.info("Test setEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test setEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getNom() {
        logger.info("Test de la méthode getNom.");
        try {
            // Vérifie que le nom du commerçant est correct
            assertEquals("John", commercant.getNom());
            logger.info("Test getNom passé.");
        } catch (AssertionError e) {
            logger.severe("Test getNom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrenom() {
        logger.info("Test de la méthode getPrenom.");
        try {
            // Vérifie que le prénom du commerçant est correct
            assertEquals("Doe", commercant.getPrenom());
            logger.info("Test getPrenom passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrenom échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void genereId() {
        logger.info("Test de la méthode genereId.");
        try {
            // Génère un nouvel ID pour le commerçant et vérifie qu'il n'est pas nul et a une longueur de 8 caractères
            commercant.genereId();
            assertNotNull(commercant.getId());
            assertEquals(8, commercant.getId().length());
            logger.info("Test genereId passé.");
        } catch (AssertionError e) {
            logger.severe("Test genereId échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void ajoutEmplacement() {
        logger.info("Test de la méthode ajoutEmplacement.");
        try {
            // Ajoute un nouvel emplacement au commerçant et vérifie son ajout et son association correcte
            Emplacement emplacement = new Emplacement(false, false, false);
            commercant.ajoutEmplacement(emplacement);
            assertTrue(commercant.getEmplacements().contains(emplacement));
            assertEquals(commercant, emplacement.getCommercant());
            logger.info("Test ajoutEmplacement passé.");
        } catch (AssertionError e) {
            logger.severe("Test ajoutEmplacement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void supprimetEmplacement() {
        logger.info("Test de la méthode supprimetEmplacement.");
        try {
            // Ajoute puis supprime un emplacement au commerçant et vérifie sa suppression et la dissociation correcte
            Emplacement emplacement = new Emplacement(false, false, false);
            commercant.ajoutEmplacement(emplacement);
            commercant.supprimetEmplacement(emplacement);
            assertFalse(commercant.getEmplacements().contains(emplacement));
            assertNull(emplacement.getCommercant());
            logger.info("Test supprimetEmplacement passé.");
        } catch (AssertionError e) {
            logger.severe("Test supprimetEmplacement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getPrix() {
        logger.info("Test de la méthode getPrix.");
        try {
            // Ajoute des emplacements au commerçant et vérifie que le prix total est calculé correctement
            Emplacement emplacement1 = new Emplacement(true, true, true);
            Emplacement emplacement2 = new Emplacement(false, false, false);
            commercant.ajoutEmplacement(emplacement1);
            commercant.ajoutEmplacement(emplacement2);
            float expectedPrix = 2 * evenement.getPrixEmplacement() + evenement.getPrixEau() + evenement.getPrixGaz() + evenement.getPrixElectricite();
            assertEquals(expectedPrix, commercant.getPrix());
            logger.info("Test getPrix passé.");
        } catch (AssertionError e) {
            logger.severe("Test getPrix échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void testToString() {
        logger.info("Test de la méthode toString.");
        try {
            // Vérifie que la méthode toString retourne le bon format
            assertEquals("Doe John", commercant.toString());
            logger.info("Test toString passé.");
        } catch (AssertionError e) {
            logger.severe("Test toString échoué : " + e.getMessage());
            throw e;
        }
    }
}
