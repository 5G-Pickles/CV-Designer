package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;

public class SoftSkillsSceneController extends SceneControllerTemplate {
    public TextField topicTextField;

    public Label topicLabel;

    public ListView softSkillsListView;

    public boolean validateTopic() {
        String text = topicTextField.getText();
        Styling.showError(topicLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) {

    }

    @Override
    protected boolean validateAll() {
        return validateTopic();
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        return null;
    }

    public void goBackToHardSkillsScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.HARD_SKILLS_SCENE_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }

    public void goNextToOtherInfoSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            loadNextScene(SceneTitles.OTHER_INFO_SCENE_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
        } else {
            Styling.showError(topicLabel, Validator.inputValid(topicTextField.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataSoftSkillsScene(ActionEvent actionEvent) {
        this.loadData(actionEvent);
    }

    public void goAddToSoftSkillsListView(ActionEvent actionEvent) {

    }
}
