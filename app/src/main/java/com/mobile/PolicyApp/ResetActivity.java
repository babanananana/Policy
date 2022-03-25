package com.mobile.PolicyApp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class ResetActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        Spinner ss_rpopular = (Spinner) findViewById(R.id.s_rpopular);
        String[] a_rpopular = getResources().getStringArray(R.array.popular_array);

        ArrayAdapter<String> adapter8;
        adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, a_rpopular);
        ss_rpopular.setAdapter(adapter8);
    }
}