package org.pickles.cvdesigner.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Objects;

public class WebViewWindowController {
    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        WebEngine engine = webView.getEngine();
        URL url = this.getClass().getResource("/org/pickles/cvdesigner/webview/designer.html");
        engine.load(Objects.requireNonNull(url).toString());
    }
}
