package com.aldinata.moritranslator.Login;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aldinata.moritranslator.OnDialogCloseListner;
import com.aldinata.moritranslator.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends BottomSheetDialogFragment {

    public static final String TAG = "Register";

    public static Register newInstance(){
        return new Register();
    }

    private DatabaseReference firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register , container , false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText username = view.findViewById(R.id.etUser);
        EditText password = view.findViewById(R.id.etPassword);
        EditText repeatpass = view.findViewById(R.id.etPasswordRepeat);
        Button daftar = view.findViewById(R.id.btnRegister);

        firebaseDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mori-translator-default-rtdb.firebaseio.com/");

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inUsername = username.getText().toString();
                String inPassword = password.getText().toString();

                String rPassword = repeatpass.getText().toString();

                if (!(rPassword.equals(inPassword))){
                    repeatpass.setError("Password Tidak Sama");
                }else{
                    firebaseDatabase.child("users").child(inUsername).child("username").setValue(inUsername);
                    firebaseDatabase.child("users").child(inUsername).child("password").setValue(inPassword);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListner){
            ((OnDialogCloseListner)activity).onDialogClose(dialog);
        }
    }

}
