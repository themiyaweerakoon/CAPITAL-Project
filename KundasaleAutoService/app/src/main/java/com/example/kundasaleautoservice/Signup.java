package com.example.kundasaleautoservice;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText firstname,lastname,mobile,email,password,confirmpassword;      //Data Insertion
    Button buttonsignup;
    DatabaseReference reff;
    Customer customer;              //Mention about the Customer table
    long maxid=0;           //set auto increment the number of customers that sign up

    private Button cancelsignup; //create button


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);           //Reference for the layout
        firstname=(EditText)findViewById(R.id.firstname) ;
        lastname=(EditText)findViewById(R.id.lastname) ;
        mobile=(EditText)findViewById(R.id.mobile) ;
        email=(EditText)findViewById(R.id.email) ;
        password=(EditText)findViewById(R.id.password) ;
        confirmpassword=(EditText)findViewById(R.id.confirmpassword) ;
        buttonsignup=(Button) findViewById(R.id.buttonsignup) ;
        customer=new Customer();
        reff= FirebaseDatabase.getInstance().getReference().child("Customer");
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
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        //set the store function when click the sign up button
                Long mob=Long.parseLong(mobile.getText().toString().trim());

                buttonsignup=(Button) findViewById(R.id.buttonsignup) ;

                email=(EditText)findViewById(R.id.email) ;
                mobile=(EditText)findViewById(R.id.mobile) ;
                password=(EditText)findViewById(R.id.password) ;
                confirmpassword=(EditText)findViewById(R.id.confirmpassword) ;


                if (isValidMobile(mobile.getText().toString())){

                    if(isValidEmailId(email.getText().toString().trim())) {

                        if (isValidPassword(password.getText().toString())){

                            if (isValidCpassword(confirmpassword.getText().toString())){

                                if(!String.valueOf(confirmpassword).equals(String.valueOf(password))) {

                                    customer.setFname(firstname.getText().toString().trim());
                                    customer.setLname(lastname.getText().toString().trim());
                                    customer.setMobile(mob);
                                    customer.setEmail(email.getText().toString().trim());
                                    customer.setPassword(password.getText().toString().trim());
                                    customer.setCpassword(confirmpassword.getText().toString().trim());
                                    reff.child(String.valueOf(maxid+1)).setValue(customer);
                                    Toast.makeText(Signup.this, "Data Inserted Successfully\n You can LOGIN now...", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "Password Not matching", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Re-enter the Password", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid Mobile Number", Toast.LENGTH_SHORT).show();


            }
        });

        cancelsignup = (Button)findViewById(R.id.cancelsignup);  //redirect the activity to another activity by pressing the button
        cancelsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signup.this,MainActivity.class); // path
                startActivity(i);
            }
        });

    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "[a-zA-Z0-9\\!\\@\\#\\$]{7,24}";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean isValidCpassword(final String cpassword) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "[a-zA-Z0-9\\!\\@\\#\\$]{7,24}";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(cpassword);

        return matcher.matches();

    }

        private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[0-9]", phone)) {
            return phone.length() == 10;
        }
        return false;
    }

}