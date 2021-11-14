package org.pickles.cvdesigner.helpers;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public abstract class Styling {
    public static void showError(Label label, boolean error) {
        if (error) {
            label.setTextFill(new Color(0.0, 0.2996, 0.7004, 1.0));
        } else {
            label.setTextFill(new Color(199/255.0, 63/255.0, 101/255.0, 1.0));
        }
    }
}
