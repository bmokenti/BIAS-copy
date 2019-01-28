package bw.org.statsbots.bias;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.List;

public class HouseHold implements Serializable {
    private int currentPerson;
    private int Head;

    public PersonRoster[] getHouseHoldeMembers() {
        return HouseHoldeMembers;
    }

    public void setHouseHoldeMembers(PersonRoster[] houseHoldeMembers) {
        HouseHoldeMembers = houseHoldeMembers;
    }

    private PersonRoster[] HouseHoldeMembers;
    protected Individual[] IndividualQuestionaire;
    protected Assignments[] EA_Assgnment;
    private String  DWELLING_NO;

    private String BatchNumber;

    private String  HH_NO;
    private String ENUMERATOR;
    private  String SUPERVISOR;
    private  String QUALITY_CONTROLLER;
    private  String INTERVIEWER_VISITS1;


    private  String NEXT_VISIT_3_TIME;

    private String DATE1;
    private  String VISIT1_RESULT;
    private  String COMMENT1;
    private  String NEXT_VISIT_2_DATE;
    private String NEXT_VISIT_2_TIME;
    private String NEXT_VISIT_2;
    private  String INTERVIEWER_VISITS2;
    private  String DATE2;
    private  String VISIT2_RESULT;
    private String COMMENT2;
    private  String NEXT_VISIT_3_DATE;
    private  String NEXT_VISIT_3;
    private  String INTERVIEWER_VISITS3;
    private  String DATE3;
    private String VISIT3_RESULT;
    private  String COMMENT_3;
    private  String TOTAL_VISITS;
    private String Assignment_ID;



    private String Sample_FK;
    private String CONSENT;
    private  String CHECKED_BY;
    private  String CODED;
    private  String FINAL_RESULT;
    private  String FINAL_OTHER;
    private  String Interview_Status;
    private  String SuperComment;
    private  String QcComment;

    public String getH13Camels() {
        return H13Camels;
    }

    public void setH13Camels(String h13Camels) {
        H13Camels = h13Camels;
    }

    private  String HQComment;



    public String getNEXT_VISIT_3_TIME() {
        return NEXT_VISIT_3_TIME;
    }

    public void setNEXT_VISIT_3_TIME(String NEXT_VISIT_1_TIME) {
        this.NEXT_VISIT_3_TIME = NEXT_VISIT_1_TIME;
    }

    public String getNEXT_VISIT_2_TIME() {
        return NEXT_VISIT_2_TIME;
    }

    public void setNEXT_VISIT_2_TIME(String NEXT_VISIT_2_TIME) {
        this.NEXT_VISIT_2_TIME = NEXT_VISIT_2_TIME;
    }


    private String H01;
    private String H02;
    private  String H03;
    private  String H03Other;
    private String H04;
    private String H04Other;
    private String H05;
    private String H05Other;
    private  String H06;
    private String Current;

    public String getCurrent() {
        return Current;
    }

    public void setCurrent(String current) {
        Current = current;
    }

    public String getIsHIVTB40() {
        return IsHIVTB40;
    }

    public void setIsHIVTB40(String isHIVTB40) {
        IsHIVTB40 = isHIVTB40;
    }

    public String getH13Tractor() {
        return H13Tractor;
    }

    public void setH13Tractor(String h13Tractor) {
        H13Tractor = h13Tractor;
    }

    public String getH13Motorcycle() {
        return H13Motorcycle;
    }

    public void setH13Motorcycle(String h13Motorcycle) {
        H13Motorcycle = h13Motorcycle;
    }

    public String getH13Bicycle() {
        return H13Bicycle;
    }

    public void setH13Bicycle(String h13Bicycle) {
        H13Bicycle = h13Bicycle;
    }

    public String getH13DonkeyCart() {
        return H13DonkeyCart;
    }

    public void setH13DonkeyCart(String h13DonkeyCart) {
        H13DonkeyCart = h13DonkeyCart;
    }

    public String getH13DonkeyHorse() {
        return H13DonkeyHorse;
    }

    public void setH13DonkeyHorse(String h13DonkeyHorse) {
        H13DonkeyHorse = h13DonkeyHorse;
    }



    private String IsHIVTB40;

    private  String H13Tractor;
    private  String H13Motorcycle;
    private  String H13Bicycle;
    private  String H13DonkeyCart;
    private  String H13DonkeyHorse;
    private  String H13Camels;


    private  String H12TV;

    public String getH12TV() {
        return H12TV;
    }

    public void setH12TV(String h12TV) {
        H12TV = h12TV;
    }

    public String getH12Telephone() {
        return H12Telephone;
    }

