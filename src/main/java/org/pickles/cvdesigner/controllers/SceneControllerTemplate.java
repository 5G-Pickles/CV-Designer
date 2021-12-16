package org.pickles.cvdesigner.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.Main;
import org.pickles.cvdesigner.enums.SceneSizes;

import java.io.IOException;

import static org.pickles.cvdesigner.Main.*;

public abstract class SceneControllerTemplate {
    protected static JSONObject fromStorageData;

    protected static void loadNextScene(String sceneTitle, String resourceName) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource(resourceName));
        scene = new Scene(fxmlLoader.load(),
                SceneSizes.MAIN_WIDTH.value,
                SceneSizes.MAIN_HEIGHT.value);
        mainStage.setTitle(sceneTitle);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    protected abstract void loadData(ActionEvent actionEvent) throws IOException, ParseException;

    protected abstract void setDataFromListViewItemData();

    protected abstract boolean validateAll();

    protected abstract String writeDataToJson() throws IOException, ParseException;
}
