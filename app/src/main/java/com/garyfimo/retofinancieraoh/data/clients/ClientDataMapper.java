package com.garyfimo.retofinancieraoh.data.clients;

import com.garyfimo.retofinancieraoh.domain.clients.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientDataMapper {

    protected Map<String, Object> parse(Client client) {
        Map<String, Object> clientEntry = new HashMap<>();
        clientEntry.put("name", client.getName());
        clientEntry.put("lastname", client.getLastname());
        clientEntry.put("age", client.getAge());
        clientEntry.put("birthday", client.getBirthday());
        return clientEntry;
    }
}