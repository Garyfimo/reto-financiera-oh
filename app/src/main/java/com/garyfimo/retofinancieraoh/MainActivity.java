package com.garyfimo.retofinancieraoh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.garyfimo.retofinancieraoh.ui.clients.add.NewClientFragment;
import com.garyfimo.retofinancieraoh.ui.clients.list.ClientListFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NewClientFragment.OnNewClientFragmentInteractionListener,
        ClientListFragment.OnClientListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addClientListFragment();
    }

    private void addNewClientFragment() {
        Fragment fragment = NewClientFragment.newInstance();
        addFragmentToBackStack(fragment);
    }

    private void addClientListFragment() {
        Fragment fragment = ClientListFragment.newInstance();
        addFragment(fragment);
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment, fragment.getTag())
                .commit();
    }

    private void addFragmentToBackStack(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getTag())
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public void onClientAdded() {
        removeFromBackStack();
    }

    private void removeFromBackStack() {
        getSupportFragmentManager()
                .popBackStack();
    }

    @Override
    public void onNewClientButtonClicked() {
        addNewClientFragment();
    }
}
