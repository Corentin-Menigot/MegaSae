package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import fr.uga.iut2.genevent.modele.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * La classe JavaFXGUI est responsable des interactions avec l'utilisateur/trice en mode graphique.
 * <p>
 * Attention, pour pouvoir faire le lien avec le {@link fr.uga.iut2.genevent.controleur.Controleur}, JavaFXGUI
 * n'est pas une sous-classe de {@link javafx.application.Application} !
 * <p>
 * Le démarrage de l'application diffère des exemples classiques trouvés dans la documentation de JavaFX :
 * l'interface est démarrée à l'initiative du {@link fr.uga.iut2.genevent.controleur.Controleur} via l'appel
 * de la méthode {@link #demarrerInteraction()}.
 */
public class JavaFXGUI extends IHM {

    private static final Logger LOGGER = Logger.getLogger(JavaFXGUI.class.getName());

    private final Controleur controleur;
    private final CountDownLatch eolBarrier;  // /!\ ne pas supprimer /!\ : suivi de la durée de vie de l'interface

    // éléments vue nouvel·le utilisa·teur/trice
    @FXML
    private TextField newUserForenameTextField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private TextField newUserSurnameTextField;
    @FXML
    private TextField newUserEmailTextField;
    @FXML
    private Button newUserOkButton;
    @FXML
    private Button passwordOkButton;
    @FXML
    private TextField userEmailTextField;
    @FXML
    private Button newUserCancelButton;
    @FXML
    private Button validedButton;
    @FXML
    private PasswordField newUserPassWordField;
    @FXML
    private TextField nomEvene;
    @FXML
    private TextField localisationEvene;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField prixGaz;
    @FXML
    private TextField prixEau;
    @FXML
    private TextField prixElec;
    @FXML
    private CheckBox checkBoxGaz;
    @FXML
    private CheckBox checkBoxEau;
    @FXML
    private CheckBox checkBoxElec;
    @FXML
    private Button newEvenementCancelButton;
    @FXML
    private TextField pirxEvenement;
    @FXML
    private TextField rowsField;
    @FXML
    private TextField colsField;
    @FXML
    private Button generateButton;
    @FXML
    private GridPane matrixGrid;
    @FXML
    private ListView<Evenement> evenementListView;
    @FXML
    private ListView<Commercant> commercantsListView;
    @FXML
    private Button testt;
    @FXML
    private ObservableList<Evenement> listeEvent;
    @FXML
    private ObservableList<Commercant> listeCommercants;
    @FXML
    private GridPane showmatrix;
    @FXML
    private Button aproposCommercantButton;
    @FXML
    private TextField commercantNomTextfield;
    @FXML
    private TextField commercantPrenomTextField;
    @FXML
    private Button terminerCommercantButton;
    @FXML
    private Button validerCommercantButton;
    @FXML
    private Button ajouterCommercantButton;
    @FXML
    private Label labelNomCommercant, labelPrenomCommercant, labelNumIdCommercant, labelPrixCommercant;
    @FXML
    private ListView<Commercant> commercantListView;
    @FXML
    private ListView<Evenement> archivedEventListView;
    @FXML private Button archiver;
    @FXML private Label nomEvenementLabel;
    @FXML private Label localisationLabel;
    @FXML private Label dateDebutLabel;
    @FXML private Label dateFinLabel;
    @FXML private Label prixEauLabel;
    @FXML private Label prixGazLabel;
    @FXML private Label prixElecLabel;
    @FXML private Label longueurLabel;
    @FXML private Label largeurLabel;
    private ObservableList<Commercant> commercantList = FXCollections.observableArrayList();
    private ObservableList<Evenement> archivedEventList;
    private static final double GRID_WIDTH = 500.0; // Définir la largeur fixe pour la grille
    private List<Button> matrixButtons = new ArrayList<>();
    private Evenement currentEvenement;
    private List<Emplacement> emplacements = new ArrayList<>();
    private Map<Evenement, Evenement> archivedEventDetails;

    /**
     * Constructeur de la classe JavaFXGUI.
     * Initialise le contrôleur et la barrière de fin de vie de l'interface.
     *
     * @param controleur le contrôleur de l'application
     */
    public JavaFXGUI(Controleur controleur) {
        this.controleur = controleur;
        this.eolBarrier = new CountDownLatch(1);  // /!\ ne pas supprimer /!\
        LOGGER.log(Level.INFO, "JavaFXGUI initialisée.");
    }

    /**
     * Point d'entrée principal pour le code de l'interface JavaFX.
     *
     * @param primaryStage stage principale de l'interface JavaFX, sur laquelle définir des scènes.
     * @throws IOException si le chargement de la vue FXML échoue.
     * @see javafx.application.Application#start(Stage)
     */
    private void start(Stage primaryStage) throws IOException {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        mainViewLoader.setController(this);
        Scene mainScene = new Scene(mainViewLoader.load());

        primaryStage.setTitle("GenEvent");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        LOGGER.log(Level.INFO, "Interface JavaFX démarrée.");
    }

    //-----  Éléments du dialogue  -------------------------------------------------

    private void exitAction() {
        // fermeture de l'interface JavaFX : on notifie sa fin de vie
        this.eolBarrier.countDown();
        LOGGER.log(Level.INFO, "Interface JavaFX fermée.");
    }

    // menu principal  -----

    @FXML
    private void newUserMenuItemAction() {
        this.controleur.saisirUtilisateur();
        LOGGER.log(Level.INFO, "Action de menu 'Nouvel Utilisateur' sélectionnée.");
    }

    @FXML
    private void validerUtilisateur() {
        IHM.InfosUtilisateur data = new IHM.InfosUtilisateur(
                this.newUserEmailTextField.getText().strip().toLowerCase(),
                this.newUserSurnameTextField.getText().strip(),
                this.newUserForenameTextField.getText().strip(),
                this.newUserPassWordField.getText().strip());
        this.controleur.validerUtilisateur(data);
        LOGGER.log(Level.INFO, "Utilisateur validé: {0}", data.email);
    }

    @FXML
    private void exitMenuItemAction() {
        Platform.exit();
        this.exitAction();
    }

    // vue nouvel·le utilisa·teur/trice  -----

    @FXML
    private void createNewUserAction() {
        IHM.InfosUtilisateur data = new IHM.InfosUtilisateur(
                this.newUserEmailTextField.getText().strip().toLowerCase(),
                this.newUserSurnameTextField.getText().strip(),
                this.newUserForenameTextField.getText().strip(),
                this.newUserPassWordField.getText().strip());
        this.controleur.creerUtilisateur(data);
        this.newUserOkButton.getScene().getWindow().hide();
        LOGGER.log(Level.INFO, "Nouvel utilisateur créé: {0}", data.email);
    }

    @FXML
    private void cancelNewUserAction() {
        this.newUserCancelButton.getScene().getWindow().hide();
        LOGGER.log(Level.INFO, "Création de nouvel utilisateur annulée.");
    }

    @FXML
    private void cancelNewEvenmentAction() {
        this.newEvenementCancelButton.getScene().getWindow().hide();
        LOGGER.log(Level.INFO, "Création de nouvel événement annulée.");
    }

    @FXML
    private void validernMap() {
        IHM.InfosNouvelEvenement data = new IHM.InfosNouvelEvenement(
                this.nomEvene.getText().strip(),
                this.localisationEvene.getText().strip(),
                this.dateDebut,
                this.dateFin,
                Integer.parseInt(this.prixEau.getText()),
                Integer.parseInt(this.prixElec.getText()),
                Integer.parseInt(this.prixGaz.getText()),
                Integer.parseInt(this.pirxEvenement.getText()),
                Integer.parseInt(this.rowsField.getText()),
                Integer.parseInt(this.colsField.getText())
        );
        this.controleur.creerEvenement(data);
        LOGGER.log(Level.INFO, "Nouvel événement validé", data.nom);
    }

    @FXML
    private void createNewEvement() {
        IHM.InfosUtilisateur data = new IHM.InfosUtilisateur(
                this.newUserEmailTextField.getText().strip().toLowerCase(),
                this.newUserSurnameTextField.getText().strip(),
                this.newUserForenameTextField.getText().strip(),
                this.newUserPassWordField.getText().strip());
        this.controleur.creerUtilisateur(data);
        this.newUserOkButton.getScene().getWindow().hide();
        LOGGER.log(Level.INFO, "Nouvel utilisateur créé: {0}", data.email);
    }

    @FXML
    private void validateTextFieldsLogin() {
        boolean isValid = true;

        isValid &= validateEmailTextField(this.userEmailTextField);
        isValid &= validateNonEmptyTextField(this.userPasswordField);

        this.passwordOkButton.setDisable(!isValid);
        LOGGER.log(Level.INFO, "Validation des champs de texte pour le login, validité:", isValid);
    }

    @FXML
    private void validateTextFields() {
        boolean isValid = true;

        isValid &= validateNonEmptyTextField(this.newUserForenameTextField);
        isValid &= validateNonEmptyTextField(this.newUserSurnameTextField);
        isValid &= validateEmailTextField(this.newUserEmailTextField);

        this.newUserOkButton.setDisable(!isValid);
        LOGGER.log(Level.INFO, "Validation des champs de texte pour un nouvel utilisateur, validité", isValid);
    }

    private static void markTextFieldErrorStatus(TextField textField, boolean isValid) {
        if (isValid) {
            textField.setStyle(null);
        } else {
            textField.setStyle("-fx-control-inner-background: f8d7da");
        }
    }

    private static boolean validateNonEmptyTextField(TextField textField) {
        boolean isValid = textField.getText().strip().length() > 0;

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

    @FXML
    public void supprimeList() {
        // Obtenir les éléments sélectionnés
        ObservableList<Evenement> selectedEvents = evenementListView.getSelectionModel().getSelectedItems();
        ObservableList<Commercant> selectedCommercants = commercantsListView.getSelectionModel().getSelectedItems();

        if (!selectedEvents.isEmpty() || !selectedCommercants.isEmpty()) {
            // Créer une alerte de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Voulez-vous vraiment supprimer les éléments sélectionnés ?");
            alert.setContentText("Cette action est irréversible.");

            // Attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer les événements sélectionnés
                if (!selectedEvents.isEmpty()) {
                    ArrayList<Evenement> eventsToRemove = new ArrayList<>(selectedEvents);
                    for (Evenement event : eventsToRemove) {
                        controleur.getGenEvent().supprimerEvenement(event);  // Assurez-vous que cette ligne correspond à votre méthode réelle pour obtenir GenEvent
                    }
                    listeEvent.removeAll(eventsToRemove);
                    evenementListView.setItems(null);
                    evenementListView.setItems(listeEvent);
                    LOGGER.log(Level.INFO, "Événements supprimés", eventsToRemove);
                }

                // Supprimer les commerçants sélectionnés
                if (!selectedCommercants.isEmpty()) {
                    ArrayList<Commercant> commercantsToRemove = new ArrayList<>(selectedCommercants);
                    listeCommercants.removeAll(commercantsToRemove);
                    commercantsListView.setItems(null);
                    commercantsListView.setItems(listeCommercants);
                    LOGGER.log(Level.INFO, "Commerçants supprimés: {0}", commercantsToRemove);
                }
            }
        } else {
            // Afficher une alerte d'information si aucun élément n'est sélectionné
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Aucun élément sélectionné");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Veuillez sélectionner des éléments à supprimer.");
            infoAlert.showAndWait();
            LOGGER.log(Level.WARNING, "Aucun élément sélectionné pour suppression.");
        }
    }

    private static boolean validateEmailTextField(TextField textField) {
        EmailValidator validator = EmailValidator.getInstance(false, false);
        boolean isValid = validator.isValid(textField.getText().strip().toLowerCase());

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

    @FXML
    private void login(ActionEvent event) {
        this.controleur.login(event);
        LOGGER.log(Level.INFO, "Action de login effectuée.");
    }

    @FXML
    private void genereMatrice() {
        this.controleur.genereMatrice();
        LOGGER.log(Level.INFO, "Génération de matrice initiée.");
    }

    @FXML
    public void ajouteList() {
        listeEvent = FXCollections.observableArrayList(controleur.addListEvent(currentEvenement));
        evenementListView.setItems(listeEvent);
        this.validedButton.getScene().getWindow().hide();
        LOGGER.log(Level.INFO, "Événement ajouté à la liste: {0}", currentEvenement);
    }

    @FXML
    private void genereAutoMatrice() {
        this.controleur.genereAutoMatrice();
        LOGGER.log(Level.INFO, "Génération automatique de matrice initiée.");
    }

    @FXML
    private void evenement() {
        this.controleur.Evenement();
        LOGGER.log(Level.INFO, "Action pour ajouter un événement initiée.");
    }

    //-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void demarrerInteraction() {
        // démarrage de l'interface JavaFX
        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            primaryStage.setOnCloseRequest((WindowEvent t) -> this.exitAction());
            try {
                this.start(primaryStage);
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        });

        // attente de la fin de vie de l'interface JavaFX
        try {
            this.eolBarrier.await();
        } catch (InterruptedException exc) {
            LOGGER.log(Level.SEVERE, "Erreur d'exécution de l'interface.", exc);
        }
    }

    @Override
    public void informerUtilisateur(String msg, boolean succes) {
        final Alert alert = new Alert(
                succes ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING
        );
        alert.setTitle("GenEvent");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        LOGGER.log(Level.INFO, "Message informatif affiché: {0}", msg);
    }

    @Override
    public void saisirUtilisateur() {
        try {
            FXMLLoader newUserViewLoader = new FXMLLoader(getClass().getResource("new-user-view.fxml"));
            newUserViewLoader.setController(this);
            Scene newUserScene = new Scene(newUserViewLoader.load());

            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Créer un·e utilisa·teur/trice");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(newUserScene);
            newUserWindow.showAndWait();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        LOGGER.log(Level.INFO, "Vue de saisie utilisateur affichée.");
    }

    @Override
    public void saisirNouvelEvenement() {
        try {
            FXMLLoader newEvementViewLoader = new FXMLLoader(getClass().getResource("UC2-2-view.fxml"));
            newEvementViewLoader.setController(this);
            Scene newUserScene = new Scene(newEvementViewLoader.load());

            Stage newEvenementWindow = new Stage();
            newEvenementWindow.setTitle("Créer un événement");
            newEvenementWindow.initModality(Modality.APPLICATION_MODAL);
            newEvenementWindow.setScene(newUserScene);
            newEvenementWindow.showAndWait();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        LOGGER.log(Level.INFO, "Vue de saisie d'un nouvel événement affichée.");
    }

    @Override
    public void loginUtilisateur(ActionEvent event) {
        if (controleur.verifierLoginMdp(this.userEmailTextField.getText().strip(), this.userPasswordField.getText().strip())) {
            try {
                FXMLLoader newUserViewLoader = new FXMLLoader(getClass().getResource("UC2-1-view.fxml"));
                newUserViewLoader.setController(this);
                Scene homePageScene = new Scene(newUserViewLoader.load());

                listeEvent = FXCollections.observableArrayList(controleur.initListEvent());
                evenementListView.setItems(listeEvent);

                evenementListView.setOnMouseClicked(event1 -> {
                    if (event1.getClickCount() == 1) {
                        Evenement selectedEvenement = evenementListView.getSelectionModel().getSelectedItem();
                        if (selectedEvenement != null) {
                            evenementSelectionne(selectedEvenement);
                            afficherMatrice(selectedEvenement); // Afficher la matrice associée
                        }
                    }
                });

                ((Stage) userPasswordField.getScene().getWindow()).setScene(homePageScene);
                LOGGER.log(Level.INFO, "Utilisateur connecté: {0}", this.userEmailTextField.getText().strip());
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        } else {
            this.informerUtilisateur("Mauvais identifiants", false);
            LOGGER.log(Level.WARNING, "Échec de connexion pour l'utilisateur: {0}", this.userEmailTextField.getText().strip());
        }
    }

    private void evenementSelectionne(Evenement evenement) {
        currentEvenement = evenement;
        listeCommercants = FXCollections.observableArrayList(evenement.getCommercants().values());
        commercantsListView.setItems(listeCommercants);
        LOGGER.log(Level.INFO, "Événement sélectionné: {0}", evenement.getNomEvenement());
    }

    @Override
    public void generationMatrice(GenEvent genEvent) {
        String rowsInput = rowsField.getText();
        String colsInput = colsField.getText();

        if (!rowsInput.isEmpty() && !colsInput.isEmpty()) {
            try {
                int rows = Integer.parseInt(rowsInput);
                int cols = Integer.parseInt(colsInput);
                if (rows > 0 && cols > 0) {
                    int eauInt = parseInt(prixEau.getText());
                    int elecInt = parseInt(prixElec.getText());
                    int gazInt = parseInt(prixGaz.getText());
                    int empaInt = eauInt + eauInt + gazInt;

                    currentEvenement = new Evenement(
                            nomEvene.getText(),
                            dateDebut.getValue(),
                            dateFin.getValue(),
                            localisationEvene.getText(),
                            eauInt,
                            gazInt,
                            elecInt,
                            empaInt,
                            rows,
                            cols
                    );

                    generateMatrix(cols, rows);
                    LOGGER.log(Level.INFO, "Matrice générée pour l'événement: {0}", currentEvenement.getNomEvenement());
                } else {
                    showError("Veuillez entrer des nombres positifs.");
                    LOGGER.log(Level.WARNING, "Les valeurs de lignes et colonnes doivent être positives.");
                }
            } catch (NumberFormatException ex) {
                showError("Veuillez entrer des nombres valides.");
                LOGGER.log(Level.SEVERE, "Erreur de format numérique pour les dimensions de la matrice.", ex);
            }
        } else {
            showError("Les champs de saisie ne peuvent pas être vides.");
            LOGGER.log(Level.WARNING, "Les champs de saisie ne peuvent pas être vides.");
        }

        matrixGrid.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");
    }

    private Integer parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Erreur de conversion de nombre: {0}", string);
            return null;
        }
    }

    private void generateMatrix(int cols, int rows) {
        matrixGrid.getChildren().clear();
        matrixGrid.getColumnConstraints().clear();
        matrixGrid.getRowConstraints().clear();
        matrixGrid.setPrefWidth(GRID_WIDTH);

        double buttonWidth = GRID_WIDTH / cols;
        matrixButtons.clear();
        currentEvenement.initialisationDisposition(rows, cols); // Réinitialiser la disposition

        for (int i = 0; i < cols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPrefWidth(buttonWidth);
            colConstraints.setPercentWidth(100.0 / cols);
            matrixGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / rows);
            matrixGrid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                Button cellButton = new Button("case " + (index + 1));
                cellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                cellButton.setMinWidth(buttonWidth);
                cellButton.setStyle("-fx-border-color: black; -fx-background-color: white;");

                Emplacement emplacement = new Emplacement(false, false, false);

                currentEvenement.getDisposition()[i][j] = emplacement; // Ajouter à la disposition

                // Colorer la cellule en fonction de son type
                switch (emplacement.getType()) {
                    case "nourriture":
                        cellButton.setStyle("-fx-background-color: orange;");
                        break;
                    case "toilettes":
                        cellButton.setStyle("-fx-background-color: green;");
                        break;
                    default:
                        cellButton.setStyle("-fx-background-color: white;");
                        break;
                }

                matrixButtons.add(cellButton);
                matrixGrid.add(cellButton, j, i);
            }
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        LOGGER.log(Level.SEVERE, "Erreur affichée: {0}", message);
    }

    @Override
    public void genereDetailedMap() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            generateDetailedMap(rows, cols);
            LOGGER.log(Level.INFO, "Carte détaillée générée.");
        } catch (NumberFormatException e) {
            showError("Veuillez entrer des nombres valides pour les lignes et les colonnes.");
            LOGGER.log(Level.SEVERE, "Erreur de format numérique pour les dimensions de la carte détaillée.", e);
        }
    }

    public void generateDetailedMap(int cols, int rows) {
        matrixGrid.getChildren().clear();
        matrixGrid.getColumnConstraints().clear();
        matrixGrid.getRowConstraints().clear();
        matrixGrid.setPrefWidth(GRID_WIDTH);

        double buttonWidth = GRID_WIDTH / cols;
        matrixButtons.clear();
        currentEvenement.initialisationDisposition(rows, cols);

        for (int i = 0; i < cols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPrefWidth(buttonWidth);
            colConstraints.setPercentWidth(100.0 / cols);
            matrixGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / rows);
            matrixGrid.getRowConstraints().add(rowConstraints);
        }

        if (cols < 7 && rows < 7) {
            addFixedSpecialCells(cols, rows);
        } else {
            addRandomSpecialCells(cols, rows);
        }
    }

    private void addFixedSpecialCells(int cols, int rows) {
        List<String> types = Arrays.asList("toilettes", "nourriture", "fontaine");
        Collections.shuffle(types);

        int[][] fixedPositions = {{0, 0}, {0, cols - 1}, {rows - 1, cols - 1}};

        for (int i = 0; i < types.size(); i++) {
            int[] pos = fixedPositions[i];
            addCell(pos[0], pos[1], types.get(i), getColorForType(types.get(i)));
        }

        // Ajouter des chemins et des étals
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!isSpecialCell(i, j, fixedPositions)) {
                    if (isPathCell(i, j, cols, rows)) {
                        addCell(i, j, "chemin", getColorForType("chemin"));
                    } else {
                        addCell(i, j, "stand", getColorForType("stand"));
                    }
                }
            }
        }
    }

    private void addRandomSpecialCells(int cols, int rows) {
        // Allouer des types aléatoires à un certain pourcentage de cellules
        double allocationPercentage = 0.2; // 20% des cellules seront des types spéciaux
        int totalCells = cols * rows;
        int specialCellCount = (int) (totalCells * allocationPercentage);

        Set<Integer> specialCellIndexes = generateRandomIndexes(totalCells, specialCellCount);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                if (isPathCell(i, j, cols, rows)) {
                    addCell(i, j, "chemin", getColorForType("chemin"));
                } else if (specialCellIndexes.contains(index)) {
                    String type = getRandomType();
                    addCell(i, j, type, getColorForType(type));
                } else {
                    addCell(i, j, "stall", getColorForType("stall"));
                }
            }
        }
    }

    private void addCell(int row, int col, String type, String couleur) {
        Button cellButton = new Button(type);
        cellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cellButton.setStyle("-fx-border-color: black; -fx-background-color: " + couleur + ";");

        Emplacement emplacement = new Emplacement(false, false, false);
        emplacement.setType(type);
        currentEvenement.getDisposition()[row][col] = emplacement;

        matrixButtons.add(cellButton);
        matrixGrid.add(cellButton, col, row);
    }

    private boolean isPathCell(int row, int col, int cols, int rows) {
        return row % 3 == 0 || col % 3 == 0;
    }

    private boolean isSpecialCell(int row, int col, int[][] specialPositions) {
        for (int[] pos : specialPositions) {
            if (pos[0] == row && pos[1] == col) {
                return true;
            }
        }
        return false;
    }

    private Set<Integer> generateRandomIndexes(int totalCells, int count) {
        Set<Integer> indexes = new HashSet<>();
        Random random = new Random();
        while (indexes.size() < count) {
            indexes.add(random.nextInt(totalCells));
        }
        return indexes;
    }

    private String getRandomType() {
        String[] types = {"nourriture", "toilettes", "loisir", "musique", "fontaine"};
        return types[(int) (Math.random() * types.length)];
    }

    private String getColorForType(String type) {
        switch (type) {
            case "nourriture":
                return "orange";
            case "toilettes":
                return "green";
            case "loisir":
                return "blue";
            case "musique":
                return "yellow";
            case "fontaine":
                return "cyan";
            case "chemin":
                return "grey";
            default:
                return "white";
        }
    }

    @Override
    public void afficherDetailCommercant(Commercant commercant) {
        try {
            FXMLLoader newUserViewLoader = new FXMLLoader(getClass().getResource("UC8-view.fxml"));
            newUserViewLoader.setController(this);
            Scene newUserScene = new Scene(newUserViewLoader.load());

            labelNomCommercant.setText(commercant.getNom());
            labelPrenomCommercant.setText(commercant.getPrenom());
            labelNumIdCommercant.setText(commercant.getId());

            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("À propos du commerçant");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(newUserScene);
            newUserWindow.showAndWait();

        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        LOGGER.log(Level.INFO, "Détails du commerçant affichés: {0} {1}", new Object[]{commercant.getNom(), commercant.getPrenom()});
    }

    @FXML
    public void initializeCommercant() {
        commercantListView.setItems(commercantList);
        commercantListView.setCellFactory(param -> new ListCell<Commercant>() {
            @Override
            protected void updateItem(Commercant commercant, boolean empty) {
                super.updateItem(commercant, empty);
                if (empty || commercant == null || commercant.getNom() == null) {
                    setText(null);
                } else {
                    setText(commercant.getNom() + " " + commercant.getPrenom());
                }
            }
        });

        commercantListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Commercant selectedCommercant = commercantListView.getSelectionModel().getSelectedItem();
                if (selectedCommercant != null) {
                    afficherDetailCommercant(selectedCommercant);
                }
            }
        });
        LOGGER.log(Level.INFO, "Liste des commerçants initialisée.");
    }

    @Override
    public void ajouterCommercantVue() {
        this.controleur.ajouterCommercantControleur();
        LOGGER.log(Level.INFO, "Vue pour ajouter un commerçant affichée.");
    }

    @Override
    public void afficherDetailCommercantVue() {
        this.controleur.detailCommercantControleur();
        LOGGER.log(Level.INFO, "Vue pour afficher les détails d'un commerçant affichée.");
    }

    @FXML
    private void validateCommercantTextFields() {
        boolean isValid = true;

        isValid &= validateNonEmptyTextField(this.commercantNomTextfield);
        isValid &= validateNonEmptyTextField(this.commercantPrenomTextField);

        this.terminerCommercantButton.setDisable(!isValid);
        LOGGER.log(Level.INFO, "Validation des champs de texte pour un commerçant, validité: {0}", isValid);
    }

    @Override
    public void ajouterCommercantControleur() {
        if (currentEvenement != null) {
            try {
                FXMLLoader newUserViewLoader = new FXMLLoader(getClass().getResource("UC7-view.fxml"));
                newUserViewLoader.setController(this);
                Scene newUserScene = new Scene(newUserViewLoader.load());

                Stage newUserWindow = new Stage();
                newUserWindow.setTitle("Ajouter un commerçant");
                newUserWindow.initModality(Modality.APPLICATION_MODAL);
                newUserWindow.setScene(newUserScene);
                newUserWindow.showAndWait();

            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
            LOGGER.log(Level.INFO, "Vue pour ajouter un commerçant affichée.");
        } else {
            informerUtilisateur("Pas d'événement sélectionné auquel ajouter un commerçant", false);
            LOGGER.log(Level.WARNING, "Aucun événement sélectionné pour ajouter un commerçant.");
        }
    }

    @FXML
    private void validerCommercant() {
        if (!validateNonEmptyTextField(commercantNomTextfield) && !validateNonEmptyTextField(commercantPrenomTextField)) {
            informerUtilisateur("Pour avoir un Identifiant il faut que vous entriez votre nom et prénom.", false);
            LOGGER.log(Level.WARNING, "Nom et prénom du commerçant non fournis.");
        } else if (!validateNonEmptyTextField(commercantNomTextfield)) {
            informerUtilisateur("Entrez votre nom.", false);
            LOGGER.log(Level.WARNING, "Nom du commerçant non fourni.");
        } else if (!validateNonEmptyTextField(commercantPrenomTextField)) {
            informerUtilisateur("Entrez votre prénom.", false);
            LOGGER.log(Level.WARNING, "Prénom du commerçant non fourni.");
        } else {
            Commercant commercant = controleur.creerCommercant(currentEvenement, commercantNomTextfield.getText().strip(), commercantPrenomTextField.getText().strip());
            commercantList.add(commercant);
            LOGGER.log(Level.INFO, "Commerçant validé et ajouté: {0} {1}", new Object[]{commercant.getNom(), commercant.getPrenom()});
        }
    }

    @FXML
    private void aproposCommercantTerminer() {
        Stage stage = (Stage) labelNomCommercant.getScene().getWindow();
        stage.close();
        LOGGER.log(Level.INFO, "Détails du commerçant fermés.");
    }

    @FXML
    private void ajouterCommercantTerminer() {
        evenementSelectionne(currentEvenement);
        Stage stage = (Stage) commercantNomTextfield.getScene().getWindow();
        stage.close();
        LOGGER.log(Level.INFO, "Ajout de commerçant terminé.");
    }

    @Override
    public void archiverButton() {
        Evenement selectedEvenement = evenementListView.getSelectionModel().getSelectedItem();
        if (selectedEvenement != null) {
            controleur.getGenEvent().archiverEvenement(selectedEvenement);
            listeEvent.remove(selectedEvenement);
            evenementListView.setItems(listeEvent);
            LOGGER.log(Level.INFO, "Événement archivé: {0}", selectedEvenement.getNomEvenement());
        } else {
            informerUtilisateur("Veuillez sélectionner un événement à archiver.", false);
            LOGGER.log(Level.WARNING, "Aucun événement sélectionné pour archivage.");
        }
    }

    @FXML
    public void consulterButton() {
        Evenement selectedEvenement = archivedEventListView.getSelectionModel().getSelectedItem();
        if (selectedEvenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UC11-2-view.fxml"));
                loader.setController(this);
                Parent root = loader.load();

                setEventDetails(selectedEvenement);

                Stage stage = new Stage();
                stage.setTitle("Consulter l'événement");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                LOGGER.log(Level.INFO, "Consultation de l'événement: {0}", selectedEvenement.getNomEvenement());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Erreur lors du chargement de la vue de consultation.", e);
            }
        } else {
            informerUtilisateur("Veuillez sélectionner un événement à consulter.", false);
            LOGGER.log(Level.WARNING, "Aucun événement sélectionné pour consultation.");
        }
    }

    @FXML
    public void dupliquerButton() {
        Evenement selectedEvenement = archivedEventListView.getSelectionModel().getSelectedItem();
        if (selectedEvenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UC2-2-view.fxml"));
                loader.setController(this);

                Parent root = loader.load();

                currentEvenement = selectedEvenement;
                prefillEventDetails(selectedEvenement);

                Stage stage = new Stage();
                stage.setTitle("Dupliquer l'événement");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                LOGGER.log(Level.INFO, "Duplication de l'événement: {0}", selectedEvenement.getNomEvenement());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Erreur lors du chargement de la vue de duplication.", e);
            }
        } else {
            informerUtilisateur("Veuillez sélectionner un événement à dupliquer.", false);
            LOGGER.log(Level.WARNING, "Aucun événement sélectionné pour duplication.");
        }
    }

    @Override
    public void ajouterEvenementButton() {
        LOGGER.log(Level.INFO, "Ajout d'événement initié.");
    }

    public void setEventDetails(Evenement event) {
        nomEvenementLabel.setText(event.getNomEvenement());
        localisationLabel.setText(event.getLieuEvenement());
        dateDebutLabel.setText(event.getDateDebut().toString());
        dateFinLabel.setText(event.getDateFin().toString());
        prixEauLabel.setText(String.valueOf(event.getPrixEau()));
        prixGazLabel.setText(String.valueOf(event.getPrixGaz()));
        prixElecLabel.setText(String.valueOf(event.getPrixElectricite()));
        longueurLabel.setText(String.valueOf(event.getLongueur()));
        largeurLabel.setText(String.valueOf(event.getLargeur()));

        generateMatrixForEvent(event);
        LOGGER.log(Level.INFO, "Détails de l'événement affichés: {0}", event.getNomEvenement());
    }

    private void generateMatrixForEvent(Evenement event) {
        int rows = event.getLongueur();
        int cols = event.getLargeur();
        Emplacement[][] disposition = event.getDisposition();

        matrixGrid.getChildren().clear();
        matrixGrid.getColumnConstraints().clear();
        matrixGrid.getRowConstraints().clear();
        matrixGrid.setPrefWidth(GRID_WIDTH);

        double buttonWidth = GRID_WIDTH / cols;

        for (int i = 0; i < cols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPrefWidth(buttonWidth);
            colConstraints.setPercentWidth(100.0 / cols);
            matrixGrid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / rows);
            matrixGrid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Emplacement emplacement = disposition[i][j];
                Button cellButton = new Button(emplacement.getType());
                cellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                cellButton.setMinWidth(buttonWidth);
                cellButton.setStyle("-fx-border-color: black; -fx-background-color: " + getColorForType(emplacement.getType()) + ";");

                matrixGrid.add(cellButton, j, i);
            }
        }
        LOGGER.log(Level.INFO, "Matrice générée pour l'événement: {0}", event.getNomEvenement());
    }

    public void prefillEventDetails(Evenement event) {
        nomEvene.setText(event.getNomEvenement());
        localisationEvene.setText(event.getLieuEvenement());
        dateDebut.setValue(event.getDateDebut());
        dateFin.setValue(event.getDateFin());
        prixEau.setText(String.valueOf(event.getPrixEau()));
        prixGaz.setText(String.valueOf(event.getPrixGaz()));
        prixElec.setText(String.valueOf(event.getPrixElectricite()));
        rowsField.setText(String.valueOf(event.getLongueur()));
        colsField.setText(String.valueOf(event.getLargeur()));

        generateMatrixForEvent(event);
        LOGGER.log(Level.INFO, "Pré-remplissage des détails de l'événement: {0}", event.getNomEvenement());
    }

    @FXML
    private void validateAndAddEvent() {
        try {
            // Récupérer les données des champs de texte
            String nom = nomEvene.getText().strip();
            String lieu = localisationEvene.getText().strip();
            LocalDate debut = dateDebut.getValue();
            LocalDate fin = dateFin.getValue();
            int eau = Integer.parseInt(prixEau.getText().strip());
            int gaz = Integer.parseInt(prixGaz.getText().strip());
            int elec = Integer.parseInt(prixElec.getText().strip());
            int prix = Integer.parseInt(pirxEvenement.getText().strip());
            int rows = Integer.parseInt(rowsField.getText().strip());
            int cols = Integer.parseInt(colsField.getText().strip());


            Evenement newEvenement = new Evenement(nom, debut, fin, lieu, eau, gaz, elec, prix, rows, cols);

            controleur.getGenEvent().addEvenement(newEvenement);

            listeEvent.add(newEvenement);
            evenementListView.setItems(listeEvent);

            Stage stage = (Stage) validedButton.getScene().getWindow();
            stage.close();
            LOGGER.log(Level.INFO, "Nouvel événement validé et ajouté: {0}", newEvenement.getNomEvenement());
        } catch (Exception e) {
            showError("Erreur lors de la création de l'événement : " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Erreur lors de la création de l'événement.", e);
        }
    }

    @FXML
    private void generateMatrixFromInput() {
        try {
            String rowsInput = rowsField.getText().strip();
            String colsInput = colsField.getText().strip();

            if (!rowsInput.isEmpty() && !colsInput.isEmpty()) {
                int rows = Integer.parseInt(rowsInput);
                int cols = Integer.parseInt(colsInput);

                if (rows > 0 && cols > 0) {
                    if (currentEvenement == null) {
                        currentEvenement = new Evenement(
                                nomEvene.getText().strip(),
                                dateDebut.getValue(),
                                dateFin.getValue(),
                                localisationEvene.getText().strip(),
                                Integer.parseInt(prixEau.getText().strip()),
                                Integer.parseInt(prixGaz.getText().strip()),
                                Integer.parseInt(prixElec.getText().strip()),
                                Integer.parseInt(pirxEvenement.getText().strip()),
                                rows,
                                cols
                        );
                    }

                    // Générer la matrice
                    generateMatrix(cols, rows);
                    LOGGER.log(Level.INFO, "Matrice générée à partir des entrées utilisateur.");
                } else {
                    showError("Veuillez entrer des nombres positifs.");
                    LOGGER.log(Level.WARNING, "Les dimensions de la matrice doivent être des nombres positifs.");
                }
            } else {
                showError("Les champs de saisie ne peuvent pas être vides.");
                LOGGER.log(Level.WARNING, "Les champs de saisie pour les dimensions de la matrice sont vides.");
            }
        } catch (NumberFormatException ex) {
            showError("Veuillez entrer des nombres valides.");
            LOGGER.log(Level.SEVERE, "Erreur de format numérique pour les dimensions de la matrice.", ex);
        }
    }

    @FXML
    private void archiver() {
        this.controleur.archiver();
        LOGGER.log(Level.INFO, "Archivage effectué.");
    }

    @FXML
    public void archiveButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UC11-1-view.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            // Initialiser les listes d'événements archivés si nécessaire
            if (archivedEventList == null) {
                archivedEventList = FXCollections.observableArrayList();
            }
            if (archivedEventDetails == null) {
                archivedEventDetails = new HashMap<>();
            }

            archivedEventListView.setItems(archivedEventList);
            archivedEventListView.setCellFactory(param -> new EventListCell());

            // Charger les événements archivés depuis le modèle
            controleur.getGenEvent().chargerEvenementsArchives();
            archivedEventList.setAll(controleur.getGenEvent().getEvenementsArchive());

            // Afficher la vue des archives dans une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Page des archives");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            LOGGER.log(Level.INFO, "Page des archives affichée.");
        } catch (IOException exc) {
            LOGGER.log(Level.SEVERE, "Erreur lors du chargement de la vue des archives.", exc);
            throw new RuntimeException(exc);
        }
    }
    private void afficherMatrice(Evenement event) {
        int rows = event.getLongueur();
        int cols = event.getLargeur();
        Emplacement[][] disposition = event.getDisposition();

        showmatrix.getChildren().clear();
        showmatrix.getColumnConstraints().clear();
        showmatrix.getRowConstraints().clear();
        showmatrix.setPrefWidth(GRID_WIDTH);

        double buttonWidth = GRID_WIDTH / cols;

        for (int i = 0; i < cols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPrefWidth(buttonWidth);
            colConstraints.setPercentWidth(100.0 / cols);
            showmatrix.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / rows);
            showmatrix.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Emplacement emplacement = disposition[i][j];
                Button cellButton = new Button(emplacement.getType());
                cellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                cellButton.setMinWidth(buttonWidth);
                cellButton.setStyle("-fx-border-color: black; -fx-background-color: " + getColorForType(emplacement.getType()) + ";");

                showmatrix.add(cellButton, j, i);
            }
        }
        LOGGER.log(Level.INFO, "Matrice affichée pour l'événement: {0}", event.getNomEvenement());
    }

}
