package com.garyfimo.retofinancieraoh.ui.login;

import android.text.TextUtils;

import com.garyfimo.retofinancieraoh.domain.util.PhoneNumberUtil;


public class LoginPresenter {

    private LoginView view;

    public void setView(LoginView view) {
        this.view = view;
    }

    void addPrefrix(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            view.phoneNumberEmpty();
            return;
        }
        view.requestValidationCode(new PhoneNumberUtil()
                .addPrefixCountry(phoneNumber));
    }
}
