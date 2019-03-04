package com.example.raisul.mess_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText register_email, register_password, register_conform_password;
    private Button signup;
    private FirebaseAuth rAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_email = (EditText) findViewById(R.id.register_email);
        register_password = (EditText) findViewById(R.id.register_password);
        register_conform_password = (EditText) findViewById(R.id.register_conform_password);
        signup = (Button) findViewById(R.id.signup_btn);
        rAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount() {

        String email = register_email.getText().toString();
        String password = register_password.getText().toString();
        String conform_password = register_conform_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Write Your Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Write Your Password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conform_password)) {
            Toast.makeText(this, "Write Your Comform Password", Toast.LENGTH_SHORT).show();
        }
       else if (!password.equals(conform_password)) {
            Toast.makeText(this, "Password can not Match to Conform Password", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Creating Account");
            loadingBar.setMessage("Phease Wait.......");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);


            rAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                SendUserToSetupActivity();
                                Toast.makeText(Register.this, "Account Is Created Succesfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                String message =task.getException().toString();
                                Toast.makeText(Register.this, "Error Occured" +message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void SendUserToSetupActivity()
    {

        Intent setupintent = new Intent(Register.this,SetupActivity.class);
        setupintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupintent);
        finish();

    }
}
