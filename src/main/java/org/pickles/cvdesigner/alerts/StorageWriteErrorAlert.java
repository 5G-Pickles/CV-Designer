package org.pickles.cvdesigner.alerts;

import javafx.scene.control.Alert;

public class StorageWriteErrorAlert extends Alert {
    public StorageWriteErrorAlert() {
        super(AlertType.ERROR);
        this.setHeaderText("Failed to write to storage");
        this.setContentText("An error occurred while writing to application storage - please " +
                "restart the application or delete the \"storage.json\" located in you home directory");
    }
}
