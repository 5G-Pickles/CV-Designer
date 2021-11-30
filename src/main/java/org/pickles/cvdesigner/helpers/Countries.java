package org.pickles.cvdesigner.helpers;

import java.util.Locale;

public class Countries {
    private final String[] countries;

    Countries() {
        String[] countryCodes = Locale.getISOCountries();
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
            if (country.toLowerCase(Locale.ROOT).equals(text.toLowerCase())) { return true; }
        }
        return false;
    }
}
