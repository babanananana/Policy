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
import com.mobile.PolicyApp.Parser.PublicDataParser;
import com.mobile.PolicyApp.Parser.WantedDetail;
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

/*API흐름
 * 리스트뷰초기화 -> URL생성 -> URL연결 -> 파서 ->데이터출력
 *
 */


public class MainActivity extends AppCompatActivity {

    //PublicDataListParser parser = new PublicDataListParser();
    PublicDataParser parser = new PublicDataParser();

    ArrayList<PublicDataList>   publicDataArray;
    ArrayList<PublicDataDetail> publicDetailArray;

    // Scroll
    final ArrayList<String> scrollItemList = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    String serachServID;

    String lifeArrayText;




    public void onClick_serch_List(View view)
    {
        Toast.makeText(getApplicationContext(), "버튼 클릭!!", Toast.LENGTH_SHORT).show();
        SearchDataList();
    }
    public void  onClick_serch_Detail(View view)
    {
        Toast.makeText(getApplicationContext(), "버튼 클릭!!", Toast.LENGTH_SHORT).show();
        SearchDateDetail();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 리스트뷰 초기화
        InitListView();

    }

    void SearchDataList()
    {
        new Thread(){
            public  void run(){
                try {

                    EditText input_searchWrd = findViewById(R.id.input_searchWrd);
                    EditText input_lifeArray = findViewById(R.id.input_lifeArray);
                    EditText input_trgterIndvdlArray = findViewById(R.id.input_trgterIndvdlArray);
                    EditText input_desireArray = findViewById(R.id.input_desireArray);




                    // 검색에 필요한 입력 데이터
                    WantedList wantedList = new WantedList();
                    wantedList.searchWrd = input_searchWrd.getText().toString();        // 키워드
                    //wantedList.lifeArray = input_lifeArray.getText().toString();        // 생애주기
                    wantedList.trgterIndvdlArray=input_trgterIndvdlArray.getText().toString();  // 가구유형
                    wantedList.desireArray=input_desireArray.getText().toString();         // 문화및여가

                    lifeArrayText=input_lifeArray.getText().toString();





                    // [목록 조회]
                    if(parser.PulbicDataList_HttpURLConnection(wantedList)) {
                        publicDataArray = parser.XMLParserDataList();
                        ShowPublicDataList();
                    }
                }
                catch (Exception e){

                }
            }
        }.start();
    }


    void SearchDateDetail(){
        new Thread(){
            public  void run(){
                try {
                    // [상세보기 ]
                    WantedDetail wantedDetail=new WantedDetail();
                    wantedDetail.servID = serachServID;
                    if(parser.PulbicDataDetail_HttpURLConnection(wantedDetail)){
                        publicDetailArray = parser.XMLParserDataDetail();

                        ShowPublicDetailData();
                    }

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                serachServID = publicDataArray.get(position).servID;
                Toast.makeText(getApplicationContext(), "servID : " + serachServID  + " / pos : " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }



    // 리스트 뷰에 목록 조회 데이터 출력
    void ShowPublicDataList()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scrollItemList.clear();

                for(int i = 0; i <publicDataArray.size(); i++)
                {
                    StringBuilder info = new StringBuilder();
                    info.append(publicDataArray.get(i).servNm + "\n");
                    info.append(publicDataArray.get(i).jurMnofNm + "\n");
                    info.append(publicDataArray.get(i).lifeArray + "\n");
                    info.append(publicDataArray.get(i).trgterIndvdlArray + "\n");
                    info.append(publicDataArray.get(i).servDgst + "\n");
                    info.append(publicDataArray.get(i).servDtlLink + "\n");
                    info.append(publicDataArray.get(i).servID + "\n");

//                    if(publicDataArray.get(i).lifeArray.contains(lifeArrayText))
//                    {
//                        info.append(lifeArrayText + "\n");
//                    }



                    scrollItemList.add((i+1) + " : " + info.toString());
                }
                adapter.notifyDataSetChanged(); //스크롤갱신
            }
        });
    }
    // 리스트 뷰에 상세 보기 데이터 출력
    void ShowPublicDetailData()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scrollItemList.clear();

                for(int i = 0; i <publicDetailArray.size(); i++)
                {
                    StringBuilder info = new StringBuilder();
                    if(publicDetailArray.get(i).servNm != null)
                        info.append(publicDetailArray.get(i).servNm + "\n");

                    if(publicDetailArray.get(i).jurMnofNm != null)
                        info.append(publicDetailArray.get(i).jurMnofNm + "\n");

                    if(publicDetailArray.get(i).tgtrDtlCn != null)
                        info.append(publicDetailArray.get(i).tgtrDtlCn + "\n");

                    if(publicDetailArray.get(i).slctCritCn != null)
                        info.append(publicDetailArray.get(i).slctCritCn + "\n");

                    if(publicDetailArray.get(i).alwServCn != null)
                        info.append(publicDetailArray.get(i).alwServCn + "\n");

                    if(publicDetailArray.get(i).trgterIndvdlArray != null)
                        info.append(publicDetailArray.get(i).trgterIndvdlArray + "\n");

                    if(publicDetailArray.get(i).lifeArray != null)
                        info.append(publicDetailArray.get(i).lifeArray + "\n");

                    scrollItemList.add(" : " + info.toString());
                }
                adapter.notifyDataSetChanged(); //스크롤갱신
            }
        });
    }
}