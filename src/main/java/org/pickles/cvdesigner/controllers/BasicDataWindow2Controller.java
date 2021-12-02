package org.pickles.cvdesigner.controllers;

import com.google.maps.errors.ApiException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.GoogleMapsService;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.File;
import java.io.IOException;

public class BasicDataWindow2Controller extends ControllerTemplate {
    public TextField countryTextField;
    public TextField cityTextField;
    public TextField roadTextField;
    public TextField zipCodeTextField;
    public TextField buildingTextField;
    public TextField apartmentTextField;

    public GridPane buildingApartmentGridPane;

    public Label countryLabel;
    public Label cityLabel;
    public Label roadLabel;
    public Label buildingLabel;
    public Label zipLabel;
    public ImageView mapImageView;

    public boolean validateCountry() {
        String text = countryTextField.getText();
        Styling.showError(countryLabel, Validator.inputValid(text, true, true, InputType.COUNTRY));

        return Validator.inputValid(text, true, true, InputType.COUNTRY);
    }

    public boolean validateCity() {
        String text = cityTextField.getText();
        Styling.showError(cityLabel, Validator.inputValid(text, true, false, null));

        return Validator.inputValid(text, true, false, null);
    }

    public boolean validateRoad() {
        String text = roadTextField.getText();
        Styling.showError(roadLabel, Validator.inputValid(text, true, false, null));

        return Validator.inputValid(text, true, false, null);
    }

    public boolean validateZipCode() {
        String text = zipCodeTextField.getText();
        Styling.showError(zipLabel, Validator.inputValid(text, true, false, null));

        return Validator.inputValid(text, true, false, null);
    }

    public boolean validateBuilding() {
        String text = buildingTextField.getText();
        Styling.showError(buildingLabel, Validator.inputValid(text, true, false, null));

        return Validator.inputValid(text, true, false, null);
    }

    public boolean validateApartment() {
        String text = apartmentTextField.getText();
        Styling.showError(buildingLabel, Validator.inputValid(text, true, false, null));

        return Validator.inputValid(text, true, false, null);
    }

    @Override
    protected boolean validateAll() {
        return (this.validateCountry() && this.validateCity() && this.validateRoad() && this.validateBuilding()
                && this.validateApartment() && this.validateZipCode());
    }

    public void showOnMap(ActionEvent actionEvent) {
        if (!this.validateAll()) {
            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
            return;
        }

        try {
            String pathToMapImage = GoogleMapsService.getStaticMapPath();
            mapImageView.setImage(new Image(String.valueOf(new File(pathToMapImage).toURI().toURL())));
        } catch (IOException | InterruptedException | ApiException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void goBackToBasicDataWindow1(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_1_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_1_SCENE.value);
    }

    public void goNextToEducationHistoryAndParse(ActionEvent actionEvent) throws IOException {
        if (this.validateAll()) {
            loadScene(SceneTitles.EDUCATION_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
        } else {
            Styling.showError(countryLabel, Validator.inputValid(countryTextField.getText(), false,
                    true, InputType.COUNTRY));
            Styling.showError(cityLabel, Validator.inputValid(cityTextField.getText(), true,
                    false, null));
            Styling.showError(roadLabel, Validator.inputValid(roadTextField.getText(), true,
                    false, null));
            Styling.showError(zipLabel, Validator.inputValid(zipCodeTextField.getText(), true,
                    false, null));
            Styling.showError(buildingLabel, Validator.inputValid(apartmentTextField.getText(), true,
                    false, null));
            Styling.showError(buildingLabel, Validator.inputValid(buildingTextField.getText(), true,
                    false, null));

            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
