package org.pickles.cvdesigner.controllers;

import com.google.maps.errors.ApiException;
import javafx.event.ActionEvent;
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
import org.pickles.cvdesigner.helpers.GoogleMapsService;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.BasicData2SceneJsonStorage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        BasicData2SceneJsonStorage sceneJsonStorage = new BasicData2SceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {

    }

    @Override
    protected boolean validateAll() {
        return (this.validateCountry() & this.validateCity() & this.validateRoad() &
                this.validateBuilding() & this.validateApartment() & this.validateZipCode());
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
            BasicData2SceneJsonStorage sceneJsonStorage = new BasicData2SceneJsonStorage();
            fromStorageData = sceneJsonStorage.getDataFromStorage();
        } catch (ParseException | IOException e) {
            new StorageReadErrorAlert();
        }

        try {
            String pathToMapImage = GoogleMapsService.getStaticMapPath(
                    (String) fromStorageData.get(countryTextField.getId()),
                    (String) fromStorageData.get(cityTextField.getId()),
                    (String) fromStorageData.get(roadTextField.getId()),
                    (String) fromStorageData.get(buildingTextField.getId()),
                    (String) fromStorageData.get(apartmentTextField.getId()),
                    (String) fromStorageData.get(zipCodeTextField.getId())
            );
            mapImageView.setImage(new Image(String.valueOf(new File(pathToMapImage).toURI().toURL())));
        } catch (NullPointerException | IOException | ParseException e) {
            new MapLoadingUnknownErrorAlert().showAndWait();
            setMapLoadErrorImage();
        } catch (InterruptedException | ApiException e) {
            new MapLoadingConnectionErrorAlert().showAndWait();
            setMapLoadErrorImage();
        }
    }

    private void setMapLoadErrorImage() {
        String path = Objects.requireNonNull(Main.class.getResource("map/mapLoadError.png")).getPath();
        path = URLDecoder.decode(path, StandardCharsets.UTF_8);
        path = new File(path).toString();
        try {
            mapImageView.setImage(new Image(String.valueOf(new File(path).toURI().toURL())));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
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
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataBasicData2Scene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }
            countryTextField.setText((String) fromStorageData.get(countryTextField.getId()));
            cityTextField.setText((String) fromStorageData.get(cityTextField.getId()));
            roadTextField.setText((String) fromStorageData.get(roadTextField.getId()));
            buildingTextField.setText((String) fromStorageData.get(buildingTextField.getId()));
            apartmentTextField.setText((String) fromStorageData.get(apartmentTextField.getId()));
            zipCodeTextField.setText((String) fromStorageData.get(zipCodeTextField.getId()));
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }
}
