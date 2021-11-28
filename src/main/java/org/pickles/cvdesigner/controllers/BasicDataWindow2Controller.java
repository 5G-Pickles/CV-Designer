package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class BasicDataWindow2Controller extends ControllerTemplate {
    public TextField countryTextField;
    public TextField cityTextField;
    public TextField roadTextField;
    public TextField zipCodeTextField;
    public GridPane buildingApartmentGridPane;
    public TextField buildingTextField;
    public TextField apartmentTextField;

    public Label countryLabel;
    public Label cityLabel;
    public Label roadLabel;
    public Label buildingLabel;

    public boolean validateCountry() {
        String text = countryTextField.getText();
        Styling.showError(countryLabel, Validator.textValid(text, false, true, InputType.COUNTRY));

        return Validator.textValid(text, false, true, InputType.COUNTRY);
    }

    public void validateCity(KeyEvent keyEvent) {
    }

    public void validateRoad(KeyEvent keyEvent) {
    }

    public void validateZipCode(KeyEvent keyEvent) {
    }

    public void validateBuilding(KeyEvent keyEvent) {
    }

    public void validateApartment(KeyEvent keyEvent) {
    }

    public void showOnMap(ActionEvent actionEvent) {
    }

    public void goBackToBasicDataWindow1(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_1_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_1_SCENE.value);
    }

    public void goNextToEducationHistoryAndParse(ActionEvent actionEvent) throws IOException {
        if (this.validateCountry()) {
            loadScene(SceneTitles.EDUCATION_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
        } else {
            Styling.showError(countryLabel, Validator.textValid(countryTextField.getText(), false, true, InputType.COUNTRY));

            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
