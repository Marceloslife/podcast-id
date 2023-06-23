package com.example.podcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.podcast.model.Podcast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PodcastAdapter.PodcastClickListener {

    private ApiService apiService;
    private Button btnTambah;
    private RecyclerView recyclerView;
    private PodcastAdapter podcastAdapter;
    private List<Podcast> podcastList;

    private static final int REQUEST_TAMBAH_PODCAST = 1;
    private Object RetrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ApiService using RetrofitClient
        apiService = RetrofitClient.createService(ApiService.class);

        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTambahActivity();
            }
        });

        recyclerView = findViewById(R.id.recyclerViewPodcasts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        podcastList = new ArrayList<>();
        podcastAdapter = new PodcastAdapter(podcastList, this);
        recyclerView.setAdapter(podcastAdapter);

        getPodcasts();
    }

    public void openTambahActivity() {
        Intent intent = new Intent(this, Tambah.class);
        startActivityForResult(intent, REQUEST_TAMBAH_PODCAST);
    }

    private void getPodcasts() {
        Call<List<Podcast>> call = apiService.getPodcasts();
        call.enqueue(new Callback<List<Podcast>>() {
            @Override
            public void onResponse(Call<List<Podcast>> call, Response<List<Podcast>> response) {
                if (response.isSuccessful()) {
                    List<Podcast> podcasts = response.body();
                    if (podcasts != null) {
                        podcastList.addAll(podcasts);
                        podcastAdapter.notifyDataSetChanged();
                    }
                } else {
                    // Handle API response errors
                }
            }

            @Override
            public void onFailure(Call<List<Podcast>> call, Throwable t) {
                // Handle network or connection errors
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAMBAH_PODCAST && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("addedPodcast")) {
                Podcast addedPodcast = data.getParcelableExtra("addedPodcast");
                if (addedPodcast != null) {
                    podcastList.add(addedPodcast);
                    podcastAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onPodcastClick(int position) {
        Podcast podcast = podcastList.get(position);
        // Perform action when podcast item is clicked
        // For example, display the podcast details in logcat
        System.out.println("Podcast Details:");
        System.out.println("Title: " + podcast.getTitle());
        System.out.println("Description: " + podcast.getDescription());
    }

}
