package com.example.myapplication;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.helper.Helper;
import com.example.myapplication.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    com.example.myapplication.adapter.Adapter adapter;
    Helper db;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new com.example.myapplication.adapter.Adapter(MainActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String name = lists.get(i).getName();
                final String deskripsi = lists.get(i).getDeskripsi();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("deskripsi", deskripsi);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();

                return false;
            }
        });
        getData();
    }


    private void getData() {
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String deskripsi = rows.get(i).get("deskripsi");

            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setDeskripsi(deskripsi);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }



}
