/*
package fr.uga.iut2.genevent.controleur;



import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import fr.uga.iut2.genevent.modele.Utilisateur;

public class ControlleurLogin {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    private Utilisateur utilisateur;

    @FXML
    public void initialie() {
        loginButton.setOnAction(e -> handleLogin());
        signUpButton.setOnAction(e -> handleSignUp());
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate email and password
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Email and password cannot be empty");
            return;
        }

        // Here you should verify the credentials, this is just a simple example
        if (email.equals("test@example.com") && password.equals("password")) {
            utilisateur = new Utilisateur(email, "John", "Doe", password);
            showAlert("Success", "Login successful");
            // Close the login window
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            // Proceed to the main application
            // For example, you can load a new scene or open a new window
        } else {
            showAlert("Error", "Invalid email or password");
        }
    }

    private void handleSignUp() {
        showAlert("Info", "Sign Up feature is not implemented yet");
        // Here you can implement the sign-up functionality
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
*/
