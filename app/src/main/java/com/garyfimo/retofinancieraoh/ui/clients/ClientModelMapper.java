package com.garyfimo.retofinancieraoh.ui.clients;

import android.util.Log;

import com.garyfimo.retofinancieraoh.domain.clients.Client;
import com.garyfimo.retofinancieraoh.ui.util.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientModelMapper {

    private static final String TAG = ClientModelMapper.class.getSimpleName();

    private DateUtils dateUtils;

    public ClientModelMapper() {
        dateUtils = new DateUtils();
    }

    public Client parse(String name, String lastname, int age, String birthday) {
        return new Client(name, lastname, age, getBirthdayDate(birthday));
    }

    public ClientModel parse(String fullname, String fullAgeDescription) {
        return new ClientModel(fullname, fullAgeDescription);
    }

    public List<ClientModel> parse(List<Client> clients) {
        List<ClientModel> clientModels = new ArrayList<>();
        for (Client client : clients) {
            clientModels.add(parse(getFullnameMerged(client.getName(), client.getLastname()),
                    getFullAgeDescriptionMerged(client.getAge(), client.getBirthday())
                    )
            );
        }
        return clientModels;
    }

    private String getFullnameMerged(String name, String lastname) {
        return String.format("%s %s", name, lastname);
    }

    private String getFullAgeDescriptionMerged(int age, Date birthday) {
        return String.format("Edad %s nacido el %s", age, getBirthdayString(birthday));
    }

    private Date getBirthdayDate(String birthday) {
        try {
            return dateUtils.getDateFromString(birthday);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    private String getBirthdayString(Date date) {
        return dateUtils.getStringFromDate(date);
    }
}
