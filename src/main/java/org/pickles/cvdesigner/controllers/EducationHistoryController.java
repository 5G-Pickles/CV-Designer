package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class EducationHistoryController extends ControllerTemplate {
    public void validateSchoolName(KeyEvent keyEvent) {
    }

    public void validateCountry(KeyEvent keyEvent) {
    }

    public void validateFieldOfStudy(KeyEvent keyEvent) {
    }

    public void validateDegree(KeyEvent keyEvent) {
    }

    public void goBackToBasicDataWindow2(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_2_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_2_SCENE.value);
    }

    public void goNextToEmploymentHistoryAndParse(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.EMPLOYMENT_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }
}
