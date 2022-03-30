package com.ronoh.moveohometask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {

    private TextView SignUp_LBL_title,SignUp_LBL_UserName,SignUp_LBL_Password,SignUp_LBL_PasswordCon;
    private EditText SignUp_ETX_PasswordCon,SignUp_ETX_Password,SignUp_ETX_UserName;
    private Button  SignUp_BTN_SignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        findView();
        initView();
        mAuth = FirebaseAuth.getInstance();
    }

    private void findView(){
        SignUp_LBL_title = findViewById(R.id.SignUp_LBL_title);
        SignUp_LBL_UserName = findViewById(R.id.SignUp_LBL_UserName);
        SignUp_LBL_Password = findViewById(R.id.SignUp_LBL_Password);
        SignUp_LBL_PasswordCon = findViewById(R.id.SignUp_LBL_PasswordCon);
        SignUp_ETX_PasswordCon = findViewById(R.id.SignUp_ETX_PasswordCon);
        SignUp_ETX_Password = findViewById(R.id.SignUp_ETX_Password);
        SignUp_ETX_UserName = findViewById(R.id.SignUp_ETX_UserName);
        SignUp_BTN_SignUp = findViewById(R.id.SignUp_BTN_SignUp);

    }
    private void initView(){
        SignUp_BTN_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void  registerUser(){
        String email =SignUp_ETX_UserName.getText().toString().trim();
        String password = SignUp_ETX_Password.getText().toString().trim();

        if (email.isEmpty()) {
            SignUp_ETX_UserName.setError("Email is required");
            SignUp_ETX_UserName.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUp_ETX_UserName.setError("please provide a valid Email!");
            SignUp_ETX_UserName.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            SignUp_ETX_Password.setError("password is required!");
            SignUp_ETX_Password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            SignUp_ETX_Password.setError("Min password is 6 lathers ");
            SignUp_ETX_Password.requestFocus();
            return;
         }
        if(!password.equals(SignUp_ETX_PasswordCon.getText().toString())){
            Toast.makeText(SignUpPage.this,"Password not Match", Toast.LENGTH_LONG).show();
            return;
        }
        newUser(email,password);
    }

    private  void newUser(String email,String password){

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(SignUpPage.this,"Registering user successful!", Toast.LENGTH_LONG).show();
                    finish();
            }
                else {

                    Toast.makeText(SignUpPage.this,"Registering user fail!", Toast.LENGTH_LONG).show();
                }
        }

     });
    }

}