package com.example.android.labbaikaputritiovani_1202152330_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {
    //deklarasi variable
    Database dtbase;
    RecyclerView rv;
    Adapter adapter;
    ArrayList<AddData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        //set title To Do List
        setTitle("To Do List");

        //mengakses recyclerview pada layout
        rv = findViewById(R.id.recview);
        //membuat araylist baru
        datalist = new ArrayList<>();
        //membuat database baru
        dtbase = new Database(this);
        //memanggil method readdata
        dtbase.readdata(datalist);

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new Adapter(this,datalist, color);
        //menghindari perubahan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        rv.setHasFixedSize(true);
        //menampilkan layoutnya linier
        rv.setLayoutManager(new LinearLayoutManager(this));
        //inisiasi adapter untuk recycler view
        rv.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        hapusgeser();
    }

    //membuat method untuk menghapus item pada to do list
    public void hapusgeser(){
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                //apabila item di swipe ke arah kiri
                if(direction==ItemTouchHelper.LEFT){
                    //remove item yang dipilih dengan to do sebagai pimary key
                    if(dtbase.removedata(current.getTodo())){
                        //menghapus data
                        adapter.deleteData(position);
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }
    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang dipilih
        int id = item.getItemId();
        //if else jika settings dipilih
        if (id==R.id.action_settings){
            //membuat intent baru dari list to do ke settings
            Intent intent = new Intent(ToDoListActivity.this, SettingActivity.class);
            //memulai intent
            startActivity(intent);
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }

    //method tombol add diklik
    public void add(View view) {
        //pindah acttivity ke add to do
        Intent intent = new Intent(ToDoListActivity.this, AddToDoActivity.class);
        //memulai intent
        startActivity(intent);
    }
}