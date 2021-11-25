package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
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


    public boolean validateName() {
        String value = nameTextField.getText();
        Styling.showError(nameLabel, Validator.textValid(value, true, true, InputType.NAME));

        return Validator.textValid(value, true, true, InputType.NAME);
    }

    public boolean validateSurname() {
        String value = surnameTextField.getText();
        Styling.showError(surnameLabel, Validator.textValid(value, true, true, InputType.NAME));

        return Validator.textValid(value, true, true, InputType.NAME);
    }

    public boolean validateTelephone(KeyEvent key) {
        String value = telephoneTextField.getText();
        Styling.showError(telephoneLabel, Validator.textValid(value, true, true, InputType.TELEPHONE));
        if (!(key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.LEFT)) {
            Styling.formatTelephoneNumber(telephoneTextField);
        }

        return Validator.textValid(value, true, true, InputType.TELEPHONE);
    }

    public boolean validateEmail() {
        String value = emailTextField.getText();
        Styling.showError(emailLabel, Validator.textValid(value, true, true, InputType.EMAIL));

        return Validator.textValid(value, true, true, InputType.EMAIL);
    }

    public String getSexRadioButtonSelected() {
        RadioButton selectedRadioButton = (RadioButton) sexRadioButtonToggleGroup.getSelectedToggle();
        if (selectedRadioButton!=null)
            return selectedRadioButton.getText();
        else
            return "";
    }

    public void goBackToStart(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.START_TITLE.value, ScenePaths.START_SCENE.value);
    }

    public void goNextToBasicDataWindow2AndParse(ActionEvent actionEvent) throws IOException {
        if (this.validateName() && this.validateSurname() && this.validateEmail()) {
            loadScene(SceneTitles.BASIC_DATA_WINDOW_2_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_2_SCENE.value);
        } else {
            Styling.showError(nameLabel, Validator.textValid(nameTextField.getText(),
                    true, true, InputType.NAME));
            Styling.showError(surnameLabel, Validator.textValid(surnameTextField.getText(),
                    true, true, InputType.NAME));
            Styling.showError(emailLabel, Validator.textValid(emailTextField.getText(),
                    true, true, InputType.EMAIL));
            Styling.showError(telephoneLabel, Validator.textValid(telephoneTextField.getText(),
                    true, true, InputType.TELEPHONE));

            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
