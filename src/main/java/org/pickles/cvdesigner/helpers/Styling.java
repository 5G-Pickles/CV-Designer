package org.pickles.cvdesigner.helpers;

import com.google.i18n.phonenumbers.AsYouTypeFormatter;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
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
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        AsYouTypeFormatter formatter = phoneUtil.getAsYouTypeFormatter("PL");
        String telephone = telephoneTextField.getText();
        String newTelephone = "";
        for (Character number: telephone.toCharArray()) {
                newTelephone = formatter.inputDigit(number);
        }

        telephoneTextField.clear();
        for (Character number: newTelephone.toCharArray()) {
            telephoneTextField.appendText(number.toString());
        }
    }
}
