package org.pickles.cvdesigner.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageNoDataInfoAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.LocalDateFormatter;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.EducationHistorySceneJsonStorage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EducationHistorySceneController extends SceneControllerTemplate {
    public Label schoolNameLabel;
    public Label countryLabel;
    public Label fieldOfStudyLabel;
    public Label degreeLabel;
    public Label fromDateLabel;
    public Label toDateLabel;

    public TextField schoolNameTextField;
    public TextField countryTextField;
    public TextField fieldOfStudyTextField;
    public TextField degreeTextField;

    public DatePicker fromDatePicker;
    public DatePicker toDatePicker;

    public ListView<String> educationHistoryListView;

    protected JSONObject listViewData = new JSONObject();
    protected JSONObject listViewItemData = new JSONObject();
    protected Integer currentNextIndex = 0;
    protected Integer selectedItemIndex = null;

    public boolean validateSchoolName() {
        String text = schoolNameTextField.getText();
        Styling.showError(schoolNameLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    public boolean validateCountry() {
        String text = countryTextField.getText();
        Styling.showError(countryLabel, Validator.inputValid(text, false, true, InputType.COUNTRY));

        return Validator.inputValid(text, false, true, InputType.COUNTRY);
    }

    public boolean validateFieldOfStudy() {
        String text = fieldOfStudyTextField.getText();
        Styling.showError(fieldOfStudyLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    public boolean validateDegree() {
        String text = degreeTextField.getText();
        Styling.showError(degreeLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @FXML
    private boolean validateDatesPicked() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate != null && toDate != null) {
            boolean validity = (fromDate.isBefore(toDate) || fromDate.isEqual(toDate));
            Styling.showError(fromDateLabel, validity);
            Styling.showError(toDateLabel, validity);
            return validity;
        } else {
            return true;
        }
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        EducationHistorySceneJsonStorage sceneJsonStorage = new EducationHistorySceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {
        schoolNameTextField.setText((String) listViewItemData.get(schoolNameTextField.getId()));
        countryTextField.setText((String) listViewItemData.get(countryTextField.getId()));
        fieldOfStudyTextField.setText((String) listViewItemData.get(fieldOfStudyTextField.getId()));
        degreeTextField.setText((String) listViewItemData.get(degreeTextField.getId()));

        fromDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) listViewItemData.get(fromDatePicker.getId())));
        toDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) listViewItemData.get(toDatePicker.getId())));
    }

    @Override
    protected boolean validateAll() {
        return (validateCountry() & validateDegree() & validateDatesPicked() &
                validateCountry() & validateSchoolName() & validateFieldOfStudy());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        EducationHistorySceneJsonStorage jsonStorage = new EducationHistorySceneJsonStorage();
        jsonStorage.writePartialDataToSubJson(educationHistoryListView.getId(), listViewData);
        return jsonStorage.writeToJsonStorage();
    }

    public void goBackToBasicData2Scene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.BASIC_DATA_2_SCENE_TITLE.value, ScenePaths.BASIC_DATA_2_SCENE.value);
    }

    public void goNextToEmploymentHistorySceneAndStoreData(ActionEvent actionEvent) throws IOException {
        try {
            writeDataToJson();
        } catch (ParseException e) {
            new StorageWriteErrorAlert();
        }
        loadNextScene(SceneTitles.EMPLOYMENT_HISTORY_SCENE_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
    }

    public void goLoadDataEducationHistoryScene(ActionEvent actionEvent) {
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

    public void goAddToEducationHistoryListView(ActionEvent actionEvent) throws IOException, ParseException {
        if (validateAll() && !schoolNameTextField.getText().isEmpty()) {
            if (selectedItemIndex == null) {
                selectedItemIndex = currentNextIndex;
            }
            if (listViewData == null) {
                listViewData = new JSONObject();
            }
            listViewItemData = new JSONObject();
            listViewItemData.put(schoolNameTextField.getId(), schoolNameTextField.getText());
            listViewItemData.put(countryTextField.getId(), countryTextField.getText());
            listViewItemData.put(fieldOfStudyTextField.getId(), fieldOfStudyTextField.getText());
            listViewItemData.put(degreeTextField.getId(), degreeTextField.getText());

            listViewItemData.put(fromDatePicker.getId(), LocalDateFormatter.localDateToString(fromDatePicker.getValue()));
            listViewItemData.put(toDatePicker.getId(), LocalDateFormatter.localDateToString(toDatePicker.getValue()));

            listViewData.put(selectedItemIndex.toString(), listViewItemData);

            selectedItemIndex = null;
            saveAndLoadDataInProperOrder(actionEvent);
        } else {
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    private void saveAndLoadDataInProperOrder(ActionEvent actionEvent) throws IOException, ParseException {
        writeDataToJson();
        this.loadData(actionEvent);
        getListViewDataFromStorage();
    }

    public void getListViewDataFromStorage() {
        listViewData = (JSONObject) fromStorageData.get(educationHistoryListView.getId());
        if (listViewData == null) {
            listViewData = new JSONObject();
        }
        ArrayList<String> listViewItems = new ArrayList<>();
        listViewData.keySet().forEach(key -> {
            listViewItemData = (JSONObject) listViewData.get(key);
            String label = "School: " + listViewItemData.get(schoolNameTextField.getId()) + "\n"
                    + "Field: " + listViewItemData.get(fieldOfStudyTextField.getId()) + "\n"
                    + "Degree: " + listViewItemData.get(degreeTextField.getId());
            listViewItems.add(Integer.parseInt((String) key), label);
        });
        currentNextIndex = listViewData.keySet().size();
        educationHistoryListView.setItems(FXCollections.observableArrayList(listViewItems));
        listViewItemData = (JSONObject) listViewData.get(String.valueOf(educationHistoryListView.getItems().size() - 1));
    }


    public void getClickedItem(MouseEvent mouseEvent) {
        selectedItemIndex = educationHistoryListView.getSelectionModel().getSelectedIndex();
        listViewItemData = (JSONObject) listViewData.get(selectedItemIndex.toString());
        if (listViewItemData != null) {
            setDataFromListViewItemData();
        }
    }
}
