package org.pickles.cvdesigner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pickles.cvdesigner.enums.SceneSizes;

import java.io.IOException;

public class Main extends Application {

    public static Stage mainStage;
    public static FXMLLoader fxmlLoader;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;

        fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/01startScene.fxml"));
        scene = new Scene(fxmlLoader.load(),
                SceneSizes.MAIN_WIDTH.value,
                SceneSizes.MAIN_HEIGHT.value);
        stage.setTitle("CV-Designer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
