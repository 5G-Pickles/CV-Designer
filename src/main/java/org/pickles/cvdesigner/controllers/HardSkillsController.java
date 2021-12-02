package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class HardSkillsController extends ControllerTemplate {
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

    public void goBackToEmploymentHistory(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.EMPLOYMENT_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }

    public void goNextToSoftSkillsAndParse(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            loadScene(SceneTitles.SOFT_SKILLS_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(topicLabel, Validator.inputValid(topicTextField.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
