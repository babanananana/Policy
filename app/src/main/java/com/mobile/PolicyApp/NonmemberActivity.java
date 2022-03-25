package com.mobile.PolicyApp;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class NonmemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonmember);

        Spinner ss_age = findViewById(R.id.s_age);
        Spinner ss_gender = (Spinner)findViewById(R.id.s_gender);
        Spinner ss_area = (Spinner)findViewById(R.id.s_area);
        Spinner ss_job = (Spinner)findViewById(R.id.s_job);
        Spinner ss_range = (Spinner)findViewById(R.id.s_range);
        Spinner ss_etc = (Spinner)findViewById(R.id.s_etc);
        Spinner ss_npopular = (Spinner)findViewById(R.id.s_npopular);



        String[] a_age = getResources().getStringArray(R.array.age_array);
        String[] a_gender = getResources().getStringArray(R.array.gender_array);
        String[] a_area = getResources().getStringArray(R.array.area_array);
        String[] a_job = getResources().getStringArray(R.array.job_array);
        String[] a_range = getResources().getStringArray(R.array.range_array);
        String[] a_etc = getResources().getStringArray(R.array.etc_array);
        String[] a_npopular = getResources().getStringArray(R.array.popular_array);


        ArrayAdapter<String> adapter0;
        adapter0 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_age);
        ss_age.setAdapter(adapter0);

        ArrayAdapter<String> adapter1;
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_gender);
        ss_gender.setAdapter(adapter1);

        ArrayAdapter<String> adapter2;
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_area);
        ss_area.setAdapter(adapter2);

        ArrayAdapter<String> adapter3;
        adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_job);
        ss_job.setAdapter(adapter3);

        ArrayAdapter<String> adapter4;
        adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_range);
        ss_range.setAdapter(adapter4);

        ArrayAdapter<String> adapter5;
        adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_etc);
        ss_etc.setAdapter(adapter5);

        ArrayAdapter<String> adapter6;
        adapter6 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a_npopular);
        ss_npopular.setAdapter(adapter6);
    }

}
