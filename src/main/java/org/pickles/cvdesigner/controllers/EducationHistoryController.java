package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.InvalidInputAlert;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;

import java.io.IOException;
import java.time.LocalDate;

public class EducationHistoryController extends ControllerTemplate {
    public Label schoolNameLabel;
    public Label countryLabel;
    public Label fieldOfStudyLabel;
    public Label degreeLabel;

    public TextField schoolNameTextField;
    public TextField countryTextField;
    public TextField fieldOfStudyTextField;
    public DatePicker fromDatePicker;
    public DatePicker toDatePicker;
    public TextField degreeTextField;
    public Label fromDateLabel;
    public Label toDateLabel;

    public boolean validateSchoolName() {
        String text = schoolNameTextField.getText();
        Styling.showError(schoolNameLabel, Validator.textValid(text, false, true, InputType.CAPITALIZED));

        return Validator.textValid(text, false, true, InputType.CAPITALIZED);
    }

    public boolean validateCountry() {
        String text = countryTextField.getText();
        Styling.showError(countryLabel, Validator.textValid(text, false, true, InputType.COUNTRY));

        return Validator.textValid(text, false, true, InputType.COUNTRY);
    }

    public boolean validateFieldOfStudy() {
        String text = fieldOfStudyTextField.getText();
        Styling.showError(fieldOfStudyLabel, Validator.textValid(text, false, true, InputType.CAPITALIZED));

        return Validator.textValid(text, false, true, InputType.CAPITALIZED);
    }

    public boolean validateDegree() {
        String text = degreeTextField.getText();
        Styling.showError(degreeLabel, Validator.textValid(text, false, true, InputType.CAPITALIZED));

        return Validator.textValid(text, false, true, InputType.CAPITALIZED);
    }

    public void goBackToBasicDataWindow2(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.BASIC_DATA_WINDOW_2_TITLE.value, ScenePaths.BASIC_DATA_WINDOW_2_SCENE.value);
    }

    @FXML
    private boolean validateDatesPicked() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate != null && toDate!= null) {
            return (fromDate.isBefore(toDate) || fromDate.isEqual(toDate));
        } else { return true; }
    }

    public void goNextToEmploymentHistoryAndParse(ActionEvent actionEvent) throws IOException {
        if (validateSchoolName()&&validateCountry()&&validateFieldOfStudy()&&validateDegree()&& validateDatesPicked()) {
            loadScene(SceneTitles.EMPLOYMENT_TITLE.value, ScenePaths.EMPLOYMENT_SCENE.value);
        } else {
            Styling.showError(schoolNameLabel, Validator.textValid(schoolNameTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(countryLabel, Validator.textValid(countryTextField.getText(), false, true, InputType.COUNTRY));
            Styling.showError(fieldOfStudyLabel, Validator.textValid(fieldOfStudyTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(degreeLabel, Validator.textValid(degreeTextField.getText(), false, true, InputType.CAPITALIZED));

            Styling.showError(fromDateLabel, validateDatesPicked());
            Styling.showError(toDateLabel, validateDatesPicked());

            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
