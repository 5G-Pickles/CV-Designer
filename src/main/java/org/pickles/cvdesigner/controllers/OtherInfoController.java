package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class OtherInfoController extends ControllerTemplate {
    public void validateOtherInfo(KeyEvent keyEvent) {
    }

    public void goBackToSoftskills(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.SOFT_SKILLS_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
    }

    public void goNextToTemplatesAndParse(ActionEvent actionEvent) throws IOException {

    }

}
