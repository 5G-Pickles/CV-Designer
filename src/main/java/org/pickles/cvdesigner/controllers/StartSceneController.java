package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class StartSceneController extends ControllerTemplate {

    @FXML
    private void goStart(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_1_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_1_SCENE.value);
    }
}
