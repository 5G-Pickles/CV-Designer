package org.pickles.cvdesigner.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;
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
import java.util.ArrayList;

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

    protected JSONObject listViewData = new JSONObject();
    protected JSONObject listViewItemData = new JSONObject();
    protected Integer currentNextIndex = 0;
    protected Integer selectedItemIndex = null;

    public String getHardSkillRadioButtonSelected() {
        RadioButton selectedRadioButton = (RadioButton) hardSkillTypeRadioButtonToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        } else { return ""; }
    }

    public boolean validateTopic() {
        String text = topicTextField.getText();
        Styling.showError(topicLabel, Validator.inputValid(text, true, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, true, true, InputType.CAPITALIZED);
    }

    public boolean validateDescription() {
        String text = descriptionTextArea.getText();
        Styling.showError(descriptionLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        HardSkillsSceneJsonStorage sceneJsonStorage = new HardSkillsSceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {
        topicTextField.setText((String) listViewItemData.get(topicTextField.getId()));
        descriptionTextArea.setText((String) listViewItemData.get(descriptionTextArea.getId()));

        String specificKnowledge = specificKnowledgeRadioButton.getText();
        String certificates = certificatesRadioButton.getText();
        String other = otherRadioButton.getText();
        String linksToPortfolio = linksToPortfolioRadioButton.getText();
        String licences = licencesRadioButton.getText();

        String hardSkill = (String) listViewItemData.get("hardSkillTypeRadioButtonToggleGroup");
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

    }

    @Override
    protected boolean validateAll() {
        return (validateTopic() && validateDescription());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        HardSkillsSceneJsonStorage sceneJsonStorage = new HardSkillsSceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson(hardSkillsListView.getId(), listViewData);
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goBackToEmploymentHistoryScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.EMPLOYMENT_HISTORY_SCENE_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }

    public void goNextToSoftSkillsSceneAndStoreData(ActionEvent actionEvent) throws IOException {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.SOFT_SKILLS_SCENE_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
    }

    public void goLoadDataHardSkillsScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }
            getListViewDataFromStorage();
            if (listViewItemData != null) {
                setDataFromListViewItemData();
            }
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goAddToHardSkillsListView(ActionEvent actionEvent) throws IOException, ParseException {
        if (validateAll()) {
            if (selectedItemIndex == null) {
                selectedItemIndex = currentNextIndex;
            }
            if (listViewData == null) {
                listViewData = new JSONObject();
            }
            listViewItemData = new JSONObject();
            listViewItemData.put(topicTextField.getId(), topicTextField.getText());
            listViewItemData.put(descriptionTextArea.getId(), descriptionTextArea.getText());
            listViewItemData.put("hardSkillTypeRadioButtonToggleGroup", getHardSkillRadioButtonSelected());
            listViewData.put(selectedItemIndex.toString(), listViewItemData);

            selectedItemIndex = null;
            saveAndLoadDataInProperOrder(actionEvent);
        } else if (topicTextField.getText().isEmpty()) {
            new InvalidInputErrorAlert("Topic cannot be empty").showAndWait();
        }
    }

    private void saveAndLoadDataInProperOrder(ActionEvent actionEvent) throws IOException, ParseException {
        writeDataToJson();
        this.loadData(actionEvent);
        getListViewDataFromStorage();
    }

    private void getListViewDataFromStorage() {
        listViewData = (JSONObject) fromStorageData.get(hardSkillsListView.getId());
        if (listViewData == null) {
            listViewData = new JSONObject();
        }
        ArrayList<String> listViewItems = new ArrayList<>();
        listViewData.keySet().forEach(key -> {
            listViewItemData = (JSONObject) listViewData.get(key);
            String label = "Topic: " + listViewItemData.get(topicTextField.getId()) + "\n"
                    + "Description: " + listViewItemData.get(descriptionTextArea.getId()) + "\n"
                    + "Skill type: " + listViewItemData.get("hardSkillTypeRadioButtonToggleGroup");
            listViewItems.add(Integer.parseInt((String) key), label);
        });
        currentNextIndex = listViewData.keySet().size();
        hardSkillsListView.setItems(FXCollections.observableArrayList(listViewItems));
        listViewItemData = (JSONObject) listViewData.get(String.valueOf(hardSkillsListView.getItems().size() - 1));
    }

    public void getClickedItem(MouseEvent mouseEvent) {
        selectedItemIndex = hardSkillsListView.getSelectionModel().getSelectedIndex();
        listViewItemData = (JSONObject) listViewData.get(selectedItemIndex.toString());
        if (listViewItemData != null) {
            setDataFromListViewItemData();
        }
    }
}
