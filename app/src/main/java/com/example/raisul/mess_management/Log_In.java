package com.example.raisul.mess_management;

import android.app.ProgressDialog;
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

    private Button login,register_as_mess_link,register_as_member_link;
    private EditText login_email,login_password;

    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        login = (Button)findViewById(R.id.log_in);
        register_as_mess_link = (Button)findViewById(R.id.register_as_mess);
        register_as_member_link = (Button)findViewById(R.id.register_as_mess_member);
        login_email=(EditText)findViewById(R.id.log_in_email);
        login_password=(EditText)findViewById(R.id.log_in_password);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowingUserToLogin();
            }
        });

        register_as_mess_link.setOnClickListener(new View.OnClickListener() {
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
             loadingBar.setTitle("Loging In");
             loadingBar.setMessage("Phease Wait.......");
             loadingBar.show();
             loadingBar.setCanceledOnTouchOutside(true);

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())

                            {
                                SendUserToMainActivity();
                                Toast.makeText(Log_In.this, "Logged In Successfully ", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                            else
                                {
                                    String message =task.getException().getMessage();
                                    Toast.makeText(Log_In.this, "Error Occured"+message, Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();

                            }

                        }
                    });
        }
    }

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(Log_In.this,MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegisterActivity() {

        Intent registerIntent = new Intent(Log_In.this,Register.class);
        startActivity(registerIntent);
    }
}
