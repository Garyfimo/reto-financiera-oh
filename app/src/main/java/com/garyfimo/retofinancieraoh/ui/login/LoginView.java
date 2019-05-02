package com.garyfimo.retofinancieraoh.ui.login;

import com.garyfimo.retofinancieraoh.ui.LoadingView;

public interface LoginView extends LoadingView {

    void phoneNumberEmpty();

    void requestValidationCode(String s);
}
