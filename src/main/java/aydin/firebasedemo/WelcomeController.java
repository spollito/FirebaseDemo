package aydin.firebasedemo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public class WelcomeController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleSignIn() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            if (validatePassword(userRecord.getUid(), password)) {
                try {
                    DemoApp.setRoot("dataAccess");
                } catch (IOException e) {
                    showErrorAlert("Error Loading Data Access View", e.getMessage());
                }
            } else {
                showErrorAlert("Sign In Failed", "Invalid password.");
            }
        } catch (FirebaseAuthException e) {
            showErrorAlert("Sign In Error", "Error getting user data: " + e.getMessage());
        }
    }

    @FXML
    private void handleRegister() {
        try {
            DemoApp.setRoot("register");
        } catch (IOException e) {
            showErrorAlert("Error Loading Register View", e.getMessage());
        }
    }

    private boolean validatePassword(String uid, String password) {
        // Custom method to validate password; implement your logic here.
        return true;
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
