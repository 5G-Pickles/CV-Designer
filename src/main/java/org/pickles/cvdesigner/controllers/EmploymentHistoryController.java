package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class EmploymentHistoryController extends ControllerTemplate {
    public void validateCompanyName(KeyEvent keyEvent) {
    }

    public void validateAddress(KeyEvent keyEvent) {
    }

    public void validateNip(KeyEvent keyEvent) {
    }

    public void validatePosition(KeyEvent keyEvent) {
    }

    public void goBackToEducationHistory(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.EDUCATION_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
    }

    public void goNextToHardSkillsAndParse(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.HARD_SKILLS_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }
}
