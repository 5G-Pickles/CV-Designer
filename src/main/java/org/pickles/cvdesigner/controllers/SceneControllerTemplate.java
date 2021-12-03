package org.pickles.cvdesigner.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.Main;
import org.pickles.cvdesigner.enums.SceneSizes;

import java.io.IOException;

import static org.pickles.cvdesigner.Main.fxmlLoader;
import static org.pickles.cvdesigner.Main.scene;
import static org.pickles.cvdesigner.Main.mainStage;

public abstract class SceneControllerTemplate {
    public static JSONObject onLoadData;

    static void loadScene(String sceneTitle, String resourceName) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource(resourceName));
        scene = new Scene(fxmlLoader.load(),
                SceneSizes.MAIN_WIDTH.value,
                SceneSizes.MAIN_HEIGHT.value);
        mainStage.setTitle(sceneTitle);
        mainStage.setScene(scene);
        mainStage.show();
    }

    protected abstract boolean validateAll();

    protected abstract String writeDataToJson() throws IOException, ParseException;
}