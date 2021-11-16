package org.pickles.cvdesigner.helpers;

public abstract class Validator {
    public static boolean textValid(String text, boolean obligatory, boolean strictCheck, String type) {
        if (text == null) {
            return false;
        } else {
            if (text.isBlank()) {
                return !obligatory;
            } else {
                if (strictCheck) {
                    if (type.equals("name")) {
                        return text.matches("([A-Z][a-z]+)(\s([A-Z][a-z]+))*");
                    }
                    if (type.equals("email")) {
                        return text.matches("(\\w)+@(\\w)+");
                    } else {
                        return false;
                    }
                } else {return true;}
            }
        }
    }
}
