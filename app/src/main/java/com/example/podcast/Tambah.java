package com.example.podcast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.podcast.model.Podcast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambah extends AppCompatActivity {

    private EditText editTextNama, editTextDeskripsi, editTextAlamat;
    private Button buttonTambah;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        apiService = ApiClient.getClient().create(ApiService.class);

        editTextNama = findViewById(R.id.editTextNama);
        editTextDeskripsi = findViewById(R.id.editTextDeskripsi);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        buttonTambah = findViewById(R.id.buttonTambah);

        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editTextNama.getText().toString();
                String deskripsi = editTextDeskripsi.getText().toString();
                String alamat = editTextAlamat.getText().toString();
                Log.d("marcel", nama + deskripsi + alamat);

                // Buat objek Podcast dengan data yang diinputkan
                Podcast podcast = new Podcast(nama, deskripsi, alamat);

                // Panggil metode createPodcast untuk menambahkan data ke API
                createPodcast(podcast);
            }
        });
    }

    private void createPodcast(Podcast podcast) {
        Call<Podcast> call = apiService.createPodcast(podcast);
        call.enqueue(new Callback<Podcast>() {
            @Override
            public void onResponse(Call<Podcast> call, Response<Podcast> response) {
                if (response.isSuccessful()) {
                    // Data berhasil ditambahkan
                    Toast.makeText(Tambah.this, "Podcast berhasil ditambahkan", Toast.LENGTH_SHORT).show();

                    // Dapatkan data podcast yang ditambahkan
                    Podcast addedPodcast = response.body();

                    // Kirim kembali data podcast yang ditambahkan ke MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("addedPodcast", addedPodcast);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // Gagal menambahkan data
                    Toast.makeText(Tambah.this, "Gagal menambahkan podcast", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Podcast> call, Throwable t) {
                // Tangani kesalahan jaringan atau koneksi
                Toast.makeText(Tambah.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
