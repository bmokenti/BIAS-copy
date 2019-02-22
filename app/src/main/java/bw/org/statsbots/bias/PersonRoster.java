package bw.org.statsbots.bias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class PersonRoster implements Serializable{



    Map<String,String> comments; /**Stores list of comments for every question*/
    private int SRNO;/**Sets person line number for consistency*/
    private String P01; /**sets person name*/
    private String P02; /**Relationship to head of house [2 digits]*/
    private String P03;/** Sex [1 digit]*/
    private String P04YY,P04MM,P04WKS;/**Age [YY] for > 2 and [YY] [MM] [WKS] for <=2 */
    private String P05;/** Citizenship [3 digits]*/
    private String P06;
    private String P07;
    private  String P08;
    private  String P09;
    private  String P10;
    private  String P11;
    private  String P12;
    private  String P13;
    private  String P13Other;
    private  String P14;
    private  String P15;
    private  String P16;
    private  String P17;
    private  String P18;
    private  String P19;
    private  String P20;
    private  String P21;
    private String B3_RapidConsent_Yes_No;
    private String Results;
    private String B3_Date;


    private String EAStatus;
    private String Batch;
    private String  AssignmentID;
    private String U15Rapid_Results;
    private String Barcode;
    private String BloodDraw;
    private String BloodVolume_1 ;
    private String BloodVolume_4 ;
    private String BloodVolume_6 ;
    private String BloodVolume_10 ;
    private String BloodVolumeComment ;
    private String Rapid ;
    private String RapidResults ; //B8_UnderRapid
    private String BloodLabTest;
    private String BloodStore ;
    private String RapidDate ;
    private String BloodSampleCollected ;
    private String ChPrntlConsentBloodDraw ;
    private String ChPrntlConsentRHT ;
    private String ChPrntlConsentLabTest ;
    private String ChPrntlConsentBloodStore ;
    private String B3_Guardian ;
    private String ChPrntlConsentDate ;
    private String PrntlConsentX_Ray ;
    private String PrntlConsentX_RayReview ;
    private String PrntlConsentX_RayStore ;
    private String PrntlConsentSP_Collect ;
    private String PrntlParentSP_AddTests ;
    private String PrntlConsentSP_LabTest ;
    private String PrntlConsentTBDate ;
    private String IndTB_X_Ray ;
    private String IndTB_X_RayReview ;
    private String IndTB_X_RayStore ;
    private String IndSP_Collect ;
    private String IndSP_AddTests ;
    private String IndSP_LabTests ;
    private String IndTB_ConsentDate ;

    public String getBloodDraw() {
        return BloodDraw;
    }

    public void setBloodDraw(String bloodDraw) {
        BloodDraw = bloodDraw;
    }

    public String getBloodVolume_1() {
        return BloodVolume_1;
    }

    public void setBloodVolume_1(String bloodVolume_1) {
        BloodVolume_1 = bloodVolume_1;
    }

    public String getBloodVolume_4() {
        return BloodVolume_4;
    }

    public void setBloodVolume_4(String bloodVolume_4) {
        BloodVolume_4 = bloodVolume_4;
    }

    public String getBloodVolume_6() {
        return BloodVolume_6;
    }

    public void setBloodVolume_6(String bloodVolume_6) {
        BloodVolume_6 = bloodVolume_6;
    }

    public String getBloodVolume_10() {
        return BloodVolume_10;
    }

    public void setBloodVolume_10(String bloodVolume_10) {
        BloodVolume_10 = bloodVolume_10;
    }

    public String getBloodVolumeComment() {
        return BloodVolumeComment;
    }

    public void setBloodVolumeComment(String bloodVolumeComment) {
        BloodVolumeComment = bloodVolumeComment;
    }

    public String getRapid() {
        return Rapid;
    }

    public void setRapid(String rapid) {
        Rapid = rapid;
    }

    public String getRapidResults() {
        return RapidResults;
    }

    public void setRapidResults(String rapidResults) {
        RapidResults = rapidResults;
    }

    public String getBloodLabTest() {
        return BloodLabTest;
    }

    public void setBloodLabTest(String bloodLabTest) {
        BloodLabTest = bloodLabTest;
    }

    public String getBloodStore() {
        return BloodStore;
    }

    public void setBloodStore(String bloodStore) {
        BloodStore = bloodStore;
    }

    public String getBloodSampleCollected() {
        return BloodSampleCollected;
    }

    public void setBloodSampleCollected(String bloodSampleCollected) {
        BloodSampleCollected = bloodSampleCollected;
    }

    public String getChPrntlConsentBloodDraw() {
        return ChPrntlConsentBloodDraw;
    }

    public void setChPrntlConsentBloodDraw(String chPrntlConsentBloodDraw) {
        ChPrntlConsentBloodDraw = chPrntlConsentBloodDraw;
    }

    public String getChPrntlConsentRHT() {
        return ChPrntlConsentRHT;
    }

    public void setChPrntlConsentRHT(String chPrntlConsentRHT) {
        ChPrntlConsentRHT = chPrntlConsentRHT;
    }

    public String getChPrntlConsentLabTest() {
        return ChPrntlConsentLabTest;
    }

    public void setChPrntlConsentLabTest(String chPrntlConsentLabTest) {
        ChPrntlConsentLabTest = chPrntlConsentLabTest;
    }

    public String getChPrntlConsentBloodStore() {
        return ChPrntlConsentBloodStore;
    }

    public void setChPrntlConsentBloodStore(String chPrntlConsentBloodStore) {
        ChPrntlConsentBloodStore = chPrntlConsentBloodStore;
    }

    public String getB3_Guardian() {
        return B3_Guardian;
    }

    public void setB3_Guardian(String b3_Guardian) {
        B3_Guardian = b3_Guardian;
    }

    public String getChPrntlConsentDate() {
        return ChPrntlConsentDate;
    }

    public void setChPrntlConsentDate(String chPrntlConsentDate) {
        ChPrntlConsentDate = chPrntlConsentDate;
    }

    public String getPrntlConsentX_Ray() {
        return PrntlConsentX_Ray;
    }

    public void setPrntlConsentX_Ray(String prntlConsentX_Ray) {
        PrntlConsentX_Ray = prntlConsentX_Ray;
    }

    public String getPrntlConsentX_RayReview() {
        return PrntlConsentX_RayReview;
    }

    public void setPrntlConsentX_RayReview(String prntlConsentX_RayReview) {
        PrntlConsentX_RayReview = prntlConsentX_RayReview;
    }

    public String getPrntlConsentX_RayStore() {
        return PrntlConsentX_RayStore;
    }

    public void setPrntlConsentX_RayStore(String prntlConsentX_RayStore) {
        PrntlConsentX_RayStore = prntlConsentX_RayStore;
    }

    public String getPrntlConsentSP_Collect() {
        return PrntlConsentSP_Collect;
    }

    public void setPrntlConsentSP_Collect(String prntlConsentSP_Collect) {
        PrntlConsentSP_Collect = prntlConsentSP_Collect;
    }

    public String getPrntlParentSP_AddTests() {
        return PrntlParentSP_AddTests;
    }

    public void setPrntlParentSP_AddTests(String prntlParentSP_AddTests) {
        PrntlParentSP_AddTests = prntlParentSP_AddTests;
    }

    public String getPrntlConsentSP_LabTest() {
        return PrntlConsentSP_LabTest;
    }

    public void setPrntlConsentSP_LabTest(String prntlConsentSP_LabTest) {
        PrntlConsentSP_LabTest = prntlConsentSP_LabTest;
    }

    public String getPrntlConsentTBDate() {
        return PrntlConsentTBDate;
    }

    public void setPrntlConsentTBDate(String prntlConsentTBDate) {
        PrntlConsentTBDate = prntlConsentTBDate;
    }

    public String getIndTB_X_Ray() {
        return IndTB_X_Ray;
    }

    public void setIndTB_X_Ray(String indTB_X_Ray) {
        IndTB_X_Ray = indTB_X_Ray;
    }

    public String getIndTB_X_RayReview() {
        return IndTB_X_RayReview;
    }

    public void setIndTB_X_RayReview(String indTB_X_RayReview) {
        IndTB_X_RayReview = indTB_X_RayReview;
    }

    public String getIndTB_X_RayStore() {
        return IndTB_X_RayStore;
    }

    public void setIndTB_X_RayStore(String indTB_X_RayStore) {
        IndTB_X_RayStore = indTB_X_RayStore;
    }

    public String getIndSP_Collect() {
        return IndSP_Collect;
    }

    public void setIndSP_Collect(String indSP_Collect) {
        IndSP_Collect = indSP_Collect;
    }

    public String getIndSP_AddTests() {
        return IndSP_AddTests;
    }

    public void setIndSP_AddTests(String indSP_AddTests) {
        IndSP_AddTests = indSP_AddTests;
    }

    public String getIndSP_LabTests() {
        return IndSP_LabTests;
    }

    public void setIndSP_LabTests(String indSP_LabTests) {
        IndSP_LabTests = indSP_LabTests;
    }

    public String getIndTB_ConsentDate() {
        return IndTB_ConsentDate;
    }

    public void setIndTB_ConsentDate(String indTB_ConsentDate) {
        IndTB_ConsentDate = indTB_ConsentDate;
    }







    public String getP13Other() {
        return P13Other;
    }

    public void setP13Other(String p13Other) {
        P13Other = p13Other;
    }



    public String getEAStatus() {
        return EAStatus;
    }

    public void setEAStatus(String EAStatus) {
        this.EAStatus = EAStatus;
    }

    public String getAssignmentID() {
        return AssignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        AssignmentID = assignmentID;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }



    public String getU15Rapid_Results() {
        return U15Rapid_Results;
    }

    public void setU15Rapid_Results(String u15Rapid_Results) {
        U15Rapid_Results = u15Rapid_Results;
    }





    public String getResults() {
        return Results;
    }

    public void setResults(String results) {
        Results = results;
    }



    public String getB3_RapidConsent_Yes_No() {
        return B3_RapidConsent_Yes_No;
    }

    public void setB3_RapidConsent_Yes_No(String b3_RapidConsent_Yes_No) {
        B3_RapidConsent_Yes_No = b3_RapidConsent_Yes_No;
    }

    public String getB3_Date() {
        return B3_Date;
    }

    public void setB3_Date(String b3_Date) {
        B3_Date = b3_Date;
    }


    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }



    public String getRapidDate() {
        return RapidDate;
    }

    public void setRapidDate(String rapidDate) {
        RapidDate = rapidDate;
    }




    public int getSRNO() {
        return SRNO;
    }

    public void setSRNO(int SRNO) {
        this.SRNO = SRNO;
    }






    public String getP07() {
        return P07;
    }

    public void setP07(String p07) {
        P07 = p07;
    }

    /** Usual Palace of residence */

    /**Checks the ages for under 2 or over 2*/
    public int checkP04(){
        int i=0;
        //Under 2 filled
        if(P04YY==null && (P04MM!=null && P04WKS!=null)){
            i=1;
        }
        //Over 2 filled
        else if(P04YY!=null && (P04MM==null && P04WKS==null)){
            i=2;
        }
        //Nothing filled
        else if(P04YY==null && P04MM==null && P04WKS==null){
            i=3;
        }


        return i;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public void setComments(Map<String, String> comments) {
        this.comments = comments;
    }

    public String getP01() {
        return P01;
    }

    public void setP01(String name) {
        P01 = name;
    }

    public String getP02() {
        return P02;
    }

    public int getLineNumber() {
        return SRNO;
    }

    public void setLineNumber(int lineNumber) {
        SRNO = lineNumber;
    }

    public String getP03() {
        return P03;
    }

    public void setP03(String p03) {
        P03 = p03;
    }

    public String getP04YY() {
        return P04YY;
    }

    public void setP04YY(String p04YY) {
        P04YY = p04YY;
    }

    public String getP04MM() {
        return P04MM;
    }

    public void setP04MM(String p04MM) {
        P04MM = p04MM;
    }

    public String getP04WKS() {
        return P04WKS;
    }

    public void setP04WKS(String p04WKS) {
        P04WKS = p04WKS;
    }

    public String getP05() {
        return P05;
    }

    public void setP05(String p05) {
        P05 = p05;
    }

    public String getP06() {
        return P06;
    }

    public void setP06(String p06) {
        P06 = p06;
    }


    public String getP08() {
        return P08;
    }

    public void setP08(String p08) {
        P08 = p08;
    }


    public String getP09() {
        return P09;
    }

    public void setP09(String p09) {
        P09 = p09;
    }


    public String getP10() { return P10; }

    public void setP10(String p10) { P10 = p10; }


    public String getP11() { return P11; }

    public void setP11(String p11) { P11 = p11; }



    public String getP12() { return P12; }

    public void setP12(String p12) { P12 = p12; }



    public String getP13() { return P13; }

    public void setP13(String p13) { P13 = p13; }


    public String getP14() { return P14; }

    public void setP14(String p14) { P14 = p14; }


    public String getP15() { return P15; }

    public void setP15(String p15) { P15 = p15; }


    public String getP16() { return P16; }

    public void setP16(String p16) { P16 = p16; }

    public void setP02(String rel){
        this.P02=rel;
    }
    public String getP17() {
        return P17;
    }

    public void setP17(String p17) {
        P17 = p17;
    }

    public String getP18() {
        return P18;
    }

    public void setP18(String p18) {
        P18 = p18;
    }

    public String getP19() {
        return P19;
    }

    public void setP19(String p19) {
        P19 = p19;
    }

    public String getP20() {
        return P20;
    }

    public void setP20(String p20) {
        P20 = p20;
    }

    public String getP21() {
        return P21;
    }

    public void setP21(String p21) {
        P21 = p21;
    }
}
