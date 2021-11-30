package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class SoftSkillsController extends ControllerTemplate {
    public TextField topicTextField;
    public Label topicLabel;

    public boolean validateTopic() {
        String text = topicTextField.getText();
        Styling.showError(topicLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    public void goBackToHardSkills(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.HARD_SKILLS_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }

    public void goNextToOtherInfoAndParse(ActionEvent actionEvent) throws IOException {
        if (validateTopic()) {
            loadScene(SceneTitles.OTHER_INFO_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
        } else {
            Styling.showError(topicLabel, Validator.inputValid(topicTextField.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
