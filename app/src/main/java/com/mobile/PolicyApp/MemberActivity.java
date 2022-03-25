package com.mobile.PolicyApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//import com.mobile.PolicyApp.R;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        /*
        Spinner ss_mpopular = (Spinner) findViewById(R.id.s_mpopular);
        String[] a_mpopular = getResources().getStringArray(R.array.popular_array);

        ArrayAdapter<String> adapter7;
        adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, a_mpopular);
        ss_mpopular.setAdapter(adapter7);

        Button resetbutton = (Button) findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.search.ResetActivity.class);
                startActivity(intent);
            }
        });*/
    }
}