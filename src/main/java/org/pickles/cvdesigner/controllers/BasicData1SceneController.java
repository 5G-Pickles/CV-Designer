package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageNoDataInfoAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.BasicData1SceneJsonStorage;

import java.io.IOException;

public class BasicData1SceneController extends SceneControllerTemplate {
    public GridPane basicInfoGridPane;

    public Label nameLabel;
    public Label surnameLabel;
    public Label telephoneLabel;
    public Label emailLabel;
    public Label sexLabel;

    public TextField nameTextField;
    public TextField surnameTextField;
    public TextField telephoneTextField;
    public TextField emailTextField;

    public RadioButton maleRadioButton;
    public RadioButton femaleRadioButton;
    public RadioButton otherRadioButton;

    public Button backToStartButton;
    public Button nextToBasicDataWindow2Button;

    public ToggleGroup sexRadioButtonToggleGroup;

    public boolean validateName() {
        String value = nameTextField.getText();
        Styling.showError(nameLabel, Validator.inputValid(value, true, true, InputType.NAME));

        return Validator.inputValid(value, true, true, InputType.NAME);
    }

    public boolean validateSurname() {
        String value = surnameTextField.getText();
        Styling.showError(surnameLabel, Validator.inputValid(value, true, true, InputType.NAME));

        return Validator.inputValid(value, true, true, InputType.NAME);
    }

    public boolean validateTelephone(KeyEvent key) {
        String value = telephoneTextField.getText();
        Styling.showError(telephoneLabel, Validator.inputValid(value, false, true, InputType.TELEPHONE));

        return Validator.inputValid(value, true, true, InputType.TELEPHONE);
    }

    public boolean validateEmail() {
        String value = emailTextField.getText();
        Styling.showError(emailLabel, Validator.inputValid(value, true, true, InputType.EMAIL));

        return Validator.inputValid(value, true, true, InputType.EMAIL);
    }

    public String getSexRadioButtonSelected() {
        RadioButton selectedRadioButton = (RadioButton) sexRadioButtonToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        } else { return ""; }
    }

    public boolean validateSexRadioButton() {
        return !this.getSexRadioButtonSelected().isEmpty();
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        BasicData1SceneJsonStorage sceneJsonStorage = new BasicData1SceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected boolean validateAll() {
        return (this.validateName() && this.validateSurname() && this.validateEmail() && this.validateSexRadioButton());
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        BasicData1SceneJsonStorage sceneJsonStorage = new BasicData1SceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson(nameTextField.getId(), nameTextField.getText());
        sceneJsonStorage.writePartialDataToSubJson(surnameTextField.getId(), surnameTextField.getText());
        sceneJsonStorage.writePartialDataToSubJson(telephoneTextField.getId(), telephoneTextField.getText());
        sceneJsonStorage.writePartialDataToSubJson(emailTextField.getId(), emailTextField.getText());
        sceneJsonStorage.writePartialDataToSubJson("sexRadioButtonToggleGroup", getSexRadioButtonSelected());
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goLoadDataBasicData1Scene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            nameTextField.setText((String) fromStorageData.get(nameTextField.getId()));
            surnameTextField.setText((String) fromStorageData.get(surnameTextField.getId()));
            telephoneTextField.setText((String) fromStorageData.get(telephoneTextField.getId()));
            emailTextField.setText((String) fromStorageData.get(emailTextField.getId()));
            String sex = (String) fromStorageData.get("sexRadioButtonToggleGroup");
            String male = maleRadioButton.getText();
            String female = femaleRadioButton.getText();
            if (sex.equals(male)) {
                sexRadioButtonToggleGroup.selectToggle(maleRadioButton);
            }
            if (sex.equals(female)) {
                sexRadioButtonToggleGroup.selectToggle(femaleRadioButton);
            }
            if (!sex.equals(male) && !sex.equals(female)) {
                sexRadioButtonToggleGroup.selectToggle(otherRadioButton);
            }
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    public void goBackToStartScene(ActionEvent actionEvent) throws IOException {
        loadNextScene(SceneTitles.START_SCENE_TITLE.value, ScenePaths.START_SCENE.value);
    }

    public void goNextToBasicData2SceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (this.validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadNextScene(SceneTitles.BASIC_DATA_2_SCENE_TITLE.value, ScenePaths.BASIC_DATA_2_SCENE.value);
        } else {
            Styling.showError(nameLabel, Validator.inputValid(nameTextField.getText(),
                    true, true, InputType.NAME));
            Styling.showError(surnameLabel, Validator.inputValid(surnameTextField.getText(),
                    true, true, InputType.NAME));
            Styling.showError(emailLabel, Validator.inputValid(emailTextField.getText(),
                    true, true, InputType.EMAIL));
            Styling.showError(telephoneLabel, Validator.inputValid(telephoneTextField.getText(),
                    false, true, InputType.TELEPHONE));
            Styling.showError(sexLabel, Validator.inputValid(this.getSexRadioButtonSelected(),
                    true, true, InputType.SEX));
            new InvalidInputErrorAlert().showAndWait();
        }
    }
}
