package com.example.podcast.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Podcast implements Parcelable {
    private String nama;
    private String deskripsi;
    private String alamat;

    // Konstruktor, getter, setter, dan metode lainnya

    // Implementasi Parcelable
    public Podcast(Parcel in) {
        nama = in.readString();
        deskripsi = in.readString();
        alamat = in.readString();
    }

    public static final Creator<Podcast> CREATOR = new Creator<Podcast>() {
        @Override
        public Podcast createFromParcel(Parcel in) {
            return new Podcast(in);
        }

        @Override
        public Podcast[] newArray(int size) {
            return new Podcast[size];
        }
    };

    public Podcast(String nama, String deskripsi, String alamat) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(deskripsi);
        dest.writeString(alamat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return nama;
    }

    public String getDescription() {
        return deskripsi;
    }

    public String getAddress() {
        return alamat;
    }
}
