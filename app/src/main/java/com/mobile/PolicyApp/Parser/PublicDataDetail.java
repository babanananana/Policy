package com.mobile.PolicyApp.Parser;

public class PublicDataDetail {
    public String jobsNm  ;
    public String wantedTitle;
    public String relJobsNm;
    public String jobCont;
    public String salTpNm;
    public String workRegion;
    public String workdayWorkhrCont;
    public String pfCond;
    public String selMthd;

    public PublicDataDetail(){

    }
    public PublicDataDetail(PublicDataDetail data)
    {
        this.jobsNm = data.jobsNm;
        this.wantedTitle =data.wantedTitle;
        this.relJobsNm = data.relJobsNm;
        this.jobCont = data.jobCont;
        this.salTpNm = data.salTpNm;
        this.workRegion = data.workRegion;
        this.workdayWorkhrCont = data.workdayWorkhrCont;
        this.pfCond = data.pfCond;
        this.selMthd = data.selMthd;

    }

    public void SetEmpty(){
        jobsNm = "";
        wantedTitle = "";
        relJobsNm= "";
        jobCont = "";
        salTpNm = "";
        workRegion = "";
        workdayWorkhrCont= "";
        pfCond= "";
        selMthd= "";

    }
}
