package com.mobile.PolicyApp.Parser;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

//상세 보기 URL 만들고 파싱해주는곳
public class PublicDataDetailParser {
    public ArrayList<PublicDataDetail> publicDataDetailArray = new ArrayList<PublicDataDetail>();

    // 상세 보기 URL 만들기
    public String CreatePublicDetailURL(@NonNull WantedDetail wantedDetail) throws UnsupportedEncodingException
    {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.work.go.kr/opi/opi/opia/wantedApi.do"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("authKey","UTF-8") +"="+ "WNL2XYNKFLGTOE5A06NKA2VR1HJ");
        urlBuilder.append("&" + URLEncoder.encode("callTp","UTF-8") + "=" + URLEncoder.encode(wantedDetail.callTp, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(wantedDetail.returnType, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("wantedAuthNo","UTF-8") + "=" + URLEncoder.encode(wantedDetail.wantedAuthNo, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("infoSvc","UTF-8") + "=" + URLEncoder.encode(wantedDetail.infoSvc, "UTF-8"));

        return urlBuilder.toString();
    }
    // XML 파서 [ 상세 보기 ]
    public  ArrayList<PublicDataDetail> XMLParser(URL url)
    {
        int eventType =0;
        String tag;
        PublicDataDetail data = new PublicDataDetail();

        publicDataDetailArray.clear();

        try {
            // XML 파서
            StringBuilder buffer = new StringBuilder();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            InputStream is =url.openStream();
            xpp.setInput( new InputStreamReader(is,"UTF-8") );

            xpp.next();


            while( eventType != XmlPullParser.END_DOCUMENT ) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작 \n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName(); // 태그 이름 얻어오기
                        if(tag.equals("wantedInfo")) {
                            data.SetEmpty();
                        }

                        else if(tag.equals("jobsNm")){

                            xpp.next();
                            data.jobsNm =xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }

                        else if(tag.equals("wantedTitle")){
                            xpp.next();
                            data.wantedTitle =xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("relJobsNm")){

                            xpp.next();
                            data.relJobsNm =xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("jobCont")){

                            xpp.next();
                            data.jobCont =xpp.getText();
                            //servNm= xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("salTpNm")){

                            xpp.next();
                            data.salTpNm =xpp.getText();


                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("relJobsNm")){

                            xpp.next();
                            data.relJobsNm =xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("pfCond")){

                            xpp.next();
                            data.pfCond =xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("selMthd")){

                            xpp.next();
                            data.selMthd =xpp.getText();

                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("workdayWorkhrCont")){

                            xpp.next();
                            data.workdayWorkhrCont =xpp.getText();


                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); // 태그 이름 얻어오기

                        if(tag.equals("wantedInfo")) {
                            buffer.append("\n"); // 첫번째 검색결과 끝 줄바꿈

                            publicDataDetailArray.add(new PublicDataDetail(data));

                        }
                        break;
                }
                eventType= xpp.next();
            }

        }
        catch (Exception e)
        {

        }

        return publicDataDetailArray;
    }
}
