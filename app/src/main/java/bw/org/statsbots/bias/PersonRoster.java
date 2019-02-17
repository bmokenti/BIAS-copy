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
    private  String B3_Guardian;
    private  String RapidDate;
    private String EAStatus;
    private String Batch;
    private String  AssignmentID;
    private String U15Rapid_Results;
    private String Barcode;
    private String HIVChildParentalConsentQ2 ;
    private String HIVChildParentalConsentQ3;
    private String HIVChildParentalConsentQ4 ;
    private String HIVChildParentalConsentNameOfInterviewer ;
    private String HIVChildParentalConsentInterviewerID ;
    private String HIVChildParentalConsentParticipantsID ;
    private String HIVChildParentalConsentDate ;
    private String HIVAssentQ1 ;
    private String HIVAssentQ2 ;
    private String HIVAssentQ3 ;
    private String HIVAssentQ4 ;
    private String HIVAssentNameOfInterviewer ;
    private String HIVAssentInterviewerID ;
    private String HIVAssentParticipantsID ;
    private String HIVAssentDate ;
    private String HIVConsentAdultsQ1 ;
    private String HIVConsentAdultsQ2;
    private String HIVConsentAdultsQ3;
    private String HIVConsentAdultsQ4 ;
    private String HIVConsentAdultsNameOfInterviewer;
    private String HIVConsentAdultsInterviewerID ;
    private String HIVConsentAdultsParticipantsID ;
    private String HIVConsentAdultsDate ;
    private String BloodSampleCollected ;
    private  String Rapid_Comment;




    private  String ChildBloodDraw ;
    private  String ChildBloodVol_1 ;
    private  String ChildBloodVol_4 ;
    private  String ChildBloodVol_6 ;
    private  String ChildBloodVol_10 ;
    private  String ChildBloodVolComment ;
    private  String Child_Rapid ;
    private  String ChildRapidResults ;
    private  String ChildBloodLabTest ;
    private  String ChildBloodStore ;
    private  String ChildRapidDate ;
    private  String ChildBloodSampleCollected ;
    private  String ChPrntlConsentBloodDraw ;
    private  String ChPrntlConsentRHT ;
    private  String ChPrntlConsentLabTest ;
    private  String ChPrntlConsentBloodStore ;
    private  String ChPrntlConsentDate ;
    private  String Ov64BloodDraw ;
    private  String Ov64BloodVol_1 ;
    private  String Ov64BloodVol_4 ;
    private  String Ov64BloodVol_6 ;
    private  String Ov64BloodVol_10 ;
    private  String Ov64BloodVolComment ;
    private  String Ov64_Rapid ;
    private  String Ov64RapidResults ;
    private  String Ov64BloodLabTest ;
    private  String Ov64BloodStore ;
    private  String Ov64RapidDate ;
    private  String Ov64BloodSampleCollected ;

    public String getChildBloodDraw() {
        return ChildBloodDraw;
    }

    public void setChildBloodDraw(String childBloodDraw) {
        ChildBloodDraw = childBloodDraw;
    }

    public String getChildBloodVol_1() {
        return ChildBloodVol_1;
    }

    public void setChildBloodVol_1(String childBloodVol_1) {
        ChildBloodVol_1 = childBloodVol_1;
    }

    public String getChildBloodVol_4() {
        return ChildBloodVol_4;
    }

    public void setChildBloodVol_4(String childBloodVol_4) {
        ChildBloodVol_4 = childBloodVol_4;
    }

    public String getChildBloodVol_6() {
        return ChildBloodVol_6;
    }

    public void setChildBloodVol_6(String childBloodVol_6) {
        ChildBloodVol_6 = childBloodVol_6;
    }

    public String getChildBloodVol_10() {
        return ChildBloodVol_10;
    }

    public void setChildBloodVol_10(String childBloodVol_10) {
        ChildBloodVol_10 = childBloodVol_10;
    }

    public String getChildBloodVolComment() {
        return ChildBloodVolComment;
    }

    public void setChildBloodVolComment(String childBloodVolComment) {
        ChildBloodVolComment = childBloodVolComment;
    }

    public String getChild_Rapid() {
        return Child_Rapid;
    }

    public void setChild_Rapid(String child_Rapid) {
        Child_Rapid = child_Rapid;
    }

    public String getChildRapidResults() {
        return ChildRapidResults;
    }

    public void setChildRapidResults(String childRapidResults) {
        ChildRapidResults = childRapidResults;
    }

    public String getChildBloodLabTest() {
        return ChildBloodLabTest;
    }

    public void setChildBloodLabTest(String childBloodLabTest) {
        ChildBloodLabTest = childBloodLabTest;
    }

    public String getChildBloodStore() {
        return ChildBloodStore;
    }

    public void setChildBloodStore(String childBloodStore) {
        ChildBloodStore = childBloodStore;
    }

    public String getChildRapidDate() {
        return ChildRapidDate;
    }

    public void setChildRapidDate(String childRapidDate) {
        ChildRapidDate = childRapidDate;
    }

    public String getChildBloodSampleCollected() {
        return ChildBloodSampleCollected;
    }

    public void setChildBloodSampleCollected(String childBloodSampleCollected) {
        ChildBloodSampleCollected = childBloodSampleCollected;
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

    public String getChPrntlConsentDate() {
        return ChPrntlConsentDate;
    }

    public void setChPrntlConsentDate(String chPrntlConsentDate) {
        ChPrntlConsentDate = chPrntlConsentDate;
    }

    public String getOv64BloodDraw() {
        return Ov64BloodDraw;
    }

    public void setOv64BloodDraw(String ov64BloodDraw) {
        Ov64BloodDraw = ov64BloodDraw;
    }

    public String getOv64BloodVol_1() {
        return Ov64BloodVol_1;
    }

    public void setOv64BloodVol_1(String ov64BloodVol_1) {
        Ov64BloodVol_1 = ov64BloodVol_1;
    }

    public String getOv64BloodVol_4() {
        return Ov64BloodVol_4;
    }

    public void setOv64BloodVol_4(String ov64BloodVol_4) {
        Ov64BloodVol_4 = ov64BloodVol_4;
    }

    public String getOv64BloodVol_6() {
        return Ov64BloodVol_6;
    }

    public void setOv64BloodVol_6(String ov64BloodVol_6) {
        Ov64BloodVol_6 = ov64BloodVol_6;
    }

    public String getOv64BloodVol_10() {
        return Ov64BloodVol_10;
    }

    public void setOv64BloodVol_10(String ov64BloodVol_10) {
        Ov64BloodVol_10 = ov64BloodVol_10;
    }

    public String getOv64BloodVolComment() {
        return Ov64BloodVolComment;
    }

    public void setOv64BloodVolComment(String ov64BloodVolComment) {
        Ov64BloodVolComment = ov64BloodVolComment;
    }

    public String getOv64_Rapid() {
        return Ov64_Rapid;
    }

    public void setOv64_Rapid(String ov64_Rapid) {
        Ov64_Rapid = ov64_Rapid;
    }

    public String getOv64RapidResults() {
        return Ov64RapidResults;
    }

    public void setOv64RapidResults(String ov64RapidResults) {
        Ov64RapidResults = ov64RapidResults;
    }

    public String getOv64BloodLabTest() {
        return Ov64BloodLabTest;
    }

    public void setOv64BloodLabTest(String ov64BloodLabTest) {
        Ov64BloodLabTest = ov64BloodLabTest;
    }

    public String getOv64BloodStore() {
        return Ov64BloodStore;
    }

    public void setOv64BloodStore(String ov64BloodStore) {
        Ov64BloodStore = ov64BloodStore;
    }

    public String getOv64RapidDate() {
        return Ov64RapidDate;
    }

    public void setOv64RapidDate(String ov64RapidDate) {
        Ov64RapidDate = ov64RapidDate;
    }

    public String getOv64BloodSampleCollected() {
        return Ov64BloodSampleCollected;
    }

    public void setOv64BloodSampleCollected(String ov64BloodSampleCollected) {
        Ov64BloodSampleCollected = ov64BloodSampleCollected;
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

    public String getRapid_Comment() {
        return Rapid_Comment;
    }

    public void setRapid_Comment(String rapid_Comment) {
        Rapid_Comment = rapid_Comment;
    }

    public String getB3_Guardian() {
        return B3_Guardian;
    }

    public void setB3_Guardian(String b3_Guardian) {
        B3_Guardian = b3_Guardian;
    }


    public String getHIVChildParentalConsentQ2() {
        return HIVChildParentalConsentQ2;
    }

    public void setHIVChildParentalConsentQ2(String HIVChildParentalConsentQ2) {
        this.HIVChildParentalConsentQ2 = HIVChildParentalConsentQ2;
    }

    public String getHIVChildParentalConsentQ3() {
        return HIVChildParentalConsentQ3;
    }

    public void setHIVChildParentalConsentQ3(String HIVChildParentalConsentQ3) {
        this.HIVChildParentalConsentQ3 = HIVChildParentalConsentQ3;
    }

    public String getHIVChildParentalConsentQ4() {
        return HIVChildParentalConsentQ4;
    }

    public void setHIVChildParentalConsentQ4(String HIVChildParentalConsentQ4) {
        this.HIVChildParentalConsentQ4 = HIVChildParentalConsentQ4;
    }

    public String getHIVChildParentalConsentNameOfInterviewer() {
        return HIVChildParentalConsentNameOfInterviewer;
    }

    public void setHIVChildParentalConsentNameOfInterviewer(String HIVChildParentalConsentNameOfInterviewer) {
        this.HIVChildParentalConsentNameOfInterviewer = HIVChildParentalConsentNameOfInterviewer;
    }

    public String getHIVChildParentalConsentInterviewerID() {
        return HIVChildParentalConsentInterviewerID;
    }

    public void setHIVChildParentalConsentInterviewerID(String HIVChildParentalConsentInterviewerID) {
        this.HIVChildParentalConsentInterviewerID = HIVChildParentalConsentInterviewerID;
    }

    public String getHIVChildParentalConsentParticipantsID() {
        return HIVChildParentalConsentParticipantsID;
    }

    public void setHIVChildParentalConsentParticipantsID(String HIVChildParentalConsentParticipantsID) {
        this.HIVChildParentalConsentParticipantsID = HIVChildParentalConsentParticipantsID;
    }

    public String getHIVChildParentalConsentDate() {
        return HIVChildParentalConsentDate;
    }

    public void setHIVChildParentalConsentDate(String HIVChildParentalConsentDate) {
        this.HIVChildParentalConsentDate = HIVChildParentalConsentDate;
    }

    public String getHIVAssentQ1() {
        return HIVAssentQ1;
    }

    public void setHIVAssentQ1(String HIVAssentQ1) {
        this.HIVAssentQ1 = HIVAssentQ1;
    }

    public String getHIVAssentQ2() {
        return HIVAssentQ2;
    }

    public void setHIVAssentQ2(String HIVAssentQ2) {
        this.HIVAssentQ2 = HIVAssentQ2;
    }

    public String getHIVAssentQ3() {
        return HIVAssentQ3;
    }

    public void setHIVAssentQ3(String HIVAssentQ3) {
        this.HIVAssentQ3 = HIVAssentQ3;
    }

    public String getHIVAssentQ4() {
        return HIVAssentQ4;
    }

    public void setHIVAssentQ4(String HIVAssentQ4) {
        this.HIVAssentQ4 = HIVAssentQ4;
    }

    public String getHIVAssentNameOfInterviewer() {
        return HIVAssentNameOfInterviewer;
    }

    public void setHIVAssentNameOfInterviewer(String HIVAssentNameOfInterviewer) {
        this.HIVAssentNameOfInterviewer = HIVAssentNameOfInterviewer;
    }

    public String getHIVAssentInterviewerID() {
        return HIVAssentInterviewerID;
    }

    public void setHIVAssentInterviewerID(String HIVAssentInterviewerID) {
        this.HIVAssentInterviewerID = HIVAssentInterviewerID;
    }

    public String getHIVAssentParticipantsID() {
        return HIVAssentParticipantsID;
    }

    public void setHIVAssentParticipantsID(String HIVAssentParticipantsID) {
        this.HIVAssentParticipantsID = HIVAssentParticipantsID;
    }

    public String getHIVAssentDate() {
        return HIVAssentDate;
    }

    public void setHIVAssentDate(String HIVAssentDate) {
        this.HIVAssentDate = HIVAssentDate;
    }

    public String getHIVConsentAdultsQ1() {
        return HIVConsentAdultsQ1;
    }

    public void setHIVConsentAdultsQ1(String HIVConsentAdultsQ1) {
        this.HIVConsentAdultsQ1 = HIVConsentAdultsQ1;
    }

    public String getHIVConsentAdultsQ2() {
        return HIVConsentAdultsQ2;
    }

    public void setHIVConsentAdultsQ2(String HIVConsentAdultsQ2) {
        this.HIVConsentAdultsQ2 = HIVConsentAdultsQ2;
    }

    public String getHIVConsentAdultsQ3() {
        return HIVConsentAdultsQ3;
    }

    public void setHIVConsentAdultsQ3(String HIVConsentAdultsQ3) {
        this.HIVConsentAdultsQ3 = HIVConsentAdultsQ3;
    }

    public String getHIVConsentAdultsQ4() {
        return HIVConsentAdultsQ4;
    }

    public void setHIVConsentAdultsQ4(String HIVConsentAdultsQ4) {
        this.HIVConsentAdultsQ4 = HIVConsentAdultsQ4;
    }

    public String getHIVConsentAdultsNameOfInterviewer() {
        return HIVConsentAdultsNameOfInterviewer;
    }

    public void setHIVConsentAdultsNameOfInterviewer(String HIVConsentAdultsNameOfInterviewer) {
        this.HIVConsentAdultsNameOfInterviewer = HIVConsentAdultsNameOfInterviewer;
    }

    public String getHIVConsentAdultsInterviewerID() {
        return HIVConsentAdultsInterviewerID;
    }

    public void setHIVConsentAdultsInterviewerID(String HIVConsentAdultsInterviewerID) {
        this.HIVConsentAdultsInterviewerID = HIVConsentAdultsInterviewerID;
    }

    public String getHIVConsentAdultsParticipantsID() {
        return HIVConsentAdultsParticipantsID;
    }

    public void setHIVConsentAdultsParticipantsID(String HIVConsentAdultsParticipantsID) {
        this.HIVConsentAdultsParticipantsID = HIVConsentAdultsParticipantsID;
    }

    public String getHIVConsentAdultsDate() {
        return HIVConsentAdultsDate;
    }

    public void setHIVConsentAdultsDate(String HIVConsentAdultsDate) {
        this.HIVConsentAdultsDate = HIVConsentAdultsDate;
    }

    public String getBloodSampleCollected() {
        return BloodSampleCollected;
    }

    public void setBloodSampleCollected(String bloodSampleCollected) {
        BloodSampleCollected = bloodSampleCollected;
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
