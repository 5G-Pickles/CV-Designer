package org.pickles.cvdesigner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.pickles.cvdesigner.enums.SceneSizes;

public class Main extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/01startScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),
                SceneSizes.MAIN_WIDTH.value,
                SceneSizes.MAIN_HEIGHT.value);
        stage.setTitle("CV-Designer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
