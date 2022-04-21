package com.example.kundasaleautoservice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Service extends AppCompatActivity {

    private Button b; //create button
    private Button c;
    private Button d;
    private Button e;
    private Button f;
    private Button g;
    private Button h;
    private Button j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        b = (Button)findViewById(R.id.accident);  //redirect the activity to another activity by pressing the button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Service.this,Accident.class); // path
                startActivity(i);
            }
        });

        c = (Button)findViewById(R.id.call);  //redirect the activity to call service by pressing the button
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0812420130"));
                startActivity(i);
            }
        });

        d = (Button)findViewById(R.id.about);  //redirect the activity to another activity by pressing the button
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Service.this,About.class); // path
                startActivity(i);
            }
        });

        e = (Button) findViewById(R.id.page);  //show fb Page
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/kundasaleautoservice/"));
                startActivity(i);
            }
        });

        f = (Button) findViewById(R.id.chat);  //show FB Messenger
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/kundasaleautoservice"));
                startActivity(i);
            }
        });

        g = (Button) findViewById(R.id.mail);  //show Email
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                String[] recipients={"kaservice@sltnet.lk"};
                i.putExtra(Intent.EXTRA_EMAIL, recipients);
                i.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                i.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                i.setType("text/html");
                i.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(i, "Send mail"));

            }
        });

        h = (Button)findViewById(R.id.btnback);  //redirect the activity to another activity by pressing the button
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Service.this,Login.class); // path
                startActivity(i);
            }
        });


        j = (Button)findViewById(R.id.process);  //redirect the activity to another activity by pressing the button
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Service.this,Status.class); // path
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_settings:
                Toast.makeText(this, "Settings",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Settings.class));
                return true;

            case R.id.nav_print:
                Toast.makeText(this, "View Your Vehicle Details",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Print.class));
                return true;

            case R.id.nav_location:
                Toast.makeText(this, "Location\n Open in Google Maps",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Location.class));
                return true;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Login.class));
                finish();
                return true;

            case R.id.nav_exit:
                Toast.makeText(this, "Exit",Toast.LENGTH_SHORT).show();
                finish();
                moveTaskToBack(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
