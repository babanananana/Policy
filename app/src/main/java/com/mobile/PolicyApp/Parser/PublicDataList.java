package com.mobile.PolicyApp.Parser;

public class PublicDataList {

    public String jurMnofNm;          // 단체
    public String servDgst;           // 내용
    public String servDtlLink;        // 링크
    public String servNm;             // 제목
    public String trgterIndvdlArray;  // 대상
    public String servID;             // 서비스 ID

    public PublicDataList(){

    }
    public PublicDataList(PublicDataList data)
    {
        this.jurMnofNm =data.jurMnofNm;
        this.servDgst = data.servDgst;
        this.servDtlLink = data.servDtlLink;
        this.servNm = data.servNm;
        this.trgterIndvdlArray = data.trgterIndvdlArray;
        this.servID = data.servID;
    }
    public PublicDataList(String jurMnofNm, String servDgst, String servDtlLink , String servNm, String trgterIndvdlArray, String servID) {
        this.jurMnofNm =jurMnofNm;
        this.servDgst = servDgst;
        this.servDtlLink = servDtlLink;
        this.servNm = servNm;
        this.trgterIndvdlArray = trgterIndvdlArray;
        this.servID = servID;

    }

    public void SetEmpty(){
        jurMnofNm = "";
        servDgst= "";
        servDtlLink = "";
        servNm = "";
        trgterIndvdlArray = "";
        servID = "";
    }

}
