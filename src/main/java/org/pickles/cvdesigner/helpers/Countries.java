package org.pickles.cvdesigner.helpers;

import java.util.Locale;

public class Countries {
    private final String [] countryCodes = Locale.getISOCountries();
    private String[] countries;

    Countries() {
        countries = new String[countryCodes.length];
        int i = 0;
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            String country = locale.getDisplayCountry();
            countries[i] = country;
            i++;
        }
    }

    public boolean isCountry(String text) {
        for (String country : countries) {
            if (country.equals(text)) {
                return true;
            }
        }
        return false;
    }
}
