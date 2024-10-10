package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.TestLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GenEventTest {

    private static final Logger logger = TestLogger.getLogger();
    private GenEvent genEvent;

    @BeforeEach
    void setUp() {
        logger.info("Initialisation des tests.");
        genEvent = new GenEvent();
    }

    @AfterEach
    void tearDown() {
        logger.info("Nettoyage après le test.");
    }

    @Test
    void getEvenements() {
        logger.info("Test de la méthode getEvenements.");
        try {
            // Vérifie que la liste des événements est vide au départ
            assertEquals(0, genEvent.getEvenements().size(), "La liste des événements doit être vide au départ.");
            // Ajoute un nouvel événement et vérifie que la liste contient cet événement
            genEvent.nouvelEvenement("NomTest", "LieuTest", LocalDate.now(), LocalDate.now().plusDays(1), 100, 200, 300, 400, 10, 20);
            assertEquals(1, genEvent.getEvenements().size(), "La liste des événements doit contenir 1 événement après l'ajout.");
            logger.info("Test getEvenements passé.");
        } catch (AssertionError e) {
            logger.severe("Test getEvenements échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getEvenementsArchive() {
        logger.info("Test de la méthode getEvenementsArchive.");
        try {
            // Vérifie que la liste des événements archivés est vide au départ
            assertEquals(0, genEvent.getEvenementsArchive().size(), "La liste des événements archivés doit être vide au départ.");
            // Ajoute et archive un événement, puis vérifie que la liste contient cet événement
            Evenement event = new Evenement("NomTest", LocalDate.now(), LocalDate.now().plusDays(1), "LieuTest", 100, 200, 300, 400, 10, 20);
            genEvent.addEvenement(event);
            genEvent.archiverEvenement(event);
            assertEquals(1, genEvent.getEvenementsArchive().size(), "La liste des événements archivés doit contenir 1 événement après l'archivage.");
            logger.info("Test getEvenementsArchive passé.");
        } catch (AssertionError e) {
            logger.severe("Test getEvenementsArchive échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void nouvelEvenement() {
        logger.info("Test de la méthode nouvelEvenement.");
        try {
            // Ajoute un nouvel événement et vérifie la mise à jour de la liste des événements
            genEvent.nouvelEvenement("NomTest", "LieuTest", LocalDate.now(), LocalDate.now().plusDays(1), 100, 200, 300, 400, 10, 20);
            ArrayList<Evenement> evenements = genEvent.getEvenements();
            assertEquals(1, evenements.size(), "La liste des événements doit contenir 1 événement après l'ajout.");
            Evenement event = evenements.get(0);
            assertEquals("NomTest", event.getNomEvenement(), "Le nom de l'événement doit correspondre au nom donné.");
            logger.info("Test nouvelEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test nouvelEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void ajouterTypeEmplacement() {
        logger.info("Test de la méthode ajouterTypeEmplacement.");
        try {
            // Ajoute un type d'emplacement et vérifie la mise à jour de la liste des types d'emplacements
            genEvent.ajouterTypeEmplacement("TypeTest");
            assertTrue(genEvent.getTypesEmplacement().contains("TypeTest"), "La liste des types d'emplacements doit contenir 'TypeTest'.");
            logger.info("Test ajouterTypeEmplacement passé.");
        } catch (AssertionError e) {
            logger.severe("Test ajouterTypeEmplacement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void ajouteUtilisateur() {
        logger.info("Test de la méthode ajouteUtilisateur.");
        try {
            // Ajoute un utilisateur et vérifie la mise à jour de la liste des utilisateurs
            boolean added = genEvent.ajouteUtilisateur("test@example.com", "NomTest", "PrenomTest", "MotDePasseTest");
            assertTrue(added, "L'utilisateur doit être ajouté.");
            Map<String, Utilisateur> utilisateurs = genEvent.getUtilisateurs();
            assertEquals(1, utilisateurs.size(), "La liste des utilisateurs doit contenir 1 utilisateur après l'ajout.");
            assertTrue(utilisateurs.containsKey("test@example.com"), "La liste des utilisateurs doit contenir l'email 'test@example.com'.");
            logger.info("Test ajouteUtilisateur passé.");
        } catch (AssertionError e) {
            logger.severe("Test ajouteUtilisateur échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void getUtilisateurs() {
        logger.info("Test de la méthode getUtilisateurs.");
        try {
            // Ajoute un utilisateur et vérifie la mise à jour de la liste des utilisateurs
            genEvent.ajouteUtilisateur("test@example.com", "NomTest", "PrenomTest", "MotDePasseTest");
            Map<String, Utilisateur> utilisateurs = genEvent.getUtilisateurs();
            assertEquals(1, utilisateurs.size(), "La liste des utilisateurs doit contenir 1 utilisateur après l'ajout.");
            Utilisateur utilisateur = utilisateurs.get("test@example.com");
            assertEquals("NomTest", utilisateur.getNom(), "Le nom de l'utilisateur doit correspondre au nom donné.");
            logger.info("Test getUtilisateurs passé.");
        } catch (AssertionError e) {
            logger.severe("Test getUtilisateurs échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void archiverEvenement() {
        logger.info("Test de la méthode archiverEvenement.");
        try {
            // Ajoute et archive un événement, puis vérifie sa présence dans la liste des événements archivés
            Evenement event = new Evenement("NomTest", LocalDate.now(), LocalDate.now().plusDays(1), "LieuTest", 100, 200, 300, 400, 10, 20);
            genEvent.addEvenement(event);
            genEvent.archiverEvenement(event);
            assertFalse(genEvent.getEvenements().contains(event), "L'événement ne doit plus être dans la liste des événements.");
            assertTrue(genEvent.getEvenementsArchive().contains(event), "L'événement doit être dans la liste des événements archivés.");
            logger.info("Test archiverEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test archiverEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void supprimerEvenement() {
        logger.info("Test de la méthode supprimerEvenement.");
        try {
            // Ajoute et supprime un événement, puis vérifie sa suppression de la liste des événements
            Evenement event = new Evenement("NomTest", LocalDate.now(), LocalDate.now().plusDays(1), "LieuTest", 100, 200, 300, 400, 10, 20);
            genEvent.addEvenement(event);
            boolean removed = genEvent.supprimerEvenement(event);
            assertTrue(removed, "L'événement doit être supprimé avec succès.");
            assertFalse(genEvent.getEvenements().contains(event), "L'événement ne doit plus être dans la liste des événements.");
            logger.info("Test supprimerEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test supprimerEvenement échoué : " + e.getMessage());
            throw e;
        }
    }

    @Test
    void addEvenement() {
        logger.info("Test de la méthode addEvenement.");
        try {
            // Ajoute un événement et vérifie sa présence dans la liste des événements
            Evenement event = new Evenement("NomTest", LocalDate.now(), LocalDate.now().plusDays(1), "LieuTest", 100, 200, 300, 400, 10, 20);
            genEvent.addEvenement(event);
            assertTrue(genEvent.getEvenements().contains(event), "L'événement doit être ajouté à la liste des événements.");
            logger.info("Test addEvenement passé.");
        } catch (AssertionError e) {
            logger.severe("Test addEvenement échoué : " + e.getMessage());
            throw e;
        }
    }
    @Test
    void chargerEvenementsArchives(){

    }
}
