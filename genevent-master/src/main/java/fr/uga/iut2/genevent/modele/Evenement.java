package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.JavaLog;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;

public class Evenement implements Serializable {
    private String nomEvenement;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String lieuEvenement;
    private float prixEau;
    private float prixGaz;
    private float prixElectricite;
    private float prixEmplacement;
    private Emplacement[][] disposition;
    private HashMap<String, Commercant> commercants;
    private int longueur;
    private int largeur;

    /**
     * Constructeur pour initialiser un événement avec les informations fournies.
     *
     * @param nomEvenement    Le nom de l'événement.
     * @param dateDebut       La date de début de l'événement.
     * @param dateFin         La date de fin de l'événement.
     * @param lieuEvenement   Le lieu de l'événement.
     * @param prixEau         Le prix de l'eau.
     * @param prixGaz         Le prix du gaz.
     * @param prixElectricite Le prix de l'électricité.
     * @param prixEmplacement Le prix de l'emplacement.
     * @param longueur        La longueur de l'événement.
     * @param largeur         La largeur de l'événement.
     */
    public Evenement(String nomEvenement, LocalDate dateDebut, LocalDate dateFin, String lieuEvenement, float prixEau, float prixGaz,
                     float prixElectricite, float prixEmplacement, int longueur, int largeur) {
        JavaLog.log(Level.INFO, "Création d'un nouvel événement", nomEvenement);
        this.nomEvenement = nomEvenement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieuEvenement = lieuEvenement;
        this.prixEau = prixEau;
        this.prixGaz = prixGaz;
        this.prixElectricite = prixElectricite;
        this.prixEmplacement = prixEmplacement;
        this.commercants = new HashMap<>();
        this.longueur = longueur;
        this.largeur = largeur;
    }

    /**
     * Retourne la liste des commerçants participant à l'événement.
     *
     * @return Une map des commerçants avec leur ID comme clé.
     */
    public HashMap<String, Commercant> getCommercants() {
        JavaLog.log(Level.INFO, "Récupération des commerçants pour l'événement", nomEvenement);
        return commercants;
    }

    /**
     * Retourne la disposition des emplacements pour l'événement.
     *
     * @return Un tableau 2D d'emplacements.
     */
    public Emplacement[][] getDisposition() {
        return this.disposition;
    }

    /**
     * Retourne la largeur de l'événement.
     *
     * @return La largeur de l'événement.
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Retourne la longueur de l'événement.
     *
     * @return La longueur de l'événement.
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * Retourne le prix de l'eau pour l'événement.
     *
     * @return Le prix de l'eau.
     */
    public float getPrixEau() {
        return prixEau;
    }

    /**
     * Retourne le prix de l'électricité pour l'événement.
     *
     * @return Le prix de l'électricité.
     */
    public float getPrixElectricite() {
        return prixElectricite;
    }

    /**
     * Retourne le prix du gaz pour l'événement.
     *
     * @return Le prix du gaz.
     */
    public float getPrixGaz() {
        return prixGaz;
    }

    /**
     * Retourne le prix de l'emplacement pour l'événement.
     *
     * @return Le prix de l'emplacement.
     */
    public float getPrixEmplacement() {
        return prixEmplacement;
    }

    /**
     * Retourne le nom de l'événement.
     *
     * @return Le nom de l'événement.
     */
    public String getNomEvenement() {
        return nomEvenement;
    }

    /**
     * Retourne le lieu de l'événement.
     *
     * @return Le lieu de l'événement.
     */
    public String getLieuEvenement() {
        return lieuEvenement;
    }

    /**
     * Retourne la date de début de l'événement.
     *
     * @return La date de début.
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Retourne la date de fin de l'événement.
     *
     * @return La date de fin.
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Ajoute un commerçant à l'événement.
     *
     * @param prenom Le prénom du commerçant.
     * @param nom    Le nom du commerçant.
     * @return
     */
    public String ajouterCommercant(String prenom, String nom) {
        Commercant commercant = new Commercant(nom, prenom, this);
        this.commercants.put(commercant.getId(), commercant);
        JavaLog.log(Level.INFO, "Ajout d'un commerçant: {0} {1} à l'événement", new Object[]{prenom, nom, nomEvenement});
        return commercant.getId();
    }

    /**
     * Supprime un commerçant de l'événement.
     *
     * @param id L'ID du commerçant à supprimer.
     */
    public void supprimerCommercant(String id) {
        if (this.commercants.containsKey(id)) {
            this.commercants.remove(id);
            JavaLog.log(Level.INFO, "Suppression du commerçant avec ID de l'événement", new Object[]{id, nomEvenement});
        }
    }

    /**
     * Initialise la disposition des emplacements pour l'événement.
     *
     * @param longueur La longueur de la disposition.
     * @param largeur  La largeur de la disposition.
     */
    public void initialisationDisposition(int longueur, int largeur) {
        this.disposition = new Emplacement[longueur][largeur];
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                disposition[i][j] = new Emplacement();
            }
        }
        JavaLog.log(Level.INFO, "Initialisation de la disposition pour l'événement avec longueur et largeur", new Object[]{nomEvenement, longueur, largeur});
    }

    /**
     * Définit la disposition des emplacements pour l'événement.
     *
     * @param disposition Un tableau 2D d'emplacements.
     */
    public void setDisposition(Emplacement[][] disposition) {
        this.disposition = disposition;
        JavaLog.log(Level.INFO, "Définition de la disposition pour l'événement ", nomEvenement);
    }

    @Override
    public String toString() {
        String result = this.nomEvenement;
        JavaLog.log(Level.INFO, "Résultat de toString", result);
        return result;
    }

}
