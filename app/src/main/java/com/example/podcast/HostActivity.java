package com.example.podcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.podcast.model.Host;
import com.example.podcast.model.Podcast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HostActivity extends AppCompatActivity implements HostAdapter.HostClickListener {

    private ApiService apiService;

    private Button btnTambah;
    private RecyclerView recyclerView;
    private HostAdapter hostAdapter;
    private List<Host> hostList;

    private static final int REQUEST_TAMBAH_HOST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        // Initialize ApiService using RetrofitClient
        Object RetrofitClient = null;

        recyclerView = findViewById(R.id.recyclerViewHost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hostList = new ArrayList<>();
        hostAdapter = new HostAdapter(hostList, this);
        recyclerView.setAdapter(hostAdapter);

        getHost();
    }

    private void getHost() {
        Call<List<Host>> call = apiService.getHost();
        call.enqueue(new Callback<List<Host>>() {
            @Override
            public void onResponse(Call<List<Host>> call, Response<List<Host>> response) {
                if (response.isSuccessful()) {
                    List<Host> hosts = response.body();
                    if (hosts != null) {
                        // Update dataset in HostAdapter
                        hostList.addAll(hosts);
                        hostAdapter.notifyDataSetChanged();
                    }
                } else {
                    // Handle API response errors
                }
            }

            @Override
            public void onFailure(Call<List<Host>> call, Throwable t) {
                // Handle network or connection errors
            }
        });
    }

    @Override
    public void onHostClick(int position) {
        // Handle host click event
    }

}