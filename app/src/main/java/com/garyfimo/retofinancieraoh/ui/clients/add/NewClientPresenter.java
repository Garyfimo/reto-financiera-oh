package com.garyfimo.retofinancieraoh.ui.clients.add;

import com.garyfimo.retofinancieraoh.domain.clients.add.AddClientUseCase;
import com.garyfimo.retofinancieraoh.ui.clients.ClientModelMapper;
import com.garyfimo.retofinancieraoh.ui.util.DateUtils;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;

public class NewClientPresenter {

    private NewClientView view;
    private AddClientUseCase useCase;
    private ClientModelMapper mapper;

    public NewClientPresenter() {
        useCase = new AddClientUseCase();
        mapper = new ClientModelMapper();
    }

    public void setView(NewClientView view) {
        this.view = view;
    }

    public void addClient(String name, String lastname, String age, String birthday) {
        if (view == null) throw new InvalidParameterException();

        if (name == null || name.isEmpty()) {
            view.nameEmpty();
            return;
        }
        if (lastname == null || lastname.isEmpty()) {
            view.lastnameEmpty();
            return;
        }
        if (age == null || age.isEmpty()) {
            view.ageEmpty();
            return;
        }
        if (birthday == null || birthday.isEmpty()) {
            view.birthdayEmpty();
            return;
        }
        useCase.addClient(mapper.parse(name, lastname, Integer.parseInt(age), birthday));
    }

    public void convertDateFromNumbers(int year, int month, int dayOfMonth) {
        view.setBirthdayText(getBirthdayString(year, month, dayOfMonth));
    }

    private String getBirthdayString(int year, int month, int dayOfMonth) {
        return new DateUtils().getStringFromDate(getBirthdayDate(year, month, dayOfMonth));
    }

    private Date getBirthdayDate(int year, int month, int dayOfMonth) {
        Calendar newDate = Calendar.getInstance();
        newDate.set(year, month, dayOfMonth);
        return newDate.getTime();
    }
}
