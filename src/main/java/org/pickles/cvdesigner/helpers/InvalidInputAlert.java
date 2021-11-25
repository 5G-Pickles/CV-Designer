package org.pickles.cvdesigner.helpers;

import javafx.scene.control.Alert;

public class InvalidInputAlert extends Alert {
    public InvalidInputAlert(AlertType alertType) {
        super(alertType);
        this.setHeaderText("Input not valid");
        this.setContentText("Please make sure all fields are filled in appropriately");
    }
}
