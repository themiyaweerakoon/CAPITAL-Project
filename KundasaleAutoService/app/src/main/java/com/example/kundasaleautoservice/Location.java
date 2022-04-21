package com.example.kundasaleautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.MapView;

public class Location extends AppCompatActivity {

    private Button b;  //create button
    private MapView m; //create Map

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        b = (Button)findViewById(R.id.buttonback); //redirect the activity to another activity by pressing the button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Location.this,Service.class);  // path
                startActivity(i);
            }
        });

        m = (MapView)findViewById(R.id.mapView);  //show the map
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Kundasale+Auto+Service,+A26,+Kundasala+20168/@7.2811619,80.6947473,21z/data=!4m13!1m7!3m6!1s0x3ae366eb94fc80fd:0xca7aabc25ba5b77f!2sKundasale+Auto+Service,+A26,+Kundasala+20168!3b1!8m2!3d7.2811657!4d80.6948875!3m4!1s0x3ae366eb94fc80fd:0xca7aabc25ba5b77f!8m2!3d7.2811657!4d80.6948875"));
        startActivity(i);
    }
}
