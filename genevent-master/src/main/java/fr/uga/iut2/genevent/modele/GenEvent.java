package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.JavaLog;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * La classe GenEvent représente l'application de gestion des événements.
 * Elle contient des listes d'événements, d'événements archivés, d'utilisateurs et de types d'emplacements.
 */
public class GenEvent implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final ArrayList<Evenement> evenements;  // association qualifiée par le nom
    private final ArrayList<Evenement> evenementsArchive;
    private final Map<String, Utilisateur> utilisateurs;  // association qualifiée par l'email
    private ArrayList<String> typesEmplacement;

    /**
     * Constructeur de la classe GenEvent.
     * Initialise les listes d'événements, d'événements archivés, d'utilisateurs et de types d'emplacements.
     */
    public GenEvent() {
        JavaLog.log(Level.INFO, "Initialisation de GenEvent");
        this.evenements = new ArrayList<>();
        this.evenementsArchive = new ArrayList<>();
        this.typesEmplacement = new ArrayList<>();
        this.utilisateurs = new HashMap<>();
    }

    /**
     * Récupère la liste des événements.
     *
     * @return la liste des événements
     */
    public ArrayList<Evenement> getEvenements() {
        JavaLog.log(Level.INFO, "Récupération des événements:", evenements);
        return this.evenements;
    }

    /**
     * Récupère la liste des événements archivés.
     *
     * @return la liste des événements archivés
     */
    public ArrayList<Evenement> getEvenementsArchive() {
        JavaLog.log(Level.INFO, "Récupération des événements archivés", evenementsArchive);
        return this.evenementsArchive;
    }

    /**
     * Ajoute un nouvel événement à la liste des événements.
     *
     * @param nom             le nom de l'événement
     * @param lieu            le lieu de l'événement
     * @param dateDebut       la date de début de l'événement
     * @param dateFin         la date de fin de l'événement
     * @param prixEau         le prix de l'eau
     * @param prixGaz         le prix du gaz
     * @param prixElec        le prix de l'électricité
     * @param prixEmplacement le prix de l'emplacement
     * @param longueur        la longueur de l'emplacement
     * @param largeur         la largeur de l'emplacement
     */
    public void nouvelEvenement(String nom,
                                String lieu,
                                LocalDate dateDebut,
                                LocalDate dateFin,
                                int prixEau,
                                int prixGaz,
                                int prixElec,
                                int prixEmplacement,
                                int longueur,
                                int largeur) {
        Evenement evenement = new Evenement(nom, dateDebut, dateFin, lieu, prixEau, prixGaz, prixElec, prixEmplacement, longueur, largeur);
        this.evenements.add(evenement);
        JavaLog.log(Level.INFO, "Ajout d'un nouvel événement", evenement);
    }

    /**
     * Ajoute un nouveau type d'emplacement à la liste des types d'emplacements.
     *
     * @param type le type d'emplacement à ajouter
     */
    public void ajouterTypeEmplacement(String type) {
        this.typesEmplacement.add(type);
        JavaLog.log(Level.INFO, "Ajout d'un type d'emplacement", type);
    }

    /**
     * Récupère la liste des types d'emplacements.
     *
     * @return la liste des types d'emplacements
     */
    public ArrayList<String> getTypesEmplacement() {
        JavaLog.log(Level.INFO, "Récupération des types d'emplacement", typesEmplacement);
        return typesEmplacement;
    }

    /**
     * Ajoute un nouvel utilisateur à la liste des utilisateurs.
     *
     * @param email      l'email de l'utilisateur
     * @param nom        le nom de l'utilisateur
     * @param prenom     le prénom de l'utilisateur
     * @param motDePasse le mot de passe de l'utilisateur
     * @return true si l'utilisateur a été ajouté, false si l'utilisateur existe déjà
     */
    public boolean ajouteUtilisateur(String email, String nom, String prenom, String motDePasse) {
        if (this.utilisateurs.containsKey(email)) {
            JavaLog.log(Level.WARNING, "Utilisateur déjà existant avec l'email", email);
            return false;
        } else {
            this.utilisateurs.put(email, new Utilisateur(email, nom, prenom, motDePasse));
            JavaLog.log(Level.INFO, "Ajout d'un nouvel utilisateur", email);
            return true;
        }
    }

    /**
     * Récupère la liste des utilisateurs.
     *
     * @return la liste des utilisateurs
     */
    public Map<String, Utilisateur> getUtilisateurs() {
        JavaLog.log(Level.INFO, "Récupération des utilisateurs", utilisateurs);
        return this.utilisateurs;
    }

    /**
     * Archive un événement, le déplace de la liste des événements à la liste des événements archivés.
     *
     * @param evenement l'événement à archiver
     */
    public void archiverEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        this.evenementsArchive.add(evenement);
        JavaLog.log(Level.INFO, "Événement archivé", evenement);
    }

    /**
     * Ajoute un événement à la liste des événements.
     *
     * @param evenement l'événement à ajouter
     */
    public void addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        JavaLog.log(Level.INFO, "Ajout d'un événement", evenement);
    }

    /**
     * Supprime un événement de la liste des événements.
     *
     * @param evenement l'événement à supprimer
     * @return true si l'événement a été supprimé, false sinon
     */
    public boolean supprimerEvenement(Evenement evenement) {
        boolean removed = this.evenements.remove(evenement);
        JavaLog.log(Level.INFO, "Suppression de l'événement, Réussi", new Object[]{evenement, removed});
        return removed;
    }

    /**
     * Charge les événements archivés à partir d'un fichier sérialisé.
     * Les événements archivés existants dans la liste sont remplacés par ceux chargés depuis le fichier.
     */
    public void chargerEvenementsArchives() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("evenementsArchives.ser"))) {
            evenementsArchive.clear();
            evenementsArchive.addAll((ArrayList<Evenement>) in.readObject());
            JavaLog.log(Level.INFO, "Chargement des événements archivés réussi.");
        } catch (IOException | ClassNotFoundException e) {
            JavaLog.log(Level.SEVERE, "Erreur lors du chargement des événements archivés", e);
        }
    }
}
