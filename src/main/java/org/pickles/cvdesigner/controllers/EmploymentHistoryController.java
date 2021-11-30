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

public class EmploymentHistoryController extends ControllerTemplate {
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

    @FXML
    private boolean validateDatesPicked() {
        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();

        if (fromDate != null && toDate!= null) {
            return (fromDate.isBefore(toDate) || fromDate.isEqual(toDate));
        } else { return true; }
    }

    public void goBackToEducationHistory(ActionEvent actionEvent) throws IOException {
        loadScene(SceneTitles.EDUCATION_TITLE.value, ScenePaths.EDUCATION_SCENE.value);
    }

    public void goNextToHardSkillsAndParse(ActionEvent actionEvent) throws IOException {
        if (validateCompanyName() && validateAddress() && validateNip()&&validatePosition()&&validateDatesPicked()) {
            loadScene(SceneTitles.HARD_SKILLS_TITLE.value, ScenePaths.HARD_SKILLS_SCENE.value);
        } else {
            Styling.showError(companyNameLabel, Validator.inputValid(companyNameTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(addressLabel, Validator.inputValid(addressTextField.getText(), false, true, InputType.CAPITALIZED));
            Styling.showError(nipLabel, Validator.inputValid(nipTextField.getText(), false, true, InputType.NIP));
            Styling.showError(positionLabel, Validator.inputValid(positionTextField.getText(), false, true, InputType.CAPITALIZED));

            Styling.showError(fromDateLabel, validateDatesPicked());
            Styling.showError(toDateLabel, validateDatesPicked());

            new InvalidInputAlert(Alert.AlertType.ERROR).showAndWait();
        }
    }
}
