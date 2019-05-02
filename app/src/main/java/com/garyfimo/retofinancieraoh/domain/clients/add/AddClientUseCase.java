package com.garyfimo.retofinancieraoh.domain.clients.add;

import com.garyfimo.retofinancieraoh.data.clients.ClientRepositoryImpl;
import com.garyfimo.retofinancieraoh.domain.clients.Client;
import com.garyfimo.retofinancieraoh.domain.clients.ClientRepository;

public class AddClientUseCase {

    private ClientRepository clientRepository;

    public AddClientUseCase() {
        clientRepository = new ClientRepositoryImpl();
    }

    public void addClient(Client client) {
        clientRepository.addClient(client);
    }
}
