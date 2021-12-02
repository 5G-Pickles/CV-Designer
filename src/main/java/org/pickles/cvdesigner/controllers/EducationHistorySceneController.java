package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

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
    protected boolean validateAll() {
        return (validateCountry() && validateDegree() && validateDatesPicked() &&
                validateCountry() && validateSchoolName() && validateFieldOfStudy());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        return null;
    }

    public void goBackToBasicData2Scene(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_2_SCENE_TITLE.value, ScenePaths.BASIC_DATA_2_SCENE.value);
    }

    public void goNextToEmploymentHistorySceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            loadScene(SceneTitles.EMPLOYMENT_HISTORY_SCENE_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
        } else {
            Styling.showError(schoolNameLabel, Validator.inputValid(schoolNameTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(countryLabel, Validator.inputValid(countryTextField.getText(), false, true, InputType.COUNTRY));
            Styling.showError(fieldOfStudyLabel, Validator.inputValid(fieldOfStudyTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(degreeLabel, Validator.inputValid(degreeTextField.getText(), false, true, InputType.CAPITALIZED));

            Styling.showError(fromDateLabel, validateDatesPicked());
            Styling.showError(toDateLabel, validateDatesPicked());

            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
