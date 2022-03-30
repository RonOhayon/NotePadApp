package com.ronoh.moveohometask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;
import java.util.List;

public class NotePage extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText np_LBL_context,np_LBL_title;
    private Calendar c;
    private String todayDate,location;
    private Note note;
    private double latitude,longitude;
    private LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        findView();
        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("new note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // get current date
        c = Calendar.getInstance();
        todayDate =c.get(Calendar.DAY_OF_MONTH) +"/"+ c.get(Calendar.MONTH+1) +"/"+ c.get(Calendar.YEAR);

    }
    private void findView(){
        toolbar = findViewById(R.id.toolbar);
        np_LBL_context =findViewById(R.id.np_LBL_context);
        np_LBL_title = findViewById(R.id.np_LBL_title);
    }
    private void initView(){
        np_LBL_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() !=0){
                    getSupportActionBar().setTitle(s);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save){
            note = setNote();
            NoteDatebase db = new NoteDatebase(this);
            db.addNote(note);
            Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show();
            goToMain();
        }
        if(item.getItemId() == R.id.delete){
            Toast.makeText(this,"Note Delete",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private Note setNote(){
        location =setCoordinate();
        Note temp = new Note(np_LBL_title.getText().toString(),np_LBL_context.getText().toString(),todayDate,location);
        return  temp;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void goToMain(){
        Intent myIntent = new Intent(this,NoteList.class);
        this.startActivity(myIntent);
        finish();
    }
    
    private String setCoordinate() {
        String temp;
        Location location = getLastKnownLocation();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        temp =""+latitude+":"+longitude;
        return temp;

    }

    private Location getLastKnownLocation() {

        if (ActivityCompat.checkSelfPermission(NotePage.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            List<String> providers = mLocationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            return bestLocation;
        }
        else {
            ActivityCompat.requestPermissions(NotePage.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            getLastKnownLocation();
            //get location again after granting permission
        }
        return null;
    }

}