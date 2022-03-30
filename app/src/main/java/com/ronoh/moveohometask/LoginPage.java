package com.ronoh.moveohometask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    private TextView login_LBL_title,login_LBL_UserName,login_LBL_Password;
    private EditText login_ETX_UserName ,login_ETX_Password;
    private Button login_BTN_SignUp,login_BTN_login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        findView();
        initView();

    }

    private void findView(){
        login_LBL_title = findViewById(R.id.login_LBL_title);
        login_LBL_UserName = findViewById(R.id.login_LBL_UserName);
        login_LBL_Password = findViewById(R.id.login_LBL_Password);

        login_ETX_UserName = findViewById(R.id.login_ETX_UserName);
        login_ETX_Password = findViewById(R.id.login_ETX_Password);

        login_BTN_SignUp = findViewById(R.id.login_BTN_SignUp);
        login_BTN_login = findViewById(R.id.login_BTN_login);
    }

    private void initView(){
        login_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // signIn();
                test();
            }
        });
        login_BTN_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private  void signIn(){
        String email = login_ETX_UserName.getText().toString().trim();
        String password = login_ETX_Password.getText().toString().trim();

        if (email.isEmpty()) {
            login_ETX_UserName.setError("Email is required");
            login_ETX_UserName.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_ETX_UserName.setError("please provide a valid Email!");
            login_ETX_UserName.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            login_ETX_Password.setError("password is required!");
            login_ETX_Password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            login_ETX_Password.setError("Min password is 6 lathers ");
            login_ETX_Password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    moveToList();
                }
                else{
                    Toast.makeText(LoginPage.this,"Check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

   private void signUp(){
       Intent myIntent = new Intent(this,SignUpPage.class);
       this.startActivity(myIntent);
   }

    private void test(){
        Intent myIntent = new Intent(this,NoteList.class);
        this.startActivity(myIntent);
    }

   private  void moveToList(){
       Intent myIntent = new Intent(this,NoteList.class);
       this.startActivity(myIntent);
       finish();
   }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
        if(mFirebaseUser != null){
        }
        else {
            startActivity(new Intent(this,LoginPage.class));
            finish();
        }
    }
}