package com.example.kundasaleautoservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity implements View.OnClickListener{

        private Button cancellogin;
        private Button buttonlogin;
        private EditText editUsername;
        private EditText editPassword;

        private ProgressDialog progressDialog;

        private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            setUpUIViews();

            firebaseAuth=FirebaseAuth.getInstance();            //get the instances from firebase


            cancellogin = (Button) findViewById(R.id.cancellogin);                                            //redirect the activity to another activity by pressing the button
            cancellogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Login.this, MainActivity.class); // path
                    startActivity(i);
                }
            });

            progressDialog = new ProgressDialog(this);

            buttonlogin = (Button)findViewById(R.id.buttonlogin);
            editUsername = (EditText)findViewById(R.id.editUsername);
            editPassword= (EditText)findViewById(R.id.editPassword);

            buttonlogin.setOnClickListener(this);
        }

        private void registerUser(){
            final String email = editUsername.getText().toString().trim();
            final String password = editPassword.getText().toString().trim();


            if(TextUtils.isEmpty(email)){
                                                                                                            //email is empty
                Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
                                                                                                            //stopping execution further
                return;
            }
            if(TextUtils.isEmpty(password)){
                                                                                                            //password is empty
                Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
                                                                                                            //stopping execution further
                return;
            }


                                                                                                             //if validations are ok
                                                                                                                //show progressbar
            progressDialog.setMessage("Registering User");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                                                                                                                             //user is successfully registered. start profile activity here
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Toast.makeText(Login.this, "Authentication success. " + user.getUid(), Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                                finish();
                                startActivity(new Intent(getApplicationContext(),Customer.class));
                                Intent i = new Intent(Login.this, Service.class); // path
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(Login.this, "Could not register. please try again", Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                            }

                        }
                    });
        }

        @Override
        public void onClick(View view) {
            if (view==buttonlogin){
                registerUser();
            }

        }

    private void setUpUIViews(){

        buttonlogin = (Button)findViewById(R.id.buttonlogin);
        editUsername = (EditText)findViewById(R.id.editUsername);
        editPassword= (EditText)findViewById(R.id.editPassword);
    }
    }