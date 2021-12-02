package org.pickles.cvdesigner.helpers;

import javafx.scene.control.Alert;

public class MapLoadingConnectionErrorAlert extends Alert {
    public MapLoadingConnectionErrorAlert(AlertType alertType) {
        super(alertType);
        this.setHeaderText("Could not load map");
        this.setContentText("Could not contact StaticMaps API - " +
                "please check your internet connection or try again later");
    }
}
