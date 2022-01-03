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
import org.pickles.cvdesigner.storage.SoftSkillsSceneJsonStorage;

import java.io.IOException;
import java.util.ArrayList;

public class SoftSkillsSceneController extends SceneControllerTemplate {
    public TextField topicTextField;
    public TextArea descriptionTextArea;

    public Label topicLabel;
    public Label descriptionLabel;
    public Button loadDataButton;

    public ListView<String> softSkillsListView;

    protected JSONObject listViewData = new JSONObject();
    protected JSONObject listViewItemData = new JSONObject();
    protected Integer currentNextIndex = 0;
    protected Integer selectedItemIndex = null;


    public boolean validateTopic() {
        String text = topicTextField.getText();
        Styling.showError(topicLabel, Validator.inputValid(text, true, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, true, true, InputType.CAPITALIZED);
    }

    public boolean validateDescription() {
        String text = descriptionTextArea.getText();
        Styling.showError(descriptionLabel, Validator.inputValid(text, false, true, InputType.FREE));

        return Validator.inputValid(text, false, true, InputType.FREE);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        SoftSkillsSceneJsonStorage sceneJsonStorage = new SoftSkillsSceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {
        topicTextField.setText((String) listViewItemData.get(topicTextField.getId()));
        descriptionTextArea.setText((String) listViewItemData.get(descriptionTextArea.getId()));
    }

    @Override
    protected boolean validateAll() {
        return (validateTopic() && validateDescription());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        SoftSkillsSceneJsonStorage sceneJsonStorage = new SoftSkillsSceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson(softSkillsListView.getId(), listViewData);
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goBackToHardSkillsScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.HARD_SKILLS_SCENE_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }

    public void goNextToOtherInfoSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        try {
            writeDataToJson();
        } catch (ParseException e) {
            new StorageWriteErrorAlert();
        }
        loadNextScene(SceneTitles.OTHER_INFO_SCENE_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
    }

    public void goLoadDataSoftSkillsScene(ActionEvent actionEvent) {
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

    public void goAddToSoftSkillsListView(ActionEvent actionEvent) throws IOException, ParseException {
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
        listViewData = (JSONObject) fromStorageData.get(softSkillsListView.getId());
        if (listViewData == null) {
            listViewData = new JSONObject();
        }
        ArrayList<String> listViewItems = new ArrayList<>();
        listViewData.keySet().forEach(key -> {
            listViewItemData = (JSONObject) listViewData.get(key);
            String label = "Topic: " + listViewItemData.get(topicTextField.getId()) + "\n"
                    + "Description: " + listViewItemData.get(descriptionTextArea.getId());
            listViewItems.add(Integer.parseInt((String) key), label);
        });
        currentNextIndex = listViewData.keySet().size();
        softSkillsListView.setItems(FXCollections.observableArrayList(listViewItems));
        listViewItemData = (JSONObject) listViewData.get(String.valueOf(softSkillsListView.getItems().size() - 1));
    }

    public void getClickedItem(MouseEvent mouseEvent) {
        selectedItemIndex = softSkillsListView.getSelectionModel().getSelectedIndex();
        listViewItemData = (JSONObject) listViewData.get(selectedItemIndex.toString());
        if (listViewItemData != null) {
            setDataFromListViewItemData();
        }
    }
}


