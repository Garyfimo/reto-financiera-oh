package com.garyfimo.retofinancieraoh.domain.util;

public class PhoneNumberUtil {

    public String addPrefixCountry(String phoneNumber) {
        return String.format("+51%s", phoneNumber);
    }
}
