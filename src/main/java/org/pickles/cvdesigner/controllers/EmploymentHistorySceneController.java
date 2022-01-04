package org.pickles.cvdesigner.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import org.pickles.cvdesigner.helpers.LocalDateFormatter;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.EmploymentHistorySceneJsonStorage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmploymentHistorySceneController extends SceneControllerTemplate {
    public Label companyNameLabel;
    public Label addressLabel;
    public Label nipLabel;
    public Label positionLabel;
    public Label fromDateLabel;
    public Label toDateLabel;

    public TextField companyNameTextField;
    public TextField addressTextField;
    public TextField nipTextField;
    public TextField positionTextField;

    public DatePicker fromDatePicker;
    public DatePicker toDatePicker;

    public Label nowDateLabel;
    public CheckBox nowDateCheckBox;

    public ListView<String> employmentHistoryListView;

    protected JSONObject listViewData = new JSONObject();
    protected JSONObject listViewItemData = new JSONObject();
    protected Integer currentNextIndex = 0;
    protected Integer selectedItemIndex = null;

    public boolean validateCompanyName() {
        String text = companyNameTextField.getText();
        Styling.showError(companyNameLabel, Validator.inputValid(text, false, true, InputType.FREE));

        return Validator.inputValid(text, false, true, InputType.FREE);
    }

    public boolean validateAddress() {
        String text = addressTextField.getText();
        Styling.showError(addressLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    public boolean validateNip() {
        String text = nipTextField.getText();
        Styling.showError(nipLabel, Validator.inputValid(text, false, true, InputType.NIP));

        return Validator.inputValid(text, false, true, InputType.NIP);
    }

    public boolean validatePosition() {
        String text = positionTextField.getText();
        Styling.showError(positionLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

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
        EmploymentHistorySceneJsonStorage sceneJsonStorage = new EmploymentHistorySceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {
        companyNameTextField.setText((String) listViewItemData.get(companyNameTextField.getId()));
        addressTextField.setText((String) listViewItemData.get(addressTextField.getId()));
        nipTextField.setText((String) listViewItemData.get(nipTextField.getId()));
        positionTextField.setText((String) listViewItemData.get(positionTextField.getId()));

        fromDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) listViewItemData.get(fromDatePicker.getId())));
        toDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) listViewItemData.get(toDatePicker.getId())));
    }

    @Override
    protected boolean validateAll() {
        return (validateAddress() & validateNip() & validatePosition()
                & validateDatesPicked() & validateCompanyName());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        EmploymentHistorySceneJsonStorage jsonStorage = new EmploymentHistorySceneJsonStorage();
        jsonStorage.writePartialDataToSubJson(employmentHistoryListView.getId(), listViewData);
        return jsonStorage.writeToJsonStorage();
    }

    public void goBackToEducationHistoryScene(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.EDUCATION_HISTORY_SCENE_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
    }

    public void goNextToHardSkillsSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        try {
            writeDataToJson();
        } catch (ParseException e) {
            new StorageWriteErrorAlert();
        }
        loadScene(SceneTitles.HARD_SKILLS_SCENE_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
    }

    public void goLoadDataEmploymentHistoryScene(ActionEvent actionEvent) {
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

    private void getListViewDataFromStorage() {
        listViewData = (JSONObject) fromStorageData.get(employmentHistoryListView.getId());
        if (listViewData == null) {
            listViewData = new JSONObject();
        }
        ArrayList<String> listViewItems = new ArrayList<>();
        listViewData.keySet().forEach(key -> {
            listViewItemData = (JSONObject) listViewData.get(key);
            String label = "Company: " + listViewItemData.get(companyNameTextField.getId()) + "\n"
                    + "NIP: " + listViewItemData.get(nipTextField.getId()) + "\n"
                    + "Position: " + listViewItemData.get(positionTextField.getId());
            listViewItems.add(Integer.parseInt((String) key), label);
        });
        currentNextIndex = listViewData.keySet().size();
        employmentHistoryListView.setItems(FXCollections.observableArrayList(listViewItems));
        listViewItemData = (JSONObject) listViewData.get(String.valueOf(employmentHistoryListView.getItems().size() - 1));
    }

    public void goAddToEmploymentHistoryListView(ActionEvent actionEvent) throws IOException, ParseException {
        if (validateAll() && !companyNameTextField.getText().isEmpty()) {
            if (selectedItemIndex == null) {
                selectedItemIndex = currentNextIndex;
            }
            if (listViewData == null) {
                listViewData = new JSONObject();
            }
            listViewItemData = new JSONObject();
            listViewItemData.put(companyNameTextField.getId(), companyNameTextField.getText());
            listViewItemData.put(addressTextField.getId(), addressTextField.getText());
            listViewItemData.put(nipTextField.getId(), nipTextField.getText());
            listViewItemData.put(positionTextField.getId(), positionTextField.getText());

            listViewItemData.put(fromDatePicker.getId(), LocalDateFormatter.localDateToString(fromDatePicker.getValue()));
            LocalDate date = nowDateCheckBox.isSelected() ? LocalDate.now() : toDatePicker.getValue();
            listViewItemData.put(toDatePicker.getId(), LocalDateFormatter.localDateToString(date));

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

    public void getClickedItem(MouseEvent mouseEvent) {
        selectedItemIndex = employmentHistoryListView.getSelectionModel().getSelectedIndex();
        listViewItemData = (JSONObject) listViewData.get(selectedItemIndex.toString());
        if (listViewItemData != null) {
            setDataFromListViewItemData();
        }
    }
}
