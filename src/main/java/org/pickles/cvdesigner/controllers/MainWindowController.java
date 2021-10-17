package org.pickles.cvdesigner.controllers;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class MainWindowController {
    @FXML
    private Label telNoLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telNoTextField;

    @FXML
    protected void verifyEmailFromEmailTextField() {
        String email = emailTextField.getText();
        if (!email.contains("@")) {
            emailLabel.setText("INCORRECT");
        } else if (email.contains("@")) {
            emailLabel.setText("CORRECT");
        }
    }

    @FXML
    protected void verifyTelNoFromTelNoTextField() {
        String telNo = telNoTextField.getText();
        telNo = telNo.replace(" ", "");
        telNo = telNo.replace("-", "");
        if (telNo.length() != 12) {
            telNoLabel.setText("INCORRECT");
        } else {
            telNoLabel.setText("CORRECT");
        }
    }

    @FXML
    protected void openAboutAppDialogue(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About CVDesigner");
        alert.setHeaderText("About us");
        alert.setContentText("© 5G-Pickles\nSubscribe to Linus Tech Tips!");
        alert.showAndWait();
    }
}

