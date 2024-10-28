package aydin.firebasedemo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;

public class DataAccessController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField phoneField;

    @FXML
    private void handleWrite() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String phoneNumber = phoneField.getText();

        if (name.isEmpty() || ageText.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Input Error", "Name, age, and phone number cannot be empty!", Alert.AlertType.ERROR);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Age must be a valid number!", Alert.AlertType.ERROR);
            return;
        }

        // Ensure we're using the correct constructor with three parameters
        Person person = new Person(name, age, phoneNumber);
        Firestore db = DemoApp.fstore;

        DocumentReference docRef = db.collection("persons").document();
        ApiFuture<WriteResult> result = docRef.set(person);

        result.addListener(() -> showAlert("Success", "Person added successfully!", Alert.AlertType.INFORMATION), Runnable::run);
    }

    @FXML
    private void handleRead() {
        showAlert("Information", "Reading persons' information code here.", Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