    public void setH12Telephone(String h12Telephone) {
        H12Telephone = h12Telephone;
    }

    public String getH12CellPhone() {
        return H12CellPhone;
    }

    public void setH12CellPhone(String h12CellPhone) {
        H12CellPhone = h12CellPhone;
    }

    public String getH12PrintMedia() {
        return H12PrintMedia;
    }

    public void setH12PrintMedia(String h12PrintMedia) {
        H12PrintMedia = h12PrintMedia;
    }

    public String getH12ElecMedia() {
        return H12ElecMedia;
    }

    public void setH12ElecMedia(String h12ElecMedia) {
        H12ElecMedia = h12ElecMedia;
    }

    public String getH12PerfomArts() {
        return H12PerfomArts;
    }

    public void setH12PerfomArts(String h12PerfomArts) {
        H12PerfomArts = h12PerfomArts;
    }

    private  String H12Telephone;
    private  String H12CellPhone;
    private  String H12PrintMedia;
    private  String H12ElecMedia;
    private  String  H12PerfomArts;

    private String H07;
    private String H08;

    public String getH08Other() {
        return H08Other;
    }

    public void setH08Other(String h08Other) {
        H08Other = h08Other;
    }

    private String H08Other;
    private  String H09;
    private  String H09Other;
    private String H10;
    private  String H11;

    public String getH03Other() {
        return H03Other;
    }

    public void setH03Other(String h03Other) {
        H03Other = h03Other;
    }

    public String getH04Other() {
        return H04Other;
    }

    public void setH04Other(String h04Other) {
        H04Other = h04Other;
    }

    public String getH05Other() {
        return H05Other;
    }

    public void setH05Other(String h05Other) {
        H05Other = h05Other;
    }

    public String getH09Other() {
        return H09Other;
    }

    public void setH09Other(String h09Other) {
        H09Other = h09Other;
    }

    public String getH11Other() {
        return H11Other;
    }

    public void setH11Other(String h11Other) {
        H11Other = h11Other;
    }

    private  String H11Other;
    private String H12;
    private String H13;



    public String getSuperComment() {
        return SuperComment;
    }

    public void setSuperComment(String superComment) {
        SuperComment = superComment;
    }



    public String getQcComment() {
        return QcComment;
    }
    public void setQcComment(String qcComment) {
        QcComment = qcComment;
    }



    public String getHQComment() {
        return HQComment;
    }
    public void setHQComment(String hqComment) {
        SuperComment = hqComment;
    }



    public String getSample_FK() {
        return Sample_FK;
    }

    public void setSample_FK(String sample_FK) {
        Sample_FK = sample_FK;
    }

    public String getInterview_Status() {
        return Interview_Status;
    }

    public void setInterview_Status(String interview_Status) {
        Interview_Status = interview_Status;
    }



    public String getAssignment_ID() {
        return Assignment_ID;
    }

    public void setAssignment_ID(String assignment_ID) {
        Assignment_ID = assignment_ID;
    }




    public HouseHold() {

    }






    public String getENUMERATOR() {
        return ENUMERATOR;
    }

    public void setENUMERATOR(String ENUMERATOR) {
        this.ENUMERATOR = ENUMERATOR;
    }

    public String getSUPERVISOR() {
        return SUPERVISOR;
    }

    public void setSUPERVISOR(String SUPERVISOR) {
        this.SUPERVISOR = SUPERVISOR;
    }

    public String getQUALITY_CONTROLLER() {
        return QUALITY_CONTROLLER;
    }

    public void setQUALITY_CONTROLLER(String QUALITY_CONTROLLER) {
        this.QUALITY_CONTROLLER = QUALITY_CONTROLLER;
    }

    public String getINTERVIEWER_VISITS1() {
        return INTERVIEWER_VISITS1;
    }

    public void setINTERVIEWER_VISITS1(String INTERVIEWER_VISITS1) {
        this.INTERVIEWER_VISITS1 = INTERVIEWER_VISITS1;
    }



    public String getNEXT_VISIT_2() {
        return NEXT_VISIT_2;
    }

    public void setNEXT_VISIT_2(String NEXT_VISIT_2) {
        this.NEXT_VISIT_2 = NEXT_VISIT_2;
    }

    public String getINTERVIEWER_VISITS2() {
        return INTERVIEWER_VISITS2;
    }

    public void setINTERVIEWER_VISITS2(String INTERVIEWER_VISITS2) {
        this.INTERVIEWER_VISITS2 = INTERVIEWER_VISITS2;
    }



    public String getNEXT_VISIT_3() {
        return NEXT_VISIT_3;
    }

