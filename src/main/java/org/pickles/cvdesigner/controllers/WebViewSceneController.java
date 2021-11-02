package org.pickles.cvdesigner.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class WebViewSceneController {
    @FXML
    private WebView webView;

    @FXML
    private void initialize() throws MalformedURLException {
        WebEngine engine = webView.getEngine();
        URL url = new URL("https://www.javatpoint.com/java-tutorial");
        engine.load(Objects.requireNonNull(url).toString());
    }
}
