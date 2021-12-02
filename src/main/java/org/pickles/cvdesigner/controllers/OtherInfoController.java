package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class OtherInfoController extends ControllerTemplate {
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

    public void goBackToSoftSkills(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            loadScene(SceneTitles.SOFT_SKILLS_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(otherInfoLabel, Validator.inputValid(otherInfoTextArea.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }

    public void goNextToTemplatesAndParse(ActionEvent actionEvent) throws IOException {

    }
}
