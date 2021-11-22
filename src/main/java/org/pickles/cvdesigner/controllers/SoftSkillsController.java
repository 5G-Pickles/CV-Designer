package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class SoftSkillsController extends ControllerTemplate {
    public void validateTopic(KeyEvent keyEvent) {
    }

    public void goBackToHardskills(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.HARD_SKILLS_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }

    public void goNextToOtherInfoAndParse(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.OTHER_INFO_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
    }
}
