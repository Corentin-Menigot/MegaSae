package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Commercant;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.GenEvent;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controleur de l'application GenEvent.
 * Gère les interactions entre le modèle GenEvent et l'interface utilisateur (IHM).
 */
public class Controleur {

    private static final Logger LOGGER = Logger.getLogger(Controleur.class.getName());

    private final GenEvent genevent;
    private final IHM ihm;

    /**
     * Constructeur du contrôleur.
     * Initialise le modèle GenEvent et choisit l'interface utilisateur.
     *
     * @param genevent l'instance du modèle GenEvent
     */
    public Controleur(GenEvent genevent) {
        this.genevent = genevent;


        this.ihm = new JavaFXGUI(this);
        LOGGER.log(Level.INFO, "Contrôleur initialisé avec succès.");
    }

    /**
     * Démarre l'interaction avec l'utilisateur.
     */
    public void demarrer() {
        LOGGER.log(Level.INFO, "Démarrage de l'interaction avec l'utilisateur.");
        this.ihm.demarrerInteraction();
    }

    /**
     * Gère la connexion de l'utilisateur.
     *
     * @param event l'événement de l'action
     */
    public void login(ActionEvent event) {
        LOGGER.log(Level.INFO, "Tentative de connexion de l'utilisateur.");
        this.ihm.loginUtilisateur(event);
    }

    /**
     * Génère la matrice.
     */
    @FXML
    public void genereMatrice() {
        LOGGER.log(Level.INFO, "Génération de la matrice.");
        this.ihm.generationMatrice(this.genevent);
    }

    /**
     * Génère automatiquement la matrice détaillée.
     */
    @FXML
    public void genereAutoMatrice() {
        LOGGER.log(Level.INFO, "Génération automatique de la matrice détaillée.");
        this.ihm.genereDetailedMap();
    }

    /**
     * Supprime la vue de la liste.
     */
    @FXML
    public void supprimeListeView() {
        LOGGER.log(Level.INFO, "Suppression de la vue de la liste.");
        this.ihm.supprimeList();
    }

    /**
     * Saisit un nouvel événement.
     */
    public void Evenement() {
        LOGGER.log(Level.INFO, "Saisie d'un nouvel événement.");
        this.ihm.saisirNouvelEvenement();
    }

    /**
     * Saisit un nouvel utilisateur.
     */
    public void saisirUtilisateur() {
        LOGGER.log(Level.INFO, "Saisie d'un nouvel utilisateur.");
        this.ihm.saisirUtilisateur();
    }

