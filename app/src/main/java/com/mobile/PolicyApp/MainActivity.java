package com.mobile.PolicyApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.PolicyApp.Parser.PublicDataDetail;
import com.mobile.PolicyApp.Parser.PublicDataList;
import com.mobile.PolicyApp.Parser.PublicDataListParser;
import com.mobile.PolicyApp.Parser.WantedList;
import com.mobile.PolicyApp.R;

import org.xml.sax.Parser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PublicDataListParser parser = new PublicDataListParser();
    ArrayList<PublicDataList> listPublicData;

    // Scroll
    final ArrayList<String> scrollItemList = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    public void onClick_serch(View view)
    {
        Toast.makeText(getApplicationContext(), "버튼 클릭!!", Toast.LENGTH_SHORT).show();
        SearchData();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 리스트뷰 초기화
        InitListView();

    }

    void SearchData()
    {
        new Thread(){
            public  void run(){
                try {

                    EditText input1 = findViewById(R.id.Input1);
                    EditText input2 = findViewById(R.id.Input2);
                    EditText input3 = findViewById(R.id.Input3);
                    EditText input4 = findViewById(R.id.Input4);

                    // 검색에 필요한 입력 데이터
                    WantedList wantedList = new WantedList();
                    wantedList.searchWrd = input1.getText().toString();        // 키워드
                    wantedList.lifeArray = input2.getText().toString();        // 생애주기
                    wantedList.trgterIndvdlArray=input3.getText().toString();  // 가구유형
                    wantedList.desireArray=input4.getText().toString();         // 문화및여가

                    // URL 생성
                    String strURL = parser.CreatePublicDataListURL(wantedList);


                    URL url = new URL(strURL);
                    // 연결
                    parser.HttpURLConnection(url);
                    // 파서
                    listPublicData = parser.XMLParser(url);
                    // 데이터 출력
                    ShowData();

                }
                catch (Exception e){

                }

            }
        }.start();
    }

    // 리스트뷰초기화
    void InitListView() {
        ListView list = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scrollItemList);
        list.setAdapter(adapter);
    }

    // 리스트 뷰에 데이터 출력
    void ShowData()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scrollItemList.clear();

                for(int i = 0; i <listPublicData.size(); i++)
                {
                    StringBuilder info = new StringBuilder();
                    info.append(listPublicData.get(i).servNm + "\n");
                    info.append(listPublicData.get(i).jurMnofNm + "\n");
                    info.append(listPublicData.get(i).trgterIndvdlArray + "\n");
                    info.append(listPublicData.get(i).servDgst + "\n");
                    info.append(listPublicData.get(i).servDtlLink + "\n");
                    info.append(listPublicData.get(i).servID + "\n");

                    scrollItemList.add((i+1) + " : " + info.toString());
                }
                adapter.notifyDataSetChanged(); //스크롤갱신
            }
        });
    }
}