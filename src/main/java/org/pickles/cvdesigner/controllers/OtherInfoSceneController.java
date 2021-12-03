package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class OtherInfoSceneController extends SceneControllerTemplate {
    public TextArea otherInfoTextArea;
    public Label otherInfoLabel;

    public boolean validateOtherInfo() {
        String text = otherInfoTextArea.getText();
        Styling.showError(otherInfoLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected boolean validateAll() {
        return validateOtherInfo();
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        return null;
    }

    public void goBackToSoftSkillsScene(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            loadNextScene(SceneTitles.SOFT_SKILLS_SCENE_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(otherInfoLabel, Validator.inputValid(otherInfoTextArea.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goNextToTemplatesSceneAndStoreData(ActionEvent actionEvent) throws IOException {

    }
}
