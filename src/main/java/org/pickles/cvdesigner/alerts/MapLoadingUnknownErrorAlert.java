package org.pickles.cvdesigner.alerts;

import javafx.scene.control.Alert;

public class MapLoadingUnknownErrorAlert extends Alert {
    public MapLoadingUnknownErrorAlert() {
        super(AlertType.ERROR);
        this.setHeaderText("Unknown API error occurred");
        this.setContentText("An unknown StaticMaps API error occurred");
    }
}
