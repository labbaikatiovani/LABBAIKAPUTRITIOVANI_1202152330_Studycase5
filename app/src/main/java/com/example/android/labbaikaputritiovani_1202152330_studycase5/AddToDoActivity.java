package com.example.android.labbaikaputritiovani_1202152330_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDoActivity extends AppCompatActivity {
    //deklarasi variable
    EditText ToDo, Description, Priority;
    Database dtbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        //set title menjadi add to do
        setTitle("Add To Do");

        //binding data
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        dtbase = new Database(this);
    }

    @Override
    public void onBackPressed() {
        //pindah activity ke to do list activity
        Intent intent = new Intent(AddToDoActivity.this, ToDoListActivity.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivitas setelah intent dijalankan
        this.finish();
    }

    //method yang dijalanan ketika tombol tambah diklik
    public void tambah(View view) {
        //apabila semua data diisi
        if (dtbase.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //maka akan menampilkan toast bawha data berhasil di tambahkan ke dalam list
            Toast.makeText(this, "One To Do List added", Toast.LENGTH_SHORT).show();
           //pindah activity ke to do list
            startActivity(new Intent(AddToDoActivity.this, ToDoListActivity.class));
            //menutup aktivitas agar tidak kembali ke activity yang dijalankan setelah intent
            this.finish();
        }else {
            //apabila data kosong
            Toast.makeText(this, "Cannot add the list", Toast.LENGTH_SHORT).show();
            //set field menjadi kosong
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }
}
