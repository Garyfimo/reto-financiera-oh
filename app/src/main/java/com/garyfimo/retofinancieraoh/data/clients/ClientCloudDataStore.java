package com.garyfimo.retofinancieraoh.data.clients;

import android.util.Log;

import com.garyfimo.retofinancieraoh.domain.clients.Client;
import com.garyfimo.retofinancieraoh.domain.clients.ClientRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientCloudDataStore {

    private static final String COLLECTION_NAME = "clients";
    private static final String TAG = ClientCloudDataStore.class.getSimpleName();
    private FirebaseFirestore database = FirebaseFirestore
            .getInstance();

    public ClientCloudDataStore() {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        database.setFirestoreSettings(settings);
    }

    public void addClient(Client client) {
        database.collection(COLLECTION_NAME)
                .add(client)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "client saved");
                })
                .addOnFailureListener(e -> {
                    Log.d(TAG, "Error saving client");
                });
    }

    public void getClients(ClientRepository.GetClientsCallback callback) {
        database.collection(COLLECTION_NAME)
                .get()
                .addOnCompleteListener(task -> checkResult(task, callback));
    }

    private void checkResult(Task<QuerySnapshot> task,
                             ClientRepository.GetClientsCallback callback) {
        if (task.isSuccessful()) {
            callback.onGetClientsSuccess(
                    getClientList(Objects.requireNonNull(
                            task.getResult()).getDocuments())
            );
        } else {
            Log.w(TAG, "Error getting documents.", task.getException());
        }
    }

    private List<Client> getClientList(List<DocumentSnapshot> documents) {
        List<Client> clients = new ArrayList<>();
        for (DocumentSnapshot ds : documents) {
            clients.add(ds.toObject(Client.class));
        }
        return clients;
    }
}