    public void setNEXT_VISIT_3(String NEXT_VISIT_3) {
        this.NEXT_VISIT_3 = NEXT_VISIT_3;
    }

    public String getINTERVIEWER_VISITS3() {
        return INTERVIEWER_VISITS3;
    }

    public void setINTERVIEWER_VISITS3(String INTERVIEWER_VISITS3) {
        this.INTERVIEWER_VISITS3 = INTERVIEWER_VISITS3;
    }



    public String getCOMMENT_3() {
        return COMMENT_3;
    }

    public void setCOMMENT_3(String COMMENT_3) {
        this.COMMENT_3 = COMMENT_3;
    }

    public String getTOTAL_VISITS() {
        return TOTAL_VISITS;
    }

    public void setTOTAL_VISITS(String TOTAL_VISITS) {
        this.TOTAL_VISITS = TOTAL_VISITS;
    }



    public String getCONSENT() {
        return CONSENT;
    }

    public void setCONSENT(String CONSENT) {
        this.CONSENT = CONSENT;
    }

    public String getCHECKED_BY() {
        return CHECKED_BY;
    }

    public void setCHECKED_BY(String CHECKED_BY) {
        this.CHECKED_BY = CHECKED_BY;
    }

    public String getCODED() {
        return CODED;
    }

    public void setCODED(String CODED) {
        this.CODED = CODED;
    }

    public String getFINAL_RESULT() {
        return FINAL_RESULT;
    }

    public void setFINAL_RESULT(String FINAL_RESULT) {
        this.FINAL_RESULT = FINAL_RESULT;
    }

    public String getFINAL_OTHER() {
        return FINAL_OTHER;
    }

    public void setFINAL_OTHER(String FINAL_OTHER) {
        this.FINAL_OTHER = FINAL_OTHER;
    }

    public String getHH_NO() {
        return HH_NO;
    }

    public void setHH_NO(String HH_NO) {
        this.HH_NO = HH_NO;
    }












    /**VISIT 1*/
    private String INTERVIEWER_VISIT1;

    private  String Visit1Other;


    /**VISIT 2*/
    private String INTERVIEWER_VISIT2;

    private  String Visit2Other;


    /**VISIT 3*/
    private String INTERVIEWER_VISIT3;

    private String COMMENT3;
    private  String Visit3Other;



    public String getNEXT_VISIT_2_DATE() {
        return NEXT_VISIT_2_DATE;
    }

    public void setNEXT_VISIT_2_DATE(String NEXT_VISIT_2_DATE) {
        this.NEXT_VISIT_2_DATE = NEXT_VISIT_2_DATE;
    }

    public String getNEXT_VISIT_3_DATE() {
        return NEXT_VISIT_3_DATE;
    }

    public void setNEXT_VISIT_3_DATE(String NEXT_VISIT_3_DATE) {
        this.NEXT_VISIT_3_DATE = NEXT_VISIT_3_DATE;
    }


    public String getVisit1Other() {
        return Visit1Other;
    }

    public void setVisit1Other(String visit1Other) {
        Visit1Other = visit1Other;
    }

    public String getVisit2Other() {
        return Visit2Other;
    }

    public void setVisit2Other(String visit2Other) {
        Visit2Other = visit2Other;
    }

    public String getVisit3Other() {
        return Visit3Other;
    }

    public void setVisit3Other(String visit3Other) {
        Visit3Other = visit3Other;
    }

    public String getINTERVIEWER_VISIT1() {
        return INTERVIEWER_VISIT1;
    }

    public void setINTERVIEWER_VISIT1(String INTERVIEWER_VISIT1) {
        this.INTERVIEWER_VISIT1 = INTERVIEWER_VISIT1;
    }

    public String getDATE1() {
        return DATE1;
    }

    public void setDATE1(String DATE1) {
        this.DATE1 = DATE1;
    }

    public String getVISIT1_RESULT() {
        return VISIT1_RESULT;
    }

    public void setVISIT1_RESULT(String VISIT1_RESULT) {
        this.VISIT1_RESULT = VISIT1_RESULT;
    }

    public String getCOMMENT1() {
        return COMMENT1;
    }

    public void setCOMMENT1(String COMMENT1) {
        this.COMMENT1 = COMMENT1;
    }

    public String getINTERVIEWER_VISIT2() {
        return INTERVIEWER_VISIT2;
    }

    public void setINTERVIEWER_VISIT2(String INTERVIEWER_VISIT2) {
        this.INTERVIEWER_VISIT2 = INTERVIEWER_VISIT2;
    }

    public String getDATE2() {
        return DATE2;
    }

