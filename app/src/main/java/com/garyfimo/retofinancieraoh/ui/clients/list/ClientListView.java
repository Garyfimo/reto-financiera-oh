package com.garyfimo.retofinancieraoh.ui.clients.list;

import com.garyfimo.retofinancieraoh.ui.LoadingView;
import com.garyfimo.retofinancieraoh.ui.clients.ClientModel;

import java.util.List;

public interface ClientListView extends LoadingView {

    void onGetClientsSuccess(List<ClientModel> clients);
}
