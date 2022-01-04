package org.pickles.cvdesigner.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageNoDataInfoAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.InputType;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneSizes;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.helpers.Styling;
import org.pickles.cvdesigner.helpers.Validator;
import org.pickles.cvdesigner.storage.OtherInfoSceneJsonStorage;
import org.pickles.cvdesigner.storage.StorageServer;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class OtherInfoSceneController extends SceneControllerTemplate {
    public TextArea otherInfoTextArea;
    public Label sceneInfoLabel;

    public boolean validateOtherInfo() {
        String text = otherInfoTextArea.getText();
        Styling.showError(sceneInfoLabel, Validator.inputValid(text, false, true, InputType.CAPITALIZED));
        return Validator.inputValid(text, false, true, InputType.CAPITALIZED);
    }

    @Override
    protected void loadData(ActionEvent actionEvent) throws IOException, ParseException {
        OtherInfoSceneJsonStorage sceneJsonStorage = new OtherInfoSceneJsonStorage();
        fromStorageData = sceneJsonStorage.getDataFromStorage();
    }

    @Override
    protected void setDataFromListViewItemData() {

    }

    @Override
    protected boolean validateAll() {
        return validateOtherInfo();
    }

    @Override
    protected String writeDataToJson() throws IOException, ParseException {
        OtherInfoSceneJsonStorage sceneJsonStorage = new OtherInfoSceneJsonStorage();
        sceneJsonStorage.writePartialDataToSubJson(otherInfoTextArea.getId(), otherInfoTextArea.getText());
        return sceneJsonStorage.writeToJsonStorage();
    }

    public void goBackToSoftSkillsScene(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadScene(SceneTitles.SOFT_SKILLS_SCENE_TITLE.value, ScenePaths.SOFT_SKILLS_SCENE.value);
        } else {
            Styling.showError(sceneInfoLabel, Validator.inputValid(otherInfoTextArea.getText(), false, true, InputType.CAPITALIZED));
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goLoadDataOtherInfoScene(ActionEvent actionEvent) {
        try {
            this.loadData(actionEvent);
            if (fromStorageData == null) {
                new StorageNoDataInfoAlert();
                return;
            }
            otherInfoTextArea.setText((String) fromStorageData.get(otherInfoTextArea.getId()));
        } catch (IOException | ParseException e) {
            new StorageNoDataInfoAlert();
        }
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    // TODO: Needs a full code refactor to a fully multithreaded programme
    private void serveReactBuild() throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            builder.command("cmd.exe", "/c", "serve -s build");
        } else {
            builder.command("sh", "-c", "serve -s build");
        }
        builder.directory(new File(System.getProperty("user.dir"), "designer"));
        Process process = builder.start();
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newFixedThreadPool(20, Thread::new).submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    public void goNextToDesignerSceneAndStoreData(ActionEvent actionEvent) throws IOException {
        if (this.validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }

//            serveReactBuild();
//            StorageServer.startServer();

            loadScene(SceneTitles.DESIGNER_SCENE_TITLE.value, ScenePaths.DESIGNER_SCENE.value,
                    SceneSizes.WEB_VIEW_WIDTH, SceneSizes.WEB_VIEW_HEIGHT);
        } else {
            new InvalidInputErrorAlert().showAndWait();
        }
    }
}
