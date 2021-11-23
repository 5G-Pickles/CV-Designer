package org.pickles.cvdesigner.helpers;
import com.google.i18n.phonenumbers.NumberParseException;
import org.apache.commons.validator.routines.EmailValidator;
import org.pickles.cvdesigner.enums.InputType;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public abstract class Validator {
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static boolean textValid(String text, boolean obligatory, boolean strictCheck, InputType type) {
        if (text == null) {
            return false;
        }
        if (text.isBlank()) {
            return !obligatory;
        }
        if (strictCheck) {
            switch (type) {
                case NAME -> { return !text.isBlank() && text.length() > 2; }
                case EMAIL -> { return EmailValidator.getInstance().isValid(text); }
                case TELEPHONE -> {
                    try {
                        return phoneUtil.isValidNumber(phoneUtil.parse(text, "PL"));
                    } catch (NumberParseException e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
