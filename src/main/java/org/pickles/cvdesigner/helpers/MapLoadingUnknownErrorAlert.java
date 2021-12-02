package org.pickles.cvdesigner.helpers;

import javafx.scene.control.Alert;

public class MapLoadingUnknownErrorAlert extends Alert {
    public MapLoadingUnknownErrorAlert(AlertType alertType) {
        super(alertType);
        this.setHeaderText("Unknown API error occurred");
        this.setContentText("An unknown StaticMaps API error occurred");
    }
}
