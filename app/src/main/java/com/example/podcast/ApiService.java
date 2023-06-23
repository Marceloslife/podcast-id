package com.example.podcast;

import com.example.podcast.model.Host;
import com.example.podcast.model.Podcast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("podcasts")
    Call<List<Podcast>> getPodcasts();

    @GET("host")
    Call<List<Host>> getHost();

    @POST("podcasts")
    Call<Podcast> createPodcast(@Body Podcast podcast);

    @PUT("podcasts/{id}")
    Call<Podcast> updatePodcast(@Path("id") String id, @Body Podcast podcast);

    @DELETE("podcasts/{id}")
    Call<Void> deletePodcast(@Path("id") String id);
}
