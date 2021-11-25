package org.pickles.cvdesigner.helpers;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.pickles.cvdesigner.enums.InputType;

import java.util.regex.Pattern;


public abstract class Validator {
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    private static final Pattern namePattern =
            Pattern.compile("^\\p{Lu}\\p{Ll}*$", Pattern.UNICODE_CHARACTER_CLASS);

    public static boolean textValid(String text, boolean obligatory, boolean strictCheck, InputType type) {
        if (text == null) {
            return false;
        }
        if (text.isBlank()) {
            return !obligatory;
        }
        if (strictCheck) {
            switch (type) {
                case NAME -> { return !text.isEmpty() && !text.isBlank()
                        && text.matches(namePattern.pattern()); }
                case EMAIL -> { return EmailValidator.getInstance().isValid(text); }
                case TELEPHONE -> {
                    try {
                        return phoneUtil.isValidNumber(phoneUtil.parse(text, "PL"))
                                && text.matches("^[0-9+\s]*$");
                    } catch (NumberParseException e) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
