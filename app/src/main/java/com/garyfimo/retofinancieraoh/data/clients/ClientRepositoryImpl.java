package com.garyfimo.retofinancieraoh.data.clients;

import com.garyfimo.retofinancieraoh.domain.clients.Client;
import com.garyfimo.retofinancieraoh.domain.clients.ClientRepository;

public class ClientRepositoryImpl implements ClientRepository {

    private ClientCloudDataStore cloudDataStore;

    public ClientRepositoryImpl() {
        cloudDataStore = new ClientCloudDataStore();
    }

    @Override
    public void getClients(GetClientsCallback callback) {
        cloudDataStore.getClients(callback);
    }

    @Override
    public void addClient(Client client) {
        cloudDataStore.addClient(client);
    }
}
