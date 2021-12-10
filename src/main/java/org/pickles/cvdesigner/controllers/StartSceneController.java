package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;

import java.io.IOException;

public class StartSceneController extends SceneControllerTemplate {

    @FXML
    private void goStart(ActionEvent actionEvent) throws IOException, ParseException {
        loadNextScene(SceneTitles.BASIC_DATA_1_SCENE_TITLE.value, ScenePaths.BASIC_DATA_1_SCENE.value);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) {
    }

    @Override
    protected void setDataFromListViewItemData() {

    }

    @Override
    protected boolean validateAll() {
        return true;
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        return null;
    }
}
