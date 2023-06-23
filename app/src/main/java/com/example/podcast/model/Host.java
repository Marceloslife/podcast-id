package com.example.podcast.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Host implements Parcelable {
    private String nama;
    private String keahlian;

    public Host(Parcel in) {
        nama = in.readString();
        keahlian = in.readString();
    }

    public static final Creator<Host> CREATOR = new Creator<Host>() {
        @Override
        public Host createFromParcel(Parcel in) {
            return new Host(in);
        }

        @Override
        public Host[] newArray(int size) {
            return new Host[size];
        }
    };

    public Host(String nama, String keahlian) {
        this.nama = nama;
        this.keahlian = keahlian;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(keahlian);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return nama;
    }
    public String getKeahlian() {
        return keahlian;
    }

}

