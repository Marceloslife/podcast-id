package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editName,editDeskripsi;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, name, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editName = findViewById(R.id.edit_name);
        editDeskripsi = findViewById(R.id.edit_deskripsi);
        btnSave = findViewById(R.id.btn_save);

        id = getIntent().getStringExtra("id");
        name= getIntent().getStringExtra("name");
        deskripsi=getIntent().getStringExtra("deskripsi");

        if(id == null || id.equals("")) {
            setTitle("Tambah User");
        }else{
            setTitle("Edit User");
            editName.setText(name);
            editDeskripsi.setText(deskripsi);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id == null || id.equals("")){
                        save();

                    } else {
                        edit();

                    }
                }catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });

    }
    private void save(){
        if (String.valueOf(editName.getText()).equals("")|| String.valueOf(editDeskripsi.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data",Toast.LENGTH_SHORT).show();
        }else {
            db.insert(editName.getText().toString(),editDeskripsi.getText().toString());
            finish();
        }
    }
    private void edit(){
        if (String.valueOf(editName.getText()).equals("")|| String.valueOf(editDeskripsi.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data",Toast.LENGTH_SHORT).show();
        }else {
            db.update(Integer.parseInt(id),editName.getText().toString(), editDeskripsi.getText().toString());
            finish();
        }
    }
}