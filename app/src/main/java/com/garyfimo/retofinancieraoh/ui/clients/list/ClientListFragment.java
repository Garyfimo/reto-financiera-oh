package com.garyfimo.retofinancieraoh.ui.clients.list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.garyfimo.retofinancieraoh.R;
import com.garyfimo.retofinancieraoh.ui.clients.ClientModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientListFragment extends Fragment implements View.OnClickListener, ClientListView {

    @BindView(R.id.layout_loading)
    LinearLayout llLoading;
    @BindView(R.id.recyclerView)
    RecyclerView rvClients;
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    private ClientListPresenter presenter;

    private OnClientListFragmentInteractionListener mListener;

    public ClientListFragment() {
    }

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ClientListPresenter();
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getClients();
        iniRecyclerView();
        initButtonListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClientListFragmentInteractionListener) {
            mListener = (OnClientListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initButtonListener() {
        fabAdd.setOnClickListener(this);
    }

    private void iniRecyclerView() {
        rvClients.setLayoutManager(new LinearLayoutManager(getContext()));
        rvClients.setAdapter(new ClientAdapter());
    }

    @Override
    public void showLoading() {
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void onGetClientsSuccess(List<ClientModel> clients) {
        ((ClientAdapter) Objects.requireNonNull(rvClients.getAdapter())).setClientList(clients);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_add) {
            mListener.onNewClientButtonClicked();
        }
    }

    public interface OnClientListFragmentInteractionListener {
        void onNewClientButtonClicked();
    }
}
