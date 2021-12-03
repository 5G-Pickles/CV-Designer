package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class HardSkillsSceneController extends SceneControllerTemplate {
    public TextField topicTextField;

    public Label topicLabel;

    public ToggleGroup hardSkillTypeRadioButtonToggleGroup;

    public boolean validateTopic() {
        String text = topicTextField.getText();
        Styling.showError(topicLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected boolean validateAll() {
        return validateTopic();
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        return null;
    }

    public void goBackToEmploymentHistoryScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.EMPLOYMENT_HISTORY_SCENE_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }

    public void goNextToSoftSkillsSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            loadNextScene(SceneTitles.SOFT_SKILLS_SCENE_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(topicLabel, Validator.inputValid(topicTextField.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputErrorAlert().showAndWait();
        }
    }
}
