package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageNoDataInfoAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.OtherInfoSceneJsonStorage;

import java.io.IOException;

public class OtherInfoSceneController extends SceneControllerTemplate {
    public TextArea otherInfoTextArea;
    public Label sceneInfoLabel;

    public boolean validateOtherInfo() {
        String text = otherInfoTextArea.getText();
        Styling.showError(sceneInfoLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));
        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        OtherInfoSceneJsonStorage sceneJsonStorage = new OtherInfoSceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {

    }

    @Override
    protected boolean validateAll() {
        return validateOtherInfo();
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        OtherInfoSceneJsonStorage sceneJsonStorage = new OtherInfoSceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson(otherInfoTextArea.getId(), otherInfoTextArea.getText());
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goBackToSoftSkillsScene(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.SOFT_SKILLS_SCENE_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(sceneInfoLabel, Validator.inputValid(otherInfoTextArea.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataOtherInfoScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }
            otherInfoTextArea.setText((String) fromStorageData.get(otherInfoTextArea.getId()));
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goNextToDesignerSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (this.validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.DESIGNER_SCENE_TITLE.value, ScenePaths.DESIGNER_SCENE.value);
        } else {
            new InvalidInputErrorAlert().showAndWait();
        }
    }
}
