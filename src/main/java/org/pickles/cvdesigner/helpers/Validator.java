package org.pickles.cvdesigner.helpers;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.apache.commons.validator.routines.EmailValidator;
import org.pickles.cvdesigner.enums.InputType;
import java.util.regex.Pattern;


public abstract class Validator {
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    private static final Countries countriesList = new Countries();

    private static final Pattern namePattern =
            Pattern.compile("^\\p{Lu}\\p{Ll}*$", Pattern.UNICODE_CHARACTER_CLASS);

    public static boolean inputValid(String text, boolean obligatory, boolean strictCheck, InputType type) {
        if (text == null) { return false; }
        if (text.isBlank()) { return !obligatory; }
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
                case COUNTRY -> { return countriesList.isCountry(text); }
                case CAPITALIZED -> { return Character.isUpperCase(text.charAt(0)); }
                case NIP -> { return validateNIP(text); }
            }
        }
        return true;
    }

    private static boolean validateNIP(String text) {
        if (text.matches("\\d{10}")) {
            String[] nipString = text.split("");
            Integer[] nip = new Integer[10];
            Integer[] weights = new Integer[]{6, 5, 7, 2, 3, 4, 5, 6, 7};
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                nip[i] = Integer.parseInt(nipString[i]) * weights[i];
                sum+=nip[i];
            }
            nip[9] = Integer.parseInt(nipString[9]);
            return sum%11==nip[9];
        } else { return false; }
    }
}
