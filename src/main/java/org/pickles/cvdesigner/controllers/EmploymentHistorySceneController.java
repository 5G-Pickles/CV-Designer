package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    public ListView employmentHistoryListView;


    public boolean validateCompanyName() {
        String text = companyNameTextField.getText();
        Styling.showError(companyNameLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));

        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
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

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        EmploymentHistorySceneJsonStorage sceneJsonStorage = new EmploymentHistorySceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected boolean validateAll() {
        return (validateAddress() && validateNip() && validatePosition()
                && validateDatesPicked() && validateCompanyName());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        EmploymentHistorySceneJsonStorage jsonStorage = new EmploymentHistorySceneJsonStorage();
        jsonStorage.writePartialDataToSubJson(companyNameTextField.getId(), companyNameTextField.getText());
        jsonStorage.writePartialDataToSubJson(addressTextField.getId(), addressTextField.getText());
        jsonStorage.writePartialDataToSubJson(nipTextField.getId(), nipTextField.getText());
        jsonStorage.writePartialDataToSubJson(positionTextField.getId(), positionTextField.getText());

        jsonStorage.writePartialDataToSubJson(fromDatePicker.getId(), LocalDateFormatter.localDateToString(fromDatePicker.getValue()));
        jsonStorage.writePartialDataToSubJson(toDatePicker.getId(), LocalDateFormatter.localDateToString(toDatePicker.getValue()));
        return jsonStorage.writeToJsonStorage();
    }

    @FXML
    private boolean validateDatesPicked() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate != null && toDate!= null) {
            return (fromDate.isBefore(toDate) || fromDate.isEqual(toDate));
        } else { return true; }
    }

    public void goBackToEducationHistoryScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.EDUCATION_HISTORY_SCENE_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
    }

    public void goNextToHardSkillsSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.HARD_SKILLS_SCENE_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
        } else {
            Styling.showError(companyNameLabel, Validator.inputValid(companyNameTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(addressLabel, Validator.inputValid(addressTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(nipLabel, Validator.inputValid(nipTextField.getText(), false, true, InputType.NIP));
            Styling.showError(positionLabel, Validator.inputValid(positionTextField.getText(), false, true, InputType.CAPITALIZED));

            Styling.showError(fromDateLabel, validateDatesPicked());
            Styling.showError(toDateLabel, validateDatesPicked());

            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataEmploymentHistoryScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }

            companyNameTextField.setText((String) fromStorageData.get(companyNameTextField.getId()));
            addressTextField.setText((String) fromStorageData.get(addressTextField.getId()));
            nipTextField.setText((String) fromStorageData.get(nipTextField.getId()));
            positionTextField.setText((String) fromStorageData.get(positionTextField.getId()));

            fromDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) fromStorageData.get(fromDatePicker.getId())));
            toDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) fromStorageData.get(toDatePicker.getId())));
        } catch(IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goAddToEmploymentHistoryListView(ActionEvent actionEvent) {

    }
}
