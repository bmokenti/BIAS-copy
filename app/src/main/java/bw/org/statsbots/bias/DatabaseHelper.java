package bw.org.statsbots.bias;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public  class DatabaseHelper extends SQLiteOpenHelper {

   protected HouseHold thisHouse;
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BIAS.db";

    // Table Names
    //private static final String TABLE_USERS = "AspNetUsers";
    private static final String tblusers = "Application_Users";
    private static final String Code = "Code";
    private static final String password = "password";
    private static final String Name = "Name";
    private static final String SName = "SName";
    private static final String Contact = "Contact";
    private static final String QC_Code = "QC_Code";
    private static final String Super_Code = "Super_Code";
    private static final String Role = "Role";
    private static final String HQ_Code = "HQ_Code";


    private static final String tblAssignments = "EA_Assignments";
    private static final String EA_Assignment_ID = "EA_Assignment_ID";
    private static final String STRATUM = "STRATUM";
    private static final String DISTRICT = "DISTRICT";
    private static final String VILLAGE = "VILLAGE";
    private static final String LOCALITY = "LOCALITY";
    private static final String EA = "EA";
    private static final String BLOCK_NO = "BLOCK_NO";
    private static final String EA_STATUS = "EA_STATUS";
    private static final String Sample_FK = "Sample_FK";
    private static final String ROUND_NUMBER = "ROUND_NUMBER";
    private static final String Supervisor = "Supervisor";


    private static final String tblhousehold = "House_Hold_Assignments";
    private static final String BatchNumber = "BatchNumber";
    private static final String HH_Assignment_ID = "EA_Assignment_ID";
    private static final String DWELLING_NO = "DWELLING_NO";
    private static final String HH_NO = "HH_NO";
    private static final String RESP_LINE = "RESP_LINE";
    private static final String ENUMERATOR = "ENUMERATOR";
    private static final String SUPERVISOR = "SUPERVISOR";
    private static final String QUALITY_CONTROLLER = "QUALITY_CONTROLLER";
    private static final String INTERVIEWER_VISITS1 = "INTERVIEWER_VISITS1";
    private static final String DATE1 = "DATE1";
    private static final String VISIT1_RESULT = "VISIT1_RESULT";
    private static final String COMMENT1 = "COMMENT1";
    private static final String NEXT_VISIT_2_DATE = "NEXT_VISIT_2_DATE";
    private static final String NEXT_VISIT_2_Time = "NEXT_VISIT_2_Time";
    private static final String INTERVIEWER_VISITS2 = "INTERVIEWER_VISITS2";
    private static final String DATE2 = "DATE2";
    private static final String VISIT2_RESULT = "VISIT2_RESULT";
    private static final String COMMENT2 = "COMMENT2";
    private static final String NEXT_VISIT_3_DATE = "NEXT_VISIT_3_DATE";
    private static final String NEXT_VISIT_3_Time = "NEXT_VISIT_3_Time";
    private static final String INTERVIEWER_VISITS3 = "INTERVIEWER_VISITS3";
    private static final String DATE3 = "DATE3";
    private static final String VISIT3_RESULT = "VISIT3_RESULT";
    private static final String COMMENT_3 = "COMMENT_3";
    private static final String TOTAL_VISITS = "TOTAL_VISITS";
    private static final String CONSENT = "CONSENT";
    private static final String CHECKED_BY = "CHECKED_BY";
    private static final String CODED = "CODED";
    private static final String FINAL_RESULT = "FINAL_RESULT";
    private static final String FINAL_OTHER = "FINAL_OTHER";
    private static final String Interview_Status = "Interview_Status";
    private static final String SuperComment = "SuperComment";



    private static final String tblhhroster = "HHP_ROSTER";
    private static final String Assignment_ID = "EA_Assignment_ID";
    private static final String BatchNumberR = "BatchNumber";
    private static final String SRNO = "SRNO";
    private static final String P01 = "P01";
    private static final String P02 = "P02";
    private static final String P03 = "P03";
    private static final String P04_YY = "P04_YY";
    private static final String P04_MM = "P04_MM";
    private static final String P04_WKS = "P04_WKS";
    private static final String P05 = "P05";
    private static final String P06 = "P06";
    private static final String P07 = "P07";

    private static final String P17 = "P17";
    private static final String P18 = "P18";
    private static final String P19 = "P19";
    private static final String P20 = "P20";
    private static final String P21 = "P21";
    private static final String BloodSampleCollected = "BloodSampleCollected";
    private static final String Barcode = "Barcode";
    private static final String RapidResults = "RapidResults";
    private static final String RapidDate = "RapidDate";
    private static final String Rapid_Comment = "Rapid_Comment";
    private static final String B3_RapidConsent_Yes_No = "B3_RapidConsent_Yes_No";
    private static final String B3_Guardian = "B3_Guardian";
    private static final String B3_Date = "B3_Date";
    private static final String U15Rapid_Result = "U15Rapid_Result";




    private static final String tblindividual = "Individual";
    private static final String BatchNumberi = "BatchNumber";
    private static final String Assignment_IDi = "Assignment_ID";
    private static final String SRNOi = "SRNO";
    private static final String Q101 = "Q101";
    private static final String Q102 = "Q102";
    private static final String Q103 = "Q103";
    private static final String Q104 = "Q104";
    private static final String Q104c = "Q104c";
    private static final String Q104cBISCED = "Q104cBISCED";
    private static final String Q401 = "Q401";
    private static final String Q402 = "Q402";
    private static final String Q402a = "Q402a";
    private static final String Q402b = "Q402b";
    private static final String Q403 = "Q403";
    private static final String Q501 = "Q501";
    private static final String Q502 = "Q502";
    private static final String Q503 = "Q503";
    private static final String Q504_1 ="Q504_1";
    private static final String Q504_2=	"Q504_2";
    private static final String Q504_3	="Q504_3";
    private static final String Q504_4	="Q504_4";
    private static final String Q504_5	="Q504_5";
    private static final String Q504_6	="Q504_6";
    private static final String Q504_7	="Q504_7";
    private static final String Q504_8	="Q504_8";
    private static final String Q504_10	="Q504_10";
    private static final String Q504_Other	="Q504_Other";
    private static final String Q504_OtherSpecify	="Q504_OtherSpcify";
    private static final String Q904 = "Q9041";
    private static final String Q1101 = "Q1101";
    private static final String Q1101a = "Q1101a";
    private static final String Q1101aOther = "Q1101aOther";
    private static final String Q1102 = "Q1102";
    private static final String Q1102a = "Q1102a";
    private static final String Q1103 = "Q1103";
    private static final String Q1103aDD = "Q1103aDD";
    private static final String Q1103aWks = "Q1103aWks";
    private static final String Q1103aDontKnow = "Q1103aDontKnow";
    private static final String Q1104 = "Q1104";
    private static final String Q1105 = "Q1105";
    private static final String Q1106 = "Q1106";
    private static final String Q1106a = "Q1106a";
    private static final String Q1106b = "Q1106b";
    private static final String Q1106bOther = "Q1106bOther";
    private static final String Q1107 = "Q1107";
    private static final String Q1107aDD = "Q1107aDD";
    private static final String Q1107aWks = "Q1107aWks";
    private static final String Q1107aDontKnow = "Q1107aDontKnow";
    private static final String Q1108 = "Q1108";
    private static final String Q1108aDD = "Q1108aDD";
    private static final String Q1108aWks = "Q1108aWks";
    private static final String Q1108aDontKnow = "Q1108aDontKnow";
    private static final String Q1109 = "Q1109";
    private static final String Q1110 = "Q1110";
    private static final String Q1111 = "Q1111";
    private static final String Q1111Other = "Q1111Other";
    private static final String Q1112 = "Q1112";
    private static final String Q1112Other = "Q1112Other";
    private static final String Q1113 = "Q1113";
    private static final String Q1113Other = "Q1113Other";
    private static final String Q1114 = "Q1114";
    private static final String IndBloodSampleCollected = "IndBloodSampleCollected";
    private static final String IndBarcode = "IndBarcode";
    private static final String IndRapidResults = "IndRapidResults";
    private static final String IndRapidDate = "IndRapidDate";
    private static final String B8_Yes_No = "B8_Yes_No";
    private static final String B8_Date = "B8_Date";
    private static final String B8_O15_Rapid = "B8_O15_Rapid";
    private static final String Q801f = "Q801f";
    private static final String IndRapid_Comment = "IndRapid_Comment";



    private static final String tblConsents = "Consents";
    private static final String Assignment_IDC = "Assignment_ID";
    private static final String BatchNumberC = "BatchNumber";
    private static final String SRNOC = "SRNO";
    private static final String BarcodeC = "Barcode";
    private static final String HIV6wks_14ParentalConsentQ2 = "HIV6wks_14ParentalConsentQ2";
    private static final String HIV6wks_14ParentalConsentQ3 = "HIV6wks_14ParentalConsentQ3";
    private static final String HIV6wks_14ParentalConsentQ4 = "HIV6wks_14ParentalConsentQ4";
    private static final String HIVwks_14ParentalConsentNameOfInterviewer = "HIV6wks_14ParentalConsentNameOfInterviewer";
    private static final String HIVwks_14ParentalConsentInterviewerID = "HIV6wks_14ParentalConsentInterviewerID";
    private static final String HIVwks_14ParentalConsentParentID = "HIV6wks_14ParentalConsentParentID";
    private static final String HIV6wks_14ParentalConsentDate = "HIV6wks_14ParentalConsentDate";

    private static final String HIV10_17ParentalConsentQ1 = "HIV10_17ParentalConsentQ1";
    private static final String HIV10_17ParentalConsentQ2 = "HIV10_17ParentalConsentQ2";
    private static final String HIV10_17ParentalConsentQ3 = "HIV10_17ParentalConsentQ3";
    private static final String HIVChild10_17ParentalConsentQ4 = "HIVChild10_17ParentalConsentQ4";
    private static final String HIV10_17ParentalConsentNameOfInterviewer = "HIV10_17ParentalConsentNameOfInterviewer";
    private static final String HIV10_17ParentalConsentInterviewerID = "HIV10_17ParentalConsentInterviewerID";
    private static final String HIV10_17ParentalConsentParentID = "HIV10_17ParentalConsentParentID";
    private static final String HIV10_17ParentalConsentDate = "HIV10_17ParentalConsentDate";



    private static final String HIVAssent10_17Q1 = "HIVAssent10_17Q1";
    private static final String HIVAssent10_17Q2 = "HIVAssent10_17Q2";
    private static final String HIVAssent10_17Q3 = "HIVAssent10_17Q3";
    private static final String HIVAssent10_17Q4 = "HIVAssent10_17Q4";
    private static final String HIVAssent10_17NameOfInterviewer = "HIVAssent10_17NameOfInterviewer";
    private static final String HIVAssent10_17InterviewerID = "HIVAssent10_17InterviewerID";
    private static final String HIVAssent10_17ParticipantsID = "HIVAssent10_17ParticipantsID";
    private static final String HIVAssent10_17Date = "HIVAssent10_17Date";
    private static final String HIVConsentAdults18_64Q1 = "HIVConsentAdults18_64Q1";
    private static final String HIVConsentAdults18_64Q2 = "HIVConsentAdults18_64Q2";
    private static final String HIVConsentAdults18_64Q3 = "HIVConsentAdults18_64Q3";
    private static final String HIVConsentAdults18_64Q4 = "HIVConsentAdults18_64Q4";
    private static final String HIVConsentAdults18_64NameOfInterviewer = "HIVConsentAdults18_64NameOfInterviewer";
    private static final String HIVConsentAdults18_64InterviewerID = "HIVConsentAdults18_64InterviewerID";
    private static final String HIVConsentAdults18_64ParticipantsID = "HIVConsentAdults18_64ParticipantsID";
    private static final String HIVConsentAdults18_64Date = "HIVConsentAdults18_64Date";
    private static final String HIVConsentAdultsOver64Q1 = "HIVConsentAdultsOver64Q1";
    private static final String HIVConsentAdultsOver64Q2 = "HIVConsentAdultsOver64Q2";
    private static final String HIVConsentAdultsOver64Q3 = "HIVConsentAdultsOver64Q3";
    private static final String HIVConsentAdultsOver64Q4 = "HIVConsentAdultsOver64Q4";
    private static final String HIVConsentAdultsOver64NameOfInterviewer = "HIVConsentAdultsOver64NameOfInterviewer";
    private static final String HIVConsentAdultsOver64InterviewerID = "HIVConsentAdultsOver64InterviewerID";
    private static final String HIVConsentAdultsOver64ParticipantsID = "HIVConsentAdultsOver64ParticipantsID";
    private static final String HIVConsentAdultsOver64Date = "HIVConsentAdultsOver64Date";




    private static final String sample = "Sample";
    private static final String PK = "PK";
    private static final String Region = "Region";
    private static final String StratumNo = "StratumNo";
    private static final String DistrictCode = "DistrictCode";
    private static final String VillageCode = "VillageCode";
    private static final String LocalityCode = "LocalityCode";
    private static final String EACode = "EACode";
    private static final String BlockNo = "BlockNo";
    private static final String StatusCode = "StatusCode";
    private static final String DistrictName = "DistrictName";
    private static final String DistrictEAVillageLocality = "DistrictEAVillageLocality";
    private static final String LocalityType = "LocalityType";
    private static final String NoofHholds = "NoofHholds";
    private static final String STATUS = "STATUS";
    private static final String TBculture = "TBculture";
    private static final String AssignmentGenerated = "AssignmentGenerated";


    // Table Create Statements
    // AspNetUsers table create statement


    // statements that creates tables
    private static final String Create_Table_Users = "CREATE TABLE " + tblusers + "(" + Code
            + " nvarchar(3) PRIMARY KEY NOT NULL,"
            + password + " nvarchar(50) NOT NULL,"
            + Name + " nvarchar(50),"
            + SName + " nvarchar(50),"
            + Contact + " nvarchar(50),"
            + QC_Code + " nvarchar(3),"
            + Super_Code + " nvarchar(3), "
            + Role + " nvarchar (2), "
            + HQ_Code + " nvarchar(3))";

    // create sample table
    private static final String Create_Table_sample = "CREATE TABLE " + sample + "("
            + PK + " varchar(50)primary key NOT NULL,"
            + Region + " varchar(50),"
            + StratumNo + " varchar(50),"
            + DistrictCode + " varchar(50),"
            + VillageCode + " varchar(50),"
            + LocalityCode + " varchar(50),"
            + EACode + " varchar(50),"
            + BlockNo + " varchar(50),"
            + StatusCode + " varchar(50),"
            + DistrictName + " varchar(100),"
            + DistrictEAVillageLocality + " varchar(100),"
            + LocalityType + " varchar(50),"
            + NoofHholds + " varchar(50),"
            + STATUS + " varchar(50),"
            + TBculture + " varchar(50),"
            + AssignmentGenerated + " varchar(1))";

// create EA_Assignment table
    private static final String Create_Table_Assignments = "CREATE TABLE " + tblAssignments + "("
            + EA_Assignment_ID + " uniqueidentifier primary key NOT NULL,"
            + STRATUM + " nvarchar(2) NOT NULL,"
            + DISTRICT + " nvarchar(2) NOT NULL,"
            + VILLAGE + " nvarchar(2) NOT NULL,"
            + LOCALITY + " nvarchar(3) NOT NULL,"
            + EA + " nvarchar(4) NOT NULL,"
            + BLOCK_NO + " nvarchar(3) NOT NULL,"
            + EA_STATUS + " nvarchar(1) NOT NULL,"
            + Sample_FK + " nvarchar(4) NOT NULL,"
            + Supervisor + " nvarchar(3) NOT NULL,"
            + ROUND_NUMBER + " nvarchar(2) NOT NULL)";



    //create household table
    private static final String Create_Table_Household = "CREATE TABLE " + tblhousehold + "("

            + BatchNumber + " uniqueidentifier  NOT NULL,"
            + DWELLING_NO + " nvarchar(5),"
            + HH_NO + " nvarchar(3),"
            + RESP_LINE + " nvarchar(2),"
            + ENUMERATOR + " nvarchar(3),"
            + SUPERVISOR + " nvarchar(3),"
            + QUALITY_CONTROLLER + " nvarchar(3),"
            + INTERVIEWER_VISITS1 + " nvarchar(1),"
            + DATE1 + " datetime,"
            + VISIT1_RESULT + " nvarchar(1),"
            + COMMENT1 + " nvarchar(500),"
            + NEXT_VISIT_2_DATE + " date,"
            + NEXT_VISIT_2_Time + " time,"
            + INTERVIEWER_VISITS2 + " nvarchar(1),"
            + DATE2 + " datetime,"
            + VISIT2_RESULT + " nvarchar(1),"
            + COMMENT2 + " nvarchar(500),"
            + NEXT_VISIT_3_DATE + " date,"
            + NEXT_VISIT_3_Time + " time,"
            + INTERVIEWER_VISITS3 + " nvarchar(1),"
            + DATE3 + " datetime,"
            + VISIT3_RESULT + " nvarchar(1),"
            + COMMENT_3 + " nvarchar(500),"
            + TOTAL_VISITS + " nvarchar(1),"
            + Sample_FK + " nvarchar(4),"
            + HH_Assignment_ID + " uniqueidentifier  NOT NULL,"
            + CONSENT + " nvarchar(1),"
            + CHECKED_BY + " nvarchar(50),"
            + CODED + " nvarchar(50),"
            + FINAL_RESULT + " nvarchar(1),"
            + FINAL_OTHER + " nvarchar(100),"
            + Interview_Status + " nvarchar(1),"
            + SuperComment + " nvarchar(500))";



    //create Persons roster table
    private static final String Create_Table_HhRoster = "CREATE TABLE " + tblhhroster + "("
            + Assignment_ID + " uniqueidentifier  NOT NULL,"
            + BatchNumberR + " nvarchar(4),"
            + SRNO + " nvarchar(2),"
            + P01 + " nvarchar(60),"
            + P02 + " nvarchar(2),"
            + P03 + " nvarchar(1),"
            + P04_YY + " nvarchar(2),"
            + P04_MM + " nvarchar(2),"
            + P04_WKS + " nvarchar(2),"
            + P05 + " nvarchar(3),"
            + P06 + " nvarchar(1),"
            + P07 + " nvarchar(2),"
            + P17 + " nvarchar(1),"
            + P18 + " nvarchar(1),"
            + P19 + " nvarchar(1),"
            + P20 + " nvarchar(1),"
            + P21 + " nvarchar(1),"
           //+ BloodSampleCollected + " nvarchar (1)  ,"
            + Barcode + " nvarchar(10),"
            //+ RapidResults + " nvarchar (1) ,"
            //+ RapidDate + " date,"
            + B3_RapidConsent_Yes_No + " nvarchar(1),"
            + B3_Guardian + " nvarchar(50),"
            + B3_Date + " date,"
            + U15Rapid_Result + " nvarchar(1),"
            + Rapid_Comment + " nvarchar(100))";


    //create table consents

    /*
    private static final String Create_Table_Consent = "CREATE TABLE " + tblConsents + "("
    + BatchNumberC + " nvarchar(3),"
+ SRNOC + " nvarchar(3),"
+ BarcodeC+ " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentQ2 + " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentQ3 + " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentQ4 + " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentNameOfInterviewer + " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentInterviewerID + " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentParentID + " nvarchar(3),"
+ HIVChild6wks_15ParentalConsentDate + " nvarchar(3),"
+ HIVAssent10_17Q1 + " nvarchar(3),"
+ HIVAssent10_17Q2 + " nvarchar(3),"
+ HIVAssent10_17Q3 + " nvarchar(3),"
+ HIVAssent10_17Q4 + " nvarchar(3),"
+ HIVAssent10_17NameOfInterviewer + " nvarchar(3),"
+ HIVAssent10_17InterviewerID + " nvarchar(3),"
+ HIVAssent10_17ParticipantsID + " nvarchar(3),"
+ HIVAssent10_17Date + " nvarchar(3),"
+ HIVConsentAdults18_64Q1 + " nvarchar(3),"
+ HIVConsentAdults18_64Q2 + " nvarchar(3),"
+ HIVConsentAdults18_64Q3 + " nvarchar(3),"
+ HIVConsentAdults18_64Q4 + " nvarchar(3),"
+ HIVConsentAdults18_64NameOfInterviewer + " nvarchar(3),"
+ HIVConsentAdults18_64InterviewerID + " nvarchar(3),"
+ HIVConsentAdults18_64ParticipantsID + " nvarchar(3),"
+ HIVConsentAdults18_64Date + " nvarchar(3),"
+ HIVConsentAdultsOver64Q1 + " nvarchar(3),"
+ HIVConsentAdultsOver64Q2 + " nvarchar(3),"
+ HIVConsentAdultsOver64Q3 + " nvarchar(3),"
+ HIVConsentAdultsOver64Q4 + " nvarchar(3),"
+ HIVConsentAdultsOver64NameOfInterviewer + " nvarchar(3),"
+ HIVConsentAdultsOver64InterviewerID + " nvarchar(3),"
+ HIVConsentAdultsOver64ParticipantsID + " nvarchar(3),"
+ HIVConsentAdultsOver64Date + " nvarchar(3))";

*/

    // create individual table
    private static final String Create_Table_individual = "CREATE TABLE " + tblindividual + "("
            + Assignment_IDi + " uniqueidentifier NOT NULL,"
            + BatchNumberi + " nvarchar(4) NOT NULL,"
            + SRNOi + " nvarchar(2) NOT NULL,"
            + Q101 + " nvarchar(1),"
            + Q102 + " nvarchar(2),"
            + Q103 + " nvarchar(1),"
            + Q104 + " nvarchar(4),"
            + Q104c + " nvarchar(4),"
            + Q104cBISCED + " nvarchar(4),"
            + Q401 + " nvarchar(1),"
            + Q402 + " nvarchar(2),"
            + Q501 + " nvarchar(1),"
            + Q502 + " nvarchar(1),"
            + Q503 + " nvarchar(1),"
            + Q402a + " nvarchar(1),"
            + Q402b + " nvarchar(1),"
            + Q403 + " nvarchar(1),"
            + Q504_1 + " nvarchar(1),"
            + Q504_2 + " nvarchar(1),"
            + Q504_3 + " nvarchar(1),"
            + Q504_4 + " nvarchar(1),"
            + Q504_5 + " nvarchar(1),"
            + Q504_6 + " nvarchar(1),"
            + Q504_7 + " nvarchar(1),"
            + Q504_8 + " nvarchar(1),"
            + Q504_10 + " nvarchar(1),"
            + Q504_Other + " nvarchar(1),"
            + Q504_OtherSpecify + " nvarchar(100),"
          + Q904 + " nvarchar(1),"
            + Q1101 + " nvarchar(1),"
            + Q1101a + " nvarchar(1),"
            + Q1101aOther + " nvarchar(100),"
            + Q1102 + " nvarchar(1),"
            + Q1102a + " nvarchar(4),"
            + Q1103 + " nvarchar(1),"
            + Q1103aDD + " nvarchar(4),"
            + Q1103aWks + " nvarchar(4),"
            + Q1103aDontKnow + " nvarchar(4),"
            + Q1104 + " nvarchar(1),"
            + Q1105 + " nvarchar(1),"
            + Q1106 + " nvarchar(1),"
            + Q1106a + " nvarchar(1),"
            + Q1106b + " nvarchar(1),"
            + Q1106bOther + " nvarchar(100),"
            + Q1107 + " nvarchar(1),"
            + Q1107aDD + " nvarchar(4),"
            + Q1107aWks + " nvarchar(4),"
            + Q1107aDontKnow + " nvarchar(4),"
            + Q1108 + " nvarchar(1),"
            + Q1108aDD + " nvarchar(4),"
            + Q1108aWks + " nvarchar(4),"
            + Q1108aDontKnow + " nvarchar(4),"
            + Q1109 + " nvarchar(1),"
            + Q1110 + " nvarchar(1),"
            + Q1111 + " nvarchar(1),"
            + Q1111Other + " nvarchar(50),"
            + Q1112 + " nvarchar(1),"
            + Q1112Other + " nvarchar(50),"
            + Q1113 + " nvarchar(1),"
            + Q1113Other + " nvarchar(50),"
            + Q1114 + " nvarchar(1),"
           // + IndBloodSampleCollected + " nvarchar (1) ,"
           + IndBarcode + " nvarchar(20),"
           // + IndRapidResults + " nvarchar (1) ,"
           // + IndRapidDate + " date,"

            + B8_Yes_No + " nvarchar(1),"
            + B8_Date + " datetime,"
            + B8_O15_Rapid + " nvarchar(1),"
            + Q801f + " nvarchar(1),"
            + IndRapid_Comment + " nvarchar(100))";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Log.d("Creating: ","Passed");
            createTables(db);

        }catch (Exception r){
            Log.d("Error: ",r.toString());
        }




    }


    public void dropTables(SQLiteDatabase db) {

      db.execSQL("DROP TABLE IF EXISTS "+tblusers);
      db.execSQL("DROP TABLE IF EXISTS "+tblAssignments);
       db.execSQL("DROP TABLE IF EXISTS " + tblhousehold);
       db.execSQL("DROP TABLE IF EXISTS " + tblhhroster);
      db.execSQL("DROP TABLE IF EXISTS " + tblindividual);
      db.execSQL("DROP TABLE IF EXISTS "+tblindividual);

    }

    public void createTables(SQLiteDatabase db) {
       db.execSQL(Create_Table_Users);
       db.execSQL(Create_Table_sample);
        db.execSQL(Create_Table_Assignments);
       db.execSQL(Create_Table_Household);
       db.execSQL(Create_Table_HhRoster);
       db.execSQL(Create_Table_individual);


    }

    public void  insertTables(SQLiteDatabase db) {

     // db.execSQL(Insert_EA);
      //db.execSQL(Insert_househol);
      // db.execSQL(Insert_household);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        onCreate(db);
    }




    public boolean insertEAAssignment( Assignments assign) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues eaassignmentstValues = new ContentValues();
        //contentValues.put("UserId", UserId);
        eaassignmentstValues.put("EA_Assignment_ID",assign.getAssignment_ID());
        eaassignmentstValues.put("STRATUM", assign.getStratum_code());
        eaassignmentstValues.put("DISTRICT", assign.getDistrict_code());
        eaassignmentstValues.put("VILLAGE", assign.getVillage_code());
        eaassignmentstValues.put("LOCALITY", assign.getLocality_code());
        eaassignmentstValues.put("EA", assign.getEA_code());
        eaassignmentstValues.put("BLOCK_NO", assign.getBlock_number());
        eaassignmentstValues.put("EA_STATUS", assign.getEA_Status());
        eaassignmentstValues.put("Sample_FK", assign.getSample_FK());
        eaassignmentstValues.put("Supervisor", assign.getSample_FK());
        eaassignmentstValues.put("ROUND_NUMBER", assign.getROUND_NUMBER());




        db.insert("EA_Assignments", null, eaassignmentstValues);
        return true;
    }



    public boolean inserthousehold(HouseHold house) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BatchNumber, house.getBatchNumber());
        contentValues.put(DWELLING_NO, house.getDWELLING_NO());
        contentValues.put(HH_NO, house.getHH_NO());
        contentValues.put(ENUMERATOR, house.getENUMERATOR());
        contentValues.put(SUPERVISOR, house.getSUPERVISOR());
        contentValues.put(QUALITY_CONTROLLER, house.getQUALITY_CONTROLLER());
        contentValues.put(INTERVIEWER_VISITS1, house.getINTERVIEWER_VISITS1());
        contentValues.put(DATE1, house.getDATE1());
        contentValues.put(VISIT1_RESULT, house.getVISIT1_RESULT());
        contentValues.put(COMMENT1, house.getCOMMENT1());
        contentValues.put(NEXT_VISIT_2_DATE, house.getNEXT_VISIT_2_DATE());
        contentValues.put(NEXT_VISIT_2_Time, house.getNEXT_VISIT_2());
        contentValues.put(INTERVIEWER_VISITS2, house.getINTERVIEWER_VISITS2());
        contentValues.put(DATE2, house.getDATE2());
        contentValues.put(VISIT2_RESULT, house.getVISIT2_RESULT());
        contentValues.put(COMMENT2, house.getCOMMENT2());
        contentValues.put(NEXT_VISIT_3_DATE, house.getNEXT_VISIT_3_DATE());
        contentValues.put(NEXT_VISIT_3_Time, house.getNEXT_VISIT_3());
        contentValues.put(INTERVIEWER_VISITS3, house.getINTERVIEWER_VISITS3());
        contentValues.put(DATE3, house.getDATE3());
        contentValues.put(VISIT3_RESULT, house.getVISIT3_RESULT());
        contentValues.put(COMMENT_3, house.getCOMMENT_3());
        contentValues.put(TOTAL_VISITS, house.getTOTAL_VISITS());
        contentValues.put(Sample_FK, house.getSample_FK());
        contentValues.put(EA_Assignment_ID, house.getAssignment_ID());
        contentValues.put(CONSENT, house.getCONSENT());
        contentValues.put(CHECKED_BY, house.getCHECKED_BY());
        contentValues.put(CODED, house.getCODED());
        contentValues.put(FINAL_RESULT, house.getFINAL_RESULT());
        contentValues.put(FINAL_OTHER, house.getFINAL_OTHER());
        contentValues.put(Interview_Status, house.getInterview_Status());
        contentValues.put(SuperComment, house.getSuperComment());

        db.insert(tblhousehold, null, contentValues);
        return true;


    }

    //INSERT ROSTER FIRST SYNC
    public boolean insertSyncRoster(PersonRoster pr,String AsID,String Batch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhrosterValues = new ContentValues();

            hhrosterValues.put(EA_Assignment_ID,AsID);
            hhrosterValues.put(BatchNumberR,Batch );
            hhrosterValues.put(SRNO, pr.getLineNumber());
            hhrosterValues.put(P01, pr.getP01());
            hhrosterValues.put(P02,  pr.getP02());
            hhrosterValues.put(P03,   pr.getP03());
            hhrosterValues.put(P04_YY,  pr.getP04YY());
            hhrosterValues.put(P04_MM, pr.getP04MM());
            hhrosterValues.put(P04_WKS,pr.getP04WKS());
            hhrosterValues.put(P05, pr.getP05());
            hhrosterValues.put(P06, pr.getP06());
            hhrosterValues.put(P07, pr.getP07());
            hhrosterValues.put(P17, pr.getP17());
            hhrosterValues.put(P18, pr.getP18());
            hhrosterValues.put(P19,pr.getP19());
            hhrosterValues.put(P20, pr.getP20());
            hhrosterValues.put(P21, pr.getP21());
            //B3_RapidConsent_Yes_No
            //B3_Guardian
            //B3_Date_Consent
           // hhrosterValues.put(BloodSampleCollected, pr.getBloodSampleCollected());
            hhrosterValues.put(Barcode, pr.getBarcode());
            //hhrosterValues.put(RapidResults, pr.getRapidResults());
            //hhrosterValues.put(RapidDate, pr.getRapidDate());
            //hhrosterValues.put(Rapid_Comment, pr.getRapid_Comment());

            hhrosterValues.put(B3_RapidConsent_Yes_No, pr.getB3_RapidConsent_Yes_No());
            hhrosterValues.put(B3_Guardian, pr.getB3_Guardian());
            hhrosterValues.put(B3_Date, pr.getB3_Date());
            hhrosterValues.put(U15Rapid_Result, pr.getU15Rapid_Results());
           // hhrosterValues.put(RapidDate, pr.getRapidDate());


            db.insert(tblhhroster, null, hhrosterValues);



        return true;
    }

    //INSERT ROSTER FROM HOUSEHOLD
    public boolean insertHhroster(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues hhrosterValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        for (int i = 0; i < houseHold.getPersons().length; i++) {
            hhrosterValues.put(EA_Assignment_ID, "12350005");
            hhrosterValues.put(BatchNumberR,"1" );
            hhrosterValues.put(SRNO, houseHold.getPersons()[i].getLineNumber());
            hhrosterValues.put(P01, houseHold.getPersons()[i].getP01());
            hhrosterValues.put(P02,  houseHold.getPersons()[i].getP02());
            hhrosterValues.put(P03,   houseHold.getPersons()[i].getP03());
            hhrosterValues.put(P04_YY,  houseHold.getPersons()[i].getP04YY());
            hhrosterValues.put(P04_MM, houseHold.getPersons()[i].getP04MM());
            hhrosterValues.put(P04_WKS,houseHold.getPersons()[i].getP04WKS());
            hhrosterValues.put(P05, houseHold.getPersons()[i].getP05());
            hhrosterValues.put(P06, houseHold.getPersons()[i].getP06());
            hhrosterValues.put(P07, houseHold.getPersons()[i].getP07());
            hhrosterValues.put(P17, houseHold.getPersons()[i].getP17());
            hhrosterValues.put(P18, houseHold.getPersons()[i].getP18());
            hhrosterValues.put(P19,houseHold.getPersons()[i].getP19());
            hhrosterValues.put(P20, houseHold.getPersons()[i].getP20());
            hhrosterValues.put(P21, houseHold.getPersons()[i].getP21());
           // hhrosterValues.put(BloodSampleCollected, houseHold.getPersons()[i].getBloodSampleCollected());
            hhrosterValues.put(Barcode, houseHold.getPersons()[i].getBarcode());
           // hhrosterValues.put(RapidResults, houseHold.getPersons()[i].getRapidResults());
            //hhrosterValues.put(RapidDate, houseHold.getPersons()[i].getRapidDate());


            hhrosterValues.put(B3_RapidConsent_Yes_No, houseHold.getPersons()[i].getB3_RapidConsent_Yes_No());
            hhrosterValues.put(B3_Guardian, houseHold.getPersons()[i].getB3_Guardian());
            hhrosterValues.put(B3_Date, houseHold.getPersons()[i].getB3_Date());
            hhrosterValues.put(U15Rapid_Result, houseHold.getPersons()[i].getU15Rapid_Results());
           // hhrosterValues.put(RapidDate, houseHold.getPersons()[i].getRapidDate());
            hhrosterValues.put(Rapid_Comment, houseHold.getPersons()[i].getRapid_Comment());

            db.insert(tblhhroster, null, hhrosterValues);

        }

        return true;
    }


    public boolean updateHhroster(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhrosterValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        for (int i = 0; i < houseHold.getPersons().length; i++) {
            hhrosterValues.put(EA_Assignment_ID, houseHold.getAssignment_ID());
            hhrosterValues.put(BatchNumberR, houseHold.getBatchNumber() );
            hhrosterValues.put(SRNO, houseHold.getPersons()[i].getLineNumber());
            hhrosterValues.put(P01, houseHold.getPersons()[i].getP01());
            hhrosterValues.put(P02,  houseHold.getPersons()[i].getP02());
            hhrosterValues.put(P03,   houseHold.getPersons()[i].getP03());
            hhrosterValues.put(P04_YY,  houseHold.getPersons()[i].getP04YY());
            hhrosterValues.put(P04_MM, houseHold.getPersons()[i].getP04MM());
            hhrosterValues.put(P04_WKS,houseHold.getPersons()[i].getP04WKS());
            hhrosterValues.put(P05, houseHold.getPersons()[i].getP05());
            hhrosterValues.put(P06, houseHold.getPersons()[i].getP06());
            hhrosterValues.put(P07, houseHold.getPersons()[i].getP07());
            hhrosterValues.put(P17, houseHold.getPersons()[i].getP17());
            hhrosterValues.put(P18, houseHold.getPersons()[i].getP18());
            hhrosterValues.put(P19,houseHold.getPersons()[i].getP19());
            hhrosterValues.put(P20, houseHold.getPersons()[i].getP20());
            hhrosterValues.put(P21, houseHold.getPersons()[i].getP21());
            // hhrosterValues.put(BloodSampleCollected, houseHold.getPersons()[i].getBloodSampleCollected());
            hhrosterValues.put(Barcode, houseHold.getPersons()[i].getBarcode());
            // hhrosterValues.put(RapidResults, houseHold.getPersons()[i].getRapidResults());
            //hhrosterValues.put(RapidDate, houseHold.getPersons()[i].getRapidDate());


            hhrosterValues.put(B3_RapidConsent_Yes_No, houseHold.getPersons()[i].getB3_RapidConsent_Yes_No());
            hhrosterValues.put(B3_Guardian, houseHold.getPersons()[i].getB3_Guardian());
            hhrosterValues.put(B3_Date, houseHold.getPersons()[i].getB3_Date());
            hhrosterValues.put(U15Rapid_Result, houseHold.getPersons()[i].getU15Rapid_Results());
            // hhrosterValues.put(RapidDate, houseHold.getPersons()[i].getRapidDate());
            hhrosterValues.put(Rapid_Comment, houseHold.getPersons()[i].getRapid_Comment());
             i = db.update(tblhhroster, // table
                     hhrosterValues, // column/value
                    "Assignment_ID = ?", // selections
                    new String[] { String.valueOf(houseHold.getAssignment_ID()) });

            db.close();


        }

        return true;
    }

    //FROM HOUSE HOLD
    public boolean insertIndividual(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues individualValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        for (int i = 0; i < houseHold.getPersons().length; i++) {
            individualValues.put(BatchNumberi, houseHold.getBatchNumber());
            individualValues.put(Assignment_IDi, houseHold.getAssignment_ID());
            individualValues.put(SRNOi, houseHold.getPersons()[i].getLineNumber());
            individualValues.put(IndBarcode, houseHold.getIndividual()[i].getIndBarcode() );
            individualValues.put(Q101, houseHold.getIndividual()[i].getQ101());
            individualValues.put(Q102, houseHold.getIndividual()[i].getQ102() );
            individualValues.put(Q103, houseHold.getIndividual()[i].getQ103() );
            individualValues.put(Q104, houseHold.getIndividual()[i].getQ104() );
            individualValues.put(Q104c, houseHold.getIndividual()[i].getQ104c() );
            individualValues.put(Q104cBISCED, houseHold.getIndividual()[i].getQ104cBISCED());
            individualValues.put(Q401, houseHold.getIndividual()[i].getQ401() );
            individualValues.put(Q402,  houseHold.getIndividual()[i].getQ402() );
            individualValues.put(Q402a, houseHold.getIndividual()[i].getQ402a() );
            individualValues.put(Q402b,  houseHold.getIndividual()[i].getQ402b() );
            individualValues.put(Q403,  houseHold.getIndividual()[i].getQ403() );
            individualValues.put(Q501, houseHold.getIndividual()[i].getQ501() );
            individualValues.put(Q502,  houseHold.getIndividual()[i].getQ502() );
            individualValues.put(Q503, houseHold.getIndividual()[i].getQ503() );
            individualValues.put(Q504_1,  houseHold.getIndividual()[i].getQ504_1() );
            individualValues.put(Q504_2,  houseHold.getIndividual()[i].getQ504_2() );
            individualValues.put(Q504_3,  houseHold.getIndividual()[i].getQ504_3() );
            individualValues.put(Q504_4,  houseHold.getIndividual()[i].getQ504_4() );
            individualValues.put(Q504_5,  houseHold.getIndividual()[i].getQ504_5() );
            individualValues.put(Q504_6,  houseHold.getIndividual()[i].getQ504_6() );
            individualValues.put(Q504_7,  houseHold.getIndividual()[i].getQ504_7() );
            individualValues.put(Q504_8,  houseHold.getIndividual()[i].getQ504_8() );
            individualValues.put(Q504_10,  houseHold.getIndividual()[i].getQ504_10() );
            individualValues.put(Q504_Other,  houseHold.getIndividual()[i].getQ504_Other() );
            individualValues.put(Q504_OtherSpecify,  houseHold.getIndividual()[i].getQ504_OtherSpecify() );
            individualValues.put(Q904,  houseHold.getIndividual()[i].getQ904() );
            individualValues.put(Q1101, houseHold.getIndividual()[i].getQ1101() );
            individualValues.put(Q1101a, houseHold.getIndividual()[i].getQ1101a() );
            individualValues.put(Q1101aOther,  houseHold.getIndividual()[i].getQ1101aOther() );
            individualValues.put(Q1102, houseHold.getIndividual()[i].getQ1102() );
            individualValues.put(Q1102a,  houseHold.getIndividual()[i].getQ1102a() );
            individualValues.put(Q1103,  houseHold.getIndividual()[i].getQ1103());
            individualValues.put(Q1103aDD, houseHold.getIndividual()[i].getQ1103aDD() );
            individualValues.put(Q1103aWks, houseHold.getIndividual()[i].getQ1103aWks() );
            individualValues.put(Q1103aDontKnow, houseHold.getIndividual()[i].getQ1103aDontKnow() );
            individualValues.put(Q1104,  houseHold.getIndividual()[i].getQ1104() );
            individualValues.put(Q1105, houseHold.getIndividual()[i].getQ1105() );
            individualValues.put(Q1106,  houseHold.getIndividual()[i].getQ1106() );
            individualValues.put(Q1106a,  houseHold.getIndividual()[i].getQ1106a() );
            individualValues.put(Q1106b,  houseHold.getIndividual()[i].getQ1106b() );
            individualValues.put(Q1106bOther,  houseHold.getIndividual()[i].getQ1106bOther() );
            individualValues.put(Q1107,  houseHold.getIndividual()[i].getQ1107() );
            individualValues.put(Q1107aDD,  houseHold.getIndividual()[i].getQ1107aDD() );
            individualValues.put(Q1107aWks,  houseHold.getIndividual()[i].getQ1107aWks() );
            individualValues.put(Q1107aDontKnow,  houseHold.getIndividual()[i].getQ1107aDontKnow() );
            individualValues.put(Q1108,  houseHold.getIndividual()[i].getQ1108() );
            individualValues.put(Q1108aDD, houseHold.getIndividual()[i].getQ1108aDD() );
            individualValues.put(Q1108aWks, houseHold.getIndividual()[i].getQ1108aWks() );
            individualValues.put(Q1108aDontKnow, houseHold.getIndividual()[i].getQ1108aDontKnow() );
            individualValues.put(Q1109, houseHold.getIndividual()[i].getQ1109() );
            individualValues.put(Q1110,  houseHold.getIndividual()[i].getQ1110() );
            individualValues.put(Q1111, houseHold.getIndividual()[i].getQ1111() );
            individualValues.put(Q1111Other, houseHold.getIndividual()[i].getQ1111Other() );
            individualValues.put(Q1112, houseHold.getIndividual()[i].getQ1112() );
            individualValues.put(Q1112Other,  houseHold.getIndividual()[i].getQ1112_Other() );
            individualValues.put(Q1113, houseHold.getIndividual()[i].getQ1113() );
            individualValues.put(Q1113Other, houseHold.getIndividual()[i].getQ1113Other() );
            individualValues.put(Q1114, houseHold.getIndividual()[i].getQ1114() );
           // individualValues.put(IndBloodSampleCollected, houseHold.getIndividual()[i].getIndBloodSampleCollected() );
            individualValues.put(B8_Yes_No, houseHold.getIndividual()[i].getB8_Yes_No() );
            individualValues.put(B8_Date, houseHold.getIndividual()[i].getB8_Date() );
            individualValues.put(B8_O15_Rapid, houseHold.getIndividual()[i].getB8_O15_Rapid() );
            individualValues.put(Q801f, houseHold.getIndividual()[i].getQ801f() );
            //individualValues.put(IndRapidResults, houseHold.getIndividual()[i].getIndRapidResults() );
           // individualValues.put(IndRapidDate, houseHold.getIndividual()[i].getIndRapidDate() );

            individualValues.put(IndRapid_Comment, houseHold.getIndividual()[i].getIndRapid_Comment() );


            db.insert("tblindividual", null, individualValues);

        }

        return true;
    }

    //INSERT INDIVIDUAL FROM SYNC
    public boolean insertSyncIndividual(Individual ind, String AsID,String Batch,int SRNO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues individualValues = new ContentValues();
        //contentValues.put("UserId", UserId);


            individualValues.put(BatchNumberi, Batch);
            individualValues.put(Assignment_IDi, AsID);
            individualValues.put(SRNOi, SRNO);
            individualValues.put(IndBarcode, ind.getIndBarcode() );
            individualValues.put(Q101, ind.getQ101());
            individualValues.put(Q102, ind.getQ102() );
            individualValues.put(Q103, ind.getQ103() );
            individualValues.put(Q104, ind.getQ104() );
            individualValues.put(Q104c, ind.getQ104c() );
            individualValues.put(Q104cBISCED, ind.getQ104cBISCED());
            individualValues.put(Q401, ind.getQ401() );
            individualValues.put(Q402,  ind.getQ402() );
            individualValues.put(Q402a, ind.getQ402a() );
            individualValues.put(Q402b,  ind.getQ402b() );
            individualValues.put(Q403,  ind.getQ403() );
            individualValues.put(Q501, ind.getQ501() );
            individualValues.put(Q502,  ind.getQ502() );
            individualValues.put(Q503, ind.getQ503() );
            individualValues.put(Q504_1,  ind.getQ504_1() );
            individualValues.put(Q504_2,  ind.getQ504_2() );
            individualValues.put(Q504_3,  ind.getQ504_3() );
            individualValues.put(Q504_4,  ind.getQ504_4() );
            individualValues.put(Q504_5,  ind.getQ504_5() );
            individualValues.put(Q504_6,  ind.getQ504_6() );
            individualValues.put(Q504_7,  ind.getQ504_7() );
            individualValues.put(Q504_8,  ind.getQ504_8() );
            individualValues.put(Q504_10,  ind.getQ504_10() );
            individualValues.put(Q504_Other,  ind.getQ504_Other() );
            individualValues.put(Q504_OtherSpecify,  ind.getQ504_OtherSpecify() );
            individualValues.put(Q904,  ind.getQ904() );


            individualValues.put(Q1101, ind.getQ1101() );
            individualValues.put(Q1101a, ind.getQ1101a() );
            individualValues.put(Q1101aOther,  ind.getQ1101aOther() );
            individualValues.put(Q1102, ind.getQ1102() );
            individualValues.put(Q1102a,  ind.getQ1102a() );
            individualValues.put(Q1103,  ind.getQ1103());
            individualValues.put(Q1103aDD, ind.getQ1103aDD() );
            individualValues.put(Q1103aWks, ind.getQ1103aWks() );
            individualValues.put(Q1103aDontKnow, ind.getQ1103aDontKnow() );
            individualValues.put(Q1104,  ind.getQ1104() );
            individualValues.put(Q1105, ind.getQ1105() );
            individualValues.put(Q1106,  ind.getQ1106() );
            individualValues.put(Q1106a,  ind.getQ1106a() );
            individualValues.put(Q1106b,  ind.getQ1106b() );
            individualValues.put(Q1106bOther,  ind.getQ1106bOther() );
            individualValues.put(Q1107,  ind.getQ1107() );
            individualValues.put(Q1107aDD,  ind.getQ1107aDD() );
            individualValues.put(Q1107aWks,  ind.getQ1107aWks() );
            individualValues.put(Q1107aDontKnow,  ind.getQ1107aDontKnow() );
            individualValues.put(Q1108,  ind.getQ1108() );
            individualValues.put(Q1108aDD, ind.getQ1108aDD() );
            individualValues.put(Q1108aWks, ind.getQ1108aWks() );
            individualValues.put(Q1108aDontKnow, ind.getQ1108aDontKnow() );
            individualValues.put(Q1109, ind.getQ1109() );
            individualValues.put(Q1110,  ind.getQ1110() );
            individualValues.put(Q1111, ind.getQ1111() );
            individualValues.put(Q1111Other, ind.getQ1111Other() );
            individualValues.put(Q1112, ind.getQ1112() );
            individualValues.put(Q1112Other,  ind.getQ1112_Other() );
            individualValues.put(Q1113, ind.getQ1113() );
            individualValues.put(Q1113Other, ind.getQ1113Other() );
            individualValues.put(Q1114, ind.getQ1114() );
            //individualValues.put(IndBloodSampleCollected, ind.getIndBloodSampleCollected() );
        individualValues.put(B8_Yes_No, ind.getB8_Yes_No() );
        individualValues.put(B8_Date, ind.getB8_Date() );
        individualValues.put(B8_O15_Rapid, ind.getB8_O15_Rapid() );
        individualValues.put(Q801f, ind.getQ801f() );

            //individualValues.put(IndRapidResults, ind.getIndRapidResults() );
           // individualValues.put(IndRapidDate, ind.getIndRapidDate() );
            individualValues.put(IndRapid_Comment, ind.getIndRapid_Comment() );

            //B8_Yes_No
            //B8_Date
            //B8_O15Rapid
            //Q801f

            db.insert("Individual", null, individualValues);



        return true;
    }

    //INSERT Sample
    public boolean insertSyncSample(Sample sample) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhrosterValues = new ContentValues();

        hhrosterValues.put(PK,sample.getPK());
        hhrosterValues.put(Region,sample.getRegion() );
        hhrosterValues.put(StratumNo, sample.getStratumNo());
        hhrosterValues.put(DistrictCode, sample.getDistrictCode());
        hhrosterValues.put(VillageCode, sample.getVillageCode());
        hhrosterValues.put(LocalityCode,   sample.getLocalityCode());
        hhrosterValues.put(EACode,  sample.getEACode());
        hhrosterValues.put(BlockNo, sample.getBlockNo());
        hhrosterValues.put(StatusCode,sample.getStatusCode());
        hhrosterValues.put(DistrictName, sample.getDistrictName());
        hhrosterValues.put(DistrictEAVillageLocality, sample.getDistrictEAVillageLocality());
        hhrosterValues.put(LocalityType, sample.getLocalityType());
        hhrosterValues.put(NoofHholds, sample.getNoofHholds());
        hhrosterValues.put(STATUS, sample.getSTATUS());
        hhrosterValues.put(TBculture,sample.getTBculture());
        hhrosterValues.put(AssignmentGenerated, sample.getAssignmentGenerated());


        db.insert("Sample", null, hhrosterValues);


        return true;
    }


        /////date format
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void getAllData(SQLiteDatabase db) {

        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);
        Cursor res = db.rawQuery("select * from " + tblAssignments, null);
        //return res;

        if(res.getCount() == 0) {
            // show message
            //myDB.showMessage("Error","Nothing found");
            Log.d("DB Number of Rows: ", String.valueOf(res.getCount()));
            return;
        }



        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("AssgnID:"+ res.getString(0)+"\n");
            buffer.append("BatchNO:"+ res.getString(1)+"\n");
            buffer.append("District:"+ res.getString(2)+"\n");
            buffer.append("village:"+ res.getString(3)+"\n");
            buffer.append("locality:"+ res.getString(4)+"\n");
            buffer.append("EA:"+ res.getString(5)+"\n");
            buffer.append("Block:"+ res.getString(6)+"\n");
            buffer.append("Ea_status:"+ res.getString(7)+"\n");
        }
        Log.d("DB EA_Assgnments: " , buffer.toString());
        // Show all data
        /// myDB.showMessage("Data",buffer.toString());
    }

    public Cursor getHhroster(String table) {

        //db = this.getWritableDatabase();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);
        Cursor res = db.rawQuery("select * from " + table, null);
        //return res;

        if (res.getCount() == 0) {
            // show message
            //myDB.showMessage("Error","Nothing found");
            Log.d("DB Number of Rows: ", String.valueOf(res.getCount()));
            //return res;
        }
        return res;
    }

    public ArrayList<String> getEA(SQLiteDatabase db) {
    ArrayList<String> a= new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);
        Cursor res = db.rawQuery("select * from " + tblAssignments, null);
        //return res;



        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            //a.add("EA NO: "+res.getString(4));
           a.add("EA NO: "+res.getString(5)+ "#"+res.getString(0)+"#"+res.getString(1)+"#"+res.getString(2)+"#"+res.getString(3)+"#"+res.getString(4)+"#"+res.getString(6)+"#"+res.getString(7));
            Log.d("Broken: ", "EA NO: "+res.getString(5)+ "#"+res.getString(0)+"#"+res.getString(1)+"#"+res.getString(2)+"#"+res.getString(3)+"#"+res.getString(4)+"#"+res.getString(6)+"#"+res.getString(7));
        }
        return a;
    }

    public ArrayList<HouseHold> getHouseHold(SQLiteDatabase db,String EA_No,String assgmnt) {
        ArrayList<HouseHold> a= new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);
        Cursor res = db.rawQuery("select * from " + tblhousehold, null);

        HouseHold buffer = new HouseHold(null,0,0);
        while (res.moveToNext())
        {
            if(res.getString(25).equals(assgmnt) && res.getString(31).equals("3"))
            {
                buffer.setBatchNumber(res.getString(0));
                buffer.setAssignment_ID(res.getString(25));
                buffer.setENUMERATOR(res.getString(4));
                buffer.setSUPERVISOR(res.getString(5));
                buffer.setQUALITY_CONTROLLER(res.getString(6));
                buffer.setInterview_Status(res.getString(31));
                a.add(buffer);
            }


        }
        return a;
    }

    public Sample getSample(SQLiteDatabase db,String assgmnt) {
        Sample s =new Sample();
        //db = this.getWritableDatabase();
        db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + tblAssignments, null);

        Assignments buffer = new Assignments();
        while (res.moveToNext())
        {
            if(res.getString(0).equals(assgmnt)){
                buffer.setSample_FK(res.getString(8));
            }


        }

        Cursor res1 = db.rawQuery("select * from Sample",null);
        while (res1.moveToNext())
        {
            if(res1.getString(0).equals(buffer.getSample_FK()))
            {
                s.setPK(res1.getString(0));
                s.setVillageCode(res1.getString(4));
                s.setDistrictEAVillageLocality(res1.getString(10));
                s.setDistrictName(res1.getString(9));
                s.setEACode(res1.getString(6));
                s.setBlockNo(res1.getString(7));
                s.setStatusCode(res1.getString(8));
                s.setDistrictCode(res1.getString(3));
                s.setLocalityType(res1.getString(11));
                s.setNoofHholds(res1.getString(12));
                s.setSTATUS(res1.getString(13));
                s.setTBculture(res1.getString(14));
                s.setStratumNo(res1.getString(2));
                s.setLocalityCode(res1.getString(5));


            }


        }

        return s;
    }



    public List<Assignments> getdataGInfo(String AssignmentID){
        // DataModel dataModel = new DataModel();
        List<Assignments> StartedHH =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblAssignments+" where EA_Assignment_ID = "+AssignmentID+ ";",null);
        StringBuffer stringBuffer = new StringBuffer();
        Assignments dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new Assignments();
            String EA_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID"));
            String STRATUM = cursor.getString(cursor.getColumnIndexOrThrow("STRATUM"));
            String DISTRICT = cursor.getString(cursor.getColumnIndexOrThrow("DISTRICT"));
            String VILLAGE = cursor.getString(cursor.getColumnIndexOrThrow("VILLAGE"));
            String LOCALITY = cursor.getString(cursor.getColumnIndexOrThrow("LOCALITY"));
            String EA = cursor.getString(cursor.getColumnIndexOrThrow("EA"));
            String BLOCK_NO = cursor.getString(cursor.getColumnIndexOrThrow("BLOCK_NO"));
            String EA_STATUS = cursor.getString(cursor.getColumnIndexOrThrow("EA_STATUS"));
            String Sample_FK = cursor.getString(cursor.getColumnIndexOrThrow("Sample_FK"));


            dataModel.setAssignment_ID(EA_Assignment_ID);
            dataModel.setStratum_code(STRATUM);
            dataModel.setDistrict_code(DISTRICT);
            dataModel.setVillage_code(VILLAGE);
            dataModel.setLocality_code(LOCALITY);
            dataModel.setEA_code(EA);
            dataModel.setBlock_number(BLOCK_NO);
            dataModel.setEA_Status(EA_STATUS);
            dataModel.setSample_FK(Sample_FK);



            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            StartedHH.add(dataModel);
        }


        return StartedHH;
    }


    public List<HouseHold> getdataHouseInfo(){
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblhousehold,null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null && cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals(3)){

            dataModel= new HouseHold();
            String HH_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("HH_Assignment_ID"));
            String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
            String DWELLING_NO = cursor.getString(cursor.getColumnIndexOrThrow("DWELLING_NO"));
            String HH_NO = cursor.getString(cursor.getColumnIndexOrThrow("HH_NO"));
            String ENUMERATOR = cursor.getString(cursor.getColumnIndexOrThrow("ENUMERATOR"));
            String SUPERVISOR = cursor.getString(cursor.getColumnIndexOrThrow("SUPERVISOR"));
            String QUALITY_CONTROLLER = cursor.getString(cursor.getColumnIndexOrThrow("QUALITY_CONTROLLER"));
            String INTERVIEWER_VISITS1 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS1"));
            String InterviewDATE1 = cursor.getString(cursor.getColumnIndexOrThrow("DATE1"));
            String VISIT1_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT1_RESULT"));
            String COMMENT1 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT1"));
           String NEXT_VISIT_2_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_DATE"));
           String NEXT_VISIT_2_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_Time"));
            String INTERVIEWER_VISITS2 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS2"));
            String InterviewDATE2 = cursor.getString(cursor.getColumnIndexOrThrow("DATE2"));
            String VISIT2_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT2_RESULT"));
            String COMMENT2 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT2"));
            String NEXT_VISIT_3_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_DATE"));
            String NEXT_VISIT_3_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_Time"));
            String INTERVIEWER_VISITS3 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS3"));
            String InterviewDATE3 = cursor.getString(cursor.getColumnIndexOrThrow("DATE3"));
            String VISIT3_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT3_RESULT"));
            String COMMENT_3 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT_3"));
            String TOTAL_VISITS = cursor.getString(cursor.getColumnIndexOrThrow("TOTAL_VISITS"));
            String Sample_FK = cursor.getString(cursor.getColumnIndexOrThrow("Sample_FK"));
            String CONSENT = cursor.getString(cursor.getColumnIndexOrThrow("CONSENT"));
            String CHECKED_BY = cursor.getString(cursor.getColumnIndexOrThrow("CHECKED_BY"));
            String CODED = cursor.getString(cursor.getColumnIndexOrThrow("CODED"));
            String FINAL_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_RESULT"));
            String FINAL_OTHER = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_OTHER"));

            dataModel.setAssignment_ID(HH_Assignment_ID);
            dataModel.setBatchNumber(BatchNumber);
            dataModel.setDWELLING_NO(DWELLING_NO);
            dataModel.setHH_NO(HH_NO);
            dataModel.setENUMERATOR(ENUMERATOR);
            dataModel.setSUPERVISOR(SUPERVISOR);
            dataModel.setQUALITY_CONTROLLER(QUALITY_CONTROLLER);
            dataModel.setINTERVIEWER_VISIT1(INTERVIEWER_VISITS1);
            dataModel.setDATE1(InterviewDATE1);
            dataModel.setVISIT1_RESULT(VISIT1_RESULT);
            dataModel.setCOMMENT1(COMMENT1);
            dataModel.setNEXT_VISIT_2_DATE(NEXT_VISIT_2_DATE);
           dataModel.setNEXT_VISIT_2(NEXT_VISIT_2_Time);
            dataModel.setINTERVIEWER_VISIT2(INTERVIEWER_VISITS2);
            dataModel.setDATE2(InterviewDATE2);
            dataModel.setVISIT2_RESULT(VISIT2_RESULT);
            dataModel.setCOMMENT2(COMMENT2);
            dataModel.setNEXT_VISIT_3_DATE(NEXT_VISIT_3_DATE);
            dataModel.setNEXT_VISIT_3(NEXT_VISIT_3_Time);
            dataModel.setINTERVIEWER_VISIT3(INTERVIEWER_VISITS3);
            dataModel.setDATE3(InterviewDATE3);
            dataModel.setVISIT3_RESULT(VISIT3_RESULT);
            dataModel.setCOMMENT3(COMMENT_3);
            dataModel.setTOTAL_VISITS(TOTAL_VISITS);
            dataModel.setCONSENT(CONSENT);
            dataModel.setCHECKED_BY(CHECKED_BY);
            dataModel.setCODED(CODED);
            dataModel.setFINAL_RESULT(FINAL_RESULT);
            dataModel.setFINAL_OTHER(FINAL_OTHER);
            dataModel.setInterview_Status(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")));

            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo:hhDetails ) {

            // Log.i("Hellomo",""+mo.getCity());
        }

        //

        return hhDetails;
    }


    /***
     *
     *
     * GET REJECTED HOUSE HOULDS FROM DB
     * @return
     */
    public List<HouseHold> getReject(){
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblhousehold,null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext())
        {
            if((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                    cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("2"))
                     )
            {

                dataModel= new HouseHold();
                String HH_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID"));
                String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
                String DWELLING_NO = cursor.getString(cursor.getColumnIndexOrThrow("DWELLING_NO"));
                String HH_NO = cursor.getString(cursor.getColumnIndexOrThrow("HH_NO"));
                String ENUMERATOR = cursor.getString(cursor.getColumnIndexOrThrow("ENUMERATOR"));
                String SUPERVISOR = cursor.getString(cursor.getColumnIndexOrThrow("SUPERVISOR"));
                String QUALITY_CONTROLLER = cursor.getString(cursor.getColumnIndexOrThrow("QUALITY_CONTROLLER"));
                String INTERVIEWER_VISITS1 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS1"));
                String InterviewDATE1 = cursor.getString(cursor.getColumnIndexOrThrow("DATE1"));
                String VISIT1_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT1_RESULT"));
                String COMMENT1 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT1"));
                String NEXT_VISIT_2_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_DATE"));
                String NEXT_VISIT_2_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_Time"));
                String INTERVIEWER_VISITS2 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS2"));
                String InterviewDATE2 = cursor.getString(cursor.getColumnIndexOrThrow("DATE2"));
                String VISIT2_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT2_RESULT"));
                String COMMENT2 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT2"));
                String NEXT_VISIT_3_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_DATE"));
                String NEXT_VISIT_3_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_Time"));
                String INTERVIEWER_VISITS3 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS3"));
                String InterviewDATE3 = cursor.getString(cursor.getColumnIndexOrThrow("DATE3"));
                String VISIT3_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT3_RESULT"));
                String COMMENT_3 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT_3"));
                String TOTAL_VISITS = cursor.getString(cursor.getColumnIndexOrThrow("TOTAL_VISITS"));
                String Sample_FK = cursor.getString(cursor.getColumnIndexOrThrow("Sample_FK"));
                String CONSENT = cursor.getString(cursor.getColumnIndexOrThrow("CONSENT"));
                String CHECKED_BY = cursor.getString(cursor.getColumnIndexOrThrow("CHECKED_BY"));
                String CODED = cursor.getString(cursor.getColumnIndexOrThrow("CODED"));
                String FINAL_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_RESULT"));
                String FINAL_OTHER = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_OTHER"));

                dataModel.setAssignment_ID(HH_Assignment_ID);
                dataModel.setBatchNumber(BatchNumber);
                dataModel.setDWELLING_NO(DWELLING_NO);
                dataModel.setHH_NO(HH_NO);
                dataModel.setENUMERATOR(ENUMERATOR);
                dataModel.setSUPERVISOR(SUPERVISOR);
                dataModel.setQUALITY_CONTROLLER(QUALITY_CONTROLLER);
                dataModel.setINTERVIEWER_VISIT1(INTERVIEWER_VISITS1);
                dataModel.setDATE1(InterviewDATE1);
                dataModel.setVISIT1_RESULT(VISIT1_RESULT);
                dataModel.setCOMMENT1(COMMENT1);
                dataModel.setNEXT_VISIT_2_DATE(NEXT_VISIT_2_DATE);
                dataModel.setNEXT_VISIT_2(NEXT_VISIT_2_Time);
                dataModel.setINTERVIEWER_VISIT2(INTERVIEWER_VISITS2);
                dataModel.setDATE2(InterviewDATE2);
                dataModel.setVISIT2_RESULT(VISIT2_RESULT);
                dataModel.setCOMMENT2(COMMENT2);
                dataModel.setNEXT_VISIT_3_DATE(NEXT_VISIT_3_DATE);
                dataModel.setNEXT_VISIT_3(NEXT_VISIT_3_Time);
                dataModel.setINTERVIEWER_VISIT3(INTERVIEWER_VISITS3);
                dataModel.setDATE3(InterviewDATE3);
                dataModel.setVISIT3_RESULT(VISIT3_RESULT);
                dataModel.setCOMMENT3(COMMENT_3);
                dataModel.setTOTAL_VISITS(TOTAL_VISITS);
                dataModel.setCONSENT(CONSENT);
                dataModel.setCHECKED_BY(CHECKED_BY);
                dataModel.setCODED(CODED);
                dataModel.setFINAL_RESULT(FINAL_RESULT);
                dataModel.setFINAL_OTHER(FINAL_OTHER);
                dataModel.setSuperComment(cursor.getString(cursor.getColumnIndexOrThrow("SuperComment")));
                dataModel.setInterview_Status(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")));

                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo:hhDetails ) {

            // Log.i("Hellomo",""+mo.getCity());
        }

        //

        return hhDetails;
    }


    /***
     * GET STARTED HOUSEHOLD FROM DB
     * @return
     */
    public List<HouseHold> getStarted(){
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblhousehold,null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext())
        {
            if((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                            cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("9")))
            {

                dataModel= new HouseHold();
                String HH_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID"));
                String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
                String DWELLING_NO = cursor.getString(cursor.getColumnIndexOrThrow("DWELLING_NO"));
                String HH_NO = cursor.getString(cursor.getColumnIndexOrThrow("HH_NO"));
                String ENUMERATOR = cursor.getString(cursor.getColumnIndexOrThrow("ENUMERATOR"));
                String SUPERVISOR = cursor.getString(cursor.getColumnIndexOrThrow("SUPERVISOR"));
                String QUALITY_CONTROLLER = cursor.getString(cursor.getColumnIndexOrThrow("QUALITY_CONTROLLER"));
                String INTERVIEWER_VISITS1 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS1"));
                String InterviewDATE1 = cursor.getString(cursor.getColumnIndexOrThrow("DATE1"));
                String VISIT1_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT1_RESULT"));
                String COMMENT1 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT1"));
                String NEXT_VISIT_2_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_DATE"));
                String NEXT_VISIT_2_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_Time"));
                String INTERVIEWER_VISITS2 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS2"));
                String InterviewDATE2 = cursor.getString(cursor.getColumnIndexOrThrow("DATE2"));
                String VISIT2_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT2_RESULT"));
                String COMMENT2 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT2"));
                String NEXT_VISIT_3_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_DATE"));
                String NEXT_VISIT_3_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_Time"));
                String INTERVIEWER_VISITS3 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS3"));
                String InterviewDATE3 = cursor.getString(cursor.getColumnIndexOrThrow("DATE3"));
                String VISIT3_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT3_RESULT"));
                String COMMENT_3 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT_3"));
                String TOTAL_VISITS = cursor.getString(cursor.getColumnIndexOrThrow("TOTAL_VISITS"));
                String Sample_FK = cursor.getString(cursor.getColumnIndexOrThrow("Sample_FK"));
                String CONSENT = cursor.getString(cursor.getColumnIndexOrThrow("CONSENT"));
                String CHECKED_BY = cursor.getString(cursor.getColumnIndexOrThrow("CHECKED_BY"));
                String CODED = cursor.getString(cursor.getColumnIndexOrThrow("CODED"));
                String FINAL_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_RESULT"));
                String FINAL_OTHER = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_OTHER"));

                dataModel.setAssignment_ID(HH_Assignment_ID);
                dataModel.setBatchNumber(BatchNumber);
                dataModel.setDWELLING_NO(DWELLING_NO);
                dataModel.setHH_NO(HH_NO);
                dataModel.setENUMERATOR(ENUMERATOR);
                dataModel.setSUPERVISOR(SUPERVISOR);
                dataModel.setQUALITY_CONTROLLER(QUALITY_CONTROLLER);
                dataModel.setINTERVIEWER_VISIT1(INTERVIEWER_VISITS1);
                dataModel.setDATE1(InterviewDATE1);
                dataModel.setVISIT1_RESULT(VISIT1_RESULT);
                dataModel.setCOMMENT1(COMMENT1);
                dataModel.setNEXT_VISIT_2_DATE(NEXT_VISIT_2_DATE);
                dataModel.setNEXT_VISIT_2(NEXT_VISIT_2_Time);
                dataModel.setINTERVIEWER_VISIT2(INTERVIEWER_VISITS2);
                dataModel.setDATE2(InterviewDATE2);
                dataModel.setVISIT2_RESULT(VISIT2_RESULT);
                dataModel.setCOMMENT2(COMMENT2);
                dataModel.setNEXT_VISIT_3_DATE(NEXT_VISIT_3_DATE);
                dataModel.setNEXT_VISIT_3(NEXT_VISIT_3_Time);
                dataModel.setINTERVIEWER_VISIT3(INTERVIEWER_VISITS3);
                dataModel.setDATE3(InterviewDATE3);
                dataModel.setVISIT3_RESULT(VISIT3_RESULT);
                dataModel.setCOMMENT3(COMMENT_3);
                dataModel.setTOTAL_VISITS(TOTAL_VISITS);
                dataModel.setCONSENT(CONSENT);
                dataModel.setCHECKED_BY(CHECKED_BY);
                dataModel.setCODED(CODED);
                dataModel.setFINAL_RESULT(FINAL_RESULT);
                dataModel.setFINAL_OTHER(FINAL_OTHER);
                dataModel.setInterview_Status(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")));

                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo:hhDetails ) {

            // Log.i("Hellomo",""+mo.getCity());
        }

        //

        return hhDetails;
    }


    ///stopped here*******************************************************************************************************************************************************************
    public List<PersonRoster> getdataHhP(String assignment_ID,String Batch){
        // DataModel dataModel = new DataModel();
        List<PersonRoster> PRoster =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblhhroster+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        PersonRoster dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new PersonRoster();

            String SRNO = cursor.getString(cursor.getColumnIndexOrThrow("SRNO"));
            String P01 = cursor.getString(cursor.getColumnIndexOrThrow("P01"));
            String P02 = cursor.getString(cursor.getColumnIndexOrThrow("P02"));
            String P03 = cursor.getString(cursor.getColumnIndexOrThrow("P03"));
            String P04_YY = cursor.getString(cursor.getColumnIndexOrThrow("P04_YY"));
            String P04_MM = cursor.getString(cursor.getColumnIndexOrThrow("P04_MM"));
            String P04_WKS = cursor.getString(cursor.getColumnIndexOrThrow("P04_WKS"));
            String P05 = cursor.getString(cursor.getColumnIndexOrThrow("P05"));
            String P06 = cursor.getString(cursor.getColumnIndexOrThrow("P06"));
            String P07 = cursor.getString(cursor.getColumnIndexOrThrow("P07"));
            String P17 = cursor.getString(cursor.getColumnIndexOrThrow("P17"));
            String P18 = cursor.getString(cursor.getColumnIndexOrThrow("P18"));
            String P19 = cursor.getString(cursor.getColumnIndexOrThrow("P19"));
            String P20 = cursor.getString(cursor.getColumnIndexOrThrow("P20"));
            String P21 = cursor.getString(cursor.getColumnIndexOrThrow("P21"));
            String B3_RapidConsent_Yes_No = cursor.getString(cursor.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));
            String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            String B3_Date = cursor.getString(cursor.getColumnIndexOrThrow("B3_Date"));
            String Barcode = cursor.getString(cursor.getColumnIndexOrThrow("Barcode"));
            //String U15Rapid_Results = cursor.getString(cursor.getColumnIndexOrThrow("U15Rapid_Results"));
            String Rapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow("Rapid_Comment"));



            dataModel.setAssignmentID(cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID")));
            dataModel.setBatch(cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber")));
            dataModel.setSRNO(Integer.valueOf(SRNO));
            dataModel.setP01(P01);
            dataModel.setP02(P02);
            dataModel.setP03(P03);
            dataModel.setP04YY(P04_YY);
            dataModel.setP04MM(P04_MM);
            dataModel.setP04WKS(P04_WKS);
            dataModel.setP05(P05);
            dataModel.setP06(P06);
            dataModel.setP07(P07);
            dataModel.setP17(P17);
            dataModel.setP18(P18);
            dataModel.setP19(P19);
            dataModel.setP20(P20);
            dataModel.setP21(P21);
            dataModel.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);
            dataModel.setB3_Guardian(B3_Guardian);
            dataModel.setB3_Date(B3_Date);
            dataModel.setBarcode(Barcode);
            //dataModel.setU15Rapid_Results(U15Rapid_Results);
            dataModel.setRapid_Comment(Rapid_Comment);







            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            if(dataModel.getAssignmentID().equals(assignment_ID) && dataModel.getBatch().equals(Batch)){
                PRoster.add(dataModel);
            }

        }


        return PRoster;
    }

    /**
     * CHECK BARCODE IN ENUMERATORS GIVEN HOUSEHOLDS
     */
    public List<PersonRoster> getPersonsBarCheck(String assignment_ID){
        // DataModel dataModel = new DataModel();
        List<PersonRoster> PRoster =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblhhroster+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        PersonRoster dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new PersonRoster();

            String SRNO = cursor.getString(cursor.getColumnIndexOrThrow("SRNO"));
            String P01 = cursor.getString(cursor.getColumnIndexOrThrow("P01"));
            String P02 = cursor.getString(cursor.getColumnIndexOrThrow("P02"));
            String P03 = cursor.getString(cursor.getColumnIndexOrThrow("P03"));
            String P04_YY = cursor.getString(cursor.getColumnIndexOrThrow("P04_YY"));
            String P04_MM = cursor.getString(cursor.getColumnIndexOrThrow("P04_MM"));
            String P04_WKS = cursor.getString(cursor.getColumnIndexOrThrow("P04_WKS"));
            String P05 = cursor.getString(cursor.getColumnIndexOrThrow("P05"));
            String P06 = cursor.getString(cursor.getColumnIndexOrThrow("P06"));
            String P07 = cursor.getString(cursor.getColumnIndexOrThrow("P07"));
            String P17 = cursor.getString(cursor.getColumnIndexOrThrow("P17"));
            String P18 = cursor.getString(cursor.getColumnIndexOrThrow("P18"));
            String P19 = cursor.getString(cursor.getColumnIndexOrThrow("P19"));
            String P20 = cursor.getString(cursor.getColumnIndexOrThrow("P20"));
            String P21 = cursor.getString(cursor.getColumnIndexOrThrow("P21"));
            String B3_RapidConsent_Yes_No = cursor.getString(cursor.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));
            String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            String B3_Date = cursor.getString(cursor.getColumnIndexOrThrow("B3_Date"));
            String Barcode = cursor.getString(cursor.getColumnIndexOrThrow("Barcode"));
            String U15Rapid_Results = cursor.getString(cursor.getColumnIndexOrThrow("U15Rapid_Result"));
            String Rapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow("Rapid_Comment"));



            dataModel.setAssignmentID(cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID")));
            dataModel.setBatch(cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber")));
            dataModel.setSRNO(Integer.valueOf(SRNO));
            dataModel.setP01(P01);
            dataModel.setP02(P02);
            dataModel.setP03(P03);
            dataModel.setP04YY(P04_YY);
            dataModel.setP04MM(P04_MM);
            dataModel.setP04WKS(P04_WKS);
            dataModel.setP05(P05);
            dataModel.setP06(P06);
            dataModel.setP07(P07);
            dataModel.setP17(P17);
            dataModel.setP18(P18);
            dataModel.setP19(P19);
            dataModel.setP20(P20);
            dataModel.setP21(P21);
            dataModel.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);
            dataModel.setB3_Guardian(B3_Guardian);
            dataModel.setB3_Date(B3_Date);
            dataModel.setBarcode(Barcode);
            dataModel.setU15Rapid_Results(U15Rapid_Results);
            dataModel.setRapid_Comment(Rapid_Comment);


            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            if(dataModel.getAssignmentID().equals(assignment_ID)){
                PRoster.add(dataModel);
            }

        }


        return PRoster;
    }
    /**
     * RETRIEVE INDIVIDUAL DATA
     * @param house
     * @return
     */
    public List<Individual> getdataIndivisual(HouseHold house){
        // DataModel dataModel = new DataModel();
        List<Individual> IndividualStarted =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblindividual+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        Individual dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new Individual();
            String EA_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("Assignment_ID"));
            String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
            String SRNO = cursor.getString(cursor.getColumnIndexOrThrow("SRNO"));
           // String BarCode = cursor.getString(cursor.getColumnIndexOrThrow("BarCode"));
            String Q101 = cursor.getString(cursor.getColumnIndexOrThrow("Q101"));
            String Q102 = cursor.getString(cursor.getColumnIndexOrThrow("Q102"));
            String Q103 = cursor.getString(cursor.getColumnIndexOrThrow("Q103"));
            String Q104 = cursor.getString(cursor.getColumnIndexOrThrow("Q104"));
            String Q104C = cursor.getString(cursor.getColumnIndexOrThrow("Q104c"));
            String Q104cBISCED = cursor.getString(cursor.getColumnIndexOrThrow("Q104cBISCED"));
            String Q401 = cursor.getString(cursor.getColumnIndexOrThrow("Q401"));
            String Q402 = cursor.getString(cursor.getColumnIndexOrThrow("Q402"));
            String Q402a = cursor.getString(cursor.getColumnIndexOrThrow("Q402a"));
            String Q402b = cursor.getString(cursor.getColumnIndexOrThrow("Q402b"));
            String Q403 = cursor.getString(cursor.getColumnIndexOrThrow("Q403"));
            String Q504_1 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_1"));
            String Q504_2 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_2"));
            String Q504_3 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_3"));
            String Q504_4 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_4"));
            String Q504_5 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_5"));
            String Q504_6 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_6"));
            String Q504_7 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_7"));
            String Q504_8 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_8"));
            String Q504_10 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_10"));
            String Q504_Other = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Other"));
            //String Q504_OtherSpecify = cursor.getString(cursor.getColumnIndexOrThrow("Q504_OtherSpecify"));
            String Q502 = cursor.getString(cursor.getColumnIndexOrThrow("Q502"));
            String Q503 = cursor.getString(cursor.getColumnIndexOrThrow("Q503"));
            //String Q904 = cursor.getString(cursor.getColumnIndexOrThrow("Q904"));
            String Q1101 = cursor.getString(cursor.getColumnIndexOrThrow("Q1101"));
            String Q1101a = cursor.getString(cursor.getColumnIndexOrThrow("Q1101a"));
            String Q1101aOther = cursor.getString(cursor.getColumnIndexOrThrow("Q1101aOther"));
            String Q1102 = cursor.getString(cursor.getColumnIndexOrThrow("Q1102"));
            String Q1102a = cursor.getString(cursor.getColumnIndexOrThrow("Q1102a"));
            String Q1103 = cursor.getString(cursor.getColumnIndexOrThrow("Q1103"));
            //String Q1103aDD = cursor.getString(cursor.getColumnIndexOrThrow("Q1101ayy"));
            //String Q1103aWks = cursor.getString(cursor.getColumnIndexOrThrow("Q1101awks"));
            //String Q1103aDontKnow = cursor.getString(cursor.getColumnIndexOrThrow("Q1103aDontKnow"));
            String Q1104 = cursor.getString(cursor.getColumnIndexOrThrow("Q1104"));
            String Q1105 = cursor.getString(cursor.getColumnIndexOrThrow("Q1105"));
            String Q1106 = cursor.getString(cursor.getColumnIndexOrThrow("Q1106"));
            String Q1106a = cursor.getString(cursor.getColumnIndexOrThrow("Q1106a"));
            String Q1106b = cursor.getString(cursor.getColumnIndexOrThrow("Q1106b"));
            String Q1106bOther = cursor.getString(cursor.getColumnIndexOrThrow("Q1106bOther"));
            String Q1107 = cursor.getString(cursor.getColumnIndexOrThrow("Q1107"));
            //String Q1107aDD = cursor.getString(cursor.getColumnIndexOrThrow("Q1107aDD"));
            //String Q1107aWks = cursor.getString(cursor.getColumnIndexOrThrow("Q1107aWks"));
           // String Q1107aDontKnow = cursor.getString(cursor.getColumnIndexOrThrow("Q1107aDontKnow"));
            String Q1108 = cursor.getString(cursor.getColumnIndexOrThrow("Q1108"));
            //String Q1108aDD = cursor.getString(cursor.getColumnIndexOrThrow("Q1108aDD"));
            //String Q1108aWks = cursor.getString(cursor.getColumnIndexOrThrow("Q1108aWks"));
            //String Q1108aDontKnow = cursor.getString(cursor.getColumnIndexOrThrow("Q1108aDontKnow"));
            String Q1109 = cursor.getString(cursor.getColumnIndexOrThrow("Q1109"));
            String Q1110 = cursor.getString(cursor.getColumnIndexOrThrow("Q1110"));
            String Q1111 = cursor.getString(cursor.getColumnIndexOrThrow("Q1111"));
            String Q1111Other = cursor.getString(cursor.getColumnIndexOrThrow("Q1111Other"));
            String Q1112 = cursor.getString(cursor.getColumnIndexOrThrow("Q1112"));
            //String Q1112_Other = cursor.getString(cursor.getColumnIndexOrThrow("Q1112_Other"));
            String Q1113 = cursor.getString(cursor.getColumnIndexOrThrow("Q1113"));
            //String Q1113_Other = cursor.getString(cursor.getColumnIndexOrThrow("Q1113_Other"));
            String Q1114 = cursor.getString(cursor.getColumnIndexOrThrow("Q1114"));
            //String	         IndBloodSampleCollected = cursor.getString(cursor.getColumnIndexOrThrow( "IndBloodSampleCollected "));
            String	         IndBarcode = cursor.getString(cursor.getColumnIndexOrThrow( "IndBarcode" ));
            //String	         IndRapidResults = cursor.getString(cursor.getColumnIndexOrThrow( "IndRapidResults" ));
            //String	         IndRapidDate = cursor.getString(cursor.getColumnIndexOrThrow( "IndRapidDate "));
            //String	        IndRapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow( "IndRapid_Comment "));

            dataModel.setAssignmentID(EA_Assignment_ID);
            dataModel.setBatch(BatchNumber);
            dataModel.setSRNO(Integer.parseInt(SRNO));
            dataModel.setQ101(Q101);
            dataModel.setQ102(Q102);
            dataModel.setQ103(Q103);
            dataModel.setQ104(Q104);
            dataModel.setQ104c(Q104C);
            dataModel.setQ104cBISCED(Q104cBISCED);
            dataModel.setQ401(Q401);
            dataModel.setQ402(Q402);
            dataModel.setQ402a(Q402a);
            dataModel.setQ402b(Q402b);
            dataModel.setQ403(Q403);
            dataModel.setQ501(Q501);
            dataModel.setQ502(Q502);
            dataModel.setQ503(Q503);
            dataModel.setQ504_1(Q504_1);
            dataModel.setQ504_2(Q504_2);
            dataModel.setQ504_3(Q504_3);
            dataModel.setQ504_4(Q504_4);
            dataModel.setQ504_5(Q504_5);
            dataModel.setQ504_6(Q504_6);
            dataModel.setQ504_7(Q504_7);
            dataModel.setQ504_8(Q504_8);
            dataModel.setQ504_10(Q504_10);
            dataModel.setQ504_Other(Q504_Other);
            //dataModel.setQ504_OtherSpecify(Q504_OtherSpecify);
            //dataModel.setQ904(Q904);
            dataModel.setQ1101(Q1101);
            dataModel.setQ1101a(Q1101a);
            dataModel.setQ1101aOther(Q1101aOther);
            dataModel.setQ1102(Q1102);
            dataModel.setQ1102a(Q1102a);
            dataModel.setQ1103(Q1103);
            dataModel.setQ1103aDD(Q1103aDD);
            dataModel.setQ1103aWks(Q1103aWks);
            dataModel.setQ1103aDontKnow(Q1103aDontKnow);
            dataModel.setQ1104(Q1104);
            dataModel.setQ1105(Q1105);
            dataModel.setQ1106(Q1106);
            dataModel.setQ1106a(Q1106a);
            dataModel.setQ1106b(Q1106b);
            dataModel.setQ1106bOther(Q1106bOther);
            dataModel.setQ1107(Q1107);
            dataModel.setQ1107aDD(Q1107aDD);
            dataModel.setQ1107aWks(Q1107aWks);
            dataModel.setQ1107aDontKnow(Q1107aDontKnow);
            dataModel.setQ1108(Q1108);
            dataModel.setQ1108aDD(Q1108aDD);
            dataModel.setQ1108aWks(Q1108aWks);
            dataModel.setQ1108aDontKnow(Q1108aDontKnow);
            dataModel.setQ1109(Q1109);
            dataModel.setQ1110(Q1110);
            dataModel.setQ1111(Q1111);
            dataModel.setQ1111Other(Q1111Other);
            dataModel.setQ1112(Q1112);
            //dataModel.setQ1112_Other(Q1112_Other);
            dataModel.setQ1113(Q1113);
            //dataModel.setQ1113Other(Q1113_Other);
            dataModel.setQ1114(Q1114);
            dataModel.setIndBloodSampleCollected(IndBloodSampleCollected);
            dataModel.setIndBarcode(IndBarcode);
            dataModel.setIndRapidResults(IndRapidResults);
            dataModel.setIndRapidDate(IndRapidDate);
            dataModel.setIndRapid_Comment(IndRapid_Comment);



         stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);

            if(house.getAssignment_ID().equals(dataModel.getAssignmentID()) && house.getBatchNumber().equals(dataModel.getBatch())){
                IndividualStarted.add(dataModel);
            }


        }

        return IndividualStarted;
    }


    /**
     * UPDATE HOUSE HOLD INTERVIEW STATUSES
     */
    public boolean updateHHStatus(HouseHold houseHold)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        //contentValues.put("UserId", UserId);
        hhValues.put("Interview_Status",houseHold.getInterview_Status());
        hhValues.put("DWELLING_NO",houseHold.getDWELLING_NO());
        hhValues.put("HH_NO",houseHold.getDWELLING_NO());


        if((!houseHold.getDATE1().equals("") && houseHold.getDATE2().equals("") && houseHold.getDATE3().equals("") )){

            Log.d("Visit 1: ", houseHold.getDATE1() + "--- " + houseHold.getVISIT1_RESULT()+"---"+houseHold.getVISIT1_RESULT());

            //Visit 1
              hhValues.put("DATE1",houseHold.getDATE1());
            hhValues.put("VISIT1_RESULT",houseHold.getVISIT1_RESULT());
            if(houseHold.getVISIT1_RESULT().equals("3") ||
                    houseHold.getVISIT1_RESULT().equals("4") ||
                    houseHold.getVISIT1_RESULT().equals("4") ||
                    houseHold.getVISIT1_RESULT().equals("5"))
            {
                hhValues.put("COMMENT1",houseHold.getCOMMENT1());
            }
            if(houseHold.getVISIT1_RESULT().equals("6")){
                hhValues.put("COMMENT1",houseHold.getVisit1Other());
            }

            hhValues.put("NEXT_VISIT_2_DATE",houseHold.getNEXT_VISIT_2_DATE());
            hhValues.put("NEXT_VISIT_2_TIME",houseHold.getNEXT_VISIT_2_TIME());
        }
        else if((!houseHold.getDATE1().equals("") && !houseHold.getDATE2().equals("") && houseHold.getDATE3().equals("") )){
            //Visit 2

            Log.d("Visit 2: ", houseHold.getDATE2() + "--- " + houseHold.getVISIT2_RESULT()+"---"+houseHold.getVISIT2_RESULT());

            hhValues.put("DATE2",houseHold.getDATE2());
            hhValues.put("VISIT2_RESULT",houseHold.getVISIT2_RESULT());
            if(houseHold.getVISIT2_RESULT().equals("3") ||
                    houseHold.getVISIT2_RESULT().equals("4") ||
                    houseHold.getVISIT2_RESULT().equals("4") ||
                    houseHold.getVISIT2_RESULT().equals("5"))
            {
                hhValues.put("COMMENT2",houseHold.getCOMMENT2());
            }
            if(houseHold.getVISIT2_RESULT().equals("6")){
                hhValues.put("COMMENT2",houseHold.getVisit2Other());
            }

            hhValues.put("NEXT_VISIT_3_DATE",houseHold.getNEXT_VISIT_3_DATE());
            hhValues.put("NEXT_VISIT_3_TIME",houseHold.getNEXT_VISIT_3_TIME());

        }

        else if((!houseHold.getDATE1().equals("") && !houseHold.getDATE2().equals("") && !houseHold.getDATE3().equals("") )){
            //VISIT 3

            Log.d("Visit 3: ", houseHold.getDATE3() + "--- " + houseHold.getVISIT3_RESULT()+"---"+houseHold.getVISIT3_RESULT());

            hhValues.put("DATE3",houseHold.getDATE3());
            hhValues.put("VISIT3_RESULT",houseHold.getVISIT3_RESULT());

            if(houseHold.getVISIT3_RESULT().equals("3") ||
                    houseHold.getVISIT3_RESULT().equals("4") ||
                    houseHold.getVISIT3_RESULT().equals("4") ||
                    houseHold.getVISIT3_RESULT().equals("5"))
            {
                hhValues.put("COMMENT_3",houseHold.getCOMMENT3());
            }
            if(houseHold.getVISIT3_RESULT().equals("6")){
                hhValues.put("COMMENT_3",houseHold.getVisit3Other());
            }
        }else{


            Log.d("Visit 1",String.valueOf((houseHold.getDATE1().equals(""))));
            Log.d("Visit 2",String.valueOf((houseHold.getDATE2().equals(""))));
            Log.d("Visit 3",String.valueOf((houseHold.getDATE3().equals(""))));


        }




        int  i = db.update
                (   tblhousehold, // table
                    hhValues, // column/value
                    "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                    new String[] { String.valueOf(houseHold.getAssignment_ID()),String.valueOf(houseHold.getBatchNumber()) }
                );

            db.close();

        return true;
    }



    /***
     * GET STARTED HOUSEHOLD FROM DB
     * @return
     */
    public List<HouseHold> getCompleted(){
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails =new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+tblhousehold,null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext())
        {
            if((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                    cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("10")))
            {

                dataModel= new HouseHold();
                String HH_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID"));
                String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
                String DWELLING_NO = cursor.getString(cursor.getColumnIndexOrThrow("DWELLING_NO"));
                String HH_NO = cursor.getString(cursor.getColumnIndexOrThrow("HH_NO"));
                String ENUMERATOR = cursor.getString(cursor.getColumnIndexOrThrow("ENUMERATOR"));
                String SUPERVISOR = cursor.getString(cursor.getColumnIndexOrThrow("SUPERVISOR"));
                String QUALITY_CONTROLLER = cursor.getString(cursor.getColumnIndexOrThrow("QUALITY_CONTROLLER"));
                String INTERVIEWER_VISITS1 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS1"));
                String InterviewDATE1 = cursor.getString(cursor.getColumnIndexOrThrow("DATE1"));
                String VISIT1_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT1_RESULT"));
                String COMMENT1 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT1"));
                String NEXT_VISIT_2_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_DATE"));
                String NEXT_VISIT_2_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_2_Time"));
                String INTERVIEWER_VISITS2 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS2"));
                String InterviewDATE2 = cursor.getString(cursor.getColumnIndexOrThrow("DATE2"));
                String VISIT2_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT2_RESULT"));
                String COMMENT2 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT2"));
                String NEXT_VISIT_3_DATE = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_DATE"));
                String NEXT_VISIT_3_Time = cursor.getString(cursor.getColumnIndexOrThrow("NEXT_VISIT_3_Time"));
                String INTERVIEWER_VISITS3 = cursor.getString(cursor.getColumnIndexOrThrow("INTERVIEWER_VISITS3"));
                String InterviewDATE3 = cursor.getString(cursor.getColumnIndexOrThrow("DATE3"));
                String VISIT3_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("VISIT3_RESULT"));
                String COMMENT_3 = cursor.getString(cursor.getColumnIndexOrThrow("COMMENT_3"));
                String TOTAL_VISITS = cursor.getString(cursor.getColumnIndexOrThrow("TOTAL_VISITS"));
                String Sample_FK = cursor.getString(cursor.getColumnIndexOrThrow("Sample_FK"));
                String CONSENT = cursor.getString(cursor.getColumnIndexOrThrow("CONSENT"));
                String CHECKED_BY = cursor.getString(cursor.getColumnIndexOrThrow("CHECKED_BY"));
                String CODED = cursor.getString(cursor.getColumnIndexOrThrow("CODED"));
                String FINAL_RESULT = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_RESULT"));
                String FINAL_OTHER = cursor.getString(cursor.getColumnIndexOrThrow("FINAL_OTHER"));

                dataModel.setAssignment_ID(HH_Assignment_ID);
                dataModel.setBatchNumber(BatchNumber);
                dataModel.setDWELLING_NO(DWELLING_NO);
                dataModel.setHH_NO(HH_NO);
                dataModel.setENUMERATOR(ENUMERATOR);
                dataModel.setSUPERVISOR(SUPERVISOR);
                dataModel.setQUALITY_CONTROLLER(QUALITY_CONTROLLER);
                dataModel.setINTERVIEWER_VISIT1(INTERVIEWER_VISITS1);
                dataModel.setDATE1(InterviewDATE1);
                dataModel.setVISIT1_RESULT(VISIT1_RESULT);
                dataModel.setCOMMENT1(COMMENT1);
                dataModel.setNEXT_VISIT_2_DATE(NEXT_VISIT_2_DATE);
                dataModel.setNEXT_VISIT_2(NEXT_VISIT_2_Time);
                dataModel.setINTERVIEWER_VISIT2(INTERVIEWER_VISITS2);
                dataModel.setDATE2(InterviewDATE2);
                dataModel.setVISIT2_RESULT(VISIT2_RESULT);
                dataModel.setCOMMENT2(COMMENT2);
                dataModel.setNEXT_VISIT_3_DATE(NEXT_VISIT_3_DATE);
                dataModel.setNEXT_VISIT_3(NEXT_VISIT_3_Time);
                dataModel.setINTERVIEWER_VISIT3(INTERVIEWER_VISITS3);
                dataModel.setDATE3(InterviewDATE3);
                dataModel.setVISIT3_RESULT(VISIT3_RESULT);
                dataModel.setCOMMENT3(COMMENT_3);
                dataModel.setTOTAL_VISITS(TOTAL_VISITS);
                dataModel.setCONSENT(CONSENT);
                dataModel.setCHECKED_BY(CHECKED_BY);
                dataModel.setCODED(CODED);
                dataModel.setFINAL_RESULT(FINAL_RESULT);
                dataModel.setFINAL_OTHER(FINAL_OTHER);
                dataModel.setInterview_Status(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")));

                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo:hhDetails ) {

            // Log.i("Hellomo",""+mo.getCity());
        }

        //

        return hhDetails;
    }

















}



//Cursor resS = db.rawQuery("select * from " + tblAssignments + "tblhousehold.BATCHNUMBER" + "tblhousehold.ENUMERATOR" + "tblhousehold.SUPERVISOR" + "tblhousehold.QUALITY_CONTROLLER+ "+ "INNER JOIN"+"tblhousehold"+"ON"+"tblhousehold.EA_Assignment_ID = tblAssignments.EA_Assignment_ID", null);