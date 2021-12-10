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
import org.pickles.cvdesigner.storage.HardSkillsSceneJsonStorage;

import java.io.IOException;

public class HardSkillsSceneController extends SceneControllerTemplate {
    public TextField topicTextField;
    public TextArea descriptionTextArea;

    public Label topicLabel;
    public Label descriptionLabel;

    public ToggleGroup hardSkillTypeRadioButtonToggleGroup;
    public RadioButton specificKnowledgeRadioButton;
    public RadioButton certificatesRadioButton;
    public RadioButton otherRadioButton;
    public RadioButton linksToPortfolioRadioButton;
    public RadioButton licencesRadioButton;

    public ListView<String> hardSkillsListView;

    public String getHardSkillRadioButtonSelected() {
        RadioButton selectedRadioButton = (RadioButton) hardSkillTypeRadioButtonToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        } else { return ""; }
    }

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
        HardSkillsSceneJsonStorage sceneJsonStorage = new HardSkillsSceneJsonStorage();
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
        HardSkillsSceneJsonStorage sceneJsonStorage = new HardSkillsSceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson("hardSkillTypeRadioButtonToggleGroup", getHardSkillRadioButtonSelected());
        sceneJsonStorage.writePartialDataToSubJson(topicTextField.getId(), topicTextField.getText());
        sceneJsonStorage.writePartialDataToSubJson(descriptionTextArea.getId(), descriptionTextArea.getText());
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goBackToEmploymentHistoryScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.EMPLOYMENT_HISTORY_SCENE_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }

    public void goNextToSoftSkillsSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.SOFT_SKILLS_SCENE_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(topicLabel, validateTopic());


            if (!validateDescription())
                new InvalidInputErrorAlert("Topic cannot be empty").showAndWait();
            else
                new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataHardSkillsScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }
            topicTextField.setText((String) fromStorageData.get(topicTextField.getId()));
            descriptionTextArea.setText((String) fromStorageData.get(descriptionTextArea.getId()));

            String hardSkill = (String) fromStorageData.get("hardSkillTypeRadioButtonToggleGroup");

            String specificKnowledge = specificKnowledgeRadioButton.getText();
            String certificates = certificatesRadioButton.getText();
            String other = otherRadioButton.getText();
            String linksToPortfolio = linksToPortfolioRadioButton.getText();
            String licences = licencesRadioButton.getText();

            if (hardSkill.equals(specificKnowledge)) {
                hardSkillTypeRadioButtonToggleGroup.selectToggle(specificKnowledgeRadioButton);
            }
            if (hardSkill.equals(certificates)) {
                hardSkillTypeRadioButtonToggleGroup.selectToggle(certificatesRadioButton);
            }
            if (hardSkill.equals(other)) {
                hardSkillTypeRadioButtonToggleGroup.selectToggle(otherRadioButton);
            }
            if (hardSkill.equals(linksToPortfolio)) {
                hardSkillTypeRadioButtonToggleGroup.selectToggle(linksToPortfolioRadioButton);
            }
            if (hardSkill.equals(licences)) {
                hardSkillTypeRadioButtonToggleGroup.selectToggle(licencesRadioButton);
            }
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goAddToHardSkillsListView(ActionEvent actionEvent) {

    }

    public void getClickedItem(MouseEvent mouseEvent) {

    }
}
