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
import org.pickles.cvdesigner.storage.EducationHistorySceneJsonStorage;

import java.io.IOException;
import java.time.LocalDate;

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

    public ListView educationHistoryListView;

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

        if (fromDate != null && toDate!= null) {
            return (fromDate.isBefore(toDate) || fromDate.isEqual(toDate));
        } else { return true; }
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        EducationHistorySceneJsonStorage sceneJsonStorage = new EducationHistorySceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected boolean validateAll() {
        return (validateCountry() && validateDegree() && validateDatesPicked() &&
                validateCountry() && validateSchoolName() && validateFieldOfStudy());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        EducationHistorySceneJsonStorage jsonStorage = new EducationHistorySceneJsonStorage();
        jsonStorage.writePartialDataToSubJson(schoolNameTextField.getId(), schoolNameTextField.getText());
        jsonStorage.writePartialDataToSubJson(countryTextField.getId(), countryTextField.getText());
        jsonStorage.writePartialDataToSubJson(fieldOfStudyTextField.getId(), fieldOfStudyTextField.getText());
        jsonStorage.writePartialDataToSubJson(degreeTextField.getId(), degreeTextField.getText());

        jsonStorage.writePartialDataToSubJson(fromDatePicker.getId(),LocalDateFormatter.localDateToString(fromDatePicker.getValue()));
        jsonStorage.writePartialDataToSubJson(toDatePicker.getId(), LocalDateFormatter.localDateToString(toDatePicker.getValue()));
        return jsonStorage.writeToJsonStorage();
    }

    public void goBackToBasicData2Scene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.BASIC_DATA_2_SCENE_TITLE.value, ScenePaths.BASIC_DATA_2_SCENE.value);
    }

    public void goNextToEmploymentHistorySceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.EMPLOYMENT_HISTORY_SCENE_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
        } else {
            Styling.showError(schoolNameLabel, Validator.inputValid(schoolNameTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(countryLabel, Validator.inputValid(countryTextField.getText(), false, true, InputType.COUNTRY));
            Styling.showError(fieldOfStudyLabel, Validator.inputValid(fieldOfStudyTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(degreeLabel, Validator.inputValid(degreeTextField.getText(), false, true, InputType.CAPITALIZED));

            Styling.showError(fromDateLabel, validateDatesPicked());
            Styling.showError(toDateLabel, validateDatesPicked());

            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataEducationHistoryScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }

            schoolNameTextField.setText((String) fromStorageData.get(schoolNameTextField.getId()));
            countryTextField.setText((String) fromStorageData.get(countryTextField.getId()));
            fieldOfStudyTextField.setText((String) fromStorageData.get(fieldOfStudyTextField.getId()));
            degreeTextField.setText((String) fromStorageData.get(degreeTextField.getId()));

            fromDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) fromStorageData.get(fromDatePicker.getId())));
            toDatePicker.setValue(LocalDateFormatter.stringToLocalDate((String) fromStorageData.get(toDatePicker.getId())));
        } catch(IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goAddToEducationHistoryListView(ActionEvent actionEvent) {

    }
}
