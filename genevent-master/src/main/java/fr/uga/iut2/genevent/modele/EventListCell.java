package fr.uga.iut2.genevent.modele;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EventListCell est une classe personnalisée pour afficher les informations des événements
 * dans une cellule de ListView.
 */
public class EventListCell extends ListCell<Evenement> {

    private static final Logger LOGGER = Logger.getLogger(EventListCell.class.getName());

    private HBox content;
    private Label nameLabel;
    private Label dateDebutLabel;
    private Label dateFinLabel;
    private Label locationLabel;

    /**
     * Constructeur de la classe EventListCell.
     * Initialise les labels et le layout pour afficher les informations de l'événement.
     */
    public EventListCell() {
        super();
        nameLabel = new Label();
        dateDebutLabel = new Label();
        dateFinLabel = new Label();
        locationLabel = new Label();

        HBox.setHgrow(nameLabel, Priority.ALWAYS);
        HBox.setHgrow(dateDebutLabel, Priority.ALWAYS);
        HBox.setHgrow(dateFinLabel, Priority.ALWAYS);
        HBox.setHgrow(locationLabel, Priority.ALWAYS);

        content = new HBox(nameLabel, dateDebutLabel, dateFinLabel, locationLabel);
        content.setSpacing(10);

        LOGGER.log(Level.INFO, "EventListCell initialisée.");
    }

    /**
     * Met à jour les informations affichées dans la cellule en fonction de l'événement.
     *
     * @param event l'événement à afficher dans la cellule
     * @param empty indique si la cellule doit être vide
     */
    @Override
    protected void updateItem(Evenement event, boolean empty) {
        super.updateItem(event, empty);

        if (event != null && !empty) {
            nameLabel.setText(event.getNomEvenement());
            dateDebutLabel.setText(event.getDateDebut().toString());
            dateFinLabel.setText(event.getDateFin().toString());
            locationLabel.setText(event.getLieuEvenement());
            setGraphic(content);
            LOGGER.log(Level.INFO, "Affichage de l'événement ", event.getNomEvenement());
        } else {
            setGraphic(null);
            LOGGER.log(Level.INFO, "Cellule vide.");
        }
    }
}
