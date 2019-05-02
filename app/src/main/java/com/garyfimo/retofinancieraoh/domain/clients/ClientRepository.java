package com.garyfimo.retofinancieraoh.domain.clients;

import java.util.List;

public interface ClientRepository {

    void getClients(GetClientsCallback callback);

    void addClient(Client client);

    interface GetClientsCallback{
        void onGetClientsSuccess(List<Client> clients);
    }
}
