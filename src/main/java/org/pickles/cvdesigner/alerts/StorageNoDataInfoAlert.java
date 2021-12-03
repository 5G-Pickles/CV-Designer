package org.pickles.cvdesigner.alerts;

import javafx.scene.control.Alert;

public class StorageNoDataInfoAlert extends Alert {
    public StorageNoDataInfoAlert() {
        super(AlertType.ERROR);
        this.setHeaderText("No previous data found");
        this.setContentText("No data for this window has been found in storage - please fill missing information");
    }
}
