package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.GenEvent;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe Persisteur est responsable de l'enregistrement et de la
 * restauration de l'état du modèle.
 * <p>
 * C'est une classe utilitaire, toutes les méthodes sont statiques.
 * La classe n'a pas vocation a être instanciée.
 *
 */
public final class Persisteur {

    private static final Logger LOGGER = Logger.getLogger(Persisteur.class.getName());
    private static final String NOM_BDD = "persistence/genevent.bdd";

    private Persisteur() {
        // interdit l'instanciation de la classe utilitaire via un constructeur privé
        throw new IllegalStateException("Classe utilitaire.");
    }

    /**
     * Enregistre l'état de l'application dans un fichier persistant.
     * <p>
     * Le fichier de persistance est le fichier "{@value Persisteur#NOM_BDD}".
     *
     * @param genevent L'application dont l'état est persisté.
     *
     * @throws FileNotFoundException si le fichier de persistance est un
     *     dossier, ne peut pas être créé ou ne peut pas être ouvert.
     *
     * @throws IOException si une erreur d'entrée/sortie survient pendant
     *     l'enregistrement.
     */
    public static final void sauverEtat(final GenEvent genevent) throws FileNotFoundException, IOException {
        try (
                FileOutputStream fos = new FileOutputStream(Persisteur.NOM_BDD);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(genevent);
            LOGGER.log(Level.INFO, "Sauvegarde de l'état réussie.");
        }
        catch (FileNotFoundException fnfe) {
            LOGGER.log(Level.SEVERE, "Erreur à la création/ouverture du fichier de persistance.", fnfe);
            throw fnfe;
        }
        catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Erreur lors de l'écriture du fichier de persistance.", ioe);
            throw ioe;
        }
    }

    /**
     * Alimente une instance d'application avec l'état du fichier de
     * persistance.
     * <p>
     * Le fichier de persistance est le fichier "{@value Persisteur#NOM_BDD}".
     *
     * @return Une nouvelle instance vierge d'application si le fichier de
     *     persistance n'existe pas, une instance dans l'état enregistré sinon.
     *
     * @throws ClassNotFoundException si le fichier de persistance contient une
     *     classe inconnue (fichier corrompu).
     *
     * @throws IOException si le fichier de persistance est corrompu ou qu'une
     *     erreur d'entrée/sortie survient.
     */
    public static final GenEvent lireEtat() throws ClassNotFoundException, IOException {
        GenEvent genevent;

        try (
                FileInputStream fis = new FileInputStream(Persisteur.NOM_BDD);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            genevent = (GenEvent) ois.readObject();
            LOGGER.log(Level.INFO, "Restauration de l'état réussie.");
        }
        catch (FileNotFoundException ignored) {
            LOGGER.log(Level.INFO, "Fichier de persistance inexistant : création d'une nouvelle instance.");
            genevent = new GenEvent();
        }
        catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Erreur de lecture du fichier de persistance.", ioe);
            throw ioe;
        }
        catch (ClassNotFoundException cnfe) {
            LOGGER.log(Level.SEVERE, "Fichier de persistance corrompu.", cnfe);
            throw cnfe;
        }

        return genevent;
    }
}
