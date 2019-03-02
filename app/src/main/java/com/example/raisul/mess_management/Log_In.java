package com.example.raisul.mess_management;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_In extends AppCompatActivity {

    private Button login;
    private EditText login_email,login_password;
    private TextView registerlink;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        login = (Button)findViewById(R.id.log_in);
        login_email=(EditText)findViewById(R.id.log_in_email);
        login_password=(EditText)findViewById(R.id.log_in_password);
        registerlink=(TextView)findViewById(R.id.register_link);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowingUserToLogin();
            }
        });

        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToRegisterActivity();
            }
        });

    }

    private void AllowingUserToLogin()
    {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }

        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())

                            {
                                SendUserToMainActivity();
                                Toast.makeText(Log_In.this, "Logged In Successfully ", Toast.LENGTH_SHORT).show();
                            }

                            else
                                {
                                    String message =task.getException().getMessage();
                                    Toast.makeText(Log_In.this, "Error Occured"+message, Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }
    }

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(Log_In.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
    }

    private void SendUserToRegisterActivity() {

        Intent registerIntent = new Intent(Log_In.this,Register.class);
        startActivity(registerIntent);
    }
}
