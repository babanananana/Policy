package com.mobile.PolicyApp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Chatbot_Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot_help);

        Toast.makeText(this, "1대1문의", Toast.LENGTH_SHORT).show();

    }
}