package com.garyfimo.retofinancieraoh.ui.clients.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.garyfimo.retofinancieraoh.R;
import com.garyfimo.retofinancieraoh.ui.clients.ClientModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private List<ClientModel> clientList;

    public ClientAdapter() {
        clientList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_item_client, parent, false);
        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        ClientModel clientModel = clientList.get(position);
        holder.tvFullAgeDescrition.setText(clientModel.getFullAgeDescription());
        holder.tvFullname.setText(clientModel.getFullname());
    }


    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void setClientList(List<ClientModel> clientList) {
        this.clientList = clientList;
        notifyDataSetChanged();
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fullname)
        TextView tvFullname;

        @BindView(R.id.tv_full_age_description)
        TextView tvFullAgeDescrition;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
