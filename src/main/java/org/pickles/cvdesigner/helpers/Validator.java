package org.pickles.cvdesigner.helpers;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public abstract class Validator {
    public static boolean textValid(String text, boolean obligatory, boolean strictCheck) {
        if (text == null) {
            return false;
        } else {
            if (text.isBlank()) {
                return !obligatory;
            } else {
                if (strictCheck) {
                    return text.matches("([A-Z][a-z]+)(\s([A-Z][a-z]+))*");
                } else {return true;}
            }

        }
    }

    public static void validationPassed(Label label, boolean passed) {
        if (passed) {
            label.setTextFill(new Color(0.0, 0.2996, 0.7004, 1.0));
        } else {
            label.setTextFill(new Color(199/255.0, 63/255.0, 101/255.0, 1.0));
        }

    }
}
