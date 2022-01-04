package org.pickles.cvdesigner.controllers;

import com.sun.net.httpserver.HttpServer;
import javafx.event.ActionEvent;
import javafx.print.*;
import javafx.scene.web.WebView;
import org.json.simple.parser.ParseException;
import org.pickles.cvdesigner.alerts.InvalidInputErrorAlert;
import org.pickles.cvdesigner.alerts.StorageWriteErrorAlert;
import org.pickles.cvdesigner.enums.ScenePaths;
import org.pickles.cvdesigner.enums.SceneTitles;
import org.pickles.cvdesigner.storage.StorageServer;

import java.io.IOException;

import static org.pickles.cvdesigner.controllers.SceneControllerTemplate.loadScene;

public class DesignerSceneController {
    public WebView designerWebView;

    private void writeDataToJson() throws ParseException{

    }

    private boolean validateAll() {
        return true;
    }

    public void goBackToOtherInfoButton(ActionEvent actionEvent) throws IOException {
        if (validateAll()) {
            try {
                writeDataToJson();
            } catch (ParseException e) {
                new StorageWriteErrorAlert();
            }
            loadScene(SceneTitles.OTHER_INFO_SCENE_TITLE.value, ScenePaths.OTHER_INFO_SCENE.value);
        } else {
            new InvalidInputErrorAlert().showAndWait();
        }
    }

    public void goNext(ActionEvent actionEvent) {
        Printer printerToUse = Printer.getDefaultPrinter();
        PrinterJob job = PrinterJob.createPrinterJob(printerToUse);
        PageLayout pageLayout = printerToUse.createPageLayout(Paper.A4,
                PageOrientation.PORTRAIT, 0, 0, 0, 0);

        job.setPrinter(printerToUse);
        job.printPage(pageLayout, designerWebView);
        job.endJob();
    }

    public void goLoadData(ActionEvent actionEvent) throws Exception {
        StorageServer.startServer();
        designerWebView.getEngine().load("http://localhost:3000/");
    }
}
