package com.garyfimo.retofinancieraoh.domain.clients.list;

import com.garyfimo.retofinancieraoh.data.clients.ClientRepositoryImpl;
import com.garyfimo.retofinancieraoh.domain.clients.Client;
import com.garyfimo.retofinancieraoh.domain.clients.ClientRepository;

import java.util.List;

public class GetClientsUseCase implements ClientRepository.GetClientsCallback {

    private ClientRepository clientRepository;
    private GetClientsListener listener;

    public GetClientsUseCase() {
        clientRepository = new ClientRepositoryImpl();
    }

    public void setListener(GetClientsListener listener) {
        this.listener = listener;
    }

    public void getClients() {
        clientRepository.getClients(this);
    }

    @Override
    public void onGetClientsSuccess(List<Client> clients) {
        listener.onGetClientsSuccess(clients);
    }
}
