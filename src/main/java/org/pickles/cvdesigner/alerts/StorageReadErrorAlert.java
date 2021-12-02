package org.pickles.cvdesigner.alerts;

import javafx.scene.control.Alert;

public class StorageReadErrorAlert extends Alert {
    public StorageReadErrorAlert() {
        super(AlertType.ERROR);
        this.setHeaderText("Failed to read from storage");
        this.setContentText("An error occurred while reading from application storage - please " +
                "restart the application or delete the \"storage.json\" located in you home directory");
    }
}