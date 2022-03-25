package com.mobile.PolicyApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Chatbot_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot_main);

       // Toast.makeText(getApplicationContext(), "챗봇 화면 !!", Toast.LENGTH_SHORT).show();

    }

    public void onClick_Faq(View view)
    {
        //Toast.makeText(getApplicationContext(), "자주하는 질문!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Chatbot_Faq.class);
        startActivity(intent);
        //finish();
    }

    public void onClick_Inquiry(View view)
    {
       // Toast.makeText(getApplicationContext(),"1대1문의",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), com.mobile.PolicyApp.Chatbot_Inquiry.class);
        startActivity(intent);

    }

    public void onClick_Help(View view)
    {
       // Toast.makeText(getApplicationContext(),"도움말",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Chatbot_Help.class);
        startActivity(intent);
    }

}