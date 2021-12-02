package org.pickles.cvdesigner.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pickles.cvdesigner.Main;
import org.pickles.cvdesigner.enums.SceneSizes;

import java.io.IOException;

public abstract class ControllerTemplate {
    private static final Stage stage = Main.mainStage;

    static void loadScene(String sceneTitle, String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceName));
        Scene scene = new Scene(fxmlLoader.load(),
                SceneSizes.MAIN_WIDTH.value,
                SceneSizes.MAIN_HEIGHT.value);
        stage.setTitle(sceneTitle);
        stage.setScene(scene);
        stage.show();
    }

    protected abstract boolean validateAll();
}
