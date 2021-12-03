package org.pickles.cvdesigner.alerts;

import javafx.scene.control.Alert;

public class InvalidInputErrorAlert extends Alert {
    public InvalidInputErrorAlert() {
        super(AlertType.ERROR);
        this.setHeaderText("Input not valid");
        this.setContentText("Please make sure all fields are filled-in appropriately");
    }
}
