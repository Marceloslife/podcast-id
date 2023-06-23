package com.example.podcast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podcast.model.Podcast;

import java.util.List;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder> {
    private List<Podcast> podcastList;
    private PodcastClickListener podcastClickListener;

    @NonNull
    @Override
    public PodcastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_podcast, parent, false);
        return new PodcastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastViewHolder holder, int position) {
        Podcast podcast = podcastList.get(position);
        holder.bind(podcast);
    }

    @Override
    public int getItemCount() {
        return podcastList.size();
    }

    public interface PodcastClickListener {
        void onPodcastClick(int position);
    }

    public PodcastAdapter(List<Podcast> podcastList, PodcastClickListener podcastClickListener) {
        this.podcastList = podcastList;
        this.podcastClickListener = podcastClickListener;
    }

    // ...
    // Implementasi metode-metode lainnya
    // ...

    public class PodcastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // ...
        private TextView tvTitle, tvDescription, tvAddress;

        public PodcastViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.textViewTitle);
            tvDescription = itemView.findViewById(R.id.textViewDescription);
            tvAddress = itemView.findViewById(R.id.textViewAddress);
            // ...

            itemView.setOnClickListener(this);
        }

        public void bind(Podcast podcast){
            tvTitle.setText(podcast.getTitle());
            tvDescription.setText(podcast.getDescription());
            tvAddress.setText(podcast.getAddress());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                podcastClickListener.onPodcastClick(position);
            }
        }
    }
}
