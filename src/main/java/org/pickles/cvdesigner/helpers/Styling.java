package org.pickles.cvdesigner.helpers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public abstract class Styling {
    public static void showError(Label label, boolean errorExists) {
        if (errorExists) {
            label.setTextFill(new Color(0.0, 0.2996, 0.7004, 1.0));
        } else {
            label.setTextFill(new Color(199/255.0, 63/255.0, 101/255.0, 1.0));
        }
    }

    public static void formatTelephoneNumber(TextField telephoneTextField) {
        String telephone = telephoneTextField.getText();
        int lengthWithoutWhitespaces = telephone.replaceAll("\\s","").length();

        if (!telephone.isBlank() && telephone.substring(telephone.length()-1).matches("\\d")) {
            if (telephone.length() > 3) {
                if ((lengthWithoutWhitespaces - 2) % 3 == 0) {
                    telephoneTextField.setText(telephone.substring(0, telephone.length() - 2) + " " + telephone.substring(telephone.length() - 2));
                    telephoneTextField.positionCaret(telephoneTextField.getText().length());
                }
            }
        } else {
            telephoneTextField.deletePreviousChar();
        }
    }
}
