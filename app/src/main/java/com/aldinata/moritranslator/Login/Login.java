package com.aldinata.moritranslator.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aldinata.moritranslator.MainActivity;
import com.aldinata.moritranslator.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    //shared pref
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "myPref";
    private static final String KEY_NAME = "name";

    private EditText username, password;
    private Button login;
    private ImageView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etUser);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        Animation animation_naik = AnimationUtils.loadAnimation(this, R.anim.transisi_naik);
        btnRegister.setAnimation(animation_naik);

        //daftar
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register.newInstance().show(getSupportFragmentManager(), Register.TAG);
            }
        });

        //sharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        //Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = username.getText().toString();
                String getPassword = password.getText().toString();

                if (getUsername.isEmpty() && getPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Atau Password Salah",Toast.LENGTH_SHORT).show();
                } else if (getUsername.equals("admin") && getPassword.equals("admin")){
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(KEY_NAME,getUsername);
                        editor.putBoolean("hasLoggedIn",true);
                        editor.apply();

                        Intent masuk = new Intent(Login.this, MainActivity.class);
                        startActivity(masuk);

                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.child(getUsername).exists()){
                                if (snapshot.child(getUsername).child("password").getValue(String.class).equals(getPassword)){

                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString(KEY_NAME,getUsername);
                                    editor.putBoolean("hasLoggedIn",true);
                                    editor.apply();

                                    Intent masuk = new Intent(Login.this, MainActivity.class);
                                    startActivity(masuk);

                                    Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Username Atau Password Salah",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Username Atau Password Salah",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }



            }
        });

    }
}