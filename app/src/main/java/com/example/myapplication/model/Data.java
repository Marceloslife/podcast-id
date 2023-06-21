package com.example.myapplication.model;

public class Data {
    private String id,name,deskripsi;
    public Data(){

    }

    public Data(String id, String name, String deskripsi){
        this.id = id;
        this.name =name;
        this.deskripsi=deskripsi;
   }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}



