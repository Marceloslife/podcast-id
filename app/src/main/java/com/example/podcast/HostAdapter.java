package com.example.podcast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podcast.model.Host;

import java.util.List;

public class HostAdapter extends RecyclerView.Adapter<HostAdapter.HostViewHolder> {
    private List<Host> hostList;
    private HostClickListener hostClickListener;

    public HostAdapter(List<Host> hostList, HostClickListener hostClickListener) {
        this.hostList = hostList;
        this.hostClickListener = hostClickListener;
    }

    @NonNull
    @Override
    public HostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_host, parent, false);
        return new HostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HostAdapter.HostViewHolder holder, int position) {
        Host host = hostList.get(position);
        holder.bind(host);
    }

    @Override
    public int getItemCount() {
        return hostList.size();
    }

    public interface HostClickListener {
        void onHostClick(int position);
    }

    public class HostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvNama, tvKeahlian;

        public HostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.textViewTitle);
            tvKeahlian = itemView.findViewById(R.id.textViewKeahlian);
            itemView.setOnClickListener(this);
        }

        public void bind(Host host) {
            tvNama.setText(host.getTitle());
            tvKeahlian.setText(host.getKeahlian());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                hostClickListener.onHostClick(position);
            }
        }
    }
}
