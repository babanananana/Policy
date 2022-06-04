package com.mobile.PolicyApp.Parser;

public class PublicDataList {



    public String  wantedAuthNo;
    public String  company;
    public String  title;
    public String  salTpNm;
    public String  region;
    public String  regDt;
    public String  closeDt;
    public String  wantedInfoUrl;



    public PublicDataList(){

    }
    public PublicDataList(PublicDataList data)
    {
        this.wantedInfoUrl =data.wantedInfoUrl;
        this.closeDt =data.closeDt;
        this.regDt =data.regDt;
        this.region =data.region;
        this.salTpNm =data.salTpNm;
        this.title =data.title;
        this.company =data.company;
        this.wantedAuthNo =data.wantedAuthNo;

    }


    public void SetEmpty(){

        wantedInfoUrl="";
        closeDt="";
        regDt="";
        region="";
        salTpNm="";
        title="";
        company= "";
        wantedAuthNo = "";

    }

}
