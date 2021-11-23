package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class BasicDataWindow2Controller extends ControllerTemplate {
    public void validateCountry(KeyEvent keyEvent) {
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
        loadScene(SceneTitles.EDUCATION_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
    }
}
