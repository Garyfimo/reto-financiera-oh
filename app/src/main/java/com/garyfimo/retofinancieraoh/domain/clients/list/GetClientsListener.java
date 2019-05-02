package com.garyfimo.retofinancieraoh.domain.clients.list;

import com.garyfimo.retofinancieraoh.domain.clients.Client;

import java.util.List;

public interface GetClientsListener {

    void onGetClientsSuccess(List<Client> clients);
}
