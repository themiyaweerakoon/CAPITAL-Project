package com.example.kundasaleautoservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    private Button cancelsettings; //create button

    private EditText fname;
    private EditText lname;
    private EditText mobile;
    private DatabaseReference mDatabase;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        fname = (EditText)findViewById(R.id.sfname);
        lname = (EditText)findViewById(R.id.slname);
        mobile = (EditText)findViewById(R.id.smobile);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Customer").child("Vehicle");

        save= (Button)findViewById(R.id.savechanges);  //redirect the activity to another activity by pressing the button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredfname = fname.getText().toString();
                String enteredlname = lname.getText().toString();
                String enteredmobile = mobile.getText().toString();
                editData(enteredfname,enteredlname,enteredmobile);

                Toast.makeText(Settings.this,"Data Edited",Toast.LENGTH_LONG).show();

                if(TextUtils.isEmpty(enteredfname)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the First Name", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(enteredlname)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Last Name", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(enteredmobile)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Mobile Number", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }
            }
        });

        cancelsettings = (Button)findViewById(R.id.cancelsettings);  //redirect the activity to another activity by pressing the button
        cancelsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Settings.this,Service.class); // path
                startActivity(i);
            }
        });

    }
    private void editData(final String strFname, final String strLname, final String strMobile){
        Query editQuery = mDatabase.orderByChild("fname").equalTo(strFname);
        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot edtData: dataSnapshot.getChildren())
                    edtData.getRef().child("Vehicle").setValue(strLname);

                for(DataSnapshot edtData: dataSnapshot.getChildren())
                    edtData.getRef().child("Vehicle").setValue(strMobile);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Settings.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
}

