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

    ArrayList<PublicDataList>   publicDataArray;   //목록조회그릇
    ArrayList<PublicDataDetail> publicDetailArray; //상세보기그릇

    // Scroll
    final ArrayList<String> scrollItemList = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    String serachServID; //서비스아이디값



    public void onClick_serch_List(View view)  //목록조회버튼
    {
        Toast.makeText(getApplicationContext(), "버튼 클릭!!", Toast.LENGTH_SHORT).show();
        SearchDataList();
    }
    public void  onClick_serch_Detail(View view)  //상세조회버튼
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

    void SearchDataList() //목록조회검색및필터링
    {
        new Thread(){
            public  void run(){
                try {
                    // 검색에 필요한 입력 데이터
                    WantedList wantedList = new WantedList();

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
                    // !? 상세정보클릭시 서비스아이디를 받고 링크만들기
                    WantedDetail wantedDetail = new WantedDetail();
                    wantedDetail.wantedAuthNo = serachServID;
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)//스크롤뷰해당하는아이템에 인덱스 번호가 포지션변수에 들어옴
            {
                serachServID = publicDataArray.get(position).wantedAuthNo;   //서비스아이디에 해당포지션에 해당하는 서비스아이디대입
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
                    info.append(publicDataArray.get(i).wantedAuthNo + "\n"); //구인인증번호
                    info.append(publicDataArray.get(i).company + "\n");  //회사명
                    info.append(publicDataArray.get(i).title + "\n");    //채용제목
                    info.append(publicDataArray.get(i).salTpNm + "\n");   //임금형태
                    info.append(publicDataArray.get(i).region + "\n");   //근무지역
                    info.append(publicDataArray.get(i).regDt + "\n");    //등록일자
                    info.append(publicDataArray.get(i).closeDt + "\n");//마감일자
                    info.append(publicDataArray.get(i).wantedInfoUrl + "\n");//워크넷 채용정보 URL


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
                scrollItemList.clear();   //아이템리스트초기화
                for(int i = 0; i <publicDetailArray.size(); i++)
                {
                    StringBuilder info = new StringBuilder();
                        info.append(publicDetailArray.get(i).jobsNm + "\n");//모집집종
                        info.append(publicDetailArray.get(i).wantedTitle + "\n");//구인제목
                        info.append(publicDetailArray.get(i).relJobsNm + "\n");//관련직종
                        info.append(publicDetailArray.get(i).jobCont + "\n");//직무내용
                        info.append(publicDetailArray.get(i).salTpNm + "\n");//임금조건
                    info.append(publicDetailArray.get(i).pfCond + "\n");//우대조건
                    info.append(publicDetailArray.get(i).selMthd + "\n");//전형방법

                        info.append(publicDetailArray.get(i).workRegion + "\n");//근무예정지
                        info.append(publicDetailArray.get(i).workdayWorkhrCont + "\n");//근무시간/형태

                    scrollItemList.add(" : " + info.toString());
                }
                adapter.notifyDataSetChanged(); //스크롤갱신
            }
        });
    }
}