package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

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
    public ToggleGroup sexRadioButtonToggleGroup;

    @FXML
    public void validateName(KeyEvent keyEvent) {
        String value = nameTextField.getText();
        Styling.showError(nameLabel, Validator.textValid(value, true, true));
    }

    public void validateSurname(KeyEvent keyEvent) {
        String value = surnameTextField.getText();
        Styling.showError(surnameLabel, Validator.textValid(value, true, true));
    }

    public void validateTelephone(KeyEvent keyEvent) {
        Styling.formatTelephoneNumber(telephoneTextField);
    }

    public void validateEmail(KeyEvent keyEvent) {

    }

    public void goBackToStart(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.START_TITLE.value, ScenePaths.START_SCENE.value);
    }

    public void goNextToBasicDataWindow2AndParse(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_2_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_2_SCENE.value);
    }
}
