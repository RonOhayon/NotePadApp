package com.ronoh.moveohometask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class NoteList extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        NoteDatebase db = new NoteDatebase(this);
        notes = db.getNotes();
        if(notes.isEmpty()){
        moveToEmptyView();
        }
        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new Adapter(this,notes);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);

        return true;
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.listOfNotes);
    }

    private  void  moveToEmptyView(){
        Intent intent = new Intent(this ,EmptyList.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Intent intent = new Intent(this ,NotePage.class);
            startActivity(intent);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }
}