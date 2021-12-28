package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.web.WebView;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

import static org.pickles.cvdesigner.controllers.SceneControllerTemplate.loadNextScene;

public class DesignerSceneController {
    public WebView designerWebView;

    private void writeDataToJson() throws ParseException{

    }

    private boolean validateAll() {
        return true;
    }

    public void goBackToOtherInfoButton(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.OTHER_INFO_SCENE_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
        } else {
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goNext(ActionEvent actionEvent) {

    }

    public void goLoadData(ActionEvent actionEvent) {
        designerWebView.getEngine().load("http://localhost:3000/");
    }
}
