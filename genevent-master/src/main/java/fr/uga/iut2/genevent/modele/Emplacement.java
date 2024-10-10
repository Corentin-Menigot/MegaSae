package fr.uga.iut2.genevent.modele;

import fr.uga.iut2.genevent.log.JavaLog;


import java.io.Serializable;
import java.util.logging.Level;

public class Emplacement implements Serializable {
    private String type;
    private boolean gaz;
    private boolean eau;
    private boolean electricite;
    private Commercant commercant;

    public Emplacement(boolean eau, boolean gaz, boolean electricite) {
        setEau(eau);
        setGaz(gaz);
        setElectricite(electricite);
        setType("vide");
        JavaLog.log(Level.INFO, "Création d'un nouvel emplacement: eau, gaz, electricite", new Object[]{eau, gaz, electricite});
    }

    public Emplacement() {
        // constructeur pour les valeurs par défaut d'un emplacement
        setEau(false);
        setGaz(false);
        setElectricite(false);
        setType("vide");
        JavaLog.log(Level.INFO, "Création d'un nouvel emplacement avec valeurs par défaut.");
    }

    /**
     * Renvoie si l'emplacement posséde de l'électricité ou non
     *
     * @return true si il y a de l'électricité, false sinon
     */
    public boolean getElec() {
        JavaLog.log(Level.INFO, "Récupération de l'état électricité", electricite);
        return electricite;
    }

    /**
     * Renvoie si l'emplacement posséde de l'eau ou non
     *
     * @return true si il y a de l'eau, false sinon
     */
    public boolean getEau() {
        JavaLog.log(Level.INFO, "Récupération de l'état eau", eau);
        return this.eau;
    }

    /**
     * Renvoie si l'emplacement posséde du gaz ou non
     *
     * @return true si il y a du gaz, false sinon
     */
    public boolean getGaz() {
        JavaLog.log(Level.INFO, "Récupération de l'état gaz", gaz);
        return this.gaz;
    }

    /**
     * Renvoie le type de l'emplacement (chemin, toilette...)
     *
     * @return le type associé à l'emplacement
     */
    public String getType() {
        JavaLog.log(Level.INFO, "Récupération du type d'emplacement", type);
        return this.type;
    }

    /**
     * Renvoie le commerçant associé à l'emplacement, s'il y en a un
     *
     * @return le commerçant associé à l'emplacement
     */
    public Commercant getCommercant() {
        JavaLog.log(Level.INFO, "Récupération du commerçant associé", commercant);
        return this.commercant;
    }

    /**
     * Permet de définir si l'emplacement posséde de l'électricité
     *
     * @param electricite -> valeur booléenne de la présence d'électricité ou non
     */
    public void setElectricite(boolean electricite) {
        this.electricite = electricite;
        JavaLog.log(Level.INFO, "Définition de l'état électricité", electricite);
    }

    /**
     * Permet de définir si l'emplacement posséde de l'eau
     *
     * @param eau -> valeur booléenne de la présence d'eau ou non
     */
    public void setEau(boolean eau) {
        this.eau = eau;
        JavaLog.log(Level.INFO, "Définition de l'état eau", eau);
    }

    /**
     * Permet de définir si l'emplacement posséde du gaz
     *
     * @param gaz -> valeur booléenne de la présence de gaz ou non
     */
    public void setGaz(boolean gaz) {
        this.gaz = gaz;
        JavaLog.log(Level.INFO, "Définition de l'état gaz", gaz);
    }

    /**
     * Attribue un type d'emplacement (arbre, chemin, etc.) à l'emplacement
     *
     * @param type -> le type d'emplacement qu'on attribue à l'emplacement
     */
    public void setType(String type) {
        this.type = type;
        JavaLog.log(Level.INFO, "Définition du type d'emplacement", type);
    }

    /**
     * Attribue un commerçant à l'emplacement. Ce commerçant posséde aussi cet emplacement
     *
     * @param commercant -> commerçant qu'on attribue à l'emplacement
     */
    public void setCommercant(Commercant commercant) {
        this.commercant = commercant;
        if (!commercant.getEmplacements().contains(this)) {
            this.commercant.ajoutEmplacement(this);
        }
        JavaLog.log(Level.INFO, "Définition du commerçant associé", commercant);
    }

    /**
     * Supprime le commerçant associé à l'emplacement
     */
    public void deleteCommercant() {
        Commercant commercant = this.commercant;
        this.commercant = null;
        if (commercant.getEmplacements().contains(this)) {
            commercant.supprimetEmplacement(this);
        }
        JavaLog.log(Level.INFO, "Suppression du commerçant associé.");
    }
}
