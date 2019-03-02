package com.example.raisul.mess_management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    private EditText user_name,full_name,mess_name;
    private Button save_from_setup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        user_name = (EditText)findViewById(R.id.user_name);
        full_name = (EditText)findViewById(R.id.full_name);
        mess_name = (EditText)findViewById(R.id.mess_name);
        save_from_setup = (Button)findViewById(R.id.save_from_setup);
    }
}
