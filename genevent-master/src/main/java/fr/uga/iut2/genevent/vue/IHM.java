package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Commercant;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.GenEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;


public abstract class IHM {


    public abstract void ajouterCommercantControleur();

    /**
     * Classe conteneur pour les informations saisies à propos d'un
     * {@link fr.uga.iut2.genevent.modele.Utilisateur}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosUtilisateur {
        public final String email;

        public final String nom;
        public final String prenom;
        public final String motDePasse;

        public InfosUtilisateur(final String email, final String nom, final String prenom, String motDePasse) {
            this.email = email;
            this.nom = nom;
            this.prenom = prenom;
            this.motDePasse = motDePasse;
        }
    }

    /**
     * Classe conteneur pour les informations saisies pour un nouvel
     * {@link fr.uga.iut2.genevent.modele.Evenement}.
     *
     * <ul>
     * <li>Tous les attributs sont `public` par commodité d'accès.</li>
     * <li>Tous les attributs sont `final` pour ne pas être modifiables.</li>
     * </ul>
     */
    public static class InfosNouvelEvenement {
        public final String nom;
        public final String lieu;
        public final LocalDate dateDebut;
        public final LocalDate dateFin;
        public final int prixEau;
        public final int prixGaz;
        public final int prixElec;
        public final int prixEmplacement;
        public final int longeur;
        public final int largeur;



        public InfosNouvelEvenement(final String nom,
                final String lieu,
                                    final DatePicker dateDebut,
                                    final DatePicker dateFin,
                                    final int prixEau,
                                    final int prixElec,
                                    final int prixGaz,
                                    final int prixEmplacement,
                                    final int longeur,
                                    final int largeur
        ) {
            assert !dateDebut.getValue().isAfter(dateFin.getValue());
            this.nom = nom;
            this.lieu = lieu;
            this.dateDebut = dateDebut.getValue();
            this.dateFin = dateFin.getValue();
            this.prixEau = prixEau;
            this.prixElec = prixElec;
            this.prixGaz = prixGaz;
            this.prixEmplacement = prixEmplacement;
            this.longeur = longeur;
            this.largeur = largeur;
        }
    }

    public static class InfosCommercant{
        String nom;
        String prenom;

        public InfosCommercant(String nom,String prenom) {
            this.nom=nom;
            this.prenom=prenom;
        }
    }

    /**
     * Rend actif l'interface Humain-machine.
     *
     * L'appel est bloquant : le contrôle est rendu à l'appelant une fois que
     * l'IHM est fermée.
     *
     */
    public abstract void demarrerInteraction();

    /**
     * Affiche un message d'information à l'attention de l'utilisa·teur/trice.
     *
     * @param msg Le message à afficher.
     *
     * @param succes true si le message informe d'une opération réussie, false
     *     sinon.
     */
    public abstract void informerUtilisateur(final String msg, final boolean succes);

    /**
     * Récupère les informations à propos d'un
     * {@link fr.uga.iut2.genevent.modele.Utilisateur}.
     *
     */
    public abstract void saisirUtilisateur();

    public abstract void saisirNouvelEvenement();

    public abstract void loginUtilisateur(ActionEvent event);

    public abstract void archiveButton();

    public abstract void consulterButton();

    public abstract void dupliquerButton();

    public abstract void ajouterEvenementButton();

    public abstract void genereDetailedMap();

    public abstract void generationMatrice(GenEvent genevent);

    public abstract void supprimeList();

    public abstract  void ajouteList();

    public abstract void afficherDetailCommercant(Commercant commercant);

    public abstract void ajouterCommercantVue();

    public abstract void afficherDetailCommercantVue();


    public abstract void archiverButton();
}
