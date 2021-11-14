package org.pickles.cvdesigner.helpers;

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
}
