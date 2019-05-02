package com.garyfimo.retofinancieraoh.ui.clients.list;

import com.garyfimo.retofinancieraoh.domain.clients.Client;
import com.garyfimo.retofinancieraoh.domain.clients.list.GetClientsListener;
import com.garyfimo.retofinancieraoh.domain.clients.list.GetClientsUseCase;
import com.garyfimo.retofinancieraoh.ui.clients.ClientModelMapper;

import java.security.InvalidParameterException;
import java.util.List;

public class ClientListPresenter implements GetClientsListener {

    private GetClientsUseCase useCase;
    private ClientListView view;
    private ClientModelMapper mapper;

    public ClientListPresenter() {
        mapper = new ClientModelMapper();
        useCase = new GetClientsUseCase();
        useCase.setListener(this);
    }

    public void setView(ClientListView view) {
        this.view = view;
    }

    public void getClients() {
        if (view == null) throw new InvalidParameterException();
        view.showLoading();
        useCase.getClients();
    }

    @Override
    public void onGetClientsSuccess(List<Client> clients) {
        view.hideLoading();
        view.onGetClientsSuccess(mapper.parse(clients));
    }
}
