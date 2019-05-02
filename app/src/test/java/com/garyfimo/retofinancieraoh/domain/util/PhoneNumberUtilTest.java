package com.garyfimo.retofinancieraoh.domain.util;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PhoneNumberUtilTest {

    private PhoneNumberUtil phoneNumberUtil;
    private String phoneNumber;
    private String prefix;

    @Before
    public void setUp() {
        phoneNumberUtil = new PhoneNumberUtil();
        phoneNumber = "959821390";
        prefix = "+51";
    }

    @Test
    public void addPrefixCountry() {
        String phoneNumberWithPrefix = String.format("%s%s", prefix, phoneNumber);
        assertThat(phoneNumberUtil.addPrefixCountry(phoneNumber),is(phoneNumberWithPrefix));
    }
}