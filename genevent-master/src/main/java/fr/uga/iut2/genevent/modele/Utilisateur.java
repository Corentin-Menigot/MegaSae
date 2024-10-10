package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Classe représentant un utilisateur.
 * Un utilisateur possède un email, un nom, un prénom, un mot de passe,
 * et une collection d'événements qu'il administre.
 */
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private static final Logger LOGGER = Logger.getLogger(Utilisateur.class.getName());

    private final String email;
    private String nom;
    private String prenom;
    private String motDePasse;
    private final Map<String, Evenement> evenementsAdministres;  // association qualifiée par le nom

    /**
     * Constructeur pour créer un utilisateur avec les informations fournies.
     *
     * @param email l'adresse email de l'utilisateur
     * @param nom le nom de l'utilisateur
     * @param prenom le prénom de l'utilisateur
     * @param motDePasse le mot de passe de l'utilisateur
     */
    public Utilisateur(String email, String nom, String prenom, String motDePasse) {
        assert EmailValidator.getInstance(false, false).isValid(email) : "Format email non valide";
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.evenementsAdministres = new HashMap<>();
        LOGGER.log(Level.INFO, "Utilisateur créé ", this.email);
    }

    /**
     * Obtient l'adresse email de l'utilisateur.
     *
     * @return l'adresse email de l'utilisateur
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Obtient le nom de l'utilisateur.
     *
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur.
     *
     * @param nom le nouveau nom de l'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
        LOGGER.log(Level.INFO, "Nom de l'utilisateur {0} mis à jour ", new Object[]{this.email, this.nom});
    }

    /**
     * Obtient le prénom de l'utilisateur.
     *
     * @return le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom de l'utilisateur.
     *
     * @param prenom le nouveau prénom de l'utilisateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
        LOGGER.log(Level.INFO, "Prénom de l'utilisateur  mis à jour ", new Object[]{this.email, this.prenom});
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     *
     * @return le mot de passe de l'utilisateur
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Ajoute un événement administré par l'utilisateur.
     *
     * @param evt l'événement à ajouter
     */
    public void ajouteEvenementAdministre(Evenement evt) {
        assert !this.evenementsAdministres.containsKey(evt.getNomEvenement()) : "L'événement est déjà administré";
        this.evenementsAdministres.put(evt.getNomEvenement(), evt);
        LOGGER.log(Level.INFO, "Événement ajouté par l'utilisateur ", new Object[]{this.email, evt.getNomEvenement()});
    }

    /**
     * Obtient la collection d'événements administrés par l'utilisateur.
     *
     * @return la collection d'événements administrés
     */
    public Map<String, Evenement> getEvenementsAdministres() {
        LOGGER.log(Level.INFO, "Récupération des événements administrés par l'utilisateur", this.email);
        return evenementsAdministres;
    }
}
