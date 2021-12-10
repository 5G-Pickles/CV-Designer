package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageNoDataInfoAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.SoftSkillsSceneJsonStorage;

import java.io.IOException;

public class SoftSkillsSceneController extends SceneControllerTemplate {
    public TextField topicTextField;

    public Label topicLabel;

    public ListView<String> softSkillsListView;
    public TextArea descriptionTextArea;
    public Button loadDataButton;

    public boolean validateTopic() {
        String text = topicTextField.getText();
        if (!(topicTextField.getText().isEmpty() ^ descriptionTextArea.getText().isEmpty())) {
            Styling.showError(topicLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));
        }

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    public boolean validateDescription() {
        String text = descriptionTextArea.getText();
        if (!(topicTextField.getText().isEmpty() ^ descriptionTextArea.getText().isEmpty())) {
            Styling.showError(topicLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));
        }

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        SoftSkillsSceneJsonStorage sceneJsonStorage = new SoftSkillsSceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {

    }

    @Override
    protected boolean validateAll() {
        return (validateTopic() & validateDescription());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        SoftSkillsSceneJsonStorage sceneJsonStorage = new SoftSkillsSceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson(topicTextField.getId(), topicTextField.getText());
        sceneJsonStorage.writePartialDataToSubJson(descriptionTextArea.getId(), descriptionTextArea.getText());
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goBackToHardSkillsScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.HARD_SKILLS_SCENE_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }

    public void goNextToOtherInfoSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.OTHER_INFO_SCENE_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
        } else {
            Styling.showError(topicLabel, validateTopic());
            Styling.showError(topicLabel, validateDescription());

            if (!validateDescription())
                new InvalidInputErrorAlert("Topic cannot be empty").showAndWait();
            else
                new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataSoftSkillsScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }
            topicTextField.setText((String) fromStorageData.get(topicTextField.getId()));
            descriptionTextArea.setText((String) fromStorageData.get(descriptionTextArea.getId()));
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goAddToSoftSkillsListView(ActionEvent actionEvent) {

    }

    public void getClickedItem(MouseEvent mouseEvent) {

    }
}