    /**
     * Valide les informations de l'utilisateur.
     *
     * @param infos les informations de l'utilisateur
     */
    public void validerUtilisateur(IHM.InfosUtilisateur infos) {
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom,
                infos.motDePasse
        );
        if (nouvelUtilisateur) {
            LOGGER.log(Level.INFO, "Nouvel utilisateur ajouté: ", new Object[]{infos.prenom, infos.nom});
        } else {
            this.ihm.informerUtilisateur("Identifiant incorrect", false);
            LOGGER.log(Level.WARNING, "Tentative d'ajout d'un utilisateur avec un identifiant incorrect:", infos.email);
        }
    }

    /**
     * Crée un nouvel utilisateur.
     *
     * @param infos les informations de l'utilisateur
     */
    public void creerUtilisateur(IHM.InfosUtilisateur infos) {
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom,
                infos.motDePasse
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisa·teur/trice : " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
            LOGGER.log(Level.INFO, "Nouvel utilisateur créé: ", new Object[]{infos.prenom, infos.nom});
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisa·teur/trice " + infos.email + " est déjà connu·e de GenEvent.",
                    false
            );
            LOGGER.log(Level.WARNING, "Utilisateur déjà existant: ", infos.email);
        }
    }

    /**
     * Saisit un nouvel événement.
     */
    public void saisirEvenement() {
        LOGGER.log(Level.INFO, "Saisie d'un nouvel événement.");
        this.ihm.saisirNouvelEvenement();
    }

    /**
     * Crée un nouvel événement.
     *
     * @param infos les informations du nouvel événement
     */
    public void creerEvenement(IHM.InfosNouvelEvenement infos) {
        this.genevent.nouvelEvenement(
                infos.nom,
                infos.lieu,
                infos.dateDebut,
                infos.dateFin,
                infos.prixEau,
                infos.prixGaz,
                infos.prixElec,
                infos.largeur,
                infos.longeur,
                infos.prixEmplacement
        );
        this.ihm.informerUtilisateur(
                "Nouvel évènement : " + infos.lieu,
                true
        );
        LOGGER.log(Level.INFO, "Nouvel événement créé: ", infos.nom);
    }

    /**
     * Vérifie les informations de connexion de l'utilisateur.
     *
     * @param email      l'adresse email de l'utilisateur
     * @param motDePasse le mot de passe de l'utilisateur
     * @return true si les informations sont correctes, false sinon
     */
    public boolean verifierLoginMdp(String email, String motDePasse) {
        boolean valide = (this.genevent.getUtilisateurs().containsKey(email)
                && this.genevent.getUtilisateurs().get(email).getMotDePasse().equals(motDePasse));
        if (valide) {
            LOGGER.log(Level.INFO, "Connexion réussie pour l'utilisateur:", email);
        } else {
            LOGGER.log(Level.WARNING, "Échec de connexion pour l'utilisateur: ", email);
        }
        return valide;
    }

    /**
     * Initialise la liste des événements.
     *
     * @return la liste des événements
     */
    public ArrayList<Evenement> initListEvent() {
        LOGGER.log(Level.INFO, "Initialisation de la liste des événements.");
        return genevent.getEvenements();
    }

    /**
     * Ajoute un événement à la liste des événements.
     *
     * @param evenement l'événement à ajouter
     * @return la liste des événements mise à jour
     */
    public ArrayList<Evenement> addListEvent(Evenement evenement) {
        genevent.addEvenement(evenement);
        LOGGER.log(Level.INFO, "Événement ajouté à la liste: ", evenement.getNomEvenement());
        return genevent.getEvenements();
    }

    /**
     * Obtient la liste des événements.
     *
     * @return la liste des événements
     */
    public ArrayList<Evenement> getListeEvent() {
        LOGGER.log(Level.INFO, "Obtention de la liste des événements.");
        return genevent.getEvenements();
    }

    /**
     * Archive les informations.
     */
    public void archive() {
        LOGGER.log(Level.INFO, "Archivage des informations.");
        this.ihm.archiveButton();
    }

    /**
     * Consulte les informations.
     */
    public void consulter() {
        LOGGER.log(Level.INFO, "Consultation des informations.");
        this.ihm.consulterButton();
    }

    /**
     * Duplique les informations.
     */
    public void dupliquer() {
        LOGGER.log(Level.INFO, "Duplication des informations.");
        this.ihm.dupliquerButton();
    }

    /**
     * Ajoute un événement.
     */
    public void ajouterEvenement() {
        LOGGER.log(Level.INFO, "Ajout d'un événement.");
        this.ihm.ajouterEvenementButton();
    }

    /**
     * Obtient l'instance de GenEvent.
     *
     * @return l'instance de GenEvent
     */
    public GenEvent getGenEvent() {
        LOGGER.log(Level.INFO, "Obtention de l'instance de GenEvent.");
        return genevent;
    }

    //------ Ajouter un commerçant ------

    /**
     * Crée un nouveau commerçant pour un événement donné.
     *
     * @param evenement l'événement auquel le commerçant est associé
     * @param prenom    le prénom du commerçant
     * @param nom       le nom du commerçant
     * @return le nouveau commerçant créé
     */
    public Commercant creerCommercant(Evenement evenement, String prenom, String nom) {
        LOGGER.log(Level.INFO, "Création d'un nouveau commerçant: {0} {1}", new Object[]{prenom, nom});
        String nouveauCommercantId = evenement.ajouterCommercant(prenom, nom);
        this.ihm.informerUtilisateur("Nouveau·le commercant : " + prenom + " " + nom, true);
        return evenement.getCommercants().get(nouveauCommercantId);
    }

    /**
     * Ajoute un commerçant via le contrôleur.
     */
    public void ajouterCommercantControleur() {
        LOGGER.log(Level.INFO, "Ajout d'un commerçant via le contrôleur.");
        this.ihm.ajouterCommercantVue();
    }

    /**
     * Affiche les détails d'un commerçant via le contrôleur.
     */
    public void detailCommercantControleur() {
        LOGGER.log(Level.INFO, "Affichage des détails d'un commerçant via le contrôleur.");
        this.ihm.afficherDetailCommercantVue();
    }

    /**
     * Archive les informations via le contrôleur.
     */
    public void archiver() {
        LOGGER.log(Level.INFO, "Archivage des informations via le contrôleur.");
        this.ihm.archiverButton();
    }
}
