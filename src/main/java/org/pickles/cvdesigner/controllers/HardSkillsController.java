package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class HardSkillsController extends ControllerTemplate {
    public void validateTopic(KeyEvent keyEvent) {
    }

    public void goBackToEmploymentHistory(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.EMPLOYMENT_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }

    public void goNextToSoftSkillsAndParse(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.SOFT_SKILLS_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
    }
}
