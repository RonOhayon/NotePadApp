package com.ronoh.moveohometask;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NoteDetails extends AppCompatActivity {
    private TextView body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the id from the adpter list
        Intent i =getIntent();
        Long id = i.getLongExtra("ID",0);

        //get the note from database
        NoteDatebase db = new NoteDatebase(this);
        Note note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        body=findViewById(R.id.Body);
        body.setText(note.getContent());



        FloatingActionButton fab = findViewById(R.id.mapBTN);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToMap();
            }
        });
    }
    private void moveToMap(){}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save){

        }
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this,"Note Delete",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}