    public void setDATE2(String DATE2) {
        this.DATE2 = DATE2;
    }

    public String getVISIT2_RESULT() {
        return VISIT2_RESULT;
    }

    public void setVISIT2_RESULT(String VISIT2_RESULT) {
        this.VISIT2_RESULT = VISIT2_RESULT;
    }

    public String getCOMMENT2() {
        return COMMENT2;
    }

    public void setCOMMENT2(String COMMENT2) {
        this.COMMENT2 = COMMENT2;
    }

    public String getINTERVIEWER_VISIT3() {
        return INTERVIEWER_VISIT3;
    }

    public void setINTERVIEWER_VISIT3(String INTERVIEWER_VISIT3) {
        this.INTERVIEWER_VISIT3 = INTERVIEWER_VISIT3;
    }

    public String getDATE3() {
        return DATE3;
    }

    public void setDATE3(String DATE3) {
        this.DATE3 = DATE3;
    }

    public String getVISIT3_RESULT() {
        return VISIT3_RESULT;
    }

    public void setVISIT3_RESULT(String VISIT3_RESULT) {
        this.VISIT3_RESULT = VISIT3_RESULT;
    }

    public String getCOMMENT3() {
        return COMMENT3;
    }

    public void setCOMMENT3(String COMMENT3) {
        this.COMMENT3 = COMMENT3;
    }





    public String getVILLAGE_NO() {
        return VILLAGE_NO;
    }

    public void setVILLAGE_NO(String VILLAGE_NO) {
        this.VILLAGE_NO = VILLAGE_NO;
    }

    private String VILLAGE_NO;
    private String EA_NO;

    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        BatchNumber = batchNumber;
    }






    public String getEA_NO() {
        return EA_NO;
    }

    public void setEA_NO(String EA_NO) {
        this.EA_NO = EA_NO;
    }




    public String getDWELLING_NO() {
        return DWELLING_NO;
    }

    public void setDWELLING_NO(String DWELLING_NO) {
        this.DWELLING_NO = DWELLING_NO;
    }
    public int getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(int currentPerson) {
        this.currentPerson = currentPerson;
    }

    public String getH01() {
        return H01;
    }

    public void setH01(String h01) {
        H01 = h01;
    }

    public String getH02() {
        return H02;
    }

    public void setH02(String h02) {
        H02 = h02;
    }

    public String getH03() {
        return H03;
    }

    public void setH03(String h03) {
        H03 = h03;
    }

    public String getH04() {
        return H04;
    }

    public void setH04(String h04) {
        H04 = h04;
    }

    public String getH05() {
        return H05;
    }

    public void setH05(String h05) {
        H05 = h05;
    }

    public String getH06() {
        return H06;
    }

    public void setH06(String h06) {
        H06 = h06;
    }

    public String getH07() {
        return H07;
    }

    public void setH07(String h07) {
        H07 = h07;
    }

    public String getH08() {
        return H08;
    }

    public void setH08(String h08) {
        H08 = h08;
    }

    public String getH09() {
        return H09;
    }

    public void setH09(String h09) {
        H09 = h09;
    }

    public String getH10() {
        return H10;
    }

    public void setH10(String h10) {
        H10 = h10;
    }

    public String getH11() {
        return H11;
    }

    public void setH11(String h11) {
        H11 = h11;
    }

    public String getH12() {
        return H12;
    }

    public void setH12(String h12) {
        H12 = h12;
    }

    public String getH13() {
        return H13;
    }

    public void setH13(String h13) {
        H13 = h13;
    }

    public void setHead(int head) {
        Head = head;
    }

    public HouseHold(String[] hhArray, int HeadofHouse, int TotalNoPersons){

        this.Head = HeadofHouse;
        HouseHoldeMembers = new PersonRoster[TotalNoPersons];

        /**
         * For each household member set their line number and name
         */
        for (int i=0;i<TotalNoPersons;i++) {
            HouseHoldeMembers[i] = new PersonRoster();
            HouseHoldeMembers[i].setLineNumber(i);
            HouseHoldeMembers[i].setP01(hhArray[i]);
        }

    }

    /**Returns the total number of household members*/
    public int getTotalPersons(){
        return this.HouseHoldeMembers.length;
    }

    /**Returns the Line number of Head of house*/
    public int getHead(){
        return this.Head;
    }

    /**Method returns roster of all persons*/
    public PersonRoster[] getPersons(){
        return this.HouseHoldeMembers;
    }

    public void writeData(String data,Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("BiasData.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    public Individual[] getIndividual(){
        return this.IndividualQuestionaire;
    }

    public void writeIndivisualData(String data,Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("BiasData.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }



}
