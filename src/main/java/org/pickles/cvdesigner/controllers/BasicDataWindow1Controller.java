package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class BasicDataWindow1Controller extends ControllerTemplate {
    public GridPane basicInfoGridPane;

    public Label nameLabel;
    public Label surnameLabel;
    public Label telephoneLabel;
    public Label emailLabel;

    public TextField nameTextField;
    public TextField surnameTextField;
    public TextField telephoneTextField;
    public TextField emailTextField;

    public RadioButton maleRadioButton;
    public RadioButton femaleRadioButton;
    public RadioButton otherRadioButton;

    public Button backToStartButton;
    public Button nextToBasicDataWindow2Button;

    @FXML
    public void validateName(KeyEvent keyEvent) {
        String value = nameTextField.getText();

        if (!value.equals("Nigga")) {
            nameLabel.setTextFill(Color.RED);
        }

        if (value.equals("Nigga")) {
            nameLabel.setTextFill(new Color(0.0, 0.2996, 0.7004, 1.0));
        }
    }

    public void validateSurname(KeyEvent keyEvent) {

    }

    public void validateTelephone(KeyEvent keyEvent) {

    }

    public void validateEmail(KeyEvent keyEvent) {

    }

    @FXML
    public void selectRadioCheck(ActionEvent actionEvent) {

    }

    public void goBackToStart(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.START_TITLE.value, ScenePaths.START_SCENE.value);
    }

    public void goNextToBasicDataWindow2AndParse(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_2_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_2_SCENE.value);
    }
}
