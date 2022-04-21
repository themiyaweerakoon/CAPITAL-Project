package com.example.kundasaleautoservice;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Status extends AppCompatActivity {

    TextView statusB,statusP,statusM;
    DatabaseReference myreff;
    DatabaseReference myref;
    DatabaseReference myre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        statusB = findViewById(R.id.bstatus);
        statusP = findViewById(R.id.pstatus);
        statusM = findViewById(R.id.mstatus);

        myreff = FirebaseDatabase.getInstance().getReference("bodyShopRepaire");
        myreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String statusb = dataSnapshot.child("status").getValue().toString();

                statusB.setText(statusb);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myref = FirebaseDatabase.getInstance().getReference("PaintingRepaire");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String statusp = dataSnapshot.child("status").getValue().toString();

                statusP.setText(statusp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myre = FirebaseDatabase.getInstance().getReference("Mechanic Repaire");
        myre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String statusm = dataSnapshot.child("status").getValue().toString();

                statusM.setText(statusm);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
