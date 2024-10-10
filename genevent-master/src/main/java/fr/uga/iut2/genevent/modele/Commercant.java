package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.JavaLog;
import fr.uga.iut2.genevent.log.JavaLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * La classe Commercant représente un commerçant participant à un événement.
 * Elle contient des informations telles que le nom, le prénom, l'ID du commerçant,
 * l'événement auquel il participe, et la liste des emplacements attribués au commerçant.
 */
public class Commercant implements Serializable {
    private String nom;
    private String prenom;
    private String id;
    private Evenement evenement;
    private ArrayList<Emplacement> emplacements;

    /**
     * Constructeur pour créer un nouveau commerçant avec le nom, le prénom, et l'événement associé.
     *
     * @param nom       Le nom du commerçant.
     * @param prenom    Le prénom du commerçant.
     * @param evenement L'événement auquel participe le commerçant.
     */
    public Commercant(String nom, String prenom, Evenement evenement) {
        JavaLog.log(Level.INFO, "Création d'un nouveau commerçant: {0} {1}", new Object[]{prenom, nom});
        setNom(nom);
        setPrenom(prenom);
        genereId();
        setEvenement(evenement);
        emplacements = new ArrayList<>();
    }

    /**
     * Renvoie l'ID du commerçant.
     *
     * @return L'ID du commerçant.
     */
    public String getId() {
        JavaLog.log(Level.INFO, "Récupération de l'ID: {0}", id);
        return this.id;
    }

    /**
     * Renvoie l'événement auquel est associé le commerçant.
     *
     * @return L'événement du commerçant.
     */
    public Evenement getEvenement() {
        JavaLog.log(Level.INFO, "Récupération de l'événement: {0}", evenement);
        return evenement;
    }

    /**
     * Renvoie la liste des emplacements attribués au commerçant.
     *
     * @return La liste des emplacements d'un commerçant.
     */
    public ArrayList<Emplacement> getEmplacements() {
        JavaLog.log(Level.INFO, "Récupération des emplacements: {0}", emplacements);
        return emplacements;
    }

    /**
     * Attribue un ID au commerçant.
     *
     * @param id L'ID attribué au commerçant.
     */
    private void setId(String id) {
        JavaLog.log(Level.INFO, "Attribution de l'ID: {0}", id);
        this.id = id;
    }

    /**
     * Attribue un prénom au commerçant.
     *
     * @param prenom Le prénom du commerçant.
     */
    public void setPrenom(String prenom) {
        JavaLog.log(Level.INFO, "Attribution du prénom: {0}", prenom);
        this.prenom = prenom;
    }

    /**
     * Attribue un nom au commerçant.
     *
     * @param nom Le nom du commerçant.
     */
    public void setNom(String nom) {
        JavaLog.log(Level.INFO, "Attribution du nom: {0}", nom);
        this.nom = nom;
    }

    /**
     * Attribue l'événement auquel appartient le commerçant.
     *
     * @param evenement L'événement auquel participe le commerçant.
     */
    public void setEvenement(Evenement evenement) {
        JavaLog.log(Level.INFO, "Attribution de l'événement: {0}", evenement);
        this.evenement = evenement;
    }

    /**
     * Renvoie le nom du commerçant.
     *
     * @return Le nom du commerçant.
     */
    public String getNom() {
        JavaLog.log(Level.INFO, "Récupération du nom: {0}", nom);
        return nom;
    }

    /**
     * Renvoie le prénom du commerçant.
     *
     * @return Le prénom du commerçant.
     */
    public String getPrenom() {
        JavaLog.log(Level.INFO, "Récupération du prénom: {0}", prenom);
        return prenom;
    }

    /**
     * Génére un identifiant (ID) aléatoirement pour le commerçant.
     * Vérifie que l'ID généré est unique.
     */
    public void genereId() {
        StringBuilder idBuilder = new StringBuilder();
        if (this.evenement == null) {
            for (int i = 0; i < 8; i++) {
                int chiffre = (int) (Math.random() * 10);
                idBuilder.append(chiffre);
            }
        } else {
            do {
                idBuilder.setLength(0);
                for (int i = 0; i < 8; i++) {
                    int chiffre = (int) (Math.random() * 10);
                    idBuilder.append(chiffre);
                }
            } while (this.evenement.getCommercants().containsKey(idBuilder.toString()));
        }
        setId(idBuilder.toString());
        JavaLog.log(Level.INFO, "ID généré: {0}", this.id);
    }

    /**
     * Ajoute un emplacement à la liste des emplacements associés au commerçant.
     *
     * @param emplacement L'emplacement à ajouter au commerçant.
     */
    public void ajoutEmplacement(Emplacement emplacement) {
        JavaLog.log(Level.INFO, "Ajout de l'emplacement: {0}", emplacement);
        this.emplacements.add(emplacement);
        if (emplacement.getCommercant() == this) {
            emplacement.setCommercant(this);
        }
    }

/**
 * Supprime un emplacement de la liste des emplacements associés au commerçant.
 *
 * @param emplacement L'emplacement à supprimer des
 * équipements liés au commerçant.
 */
public void supprimetEmplacement(Emplacement emplacement) {
    JavaLog.log(Level.INFO, "Suppression de l'emplacement: {0}", emplacement);
    this.emplacements.remove(emplacement);
    if (emplacement.getCommercant() == this) {
        emplacement.deleteCommercant();
    }
}

    /**
     * Calcule et renvoie le prix que le commerçant doit payer à l'organisateur.
     * Le prix est calculé en fonction du nombre d'emplacements et des caractéristiques
     * de chaque emplacement (eau, gaz, électricité) et du prix associé à chaque caractéristique.
     *
     * @return Le prix calculé que doit payer le commerçant.
     */
    public float getPrix() {
        float prix = 0;
        for (int i = 0; i < emplacements.size(); i++) {
            Emplacement emplacement = this.emplacements.get(i);
            prix = prix + this.evenement.getPrixEmplacement();
            if (emplacement.getEau()) {
                prix = prix + this.evenement.getPrixEau();
            }
            if (emplacement.getGaz()) {
                prix = prix + this.evenement.getPrixGaz();
            }
            if (emplacement.getElec()) {
                prix = prix + this.evenement.getPrixElectricite();
            }
        }
        JavaLog.log(Level.INFO, "Prix calculé: {0}", prix);
        return prix;
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères du commerçant.
     *
     * @return Une chaîne de caractères représentant le commerçant.
     */
    @Override
    public String toString() {
        String result = this.prenom + " " + this.nom;
        JavaLog.log(Level.INFO, "Résultat de toString: {0}", result);
        return result;
    }
}