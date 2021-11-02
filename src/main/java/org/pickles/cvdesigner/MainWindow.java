package org.pickles.cvdesigner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.pickles.cvdesigner.enums.WindowSizes;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("fxml/mainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),
                WindowSizes.MAIN_WINDOW_WIDTH.value,
                WindowSizes.MAIN_WINDOW_HEIGHT.value);
        stage.setTitle("CV-Designer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
