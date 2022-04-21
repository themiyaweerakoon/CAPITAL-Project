package com.example.kundasaleautoservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Accident extends AppCompatActivity {

    EditText address,regnum,vehicletype,makemodel,colour,chassis,year,insurance;      //Data Insertion
    Button buttonsubmit;
    DatabaseReference reff;
    Vehicle vehicle;              //Mention about the Customer table
    long maxid=0;           //set auto increment the number of customers that sign up

    private Button cancel; //create button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);

        address=(EditText)findViewById(R.id.address) ;
        regnum=(EditText)findViewById(R.id.regnum) ;
        vehicletype=(EditText)findViewById(R.id.vehicletype) ;
        makemodel=(EditText)findViewById(R.id.makemodel) ;
        colour=(EditText)findViewById(R.id.colour) ;
        chassis=(EditText)findViewById(R.id.chassis) ;
        year=(EditText)findViewById(R.id.year) ;
        insurance=(EditText)findViewById(R.id.insurance) ;
        buttonsubmit=(Button) findViewById(R.id.buttonsubmit) ;
        vehicle=new Vehicle();
        reff= FirebaseDatabase.getInstance().getReference().child("Customer").child("Vehicle");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());        //set snapshot to count the number of customers
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //set the store function when click the sign up button

                final String Address = address.getText().toString().trim();
                final String Regnum = regnum.getText().toString().trim();
                final String Vehicletype = vehicletype.getText().toString().trim();
                final String Makemodel = makemodel.getText().toString().trim();
                final String Colour = colour.getText().toString().trim();
                final String Chassis = chassis.getText().toString().trim();
                final String Year = year.getText().toString().trim();
                final String Insurance = insurance.getText().toString().trim();


                if(TextUtils.isEmpty(Address)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Address", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Regnum)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Register Number", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Vehicletype)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Vehicle Type", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Makemodel)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Make and Model", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Colour)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Colour", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Chassis)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Chassis Number", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Year)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Year", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                if(TextUtils.isEmpty(Insurance)){
                    //email is empty
                    Toast.makeText(getApplicationContext(), "Enter the Insurance Company", Toast.LENGTH_SHORT).show();
                    //stopping execution further
                    return;
                }

                vehicle.setAddress(address.getText().toString().trim());
                vehicle.setRegnum(regnum.getText().toString().trim());
                vehicle.setVehicletype(vehicletype.getText().toString().trim());
                vehicle.setMakemodel(makemodel.getText().toString().trim());
                vehicle.setColour(colour.getText().toString().trim());
                vehicle.setChassis(chassis.getText().toString().trim());
                vehicle.setYear(year.getText().toString().trim());
                vehicle.setInsurance(insurance.getText().toString().trim());
                reff.child(String.valueOf(maxid+1)).setValue(vehicle);
                Toast.makeText(Accident.this, "Data Submission Successfully", Toast.LENGTH_SHORT).show();

            }

        });

        cancel = (Button)findViewById(R.id.cancelaccident);  //redirect the activity to another activity by pressing the button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accident.this,Service.class); // path
                startActivity(i);
            }
        });

    }
}
