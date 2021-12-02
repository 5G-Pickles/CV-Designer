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
import org.pickles.cvdesigner.Main;
import org.pickles.cvdesigner.alerts.*;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.*;
import org.pickles.cvdesigner.storage.BasicData2SceneJsonStorage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class BasicData2SceneController extends SceneControllerTemplate {
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

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        BasicData2SceneJsonStorage jsonStorage = new BasicData2SceneJsonStorage();
        jsonStorage.writePartialDataToSubJson(countryTextField.getId(), countryTextField.getText());
        jsonStorage.writePartialDataToSubJson(cityTextField.getId(), cityTextField.getText());
        jsonStorage.writePartialDataToSubJson(roadTextField.getId(), roadTextField.getText());
        jsonStorage.writePartialDataToSubJson(zipCodeTextField.getId(), zipCodeTextField.getText());
        jsonStorage.writePartialDataToSubJson(buildingTextField.getId(),buildingTextField.getText());
        jsonStorage.writePartialDataToSubJson(apartmentTextField.getId(), apartmentTextField.getText());
        return jsonStorage.writeToJsonStorage();
    }

    public void showOnMap(ActionEvent actionEvent) {
        if (!this.validateAll()) {
            new InvalidInputErrorAlert().showAndWait();
            return;
        }

        try {
            writeDataToJson();
        } catch (ParseException | IOException e) {
            new StorageWriteErrorAlert();
        }

        try {
            onLoadData = BasicData2SceneJsonStorage.getSceneDataFromStorage();
        } catch (ParseException | IOException e) {
            new StorageReadErrorAlert();
        }

        try {
            String pathToMapImage = GoogleMapsService.getStaticMapPath(
                    (String) onLoadData.get(countryTextField.getId()),
                    (String) onLoadData.get(cityTextField.getId()),
                    (String) onLoadData.get(roadTextField.getId()),
                    (String) onLoadData.get(buildingTextField.getId()),
                    (String) onLoadData.get(apartmentTextField.getId()),
                    (String) onLoadData.get(zipCodeTextField.getId())
            );
            mapImageView.setImage(new Image(String.valueOf(new File(pathToMapImage).toURI().toURL())));
        } catch (IOException | ParseException e) {
            new MapLoadingUnknownErrorAlert().showAndWait();
            mapImageView.setImage(new Image(Objects.requireNonNull(Main.class.
                    getResource("couldNotLoadMap.png")).getPath()));
        } catch (InterruptedException | ApiException e) {
            new MapLoadingConnectionErrorAlert().showAndWait();
            mapImageView.setImage(new Image(Objects.requireNonNull(Main.class.
                    getResource("couldNotLoadMap.png")).getPath()));
        }
    }

    public void goBackToBasicData1Scene(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_1_SCENE_TITLE.value, ScenePaths.BASIC_DATA_1_SCENE.value);
    }

    public void goNextToEducationHistorySceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (this.validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadScene(SceneTitles.EDUCATION_HISTORY_SCENE_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
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

            new InvalidInputErrorAlert().showAndWait();
        }
    }
}
