package com.garyfimo.retofinancieraoh;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showToast(int idMessage) {
        Toast.makeText(this, idMessage, Toast.LENGTH_LONG).show();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public abstract void showNotNetworkConnection();

    public abstract void hideNotNetworkConnection();

    public void checkNetworkConnectivity() {
        if (isOnline()) {
            hideNotNetworkConnection();
        } else {
            showNotNetworkConnection();
        }
    }
}