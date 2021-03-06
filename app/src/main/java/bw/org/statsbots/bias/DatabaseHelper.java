package bw.org.statsbots.bias;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import org.joda.time.DateTime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.Proxy.Type.HTTP;


public  class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BIAS.db";

    /**
     * Table Names
     * //private static final String TABLE_USERS = "AspNetUsers";
     **/
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
    private static final String ROUND_NUMBER = "ROUND_NUMBER";
    private static final String Supervisor = "Supervisor";
    private static final String Sample_FK = "Sample_FK";


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
    private static final String SuperComment = "SuperComment";
    private static final String QcComment = "QcComment";
    private static final String HQComment = "HQComment";
    // private static final String Sample_FK = "Sample_FK";
    private static final String CONSENT = "CONSENT";
    private static final String CHECKED_BY = "CHECKED_BY";
    private static final String CODED = "CODED";
    private static final String FINAL_RESULT = "FINAL_RESULT";
    private static final String FINAL_OTHER = "FINAL_OTHER";
    private static final String Interview_Status = "Interview_Status";
    private static final String Clear = "Clear";
    private static final String Is_Checked = "Is_Checked";
    private static final String H01 = "H01";
    private static final String H02 = "H02";
    private static final String H03 = "H03";
    private static final String H03Other = "H03Other";
    private static final String H04 = "H04";
    private static final String H04Other = "H04Other";
    private static final String H05 = "H05";
    private static final String H05Other = "H05Other";
    private static final String H06 = "H06";
    private static final String H07 = "H07";
    private static final String H08 = "H08";
    private static final String H08Other = "H08Other";
    private static final String H09 = "H09";
    private static final String H09Other = "H09Other";
    private static final String H10 = "H10";
    private static final String H11 = "H11";
    private static final String H11Other = "H11Other";
    private static final String H12Radio = "H12Radio";
    private static final String H12TV = "H12TV";
    private static final String H12Telephone = "H12Telephone";
    private static final String H12Cellphone = "H12CellPhone";
    private static final String H12PrintMedia = "H12PrintMedia";
    private static final String H12ElecMedia = "H12ElecMedia";
    private static final String H12PerformArts = "H12PerformArts";
    private static final String H13Vehicle = "H13Vehicle";
    private static final String H13Tractor = "H13Tractor";
    private static final String H13Motorcycle = "H13Motorcycle";
    private static final String H13Bicycle = "H13Bicycle";
    private static final String H13DonkeyCart = "H13DonkeyCart";
    private static final String H13DonkeyHorse = "H13DonkeyHorse";
    private static final String H13Camels = "H13Camels";


    private static final String tblhhroster = "HHP_ROSTER";
    private static final String Assignment_ID = "EA_Assignment_ID";
    private static final String BatchNumberR = "BatchNumber";
    private static final String SRNO = "SRNO";
    private static final String BarCodeHHR = "BarcodeHHR";
    private static final String P01 = "P01";
    private static final String P02 = "P02";
    private static final String P03 = "P03";
    private static final String P04_YY = "P04_YY";
    private static final String P04_MM = "P04_MM";
    private static final String P04_WKS = "P04_WKS";
    private static final String P05 = "P05";
    private static final String P06 = "P06";
    private static final String P07 = "P07";
    private static final String P08 = "P08";
    private static final String P09 = "P09";
    private static final String P10 = "P10";
    private static final String P11 = "P11";
    private static final String P12 = "P12";
    private static final String P13 = "P13";
    private static final String P13Other = "P13Other";
    private static final String P14 = "P14";
    private static final String P15 = "P15";
    private static final String P16 = "P16";
    private static final String P17 = "P17";
    private static final String P18 = "P18";
    private static final String P19 = "P19";
    private static final String P20 = "P20";
    private static final String P21 = "P21";
    //private static final String BloodSampleCollected = "BloodSampleCollected";
    private static final String Barcode = "Barcode";
    // private static final String RapidResults = "RapidResults";
    // private static final String RapidDate = "RapidDate";


    private static final String BloodDraw = "BloodDraw";
    private static final String BloodVolume_1 = "BloodVolume_1";
    private static final String BloodVolume_4 = "BloodVolume_4";
    private static final String BloodVolume_6 = "BloodVolume_6";
    private static final String BloodVolume_10 = "BloodVolume_10";
    private static final String BloodVolumeComment = "BloodVolumeComment";
    private static final String Rapid = "Rapid";
    private static final String RapidResults = "RapidResults"; //B8_UnderRapid
    private static final String BloodLabTest = "BloodLabTest";
    private static final String BloodStore = "BloodStore";
    private static final String RapidDate = "RapidDate";
    private static final String BloodSampleCollected = "BloodSampleCollected";
    private static final String ChPrntlConsentBloodDraw = "ChPrntlConsentBloodDraw";
    private static final String ChPrntlConsentRHT = "ChPrntlConsentRHT";
    private static final String ChPrntlConsentLabTest = "ChPrntlConsentLabTest";
    private static final String ChPrntlConsentBloodStore = "ChPrntlConsentBloodStore";
    private static final String B3_Guardian = "B3_Guardian";
    private static final String ChPrntlConsentDate = "ChPrntlConsentDate";

    private static final String PrntlConsentX_Ray = "PrntlConsentX_Ray";
    private static final String PrntlConsentX_RayReview = "PrntlConsentX_RayReview";
    private static final String PrntlConsentX_RayStore = "PrntlConsentX_RayStore";
    private static final String PrntlConsentSP_Collect = "PrntlConsentSP_Collect";
    private static final String PrntlParentSP_AddTests = "PrntlParentSP_AddTests";
    private static final String PrntlConsentSP_LabTest = "PrntlConsentSP_LabTest";
    private static final String PrntlConsentTBDate = "PrntlConsentTBDate";
    private static final String IndTB_X_Ray = "IndTB_X_Ray";
    private static final String IndTB_X_RayReview = "IndTB_X_RayReview";
    private static final String IndTB_X_RayStore = "IndTB_X_RayStore";
    private static final String IndSP_Collect = "IndSP_Collect";
    private static final String IndSP_AddTests = "IndSP_AddTests";
    private static final String IndSP_LabTests = "IndSP_LabTests";
    private static final String IndTB_ConsentDate = "IndTB_ConsentDate";


    private static final String tblindividual = "Individual";
    private static final String BatchNumberi = "BatchNumber";
    private static final String Assignment_IDi = "Assignment_ID";
    private static final String SRNOi = "SRNO";
    private static final String BarCodeIND = "BarCodeIND";
    private static final String Q101 = "Q101";
    private static final String Q102 = "Q102";
    private static final String Q103 = "Q103";
    private static final String Q104 = "Q104";
    private static final String Q104a = "Q104a";
    private static final String Q104b = "Q104b";
    private static final String Q104c = "Q104c";
    private static final String Q104cBISCED = "Q104cBISCED";
    private static final String Q105 = "Q105";
    private static final String Q105Other = "Q105Other";
    private static final String Q105a = "Q105a";
    private static final String Q105aBOSCO = "Q105aBOSCO";
    private static final String Q105b = "Q105b";
    private static final String Q105bBISIC = "Q105bBISIC";
    private static final String Q106 = "Q106";
    private static final String Q106a = "Q106a";
    private static final String Q106aOther = "Q106aOther";
    private static final String Q106b = "Q106b";
    private static final String Q106c = "Q106c";
    private static final String Q106cBOSCO = "Q106cBOSCO";
    private static final String Q106d = "Q106d";
    private static final String Q106dBISIC = "Q106dBISIC";
    private static final String Q107 = "Q107";
    private static final String Q107a = "Q107a";
    private static final String Q107b = "Q107b";
    private static final String Q107bOther = "Q107bOther";
    private static final String Q107c = "Q107c";
    private static final String Q107cOther = "Q107cOther";
    private static final String Q201 = "Q201";
    private static final String Q202 = "Q202";
    private static final String Q203 = "Q203";
    private static final String Q204 = "Q204";
    private static final String Q205 = "Q205";
    private static final String Q205a = "Q205a";
    private static final String Q301 = "Q301";
    private static final String Q301a = "Q301a";
    private static final String Q302 = "Q302";
    private static final String Q303 = "Q303";
    private static final String Q303a = "Q303a";
    private static final String Q304 = "Q304";
    private static final String Q304a = "Q304a";
    private static final String Q305Smoking = "Q305Smoking";
    private static final String Q305Sniffing = "Q305Sniffing";
    private static final String Q305Chewing = "Q305Chewing";
    private static final String Q305None = "Q305None";
    private static final String Q306 = "Q306";
    private static final String Q307 = "Q307";
    private static final String Q401 = "Q401";
    private static final String Q402 = "Q402";
    private static final String Q402a = "Q402a";
    private static final String Q402b = "Q402b";
    private static final String Q403 = "Q403";
    private static final String Q404Vaginal = "Q404Vaginal";
    private static final String Q404Anal = "Q404Anal";
    private static final String Q404Oral = "Q404Oral";
    private static final String Q404a = "Q404a";
    private static final String Q405 = "Q405";
    private static final String Q406 = "Q406";
    private static final String Q407 = "Q407";
    private static final String Q408 = "Q408";
    private static final String Q408a = "Q408a";
    private static final String Q410Slapped = "Q410Slapped";
    private static final String Q410Pushed = "Q410Pushed";
    private static final String Q410Chocked = "Q410Chocked";
    private static final String Q410Threatened = "Q410Threatened";
    private static final String Q410Physical = "Q410Physical";
    private static final String Q410Forced = "Q410Forced";
    private static final String Q410MadeAfraid = "Q410MadeAfraid";
    private static final String Q501 = "Q501";
    private static final String Q502 = "Q502";
    private static final String Q503 = "Q503";
    private static final String Q504_Pain = "Q504_Pain";
    private static final String Q504_Reduced = "Q504_Reduced";
    private static final String Q504_Fear = "Q504_Fear";
    private static final String Q504_Culture = "Q504_Culture";
    private static final String Q504_Religion = "Q504_Religion";
    private static final String Q504_Spouse = "Q504_Spouse";
    private static final String Q504_Parental = "Q504_Parental";
    private static final String Q504_Long = "Q504_Long";
    private static final String Q504_FearHIV = "Q504_FearHIV";
    private static final String Q504_Other = "Q504_Other";
    private static final String Q504_OtherSpecify = "Q504_OtherSpcify";
    private static final String Q601 = "Q601";
    private static final String Q601a = "Q601a";
    private static final String Q602Youth = "Q602Youth";
    private static final String Q602TV = "Q602TV";
    private static final String Q602Radio = "Q602Radio";
    private static final String Q602Newspaper = "Q602Newspaper";
    private static final String Q602Hospital = "Q602Hospital";
    private static final String Q602Posters = "Q602Posters";
    private static final String Q602Traditional = "Q602Traditional";
    private static final String Q602Workshop = "Q602Workshop";
    private static final String Q602Individual = "Q602Individual";
    private static final String Q602Church = "Q602Church";
    private static final String Q602Kgotla = "Q602Kgotla";
    private static final String Q602Workplace = "Q602Workplace";
    private static final String Q602Peer = "Q602Peer";
    private static final String Q602School = "Q602School";
    private static final String Q602_Other = "Q602_Other";
    private static final String Q603Condom = "Q603Condom";
    private static final String Q603FewerP = "Q603FewerP";
    private static final String Q603Both = "Q603Both";
    private static final String Q603NoCasual = "Q603NoCasual";
    private static final String Q603Abstain = "Q603Abstain";
    private static final String Q603NoCommercial = "Q603NoCommercial";
    private static final String Q603Injection = "Q603Injection";
    private static final String Q603Blood = "Q603Blood";
    private static final String Q603DontKnow = "Q603DontKnow";
    private static final String Q603Other = "Q603Other";
    private static final String Q604 = "Q604";
    private static final String Q604a = "Q604a";
    private static final String Q604bYouth = "Q604bYouth";
    private static final String Q604bTV = "Q604bTV";
    private static final String Q604bRadio = "Q604bRadio";
    private static final String Q604bNewspaper = "Q604bNewspaper";
    private static final String Q604bHospital = "Q604bHospital";
    private static final String Q604bPoster = "Q604bPoster";
    private static final String Q604bTraditional = "Q604bTraditional";
    private static final String Q604bWorkshop = "Q604bWorkshop";
    private static final String Q604bIndividual = "Q604bIndividual";
    private static final String Q604bChurch = "Q604bChurch";
    private static final String Q604bKgotla = "Q604bKgotla";
    private static final String Q604bWorkplace = "Q604bWorkplace";
    private static final String Q604bPeer = "Q604bPeer";
    private static final String Q604bSchool = "Q604bSchool";
    private static final String Q604bOther = "Q604bOther";
    private static final String Q605Windows = "Q605Windows";
    private static final String Q605Mouth = "Q605Mouth";
    private static final String Q605Hands = "Q605Hands";
    private static final String Q605Nutrition = "Q605Nutrition";
    private static final String Q605Praying = "Q605Praying";
    private static final String Q605DontKnow = "Q605DontKnow";
    private static final String Q605Other = "Q605Other";
    private static final String Q605OtherSpecify = "Q605OtherSpecify";
    private static final String Q606 = "Q606";
    private static final String Q607 = "Q607";
    private static final String Q608 = "Q608";
    private static final String Q609 = "Q609";
    private static final String Q610 = "Q610";
    private static final String Q611a = "Q611a";
    private static final String Q611b = "Q611b";
    private static final String Q611c = "Q611c";
    private static final String Q612 = "Q612";
    private static final String Q612a = "Q612a";
    private static final String Q612Other = "Q612Other";
    private static final String Q613 = "Q613";
    private static final String Q613a = "Q613a";
    private static final String Q613aOther = "Q613aOther";
    private static final String Q614 = "Q614";
    private static final String Q614Other = "Q614Other";
    private static final String Q615 = "Q615";
    private static final String Q616Anybody = "Q616Anybody";
    private static final String Q616Poor = "Q616Poor";
    private static final String Q616Homeless = "Q616Homeless";
    private static final String Q616Alcoholics = "Q616Alcoholics";
    private static final String Q616Drugs = "Q616Drugs";
    private static final String Q616PeopHIV = "Q616PeopHIV";
    private static final String Q616PeopPrison = "Q616PeopPrison";
    private static final String Q616Smokers = "Q616Smokers";
    private static final String Q616DntKnow = "Q616DntKnow";
    private static final String Q616Other = "Q616Other";
    private static final String Q617Meal = "Q617Meal";
    private static final String Q617Clothes = "Q617Clothes";
    private static final String Q617Miscarried = "Q617Miscarried";
    private static final String Q617Widow = "Q617Widow";
    private static final String Q617FamilyHIV = "Q617FamilyHIV";
    private static final String Q617Sejeso = "Q617Sejeso";
    private static final String Q617Touching = "Q617Touching";
    private static final String Q617Someone = "Q617Someone";
    private static final String Q617Other = "Q617Other";
    private static final String Q618 = "Q618";
    private static final String Q619Rash = "Q619Rash";
    private static final String Q619Cough = "Q619Cough";
    private static final String Q619LongCough = "Q619LongCough";
    private static final String Q619Blood = "Q619Blood";
    private static final String Q619Headache = "Q619Headache";
    private static final String Q619Nausea = "Q619Nausea";
    private static final String Q619Weight = "Q619Weight";
    private static final String Q619Fever = "Q619Fever";
    private static final String Q619Fever7Days = "Q619Fever7Days";
    private static final String Q619ChestPain = "Q619ChestPain";
    private static final String Q619Breath = "Q619Breath";
    private static final String Q619Fatigue = "Q619Fatigue";
    private static final String Q619Sweats = "Q619Sweats";
    private static final String Q619DontKnow = "Q619DontKnow";
    private static final String Q619Other = "Q619Other";
    private static final String Q620 = "Q620";
    private static final String Q620Other = "Q620Other";
    private static final String Q621 = "Q621";
    private static final String Q621aSpouse = "Q621aSpouse";
    private static final String Q621aPartner = "Q621aPartner";
    private static final String Q621aFriend = "Q621aFriend";
    private static final String Q621aFamily = "Q621aFamily";
    private static final String Q621aRelative = "Q621aRelative";
    private static final String Q621aHCWorker = "Q621aHCWorker";
    private static final String Q621aCoWorker = "Q621aCoWorker";
    private static final String Q621aOther = "Q621aOther";
    private static final String Q621b = "Q621b";
    private static final String Q621bOther = "Q621bOther";
    private static final String Q622 = "Q622";
    private static final String Q622a = "Q622a";
    private static final String Q622aOther = "Q622aOther";
    private static final String Q622b = "Q622b";
    private static final String Q622bOther = "Q622bOther";
    private static final String Q623 = "Q623";
    private static final String Q624 = "Q624";
    private static final String Q625 = "Q625";
    private static final String Q701 = "Q701";
    private static final String Q702 = "Q702";
    private static final String Q703 = "Q703";
    private static final String Q704 = "Q704";
    private static final String Q705 = "Q705";
    private static final String Q801 = "Q801";
    private static final String Q801a = "Q801a";
    private static final String Q801b = "Q801b";
    private static final String Q801c = "Q801c";
    private static final String Q801d = "Q801d";
    private static final String Q801dOther = "Q801dOther";
    private static final String Q801e = "Q801e";
    private static final String Q801eOther = "Q801eOther";
    private static final String Q801f = "Q801f";
    private static final String Q802 = "Q802";
    private static final String Q802a = "Q802a";
    private static final String Q802aOther = "Q802aOther";
    private static final String Q803 = "Q803";
    private static final String Q803Other = "Q803Other";
    private static final String Q804 = "Q804";
    private static final String Q804Other = "Q804Other";
    private static final String Q901 = "Q901";
    private static final String Q901a = "Q901a";
    private static final String Q901aOther = "Q901aOther";
    private static final String Q902 = "Q902";
    private static final String Q903DenyCare = "Q903DenyCare";
    private static final String Q903Gossip = "Q903Gossip";
    private static final String Q903NoSex = "Q903NoSex";
    private static final String Q903VerbalAbuse = "Q903VerbalAbuse";
    private static final String Q903PhysicalAbuse = "Q903PhysicalAbuse";
    private static final String Q903NoContact = "Q903NoContact";
    private static final String Q903SharingStatus = "Q903SharingStatus";
    private static final String Q904 = "Q904";
    private static final String Q904a = "Q904a";
    private static final String Q904aOther = "Q904aOther";
    private static final String Q904b = "Q904b";
    private static final String Q904c = "Q904c";
    private static final String Q904cOther = "Q904cOther";
    private static final String Q905 = "Q905";
    private static final String Q905a = "Q905a";
    private static final String Q905aOther = "Q905aOther";
    private static final String Q1001 = "Q1001";
    private static final String Q1002 = "Q1002";
    private static final String Q1002aMCondom = "Q1002aMCondom";
    private static final String Q1002aFCondom = "Q1002aFCondom";
    private static final String Q1002aInjectContra = "Q1002aInjectContra";
    private static final String Q1002aOralContra = "Q1002aOralContra";
    private static final String Q1002aUID = "Q1002aUID";
    private static final String Q1002aBTL = "Q1002aBTL";
    private static final String Q1002aFSterilization = "Q1002aFSterilization";
    private static final String Q1002aMSterilization = "Q1002aMSterilization";
    private static final String Q1002aImplants = "Q1002aImplants";
    private static final String Q1002aEContra = "Q1002aEContra";
    private static final String Q1002aSafePeriod = "Q1002aSafePeriod";
    private static final String Q1002aLAM = "Q1002aLAM";
    private static final String Q1002aDiagraphm = "Q1002aDiagraphm";
    private static final String Q1002aSpermicides = "Q1002aSpermicides";
    private static final String Q1002aNatural = "Q1002aNatural";
    private static final String Q1002aTraditional = "Q1002aTraditional";
    private static final String Q1002aSpiritual = "Q1002aSpiritual";
    private static final String Q1002aOther = "Q1002aOther";
    private static final String Q1002b = "Q1002b";
    private static final String Q1002bOther = "Q1002bOther";
    private static final String Q1003 = "Q1003";
    private static final String Q1004 = "Q1004";
    private static final String Q1004a = "Q1004a";
    private static final String Q1004b = "Q1004b";
    private static final String Q1004bOther = "Q1004bOther";
    private static final String Q1005 = "Q1005";
    private static final String Q1005a = "Q1005a";
    private static final String Q1006 = "Q1006";
    private static final String Q1007 = "Q1007";
    private static final String Q1007a = "Q1007a";
    private static final String Q1008 = "Q1008";
    private static final String Q1008a = "Q1008a";
    private static final String Q1008aOther = "Q1008aOther";
    private static final String Q1009 = "Q1009";
    private static final String Q1009a = "Q1009a";
    private static final String Q1010 = "Q1010";
    private static final String Q1010Other = "Q1010Other";
    private static final String Q1011 = "Q1011";
    private static final String Q1011Other = "Q1011Other";
    private static final String Q1012 = "Q1012";
    private static final String Q1012Still = "Q1012Still";
    private static final String Q1013 = "Q1013";
    private static final String Q1014 = "Q1014";
    private static final String Q1014a = "Q1014a";
    private static final String Q1014b = "Q1014b";
    private static final String Q1015 = "Q1015";
    private static final String Q1015a = "Q1015a";
    private static final String Q1015b = "Q1015b";
    private static final String Q1016 = "Q1016";
    private static final String Q1017 = "Q1017";
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
    private static final String IndBarcode = "IndBarcode";

    private static final String IndvQuestionnairePConsent15_17 = "IndvQuestionnairePConsent15_17";
    private static final String IndvQuestionnaireConsent = "IndvQuestionnaireConsent";
    private static final String IndvBloodDraw = "IndvBloodDraw";
    private static final String BloodVol_1 = "BloodVol_1";
    private static final String BloodVol_4 = "BloodVol_4";
    private static final String BloodVol_6 = "BloodVol_6";
    private static final String BloodVol_10 = "BloodVol_10";
    private static final String BloodVolComment = "BloodVolComment";
    private static final String B8_O15_Rapid = "B8_O15_Rapid";
    private static final String IndRapidResults = "IndRapidResults";
    private static final String IndBloodLabTest = "IndBloodLabTest";
    private static final String IndBloodStore = "IndBloodStore";
    private static final String IndRapidDate = "IndRapidDate";
    private static final String IndBloodSampleCollected = "IndBloodSampleCollected";
    private static final String PrntlConsentBloodDraw = "PrntlConsentBloodDraw";
    private static final String PrntlConsentRHT = "PrntlConsentRHT";
    private static final String PrntlConsentLabTest = "PrntlConsentLabTest";
    private static final String PrntlConsentBloodStore = "PrntlConsentBloodStore";
    private static final String PrntlParentID = "PrntlParentID";
    private static final String PrntlConsentDate = "PrntlConsentDate";
    private static final String VISIT1 = "VISIT1";
    private static final String VISIT2 = "VISIT2";
    private static final String VISIT3 = "VISIT3";
    private static final String INDDATE1 = "DATE1";
    private static final String INDDATE2 = "DATE2";
    private static final String INDDATE3 = "DATE3";
    private static final String Sync = "Sync";

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
            + Super_Code + " nvarchar(3),"
            + Role + " nvarchar (2),"
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
            + Assignment_ID + " nvarchar(13) NOT NULL,"
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
            + SuperComment + " nvarchar(500),"
            + QcComment + " nvarchar(500),"
            + HQComment + " nvarchar(500),"
            + Sample_FK + " nvarchar(4),"
            + CONSENT + " nvarchar(1),"
            + CHECKED_BY + " nvarchar(50),"
            + CODED + " nvarchar(50),"
            + FINAL_RESULT + " nvarchar(1),"
            + FINAL_OTHER + " nvarchar(100),"
            + Interview_Status + " nvarchar(1),"
            + Clear + " nvarchar(1),"
            + Is_Checked + " nvarchar(1),"
            + H01 + " nvarchar(2),"
            + H02 + " nvarchar(2),"
            + H03 + " nvarchar(2),"
            + H03Other + " nvarchar(500),"
            + H04 + " nvarchar(1),"
            + H04Other + " nvarchar(500),"
            + H05 + " nvarchar(1),"
            + H05Other + " nvarchar(500),"
            + H06 + " nvarchar(2),"
            + H07 + " nvarchar(2),"
            + H08 + " nvarchar(1),"
            + H08Other + " nvarchar(500),"
            + H09 + " nvarchar(1),"
            + H09Other + " nvarchar(500),"
            + H10 + " nvarchar(1),"
            + H11 + " nvarchar(1),"
            + H11Other + " nvarchar(500),"
            + H12Radio + " nvarchar(1),"
            + H12TV + " nvarchar(1),"
            + H12Telephone + " nvarchar(1),"
            + H12Cellphone + " nvarchar(1),"
            + H12PrintMedia + " nvarchar(1),"
            + H12ElecMedia + " nvarchar(1),"
            + H12PerformArts + " nvarchar(1),"
            + H13Vehicle + " nvarchar(1),"
            + H13Tractor + " nvarchar(1),"
            + H13Motorcycle + " nvarchar(1),"
            + H13Bicycle + " nvarchar(1),"
            + H13DonkeyCart + " nvarchar(1),"
            + H13DonkeyHorse + " nvarchar(1),"
            + H13Camels + " nvarchar(1), HIVTB40 nvarchar(1))";


    //create Persons roster table
    private static final String Create_Table_HhRoster = "CREATE TABLE " + tblhhroster + "("
            + Assignment_ID + " uniqueidentifier  NOT NULL,"
            + BatchNumberR + " nvarchar(4) NOT NULL,"
            + SRNO + " nvarchar(2),"
            + BarCodeHHR + " nvarchar(60),"
            + P01 + " nvarchar(60),"
            + P02 + " nvarchar(2),"
            + P03 + " nvarchar(1),"
            + P04_YY + " nvarchar(2),"
            + P04_MM + " nvarchar(2),"
            + P04_WKS + " nvarchar(2),"
            + P05 + " nvarchar(3),"
            + P06 + " nvarchar(1),"
            + P07 + " nvarchar(2),"
            + P08 + " nvarchar(1),"
            + P09 + " nvarchar(1),"
            + P10 + " nvarchar(4),"
            + P11 + " nvarchar(100),"
            + P12 + " nvarchar(1),"
            + P13 + " nvarchar(1),"
            + P13Other + " nvarchar(500),"
            + P14 + " nvarchar(1),"
            + P15 + " nvarchar(500),"
            + P16 + " nvarchar(500),"
            + P17 + " nvarchar(1),"
            + P18 + " nvarchar(1),"
            + P19 + " nvarchar(1),"
            + P20 + " nvarchar(1),"
            + P21 + " nvarchar(1),"
            + Barcode + " nvarchar(10),"
            + BloodDraw + " nvarchar(1),"
            + BloodVolume_1 + " nvarchar(1),"
            + BloodVolume_4 + " nvarchar(1),"
            + BloodVolume_6 + " nvarchar(1),"
            + BloodVolume_10 + " nvarchar(1),"
            + BloodVolumeComment + " nvarchar(1),"
            + Rapid + " nvarchar(1),"
            + RapidResults + " nvarchar(1),"
            + BloodLabTest + " nvarchar(1),"
            + BloodStore + " nvarchar(1),"
            + RapidDate + " datetime,"
            + BloodSampleCollected + " nvarchar(1),"
            + ChPrntlConsentBloodDraw + " nvarchar(1),"
            + ChPrntlConsentRHT + " nvarchar(1),"
            + ChPrntlConsentLabTest + " nvarchar(1),"
            + ChPrntlConsentBloodStore + " nvarchar(1),"
            + B3_Guardian + " nvarchar(50),"
            + ChPrntlConsentDate + " datetime,"
            + PrntlConsentX_Ray + " nvarchar(1),"
            + PrntlConsentX_RayReview + " nvarchar(1),"
            + PrntlConsentX_RayStore + " nvarchar(1),"
            + PrntlConsentSP_Collect + " nvarchar(1),"
            + PrntlParentSP_AddTests + " nvarchar(1),"
            + PrntlConsentSP_LabTest + " nvarchar(1),"
            + PrntlConsentTBDate + " datetime,"
            + IndTB_X_Ray + " nvarchar(1),"
            + IndTB_X_RayReview + " nvarchar(1),"
            + IndTB_X_RayStore + " nvarchar(1),"
            + IndSP_Collect + " nvarchar(1),"
            + IndSP_AddTests + " nvarchar(50),"
            + IndSP_LabTests + " nvarchar(1),"
            + IndTB_ConsentDate + " datetime)";


    //+ B3_Date + " date,"
    // + U15Rapid_Result + " nvarchar(1),"
    // + BloodConsent + " nvarchar(1))";


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
            + Assignment_IDi + " nvarchar(13),"
            + BatchNumberi + " nvarchar(4),"
            + SRNOi + " nvarchar(2),"
            + IndBarcode + " nvarchar(50),"
            + Q101 + " nvarchar(1),"
            + Q102 + " nvarchar(2),"
            + Q103 + " nvarchar(1),"
            + Q104 + " nvarchar(4),"
            + Q104a + " nvarchar(4),"
            + Q104b + " nvarchar(4),"
            + Q104c + " nvarchar(100),"
            + Q104cBISCED + " nvarchar(4),"
            + Q105 + " nvarchar(1),"
            + Q105Other + " nvarchar(500),"
            + Q105a + " nvarchar(500),"
            + Q105aBOSCO + " nvarchar(4),"
            + Q105b + " nvarchar(500),"
            + Q105bBISIC + " nvarchar(4),"
            + Q106 + " nvarchar(1),"
            + Q106a + " nvarchar(1),"
            + Q106aOther + " nvarchar(500),"
            + Q106b + " nvarchar(2),"
            + Q106c + " nvarchar(500),"
            + Q106cBOSCO + " nvarchar(4),"
            + Q106d + " nvarchar(500),"
            + Q106dBISIC + " nvarchar(500),"
            + Q107 + " nvarchar(1),"
            + Q107a + " nvarchar(4),"
            + Q107b + " nvarchar(1),"
            + Q107bOther + " nvarchar(500),"
            + Q107c + " nvarchar(1),"
            + Q107cOther + " nvarchar(500),"
            + Q201 + " nvarchar(1),"
            + Q202 + " nvarchar(1),"
            + Q203 + " nvarchar(2),"
            + Q204 + " nvarchar(2),"
            + Q205 + " nvarchar(1),"
            + Q205a + " nvarchar(1),"
            + Q301 + " nvarchar(1),"
            + Q301a + " nvarchar(1),"
            + Q302 + " nvarchar(1),"
            + Q303 + " nvarchar(1),"
            + Q303a + " nvarchar(1),"
            + Q304 + " nvarchar(1),"
            + Q304a + " nvarchar(1),"
            + Q305Smoking + " nvarchar(1),"
            + Q305Sniffing + " nvarchar(1),"
            + Q305Chewing + " nvarchar(1),"
            + Q305None + " nvarchar(1),"
            + Q306 + " nvarchar(1),"
            + Q307 + " nvarchar(1),"
            + Q401 + " nvarchar(1),"
            + Q402 + " nvarchar(2),"
            + Q402a + " nvarchar(1),"
            + Q402b + " nvarchar(1),"
            + Q403 + " nvarchar(1),"
            + Q404Vaginal + " nvarchar(1),"
            + Q404Anal + " nvarchar(1),"
            + Q404Oral + " nvarchar(1),"
            + Q404a + " nvarchar(1),"
            + Q405 + " nvarchar(1),"
            + Q406 + " nvarchar(2),"
            + Q407 + " nvarchar(1),"
            + Q408 + " nvarchar(1),"
            + Q408a + " nvarchar(1),"
            + Q410Slapped + " nvarchar(1),"
            + Q410Pushed + " nvarchar(1),"
            + Q410Chocked + " nvarchar(1),"
            + Q410Threatened + " nvarchar(1),"
            + Q410Physical + " nvarchar(1),"
            + Q410Forced + " nvarchar(1),"
            + Q410MadeAfraid + " nvarchar(1),"
            + Q501 + " nvarchar(1),"
            + Q502 + " nvarchar(1),"
            + Q503 + " nvarchar(1),"
            + Q504_Pain + " nvarchar(1),"
            + Q504_Reduced + " nvarchar(1),"
            + Q504_Fear + " nvarchar(1),"
            + Q504_Culture + " nvarchar(1),"
            + Q504_Religion + " nvarchar(1),"
            + Q504_Spouse + " nvarchar(1),"
            + Q504_Parental + " nvarchar(1),"
            + Q504_Long + " nvarchar(1),"
            + Q504_FearHIV + " nvarchar(1),"
            + Q504_Other + " nvarchar(1),"
            + Q504_OtherSpecify + " nvarchar(100),"
            + Q601 + " nvarchar(1),"
            + Q601a + " nvarchar(1),"
            + Q602Youth + " nvarchar(2),"
            + Q602TV + " nvarchar(2),"
            + Q602Radio + " nvarchar(2),"
            + Q602Newspaper + " nvarchar(2),"
            + Q602Hospital + " nvarchar(2),"
            + Q602Posters + " nvarchar(2),"
            + Q602Traditional + " nvarchar(2),"
            + Q602Workshop + " nvarchar(2),"
            + Q602Individual + " nvarchar(2),"
            + Q602Church + " nvarchar(2),"
            + Q602Kgotla + " nvarchar(2),"
            + Q602Workplace + " nvarchar(2),"
            + Q602Peer + " nvarchar(2),"
            + Q602School + " nvarchar(2),"
            + Q602_Other + " nvarchar(255),"
            + Q603Condom + " nvarchar(1),"
            + Q603FewerP + " nvarchar(1),"
            + Q603Both + " nvarchar(1),"
            + Q603NoCasual + " nvarchar(1),"
            + Q603Abstain + " nvarchar(1),"
            + Q603NoCommercial + " nvarchar(1),"
            + Q603Injection + " nvarchar(1),"
            + Q603Blood + " nvarchar(1),"
            + Q603DontKnow + " nvarchar(1),"
            + Q603Other + " nvarchar(255),"
            + Q604 + " nvarchar(1),"
            + Q604a + " nvarchar(1),"
            + Q604bYouth + " nvarchar(2),"
            + Q604bTV + " nvarchar(2),"
            + Q604bRadio + " nvarchar(2),"
            + Q604bNewspaper + " nvarchar(2),"
            + Q604bHospital + " nvarchar(2),"
            + Q604bPoster + " nvarchar(2),"
            + Q604bTraditional + " nvarchar(2),"
            + Q604bWorkshop + " nvarchar(2),"
            + Q604bIndividual + " nvarchar(2),"
            + Q604bChurch + " nvarchar(2),"
            + Q604bKgotla + " nvarchar(2),"
            + Q604bWorkplace + " nvarchar(2),"
            + Q604bPeer + " nvarchar(2),"
            + Q604bSchool + " nvarchar(2),"
            + Q604bOther + " nvarchar(255),"
            + Q605Windows + " nvarchar(1),"
            + Q605Mouth + " nvarchar(1),"
            + Q605Hands + " nvarchar(1),"
            + Q605Nutrition + " nvarchar(1),"
            + Q605Praying + " nvarchar(1),"
            + Q605DontKnow + " nvarchar(1),"
            + Q605Other + " nvarchar(255),"

            + Q606 + " nvarchar(1),"
            + Q607 + " nvarchar(1),"
            + Q608 + " nvarchar(1),"
            + Q609 + " nvarchar(1),"
            + Q610 + " nvarchar(1),"
            + Q611a + " nvarchar(1),"
            + Q611b + " nvarchar(1),"
            + Q611c + " nvarchar(1),"
            + Q612 + " nvarchar(1),"
            + Q612a + " nvarchar(1),"
            + Q612Other + " nvarchar(255),"
            + Q613 + " nvarchar(1),"
            + Q613a + " nvarchar(1),"
            + Q613aOther + " nvarchar(255),"
            + Q614 + " nvarchar(1),"
            + Q614Other + " nvarchar(255),"
            + Q615 + " nvarchar(1),"
            + Q616Anybody + " nvarchar(1),"
            + Q616Poor + " nvarchar(1),"
            + Q616Homeless + " nvarchar(1),"
            + Q616Alcoholics + " nvarchar(1),"
            + Q616Drugs + " nvarchar(1),"
            + Q616PeopHIV + " nvarchar(1),"
            + Q616PeopPrison + " nvarchar(1),"
            + Q616Smokers + " nvarchar(1),"
            + Q616DntKnow + " nvarchar(1),"
            + Q616Other + " nvarchar(255),"
            + Q617Meal + " nvarchar(1),"
            + Q617Clothes + " nvarchar(1),"
            + Q617Miscarried + " nvarchar(1),"
            + Q617Widow + " nvarchar(1),"
            + Q617FamilyHIV + " nvarchar(1),"
            + Q617Sejeso + " nvarchar(1),"
            + Q617Touching + " nvarchar(1),"
            + Q617Someone + " nvarchar(1),"
            + Q617Other + " nvarchar(255),"
            + Q618 + " nvarchar(1),"
            + Q619Rash + " nvarchar(2),"
            + Q619Cough + " nvarchar(2),"
            + Q619LongCough + " nvarchar(2),"
            + Q619Blood + " nvarchar(2),"
            + Q619Headache + " nvarchar(2),"
            + Q619Nausea + " nvarchar(2),"
            + Q619Weight + " nvarchar(2),"
            + Q619Fever + " nvarchar(2),"
            + Q619Fever7Days + " nvarchar(2),"
            + Q619ChestPain + " nvarchar(2),"
            + Q619Breath + " nvarchar(2),"
            + Q619Fatigue + " nvarchar(2),"
            + Q619Sweats + " nvarchar(2),"
            + Q619DontKnow + " nvarchar(2),"
            + Q619Other + " nvarchar(255),"
            + Q620 + " nvarchar(1),"
            + Q620Other + " nvarchar(255),"
            + Q621 + " nvarchar(1),"
            + Q621aSpouse + " nvarchar(1),"
            + Q621aPartner + " nvarchar(1),"
            + Q621aFriend + " nvarchar(1),"
            + Q621aFamily + " nvarchar(1),"
            + Q621aRelative + " nvarchar(1),"
            + Q621aHCWorker + " nvarchar(1),"
            + Q621aCoWorker + " nvarchar(1),"
            + Q621aOther + " nvarchar(255),"
            + Q621b + " nvarchar(1),"
            + Q621bOther + " nvarchar(255),"
            + Q622 + " nvarchar(1),"
            + Q622a + " nvarchar(1),"
            + Q622aOther + " nvarchar(255),"
            + Q622b + " nvarchar(1),"
            + Q622bOther + " nvarchar(255),"
            + Q623 + " nvarchar(1),"
            + Q624 + " nvarchar(1),"
            + Q625 + " nvarchar(1),"
            + Q701 + " nvarchar(1),"
            + Q702 + " nvarchar(1),"
            + Q703 + " nvarchar(2),"
            + Q704 + " nvarchar(1),"
            + Q705 + " nvarchar(1),"
            + Q801 + " nvarchar(1),"
            + Q801a + " nvarchar(1),"
            + Q801b + " nvarchar(1),"
            + Q801c + " nvarchar(6),"
            + Q801d + " nvarchar(2),"
            + Q801dOther + " nvarchar(255),"
            + Q801e + " nvarchar(10),"
            + Q801eOther + " nvarchar(255),"
            + Q801f + " nvarchar(1),"
            + Q802 + " nvarchar(1),"
            + Q802a + " nvarchar(1),"
            + Q802aOther + " nvarchar(255),"
            + Q803 + " nvarchar(1),"
            + Q803Other + " nvarchar(255),"
            + Q804 + " nvarchar(1),"
            + Q804Other + " nvarchar(255),"
            + Q901 + " nvarchar(1),"
            + Q901a + " nvarchar(2),"
            + Q901aOther + " nvarchar(255),"
            + Q902 + " nvarchar(6),"
            + Q903DenyCare + " nvarchar(1),"
            + Q903Gossip + " nvarchar(1),"
            + Q903NoSex + " nvarchar(1),"
            + Q903VerbalAbuse + " nvarchar(1),"
            + Q903PhysicalAbuse + " nvarchar(1),"
            + Q903NoContact + " nvarchar(1),"
            + Q903SharingStatus + " nvarchar(1),"
            + Q904 + " nvarchar(1),"
            + Q904a + " nvarchar(1),"
            + Q904aOther + " nvarchar(255),"
            + Q904b + " nvarchar(6),"
            + Q904c + " nvarchar(2),"
            + Q904cOther + " nvarchar(255),"
            + Q905 + " nvarchar(2),"
            + Q905a + " nvarchar(1),"
            + Q905aOther + " nvarchar(255),"
            + Q1001 + " nvarchar(1),"
            + Q1002 + " nvarchar(1),"
            + Q1002aMCondom + " nvarchar(1),"
            + Q1002aFCondom + " nvarchar(1),"
            + Q1002aInjectContra + " nvarchar(1),"
            + Q1002aOralContra + " nvarchar(1),"
            + Q1002aUID + " nvarchar(1),"
            + Q1002aBTL + " nvarchar(1),"
            + Q1002aFSterilization + " nvarchar(1),"
            + Q1002aMSterilization + " nvarchar(1),"
            + Q1002aImplants + " nvarchar(1),"
            + Q1002aEContra + " nvarchar(1),"
            + Q1002aSafePeriod + " nvarchar(1),"
            + Q1002aLAM + " nvarchar(1),"
            + Q1002aDiagraphm + " nvarchar(1),"
            + Q1002aSpermicides + " nvarchar(1),"
            + Q1002aNatural + " nvarchar(1),"
            + Q1002aTraditional + " nvarchar(1),"
            + Q1002aSpiritual + " nvarchar(1),"
            + Q1002aOther + " nvarchar(255),"
            + Q1002b + " nvarchar(1),"
            + Q1002bOther + " nvarchar(255),"
            + Q1003 + " nvarchar(1),"
            + Q1004 + " nvarchar(8),"
            + Q1004a + " nvarchar(1),"
            + Q1004b + " nvarchar(2),"
            + Q1004bOther + " nvarchar(255),"
            + Q1005 + " nvarchar(1),"
            + Q1005a + " nvarchar(1),"
            + Q1006 + " nvarchar(1),"
            + Q1007 + " nvarchar(1),"
            + Q1007a + " nvarchar(1),"
            + Q1008 + " nvarchar(1),"
            + Q1008a + " nvarchar(1),"
            + Q1008aOther + " nvarchar(255),"
            + Q1009 + " nvarchar(1),"
            + Q1009a + " nvarchar(1),"
            + Q1010 + " nvarchar(1),"
            + Q1010Other + " nvarchar(255),"
            + Q1011 + " nvarchar(1),"
            + Q1011Other + " nvarchar(100),"
            + Q1012 + " nvarchar(8),"
            + Q1012Still + " nvarchar(1),"
            + Q1013 + " nvarchar(1),"
            + Q1014 + " nvarchar(1),"
            + Q1014a + " nvarchar(1),"
            + Q1014b + " nvarchar(1),"
            + Q1015 + " nvarchar(1),"
            + Q1015a + " nvarchar(1),"
            + Q1015b + " nvarchar(1),"
            + Q1016 + " nvarchar(1),"
            + Q1017 + " nvarchar(1),"
            + Q1101 + " nvarchar(1),"
            + Q1101a + " nvarchar(1),"
            + Q1101aOther + " nvarchar(255),"
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
            + Q1106bOther + " nvarchar(255),"
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
            + Q1111Other + " nvarchar(255),"
            + Q1112 + " nvarchar(1),"
            + Q1112Other + " nvarchar(255),"
            + Q1113 + " nvarchar(1),"
            + Q1113Other + " nvarchar(255),"
            + Q1114 + " nvarchar(1),"

            + IndvQuestionnairePConsent15_17 + " nvarchar(1),"
            + IndvQuestionnaireConsent + " nvarchar(1),"
            + IndvBloodDraw + " nvarchar(1),"
            + BloodVol_1 + " nvarchar(1),"
            + BloodVol_4 + " nvarchar(1),"
            + BloodVol_6 + " nvarchar(1),"
            + BloodVol_10 + " nvarchar(1),"
            + BloodVolComment + " nvarchar(255),"
            + B8_O15_Rapid + " nvarchar(1),"
            + IndRapidResults + " nvarchar(255),"
            + IndBloodLabTest + " nvarchar(1),"
            + IndBloodStore + " nvarchar(1),"
            + IndRapidDate + " datetime,"
            + IndBloodSampleCollected + " nvarchar(1),"
            + PrntlConsentBloodDraw + " nvarchar(1),"
            + PrntlConsentRHT + " nvarchar(1),"
            + PrntlConsentLabTest + " nvarchar(1),"
            + PrntlConsentBloodStore + " nvarchar(1),"
            + PrntlParentID + " nvarchar(1),"
            + PrntlConsentDate + " datetime,"
            + Sync + " nvarchar(1),"
            + VISIT1 + " nvarchar(1),"
            + VISIT2 + " nvarchar(1),"
            + VISIT3 + " nvarchar(1),"
            + INDDATE1 + " datetime,"
            + INDDATE2 + " datetime,"
            + INDDATE3 + " datetime)";


    // + RapidResultsOther + " nvarchar(1),"
    // + IndRapid_Comment + " nvarchar(50),"
    //+ B8_Yes_No + " nvarchar(1),"
    //+ B8_Date + " datetime,"
    //+ B8_O15_Rapid + " nvarchar(1),"
    // + BloodConsent + " nvarchar(1),"
    //+ Rapid_Comment + " nvarchar(100)


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Log.d("Creating: ", "Passed");
            createTables(db);

        } catch (Exception r) {
            Log.d("Error: ", r.toString());
        }


    }


    public void dropTables(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + tblusers);
        db.execSQL("DROP TABLE IF EXISTS " + tblAssignments);
        db.execSQL("DROP TABLE IF EXISTS " + tblhousehold);
        db.execSQL("DROP TABLE IF EXISTS " + tblhhroster);
        db.execSQL("DROP TABLE IF EXISTS " + tblindividual);

    }

    public void createTables(SQLiteDatabase db) {
        try {
            Log.d("Inidividual:++++++ ", Create_Table_individual);
            db.execSQL(Create_Table_Users);
            db.execSQL(Create_Table_sample);
            db.execSQL(Create_Table_Assignments);
            db.execSQL(Create_Table_Household);
            db.execSQL(Create_Table_HhRoster);
            db.execSQL(Create_Table_individual);


        } catch (Exception e) {
            Log.d("Database Error", e.toString());
            e.printStackTrace();
        }


    }

    public void insertTables(SQLiteDatabase db) {

        // db.execSQL(Insert_EA);
        //db.execSQL(Insert_househol);
        // db.execSQL(Insert_household);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        onCreate(db);
    }

    public boolean insertEAAssignment(Assignments assign) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues eaassignmentstValues = new ContentValues();
        //contentValues.put("UserId", UserId);
        eaassignmentstValues.put("EA_Assignment_ID", assign.getAssignment_ID());
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


        try {

            int nombr = 0;
            Cursor cursor = db.rawQuery("SELECT * FROM EA_Assignments WHERE EA_Assignment_ID = " + assign.getAssignment_ID(), null);
            nombr = cursor.getCount();
            if (nombr == 1) {
                return true;
            } else {
                db.insert("EA_Assignments", null, eaassignmentstValues);
            }


        } catch (Exception e) {

        }

        return true;
    }


    public String checkNull(String s){
        String f=null;
        if(s!=null){
        if(s.trim().equals("") || s.isEmpty()){

        }else{
            if(s.trim().length()==0 || s.trim().equals("null") || s.trim().equals("nullnu") || s.trim().equals("nullnull") || s.trim().equals("nullnullnull") || s.trim().equals("nullnullnullnull")){
                f=null;
            }else {
                f=s;
            }
        }}
        else{
            f=null;
        }
        return f;
    }

    public boolean inserthousehold(HouseHold house) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BatchNumber, house.getBatchNumber());
        contentValues.put(Assignment_ID, house.getAssignment_ID());
        contentValues.put(DWELLING_NO, house.getDWELLING_NO());
        contentValues.put(HH_NO, house.getHH_NO());
        contentValues.put(RESP_LINE, "");
        contentValues.put(ENUMERATOR, house.getENUMERATOR());
        contentValues.put(SUPERVISOR, house.getSUPERVISOR());
        contentValues.put(QUALITY_CONTROLLER, house.getQUALITY_CONTROLLER());
        contentValues.put(INTERVIEWER_VISITS1, checkNull(house.getINTERVIEWER_VISITS1()));
        contentValues.put(DATE1, checkNull(house.getDATE1()));
        contentValues.put(VISIT1_RESULT, checkNull(house.getVISIT1_RESULT()));
        contentValues.put(COMMENT1, checkNull(house.getCOMMENT1()));
        contentValues.put(NEXT_VISIT_2_DATE, checkNull(house.getNEXT_VISIT_2_DATE()));
        contentValues.put(NEXT_VISIT_2_Time, checkNull(house.getNEXT_VISIT_2()));
        contentValues.put(INTERVIEWER_VISITS2, checkNull(house.getINTERVIEWER_VISITS2()));
        contentValues.put(DATE2, checkNull(house.getDATE2()));
        contentValues.put(VISIT2_RESULT, checkNull(house.getVISIT2_RESULT()));
        contentValues.put(COMMENT2, checkNull(house.getCOMMENT2()));
        contentValues.put(NEXT_VISIT_3_DATE, checkNull(house.getNEXT_VISIT_3_DATE()));
        contentValues.put(NEXT_VISIT_3_Time, checkNull(house.getNEXT_VISIT_3()));
        contentValues.put(INTERVIEWER_VISITS3, checkNull(house.getINTERVIEWER_VISITS3()));
        contentValues.put(DATE3, checkNull(house.getDATE3()));
        contentValues.put(VISIT3_RESULT, checkNull(house.getVISIT3_RESULT()));
        contentValues.put(COMMENT_3, checkNull(house.getCOMMENT_3()));
        contentValues.put(TOTAL_VISITS, checkNull(house.getTOTAL_VISITS()));
        contentValues.put(SuperComment, checkNull(house.getSuperComment()));
        //contentValues.put(QcComment, checkNull(house.getQcComment()));
        //contentValues.put(HQComment, checkNull(house.getHQComment()));
        contentValues.put(Sample_FK, checkNull(house.getSample_FK()));
        contentValues.put(CONSENT, checkNull(house.getCONSENT()));
        contentValues.put(CHECKED_BY, checkNull(house.getCHECKED_BY()));
        contentValues.put(CODED, checkNull(house.getCODED()));
        contentValues.put(FINAL_RESULT, checkNull(house.getFINAL_RESULT()));
        contentValues.put(FINAL_OTHER, checkNull(house.getFINAL_OTHER()));
        contentValues.put(Interview_Status, checkNull(house.getInterview_Status()));
        contentValues.put(Clear, checkNull(house.getClear()));
        contentValues.put(H01, checkNull(house.getH01()));
        contentValues.put(H02, checkNull(house.getH02()));
        contentValues.put(H03, checkNull(house.getH03()));
        contentValues.put(H03Other, checkNull(house.getH03Other()));
        contentValues.put(H04, checkNull(house.getH04()));
        contentValues.put(H05, checkNull(house.getH05()));
        contentValues.put(H06, checkNull(house.getH06()));
        contentValues.put(H07, checkNull(house.getH07()));
        contentValues.put(H08, checkNull(house.getH08()));
        contentValues.put(H09, checkNull(house.getH09()));
        contentValues.put(H10, checkNull(house.getH10()));
        contentValues.put(H11, checkNull(house.getH11()));
        contentValues.put(H12Radio, checkNull(house.getH12()));
        contentValues.put(H13Vehicle, checkNull(house.getH13()));
        contentValues.put("HIVTB40", checkNull(house.getHIVTB40()));
        contentValues.put("H12Radio", checkNull(house.getH12()));
        contentValues.put("H12TV", checkNull(house.getH12TV()));
        contentValues.put("H12Telephone", checkNull(house.getH12Telephone()));
        contentValues.put("H12CellPhone", checkNull(house.getH12CellPhone()));
        contentValues.put("H12PrintMedia", checkNull(house.getH12PrintMedia()));
        contentValues.put("H12ElecMedia", checkNull(house.getH12ElecMedia()));
        contentValues.put("H12PerformArts", checkNull(house.getH12PerfomArts()));

        contentValues.put("H13Vehicle", checkNull(house.getH13()));
        contentValues.put("H13Tractor", checkNull(house.getH13Tractor()));
        contentValues.put("H13Motorcycle", checkNull(house.getH13Motorcycle()));
        contentValues.put("H13Bicycle", checkNull(house.getH13Bicycle()));
        contentValues.put("H13DonkeyCart", checkNull(house.getH13DonkeyCart()));
        contentValues.put("H13DonkeyHorse", checkNull(house.getH13DonkeyHorse()));
        contentValues.put("H13Camels", checkNull(house.getH13Camels()));
        contentValues.put("H13Camels", checkNull(house.getH13Camels()));


        db.insert(tblhousehold, null, contentValues);

        return true;


    }

    public boolean checkUser(PersonRoster p) {
        SQLiteDatabase db = this.getWritableDatabase();


        int nombr = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + tblhhroster + " WHERE Assignment_ID = " + p.getAssignmentID() + " and BatchNumber=" + p.getBatch() + " and SRNO=" + p.getSRNO(), null);
        nombr = cursor.getCount();
        if (nombr == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkIndividual(Individual ind) {
        SQLiteDatabase db = this.getWritableDatabase();


        int nombr = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + tblindividual + " WHERE Assignment_ID = " + ind.getAssignmentID() + " and BatchNumber=" + ind.getBatch() + " and SRNO=" + ind.getSRNO(), null);
        nombr = cursor.getCount();
        if (nombr == 1) {
            return true;
        } else {
            return false;
        }

    }

    //INSERT ROSTER FIRST SYNC
    public boolean insertSyncRoster(PersonRoster pr, String AsID, String Batch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhrosterValues = new ContentValues();

        hhrosterValues.put(EA_Assignment_ID, AsID);
        hhrosterValues.put(BatchNumberR, Batch);
        hhrosterValues.put(SRNO, pr.getLineNumber());
        hhrosterValues.put(P01, checkNull(pr.getP01()));
        hhrosterValues.put(P02, checkNull(pr.getP02()));
        hhrosterValues.put(P03, checkNull(pr.getP03()));
        hhrosterValues.put(P04_YY, checkNull(pr.getP04YY()));
        hhrosterValues.put(P04_MM, checkNull(pr.getP04MM()));
        hhrosterValues.put(P04_WKS, checkNull(pr.getP04WKS()));
        hhrosterValues.put(P05, checkNull(pr.getP05()));
        hhrosterValues.put(P06, checkNull(pr.getP06()));
        hhrosterValues.put(P07, checkNull(pr.getP07()));
        hhrosterValues.put(P08, checkNull(pr.getP08()));
        hhrosterValues.put(P09, checkNull(pr.getP09()));
        hhrosterValues.put(P10, checkNull(pr.getP10()));
        hhrosterValues.put(P11, checkNull(pr.getP11()));
        hhrosterValues.put(P12, checkNull(pr.getP12()));
        hhrosterValues.put(P13, checkNull(pr.getP13()));
        hhrosterValues.put(P14, checkNull(pr.getP14()));
        hhrosterValues.put(P15, checkNull(pr.getP15()));
        hhrosterValues.put(P16, checkNull(pr.getP16()));
        hhrosterValues.put(P17, checkNull(pr.getP17()));
        hhrosterValues.put(P18, checkNull(pr.getP18()));
        hhrosterValues.put(P19, checkNull(pr.getP19()));
        hhrosterValues.put(P20, checkNull(pr.getP20()));
        hhrosterValues.put(P21, checkNull(pr.getP21()));
        //B3_RapidConsent_Yes_No
        //B3_Guardian
        //B3_Date_Consent
        // hhrosterValues.put(BloodSampleCollected, pr.getBloodSampleCollected());
        hhrosterValues.put(Barcode, checkNull(pr.getBarcode()));
        //hhrosterValues.put(RapidResults, pr.getRapidResults());
        //hhrosterValues.put(RapidDate, pr.getRapidDate());
        //hhrosterValues.put(Rapid_Comment, pr.getRapid_Comment());
        hhrosterValues.put(BloodDraw, checkNull(pr.getBloodDraw()));
        hhrosterValues.put(BloodVolume_1, checkNull(pr.getBloodVolume_1()));
        hhrosterValues.put(BloodVolume_4, checkNull(pr.getBloodVolume_4()));
        hhrosterValues.put(BloodVolume_6, checkNull(pr.getBloodVolume_6()));
        hhrosterValues.put(BloodVolume_10, checkNull(pr.getBloodVolume_10()));
        hhrosterValues.put(BloodVolumeComment, checkNull(pr.getBloodVolumeComment()));
        hhrosterValues.put(Rapid, checkNull(pr.getRapid()));
        hhrosterValues.put(RapidResults, checkNull(pr.getRapidResults()));
        hhrosterValues.put(BloodLabTest, checkNull(pr.getBloodLabTest()));
        hhrosterValues.put(BloodStore,checkNull(pr.getBloodStore()));
        hhrosterValues.put(RapidDate, checkNull(pr.getRapidDate()));
        hhrosterValues.put(BloodSampleCollected, checkNull(pr.getBloodSampleCollected()));
        hhrosterValues.put(ChPrntlConsentBloodDraw, checkNull(pr.getChPrntlConsentBloodDraw()));
        hhrosterValues.put(ChPrntlConsentRHT, checkNull(pr.getChPrntlConsentRHT()));
        hhrosterValues.put(ChPrntlConsentLabTest, checkNull(pr.getChPrntlConsentLabTest()));
        hhrosterValues.put(ChPrntlConsentBloodStore, checkNull(pr.getChPrntlConsentBloodStore()));
        hhrosterValues.put(B3_Guardian, checkNull(pr.getB3_Guardian()));
        hhrosterValues.put(ChPrntlConsentDate, checkNull(pr.getChPrntlConsentDate()));
        hhrosterValues.put(PrntlConsentX_Ray, checkNull(pr.getPrntlConsentX_Ray()));
        hhrosterValues.put(PrntlConsentX_RayReview, checkNull(pr.getPrntlConsentX_RayReview()));
        hhrosterValues.put(PrntlConsentX_RayStore, checkNull(pr.getPrntlConsentX_RayStore()));
        hhrosterValues.put(PrntlConsentSP_Collect, checkNull(pr.getPrntlConsentSP_Collect()));
        hhrosterValues.put(PrntlParentSP_AddTests, checkNull(pr.getPrntlParentSP_AddTests()));
        hhrosterValues.put(PrntlConsentSP_LabTest, checkNull(pr.getPrntlConsentSP_LabTest()));
        hhrosterValues.put(PrntlConsentTBDate, checkNull(pr.getPrntlConsentTBDate()));
        hhrosterValues.put(IndTB_X_Ray, checkNull(pr.getIndTB_X_Ray()));
        hhrosterValues.put(IndTB_X_RayReview, checkNull(pr.getIndTB_X_RayReview()));
        hhrosterValues.put(IndTB_X_RayStore, checkNull(pr.getIndTB_X_RayStore()));
        hhrosterValues.put(IndSP_Collect, checkNull(pr.getIndSP_Collect()));
        hhrosterValues.put(IndSP_AddTests, checkNull(pr.getIndSP_AddTests()));
        hhrosterValues.put(IndSP_LabTests, checkNull(pr.getIndSP_LabTests()));
        hhrosterValues.put(IndTB_ConsentDate, checkNull(pr.getIndTB_ConsentDate()));

        db.insert(tblhhroster, null, hhrosterValues);


        return true;
    }

    //INSERT ROSTER FROM HOUSEHOLD
    public boolean insertHhroster(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues hhrosterValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        for (int i = 0; i < houseHold.getPersons().length; i++) {
            hhrosterValues.put(EA_Assignment_ID, houseHold.getAssignment_ID());
            hhrosterValues.put(BatchNumberR, houseHold.getBatchNumber());
            hhrosterValues.put(SRNO, houseHold.getPersons()[i].getLineNumber());
            hhrosterValues.put(P01, houseHold.getPersons()[i].getP01());
            hhrosterValues.put(P02, houseHold.getPersons()[i].getP02());
            hhrosterValues.put(P03, houseHold.getPersons()[i].getP03());
            hhrosterValues.put(P04_YY, houseHold.getPersons()[i].getP04YY());
            hhrosterValues.put(P04_MM, houseHold.getPersons()[i].getP04MM());
            hhrosterValues.put(P04_WKS, houseHold.getPersons()[i].getP04WKS());
            hhrosterValues.put(P05, houseHold.getPersons()[i].getP05());
            hhrosterValues.put(P06, houseHold.getPersons()[i].getP06());
            hhrosterValues.put(P07, houseHold.getPersons()[i].getP07());
            hhrosterValues.put(P08, houseHold.getPersons()[i].getP08());
            hhrosterValues.put(P09, houseHold.getPersons()[i].getP09());
            hhrosterValues.put(P10, houseHold.getPersons()[i].getP10());
            hhrosterValues.put(P11, houseHold.getPersons()[i].getP11());
            hhrosterValues.put(P12, houseHold.getPersons()[i].getP12());
            hhrosterValues.put(P13, houseHold.getPersons()[i].getP13());
            hhrosterValues.put(P14, houseHold.getPersons()[i].getP14());
            hhrosterValues.put(P15, houseHold.getPersons()[i].getP15());
            hhrosterValues.put(P16, houseHold.getPersons()[i].getP16());
            hhrosterValues.put(P17, houseHold.getPersons()[i].getP17());
            hhrosterValues.put(P18, houseHold.getPersons()[i].getP18());
            hhrosterValues.put(P19, houseHold.getPersons()[i].getP19());
            hhrosterValues.put(P20, houseHold.getPersons()[i].getP20());
            hhrosterValues.put(P21, houseHold.getPersons()[i].getP21());
            // hhrosterValues.put(BloodSampleCollected, houseHold.getPersons()[i].getBloodSampleCollected());
            hhrosterValues.put(Barcode, houseHold.getPersons()[i].getBarcode());


            hhrosterValues.put(BloodDraw, houseHold.getPersons()[i].getBloodDraw());
            hhrosterValues.put(BloodVolume_1, houseHold.getPersons()[i].getBloodVolume_1());
            hhrosterValues.put(BloodVolume_4, houseHold.getPersons()[i].getBloodVolume_4());
            hhrosterValues.put(BloodVolume_6, houseHold.getPersons()[i].getBloodVolume_6());
            hhrosterValues.put(BloodVolume_10, houseHold.getPersons()[i].getBloodVolume_10());
            hhrosterValues.put(BloodVolumeComment, houseHold.getPersons()[i].getBloodVolumeComment());
            hhrosterValues.put(Rapid, houseHold.getPersons()[i].getRapid());
            hhrosterValues.put(RapidResults, houseHold.getPersons()[i].getRapidResults());
            hhrosterValues.put(BloodLabTest, houseHold.getPersons()[i].getBloodLabTest());
            hhrosterValues.put(BloodStore, houseHold.getPersons()[i].getBloodStore());
            hhrosterValues.put(RapidDate, houseHold.getPersons()[i].getRapidDate());
            hhrosterValues.put(BloodSampleCollected, houseHold.getPersons()[i].getBloodSampleCollected());
            hhrosterValues.put(ChPrntlConsentBloodDraw, houseHold.getPersons()[i].getChPrntlConsentBloodDraw());
            hhrosterValues.put(ChPrntlConsentRHT, houseHold.getPersons()[i].getChPrntlConsentRHT());
            hhrosterValues.put(ChPrntlConsentLabTest, houseHold.getPersons()[i].getChPrntlConsentLabTest());
            hhrosterValues.put(ChPrntlConsentBloodStore, houseHold.getPersons()[i].getChPrntlConsentBloodStore());
            hhrosterValues.put(B3_Guardian, houseHold.getPersons()[i].getB3_Guardian());
            hhrosterValues.put(ChPrntlConsentDate, houseHold.getPersons()[i].getChPrntlConsentDate());

            hhrosterValues.put(PrntlConsentX_Ray, houseHold.getPersons()[i].getPrntlConsentX_Ray());
            hhrosterValues.put(PrntlConsentX_RayReview, houseHold.getPersons()[i].getPrntlConsentX_RayReview());
            hhrosterValues.put(PrntlConsentX_RayStore, houseHold.getPersons()[i].getPrntlConsentX_RayStore());
            hhrosterValues.put(PrntlConsentSP_Collect, houseHold.getPersons()[i].getPrntlConsentSP_Collect());
            hhrosterValues.put(PrntlParentSP_AddTests, houseHold.getPersons()[i].getPrntlParentSP_AddTests());
            hhrosterValues.put(PrntlConsentSP_LabTest, houseHold.getPersons()[i].getPrntlConsentSP_LabTest());
            hhrosterValues.put(PrntlConsentTBDate, houseHold.getPersons()[i].getPrntlConsentTBDate());

            hhrosterValues.put(IndTB_X_Ray, houseHold.getPersons()[i].getIndTB_X_Ray());
            hhrosterValues.put(IndTB_X_RayReview, houseHold.getPersons()[i].getIndTB_X_RayReview());
            hhrosterValues.put(IndTB_X_RayStore, houseHold.getPersons()[i].getIndTB_X_RayStore());
            hhrosterValues.put(IndSP_Collect, houseHold.getPersons()[i].getIndSP_Collect());
            hhrosterValues.put(IndSP_AddTests, houseHold.getPersons()[i].getIndSP_AddTests());
            hhrosterValues.put(IndSP_LabTests, houseHold.getPersons()[i].getIndSP_LabTests());
            hhrosterValues.put(IndTB_ConsentDate, houseHold.getPersons()[i].getIndTB_ConsentDate());


            db.insert(tblhhroster, null, hhrosterValues);

        }

        return true;
    }


    //INSERT ROSTER FROM HOUSEHOLD
    public boolean insertPerson(PersonRoster p, String assID, String Batch) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues hhrosterValues = new ContentValues();
        hhrosterValues.put(EA_Assignment_ID, assID);
        hhrosterValues.put(BatchNumberR, Batch);
        hhrosterValues.put(SRNO, p.getLineNumber());
        hhrosterValues.put(P01, p.getP01());

        db.insert(tblhhroster, null, hhrosterValues);


        return true;
    }


    public boolean updateHhroster(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhrosterValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        for (int i = 0; i < houseHold.getPersons().length; i++) {
            hhrosterValues.put(EA_Assignment_ID, houseHold.getAssignment_ID());
            hhrosterValues.put(BatchNumberR, houseHold.getBatchNumber());
            hhrosterValues.put(SRNO, houseHold.getPersons()[i].getLineNumber());
            hhrosterValues.put(P01, houseHold.getPersons()[i].getP01());
            hhrosterValues.put(P02, houseHold.getPersons()[i].getP02());
            hhrosterValues.put(P03, houseHold.getPersons()[i].getP03());
            hhrosterValues.put(P04_YY, houseHold.getPersons()[i].getP04YY());
            hhrosterValues.put(P04_MM, houseHold.getPersons()[i].getP04MM());
            hhrosterValues.put(P04_WKS, houseHold.getPersons()[i].getP04WKS());
            hhrosterValues.put(P05, houseHold.getPersons()[i].getP05());
            hhrosterValues.put(P06, houseHold.getPersons()[i].getP06());
            hhrosterValues.put(P07, houseHold.getPersons()[i].getP07());
            hhrosterValues.put(P08, houseHold.getPersons()[i].getP08());
            hhrosterValues.put(P09, houseHold.getPersons()[i].getP09());
            hhrosterValues.put(P10, houseHold.getPersons()[i].getP10());
            hhrosterValues.put(P11, houseHold.getPersons()[i].getP11());
            hhrosterValues.put(P12, houseHold.getPersons()[i].getP12());
            hhrosterValues.put(P13, houseHold.getPersons()[i].getP13());
            hhrosterValues.put(P14, houseHold.getPersons()[i].getP14());
            hhrosterValues.put(P15, houseHold.getPersons()[i].getP15());
            hhrosterValues.put(P16, houseHold.getPersons()[i].getP16());
            hhrosterValues.put(P17, houseHold.getPersons()[i].getP17());
            hhrosterValues.put(P18, houseHold.getPersons()[i].getP18());
            hhrosterValues.put(P19, houseHold.getPersons()[i].getP19());
            hhrosterValues.put(P20, houseHold.getPersons()[i].getP20());
            hhrosterValues.put(P21, houseHold.getPersons()[i].getP21());
            hhrosterValues.put(Barcode, houseHold.getPersons()[i].getBarcode());

            hhrosterValues.put(BloodDraw, houseHold.getPersons()[i].getBloodDraw());
            hhrosterValues.put(BloodVolume_1, houseHold.getPersons()[i].getBloodVolume_1());
            hhrosterValues.put(BloodVolume_4, houseHold.getPersons()[i].getBloodVolume_4());
            hhrosterValues.put(BloodVolume_6, houseHold.getPersons()[i].getBloodVolume_6());
            hhrosterValues.put(BloodVolume_10, houseHold.getPersons()[i].getBloodVolume_10());
            hhrosterValues.put(BloodVolumeComment, houseHold.getPersons()[i].getBloodVolumeComment());
            hhrosterValues.put(Rapid, houseHold.getPersons()[i].getRapid());
            hhrosterValues.put(RapidResults, houseHold.getPersons()[i].getRapidResults());
            hhrosterValues.put(BloodLabTest, houseHold.getPersons()[i].getBloodLabTest());
            hhrosterValues.put(BloodStore, houseHold.getPersons()[i].getBloodStore());
            hhrosterValues.put(RapidDate, houseHold.getPersons()[i].getRapidDate());
            hhrosterValues.put(BloodSampleCollected, houseHold.getPersons()[i].getBloodSampleCollected());
            hhrosterValues.put(ChPrntlConsentBloodDraw, houseHold.getPersons()[i].getChPrntlConsentBloodDraw());
            hhrosterValues.put(ChPrntlConsentRHT, houseHold.getPersons()[i].getChPrntlConsentRHT());
            hhrosterValues.put(ChPrntlConsentLabTest, houseHold.getPersons()[i].getChPrntlConsentLabTest());
            hhrosterValues.put(ChPrntlConsentBloodStore, houseHold.getPersons()[i].getChPrntlConsentBloodStore());
            hhrosterValues.put(B3_Guardian, houseHold.getPersons()[i].getB3_Guardian());
            hhrosterValues.put(ChPrntlConsentDate, houseHold.getPersons()[i].getChPrntlConsentDate());

            hhrosterValues.put(PrntlConsentX_Ray, houseHold.getPersons()[i].getPrntlConsentX_Ray());
            hhrosterValues.put(PrntlConsentX_RayReview, houseHold.getPersons()[i].getPrntlConsentX_RayReview());
            hhrosterValues.put(PrntlConsentX_RayStore, houseHold.getPersons()[i].getPrntlConsentX_RayStore());
            hhrosterValues.put(PrntlConsentSP_Collect, houseHold.getPersons()[i].getPrntlConsentSP_Collect());
            hhrosterValues.put(PrntlParentSP_AddTests, houseHold.getPersons()[i].getPrntlParentSP_AddTests());
            hhrosterValues.put(PrntlConsentSP_LabTest, houseHold.getPersons()[i].getPrntlConsentSP_LabTest());
            hhrosterValues.put(PrntlConsentTBDate, houseHold.getPersons()[i].getPrntlConsentTBDate());

            hhrosterValues.put(IndTB_X_Ray, houseHold.getPersons()[i].getIndTB_X_Ray());
            hhrosterValues.put(IndTB_X_RayReview, houseHold.getPersons()[i].getIndTB_X_RayReview());
            hhrosterValues.put(IndTB_X_RayStore, houseHold.getPersons()[i].getIndTB_X_RayStore());
            hhrosterValues.put(IndSP_Collect, houseHold.getPersons()[i].getIndSP_Collect());
            hhrosterValues.put(IndSP_AddTests, houseHold.getPersons()[i].getIndSP_AddTests());
            hhrosterValues.put(IndSP_LabTests, houseHold.getPersons()[i].getIndSP_LabTests());
            hhrosterValues.put(IndTB_ConsentDate, houseHold.getPersons()[i].getIndTB_ConsentDate());


            i = db.update(tblhhroster, // table
                    hhrosterValues, // column/value
                    "EA_Assignment_ID = ? and BatchNumber=?", // selections
                    new String[]{String.valueOf(houseHold.getAssignment_ID()), String.valueOf(houseHold.getBatchNumber())});

            db.close();


        }

        return true;
    }

    //FROM HOUSE HOLD
    public boolean insertIndividuals(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues individualValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        for (int i = 0; i < houseHold.getPersons().length; i++) {
            individualValues.put(BatchNumberi, houseHold.getBatchNumber());
            individualValues.put(Assignment_IDi, houseHold.getAssignment_ID());
            individualValues.put(SRNOi, houseHold.getPersons()[i].getLineNumber());
            individualValues.put(IndBarcode, houseHold.getIndividual()[i].getIndBarcode());
            individualValues.put(Q101, houseHold.getIndividual()[i].getQ101());
            individualValues.put(Q102, houseHold.getIndividual()[i].getQ102());
            individualValues.put(Q103, houseHold.getIndividual()[i].getQ103());
            individualValues.put(Q104, houseHold.getIndividual()[i].getQ104());
            individualValues.put(Q104c, houseHold.getIndividual()[i].getQ104c());
            individualValues.put(Q104cBISCED, houseHold.getIndividual()[i].getQ104cBISCED());

            individualValues.put(Q105, houseHold.getIndividual()[i].getQ105());
            individualValues.put(Q105Other, houseHold.getIndividual()[i].getQ105Other());
            individualValues.put(Q105a, houseHold.getIndividual()[i].getQ105a());
            //individualValues.put(Q105aBOSCO, houseHold.getIndividual()[i].getQ105aBosco() );
            individualValues.put(Q105b, houseHold.getIndividual()[i].getQ105b());
            // individualValues.put(Q105bBISIC, houseHold.getIndividual()[i].getQ105bBISIC() );
            individualValues.put(Q106, houseHold.getIndividual()[i].getQ106());
            individualValues.put(Q106a, houseHold.getIndividual()[i].getQ106a());
            individualValues.put(Q106aOther, houseHold.getIndividual()[i].getQ106aOther());
            individualValues.put(Q106b, houseHold.getIndividual()[i].getQ106b());
            individualValues.put(Q106c, houseHold.getIndividual()[i].getQ106c());
            //individualValues.put(Q106cBOSCO, houseHold.getIndividual()[i].getQ106cBOSCO() );
            individualValues.put(Q106d, houseHold.getIndividual()[i].getQ106d());
            //individualValues.put(Q106dBISIC, houseHold.getIndividual()[i].getQ106dBISIC());
            individualValues.put(Q107, houseHold.getIndividual()[i].getQ107());
            individualValues.put(Q107a, houseHold.getIndividual()[i].getQ107aMnth());
            individualValues.put(Q107b, houseHold.getIndividual()[i].getQ107b());
            individualValues.put(Q107bOther, houseHold.getIndividual()[i].getQ107bOther());
            individualValues.put(Q107c, houseHold.getIndividual()[i].getQ107c());
            individualValues.put(Q107cOther, houseHold.getIndividual()[i].getQ107cOther());

            individualValues.put(Q201, houseHold.getIndividual()[i].getQ201());
            individualValues.put(Q202, houseHold.getIndividual()[i].getQ202());
            individualValues.put(Q203, houseHold.getIndividual()[i].getQ203());
            individualValues.put(Q204, houseHold.getIndividual()[i].getQ204());
            individualValues.put(Q205, houseHold.getIndividual()[i].getQ205());
            individualValues.put(Q205a, houseHold.getIndividual()[i].getQ205a());


            individualValues.put(Q301, houseHold.getIndividual()[i].getQ301());
            individualValues.put(Q301a, houseHold.getIndividual()[i].getQ301a());
            individualValues.put(Q302, houseHold.getIndividual()[i].getQ302());
            individualValues.put(Q303, houseHold.getIndividual()[i].getQ303());
            individualValues.put(Q303a, houseHold.getIndividual()[i].getQ303a());
            individualValues.put(Q304, houseHold.getIndividual()[i].getQ304());
            individualValues.put(Q304a, houseHold.getIndividual()[i].getQ304a());

            individualValues.put(Q305Smoking, houseHold.getIndividual()[i].getQ305_1());
            individualValues.put(Q305Sniffing, houseHold.getIndividual()[i].getQ305_2());
            individualValues.put(Q305Chewing, houseHold.getIndividual()[i].getQ305_3());
            individualValues.put(Q305None, houseHold.getIndividual()[i].getQ305_4());

            individualValues.put(Q306, houseHold.getIndividual()[i].getQ306());
            individualValues.put(Q307, houseHold.getIndividual()[i].getQ307());

            individualValues.put(Q401, houseHold.getIndividual()[i].getQ401());
            individualValues.put(Q402, houseHold.getIndividual()[i].getQ402());
            individualValues.put(Q402a, houseHold.getIndividual()[i].getQ402a());
            individualValues.put(Q402b, houseHold.getIndividual()[i].getQ402b());
            individualValues.put(Q403, houseHold.getIndividual()[i].getQ403());

            individualValues.put(Q404a,houseHold.getIndividual()[i].getQ404a());
            individualValues.put(Q404Vaginal,houseHold.getIndividual()[i].getQ404_1());
            individualValues.put(Q404Anal,houseHold.getIndividual()[i].getQ404_2());
            individualValues.put(Q404Oral,houseHold.getIndividual()[i].getQ404_3());

            individualValues.put(Q405,houseHold.getIndividual()[i].getQ405());
            individualValues.put(Q406,houseHold.getIndividual()[i].getQ406());
            individualValues.put(Q407,houseHold.getIndividual()[i].getQ407());
            individualValues.put(Q408,houseHold.getIndividual()[i].getQ408());
            individualValues.put(Q408a,houseHold.getIndividual()[i].getQ408a());
            individualValues.put(Q410Chocked,houseHold.getIndividual()[i].getQ410Choked());
            individualValues.put(Q410Pushed,houseHold.getIndividual()[i].getQ410Pushed());
            individualValues.put(Q410Threatened,houseHold.getIndividual()[i].getQ410Threatened());
            individualValues.put(Q410Physical,houseHold.getIndividual()[i].getQ410Physical());
            individualValues.put(Q410Forced,houseHold.getIndividual()[i].getQ410Forced());
            individualValues.put(Q410MadeAfraid,houseHold.getIndividual()[i].getQ410MadeAfraid());
            individualValues.put(Q410Slapped,houseHold.getIndividual()[i].getQ410Slapped());





            individualValues.put(Q501, houseHold.getIndividual()[i].getQ501());
            individualValues.put(Q502, houseHold.getIndividual()[i].getQ502());
            individualValues.put(Q503, houseHold.getIndividual()[i].getQ503());

            individualValues.put(Q504_Pain, houseHold.getIndividual()[i].getQ504_1());
            individualValues.put(Q504_Reduced, houseHold.getIndividual()[i].getQ504_2());
            individualValues.put(Q504_Fear, houseHold.getIndividual()[i].getQ504_3());
            individualValues.put(Q504_Culture, houseHold.getIndividual()[i].getQ504_4());
            individualValues.put(Q504_Religion, houseHold.getIndividual()[i].getQ504_5());
            individualValues.put(Q504_Spouse, houseHold.getIndividual()[i].getQ504_6());
            individualValues.put(Q504_Parental, houseHold.getIndividual()[i].getQ504_7());
            individualValues.put(Q504_Long, houseHold.getIndividual()[i].getQ504_8());
            individualValues.put(Q504_FearHIV, houseHold.getIndividual()[i].getQ504_10());
            individualValues.put(Q504_Other, houseHold.getIndividual()[i].getQ504_Other());
            individualValues.put(Q504_OtherSpecify, houseHold.getIndividual()[i].getQ504_OtherSpecify());


            individualValues.put(Q601, houseHold.getIndividual()[i].getQ601());
            individualValues.put(Q601a, houseHold.getIndividual()[i].getQ601a());
            individualValues.put(Q602Youth, houseHold.getIndividual()[i].getQ602_1());
            individualValues.put(Q602TV, houseHold.getIndividual()[i].getQ602_2());
            individualValues.put(Q602Radio, houseHold.getIndividual()[i].getQ602_3());
            individualValues.put(Q602Newspaper, houseHold.getIndividual()[i].getQ602_4());
            individualValues.put(Q602Hospital, houseHold.getIndividual()[i].getQ602_5());
            individualValues.put(Q602Posters, houseHold.getIndividual()[i].getQ602_6());
            individualValues.put(Q602Traditional, houseHold.getIndividual()[i].getQ602_7());
            individualValues.put(Q602Workshop, houseHold.getIndividual()[i].getQ602_8());
            individualValues.put(Q602Individual, houseHold.getIndividual()[i].getQ602_10());
            individualValues.put(Q602Church, houseHold.getIndividual()[i].getQ602_11());
            individualValues.put(Q602Kgotla, houseHold.getIndividual()[i].getQ602_12());
            individualValues.put(Q602Workplace, houseHold.getIndividual()[i].getQ602_13());
            individualValues.put(Q602Peer, houseHold.getIndividual()[i].getQ602_14());
            individualValues.put(Q602School, houseHold.getIndividual()[i].getQ602_15());
            individualValues.put(Q602_Other, houseHold.getIndividual()[i].getQ602_Other());

            individualValues.put(Q603Condom, houseHold.getIndividual()[i].getQ603_1());
            individualValues.put(Q603FewerP, houseHold.getIndividual()[i].getQ603_2());
            individualValues.put(Q603Both, houseHold.getIndividual()[i].getQ603_3());
            individualValues.put(Q603NoCasual, houseHold.getIndividual()[i].getQ603_4());
            individualValues.put(Q603Abstain, houseHold.getIndividual()[i].getQ603_5());
            individualValues.put(Q603NoCommercial, houseHold.getIndividual()[i].getQ603_6());
            individualValues.put(Q603Injection, houseHold.getIndividual()[i].getQ603_7());
            individualValues.put(Q603Blood, houseHold.getIndividual()[i].getQ603_8());
            individualValues.put(Q603DontKnow, houseHold.getIndividual()[i].getQ603_9());
            individualValues.put(Q603Other, houseHold.getIndividual()[i].getQ603_Other());


            individualValues.put(Q604, houseHold.getIndividual()[i].getQ604());
            individualValues.put(Q604a, houseHold.getIndividual()[i].getQ604a());
            individualValues.put(Q604bYouth, houseHold.getIndividual()[i].getQ604b_1());
            individualValues.put(Q604bTV, houseHold.getIndividual()[i].getQ604b_2());
            individualValues.put(Q604bRadio, houseHold.getIndividual()[i].getQ604b_3());
            individualValues.put(Q604bNewspaper, houseHold.getIndividual()[i].getQ604b_4());
            individualValues.put(Q604bHospital, houseHold.getIndividual()[i].getQ604b_5());
            individualValues.put(Q604bPoster, houseHold.getIndividual()[i].getQ604b_6());
            individualValues.put(Q604bTraditional, houseHold.getIndividual()[i].getQ604b_7());
            individualValues.put(Q604bWorkshop, houseHold.getIndividual()[i].getQ604b_8());
            individualValues.put(Q604bIndividual, houseHold.getIndividual()[i].getQ604b_10());
            individualValues.put(Q604bChurch, houseHold.getIndividual()[i].getQ604b_11());
            individualValues.put(Q604bKgotla, houseHold.getIndividual()[i].getQ604b_12());
            individualValues.put(Q604bWorkplace, houseHold.getIndividual()[i].getQ604b_13());
            individualValues.put(Q604bPeer, houseHold.getIndividual()[i].getQ604b_14());
            individualValues.put(Q604bSchool, houseHold.getIndividual()[i].getQ604b_15());
            individualValues.put(Q604bOther, houseHold.getIndividual()[i].getQ604b_Other());

            individualValues.put(Q605Windows, houseHold.getIndividual()[i].getQ605_1());
            individualValues.put(Q605Mouth, houseHold.getIndividual()[i].getQ605_2());
            individualValues.put(Q605Hands, houseHold.getIndividual()[i].getQ605_3());
            individualValues.put(Q605Nutrition, houseHold.getIndividual()[i].getQ605_4());
            individualValues.put(Q605Praying, houseHold.getIndividual()[i].getQ605_5());
            individualValues.put(Q605DontKnow, houseHold.getIndividual()[i].getQ605_9());
            individualValues.put(Q605Other, houseHold.getIndividual()[i].getQ605_Other());


            individualValues.put(Q606, houseHold.getIndividual()[i].getQ606());
            individualValues.put(Q607, houseHold.getIndividual()[i].getQ607());
            individualValues.put(Q608, houseHold.getIndividual()[i].getQ608());
            individualValues.put(Q609, houseHold.getIndividual()[i].getQ609());
            individualValues.put(Q610, houseHold.getIndividual()[i].getQ610());
            individualValues.put(Q611a, houseHold.getIndividual()[i].getQ611a());
            individualValues.put(Q611b, houseHold.getIndividual()[i].getQ611b());
            individualValues.put(Q611c, houseHold.getIndividual()[i].getQ611c());
            individualValues.put(Q612, houseHold.getIndividual()[i].getQ612());
            individualValues.put(Q612a, houseHold.getIndividual()[i].getQ612a());
            individualValues.put(Q612Other, houseHold.getIndividual()[i].getQ612aOther());

            individualValues.put(Q613, houseHold.getIndividual()[i].getQ613());
            individualValues.put(Q613a, houseHold.getIndividual()[i].getQ613a());
            individualValues.put(Q613aOther, houseHold.getIndividual()[i].getQ613aOther());
            individualValues.put(Q614, houseHold.getIndividual()[i].getQ614());
            individualValues.put(Q614Other, houseHold.getIndividual()[i].getQ614Other());


            individualValues.put(Q615, houseHold.getIndividual()[i].getQ615());
            individualValues.put(Q616Anybody, houseHold.getIndividual()[i].getQ616_1());
            individualValues.put(Q616Poor, houseHold.getIndividual()[i].getQ616_2());
            individualValues.put(Q616Homeless, houseHold.getIndividual()[i].getQ616_3());
            individualValues.put(Q616Alcoholics, houseHold.getIndividual()[i].getQ616_4());
            individualValues.put(Q616Drugs, houseHold.getIndividual()[i].getQ616_5());
            individualValues.put(Q616PeopHIV, houseHold.getIndividual()[i].getQ616_6());
            individualValues.put(Q616PeopPrison, houseHold.getIndividual()[i].getQ616_7());
            individualValues.put(Q616Smokers, houseHold.getIndividual()[i].getQ616_8());
            individualValues.put(Q616DntKnow, houseHold.getIndividual()[i].getQ616_9());
            individualValues.put(Q616Other, houseHold.getIndividual()[i].getQ616_10());


            individualValues.put(Q617Meal, houseHold.getIndividual()[i].getQ617a());
            individualValues.put(Q617Clothes, houseHold.getIndividual()[i].getQ617b());
            individualValues.put(Q617Miscarried, houseHold.getIndividual()[i].getQ617c());
            individualValues.put(Q617Widow, houseHold.getIndividual()[i].getQ617d());
            individualValues.put(Q617FamilyHIV, houseHold.getIndividual()[i].getQ617e());
            individualValues.put(Q617Sejeso, houseHold.getIndividual()[i].getQ617f());
            individualValues.put(Q617Touching, houseHold.getIndividual()[i].getQ617g());
            individualValues.put(Q617Someone, houseHold.getIndividual()[i].getQ617h());
            individualValues.put(Q617Other, houseHold.getIndividual()[i].getQ617_0ther());


            individualValues.put(Q618, houseHold.getIndividual()[i].getQ618());


            individualValues.put(Q619Rash, houseHold.getIndividual()[i].getQ619_1());
            individualValues.put(Q619Cough, houseHold.getIndividual()[i].getQ619_2());
            individualValues.put(Q619LongCough, houseHold.getIndividual()[i].getQ619_3());
            individualValues.put(Q619Blood, houseHold.getIndividual()[i].getQ619_4());
            individualValues.put(Q619Headache, houseHold.getIndividual()[i].getQ619_5());
            individualValues.put(Q619Nausea, houseHold.getIndividual()[i].getQ619_6());
            individualValues.put(Q619Weight, houseHold.getIndividual()[i].getQ619_7());
            individualValues.put(Q619Fever, houseHold.getIndividual()[i].getQ619_8());
            individualValues.put(Q619Fever7Days, houseHold.getIndividual()[i].getQ619_10());
            individualValues.put(Q619ChestPain, houseHold.getIndividual()[i].getQ619_11());
            individualValues.put(Q619Breath, houseHold.getIndividual()[i].getQ619_12());
            individualValues.put(Q619Fatigue, houseHold.getIndividual()[i].getQ619_13());
            individualValues.put(Q619Sweats, houseHold.getIndividual()[i].getQ619_14());
            individualValues.put(Q619DontKnow, houseHold.getIndividual()[i].getQ619_9());
            individualValues.put(Q619Other, houseHold.getIndividual()[i].getQ619_Other());


            individualValues.put(Q620, houseHold.getIndividual()[i].getQ620());
            individualValues.put(Q620Other, houseHold.getIndividual()[i].getQ620_Other());


            individualValues.put(Q621, houseHold.getIndividual()[i].getQ621());
            individualValues.put(Q621aSpouse, houseHold.getIndividual()[i].getQ621a_1());
            individualValues.put(Q621aPartner, houseHold.getIndividual()[i].getQ621a_2());
            individualValues.put(Q621aFriend, houseHold.getIndividual()[i].getQ621a_3());
            individualValues.put(Q621aFamily, houseHold.getIndividual()[i].getQ621a_4());
            individualValues.put(Q621aRelative, houseHold.getIndividual()[i].getQ621a_5());
            individualValues.put(Q621aHCWorker, houseHold.getIndividual()[i].getQ621a_6());
            individualValues.put(Q621aCoWorker, houseHold.getIndividual()[i].getQ621a_7());
            individualValues.put(Q621aOther, houseHold.getIndividual()[i].getQ621a_Other());



            individualValues.put(Q621b, houseHold.getIndividual()[i].getQ621b());
            individualValues.put(Q621bOther, houseHold.getIndividual()[i].getQ621bOther());

            individualValues.put(Q622, houseHold.getIndividual()[i].getQ622());
            individualValues.put(Q622a, houseHold.getIndividual()[i].getQ622a());
            individualValues.put(Q622aOther, houseHold.getIndividual()[i].getQ622aOther());
            individualValues.put(Q622b, houseHold.getIndividual()[i].getQ622b());
            individualValues.put(Q622bOther, houseHold.getIndividual()[i].getQ622bOther());
            individualValues.put(Q623, houseHold.getIndividual()[i].getQ623());
            individualValues.put(Q624, houseHold.getIndividual()[i].getQ624());
            individualValues.put(Q625, houseHold.getIndividual()[i].getQ625());


            individualValues.put(Q701, houseHold.getIndividual()[i].getQ701());
            individualValues.put(Q702, houseHold.getIndividual()[i].getQ702());
            individualValues.put(Q703, houseHold.getIndividual()[i].getQ703());
            individualValues.put(Q704, houseHold.getIndividual()[i].getQ704());
            individualValues.put(Q705, houseHold.getIndividual()[i].getQ705());


            individualValues.put(Q801, houseHold.getIndividual()[i].getQ801());
            individualValues.put(Q801a, houseHold.getIndividual()[i].getQ801a());
            individualValues.put(Q801b, houseHold.getIndividual()[i].getQ801b());
            individualValues.put(Q801c, houseHold.getIndividual()[i].getQ801cMonth());
            individualValues.put(Q801c, houseHold.getIndividual()[i].getQ801cYear());
            individualValues.put(Q801d, houseHold.getIndividual()[i].getQ801d());
            individualValues.put(Q801dOther, houseHold.getIndividual()[i].getQ801dOther());
            individualValues.put(Q801e, houseHold.getIndividual()[i].getQ801e());
            individualValues.put(Q801eOther, houseHold.getIndividual()[i].getQ801eOther());
            individualValues.put(Q801f, houseHold.getIndividual()[i].getQ801f());


            individualValues.put(Q802, houseHold.getIndividual()[i].getQ802());
            individualValues.put(Q802a, houseHold.getIndividual()[i].getQ802a());
            individualValues.put(Q802aOther, houseHold.getIndividual()[i].getQ802aOther());
            individualValues.put(Q803, houseHold.getIndividual()[i].getQ803());
            individualValues.put(Q803Other, houseHold.getIndividual()[i].getQ803Other());
            individualValues.put(Q804, houseHold.getIndividual()[i].getQ804());
            individualValues.put(Q804Other, houseHold.getIndividual()[i].getQ804Other());


            individualValues.put(Q901, houseHold.getIndividual()[i].getQ901());
            individualValues.put(Q901a, houseHold.getIndividual()[i].getQ901a());
            individualValues.put(Q901aOther, houseHold.getIndividual()[i].getQ901aOther());
            individualValues.put(Q902, houseHold.getIndividual()[i].getQ902Month());
            individualValues.put(Q902, houseHold.getIndividual()[i].getQ902Year());

            individualValues.put(Q903DenyCare, houseHold.getIndividual()[i].getQ903a());
            individualValues.put(Q903Gossip, houseHold.getIndividual()[i].getQ903b());
            individualValues.put(Q903NoSex, houseHold.getIndividual()[i].getQ903c());
            individualValues.put(Q903VerbalAbuse, houseHold.getIndividual()[i].getQ903d());
            individualValues.put(Q903PhysicalAbuse, houseHold.getIndividual()[i].getQ903e());
            individualValues.put(Q903NoContact, houseHold.getIndividual()[i].getQ903f());
            individualValues.put(Q903SharingStatus, houseHold.getIndividual()[i].getQ903g());


            individualValues.put(Q904, houseHold.getIndividual()[i].getQ904());
            individualValues.put(Q904a, houseHold.getIndividual()[i].getQ904a());
            individualValues.put(Q904aOther, houseHold.getIndividual()[i].getQ904aOther());
            individualValues.put(Q904b, houseHold.getIndividual()[i].getQ904bMM());
            individualValues.put(Q904b, houseHold.getIndividual()[i].getQ904bYYYY());
            individualValues.put(Q904c, houseHold.getIndividual()[i].getQ904c());
            individualValues.put(Q904cOther, houseHold.getIndividual()[i].getQ904cOther());
            individualValues.put(Q905, houseHold.getIndividual()[i].getQ905());
            individualValues.put(Q905a, houseHold.getIndividual()[i].getQ905a());
            individualValues.put(Q905aOther, houseHold.getIndividual()[i].getQ905aOther());

            individualValues.put(Q1001, houseHold.getIndividual()[i].getQ1001());
            individualValues.put(Q1002, houseHold.getIndividual()[i].getQ1002());
            individualValues.put(Q1002aMCondom, houseHold.getIndividual()[i].getQ1002a_1());
            individualValues.put(Q1002aFCondom, houseHold.getIndividual()[i].getQ1002a_2());
            individualValues.put(Q1002aInjectContra, houseHold.getIndividual()[i].getQ1002a_3());
            individualValues.put(Q1002aOralContra, houseHold.getIndividual()[i].getQ1002a_4());
            individualValues.put(Q1002aUID, houseHold.getIndividual()[i].getQ1002a_5());
            individualValues.put(Q1002aBTL, houseHold.getIndividual()[i].getQ1002a_6());
            individualValues.put(Q1002aFSterilization, houseHold.getIndividual()[i].getQ1002a_7());
            individualValues.put(Q1002aMSterilization, houseHold.getIndividual()[i].getQ1002a_8());
            individualValues.put(Q1002aImplants, houseHold.getIndividual()[i].getQ1002a_10());
            individualValues.put(Q1002aEContra, houseHold.getIndividual()[i].getQ1002a_11());
            individualValues.put(Q1002aSafePeriod, houseHold.getIndividual()[i].getQ1002a_12());
            individualValues.put(Q1002aLAM, houseHold.getIndividual()[i].getQ1002a_13());
            individualValues.put(Q1002aDiagraphm, houseHold.getIndividual()[i].getQ1002a_14());
            individualValues.put(Q1002aSpermicides, houseHold.getIndividual()[i].getQ1002a_15());
            individualValues.put(Q1002aNatural, houseHold.getIndividual()[i].getQ1002a_16());
            individualValues.put(Q1002aTraditional, houseHold.getIndividual()[i].getQ1002a_17());
            individualValues.put(Q1002aSpiritual, houseHold.getIndividual()[i].getQ1002a_Other());
            individualValues.put(Q1002aOther, houseHold.getIndividual()[i].getQ1005());


            individualValues.put(Q1002, houseHold.getIndividual()[i].getQ1002());
            individualValues.put(Q1002bOther, houseHold.getIndividual()[i].getQ1002bOther());
            individualValues.put(Q1003, houseHold.getIndividual()[i].getQ1003());
           // individualValues.put(Q1004, houseHold.getIndividual()[i].getQ1004_Day());
           // individualValues.put(Q1004, houseHold.getIndividual()[i].getQ1004_Month());

            String tempMonth = "00";
            String tempYear = "0000";
            String tempDay = "00";


            if (houseHold.getIndividual()[i].getQ1004_Day() != null) {
                tempDay = houseHold.getIndividual()[i].getQ1004_Day();
            }
            if (houseHold.getIndividual()[i].getQ1004_Month() != null) {
                tempMonth = houseHold.getIndividual()[i].getQ1004_Month();
            }
            if (houseHold.getIndividual()[i].getQ1004_Year() != null) {
                tempYear = houseHold.getIndividual()[i].getQ1004_Year();
            }
            individualValues.put("Q1004", tempDay + tempMonth + tempYear);

           // individualValues.put(Q1004, houseHold.getIndividual()[i].getQ1004_Year());
            individualValues.put(Q1004a, houseHold.getIndividual()[i].getQ1004a());
            individualValues.put(Q1004b, houseHold.getIndividual()[i].getQ1004b());
            individualValues.put(Q1004bOther, houseHold.getIndividual()[i].getQ1004bOther());
            individualValues.put(Q1005, houseHold.getIndividual()[i].getQ1005());
            individualValues.put(Q1005a, houseHold.getIndividual()[i].getQ1005a());
            individualValues.put(Q1006, houseHold.getIndividual()[i].getQ1006());
            individualValues.put(Q1007, houseHold.getIndividual()[i].getQ1007());
            individualValues.put(Q1007a, houseHold.getIndividual()[i].getQ1007a());
            individualValues.put(Q1008, houseHold.getIndividual()[i].getQ1008());
            individualValues.put(Q1008a, houseHold.getIndividual()[i].getQ1008a());
            individualValues.put(Q1008aOther, houseHold.getIndividual()[i].getQ1008aOther());
            individualValues.put(Q1009, houseHold.getIndividual()[i].getQ1009());
            individualValues.put(Q1009a, houseHold.getIndividual()[i].getQ1009a());
            individualValues.put(Q1010, houseHold.getIndividual()[i].getQ1010());
            individualValues.put(Q1010Other, houseHold.getIndividual()[i].getQ1010Other());
            individualValues.put(Q1011, houseHold.getIndividual()[i].getQ1011());
            individualValues.put(Q1011Other, houseHold.getIndividual()[i].getQ1011_Other());

//            individualValues.put(Q1012, houseHold.getIndividual()[i].getQ1012_Week());
//            individualValues.put(Q1012, houseHold.getIndividual()[i].getQ1012_Month());
//            individualValues.put(Q1012, houseHold.getIndividual()[i].getQ1012_Year());

            tempMonth = "00";
            tempYear = "00";
            tempDay = "00";

            if (houseHold.getIndividual()[i].getQ1012_Week() != null) {
                tempDay = houseHold.getIndividual()[i].getQ1012_Week();
            }
            if (houseHold.getIndividual()[i].getQ1012_Month() != null) {
                tempMonth = houseHold.getIndividual()[i].getQ1012_Month();
            }
            if (houseHold.getIndividual()[i].getQ1012_Year() != null) {
                tempYear = houseHold.getIndividual()[i].getQ1012_Year();
            }
            individualValues.put("Q1012", tempDay + tempMonth + tempYear);

            individualValues.put(Q1013, houseHold.getIndividual()[i].getQ1013());
            individualValues.put(Q1014, houseHold.getIndividual()[i].getQ1014());
            individualValues.put(Q1014a, houseHold.getIndividual()[i].getQ1014a());
            individualValues.put(Q1014b, houseHold.getIndividual()[i].getQ1014b());
            individualValues.put(Q1015, houseHold.getIndividual()[i].getQ1015());
            individualValues.put(Q1015a, houseHold.getIndividual()[i].getQ1015a());
            individualValues.put(Q1015b, houseHold.getIndividual()[i].getQ1015b());
            individualValues.put(Q1016, houseHold.getIndividual()[i].getQ1016());
            individualValues.put(Q1017, houseHold.getIndividual()[i].getQ1017());
            individualValues.put(Q1101, houseHold.getIndividual()[i].getQ1101());
            individualValues.put(Q1101a, houseHold.getIndividual()[i].getQ1101a());
            individualValues.put(Q1101aOther, houseHold.getIndividual()[i].getQ1101aOther());
            individualValues.put(Q1102, houseHold.getIndividual()[i].getQ1102());
            individualValues.put(Q1102a, houseHold.getIndividual()[i].getQ1102a());
            individualValues.put(Q1103, houseHold.getIndividual()[i].getQ1103());
            individualValues.put(Q1103aDD, houseHold.getIndividual()[i].getQ1103aDD());
            individualValues.put(Q1103aWks, houseHold.getIndividual()[i].getQ1103aWks());
            individualValues.put(Q1103aDontKnow, houseHold.getIndividual()[i].getQ1103aDontKnow());
            individualValues.put(Q1104, houseHold.getIndividual()[i].getQ1104());
            individualValues.put(Q1105, houseHold.getIndividual()[i].getQ1105());
            individualValues.put(Q1106, houseHold.getIndividual()[i].getQ1106());
            individualValues.put(Q1106a, houseHold.getIndividual()[i].getQ1106a());
            individualValues.put(Q1106b, houseHold.getIndividual()[i].getQ1106b());
            individualValues.put(Q1106bOther, houseHold.getIndividual()[i].getQ1106bOther());
            individualValues.put(Q1107, houseHold.getIndividual()[i].getQ1107());
            individualValues.put(Q1107aDD, houseHold.getIndividual()[i].getQ1107aDD());
            individualValues.put(Q1107aWks, houseHold.getIndividual()[i].getQ1107aWks());
            individualValues.put(Q1107aDontKnow, houseHold.getIndividual()[i].getQ1107aDontKnow());
            individualValues.put(Q1108, houseHold.getIndividual()[i].getQ1108());
            individualValues.put(Q1108aDD, houseHold.getIndividual()[i].getQ1108aDD());
            individualValues.put(Q1108aWks, houseHold.getIndividual()[i].getQ1108aWks());
            individualValues.put(Q1108aDontKnow, houseHold.getIndividual()[i].getQ1108aDontKnow());
            individualValues.put(Q1109, houseHold.getIndividual()[i].getQ1109());
            individualValues.put(Q1110, houseHold.getIndividual()[i].getQ1110());
            individualValues.put(Q1111, houseHold.getIndividual()[i].getQ1111());
            individualValues.put(Q1111Other, houseHold.getIndividual()[i].getQ1111Other());
            individualValues.put(Q1112, houseHold.getIndividual()[i].getQ1112());
            individualValues.put(Q1112Other, houseHold.getIndividual()[i].getQ1112_Other());
            individualValues.put(Q1113, houseHold.getIndividual()[i].getQ1113());
            individualValues.put(Q1113Other, houseHold.getIndividual()[i].getQ1113Other());
            individualValues.put(Q1114, houseHold.getIndividual()[i].getQ1114());

            individualValues.put(B8_O15_Rapid, houseHold.getIndividual()[i].getB8_O15_Rapid());
            individualValues.put(Q801f, houseHold.getIndividual()[i].getQ801f());
            individualValues.put(IndvQuestionnairePConsent15_17, houseHold.getIndividual()[i].getIndvQuestionnairePConsent15_17());
            individualValues.put(IndvQuestionnaireConsent, houseHold.getIndividual()[i].getIndvQuestionnaireConsent());
            individualValues.put(IndvBloodDraw, houseHold.getIndividual()[i].getIndvBloodDraw());
            individualValues.put(BloodVol_1, houseHold.getIndividual()[i].getBloodVol_1());
            individualValues.put(BloodVol_4, houseHold.getIndividual()[i].getBloodVol_4());
            individualValues.put(BloodVol_6, houseHold.getIndividual()[i].getBloodVol_6());
            individualValues.put(BloodVol_10, houseHold.getIndividual()[i].getBloodVol_10());
            individualValues.put(BloodVolComment, houseHold.getIndividual()[i].getBloodVolComment());

            //individualValues.put(B8_O15_Rapid, houseHold.getIndividual()[i].getB8_O15_Rapid() );
            individualValues.put(IndRapidResults, houseHold.getIndividual()[i].getIndRapidResults());
            individualValues.put(IndBloodLabTest, houseHold.getIndividual()[i].getIndBloodLabTest());
            individualValues.put(IndBloodStore, houseHold.getIndividual()[i].getIndBloodStore());
            individualValues.put(IndRapidDate, houseHold.getIndividual()[i].getIndRapidDate());
            individualValues.put(VISIT1, houseHold.getIndividual()[i].getVISIT1());
            individualValues.put(VISIT2, houseHold.getIndividual()[i].getVISIT2());
            individualValues.put(VISIT3, houseHold.getIndividual()[i].getVISIT3());
            individualValues.put(INDDATE1, houseHold.getIndividual()[i].getDATE1());
            individualValues.put(INDDATE2, houseHold.getIndividual()[i].getDATE2());
            individualValues.put(INDDATE3, houseHold.getIndividual()[i].getDATE3());
            individualValues.put(Sync, houseHold.getIndividual()[i].getSync());


            individualValues.put(IndBloodSampleCollected, houseHold.getIndividual()[i].getIndBloodSampleCollected());
            individualValues.put(PrntlConsentBloodDraw, houseHold.getIndividual()[i].getPrntlConsentBloodDraw());
            individualValues.put(PrntlConsentRHT, houseHold.getIndividual()[i].getPrntlConsentRHT());
            individualValues.put(PrntlConsentLabTest, houseHold.getIndividual()[i].getPrntlConsentLabTest());
            individualValues.put(PrntlConsentBloodStore, houseHold.getIndividual()[i].getPrntlConsentBloodStore());

            individualValues.put(PrntlParentID, houseHold.getIndividual()[i].getPrntlParentID());
            individualValues.put(PrntlConsentDate, houseHold.getIndividual()[i].getPrntlConsentDate());


            db.insert("tblindividual", null, individualValues);

        }

        return true;
    }

    //FROM HOUSE HOLD
    public boolean insertIndividual(Individual houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues individualValues = new ContentValues();
        //contentValues.put("UserId", UserId);

        //for (int i = 0; i < houseHold.getPersons().length; i++)
        {
            individualValues.put(BatchNumberi, houseHold.getBatch());
            individualValues.put(Assignment_IDi, houseHold.getAssignmentID());
            individualValues.put(SRNOi, houseHold.getSRNO());
            individualValues.put(IndBarcode, houseHold.getIndBarcode());
            individualValues.put(Q101, houseHold.getQ101());
            individualValues.put(Q102, houseHold.getQ102());
            individualValues.put(Q103, houseHold.getQ103());
            individualValues.put(Q104, houseHold.getQ104());
            individualValues.put(Q104c, houseHold.getQ104c());
            individualValues.put(Q104cBISCED, houseHold.getQ104cBISCED());

            individualValues.put(Q105, houseHold.getQ105());
            individualValues.put(Q105Other, houseHold.getQ105Other());
            individualValues.put(Q105a, houseHold.getQ105a());
            //individualValues.put(Q105aBOSCO, houseHold.getQ105aBosco() );
            individualValues.put(Q105b, houseHold.getQ105b());
            // individualValues.put(Q105bBISIC, houseHold.getQ105bBISIC() );
            individualValues.put(Q106, houseHold.getQ106());
            individualValues.put(Q106a, houseHold.getQ106a());
            individualValues.put(Q106aOther, houseHold.getQ106aOther());
            individualValues.put(Q106b, houseHold.getQ106b());
            individualValues.put(Q106c, houseHold.getQ106c());
            //individualValues.put(Q106cBOSCO, houseHold.getQ106cBOSCO() );
            individualValues.put(Q106d, houseHold.getQ106d());
            //individualValues.put(Q106dBISIC, houseHold.getQ106dBISIC());
            individualValues.put(Q107, houseHold.getQ107());
            individualValues.put(Q107a, houseHold.getQ107aMnth());
            individualValues.put(Q107b, houseHold.getQ107b());
            individualValues.put(Q107bOther, houseHold.getQ107bOther());
            individualValues.put(Q107c, houseHold.getQ107c());
            individualValues.put(Q107cOther, houseHold.getQ107cOther());

            individualValues.put(Q201, houseHold.getQ201());
            individualValues.put(Q202, houseHold.getQ202());
            individualValues.put(Q203, houseHold.getQ203());
            individualValues.put(Q204, houseHold.getQ204());
            individualValues.put(Q205, houseHold.getQ205());
            individualValues.put(Q205a, houseHold.getQ205a());


            individualValues.put(Q301, houseHold.getQ301());
            individualValues.put(Q301a, houseHold.getQ301a());
            individualValues.put(Q302, houseHold.getQ302());
            individualValues.put(Q303, houseHold.getQ303());
            individualValues.put(Q303a, houseHold.getQ303a());
            individualValues.put(Q304, houseHold.getQ304());
            individualValues.put(Q304a, houseHold.getQ304a());

            individualValues.put(Q305Smoking, houseHold.getQ305_1());
            individualValues.put(Q305Sniffing, houseHold.getQ305_2());
            individualValues.put(Q305Chewing, houseHold.getQ305_3());
            individualValues.put(Q305None, houseHold.getQ305_4());

            individualValues.put(Q306, houseHold.getQ306());
            individualValues.put(Q307, houseHold.getQ307());

            individualValues.put(Q401, houseHold.getQ401());
            individualValues.put(Q402, houseHold.getQ402());
            individualValues.put(Q402a, houseHold.getQ402a());
            individualValues.put(Q402b, houseHold.getQ402b());
            individualValues.put(Q403, houseHold.getQ403());

            individualValues.put(Q404a,houseHold.getQ404a());
            individualValues.put(Q404Vaginal,houseHold.getQ404_1());
            individualValues.put(Q404Anal,houseHold.getQ404_2());
            individualValues.put(Q404Oral,houseHold.getQ404_3());

            individualValues.put(Q405,houseHold.getQ405());
            individualValues.put(Q406,houseHold.getQ406());
            individualValues.put(Q407,houseHold.getQ407());
            individualValues.put(Q408,houseHold.getQ408());
            individualValues.put(Q408a,houseHold.getQ408a());
            individualValues.put(Q410Chocked,houseHold.getQ410Choked());
            individualValues.put(Q410Pushed,houseHold.getQ410Pushed());
            individualValues.put(Q410Threatened,houseHold.getQ410Threatened());
            individualValues.put(Q410Physical,houseHold.getQ410Physical());
            individualValues.put(Q410Forced,houseHold.getQ410Forced());
            individualValues.put(Q410MadeAfraid,houseHold.getQ410MadeAfraid());
            individualValues.put(Q410Slapped,houseHold.getQ410Slapped());






            individualValues.put(Q501, houseHold.getQ501());
            individualValues.put(Q502, houseHold.getQ502());
            individualValues.put(Q503, houseHold.getQ503());
            individualValues.put(Q504_Pain, houseHold.getQ504_1());
            individualValues.put(Q504_Reduced, houseHold.getQ504_2());
            individualValues.put(Q504_Fear, houseHold.getQ504_3());
            individualValues.put(Q504_Culture, houseHold.getQ504_4());
            individualValues.put(Q504_Religion, houseHold.getQ504_5());
            individualValues.put(Q504_Spouse, houseHold.getQ504_6());
            individualValues.put(Q504_Parental, houseHold.getQ504_7());
            individualValues.put(Q504_Long, houseHold.getQ504_8());
            individualValues.put(Q504_FearHIV, houseHold.getQ504_10());
            individualValues.put(Q504_Other, houseHold.getQ504_Other());
            individualValues.put(Q504_OtherSpecify, houseHold.getQ504_OtherSpecify());


            individualValues.put(Q601, houseHold.getQ601());
            individualValues.put(Q601a, houseHold.getQ601a());
            individualValues.put(Q602Youth, houseHold.getQ602_1());
            individualValues.put(Q602TV, houseHold.getQ602_2());
            individualValues.put(Q602Radio, houseHold.getQ602_3());
            individualValues.put(Q602Newspaper, houseHold.getQ602_4());
            individualValues.put(Q602Hospital, houseHold.getQ602_5());
            individualValues.put(Q602Posters, houseHold.getQ602_6());
            individualValues.put(Q602Traditional, houseHold.getQ602_7());
            individualValues.put(Q602Workshop, houseHold.getQ602_8());
            individualValues.put(Q602Individual, houseHold.getQ602_10());
            individualValues.put(Q602Church, houseHold.getQ602_11());
            individualValues.put(Q602Kgotla, houseHold.getQ602_12());
            individualValues.put(Q602Workplace, houseHold.getQ602_13());
            individualValues.put(Q602Peer, houseHold.getQ602_14());
            individualValues.put(Q602School, houseHold.getQ602_15());
            individualValues.put(Q602_Other, houseHold.getQ602_Other());

            individualValues.put(Q603Condom, houseHold.getQ603_1());
            individualValues.put(Q603FewerP, houseHold.getQ603_2());
            individualValues.put(Q603Both, houseHold.getQ603_3());
            individualValues.put(Q603NoCasual, houseHold.getQ603_4());
            individualValues.put(Q603Abstain, houseHold.getQ603_5());
            individualValues.put(Q603NoCommercial, houseHold.getQ603_6());
            individualValues.put(Q603Injection, houseHold.getQ603_7());
            individualValues.put(Q603Blood, houseHold.getQ603_8());
            individualValues.put(Q603DontKnow, houseHold.getQ603_9());
            individualValues.put(Q603Other, houseHold.getQ603_Other());


            individualValues.put(Q604, houseHold.getQ604());
            individualValues.put(Q604a, houseHold.getQ604a());

            individualValues.put(Q604bYouth, houseHold.getQ604b_1());
            individualValues.put(Q604bTV, houseHold.getQ604b_2());
            individualValues.put(Q604bRadio, houseHold.getQ604b_3());
            individualValues.put(Q604bNewspaper, houseHold.getQ604b_4());
            individualValues.put(Q604bHospital, houseHold.getQ604b_5());
            individualValues.put(Q604bPoster, houseHold.getQ604b_6());
            individualValues.put(Q604bTraditional, houseHold.getQ604b_7());
            individualValues.put(Q604bWorkshop, houseHold.getQ604b_8());
            individualValues.put(Q604bIndividual, houseHold.getQ604b_10());
            individualValues.put(Q604bChurch, houseHold.getQ604b_11());
            individualValues.put(Q604bKgotla, houseHold.getQ604b_12());
            individualValues.put(Q604bWorkplace, houseHold.getQ604b_13());
            individualValues.put(Q604bPeer, houseHold.getQ604b_14());
            individualValues.put(Q604bSchool, houseHold.getQ604b_15());
            individualValues.put(Q604bOther, houseHold.getQ604b_Other());

            individualValues.put(Q605Windows, houseHold.getQ605_1());
            individualValues.put(Q605Mouth, houseHold.getQ605_2());
            individualValues.put(Q605Hands, houseHold.getQ605_3());
            individualValues.put(Q605Nutrition, houseHold.getQ605_4());
            individualValues.put(Q605Praying, houseHold.getQ605_5());
            individualValues.put(Q605DontKnow, houseHold.getQ605_9());
            individualValues.put(Q605Other, houseHold.getQ605_Other());


            individualValues.put(Q606, houseHold.getQ606());
            individualValues.put(Q607, houseHold.getQ607());
            individualValues.put(Q608, houseHold.getQ608());
            individualValues.put(Q609, houseHold.getQ609());
            individualValues.put(Q610, houseHold.getQ610());
            individualValues.put(Q611a, houseHold.getQ611a());
            individualValues.put(Q611b, houseHold.getQ611b());
            individualValues.put(Q611c, houseHold.getQ611c());




            individualValues.put(Q612, houseHold.getQ612());
            individualValues.put(Q612a, houseHold.getQ612a());
            individualValues.put(Q612Other, houseHold.getQ612aOther());

            individualValues.put(Q613, houseHold.getQ613());
            individualValues.put(Q613a, houseHold.getQ613a());
            individualValues.put(Q613aOther, houseHold.getQ613aOther());
            individualValues.put(Q614, houseHold.getQ614());
            individualValues.put(Q614Other, houseHold.getQ614Other());


            individualValues.put(Q615, houseHold.getQ615());
            individualValues.put(Q616Anybody, houseHold.getQ616_1());
            individualValues.put(Q616Poor, houseHold.getQ616_2());
            individualValues.put(Q616Homeless, houseHold.getQ616_3());
            individualValues.put(Q616Alcoholics, houseHold.getQ616_4());
            individualValues.put(Q616Drugs, houseHold.getQ616_5());
            individualValues.put(Q616PeopHIV, houseHold.getQ616_6());
            individualValues.put(Q616PeopPrison, houseHold.getQ616_7());
            individualValues.put(Q616Smokers, houseHold.getQ616_8());
            individualValues.put(Q616DntKnow, houseHold.getQ616_9());
            individualValues.put(Q616Other, houseHold.getQ616_10());


            individualValues.put(Q617Meal, houseHold.getQ617a());
            individualValues.put(Q617Clothes, houseHold.getQ617b());
            individualValues.put(Q617Miscarried, houseHold.getQ617c());
            individualValues.put(Q617Widow, houseHold.getQ617d());
            individualValues.put(Q617FamilyHIV, houseHold.getQ617e());
            individualValues.put(Q617Sejeso, houseHold.getQ617f());
            individualValues.put(Q617Touching, houseHold.getQ617g());
            individualValues.put(Q617Someone, houseHold.getQ617h());
            individualValues.put(Q617Other, houseHold.getQ617_0ther());

            individualValues.put(Q618, houseHold.getQ618());

            individualValues.put(Q619Rash, houseHold.getQ619_1());
            individualValues.put(Q619Cough, houseHold.getQ619_2());
            individualValues.put(Q619LongCough, houseHold.getQ619_3());
            individualValues.put(Q619Blood, houseHold.getQ619_4());
            individualValues.put(Q619Headache, houseHold.getQ619_5());
            individualValues.put(Q619Nausea, houseHold.getQ619_6());
            individualValues.put(Q619Weight, houseHold.getQ619_7());
            individualValues.put(Q619Fever, houseHold.getQ619_8());
            individualValues.put(Q619Fever7Days, houseHold.getQ619_10());
            individualValues.put(Q619ChestPain, houseHold.getQ619_11());
            individualValues.put(Q619Breath, houseHold.getQ619_12());
            individualValues.put(Q619Fatigue, houseHold.getQ619_13());
            individualValues.put(Q619Sweats, houseHold.getQ619_14());
            individualValues.put(Q619DontKnow, houseHold.getQ619_9());
            individualValues.put(Q619Other, houseHold.getQ619_Other());


            individualValues.put(Q620, houseHold.getQ620());
            individualValues.put(Q620Other, houseHold.getQ620_Other());


            individualValues.put(Q621, houseHold.getQ621());

            individualValues.put(Q621aSpouse, houseHold.getQ621a_1());
            individualValues.put(Q621aPartner, houseHold.getQ621a_2());
            individualValues.put(Q621aFriend, houseHold.getQ621a_3());
            individualValues.put(Q621aFamily, houseHold.getQ621a_4());
            individualValues.put(Q621aRelative, houseHold.getQ621a_5());
            individualValues.put(Q621aHCWorker, houseHold.getQ621a_6());
            individualValues.put(Q621aCoWorker, houseHold.getQ621a_7());
            individualValues.put(Q621aOther, houseHold.getQ621a_Other());


            individualValues.put(Q621b, houseHold.getQ621b());
            individualValues.put(Q621bOther, houseHold.getQ621bOther());

            individualValues.put(Q622, houseHold.getQ622());
            individualValues.put(Q622a, houseHold.getQ622a());
            individualValues.put(Q622aOther, houseHold.getQ622aOther());
            individualValues.put(Q622b, houseHold.getQ622b());
            individualValues.put(Q622bOther, houseHold.getQ622bOther());
            individualValues.put(Q623, houseHold.getQ623());
            individualValues.put(Q624, houseHold.getQ624());
            individualValues.put(Q625, houseHold.getQ625());


            individualValues.put(Q701, houseHold.getQ701());
            individualValues.put(Q702, houseHold.getQ702());
            individualValues.put(Q703, houseHold.getQ703());
            individualValues.put(Q704, houseHold.getQ704());
            individualValues.put(Q705, houseHold.getQ705());


            individualValues.put(Q801, houseHold.getQ801());
            individualValues.put(Q801a, houseHold.getQ801a());
            individualValues.put(Q801b, houseHold.getQ801b());
            individualValues.put(Q801c, houseHold.getQ801cMonth());
            individualValues.put(Q801c, houseHold.getQ801cYear());
            individualValues.put(Q801d, houseHold.getQ801d());
            individualValues.put(Q801dOther, houseHold.getQ801dOther());
            individualValues.put(Q801e, houseHold.getQ801e());
            individualValues.put(Q801eOther, houseHold.getQ801eOther());
            individualValues.put(Q801f, houseHold.getQ801f());


            individualValues.put(Q802, houseHold.getQ802());
            individualValues.put(Q802a, houseHold.getQ802a());
            individualValues.put(Q802aOther, houseHold.getQ802aOther());
            individualValues.put(Q803, houseHold.getQ803());
            individualValues.put(Q803Other, houseHold.getQ803Other());
            individualValues.put(Q804, houseHold.getQ804());
            individualValues.put(Q804Other, houseHold.getQ804Other());


            individualValues.put(Q901, houseHold.getQ901());
            individualValues.put(Q901a, houseHold.getQ901a());
            individualValues.put(Q901aOther, houseHold.getQ901aOther());
            individualValues.put(Q902, houseHold.getQ902Month()+houseHold.getQ902Year());

            individualValues.put(Q903DenyCare, houseHold.getQ903a());
            individualValues.put(Q903Gossip, houseHold.getQ903b());
            individualValues.put(Q903NoSex, houseHold.getQ903c());
            individualValues.put(Q903VerbalAbuse, houseHold.getQ903d());
            individualValues.put(Q903PhysicalAbuse, houseHold.getQ903e());
            individualValues.put(Q903NoContact, houseHold.getQ903f());
            individualValues.put(Q903SharingStatus, houseHold.getQ903g());
            individualValues.put(Q904, houseHold.getQ904());
            individualValues.put(Q904a, houseHold.getQ904a());
            individualValues.put(Q904aOther, houseHold.getQ904aOther());
            individualValues.put(Q904b, houseHold.getQ904bMM()+houseHold.getQ904bYYYY());
            individualValues.put(Q904c, houseHold.getQ904c());
            individualValues.put(Q904cOther, houseHold.getQ904cOther());
            individualValues.put(Q905, houseHold.getQ905());
            individualValues.put(Q905a, houseHold.getQ905a());
            individualValues.put(Q905aOther, houseHold.getQ905aOther());
            individualValues.put(Q1001, houseHold.getQ1001());
            individualValues.put(Q1002, houseHold.getQ1002());
            individualValues.put(Q1002aMCondom, houseHold.getQ1002a_1());
            individualValues.put(Q1002aFCondom, houseHold.getQ1002a_2());
            individualValues.put(Q1002aInjectContra, houseHold.getQ1002a_3());
            individualValues.put(Q1002aOralContra, houseHold.getQ1002a_4());
            individualValues.put(Q1002aUID, houseHold.getQ1002a_5());
            individualValues.put(Q1002aBTL, houseHold.getQ1002a_6());
            individualValues.put(Q1002aFSterilization, houseHold.getQ1002a_7());
            individualValues.put(Q1002aMSterilization, houseHold.getQ1002a_8());
            individualValues.put(Q1002aImplants, houseHold.getQ1002a_10());
            individualValues.put(Q1002aEContra, houseHold.getQ1002a_11());
            individualValues.put(Q1002aSafePeriod, houseHold.getQ1002a_12());
            individualValues.put(Q1002aLAM, houseHold.getQ1002a_13());
            individualValues.put(Q1002aDiagraphm, houseHold.getQ1002a_14());
            individualValues.put(Q1002aSpermicides, houseHold.getQ1002a_15());
            individualValues.put(Q1002aNatural, houseHold.getQ1002a_16());
            individualValues.put(Q1002aTraditional, houseHold.getQ1002a_17());
            individualValues.put(Q1002aSpiritual, houseHold.getQ1002a_Other());
            individualValues.put(Q1002aOther, houseHold.getQ1005());
            individualValues.put(Q1002, houseHold.getQ1002());
            individualValues.put(Q1002bOther, houseHold.getQ1002bOther());
            individualValues.put(Q1003, houseHold.getQ1003());


//            individualValues.put(Q1004, houseHold.getQ1004_Day());
//            individualValues.put(Q1004, houseHold.getQ1004_Month());
//            individualValues.put(Q1004, houseHold.getQ1004_Year());

            String tempMonth = "00";
            String tempYear = "0000";
            String tempDay = "00";

            if (houseHold.getQ1004_Day() != null) {
                tempDay = houseHold.getQ1004_Day();
            }
            if (houseHold.getQ1004_Month() != null) {
                tempMonth = houseHold.getQ1004_Month();
            }
            if (houseHold.getQ1004_Year() != null) {
                tempYear = houseHold.getQ1004_Year();
            }
            individualValues.put("Q1004", tempDay + tempMonth + tempYear);

            individualValues.put(Q1004a, houseHold.getQ1004a());
            individualValues.put(Q1004b, houseHold.getQ1004b());
            individualValues.put(Q1004bOther, houseHold.getQ1004bOther());
            individualValues.put(Q1005, houseHold.getQ1005());
            individualValues.put(Q1005a, houseHold.getQ1005a());
            individualValues.put(Q1006, houseHold.getQ1006());
            individualValues.put(Q1007, houseHold.getQ1007());
            individualValues.put(Q1007a, houseHold.getQ1007a());
            individualValues.put(Q1008, houseHold.getQ1008());
            individualValues.put(Q1008a, houseHold.getQ1008a());
            individualValues.put(Q1008aOther, houseHold.getQ1008aOther());
            individualValues.put(Q1009, houseHold.getQ1009());
            individualValues.put(Q1009a, houseHold.getQ1009a());
            individualValues.put(Q1010, houseHold.getQ1010());
            individualValues.put(Q1010Other, houseHold.getQ1010Other());
            individualValues.put(Q1011, houseHold.getQ1011());
            individualValues.put(Q1011Other, houseHold.getQ1011_Other());
//
//            individualValues.put(Q1012, houseHold.getQ1012_Week());
//            individualValues.put(Q1012, houseHold.getQ1012_Month());
//            individualValues.put(Q1012, houseHold.getQ1012_Year());

            tempMonth = "00";
            tempYear = "00";
            tempDay = "00";

            if (houseHold.getQ1012_Week() != null) {
                tempDay = houseHold.getQ1012_Week();
            }
            if (houseHold.getQ1012_Month() != null) {
                tempMonth = houseHold.getQ1012_Month();
            }
            if (houseHold.getQ1012_Year() != null) {
                tempYear = houseHold.getQ1012_Year();
            }
            individualValues.put("Q1012", tempDay + tempMonth + tempYear);


            individualValues.put(Q1012Still, houseHold.getQ1012Still());

            individualValues.put(Q1013, houseHold.getQ1013());
            individualValues.put(Q1014, houseHold.getQ1014());
            individualValues.put(Q1014a, houseHold.getQ1014a());
            individualValues.put(Q1014b, houseHold.getQ1014b());
            individualValues.put(Q1015, houseHold.getQ1015());
            individualValues.put(Q1015a, houseHold.getQ1015a());
            individualValues.put(Q1015b, houseHold.getQ1015b());
            individualValues.put(Q1016, houseHold.getQ1016());
            individualValues.put(Q1017, houseHold.getQ1017());

            individualValues.put(Q1101, houseHold.getQ1101());
            individualValues.put(Q1101a, houseHold.getQ1101a());
            individualValues.put(Q1101aOther, houseHold.getQ1101aOther());
            individualValues.put(Q1102, houseHold.getQ1102());
            individualValues.put(Q1102a, houseHold.getQ1102a());
            individualValues.put(Q1103, houseHold.getQ1103());
            individualValues.put(Q1103aDD, houseHold.getQ1103aDD());
            individualValues.put(Q1103aWks, houseHold.getQ1103aWks());
            individualValues.put(Q1103aDontKnow, houseHold.getQ1103aDontKnow());
            individualValues.put(Q1104, houseHold.getQ1104());
            individualValues.put(Q1105, houseHold.getQ1105());
            individualValues.put(Q1106, houseHold.getQ1106());
            individualValues.put(Q1106a, houseHold.getQ1106a());
            individualValues.put(Q1106b, houseHold.getQ1106b());
            individualValues.put(Q1106bOther, houseHold.getQ1106bOther());

            individualValues.put(Q1107, houseHold.getQ1107());
            individualValues.put(Q1107aDD, houseHold.getQ1107aDD());
            individualValues.put(Q1107aWks, houseHold.getQ1107aWks());
            individualValues.put(Q1107aDontKnow, houseHold.getQ1107aDontKnow());
            individualValues.put(Q1108, houseHold.getQ1108());
            individualValues.put(Q1108aDD, houseHold.getQ1108aDD());
            individualValues.put(Q1108aWks, houseHold.getQ1108aWks());
            individualValues.put(Q1108aDontKnow, houseHold.getQ1108aDontKnow());
            individualValues.put(Q1109, houseHold.getQ1109());
            individualValues.put(Q1110, houseHold.getQ1110());
            individualValues.put(Q1111, houseHold.getQ1111());
            individualValues.put(Q1111Other, houseHold.getQ1111Other());
            individualValues.put(Q1112, houseHold.getQ1112());
            individualValues.put(Q1112Other, houseHold.getQ1112_Other());
            individualValues.put(Q1113, houseHold.getQ1113());
            individualValues.put(Q1113Other, houseHold.getQ1113Other());
            individualValues.put(Q1114, houseHold.getQ1114());

            individualValues.put(VISIT1, houseHold.getVISIT1());
            individualValues.put(VISIT2, houseHold.getVISIT2());
            individualValues.put(VISIT3, houseHold.getVISIT3());
            individualValues.put(INDDATE1, houseHold.getDATE1());
            individualValues.put(INDDATE2, houseHold.getDATE2());
            individualValues.put(INDDATE3, houseHold.getDATE3());
            individualValues.put(Sync, houseHold.getSync());

            // individualValues.put(IndBloodSampleCollected, houseHold.getIndBloodSampleCollected() );
            //individualValues.put(B8_Yes_No, houseHold.getB8_Yes_No() );
            //individualValues.put(B8_Date, houseHold.getB8_Date() );

            //individualValues.put(Q801f, houseHold.getQ801f() );
            //individualValues.put(IndRapidResults, houseHold.getIndRapidResults() );
            // individualValues.put(IndRapidDate, houseHold.getIndRapidDate() );

            // individualValues.put(IndRapid_Comment, houseHold.getIndRapid_Comment() );

            individualValues.put(IndvQuestionnairePConsent15_17, houseHold.getIndvQuestionnairePConsent15_17());
            individualValues.put(IndvQuestionnaireConsent, houseHold.getIndvQuestionnaireConsent());
            individualValues.put(IndvBloodDraw, houseHold.getIndvBloodDraw());
            individualValues.put(BloodVol_1, houseHold.getBloodVol_1());
            individualValues.put(BloodVol_4, houseHold.getBloodVol_4());
            individualValues.put(BloodVol_6, houseHold.getBloodVol_6());
            individualValues.put(BloodVol_10, houseHold.getBloodVol_10());
            individualValues.put(BloodVolComment, houseHold.getBloodVolComment());

            individualValues.put(B8_O15_Rapid, houseHold.getB8_O15_Rapid());
            individualValues.put(IndRapidResults, houseHold.getIndRapidResults());
            individualValues.put(IndBloodLabTest, houseHold.getIndBloodLabTest());
            individualValues.put(IndBloodStore, houseHold.getIndBloodStore());
            individualValues.put(IndRapidDate, houseHold.getIndRapidDate());


            individualValues.put(IndBloodSampleCollected, houseHold.getIndBloodSampleCollected());
            individualValues.put(PrntlConsentBloodDraw, houseHold.getPrntlConsentBloodDraw());
            individualValues.put(PrntlConsentRHT, houseHold.getPrntlConsentRHT());
            individualValues.put(PrntlConsentLabTest, houseHold.getPrntlConsentLabTest());
            individualValues.put(PrntlConsentBloodStore, houseHold.getPrntlConsentBloodStore());

            individualValues.put(PrntlParentID, houseHold.getPrntlParentID());
            individualValues.put(PrntlConsentDate, houseHold.getPrntlConsentDate());

            db.insert("Individual", null, individualValues);

        }

        return true;
    }

    //INSERT INDIVIDUAL FROM SYNC
    public boolean insertSyncIndividual(Individual ind, String AsID, String Batch, int SRNO) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues individualValues = new ContentValues();
        //contentValues.put("UserId", UserId);
        individualValues.put(Assignment_IDi, AsID);
        individualValues.put(BatchNumberi, Batch);
        individualValues.put(SRNOi, SRNO);


        individualValues.put(IndBarcode, checkNull(ind.getIndBarcode()));

        individualValues.put(Q101, ind.getQ101());
        individualValues.put(Q102, ind.getQ102());
        individualValues.put(Q103, ind.getQ103());
        individualValues.put(Q104, checkNull(ind.getQ104()));
        individualValues.put(Q104a, checkNull(ind.getQ104a()));
        individualValues.put(Q104b, checkNull(ind.getQ104b()));
        individualValues.put(Q104c, checkNull(ind.getQ104c()));
        individualValues.put(Q104cBISCED, checkNull(ind.getQ104cBISCED()));
        individualValues.put(Q105, checkNull(ind.getQ105()));
        individualValues.put(Q105Other, checkNull(ind.getQ105Other()));
        individualValues.put(Q105a, checkNull(ind.getQ105a()));
        //individualValues.put(Q105aBOSCO, ind.getQ105aBosco() ));
        individualValues.put(Q105b, checkNull(ind.getQ105b()));
        // individualValues.put(Q105bBISIC, ind.getQ105bBISIC() ));
        individualValues.put(Q106, checkNull(ind.getQ106()));
        individualValues.put(Q106a, checkNull(ind.getQ106a()));
        individualValues.put(Q106aOther, checkNull(ind.getQ106aOther()));
        individualValues.put(Q106b, checkNull(ind.getQ106b()));
        individualValues.put(Q106c, checkNull(ind.getQ106c()));
        //individualValues.put(Q106cBOSCO, ind.getQ106cBOSCO() ));
        individualValues.put(Q106d, checkNull(ind.getQ106d()));
        //individualValues.put(Q106dBISIC, ind.getQ106dBISIC()));
        individualValues.put(Q107, checkNull(ind.getQ107()));
        individualValues.put(Q107a, checkNull(ind.getQ107aYY()+ind.getQ107aMnth()));
        individualValues.put(Q107b, checkNull(ind.getQ107b()));
        individualValues.put(Q107bOther, checkNull(ind.getQ107bOther()));
        individualValues.put(Q107c, checkNull(ind.getQ107c()));
        individualValues.put(Q107cOther, checkNull(ind.getQ107cOther()));
        individualValues.put(Q201, checkNull(ind.getQ201()));
        individualValues.put(Q202, checkNull(ind.getQ202()));
        individualValues.put(Q203, checkNull(ind.getQ203()));
        individualValues.put(Q204, checkNull(ind.getQ204()));
        individualValues.put(Q205, checkNull(ind.getQ205()));
        individualValues.put(Q205a, checkNull(ind.getQ205a()));
        individualValues.put(Q301, checkNull(ind.getQ301()));
        individualValues.put(Q301a, checkNull(ind.getQ301a()));
        individualValues.put(Q302, checkNull(ind.getQ302()));
        individualValues.put(Q303, checkNull(ind.getQ303()));
        individualValues.put(Q303a, checkNull(ind.getQ303a()));
        individualValues.put(Q304, checkNull(ind.getQ304()));
        individualValues.put(Q304a, checkNull(ind.getQ304a()));
        individualValues.put(Q305Smoking, checkNull(ind.getQ305_1()));
        individualValues.put(Q305Sniffing, checkNull(ind.getQ305_2()));
        individualValues.put(Q305Chewing, checkNull(ind.getQ305_3()));
        individualValues.put(Q305None, checkNull(ind.getQ305_4()));
        individualValues.put(Q306, checkNull(ind.getQ306()));
        individualValues.put(Q307, checkNull(ind.getQ307()));
        individualValues.put(Q401, checkNull(ind.getQ401()));
        individualValues.put(Q402, checkNull(ind.getQ402()));
        individualValues.put(Q402a, checkNull(ind.getQ402a()));
        individualValues.put(Q402b, checkNull(ind.getQ402b()));
        individualValues.put(Q403, checkNull(ind.getQ403()));

        individualValues.put(Q404a,checkNull(ind.getQ404a()));
        individualValues.put(Q404Vaginal,checkNull(ind.getQ404_1()));
        individualValues.put(Q404Anal,checkNull(ind.getQ404_2()));
        individualValues.put(Q404Oral,checkNull(ind.getQ404_3()));

        individualValues.put(Q405,checkNull(ind.getQ405()));
        individualValues.put(Q406,checkNull(ind.getQ406()));
        individualValues.put(Q407,checkNull(ind.getQ407()));
        individualValues.put(Q408,checkNull(ind.getQ408()));
        individualValues.put(Q408a,checkNull(ind.getQ408a()));
        individualValues.put(Q410Chocked,checkNull(ind.getQ410Choked()));
        individualValues.put(Q410Pushed,checkNull(ind.getQ410Pushed()));
        individualValues.put(Q410Threatened,checkNull(ind.getQ410Threatened()));
        individualValues.put(Q410Physical,checkNull(ind.getQ410Physical()));
        individualValues.put(Q410Forced,checkNull(ind.getQ410Forced()));
        individualValues.put(Q410MadeAfraid,checkNull(ind.getQ410MadeAfraid()));
        individualValues.put(Q410Slapped,checkNull(ind.getQ410Slapped()));





        individualValues.put(Q501, checkNull(ind.getQ501()));
        individualValues.put(Q502, checkNull(ind.getQ502()));
        individualValues.put(Q503, checkNull(ind.getQ503()));
        individualValues.put(Q504_Pain, checkNull(ind.getQ504_1()));
        individualValues.put(Q504_Reduced, checkNull(ind.getQ504_2()));
        individualValues.put(Q504_Fear, checkNull(ind.getQ504_3()));
        individualValues.put(Q504_Culture, checkNull(ind.getQ504_4()));
        individualValues.put(Q504_Religion, checkNull(ind.getQ504_5()));
        individualValues.put(Q504_Spouse, checkNull(ind.getQ504_6()));
        individualValues.put(Q504_Parental, checkNull(ind.getQ504_7()));
        individualValues.put(Q504_Long, checkNull(ind.getQ504_8()));
        individualValues.put(Q504_FearHIV, checkNull(ind.getQ504_10()));
        individualValues.put(Q504_Other, checkNull(ind.getQ504_Other()));
        individualValues.put(Q504_OtherSpecify, checkNull(ind.getQ504_OtherSpecify()));
        individualValues.put(Q601, checkNull(ind.getQ601()));
        individualValues.put(Q601a, checkNull(ind.getQ601a()));
        individualValues.put(Q602Youth, checkNull(ind.getQ602_1()));
        individualValues.put(Q602TV, checkNull(ind.getQ602_2()));
        individualValues.put(Q602Radio, checkNull(ind.getQ602_3()));
        individualValues.put(Q602Newspaper, checkNull(ind.getQ602_4()));
        individualValues.put(Q602Hospital, checkNull(ind.getQ602_5()));
        individualValues.put(Q602Posters, checkNull(ind.getQ602_6()));
        individualValues.put(Q602Traditional, checkNull(ind.getQ602_7()));
        individualValues.put(Q602Workshop, checkNull(ind.getQ602_8()));
        individualValues.put(Q602Individual, checkNull(ind.getQ602_10()));
        individualValues.put(Q602Church, checkNull(ind.getQ602_11()));
        individualValues.put(Q602Kgotla, checkNull(ind.getQ602_12()));
        individualValues.put(Q602Workplace, checkNull(ind.getQ602_13()));
        individualValues.put(Q602Peer, checkNull(ind.getQ602_14()));
        individualValues.put(Q602School, checkNull(ind.getQ602_15()));
        individualValues.put(Q602_Other, checkNull(ind.getQ602_Other()));
        individualValues.put(Q603Condom, checkNull(ind.getQ603_1()));
        individualValues.put(Q603FewerP, checkNull(ind.getQ603_2()));
        individualValues.put(Q603Both, checkNull(ind.getQ603_3()));
        individualValues.put(Q603NoCasual, checkNull(ind.getQ603_4()));
        individualValues.put(Q603Abstain, checkNull(ind.getQ603_5()));
        individualValues.put(Q603NoCommercial, checkNull(ind.getQ603_6()));
        individualValues.put(Q603Injection, checkNull(ind.getQ603_7()));
        individualValues.put(Q603Blood, checkNull(ind.getQ603_8()));
        individualValues.put(Q603DontKnow, checkNull(ind.getQ603_9()));
        individualValues.put(Q603Other, checkNull(ind.getQ603_Other()));
        individualValues.put(Q604, checkNull(ind.getQ604()));
        individualValues.put(Q604a, checkNull(ind.getQ604a()));
        individualValues.put(Q604bYouth, checkNull(ind.getQ604b_1()));
        individualValues.put(Q604bTV, checkNull(ind.getQ604b_2()));
        individualValues.put(Q604bRadio, checkNull(ind.getQ604b_3()));
        individualValues.put(Q604bNewspaper, checkNull(ind.getQ604b_4()));
        individualValues.put(Q604bHospital, checkNull(ind.getQ604b_5()));
        individualValues.put(Q604bPoster, checkNull(ind.getQ604b_6()));
        individualValues.put(Q604bTraditional, checkNull(ind.getQ604b_7()));
        individualValues.put(Q604bWorkshop, checkNull(ind.getQ604b_8()));
        individualValues.put(Q604bIndividual, checkNull(ind.getQ604b_10()));
        individualValues.put(Q604bChurch, checkNull(ind.getQ604b_11()));
        individualValues.put(Q604bKgotla, checkNull(ind.getQ604b_12()));
        individualValues.put(Q604bWorkplace, checkNull(ind.getQ604b_13()));
        individualValues.put(Q604bPeer, checkNull(ind.getQ604b_14()));
        individualValues.put(Q604bSchool, checkNull(ind.getQ604b_15()));
        individualValues.put(Q604bOther, checkNull(ind.getQ604b_Other()));
        individualValues.put(Q605Windows, checkNull(ind.getQ605_1()));
        individualValues.put(Q605Mouth, checkNull(ind.getQ605_2()));
        individualValues.put(Q605Hands, checkNull(ind.getQ605_3()));
        individualValues.put(Q605Nutrition, checkNull(ind.getQ605_4()));
        individualValues.put(Q605Praying, checkNull(ind.getQ605_5()));
        individualValues.put(Q605DontKnow, checkNull(ind.getQ605_9()));
        individualValues.put(Q605Other, checkNull(ind.getQ605_Other()));
        individualValues.put(Q606, checkNull(ind.getQ606()));
        individualValues.put(Q607, checkNull(ind.getQ607()));
        individualValues.put(Q608, checkNull(ind.getQ608()));
        individualValues.put(Q609, checkNull(ind.getQ609()));
        individualValues.put(Q610, checkNull(ind.getQ610()));
        individualValues.put(Q611a, checkNull(ind.getQ611a()));
        individualValues.put(Q611b, checkNull(ind.getQ611b()));
        individualValues.put(Q612, checkNull(ind.getQ612()));
        individualValues.put(Q612a, checkNull(ind.getQ612a()));
        individualValues.put(Q612Other, checkNull(ind.getQ612aOther()));
        individualValues.put(Q613, checkNull(ind.getQ613()));
        individualValues.put(Q613a, checkNull(ind.getQ613a()));
        individualValues.put(Q613aOther, checkNull(ind.getQ613aOther()));
        individualValues.put(Q614, checkNull(ind.getQ614()));
        individualValues.put(Q614Other, checkNull(ind.getQ614Other()));
        individualValues.put(Q615, checkNull(ind.getQ615()));
        individualValues.put(Q616Anybody, checkNull(ind.getQ616_1()));
        individualValues.put(Q616Poor, checkNull(ind.getQ616_2()));
        individualValues.put(Q616Homeless, checkNull(ind.getQ616_3()));
        individualValues.put(Q616Alcoholics, checkNull(ind.getQ616_4()));
        individualValues.put(Q616Drugs, checkNull(ind.getQ616_5()));
        individualValues.put(Q616PeopHIV, checkNull(ind.getQ616_6()));
        individualValues.put(Q616PeopPrison, checkNull(ind.getQ616_7()));
        individualValues.put(Q616Smokers, checkNull(ind.getQ616_8()));
        individualValues.put(Q616DntKnow, checkNull(ind.getQ616_9()));
        individualValues.put(Q616Other, checkNull(ind.getQ616_10()));
        individualValues.put(Q617Meal, checkNull(ind.getQ617a()));
        individualValues.put(Q617Clothes, checkNull(ind.getQ617b()));
        individualValues.put(Q617Miscarried, checkNull(ind.getQ617c()));
        individualValues.put(Q617Widow, checkNull(ind.getQ617d()));
        individualValues.put(Q617FamilyHIV, checkNull(ind.getQ617e()));
        individualValues.put(Q617Sejeso, checkNull(ind.getQ617f()));
        individualValues.put(Q617Touching, checkNull(ind.getQ617g()));
        individualValues.put(Q617Someone, checkNull(ind.getQ617h()));
        individualValues.put(Q617Other, checkNull(ind.getQ617_0ther()));
        individualValues.put(Q618, checkNull(ind.getQ618()));
        individualValues.put(Q619Rash, checkNull(ind.getQ619_1()));
        individualValues.put(Q619Cough, checkNull(ind.getQ619_2()));
        individualValues.put(Q619LongCough, checkNull(ind.getQ619_3()));
        individualValues.put(Q619Blood, checkNull(ind.getQ619_4()));
        individualValues.put(Q619Headache, checkNull(ind.getQ619_5()));
        individualValues.put(Q619Nausea, checkNull(ind.getQ619_6()));
        individualValues.put(Q619Weight, checkNull(ind.getQ619_7()));
        individualValues.put(Q619Fever, checkNull(ind.getQ619_8()));
        individualValues.put(Q619Fever7Days, checkNull(ind.getQ619_10()));
        individualValues.put(Q619ChestPain, checkNull(ind.getQ619_11()));
        individualValues.put(Q619Breath, checkNull(ind.getQ619_12()));
        individualValues.put(Q619Fatigue, checkNull(ind.getQ619_13()));
        individualValues.put(Q619Sweats, checkNull(ind.getQ619_14()));
        individualValues.put(Q619DontKnow, checkNull(ind.getQ619_9()));
        individualValues.put(Q619Other, checkNull(ind.getQ619_Other()));
        individualValues.put(Q620, checkNull(ind.getQ620()));
        individualValues.put(Q620Other, checkNull(ind.getQ620_Other()));
        individualValues.put(Q621, checkNull(ind.getQ621()));
        individualValues.put(Q621aSpouse, checkNull(ind.getQ621a_1()));
        individualValues.put(Q621aPartner, checkNull(ind.getQ621a_2()));
        individualValues.put(Q621aFriend, checkNull(ind.getQ621a_3()));
        individualValues.put(Q621aFamily, checkNull(ind.getQ621a_4()));
        individualValues.put(Q621aRelative, checkNull(ind.getQ621a_5()));
        individualValues.put(Q621aHCWorker, checkNull(ind.getQ621a_6()));
        individualValues.put(Q621aCoWorker, checkNull(ind.getQ621a_7()));
        individualValues.put(Q621aOther, checkNull(ind.getQ621a_Other()));


        individualValues.put(Q621b, checkNull(ind.getQ621b()));
        individualValues.put(Q621bOther, checkNull(ind.getQ621bOther()));
        individualValues.put(Q622, checkNull(ind.getQ622()));
        individualValues.put(Q622a, checkNull(ind.getQ622a()));
        individualValues.put(Q622aOther, checkNull(ind.getQ622aOther()));
        individualValues.put(Q622b, checkNull(ind.getQ622b()));
        individualValues.put(Q622bOther, checkNull(ind.getQ622bOther()));
        individualValues.put(Q623, checkNull(ind.getQ623()));
        individualValues.put(Q624, checkNull(ind.getQ624()));
        individualValues.put(Q625, checkNull(ind.getQ625()));
        individualValues.put(Q701, checkNull(ind.getQ701()));
        individualValues.put(Q702, checkNull(ind.getQ702()));
        individualValues.put(Q703, checkNull(ind.getQ703()));
        individualValues.put(Q704, checkNull(ind.getQ704()));
        individualValues.put(Q705, checkNull(ind.getQ705()));
        individualValues.put(Q801, checkNull(ind.getQ801()));
        individualValues.put(Q801a, checkNull(ind.getQ801a()));
        individualValues.put(Q801b, checkNull(ind.getQ801b()));

        individualValues.put(Q801c, checkNull(ind.getQ801cMonth() + ind.getQ801cYear()));

        individualValues.put(Q801d, checkNull(ind.getQ801d()));
        individualValues.put(Q801dOther, checkNull(ind.getQ801dOther()));
        individualValues.put(Q801e, checkNull(ind.getQ801e()));
        individualValues.put(Q801eOther, checkNull(ind.getQ801eOther()));
        individualValues.put(Q801f, checkNull(ind.getQ801f()));
        individualValues.put(Q802, checkNull(ind.getQ802()));
        individualValues.put(Q802a, checkNull(ind.getQ802a()));
        individualValues.put(Q802aOther, checkNull(ind.getQ802aOther()));
        individualValues.put(Q803, checkNull(ind.getQ803()));
        individualValues.put(Q803Other, checkNull(ind.getQ803Other()));
        individualValues.put(Q804, checkNull(ind.getQ804()));
        individualValues.put(Q804Other, checkNull(ind.getQ804Other()));

        individualValues.put(Q901, checkNull(ind.getQ901()));
        individualValues.put(Q901a, checkNull(ind.getQ901a()));
        individualValues.put(Q901aOther, checkNull(ind.getQ901aOther()));

        individualValues.put(Q902, checkNull(ind.getQ902Month() + ind.getQ902Year()));

        individualValues.put(Q903DenyCare, checkNull(ind.getQ903a()));
        individualValues.put(Q903Gossip, checkNull(ind.getQ903b()));
        individualValues.put(Q903NoSex, checkNull(ind.getQ903c()));
        individualValues.put(Q903VerbalAbuse, checkNull(ind.getQ903d()));
        individualValues.put(Q903PhysicalAbuse, checkNull(ind.getQ903e()));
        individualValues.put(Q903NoContact, checkNull(ind.getQ903f()));
        individualValues.put(Q903SharingStatus, checkNull(ind.getQ903g()));

        individualValues.put(Q904, checkNull(ind.getQ904()));
        individualValues.put(Q904a, checkNull(ind.getQ904a()));
        individualValues.put(Q904aOther, checkNull(ind.getQ904aOther()));

        individualValues.put(Q904b, checkNull(ind.getQ904bMM() + ind.getQ904bYYYY()));

        individualValues.put(Q904c, checkNull(ind.getQ904c()));
        individualValues.put(Q904cOther, checkNull(ind.getQ904cOther()));

        individualValues.put(Q905, checkNull(ind.getQ905()));
        individualValues.put(Q905a, checkNull(ind.getQ905a()));
        individualValues.put(Q905aOther, checkNull(ind.getQ905aOther()));

        individualValues.put(Q1001, checkNull(ind.getQ1001()));
        individualValues.put(Q1002, checkNull(ind.getQ1002()));
        individualValues.put(Q1002aMCondom, checkNull(ind.getQ1002a_1()));
        individualValues.put(Q1002aFCondom, checkNull(ind.getQ1002a_2()));
        individualValues.put(Q1002aInjectContra, checkNull(ind.getQ1002a_3()));
        individualValues.put(Q1002aOralContra, checkNull(ind.getQ1002a_4()));
        individualValues.put(Q1002aUID, checkNull(ind.getQ1002a_5()));
        individualValues.put(Q1002aBTL, checkNull(ind.getQ1002a_6()));
        individualValues.put(Q1002aFSterilization, checkNull(ind.getQ1002a_7()));
        individualValues.put(Q1002aMSterilization, checkNull(ind.getQ1002a_8()));
        individualValues.put(Q1002aImplants, checkNull(ind.getQ1002a_10()));
        individualValues.put(Q1002aEContra, checkNull(ind.getQ1002a_11()));
        individualValues.put(Q1002aSafePeriod, checkNull(ind.getQ1002a_12()));
        individualValues.put(Q1002aLAM, checkNull(ind.getQ1002a_13()));
        individualValues.put(Q1002aDiagraphm, checkNull(ind.getQ1002a_14()));
        individualValues.put(Q1002aSpermicides, checkNull(ind.getQ1002a_15()));
        individualValues.put(Q1002aNatural, checkNull(ind.getQ1002a_16()));
        individualValues.put(Q1002aTraditional, checkNull(ind.getQ1002a_17()));
        individualValues.put(Q1002aSpiritual, checkNull(ind.getQ1002a_18()));
        individualValues.put(Q1002aOther, checkNull(ind.getQ1002a_Other()));

        individualValues.put(Q1002b, checkNull(ind.getQ1002b()));
        individualValues.put(Q1002bOther, checkNull(ind.getQ1002bOther()));

        individualValues.put(Q1003, checkNull(ind.getQ1003()));
        individualValues.put(Q1004, checkNull(ind.getQ1004_Day()));
        individualValues.put(Q1004, checkNull(ind.getQ1004_Month()));
        individualValues.put(Q1004, checkNull(ind.getQ1004_Year()));
        individualValues.put(Q1004a, checkNull(ind.getQ1004a()));
        individualValues.put(Q1004b, checkNull(ind.getQ1004b()));
        individualValues.put(Q1004bOther, checkNull(ind.getQ1004bOther()));

        individualValues.put(Q1005, checkNull(ind.getQ1005()));
        individualValues.put(Q1005a, checkNull(ind.getQ1005a()));
        individualValues.put(Q1006, checkNull(ind.getQ1006()));
        individualValues.put(Q1007, checkNull(ind.getQ1007()));
        individualValues.put(Q1007a, checkNull(ind.getQ1007a()));
        individualValues.put(Q1008, checkNull(ind.getQ1008()));
        individualValues.put(Q1008a, checkNull(ind.getQ1008a()));
        individualValues.put(Q1008aOther, checkNull(ind.getQ1008aOther()));

        individualValues.put(Q1009, checkNull(ind.getQ1009()));
        individualValues.put(Q1009a, checkNull(ind.getQ1009a()));
        individualValues.put(Q1010, checkNull(ind.getQ1010()));
        individualValues.put(Q1010Other, checkNull(ind.getQ1010Other()));
        individualValues.put(Q1011, checkNull(ind.getQ1011()));
        individualValues.put(Q1011Other, checkNull(ind.getQ1011_Other()));

        individualValues.put(Q1012, checkNull(ind.getQ1012_Week() +ind.getQ1012_Month() + ind.getQ1012_Year()));

        individualValues.put(Q1013, checkNull(ind.getQ1013()));
        individualValues.put(Q1014, checkNull(ind.getQ1014()));
        individualValues.put(Q1014a, checkNull(ind.getQ1014a()));
        individualValues.put(Q1014b, checkNull(ind.getQ1014b()));
        individualValues.put(Q1015, checkNull(ind.getQ1015()));
        individualValues.put(Q1015a, checkNull(ind.getQ1015a()));
        individualValues.put(Q1015b, checkNull(ind.getQ1015b()));
        individualValues.put(Q1016, checkNull(ind.getQ1016()));
        individualValues.put(Q1017, checkNull(ind.getQ1017()));

        individualValues.put(Q1101, checkNull(ind.getQ1101()));
        individualValues.put(Q1101a, checkNull(ind.getQ1101a()));
        individualValues.put(Q1101aOther, checkNull(ind.getQ1101aOther()));
        individualValues.put(Q1102, checkNull(ind.getQ1102()));
        individualValues.put(Q1102a, checkNull(ind.getQ1102a()));

        individualValues.put(Q1103, checkNull(ind.getQ1103()));
        individualValues.put(Q1103aDD, checkNull(ind.getQ1103aDD()));
        individualValues.put(Q1103aWks, checkNull(ind.getQ1103aWks()));
        individualValues.put(Q1103aDontKnow, checkNull(ind.getQ1103aDontKnow()));
        individualValues.put(Q1104, checkNull(ind.getQ1104()));
        individualValues.put(Q1105, checkNull(ind.getQ1105()));
        individualValues.put(Q1106, checkNull(ind.getQ1106()));
        individualValues.put(Q1106a, checkNull(ind.getQ1106a()));
        individualValues.put(Q1106b, checkNull(ind.getQ1106b()));
        individualValues.put(Q1106bOther, checkNull(ind.getQ1106bOther()));

        individualValues.put(Q1107, checkNull(ind.getQ1107()));
        individualValues.put(Q1107aDD, checkNull(ind.getQ1107aDD()));
        individualValues.put(Q1107aWks, checkNull(ind.getQ1107aWks()));
        individualValues.put(Q1107aDontKnow, checkNull(ind.getQ1107aDontKnow()));
        individualValues.put(Q1108, checkNull(ind.getQ1108()));
        individualValues.put(Q1108aDD, checkNull(ind.getQ1108aDD()));
        individualValues.put(Q1108aWks, checkNull(ind.getQ1108aWks()));
        individualValues.put(Q1108aDontKnow, checkNull(ind.getQ1108aDontKnow()));
        individualValues.put(Q1109, checkNull(ind.getQ1109()));
        individualValues.put(Q1110, checkNull(ind.getQ1110()));
        individualValues.put(Q1111, checkNull(ind.getQ1111()));
        individualValues.put(Q1111Other, checkNull(ind.getQ1111Other()));
        individualValues.put(Q1112, checkNull(ind.getQ1112()));
        individualValues.put(Q1112Other, checkNull(ind.getQ1112_Other()));
        individualValues.put(Q1012Still,checkNull(ind.getQ1012Still()));


        individualValues.put(Q1113, checkNull(ind.getQ1113()));
        individualValues.put(Q1113Other, checkNull(ind.getQ1113Other()));
        individualValues.put(Q1114, checkNull(ind.getQ1114()));
        // individualValues.put(IndBloodSampleCollected, checkNull(ind.getIndBloodSampleCollected() ));
        // individualValues.put(B8_Yes_No, checkNull(ind.getB8_Yes_No() ));
        // individualValues.put(B8_Date, checkNull(ind.getB8_Date() ));

        individualValues.put(IndvQuestionnairePConsent15_17, checkNull(ind.getIndvQuestionnairePConsent15_17()));
        individualValues.put(IndvQuestionnaireConsent, checkNull(ind.getIndvQuestionnaireConsent()));
        individualValues.put(IndvBloodDraw, checkNull(ind.getIndvBloodDraw()));
        individualValues.put(BloodVol_1, checkNull(ind.getBloodVol_1()));
        individualValues.put(BloodVol_4, checkNull(ind.getBloodVol_4()));
        individualValues.put(BloodVol_6, checkNull(ind.getBloodVol_6()));
        individualValues.put(BloodVol_10, checkNull(ind.getBloodVol_10()));
        individualValues.put(BloodVolComment, checkNull(ind.getBloodVolComment()));
        individualValues.put(B8_O15_Rapid, checkNull(ind.getB8_O15_Rapid()));
        individualValues.put(IndRapidResults, checkNull(ind.getIndRapidResults()));
        individualValues.put(IndBloodLabTest, checkNull(ind.getIndBloodLabTest()));
        individualValues.put(IndBloodStore, checkNull(ind.getIndBloodStore()));
        individualValues.put(IndRapidDate, checkNull(ind.getIndRapidDate()));
        individualValues.put(IndBloodSampleCollected, checkNull(ind.getIndBloodSampleCollected()));
        individualValues.put(PrntlConsentBloodDraw, checkNull(ind.getPrntlConsentBloodDraw()));
        individualValues.put(PrntlConsentRHT, checkNull(ind.getPrntlConsentRHT()));
        individualValues.put(PrntlConsentLabTest, checkNull(ind.getPrntlConsentLabTest()));
        individualValues.put(PrntlConsentBloodStore, checkNull(ind.getPrntlConsentBloodStore()));
        individualValues.put(PrntlParentID, checkNull(ind.getPrntlParentID()));
        individualValues.put(PrntlConsentDate, checkNull(ind.getPrntlConsentDate()));
        individualValues.put(VISIT1, checkNull(ind.getVISIT1()));
        individualValues.put(VISIT2, checkNull(ind.getVISIT2()));
        individualValues.put(VISIT3, checkNull(ind.getVISIT3()));
        individualValues.put(INDDATE1, checkNull(ind.getDATE1()));
        individualValues.put(INDDATE2, checkNull(ind.getDATE2()));
        individualValues.put(INDDATE3, checkNull(ind.getDATE3()));
        individualValues.put(Sync, checkNull(ind.getSync()));
        // individualValues.put(Q801f, ind.getQ801f() );
        //individualValues.put(IndRapidResults, ind.getIndRapidResults() );
        // individualValues.put(IndRapidDate, ind.getIndRapidDate() );
        //individualValues.put(IndRapid_Comment, ind.getIndRapid_Comment() );
        db.insert("Individual", null, individualValues);

        return true;
    }

    //INSERT Sample
    public boolean insertSyncSample(Sample sample) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhrosterValues = new ContentValues();

        hhrosterValues.put(PK, sample.getPK());
        hhrosterValues.put(Region, sample.getRegion());
        hhrosterValues.put(StratumNo, sample.getStratumNo());
        hhrosterValues.put(DistrictCode, sample.getDistrictCode());
        hhrosterValues.put(VillageCode, sample.getVillageCode());
        hhrosterValues.put(LocalityCode, sample.getLocalityCode());
        hhrosterValues.put(EACode, sample.getEACode());
        hhrosterValues.put(BlockNo, sample.getBlockNo());
        hhrosterValues.put(StatusCode, sample.getStatusCode());
        hhrosterValues.put(DistrictName, sample.getDistrictName());
        hhrosterValues.put(DistrictEAVillageLocality, sample.getDistrictEAVillageLocality());
        hhrosterValues.put(LocalityType, sample.getLocalityType());
        hhrosterValues.put(NoofHholds, sample.getNoofHholds());
        hhrosterValues.put(STATUS, sample.getSTATUS());
        hhrosterValues.put(TBculture, sample.getTBculture());
        hhrosterValues.put(AssignmentGenerated, sample.getAssignmentGenerated());


        try {

            int nombr = 0;
            Cursor cursor = db.rawQuery("SELECT * FROM Sample WHERE PK = " + sample.getPK(), null);
            nombr = cursor.getCount();
            if (nombr == 1) {
                return true;
            } else {
                db.insert("Sample", null, hhrosterValues);
            }


        } catch (Exception e) {

        }


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

        if (res.getCount() == 0) {
            // show message
            //myDB.showMessage("Error","Nothing found");
            Log.d("DB Number of Rows: ", String.valueOf(res.getCount()));
            return;
        }


        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("AssgnID:" + res.getString(0) + "\n");
            buffer.append("BatchNO:" + res.getString(1) + "\n");
            buffer.append("District:" + res.getString(2) + "\n");
            buffer.append("village:" + res.getString(3) + "\n");
            buffer.append("locality:" + res.getString(4) + "\n");
            buffer.append("EA:" + res.getString(5) + "\n");
            buffer.append("Block:" + res.getString(6) + "\n");
            buffer.append("Ea_status:" + res.getString(7) + "\n");
        }
        //Log.d("DB EA_Assgnments: ", buffer.toString());
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

    //Pull from TBL Assignments
    public ArrayList<String> getEA(SQLiteDatabase db) {
        ArrayList<String> a = new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);


        Cursor res = db.rawQuery("select * from " + tblAssignments, null);
        //return res;


        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            //a.add("EA NO: "+res.getString(4));
            a.add("EA NO: " + res.getString(5) + "#" + res.getString(0) + "#" + res.getString(1) + "#" + res.getString(2) + "#" + res.getString(3) + "#" + res.getString(4) + "#" + res.getString(6) + "#" + res.getString(7));

            Log.d("Broken: ", "EA NO: " + res.getString(5) + "#" + res.getString(0) + "#" + res.getString(1) + "#" + res.getString(2) + "#" + res.getString(3) + "#" + res.getString(4) + "#" + res.getString(6) + "#" + res.getString(7));
        }
        return a;
    }

    /******get all ea assignments *****/
    public ArrayList<String> getAllEA_Ass(SQLiteDatabase db) {
        ArrayList<String> a = new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);


        Cursor res = db.rawQuery("select * from " + tblAssignments, null);
        //return res;


        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            //a.add("EA NO: "+res.getString(4));
            a.add(res.getString(0)+"#"+res.getString(8));

           }
        return a;
    }

/******get all samples *****/
    public ArrayList<String> getSampleAll(SQLiteDatabase db) {
        ArrayList<String> a = new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);


        Cursor res = db.rawQuery("select * from " + tblAssignments, null);
        //return res;


        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            //a.add("EA NO: "+res.getString(4));
            a.add(res.getString(0));

        }
        return a;
    }

    /******get all REMAINING HOUSEHOLDS *****/
    public ArrayList<String> getRemainingHH(SQLiteDatabase db) {
        ArrayList<String> a = new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from " + table, null);


        Cursor res = db.rawQuery("select * from " + tblhousehold, null);
        //return res;


        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            //a.add("EA NO: "+res.getString(4));
            a.add(res.getString(0));

        }
        return a;
    }
















    public ArrayList<HouseHold> getHouseHold(SQLiteDatabase db, String EA_No, String assgmnt) {
        ArrayList<HouseHold> a = new ArrayList<>();
        db = this.getWritableDatabase();
        db = this.getReadableDatabase();


        Cursor res = db.rawQuery("select * from " + tblhousehold + " where EA_Assignment_ID = " + assgmnt + " and Interview_Status=3;", null);


        while (res.moveToNext()) {
            HouseHold buffer = new HouseHold(null, 0, 0);
            buffer.setBatchNumber(res.getString(0));
            buffer.setAssignment_ID(res.getString(1));
            buffer.setDWELLING_NO(res.getString(2));
            buffer.setHH_NO(res.getString(3));
            buffer.setENUMERATOR(res.getString(5));
            buffer.setSUPERVISOR(res.getString(6));
            buffer.setQUALITY_CONTROLLER(res.getString(7));
            buffer.setInterview_Status(res.getString(34));
            a.add(buffer);

        }
        return a;
    }

    public Sample getSample(SQLiteDatabase db, String assgmnt) {
        Sample s = new Sample();
        //db = this.getWritableDatabase();
        db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + tblAssignments, null);

        Assignments buffer = new Assignments();
        while (res.moveToNext()) {
            if (res.getString(0).equals(assgmnt)) {
                buffer.setSample_FK(res.getString(8));
            }


        }

        Cursor res1 = db.rawQuery("select * from Sample", null);
        while (res1.moveToNext()) {
            if (res1.getString(0).equals(buffer.getSample_FK())) {
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

    /************to attend************/
    public List<Assignments> getdataGInfo(String AssignmentID) {
        // DataModel dataModel = new DataModel();
        List<Assignments> StartedHH = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblAssignments + " where EA_Assignment_ID = " + AssignmentID + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        Assignments dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new Assignments();
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

    /***************to atted******************/
    public List<HouseHold> getdataHouseInfo() {
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhousehold, null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null && cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals(3)) {

                dataModel = new HouseHold();
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
                dataModel.setClear(cursor.getString(cursor.getColumnIndexOrThrow("Clear")));

                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo : hhDetails) {

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
    public List<HouseHold> getReject() {
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhousehold, null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {
            if ((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                    cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("2"))
            ) {

                dataModel = new HouseHold();
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
                dataModel.setClear(cursor.getString(cursor.getColumnIndexOrThrow("Clear")));

                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo : hhDetails) {

            // Log.i("Hellomo",""+mo.getCity());
        }

        //

        return hhDetails;
    }


    /*** to atted****************
     * GET STARTED HOUSEHOLD FROM DB
     * @return
     */
    public List<HouseHold> getStarted() {
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhousehold, null);
        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {

            if ((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                    cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("9"))) {

                dataModel = new HouseHold();
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
                dataModel.setClear(cursor.getString(cursor.getColumnIndexOrThrow("Clear")));

                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);
            }
        }

        for (HouseHold mo : hhDetails) {

            // Log.i("Hellomo",""+mo.getCity());
        }

        //

        return hhDetails;
    }

 ///stopped here*******************************************************************************************************************************************************************
    public List<PersonRoster> getdataHhP(String assignment_ID, String Batch) {

        List<PersonRoster> PRoster = new ArrayList<>();
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhhroster + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        PersonRoster dataModel = null;

        while (cursor.moveToNext()) {
            dataModel = new PersonRoster();

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
            String P08 = cursor.getString(cursor.getColumnIndexOrThrow("P08"));
            String P09 = cursor.getString(cursor.getColumnIndexOrThrow("P09"));
            String P10 = cursor.getString(cursor.getColumnIndexOrThrow("P10"));
            String P11 = cursor.getString(cursor.getColumnIndexOrThrow("P11"));
            String P12 = cursor.getString(cursor.getColumnIndexOrThrow("P12"));
            String P13 = cursor.getString(cursor.getColumnIndexOrThrow("P13"));
            String P13Other= cursor.getString(cursor.getColumnIndexOrThrow("P13Other"));
            String P14 = cursor.getString(cursor.getColumnIndexOrThrow("P14"));
            String P15 = cursor.getString(cursor.getColumnIndexOrThrow("P15"));
            String P16 = cursor.getString(cursor.getColumnIndexOrThrow("P16"));
            String P17 = cursor.getString(cursor.getColumnIndexOrThrow("P17"));
            String P18 = cursor.getString(cursor.getColumnIndexOrThrow("P18"));
            String P19 = cursor.getString(cursor.getColumnIndexOrThrow("P19"));
            String P20 = cursor.getString(cursor.getColumnIndexOrThrow("P20"));
            String P21 = cursor.getString(cursor.getColumnIndexOrThrow("P21"));
            //String B3_RapidConsent_Yes_No = cursor.getString(cursor.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));

            // String B3_Date = cursor.getString(cursor.getColumnIndexOrThrow("B3_Date"));
            String Barcode = cursor.getString(cursor.getColumnIndexOrThrow("Barcode"));
            //String U15Rapid_Results = cursor.getString(cursor.getColumnIndexOrThrow("U15Rapid_Result"));
            // String Rapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow("Rapid_Comment"));


            String BloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("BloodDraw"));
            String BloodVolume_1 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_1"));
            String BloodVolume_4 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_4"));
            String BloodVolume_6 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_6"));
            String BloodVolume_10 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_10"));
            String BloodVolumeComment = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolumeComment"));
            String Rapid = cursor.getString(cursor.getColumnIndexOrThrow("Rapid"));
            String RapidResults = cursor.getString(cursor.getColumnIndexOrThrow("RapidResults"));
            String BloodLabTest = cursor.getString(cursor.getColumnIndexOrThrow("BloodLabTest"));
            String BloodStore = cursor.getString(cursor.getColumnIndexOrThrow("BloodStore"));
            String RapidDate = cursor.getString(cursor.getColumnIndexOrThrow("RapidDate"));
            String BloodSampleCollected = cursor.getString(cursor.getColumnIndexOrThrow("BloodSampleCollected"));
            String ChPrntlConsentBloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodDraw"));
            String ChPrntlConsentRHT = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentRHT"));
            String ChPrntlConsentLabTest = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentLabTest"));
            String ChPrntlConsentBloodStore = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodStore"));
            String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            String ChPrntlConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentDate"));

            String PrntlConsentX_Ray = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_Ray"));
            String PrntlConsentX_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayReview"));
            String PrntlConsentX_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayStore"));
            String PrntlConsentSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_Collect"));
            String PrntlParentSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("PrntlParentSP_AddTests"));
            String PrntlConsentSP_LabTest = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_LabTest"));
            String PrntlConsentTBDate = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentTBDate"));

            String IndTB_X_Ray = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_Ray"));
            String IndTB_X_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayReview"));
            String IndTB_X_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayStore"));
            String IndSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_Collect"));
            String IndSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_AddTests"));

            String IndSP_LabTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_LabTests"));
            String IndTB_ConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_ConsentDate"));

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
            dataModel.setP08(P08);
            dataModel.setP09(P09);
            dataModel.setP10(P10);
            dataModel.setP11(P11);
            dataModel.setP12(P12);
            dataModel.setP13(P13);
            dataModel.setP13Other(P13Other);
            dataModel.setP14(P14);
            dataModel.setP15(P15);
            dataModel.setP16(P16);
            dataModel.setP17(P17);
            dataModel.setP18(P18);
            dataModel.setP19(P19);
            dataModel.setP20(P20);
            dataModel.setP21(P21);
            // dataModel.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);

            // dataModel.setB3_Date(B3_Date);


            dataModel.setBarcode(Barcode);
            dataModel.setBloodDraw(BloodDraw);
            dataModel.setBloodVolume_1(BloodVolume_1);
            dataModel.setBloodVolume_4(BloodVolume_4);
            dataModel.setBloodVolume_6(BloodVolume_6);
            dataModel.setBloodVolume_10(BloodVolume_10);
            dataModel.setBloodVolumeComment(BloodVolumeComment);
            dataModel.setRapid(Rapid);
            dataModel.setRapidResults(RapidResults);
            dataModel.setBloodLabTest(BloodLabTest);
            dataModel.setBloodStore(BloodStore);
            dataModel.setRapidDate(RapidDate);
            dataModel.setBloodSampleCollected(BloodSampleCollected);
            dataModel.setChPrntlConsentBloodDraw(ChPrntlConsentBloodDraw);
            dataModel.setChPrntlConsentRHT(ChPrntlConsentRHT);
            dataModel.setChPrntlConsentLabTest(ChPrntlConsentLabTest);
            dataModel.setChPrntlConsentBloodStore(ChPrntlConsentBloodStore);
            dataModel.setB3_Guardian(B3_Guardian);
            dataModel.setChPrntlConsentDate(ChPrntlConsentDate);

            dataModel.setPrntlConsentX_Ray(PrntlConsentX_Ray);
            dataModel.setPrntlConsentX_RayReview(PrntlConsentX_RayReview);
            dataModel.setPrntlConsentX_RayStore(PrntlConsentX_RayStore);
            dataModel.setPrntlConsentSP_Collect(PrntlConsentSP_Collect);
            dataModel.setPrntlParentSP_AddTests(PrntlParentSP_AddTests);
            dataModel.setPrntlConsentSP_LabTest(PrntlConsentSP_LabTest);
            dataModel.setPrntlConsentTBDate(PrntlConsentTBDate);

            dataModel.setIndTB_X_Ray(IndTB_X_Ray);
            dataModel.setIndTB_X_RayReview(IndTB_X_RayReview);
            dataModel.setIndTB_X_RayStore(IndTB_X_RayStore);
            dataModel.setIndSP_Collect(IndSP_Collect);
            dataModel.setIndSP_AddTests(IndSP_AddTests);

            dataModel.setIndSP_LabTests(IndSP_LabTests);
            dataModel.setIndTB_ConsentDate(IndTB_ConsentDate);

            // dataModel.setOv64BloodDraw(Ov64BloodDraw);
            //dataModel.setRapid_Comment(Rapid_Comment);

            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            if (dataModel.getAssignmentID().equals(assignment_ID) && dataModel.getBatch().equals(Batch)) {
                PRoster.add(dataModel);
            }

        }


        return PRoster;
    }




    public List<PersonRoster> getdataHhPBar() {
        // DataModel dataModel = new DataModel();
        List<PersonRoster> PRoster = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhhroster + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        PersonRoster dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new PersonRoster();

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
            String P08 = cursor.getString(cursor.getColumnIndexOrThrow("P08"));
            String P09 = cursor.getString(cursor.getColumnIndexOrThrow("P09"));
            String P10 = cursor.getString(cursor.getColumnIndexOrThrow("P10"));
            String P11 = cursor.getString(cursor.getColumnIndexOrThrow("P11"));
            String P12 = cursor.getString(cursor.getColumnIndexOrThrow("P12"));
            String P13 = cursor.getString(cursor.getColumnIndexOrThrow("P13"));
            String P13Other= cursor.getString(cursor.getColumnIndexOrThrow("P13Other"));
            String P14 = cursor.getString(cursor.getColumnIndexOrThrow("P14"));
            String P15 = cursor.getString(cursor.getColumnIndexOrThrow("P15"));
            String P16 = cursor.getString(cursor.getColumnIndexOrThrow("P16"));
            String P17 = cursor.getString(cursor.getColumnIndexOrThrow("P17"));
            String P18 = cursor.getString(cursor.getColumnIndexOrThrow("P18"));
            String P19 = cursor.getString(cursor.getColumnIndexOrThrow("P19"));
            String P20 = cursor.getString(cursor.getColumnIndexOrThrow("P20"));
            String P21 = cursor.getString(cursor.getColumnIndexOrThrow("P21"));
            //String B3_RapidConsent_Yes_No = cursor.getString(cursor.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));

            // String B3_Date = cursor.getString(cursor.getColumnIndexOrThrow("B3_Date"));
            String Barcode = cursor.getString(cursor.getColumnIndexOrThrow("Barcode"));
            //String U15Rapid_Results = cursor.getString(cursor.getColumnIndexOrThrow("U15Rapid_Result"));
            // String Rapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow("Rapid_Comment"));


            String BloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("BloodDraw"));
            String BloodVolume_1 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_1"));
            String BloodVolume_4 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_4"));
            String BloodVolume_6 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_6"));
            String BloodVolume_10 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_10"));
            String BloodVolumeComment = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolumeComment"));
            String Rapid = cursor.getString(cursor.getColumnIndexOrThrow("Rapid"));
            String RapidResults = cursor.getString(cursor.getColumnIndexOrThrow("RapidResults"));
            String BloodLabTest = cursor.getString(cursor.getColumnIndexOrThrow("BloodLabTest"));
            String BloodStore = cursor.getString(cursor.getColumnIndexOrThrow("BloodStore"));
            String RapidDate = cursor.getString(cursor.getColumnIndexOrThrow("RapidDate"));
            String BloodSampleCollected = cursor.getString(cursor.getColumnIndexOrThrow("BloodSampleCollected"));
            String ChPrntlConsentBloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodDraw"));
            String ChPrntlConsentRHT = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentRHT"));
            String ChPrntlConsentLabTest = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentLabTest"));
            String ChPrntlConsentBloodStore = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodStore"));
            String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            String ChPrntlConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentDate"));

            String PrntlConsentX_Ray = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_Ray"));
            String PrntlConsentX_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayReview"));
            String PrntlConsentX_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayStore"));
            String PrntlConsentSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_Collect"));
            String PrntlParentSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("PrntlParentSP_AddTests"));
            String PrntlConsentSP_LabTest = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_LabTest"));
            String PrntlConsentTBDate = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentTBDate"));

            String IndTB_X_Ray = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_Ray"));
            String IndTB_X_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayReview"));
            String IndTB_X_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayStore"));
            String IndSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_Collect"));
            String IndSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_AddTests"));

            String IndSP_LabTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_LabTests"));
            String IndTB_ConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_ConsentDate"));

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
            dataModel.setP08(P08);
            dataModel.setP09(P09);
            dataModel.setP10(P10);
            dataModel.setP11(P11);
            dataModel.setP12(P12);
            dataModel.setP13(P13);
            dataModel.setP13Other(P13Other);
            dataModel.setP14(P14);
            dataModel.setP15(P15);
            dataModel.setP16(P16);
            dataModel.setP17(P17);
            dataModel.setP18(P18);
            dataModel.setP19(P19);
            dataModel.setP20(P20);
            dataModel.setP21(P21);
            // dataModel.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);

            // dataModel.setB3_Date(B3_Date);


            dataModel.setBarcode(Barcode);
            dataModel.setBloodDraw(BloodDraw);
            dataModel.setBloodVolume_1(BloodVolume_1);
            dataModel.setBloodVolume_4(BloodVolume_4);
            dataModel.setBloodVolume_6(BloodVolume_6);
            dataModel.setBloodVolume_10(BloodVolume_10);
            dataModel.setBloodVolumeComment(BloodVolumeComment);
            dataModel.setRapid(Rapid);
            dataModel.setRapidResults(RapidResults);
            dataModel.setBloodLabTest(BloodLabTest);
            dataModel.setBloodStore(BloodStore);
            dataModel.setRapidDate(RapidDate);
            dataModel.setBloodSampleCollected(BloodSampleCollected);
            dataModel.setChPrntlConsentBloodDraw(ChPrntlConsentBloodDraw);
            dataModel.setChPrntlConsentRHT(ChPrntlConsentRHT);
            dataModel.setChPrntlConsentLabTest(ChPrntlConsentLabTest);
            dataModel.setChPrntlConsentBloodStore(ChPrntlConsentBloodStore);
            dataModel.setB3_Guardian(B3_Guardian);
            dataModel.setChPrntlConsentDate(ChPrntlConsentDate);

            dataModel.setPrntlConsentX_Ray(PrntlConsentX_Ray);
            dataModel.setPrntlConsentX_RayReview(PrntlConsentX_RayReview);
            dataModel.setPrntlConsentX_RayStore(PrntlConsentX_RayStore);
            dataModel.setPrntlConsentSP_Collect(PrntlConsentSP_Collect);
            dataModel.setPrntlParentSP_AddTests(PrntlParentSP_AddTests);
            dataModel.setPrntlConsentSP_LabTest(PrntlConsentSP_LabTest);
            dataModel.setPrntlConsentTBDate(PrntlConsentTBDate);

            dataModel.setIndTB_X_Ray(IndTB_X_Ray);
            dataModel.setIndTB_X_RayReview(IndTB_X_RayReview);
            dataModel.setIndTB_X_RayStore(IndTB_X_RayStore);
            dataModel.setIndSP_Collect(IndSP_Collect);
            dataModel.setIndSP_AddTests(IndSP_AddTests);

            dataModel.setIndSP_LabTests(IndSP_LabTests);
            dataModel.setIndTB_ConsentDate(IndTB_ConsentDate);

            // dataModel.setOv64BloodDraw(Ov64BloodDraw);
            //dataModel.setRapid_Comment(Rapid_Comment);

            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
           // if (dataModel.getAssignmentID().equals(assignment_ID) && dataModel.getBatch().equals(Batch)) {
                PRoster.add(dataModel);
            //}

        }


        return PRoster;
    }

    /**
     * get persons for Listing P01
     */
    public List<PersonRoster> getPersonP01(String assignment_ID, String Batch) {

        List<PersonRoster> PRoster = new ArrayList<>();
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhhroster + " WHERE EA_Assignment_ID="+assignment_ID+" and BatchNumber="+Batch+";", null);
        StringBuffer stringBuffer = new StringBuffer();
        PersonRoster dataModel = null;

        while (cursor.moveToNext()) {
            dataModel = new PersonRoster();

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
            String P08 = cursor.getString(cursor.getColumnIndexOrThrow("P08"));
            String P09 = cursor.getString(cursor.getColumnIndexOrThrow("P09"));
            String P10 = cursor.getString(cursor.getColumnIndexOrThrow("P10"));
            String P11 = cursor.getString(cursor.getColumnIndexOrThrow("P11"));
            String P12 = cursor.getString(cursor.getColumnIndexOrThrow("P12"));
            String P13 = cursor.getString(cursor.getColumnIndexOrThrow("P13"));
            String P13Other= cursor.getString(cursor.getColumnIndexOrThrow("P13Other"));
            String P14 = cursor.getString(cursor.getColumnIndexOrThrow("P14"));
            String P15 = cursor.getString(cursor.getColumnIndexOrThrow("P15"));
            String P16 = cursor.getString(cursor.getColumnIndexOrThrow("P16"));
            String P17 = cursor.getString(cursor.getColumnIndexOrThrow("P17"));
            String P18 = cursor.getString(cursor.getColumnIndexOrThrow("P18"));
            String P19 = cursor.getString(cursor.getColumnIndexOrThrow("P19"));
            String P20 = cursor.getString(cursor.getColumnIndexOrThrow("P20"));
            String P21 = cursor.getString(cursor.getColumnIndexOrThrow("P21"));
            //String B3_RapidConsent_Yes_No = cursor.getString(cursor.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));

            // String B3_Date = cursor.getString(cursor.getColumnIndexOrThrow("B3_Date"));
            String Barcode = cursor.getString(cursor.getColumnIndexOrThrow("Barcode"));
            //String U15Rapid_Results = cursor.getString(cursor.getColumnIndexOrThrow("U15Rapid_Result"));
            // String Rapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow("Rapid_Comment"));


            String BloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("BloodDraw"));
            String BloodVolume_1 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_1"));
            String BloodVolume_4 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_4"));
            String BloodVolume_6 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_6"));
            String BloodVolume_10 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_10"));
            String BloodVolumeComment = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolumeComment"));
            String Rapid = cursor.getString(cursor.getColumnIndexOrThrow("Rapid"));
            String RapidResults = cursor.getString(cursor.getColumnIndexOrThrow("RapidResults"));
            String BloodLabTest = cursor.getString(cursor.getColumnIndexOrThrow("BloodLabTest"));
            String BloodStore = cursor.getString(cursor.getColumnIndexOrThrow("BloodStore"));
            String RapidDate = cursor.getString(cursor.getColumnIndexOrThrow("RapidDate"));
            String BloodSampleCollected = cursor.getString(cursor.getColumnIndexOrThrow("BloodSampleCollected"));
            String ChPrntlConsentBloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodDraw"));
            String ChPrntlConsentRHT = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentRHT"));
            String ChPrntlConsentLabTest = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentLabTest"));
            String ChPrntlConsentBloodStore = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodStore"));
            String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            String ChPrntlConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentDate"));

            String PrntlConsentX_Ray = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_Ray"));
            String PrntlConsentX_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayReview"));
            String PrntlConsentX_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayStore"));
            String PrntlConsentSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_Collect"));
            String PrntlParentSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("PrntlParentSP_AddTests"));
            String PrntlConsentSP_LabTest = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_LabTest"));
            String PrntlConsentTBDate = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentTBDate"));

            String IndTB_X_Ray = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_Ray"));
            String IndTB_X_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayReview"));
            String IndTB_X_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayStore"));
            String IndSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_Collect"));
            String IndSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_AddTests"));

            String IndSP_LabTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_LabTests"));
            String IndTB_ConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_ConsentDate"));

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
            dataModel.setP08(P08);
            dataModel.setP09(P09);
            dataModel.setP10(P10);
            dataModel.setP11(P11);
            dataModel.setP12(P12);
            dataModel.setP13(P13);
            dataModel.setP13Other(P13Other);
            dataModel.setP14(P14);
            dataModel.setP15(P15);
            dataModel.setP16(P16);
            dataModel.setP17(P17);
            dataModel.setP18(P18);
            dataModel.setP19(P19);
            dataModel.setP20(P20);
            dataModel.setP21(P21);
            // dataModel.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);

            // dataModel.setB3_Date(B3_Date);


            dataModel.setBarcode(Barcode);
            dataModel.setBloodDraw(BloodDraw);
            dataModel.setBloodVolume_1(BloodVolume_1);
            dataModel.setBloodVolume_4(BloodVolume_4);
            dataModel.setBloodVolume_6(BloodVolume_6);
            dataModel.setBloodVolume_10(BloodVolume_10);
            dataModel.setBloodVolumeComment(BloodVolumeComment);
            dataModel.setRapid(Rapid);
            dataModel.setRapidResults(RapidResults);
            dataModel.setBloodLabTest(BloodLabTest);
            dataModel.setBloodStore(BloodStore);
            dataModel.setRapidDate(RapidDate);
            dataModel.setBloodSampleCollected(BloodSampleCollected);
            dataModel.setChPrntlConsentBloodDraw(ChPrntlConsentBloodDraw);
            dataModel.setChPrntlConsentRHT(ChPrntlConsentRHT);
            dataModel.setChPrntlConsentLabTest(ChPrntlConsentLabTest);
            dataModel.setChPrntlConsentBloodStore(ChPrntlConsentBloodStore);
            dataModel.setB3_Guardian(B3_Guardian);
            dataModel.setChPrntlConsentDate(ChPrntlConsentDate);

            dataModel.setPrntlConsentX_Ray(PrntlConsentX_Ray);
            dataModel.setPrntlConsentX_RayReview(PrntlConsentX_RayReview);
            dataModel.setPrntlConsentX_RayStore(PrntlConsentX_RayStore);
            dataModel.setPrntlConsentSP_Collect(PrntlConsentSP_Collect);
            dataModel.setPrntlParentSP_AddTests(PrntlParentSP_AddTests);
            dataModel.setPrntlConsentSP_LabTest(PrntlConsentSP_LabTest);
            dataModel.setPrntlConsentTBDate(PrntlConsentTBDate);

            dataModel.setIndTB_X_Ray(IndTB_X_Ray);
            dataModel.setIndTB_X_RayReview(IndTB_X_RayReview);
            dataModel.setIndTB_X_RayStore(IndTB_X_RayStore);
            dataModel.setIndSP_Collect(IndSP_Collect);
            dataModel.setIndSP_AddTests(IndSP_AddTests);

            dataModel.setIndSP_LabTests(IndSP_LabTests);
            dataModel.setIndTB_ConsentDate(IndTB_ConsentDate);

            // dataModel.setOv64BloodDraw(Ov64BloodDraw);
            //dataModel.setRapid_Comment(Rapid_Comment);

            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            if (dataModel.getAssignmentID().equals(assignment_ID) && dataModel.getBatch().equals(Batch)) {
                PRoster.add(dataModel);
            }

        }


        return PRoster;
    }












    /**
     * CHECK BARCODE IN ENUMERATORS GIVEN HOUSEHOLDS
     */
    public List<PersonRoster> getPersonsBarCheck(String assignment_ID) {
        // DataModel dataModel = new DataModel();
        List<PersonRoster> PRoster = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblhhroster + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        PersonRoster dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new PersonRoster();

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
            //String B3_RapidConsent_Yes_No = cursor.getString(cursor.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));
            // String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            // String B3_Date = cursor.getString(cursor.getColumnIndexOrThrow("B3_Date"));
            String Barcode = cursor.getString(cursor.getColumnIndexOrThrow("Barcode"));
            //String U15Rapid_Results = cursor.getString(cursor.getColumnIndexOrThrow("U15Rapid_Result"));
            //String Rapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow("Rapid_Comment"));
            String BloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("BloodDraw"));
            String BloodVolume_1 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_1"));
            String BloodVolume_4 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_4"));
            String BloodVolume_6 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_6"));
            String BloodVolume_10 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolume_10"));
            String BloodVolumeComment = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolumeComment"));
            String Rapid = cursor.getString(cursor.getColumnIndexOrThrow("Rapid"));
            String RapidResults = cursor.getString(cursor.getColumnIndexOrThrow("RapidResults"));
            String BloodLabTest = cursor.getString(cursor.getColumnIndexOrThrow("BloodLabTest"));
            String BloodStore = cursor.getString(cursor.getColumnIndexOrThrow("BloodStore"));
            String RapidDate = cursor.getString(cursor.getColumnIndexOrThrow("RapidDate"));
            String BloodSampleCollected = cursor.getString(cursor.getColumnIndexOrThrow("BloodSampleCollected"));
            String ChPrntlConsentBloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodDraw"));
            String ChPrntlConsentRHT = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentRHT"));
            String ChPrntlConsentLabTest = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentLabTest"));
            String ChPrntlConsentBloodStore = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentBloodStore"));
            String B3_Guardian = cursor.getString(cursor.getColumnIndexOrThrow("B3_Guardian"));
            String ChPrntlConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("ChPrntlConsentDate"));

            String PrntlConsentX_Ray = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_Ray"));
            String PrntlConsentX_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayReview"));
            String PrntlConsentX_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentX_RayStore"));
            String PrntlConsentSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_Collect"));
            String PrntlParentSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("PrntlParentSP_AddTests"));
            String PrntlConsentSP_LabTest = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentSP_LabTest"));
            String PrntlConsentTBDate = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentTBDate"));

            String IndTB_X_Ray = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_Ray"));
            String IndTB_X_RayReview = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayReview"));
            String IndTB_X_RayStore = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_X_RayStore"));
            String IndSP_Collect = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_Collect"));
            String IndSP_AddTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_AddTests"));

            String IndSP_LabTests = cursor.getString(cursor.getColumnIndexOrThrow("IndSP_LabTests"));
            String IndTB_ConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("IndTB_ConsentDate"));


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
            //dataModel.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);
            // dataModel.setB3_Guardian(B3_Guardian);
            //dataModel.setB3_Date(B3_Date);
            dataModel.setBarcode(Barcode);
            //dataModel.setU15Rapid_Results(U15Rapid_Results);
            //dataModel.setRapid_Comment(Rapid_Comment);
            dataModel.setBloodDraw(BloodDraw);
            dataModel.setBloodVolume_1(BloodVolume_1);
            dataModel.setBloodVolume_4(BloodVolume_4);
            dataModel.setBloodVolume_6(BloodVolume_6);
            dataModel.setBloodVolume_10(BloodVolume_10);
            dataModel.setBloodVolumeComment(BloodVolumeComment);
            dataModel.setRapid(Rapid);
            dataModel.setRapidResults(RapidResults);
            dataModel.setBloodLabTest(BloodLabTest);
            dataModel.setBloodStore(BloodStore);
            dataModel.setRapidDate(RapidDate);
            dataModel.setBloodSampleCollected(BloodSampleCollected);

            dataModel.setChPrntlConsentBloodDraw(ChPrntlConsentBloodDraw);
            dataModel.setChPrntlConsentRHT(ChPrntlConsentRHT);
            dataModel.setChPrntlConsentLabTest(ChPrntlConsentLabTest);
            dataModel.setChPrntlConsentBloodStore(ChPrntlConsentBloodStore);
            dataModel.setB3_Guardian(B3_Guardian);
            dataModel.setChPrntlConsentDate(ChPrntlConsentDate);

            dataModel.setPrntlConsentX_Ray(PrntlConsentX_Ray);
            dataModel.setPrntlConsentX_RayReview(PrntlConsentX_RayReview);
            dataModel.setPrntlConsentX_RayStore(PrntlConsentX_RayStore);
            dataModel.setPrntlConsentSP_Collect(PrntlConsentSP_Collect);
            dataModel.setPrntlParentSP_AddTests(PrntlParentSP_AddTests);
            dataModel.setPrntlConsentSP_LabTest(PrntlConsentSP_LabTest);
            dataModel.setPrntlConsentTBDate(PrntlConsentTBDate);

            dataModel.setIndTB_X_Ray(IndTB_X_Ray);
            dataModel.setIndTB_X_RayReview(IndTB_X_RayReview);
            dataModel.setIndTB_X_RayStore(IndTB_X_RayStore);
            dataModel.setIndSP_Collect(IndSP_Collect);
            dataModel.setIndSP_AddTests(IndSP_AddTests);

            dataModel.setIndSP_LabTests(IndSP_LabTests);
            dataModel.setIndTB_ConsentDate(IndTB_ConsentDate);

            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            if (dataModel.getAssignmentID().equals(assignment_ID)) {
                PRoster.add(dataModel);
            }

        }


        return PRoster;
    }

    /**
     * RETRIEVE INDIVIDUAL DATA
     *
     * @param house
     * @return
     */

    public List<Individual> getdataIndivisual(HouseHold house) {
        // DataModel dataModel = new DataModel();
        List<Individual> IndividualStarted = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tblindividual + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        Individual dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new Individual();
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

            String Q504_1 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Pain"));
            String Q504_2 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Reduced"));
            String Q504_3 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Fear"));
            String Q504_4 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Culture"));
            String Q504_5 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Religion"));
            String Q504_6 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Spouse"));
            String Q504_7 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Parental"));
            String Q504_8 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_Long"));
            String Q504_10 = cursor.getString(cursor.getColumnIndexOrThrow("Q504_FearHIV"));
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
            String IndBarcode = cursor.getString(cursor.getColumnIndexOrThrow("IndBarcode"));
            String IndRapidResults = cursor.getString(cursor.getColumnIndexOrThrow("IndRapidResults"));
            //String	         IndRapidDate = cursor.getString(cursor.getColumnIndexOrThrow( "IndRapidDate "));
            //String	        IndRapid_Comment = cursor.getString(cursor.getColumnIndexOrThrow( "IndRapid_Comment "));

            String IndvQuestionnairePConsent15_17 = cursor.getString(cursor.getColumnIndexOrThrow("IndvQuestionnairePConsent15_17"));
            String IndvQuestionnaireConsent = cursor.getString(cursor.getColumnIndexOrThrow("IndvQuestionnaireConsent"));
            String IndvBloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("IndvBloodDraw"));
            String BloodVol_1 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVol_1"));
            String BloodVol_4 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVol_4"));
            String BloodVol_6 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVol_6"));
            String BloodVol_10 = cursor.getString(cursor.getColumnIndexOrThrow("BloodVol_10"));
            String BloodVolComment = cursor.getString(cursor.getColumnIndexOrThrow("BloodVolComment"));
            String IndBloodLabTest = cursor.getString(cursor.getColumnIndexOrThrow("IndBloodLabTest"));
            String IndBloodStore = cursor.getString(cursor.getColumnIndexOrThrow("IndBloodStore"));
            String PrntlConsentBloodDraw = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentBloodDraw"));
            String PrntlConsentRHT = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentRHT"));
            String PrntlConsentLabTest = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentLabTest"));
            String PrntlConsentBloodStore = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentBloodStore"));
            String PrntlParentID = cursor.getString(cursor.getColumnIndexOrThrow("PrntlParentID"));
            String PrntlConsentDate = cursor.getString(cursor.getColumnIndexOrThrow("PrntlConsentDate"));

            String VISIT1 = cursor.getString(cursor.getColumnIndexOrThrow("VISIT1"));
            String VISIT2 = cursor.getString(cursor.getColumnIndexOrThrow("VISIT2"));
            String VISIT3 = cursor.getString(cursor.getColumnIndexOrThrow("VISIT3"));
            String INDDATE1 = cursor.getString(cursor.getColumnIndexOrThrow("DATE1"));
            String INDDATE2 = cursor.getString(cursor.getColumnIndexOrThrow("DATE2"));
            String INDDATE3 = cursor.getString(cursor.getColumnIndexOrThrow("DATE3"));
            String Sync = cursor.getString(cursor.getColumnIndexOrThrow("Sync"));


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
            // dataModel.setIndRapid_Comment(IndRapid_Comment);
            dataModel.setIndvQuestionnairePConsent15_17(IndvQuestionnairePConsent15_17);
            dataModel.setIndvQuestionnaireConsent(IndvQuestionnaireConsent);
            dataModel.setIndvBloodDraw(IndvBloodDraw);
            dataModel.setBloodVol_1(BloodVol_1);
            dataModel.setBloodVol_4(BloodVol_4);
            dataModel.setBloodVol_6(BloodVol_6);
            dataModel.setBloodVol_10(BloodVol_10);
            dataModel.setBloodVolComment(BloodVolComment);
            dataModel.setIndBloodLabTest(IndBloodLabTest);
            dataModel.setIndBloodStore(IndBloodStore);
            dataModel.setPrntlConsentBloodDraw(PrntlConsentBloodDraw);
            dataModel.setPrntlConsentRHT(PrntlConsentRHT);
            dataModel.setPrntlConsentLabTest(PrntlConsentLabTest);
            dataModel.setPrntlConsentBloodStore(PrntlConsentBloodStore);
            dataModel.setPrntlParentID(PrntlParentID);
            dataModel.setPrntlConsentDate(PrntlConsentDate);

            dataModel.setVISIT1(VISIT1);
            dataModel.setVISIT2(VISIT2);
            dataModel.setVISIT3(VISIT3);
            dataModel.setDATE1(INDDATE1);
            dataModel.setDATE2(INDDATE2);
            dataModel.setDATE3(INDDATE3);
            dataModel.setSync(Sync);


            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);

            if (house.getAssignment_ID().equals(dataModel.getAssignmentID()) && house.getBatchNumber().equals(dataModel.getBatch())) {
                IndividualStarted.add(dataModel);
            }


        }

        return IndividualStarted;
    }


    public Individual getdataIndivisual(String AssID, String bNumber, int SRN) {


        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();
        Cursor cursor2 = db.rawQuery("select * from " + tblindividual + " WHERE Assignment_ID=" + AssID + " and BatchNumber=" + bNumber + " AND SRNO=" + SRN, null);

        StringBuffer stringBuffer = new StringBuffer();
        Individual ind = new Individual();
        while (cursor2.moveToNext()) {

            ind.setAssignmentID(cursor2.getString(cursor2.getColumnIndexOrThrow("Assignment_ID")));
            ind.setBatch(cursor2.getString(cursor2.getColumnIndexOrThrow("BatchNumber")));
            ind.setSRNO(Integer.parseInt(cursor2.getString(cursor2.getColumnIndexOrThrow("SRNO"))));
            ind.setIndBarcode(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBarcode")));
            ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
            ind.setQ102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q102")));
            ind.setQ103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q103")));
            ind.setQ104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104")));
            ind.setQ104a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104a")));
            ind.setQ104b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104b")));
            ind.setQ104c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104c")));
            //ind.setQ104cBISCED(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104cBISCED")));
            ind.setQ105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105")));
            ind.setQ105Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105Other")));
            ind.setQ105a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105a")));
            ind.setQ105b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105b")));
            ind.setQ106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106")));
            ind.setQ106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106a")));
            ind.setQ106aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106aOther")));
            ind.setQ106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106b")));
            ind.setQ106c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106c")));
            ind.setQ106d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106d")));
            ind.setQ107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107")));
            String s = cursor2.getString(cursor2.getColumnIndexOrThrow("Q107a"));

            if (s != null) {
                ind.setQ107aMnth(s.substring(0, 2));
                ind.setQ107aYY(s.substring(2, 4));
            }
            ind.setQ107b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107b")));
            ind.setQ107bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107bOther")));
            ind.setQ107c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107c")));
            ind.setQ107cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107cOther")));
            ind.setQ201(cursor2.getString(cursor2.getColumnIndexOrThrow("Q201")));
            ind.setQ202(cursor2.getString(cursor2.getColumnIndexOrThrow("Q202")));
            ind.setQ203(cursor2.getString(cursor2.getColumnIndexOrThrow("Q203")));
            ind.setQ204(cursor2.getString(cursor2.getColumnIndexOrThrow("Q204")));
            ind.setQ205(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205")));
            ind.setQ205a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205a")));
            ind.setQ301(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
            ind.setQ301a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
            ind.setQ302(cursor2.getString(cursor2.getColumnIndexOrThrow("Q302")));
            ind.setQ303(cursor2.getString(cursor2.getColumnIndexOrThrow("Q303")));
            ind.setQ303a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q303a")));
            ind.setQ304(cursor2.getString(cursor2.getColumnIndexOrThrow("Q304")));
            ind.setQ304a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q304a")));
            ind.setQ305_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Smoking")));
            ind.setQ305_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Sniffing")));
            ind.setQ305_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Chewing")));
            ind.setQ305_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305None")));
            ind.setQ306(cursor2.getString(cursor2.getColumnIndexOrThrow("Q306")));
            ind.setQ307(cursor2.getString(cursor2.getColumnIndexOrThrow("Q307")));
            ind.setQ401(cursor2.getString(cursor2.getColumnIndexOrThrow("Q401")));
            ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
            ind.setQ402(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402")));
            ind.setQ402a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402a")));
            ind.setQ402b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402b")));
            ind.setQ403(cursor2.getString(cursor2.getColumnIndexOrThrow("Q403")));
            ind.setQ404_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Vaginal")));
            ind.setQ404_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Anal")));
            ind.setQ404_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Oral")));
            ind.setQ404a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404a")));
            ind.setQ405(cursor2.getString(cursor2.getColumnIndexOrThrow("Q405")));
            ind.setQ406(cursor2.getString(cursor2.getColumnIndexOrThrow("Q406")));
            ind.setQ407(cursor2.getString(cursor2.getColumnIndexOrThrow("Q407")));
            ind.setQ408(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408")));
            ind.setQ408a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408a")));
            ind.setQ410Slapped(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Slapped")));
            ind.setQ410Pushed(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Pushed")));
            ind.setQ410Choked(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Chocked")));
            ind.setQ410Threatened(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Threatened")));
            ind.setQ410Physical(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Physical")));
            ind.setQ410Forced(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Forced")));
            ind.setQ410MadeAfraid(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410MadeAfraid")));
            ind.setQ501(cursor2.getString(cursor2.getColumnIndexOrThrow("Q501")));
            ind.setQ502(cursor2.getString(cursor2.getColumnIndexOrThrow("Q502")));
            ind.setQ503(cursor2.getString(cursor2.getColumnIndexOrThrow("Q503")));
            ind.setQ504_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Pain")));
            ind.setQ504_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Reduced")));
            ind.setQ504_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Fear")));
            ind.setQ504_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Culture")));
            ind.setQ504_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Religion")));
            ind.setQ504_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Spouse")));
            ind.setQ504_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Parental")));
            ind.setQ504_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Long")));
            ind.setQ504_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_FearHIV")));
            ind.setQ504_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Other")));
            ind.setQ601(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601")));
            ind.setQ601a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601a")));
            ind.setQ602_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Youth")));
            ind.setQ602_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602TV")));
            ind.setQ602_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Radio")));
            ind.setQ602_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Newspaper")));
            ind.setQ602_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Hospital")));
            ind.setQ602_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Posters")));
            ind.setQ602_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Traditional")));
            ind.setQ602_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
            ind.setQ602_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Individual")));
            ind.setQ602_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Church")));
            ind.setQ602_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Kgotla")));
            ind.setQ602_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workplace")));
            ind.setQ602_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Peer")));
            ind.setQ602_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602School")));
            ind.setQ602_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602_Other")));
            ind.setQ603_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Condom")));
            ind.setQ603_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603FewerP")));
            ind.setQ603_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Both")));
            ind.setQ603_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCasual")));
            ind.setQ603_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Abstain")));
            ind.setQ603_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCommercial")));
            ind.setQ603_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Injection")));
            ind.setQ603_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Blood")));
            ind.setQ603_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603DontKnow")));
            ind.setQ603_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Other")));
            ind.setQ604(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604")));
            ind.setQ604a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604a")));
            ind.setQ604b_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bYouth")));
            ind.setQ604b_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTV")));
            ind.setQ604b_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bRadio")));
            ind.setQ604b_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bNewspaper")));
            ind.setQ604b_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bHospital")));
            ind.setQ604b_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPoster")));
            ind.setQ604b_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTraditional")));
            ind.setQ604b_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
            ind.setQ604b_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bIndividual")));
            ind.setQ604b_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bChurch")));
            ind.setQ604b_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bKgotla")));
            ind.setQ604b_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bWorkplace")));
            ind.setQ604b_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPeer")));
            ind.setQ604b_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bSchool")));
            ind.setQ604b_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bOther")));
            ind.setQ605_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Windows")));
            ind.setQ605_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Mouth")));
            ind.setQ605_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Hands")));
            ind.setQ605_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Nutrition")));
            ind.setQ605_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Praying")));
            ind.setQ605_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605DontKnow")));
            ind.setQ605_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Other")));
            ind.setQ606(cursor2.getString(cursor2.getColumnIndexOrThrow("Q606")));
            ind.setQ607(cursor2.getString(cursor2.getColumnIndexOrThrow("Q607")));
            ind.setQ608(cursor2.getString(cursor2.getColumnIndexOrThrow("Q608")));
            ind.setQ609(cursor2.getString(cursor2.getColumnIndexOrThrow("Q609")));
            ind.setQ610(cursor2.getString(cursor2.getColumnIndexOrThrow("Q610")));
            ind.setQ611a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611a")));
            ind.setQ611b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611b")));
            ind.setQ611c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611c")));
            ind.setQ612(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612")));
            ind.setQ612a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612a")));
            ind.setQ612aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612Other")));
            ind.setQ613(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613")));
            ind.setQ613a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613a")));
            ind.setQ613aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613aOther")));
            ind.setQ614(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614")));
            ind.setQ614Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614Other")));
            ind.setQ615(cursor2.getString(cursor2.getColumnIndexOrThrow("Q615")));
            ind.setQ616_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Anybody")));
            ind.setQ616_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Poor")));
            ind.setQ616_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Homeless")));
            ind.setQ616_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Alcoholics")));
            ind.setQ616_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Drugs")));
            ind.setQ616_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopHIV")));
            ind.setQ616_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopPrison")));
            ind.setQ616_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Smokers")));
            ind.setQ616_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616DntKnow")));
            ind.setQ616_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Other")));
            ind.setQ617a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Meal")));
            ind.setQ617b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Clothes")));
            ind.setQ617c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Miscarried")));
            ind.setQ617d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Widow")));
            ind.setQ617e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617FamilyHIV")));
            ind.setQ617f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Sejeso")));
            ind.setQ617g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Touching")));
            ind.setQ617h(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Someone")));
            ind.setQ617_0ther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Other")));
            ind.setQ618(cursor2.getString(cursor2.getColumnIndexOrThrow("Q618")));
            ind.setQ619_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Rash")));
            ind.setQ619_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Cough")));
            ind.setQ619_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619LongCough")));
            ind.setQ619_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Blood")));
            ind.setQ619_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Headache")));
            ind.setQ619_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Nausea")));
            ind.setQ619_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Weight")));
            ind.setQ619_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever")));
            ind.setQ619_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever7Days")));
            ind.setQ619_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619ChestPain")));
            ind.setQ619_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Breath")));
            ind.setQ619_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fatigue")));
            ind.setQ619_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Sweats")));
            ind.setQ619_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619DontKnow")));
            ind.setQ619_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Other")));
            ind.setQ620(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620")));
            ind.setQ620_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620Other")));
            ind.setQ621(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621")));
            ind.setQ621a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aSpouse")));
            ind.setQ621a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aPartner")));
            ind.setQ621a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFriend")));
            ind.setQ621a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFamily")));
            ind.setQ621a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aRelative")));
            ind.setQ621a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aHCWorker")));
            ind.setQ621a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aCoWorker")));
            ind.setQ621a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aOther")));
            ind.setQ621b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621b")));
            ind.setQ621bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621bOther")));
            ind.setQ622(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622")));
            ind.setQ622a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622a")));
            ind.setQ622aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622aOther")));
            ind.setQ622b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622b")));
            ind.setQ622bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622bOther")));
            ind.setQ623(cursor2.getString(cursor2.getColumnIndexOrThrow("Q623")));
            ind.setQ624(cursor2.getString(cursor2.getColumnIndexOrThrow("Q624")));
            ind.setQ625(cursor2.getString(cursor2.getColumnIndexOrThrow("Q625")));
            ind.setQ701(cursor2.getString(cursor2.getColumnIndexOrThrow("Q701")));
            ind.setQ702(cursor2.getString(cursor2.getColumnIndexOrThrow("Q702")));
            ind.setQ703(cursor2.getString(cursor2.getColumnIndexOrThrow("Q703")));
            ind.setQ704(cursor2.getString(cursor2.getColumnIndexOrThrow("Q704")));
            ind.setQ705(cursor2.getString(cursor2.getColumnIndexOrThrow("Q705")));
            ind.setQ801(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801")));
            ind.setQ801a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801a")));
            ind.setQ801b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801b")));
            if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")) != null) {
                ind.setQ801cMonth(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(0, 2));
                ind.setQ801cYear(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(2, 6));
            }
            ind.setQ801d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801d")));
            ind.setQ801dOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801dOther")));
            ind.setQ801e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801e")));
            ind.setQ801eOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801eOther")));
            ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));
            ind.setQ802(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802")));
            ind.setQ802a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802a")));
            ind.setQ802aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802aOther")));
            ind.setQ803(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803")));
            ind.setQ803Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803Other")));
            ind.setQ804(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804")));
            ind.setQ804Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804Other")));
            ind.setQ901(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901")));
            ind.setQ901a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901a")));
            ind.setQ901aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901aOther")));
            String sg = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));
            String ss = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));

            if (sg != null) {
                ind.setQ902Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(0, 2));
            }
            if (ss != null) {
                ind.setQ902Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(2, 6));
            }
            ind.setQ903a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903DenyCare")));
            ind.setQ903b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903Gossip")));
            ind.setQ903c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoSex")));
            ind.setQ903d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903VerbalAbuse")));
            ind.setQ903e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903PhysicalAbuse")));
            ind.setQ903f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoContact")));
            ind.setQ903g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903SharingStatus")));
            ind.setQ904(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904")));
            ind.setQ904a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904a")));
            ind.setQ904aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904aOther")));
            if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")) != null) {
                ind.setQ904bMM(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(0, 2));

                        ind.setQ904bYYYY (cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(2, 6));
            }
            ind.setQ904c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904c")));
            ind.setQ904cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904cOther")));
            ind.setQ905(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905")));
            ind.setQ905a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905a")));
            ind.setQ905aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905aOther")));
            ind.setQ1001(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1001")));
            ind.setQ1002(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002")));
            ind.setQ1002a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMCondom")));
            ind.setQ1002a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFCondom")));
            ind.setQ1002a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aInjectContra")));
            ind.setQ1002a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOralContra")));
            ind.setQ1002a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aUID")));
            ind.setQ1002a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aBTL")));
            ind.setQ1002a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFSterilization")));
            ind.setQ1002a_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMSterilization")));
            ind.setQ1002a_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aImplants")));
            ind.setQ1002a_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aEContra")));
            ind.setQ1002a_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSafePeriod")));
            ind.setQ1002a_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aLAM")));
            ind.setQ1002a_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aDiagraphm")));
            ind.setQ1002a_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpermicides")));
            ind.setQ1002a_16(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aNatural")));
            ind.setQ1002a_17(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aTraditional")));
            ind.setQ1002a_18(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpiritual")));
            ind.setQ1002a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOther")));
            ind.setQ1002b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002b")));
            ind.setQ1002bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002bOther")));
            ind.setQ1003(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1003")));
            if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 8) {
                ind.setQ1004_Day(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(0, 2));
                ind.setQ1004_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(2, 4));
                ind.setQ1004_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(4, 8));
            }
            else
            {
                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 4)
                {

                    ind.setQ1004_Day("00");
                    ind.setQ1004_Month("00");
                    ind.setQ1004_Year("0000");
                }
            }

            ind.setQ1004a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004a")));
            ind.setQ1004b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004b")));
            ind.setQ1004bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004bOther")));
            ind.setQ1005(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005")));
            ind.setQ1005a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005a")));
            ind.setQ1006(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1006")));
            ind.setQ1007(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007")));
            ind.setQ1007a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007a")));
            ind.setQ1008(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008")));
            ind.setQ1008a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008a")));
            ind.setQ1008aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008aOther")));
            ind.setQ1009(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009")));
            ind.setQ1009a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009a")));
            ind.setQ1010(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010")));
            ind.setQ1010Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010Other")));
            ind.setQ1011(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011")));
            ind.setQ1011_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011Other")));
            if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")) != null) {
                ind.setQ1012_Week(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(0, 2));
                ind.setQ1012_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(2, 4));
                ind.setQ1012_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(4, 6));
            }
            ind.setQ1013(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1013")));
            ind.setQ1014(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014")));
            ind.setQ1014a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014a")));
            ind.setQ1014b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014b")));
            ind.setQ1015(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015")));
            ind.setQ1015a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015a")));
            ind.setQ1015b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015b")));
            ind.setQ1016(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1016")));
            ind.setQ1017(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1017")));
            ind.setQ1101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101")));
            ind.setQ1101a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101a")));
            ind.setQ1101aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101aOther")));
            ind.setQ1102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102")));
            ind.setQ1102a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102a")));
            ind.setQ1103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103")));
            ind.setQ1103aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDD")));
            ind.setQ1103aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aWks")));
            ind.setQ1103aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDontKnow")));
            ind.setQ1104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1104")));
            ind.setQ1105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1105")));
            ind.setQ1106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106")));
            ind.setQ1106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106a")));
            ind.setQ1106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106b")));
            ind.setQ1106bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106bOther")));
            ind.setQ1107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107")));
            ind.setQ1107aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDD")));
            ind.setQ1107aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aWks")));
            ind.setQ1107aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDontKnow")));
            ind.setQ1108(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108")));
            ind.setQ1108aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDD")));
            ind.setQ1108aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aWks")));
            ind.setQ1108aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDontKnow")));
            ind.setQ1109(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1109")));
            ind.setQ1110(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1110")));
            ind.setQ1111(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111")));
            ind.setQ1111Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111Other")));
            ind.setQ1112(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112")));
            ind.setQ1112_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112Other")));
            ind.setQ1113(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113")));
            ind.setQ1113Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113Other")));
            ind.setQ1114(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1114")));
            ind.setIndvQuestionnairePConsent15_17(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnairePConsent15_17")));
            ind.setIndvQuestionnaireConsent(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnaireConsent")));
            ind.setIndvBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvBloodDraw")));
            ind.setBloodVol_1(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_1")));
            ind.setBloodVol_4(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_4")));
            ind.setBloodVol_6(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_6")));
            ind.setBloodVol_10(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_10")));
            ind.setBloodVolComment(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVolComment")));
            ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_O15_Rapid")));
            ind.setIndRapidResults(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
            ind.setIndBloodLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodLabTest")));
            ind.setIndBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodStore")));
            ind.setIndRapidDate(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidDate")));
            ind.setIndBloodSampleCollected(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodSampleCollected")));
            ind.setPrntlConsentBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodDraw")));
            ind.setPrntlConsentRHT(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentRHT")));
            ind.setPrntlConsentLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentLabTest")));
            ind.setPrntlConsentBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodStore")));
            ind.setPrntlParentID(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlParentID")));
            ind.setPrntlConsentDate(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentDate")));
            //ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
            ind.setVISIT1(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT1")));
            ind.setVISIT2(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT2")));
            ind.setVISIT3(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT3")));
            ind.setDATE1(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE1")));
            ind.setDATE2(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE2")));
            ind.setDATE3(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE3")));
            ind.setSync(cursor2.getString(cursor2.getColumnIndexOrThrow("Sync")));


        }


        return ind;
    }


    /**
     * UPDATE HOUSE HOLD INTERVIEW STATUSES
     */
    public boolean updateHHStatus(HouseHold houseHold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        //contentValues.put("UserId", UserId);
        hhValues.put("Interview_Status", houseHold.getInterview_Status());
        hhValues.put("DWELLING_NO", houseHold.getDWELLING_NO());
        hhValues.put("HH_NO", houseHold.getHH_NO());


        if ((houseHold.getDATE1() != null && houseHold.getDATE2() == null && houseHold.getDATE3() == null)) {

            Log.d("Visit 1: ", houseHold.getDATE1() + "--- " + houseHold.getVISIT1_RESULT() + "---" + houseHold.getVISIT1_RESULT());

            //Visit 1
            hhValues.put("DATE1", houseHold.getDATE1());
            hhValues.put("VISIT1_RESULT", houseHold.getVISIT1_RESULT());
            if (houseHold.getVISIT1_RESULT().equals("3") ||
                    houseHold.getVISIT1_RESULT().equals("4") ||
                    houseHold.getVISIT1_RESULT().equals("4") ||
                    houseHold.getVISIT1_RESULT().equals("5")) {
                hhValues.put("COMMENT1", houseHold.getCOMMENT1());
            }
            if (houseHold.getVISIT1_RESULT().equals("6")) {
                hhValues.put("COMMENT1", houseHold.getVisit1Other());
            }

            hhValues.put("NEXT_VISIT_2_DATE", houseHold.getNEXT_VISIT_2_DATE());
            hhValues.put("NEXT_VISIT_2_TIME", houseHold.getNEXT_VISIT_2_TIME());
        } else if ((houseHold.getDATE1() != null && houseHold.getDATE2() != null && houseHold.getDATE3() == null)) {
            //Visit 2

            Log.d("Visit 2: ", houseHold.getDATE2() + "--- " + houseHold.getVISIT2_RESULT() + "---" + houseHold.getVISIT2_RESULT());

            hhValues.put("DATE2", houseHold.getDATE2());
            hhValues.put("VISIT2_RESULT", houseHold.getVISIT2_RESULT());
            if (houseHold.getVISIT2_RESULT().equals("3") ||
                    houseHold.getVISIT2_RESULT().equals("4") ||
                    houseHold.getVISIT2_RESULT().equals("4") ||
                    houseHold.getVISIT2_RESULT().equals("5")) {
                hhValues.put("COMMENT2", houseHold.getCOMMENT2());
            }
            if (houseHold.getVISIT2_RESULT().equals("6")) {
                hhValues.put("COMMENT2", houseHold.getVisit2Other());
            }

            hhValues.put("NEXT_VISIT_3_DATE", houseHold.getNEXT_VISIT_3_DATE());
            hhValues.put("NEXT_VISIT_3_TIME", houseHold.getNEXT_VISIT_3_TIME());

        } else if (houseHold.getDATE1() != null && houseHold.getDATE2() != null && houseHold.getDATE3() != null) {
            //VISIT 3

            Log.d("Visit 3: ", houseHold.getDATE3() + "--- " + houseHold.getVISIT3_RESULT() + "---" + houseHold.getVISIT3_RESULT());

            hhValues.put("DATE3", houseHold.getDATE3());
            hhValues.put("VISIT3_RESULT", houseHold.getVISIT3_RESULT());

            if (houseHold.getVISIT3_RESULT().equals("3") ||
                    houseHold.getVISIT3_RESULT().equals("4") ||
                    houseHold.getVISIT3_RESULT().equals("4") ||
                    houseHold.getVISIT3_RESULT().equals("5")) {
                hhValues.put("COMMENT_3", houseHold.getCOMMENT3());
            }
            if (houseHold.getVISIT3_RESULT().equals("6")) {
                hhValues.put("COMMENT_3", houseHold.getVisit3Other());
            }
        } else {


            Log.d("Visit 1", String.valueOf((houseHold.getDATE1().equals(""))));
            Log.d("Visit 2", String.valueOf((houseHold.getDATE2().equals(""))));
            Log.d("Visit 3", String.valueOf((houseHold.getDATE3().equals(""))));


        }


        int i = db.update
                (tblhousehold, // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                        new String[]{String.valueOf(houseHold.getAssignment_ID()), String.valueOf(houseHold.getBatchNumber())}
                );

        db.close();

        return true;
    }

    //SEARCH FOR HOUSEHOLD
    public HouseHold searchHouse(SQLiteDatabase db, String assignment_ID, String batchnumber) {
        HouseHold h = null;
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from House_Hold_Assignments where EA_Assignment_ID = " + assignment_ID + " and BatchNumber=" + batchnumber, null);

        while (cursor.moveToNext()) {
            h = new HouseHold();
            h.setAssignment_ID(cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID")));
            h.setBatchNumber(cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber")));
        }

        return h;
    }


    //UPDATE HOUSEHOLD COLUMNS EACH
    public void updateHousehold(SQLiteDatabase db, String assignment_ID, String batchnumber, String columnName, String value) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        hhValues.put(columnName, value);

        int i = db1.update
                (tblhousehold, // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                        new String[]{String.valueOf(assignment_ID), String.valueOf(batchnumber)}
                );

        db1.close();

    }


    //UPDATE HOUSEHOLD COLUMNS EACH
    public void updateRoster(HouseHold thisHouse, String columnName, String value, String srno) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        hhValues.put(columnName, value);

        int i = db1.update
                (tblhhroster, // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                        new String[]{String.valueOf(thisHouse.getAssignment_ID()), String.valueOf(thisHouse.getBatchNumber()), srno}
                );

        db1.close();

    }


    //UPDATE HOUSEHOLD SECTION
    public void updateHHEnd(SQLiteDatabase db, HouseHold houseHold) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();


        //String HH_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID"));
        //String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
        //hhValues.put("DWELLING_NO", houseHold.getDWELLING_NO());
        //hhValues.put("HH_NO", houseHold.getHH_NO());
        //String ENUMERATOR = cursor.getString(cursor.getColumnIndexOrThrow("ENUMERATOR"));
        //String SUPERVISOR = cursor.getString(cursor.getColumnIndexOrThrow("SUPERVISOR"));
        // String QUALITY_CONTROLLER = cursor.getString(cursor.getColumnIndexOrThrow("QUALITY_CONTROLLER"));
        hhValues.put("INTERVIEWER_VISITS1", houseHold.getINTERVIEWER_VISIT1());
        hhValues.put("DATE1", houseHold.getDATE1());
        hhValues.put("VISIT1_RESULT", houseHold.getVISIT1_RESULT());
        hhValues.put("COMMENT1", houseHold.getCOMMENT1());
        hhValues.put("NEXT_VISIT_2_DATE", houseHold.getNEXT_VISIT_2_DATE());
        hhValues.put("NEXT_VISIT_2_Time", houseHold.getNEXT_VISIT_2_TIME());
        hhValues.put("INTERVIEWER_VISITS2", houseHold.getINTERVIEWER_VISIT2());
        hhValues.put("DATE2", houseHold.getDATE2());
        hhValues.put("VISIT2_RESULT", houseHold.getVISIT2_RESULT());
        hhValues.put("COMMENT2", houseHold.getCOMMENT2());
        hhValues.put("NEXT_VISIT_3_DATE", houseHold.getNEXT_VISIT_3_DATE());
        hhValues.put("NEXT_VISIT_3_Time", houseHold.getNEXT_VISIT_3_TIME());
        hhValues.put("INTERVIEWER_VISITS3", houseHold.getINTERVIEWER_VISIT3());
        hhValues.put("DATE3", houseHold.getDATE3());
        hhValues.put("VISIT3_RESULT", houseHold.getVISIT3_RESULT());
        hhValues.put("COMMENT_3", houseHold.getCOMMENT3());
        hhValues.put("TOTAL_VISITS", houseHold.getTOTAL_VISITS());

        hhValues.put("CONSENT", houseHold.getCONSENT());
        //hhValues.put("CHECKED_BY",houseHold.get);
        //hhValues.put("CODED",houseHold);
        hhValues.put("FINAL_RESULT", houseHold.getFINAL_RESULT());
        hhValues.put("FINAL_OTHER", houseHold.getFINAL_OTHER());
        hhValues.put("H01", houseHold.getH01());
        hhValues.put("Interview_Status", houseHold.getInterview_Status());
        hhValues.put("H02", houseHold.getH02());
        hhValues.put("H03", houseHold.getH03());
        hhValues.put("H03Other", houseHold.getH03Other());
        hhValues.put("H04", houseHold.getH04());
        hhValues.put("H04Other", houseHold.getH04Other());
        hhValues.put("H05", houseHold.getH05());
        hhValues.put("H05Other", houseHold.getH05Other());
        hhValues.put("H06", houseHold.getH06());
        hhValues.put("H07", houseHold.getH07());
        hhValues.put("H08", houseHold.getH08());
        hhValues.put("H08Other", houseHold.getH08Other());
        hhValues.put("H09", houseHold.getH09());
        hhValues.put("H09Other", houseHold.getH09Other());
        hhValues.put("H10", houseHold.getH10());
        hhValues.put("H11", houseHold.getH11());
        hhValues.put("H11Other", houseHold.getH11Other());

        hhValues.put("H12Radio", houseHold.getH12());
        hhValues.put("H12TV", houseHold.getH12TV());
        hhValues.put("H12Telephone", houseHold.getH12Telephone());
        hhValues.put("H12CellPhone", houseHold.getH12CellPhone());
        hhValues.put("H12PrintMedia", houseHold.getH12PrintMedia());
        hhValues.put("H12ElecMedia", houseHold.getH12ElecMedia());
        hhValues.put("H12PerformArts", houseHold.getH12PerfomArts());

        hhValues.put("H13Vehicle", houseHold.getH13());
        hhValues.put("H13Tractor", houseHold.getH13Tractor());
        hhValues.put("H13Motorcycle", houseHold.getH13Motorcycle());
        hhValues.put("H13Bicycle", houseHold.getH13Bicycle());
        hhValues.put("H13DonkeyCart", houseHold.getH13DonkeyCart());
        hhValues.put("H13DonkeyHorse", houseHold.getH13DonkeyHorse());
        hhValues.put("H13Camels", houseHold.getH13Camels());

        int i = db1.update
                (tblhousehold, // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                        new String[]{String.valueOf(houseHold.getAssignment_ID()), String.valueOf(houseHold.getBatchNumber())}
                );


        db1.close();
    }



    //UPDATE HOUSEHOLD COLUMNS EACH
    public void updateHouseholdAllColumns(SQLiteDatabase db, HouseHold houseHold) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();


        //String HH_Assignment_ID = cursor.getString(cursor.getColumnIndexOrThrow("EA_Assignment_ID"));
        //String BatchNumber = cursor.getString(cursor.getColumnIndexOrThrow("BatchNumber"));
        //hhValues.put("DWELLING_NO", houseHold.getDWELLING_NO());
        //hhValues.put("HH_NO", houseHold.getHH_NO());
        //String ENUMERATOR = cursor.getString(cursor.getColumnIndexOrThrow("ENUMERATOR"));
        //String SUPERVISOR = cursor.getString(cursor.getColumnIndexOrThrow("SUPERVISOR"));
        // String QUALITY_CONTROLLER = cursor.getString(cursor.getColumnIndexOrThrow("QUALITY_CONTROLLER"));
        hhValues.put("INTERVIEWER_VISITS1", houseHold.getINTERVIEWER_VISIT1());
        hhValues.put("DATE1", houseHold.getDATE1());
        hhValues.put("VISIT1_RESULT", houseHold.getVISIT1_RESULT());
        hhValues.put("COMMENT1", houseHold.getCOMMENT1());
        hhValues.put("NEXT_VISIT_2_DATE", houseHold.getNEXT_VISIT_2_DATE());
        hhValues.put("NEXT_VISIT_2_Time", houseHold.getNEXT_VISIT_2_TIME());
        hhValues.put("INTERVIEWER_VISITS2", houseHold.getINTERVIEWER_VISIT2());
        hhValues.put("DATE2", houseHold.getDATE2());
        hhValues.put("VISIT2_RESULT", houseHold.getVISIT2_RESULT());
        hhValues.put("COMMENT2", houseHold.getCOMMENT2());
        hhValues.put("NEXT_VISIT_3_DATE", houseHold.getNEXT_VISIT_3_DATE());
        hhValues.put("NEXT_VISIT_3_Time", houseHold.getNEXT_VISIT_3_TIME());
        hhValues.put("INTERVIEWER_VISITS3", houseHold.getINTERVIEWER_VISIT3());
        hhValues.put("DATE3", houseHold.getDATE3());
        hhValues.put("VISIT3_RESULT", houseHold.getVISIT3_RESULT());
        hhValues.put("COMMENT_3", houseHold.getCOMMENT3());
        hhValues.put("TOTAL_VISITS", houseHold.getTOTAL_VISITS());

        hhValues.put("CONSENT", houseHold.getCONSENT());
        //hhValues.put("CHECKED_BY",houseHold.get);
        //hhValues.put("CODED",houseHold);
        hhValues.put("FINAL_RESULT", houseHold.getFINAL_RESULT());
        hhValues.put("FINAL_OTHER", houseHold.getFINAL_OTHER());
        hhValues.put("H01", houseHold.getH01());
        hhValues.put("Interview_Status", houseHold.getInterview_Status());
        hhValues.put("H02", houseHold.getH02());
        hhValues.put("H03", houseHold.getH03());
        hhValues.put("H03Other", houseHold.getH03Other());
        hhValues.put("H04", houseHold.getH04());
        hhValues.put("H04Other", houseHold.getH04Other());
        hhValues.put("H05", houseHold.getH05());
        hhValues.put("H05Other", houseHold.getH05Other());
        hhValues.put("H06", houseHold.getH06());
        hhValues.put("H07", houseHold.getH07());
        hhValues.put("H08", houseHold.getH08());
        hhValues.put("H08Other", houseHold.getH08Other());
        hhValues.put("H09", houseHold.getH09());
        hhValues.put("H09Other", houseHold.getH09Other());
        hhValues.put("H10", houseHold.getH10());
        hhValues.put("H11", houseHold.getH11());
        hhValues.put("H11Other", houseHold.getH11Other());

        hhValues.put("H12Radio", houseHold.getH12());
        hhValues.put("H12TV", houseHold.getH12TV());
        hhValues.put("H12Telephone", houseHold.getH12Telephone());
        hhValues.put("H12CellPhone", houseHold.getH12CellPhone());
        hhValues.put("H12PrintMedia", houseHold.getH12PrintMedia());
        hhValues.put("H12ElecMedia", houseHold.getH12ElecMedia());
        hhValues.put("H12PerformArts", houseHold.getH12PerfomArts());

        hhValues.put("H13Vehicle", houseHold.getH13());
        hhValues.put("H13Tractor", houseHold.getH13Tractor());
        hhValues.put("H13Motorcycle", houseHold.getH13Motorcycle());
        hhValues.put("H13Bicycle", houseHold.getH13Bicycle());
        hhValues.put("H13DonkeyCart", houseHold.getH13DonkeyCart());
        hhValues.put("H13DonkeyHorse", houseHold.getH13DonkeyHorse());
        hhValues.put("H13Camels", houseHold.getH13Camels());

        int i = db1.update
                (tblhousehold, // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                        new String[]{String.valueOf(houseHold.getAssignment_ID()), String.valueOf(houseHold.getBatchNumber())}
                );

        //db1.close();

        if (houseHold.getPersons() != null) {

            for (int k = 0; k < houseHold.getPersons().length; k++) {
                if(houseHold.getPersons()[k] !=null) {
                    if(houseHold.getPersons()[k].getP01()!=null) {
                        if(houseHold.getPersons()[k].getP01().length()>0) {
                            hhValues = new ContentValues();
                            hhValues.put("EA_Assignment_ID", houseHold.getAssignment_ID());
                            hhValues.put("BatchNumber", houseHold.getBatchNumber());
                            //Log.d("From update Roster", houseHold.getPersons()[k].getP01());
                            hhValues.put("SRNO", houseHold.getPersons()[k].getSRNO());
                            hhValues.put("P01", houseHold.getPersons()[k].getP01());
                            hhValues.put("P02", houseHold.getPersons()[k].getP02());
                            hhValues.put("P03", houseHold.getPersons()[k].getP03());
                            hhValues.put("P04_YY", houseHold.getPersons()[k].getP04YY());
                            hhValues.put("P04_MM", houseHold.getPersons()[k].getP04MM());
                            hhValues.put("P04_WKS", houseHold.getPersons()[k].getP04WKS());
                            hhValues.put("P06", houseHold.getPersons()[k].getP06());
                            hhValues.put("P07", houseHold.getPersons()[k].getP07());
                            hhValues.put("P17", houseHold.getPersons()[k].getP17());
                            hhValues.put("P18", houseHold.getPersons()[k].getP18());
                            hhValues.put("P19", houseHold.getPersons()[k].getP19());
                            hhValues.put("P20", houseHold.getPersons()[k].getP20());
                            hhValues.put("P21", houseHold.getPersons()[k].getP21());

                            //hhValues.put("B3_RapidConsent_Yes_No", houseHold.getPersons()[k].getB3_RapidConsent_Yes_No());
                            // hhValues.put("B3_Guardian", houseHold.getPersons()[k].getB3_Guardian());
                            //hhValues.put("B3_Date", houseHold.getPersons()[k].getB3_Date());
                            //hhValues.put("U15Rapid_Result", houseHold.getPersons()[k].getU15Rapid_Results());
                            // hhValues.put("Rapid_Comment", houseHold.getPersons()[k].getRapid_Comment());
                            hhValues.put("BarCodeHHR", houseHold.getPersons()[k].getBarcode());
                            hhValues.put("P08", houseHold.getPersons()[k].getP08());
                            hhValues.put("P10", houseHold.getPersons()[k].getP10());
                            hhValues.put("P11", houseHold.getPersons()[k].getP11());
                            hhValues.put("P12", houseHold.getPersons()[k].getP12());
                            hhValues.put("P13", houseHold.getPersons()[k].getP13());
                            hhValues.put("P13Other", houseHold.getPersons()[k].getP13Other());
                            hhValues.put("P14", houseHold.getPersons()[k].getP14());
                            hhValues.put("P15", houseHold.getPersons()[k].getP15());
                            hhValues.put("P16", houseHold.getPersons()[k].getP16());

                            hhValues.put("BloodDraw", houseHold.getPersons()[k].getBloodDraw());
                            hhValues.put("BloodVolume_1", houseHold.getPersons()[k].getBloodVolume_1());
                            hhValues.put("BloodVolume_4", houseHold.getPersons()[k].getBloodVolume_4());
                            hhValues.put("BloodVolume_6", houseHold.getPersons()[k].getBloodVolume_6());
                            hhValues.put("BloodVolume_10", houseHold.getPersons()[k].getBloodVolume_10());
                            hhValues.put("BloodVolumeComment", houseHold.getPersons()[k].getBloodVolumeComment());
                            hhValues.put("Rapid", houseHold.getPersons()[k].getRapid());
                            hhValues.put("RapidResults", houseHold.getPersons()[k].getRapidResults());
                            hhValues.put("BloodLabTest", houseHold.getPersons()[k].getBloodLabTest());
                            hhValues.put("BloodStore", houseHold.getPersons()[k].getBloodStore());
                            hhValues.put("RapidDate", houseHold.getPersons()[k].getRapidDate());
                            hhValues.put("BloodSampleCollected", houseHold.getPersons()[k].getBloodSampleCollected());
                            hhValues.put("ChPrntlConsentBloodDraw", houseHold.getPersons()[k].getChPrntlConsentBloodDraw());
                            hhValues.put("ChPrntlConsentRHT", houseHold.getPersons()[k].getChPrntlConsentRHT());
                            hhValues.put("ChPrntlConsentLabTest", houseHold.getPersons()[k].getChPrntlConsentLabTest());
                            hhValues.put("ChPrntlConsentBloodStore", houseHold.getPersons()[k].getChPrntlConsentBloodStore());
                            hhValues.put("B3_Guardian", houseHold.getPersons()[k].getB3_Guardian());
                            hhValues.put("ChPrntlConsentDate", houseHold.getPersons()[k].getChPrntlConsentDate());

                            hhValues.put("PrntlConsentX_Ray", houseHold.getPersons()[k].getPrntlConsentX_Ray());
                            hhValues.put("PrntlConsentX_RayReview", houseHold.getPersons()[k].getPrntlConsentX_RayReview());
                            hhValues.put("PrntlConsentX_RayStore", houseHold.getPersons()[k].getPrntlConsentX_RayStore());
                            hhValues.put("PrntlConsentSP_Collect", houseHold.getPersons()[k].getPrntlConsentSP_Collect());
                            hhValues.put("PrntlParentSP_AddTests", houseHold.getPersons()[k].getPrntlParentSP_AddTests());
                            hhValues.put("PrntlConsentSP_LabTest", houseHold.getPersons()[k].getPrntlConsentSP_LabTest());
                            hhValues.put("PrntlConsentTBDate", houseHold.getPersons()[k].getPrntlConsentTBDate());

                            hhValues.put("IndTB_X_Ray", houseHold.getPersons()[k].getIndTB_X_Ray());
                            hhValues.put("IndTB_X_RayReview", houseHold.getPersons()[k].getIndTB_X_RayReview());
                            hhValues.put("IndTB_X_RayStore", houseHold.getPersons()[k].getIndTB_X_RayStore());
                            hhValues.put("IndSP_Collect", houseHold.getPersons()[k].getIndSP_Collect());
                            hhValues.put("IndSP_AddTests", houseHold.getPersons()[k].getIndSP_AddTests());
                            hhValues.put("IndSP_LabTests", houseHold.getPersons()[k].getIndSP_LabTests());
                            hhValues.put("IndTB_ConsentDate", houseHold.getPersons()[k].getIndTB_ConsentDate());


                            int ii = db1.update
                                    (tblhhroster, // table
                                            hhValues, // column/value
                                            "EA_Assignment_ID = ? and BatchNumber = ? and SRNO =?", // selections
                                            new String[]{String.valueOf(houseHold.getAssignment_ID()), String.valueOf(houseHold.getBatchNumber()), String.valueOf(houseHold.getPersons()[k].getSRNO())}
                                    );

                            if (ii > 0) {
                                //Log.d("HH Roster", "Person roster updated");
                            }
                        }
                    }
                }
            }
        }
        db1.close();

    }

    //UPDATE INDIVIDUAL********************************************************************************************************************
    public void updateIndividual(SQLiteDatabase db, Individual ind) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        hhValues.put("SRNO", ind.getSRNO());

        hhValues.put("IndBarcode", ind.getIndBarcode());
        hhValues.put("Q101", ind.getQ101());
        hhValues.put("Q102", ind.getQ102());
        hhValues.put("Q103", ind.getQ103());
        hhValues.put("Q104", ind.getQ104());
        hhValues.put("Q104a", ind.getQ104a());
        hhValues.put("Q104b", ind.getQ104b());
        hhValues.put("Q104c", ind.getQ104c());


        hhValues.put("Q105", ind.getQ105());
        hhValues.put("Q105Other", ind.getQ105Other());
        hhValues.put("Q105a", ind.getQ105a());

        hhValues.put("Q105b", ind.getQ105b());

        hhValues.put("Q106", ind.getQ106());
        hhValues.put("Q106a", ind.getQ106a());
        hhValues.put("Q106aOther", ind.getQ106aOther());

        hhValues.put("Q106b", ind.getQ106b());
        hhValues.put("Q106c", ind.getQ106c());

        hhValues.put("Q106d", ind.getQ106d());

        hhValues.put("Q107", ind.getQ107());

        String yy = "00";
        String mm = "00";

        if (ind.getQ107aYY() != null) {
            if (ind.getQ107aYY().length() == 1) {
                yy = "0" + ind.getQ107aYY();
            } else {
                yy = ind.getQ107aYY();
            }

        }
        if (ind.getQ107aMnth() != null) {
            if (ind.getQ107aMnth().length() == 1) {
                mm = "0" + ind.getQ107aMnth();
            } else {
                mm = ind.getQ107aMnth();
            }

        }
        hhValues.put("Q107a", yy + mm);

        hhValues.put("Q107b", ind.getQ107b());


        hhValues.put("Q107bOther", ind.getQ107bOther());
        hhValues.put("Q107c", ind.getQ107c());
        hhValues.put("Q107cOther", ind.getQ107cOther());

        hhValues.put("Q201", ind.getQ201());
        hhValues.put("Q202", ind.getQ202());
        hhValues.put("Q203", ind.getQ203());
        hhValues.put("Q204", ind.getQ204());
        hhValues.put("Q205", ind.getQ205());
        hhValues.put("Q205a", ind.getQ205a());


        hhValues.put("Q301", ind.getQ301());
        hhValues.put("Q301a", ind.getQ301a());

        hhValues.put("Q302", ind.getQ302());

        hhValues.put("Q303", ind.getQ303());
        hhValues.put("Q303a", ind.getQ303a());

        hhValues.put("Q304", ind.getQ304());
        hhValues.put("Q304a", ind.getQ304a());

        hhValues.put("Q305Smoking", ind.getQ305_1());
        hhValues.put("Q305Sniffing", ind.getQ305_2());
        hhValues.put("Q305Chewing", ind.getQ305_3());
        hhValues.put("Q305None", ind.getQ305_4());

        hhValues.put("Q306", ind.getQ306());
        hhValues.put("Q307", ind.getQ307());

        hhValues.put("Q401", ind.getQ401());
        hhValues.put("Q402", ind.getQ402());
        hhValues.put("Q402a", ind.getQ402a());
        hhValues.put("Q402b", ind.getQ402b());
        hhValues.put("Q403", ind.getQ403());


        hhValues.put("Q404Vaginal", ind.getQ404_1());
        hhValues.put("Q404Anal", ind.getQ404_2());
        hhValues.put("Q404Oral", ind.getQ404_3());

        hhValues.put("Q404a", ind.getQ404a());

        hhValues.put("Q405", ind.getQ405());
        hhValues.put("Q406", ind.getQ406());
        hhValues.put("Q407", ind.getQ407());

        hhValues.put("Q408", ind.getQ408());
        hhValues.put("Q408a", ind.getQ408a());

        hhValues.put("Q410Slapped", ind.getQ410Slapped());
        hhValues.put("Q410Pushed", ind.getQ410Pushed());
        hhValues.put("Q410Chocked", ind.getQ410Choked());
        hhValues.put("Q410Threatened", ind.getQ410Threatened());
        hhValues.put("Q410Physical", ind.getQ410Physical());
        hhValues.put("Q410Forced", ind.getQ410Forced());
        hhValues.put("Q410MadeAfraid", ind.getQ410MadeAfraid());

        hhValues.put("Q501", ind.getQ501());
        hhValues.put("Q502", ind.getQ502());
        hhValues.put("Q503", ind.getQ503());

        hhValues.put("Q504_Pain", ind.getQ504_1());
        hhValues.put("Q504_Reduced", ind.getQ504_2());
        hhValues.put("Q504_Fear", ind.getQ504_3());
        hhValues.put("Q504_Culture", ind.getQ504_4());
        hhValues.put("Q504_Religion", ind.getQ504_5());
        hhValues.put("Q504_Spouse", ind.getQ504_6());
        hhValues.put("Q504_Parental", ind.getQ504_7());
        hhValues.put("Q504_Long", ind.getQ504_8());
        hhValues.put("Q504_FearHIV", ind.getQ504_10());

        hhValues.put("Q504_Other", ind.getQ504_Other());

        hhValues.put("Q601", ind.getQ601());
        hhValues.put("Q601a", ind.getQ601a());

        hhValues.put("Q602Youth", ind.getQ602_1());
        hhValues.put("Q602TV", ind.getQ602_2());
        hhValues.put("Q602Radio", ind.getQ602_3());
        hhValues.put("Q602NewsPaper", ind.getQ602_4());
        hhValues.put("Q602Hospital", ind.getQ602_5());
        hhValues.put("Q602Posters", ind.getQ602_6());
        hhValues.put("Q602Traditional", ind.getQ602_7());
        hhValues.put("Q602Workshop", ind.getQ602_8());
        hhValues.put("Q602Individual", ind.getQ602_10());
        hhValues.put("Q602Church", ind.getQ602_11());
        hhValues.put("Q602Kgotla", ind.getQ602_12());
        hhValues.put("Q602WorkPlace", ind.getQ602_13());
        hhValues.put("Q602Peer", ind.getQ602_14());
        hhValues.put("Q602School", ind.getQ602_15());
        hhValues.put("Q602_Other", ind.getQ602_Other());

        hhValues.put("Q603Condom", ind.getQ603_1());
        hhValues.put("Q603FewerP", ind.getQ603_2());
        hhValues.put("Q603Both", ind.getQ603_3());
        hhValues.put("Q603NoCasual", ind.getQ603_4());
        hhValues.put("Q603Abstain", ind.getQ603_5());
        hhValues.put("Q603NoCommercial", ind.getQ603_6());
        hhValues.put("Q603Injection", ind.getQ603_7());
        hhValues.put("Q603Blood", ind.getQ603_8());
        hhValues.put("Q603DontKnow", ind.getQ603_9());
        hhValues.put("Q603Other", ind.getQ603_Other());


        hhValues.put("Q604", ind.getQ604());
        hhValues.put("Q604a", ind.getQ604a());
        hhValues.put("Q604bYouth", ind.getQ604b_1());
        hhValues.put("Q604bTV", ind.getQ604b_2());
        hhValues.put("Q604bRadio", ind.getQ604b_3());
        hhValues.put("Q604bNewsPaper", ind.getQ604b_4());
        hhValues.put("Q604bHospital", ind.getQ604b_5());
        hhValues.put("Q604bPoster", ind.getQ604b_6());
        hhValues.put("Q604bTraditional", ind.getQ604b_7());
        hhValues.put("Q604bWorkshop", ind.getQ604b_8());
        hhValues.put("Q604bIndividual", ind.getQ604b_10());
        hhValues.put("Q604bChurch", ind.getQ604b_11());
        hhValues.put("Q604bKgotla", ind.getQ604b_12());
        hhValues.put("Q604bWorkPlace", ind.getQ604b_13());
        hhValues.put("Q604bPeer", ind.getQ604b_14());
        hhValues.put("Q604bSchool", ind.getQ604b_15());
        hhValues.put("Q604bOther", ind.getQ604b_Other());


        hhValues.put("Q605Windows", ind.getQ605_1());
        hhValues.put("Q605Mouth", ind.getQ605_2());
        hhValues.put("Q605Hands", ind.getQ605_3());
        hhValues.put("Q605Nutrition", ind.getQ605_4());
        hhValues.put("Q605Praying", ind.getQ605_5());
        hhValues.put("Q605DontKnow", ind.getQ605_9());
        hhValues.put("Q605Other", ind.getQ605_Other());


        hhValues.put("Q606", ind.getQ606());
        hhValues.put("Q607", ind.getQ607());
        hhValues.put("Q608", ind.getQ608());
        hhValues.put("Q609", ind.getQ609());
        hhValues.put("Q610", ind.getQ610());

        hhValues.put("Q611a", ind.getQ611a());
        hhValues.put("Q611b", ind.getQ611b());
        hhValues.put("Q611c", ind.getQ611c());

        hhValues.put("Q612", ind.getQ612());
        hhValues.put("Q612a", ind.getQ612a());
        hhValues.put("Q612Other", ind.getQ612aOther());

        hhValues.put("Q613", ind.getQ613());
        hhValues.put("Q613a", ind.getQ613a());
        hhValues.put("Q613aOther", ind.getQ613aOther());
        hhValues.put("Q614", ind.getQ614());
        hhValues.put("Q614Other", ind.getQ614Other());
        hhValues.put("Q615", ind.getQ615());

        hhValues.put("Q616Anybody", ind.getQ616_1());
        hhValues.put("Q616Poor", ind.getQ616_2());
        hhValues.put("Q616Homeless", ind.getQ616_3());
        hhValues.put("Q616Alcoholics", ind.getQ616_4());
        hhValues.put("Q616Drugs", ind.getQ616_5());
        hhValues.put("Q616PeopHIV", ind.getQ616_6());
        hhValues.put("Q616PeopPrison", ind.getQ616_7());
        hhValues.put("Q616Smokers", ind.getQ616_8());
        hhValues.put("Q616DntKnow", ind.getQ616_9());
        hhValues.put("Q616Other", ind.getQ616_10());


        hhValues.put("Q617Meal", ind.getQ617a());
        hhValues.put("Q617Clothes", ind.getQ617b());
        hhValues.put("Q617Miscarried", ind.getQ617c());
        hhValues.put("Q617Widow", ind.getQ617d());
        hhValues.put("Q617FamilyHIV", ind.getQ617e());
        hhValues.put("Q617Sejeso", ind.getQ617f());
        hhValues.put("Q617Touching", ind.getQ617g());
        hhValues.put("Q617Someone", ind.getQ617h());
        hhValues.put("Q617Other", ind.getQ617_0ther());
        hhValues.put("Q618", ind.getQ618());

        hhValues.put("Q619Rash", ind.getQ619_1());
        hhValues.put("Q619Cough", ind.getQ619_2());
        hhValues.put("Q619LongCough", ind.getQ619_3());
        hhValues.put("Q619Blood", ind.getQ619_4());
        hhValues.put("Q619Headache", ind.getQ619_5());
        hhValues.put("Q619Nausea", ind.getQ619_6());
        hhValues.put("Q619Weight", ind.getQ619_7());
        hhValues.put("Q619Fever", ind.getQ619_8());
        hhValues.put("Q619Fever7Days", ind.getQ619_10());
        hhValues.put("Q619ChestPain", ind.getQ619_11());
        hhValues.put("Q619Breath", ind.getQ619_12());
        hhValues.put("Q619Fatigue", ind.getQ619_13());
        hhValues.put("Q619Sweats", ind.getQ619_14());
        hhValues.put("Q619DontKnow", ind.getQ619_9());
        hhValues.put("Q619Other", ind.getQ619_Other());

        hhValues.put("Q620", ind.getQ620());
        hhValues.put("Q620Other", ind.getQ620_Other());

        hhValues.put("Q621", ind.getQ621());
        hhValues.put("Q621aSpouse", ind.getQ621a_1());
        hhValues.put("Q621aPartner", ind.getQ621a_2());
        hhValues.put("Q621aFriend", ind.getQ621a_3());
        hhValues.put("Q621aFamily", ind.getQ621a_4());
        hhValues.put("Q621aRelative", ind.getQ621a_5());
        hhValues.put("Q621aHCWorker", ind.getQ621a_6());
        hhValues.put("Q621aCoWorker", ind.getQ621a_7());
        hhValues.put("Q621aOther", ind.getQ621a_Other());
        hhValues.put("Q621b", ind.getQ621b());

        hhValues.put("Q621bOther", ind.getQ621bOther());

        hhValues.put("Q622", ind.getQ622());

        hhValues.put("Q622a", ind.getQ622a());
        hhValues.put("Q622aOther", ind.getQ622aOther());

        hhValues.put("Q622b", ind.getQ622b());
        hhValues.put("Q622bOther", ind.getQ622bOther());


        hhValues.put("Q623", ind.getQ623());
        hhValues.put("Q624", ind.getQ624());
        hhValues.put("Q625", ind.getQ625());
        hhValues.put("Q701", ind.getQ701());
        hhValues.put("Q702", ind.getQ702());
        hhValues.put("Q703", ind.getQ703());
        hhValues.put("Q704", ind.getQ704());
        hhValues.put("Q705", ind.getQ705());
        hhValues.put("Q801", ind.getQ801());


        hhValues.put("Q801a", ind.getQ801a());
        hhValues.put("Q801b", ind.getQ801b());

        hhValues.put("Q801c", ind.getQ801cMonth() + ind.getQ801cYear());


        hhValues.put("Q801d", ind.getQ801d());
        hhValues.put("Q801dOther", ind.getQ801dOther());

        hhValues.put("Q801e", ind.getQ801e());
        hhValues.put("Q801eOther", ind.getQ801eOther());


        hhValues.put("Q801f", ind.getQ801f());

        hhValues.put("Q802", ind.getQ802());
        hhValues.put("Q802a", ind.getQ802a());
        hhValues.put("Q802aOther", ind.getQ802aOther());


        hhValues.put("Q803", ind.getQ803());
        hhValues.put("Q803Other", ind.getQ803Other());

        hhValues.put("Q804", ind.getQ804());
        hhValues.put("Q804Other", ind.getQ804Other());

        hhValues.put("Q901", ind.getQ901());
        hhValues.put("Q901a", ind.getQ901a());
        hhValues.put("Q901aOther", ind.getQ901aOther());

        String Month = "00";
        String Year = "0000";
        if (ind.getQ902Month() != null) {
            Month = ind.getQ902Month();
        }
        if (ind.getQ902Year() != null) {
            Year = ind.getQ902Year();
        }
        hhValues.put("Q902", Month + Year);

        hhValues.put("Q903DenyCare", ind.getQ903a());
        hhValues.put("Q903Gossip", ind.getQ903b());
        hhValues.put("Q903NoSex", ind.getQ903c());
        hhValues.put("Q903VerbalAbuse", ind.getQ903d());
        hhValues.put("Q903PhysicalAbuse", ind.getQ903e());
        hhValues.put("Q903NoContact", ind.getQ903f());
        hhValues.put("Q903SharingStatus", ind.getQ903g());


        hhValues.put("Q904", ind.getQ904());
        hhValues.put("Q904a", ind.getQ904a());
        hhValues.put("Q904aOther", ind.getQ904aOther());

        String s = ind.getQ904bMM();
        String s2 = ind.getQ904bYYYY();

        if (s == null || s.equals("null")) {
            s = "00";
        }

        if (s2 == null || s2.equals("null")) {
            s2 = "0000";
        }

        hhValues.put("Q904b", s + s2);


        hhValues.put("Q904c", ind.getQ904c());
        hhValues.put("Q904cOther", ind.getQ904cOther());


        hhValues.put("Q905", ind.getQ905());
        hhValues.put("Q905a", ind.getQ905a());
        hhValues.put("Q905aOther", ind.getQ905aOther());

        hhValues.put("Q1001", ind.getQ1001());
        hhValues.put("Q1002", ind.getQ1002());

        hhValues.put("Q1002aMCondom", ind.getQ1002a_1());
        hhValues.put("Q1002aFCondom", ind.getQ1002a_2());
        hhValues.put("Q1002aInjectContra", ind.getQ1002a_3());
        hhValues.put("Q1002aOralContra", ind.getQ1002a_4());
        hhValues.put("Q1002aUID", ind.getQ1002a_5());
        hhValues.put("Q1002aBTL", ind.getQ1002a_6());
        hhValues.put("Q1002aFSterilization", ind.getQ1002a_7());
        hhValues.put("Q1002aMSterilization", ind.getQ1002a_8());
        hhValues.put("Q1002aImplants", ind.getQ1002a_10());
        hhValues.put("Q1002aEContra", ind.getQ1002a_11());
        hhValues.put("Q1002aSafePeriod", ind.getQ1002a_12());
        hhValues.put("Q1002aLAM", ind.getQ1002a_13());
        hhValues.put("Q1002aDiagraphm", ind.getQ1002a_14());
        hhValues.put("Q1002aSpermicides", ind.getQ1002a_15());
        hhValues.put("Q1002aNatural", ind.getQ1002a_16());
        hhValues.put("Q1002aTraditional", ind.getQ1002a_17());
        hhValues.put("Q1002aSpiritual", ind.getQ1002a_18());
        hhValues.put("Q1002aOther", ind.getQ1002a_Other());

        hhValues.put("Q1002b", ind.getQ1002b());
        hhValues.put("Q1002bOther", ind.getQ1002bOther());

        hhValues.put("Q1003", ind.getQ1003());

        String tempMonth = "00";
        String tempYear = "0000";
        String tempDay = "00";

        if (ind.getQ1004_Day() != null) {
            tempDay = ind.getQ1004_Day();
        }
        if (ind.getQ1004_Month() != null) {
            tempMonth = ind.getQ1004_Month();
        }
        if (ind.getQ1004_Year() != null) {
            tempYear = ind.getQ1004_Year();
        }
        hhValues.put("Q1004", tempDay + tempMonth + tempYear);

        hhValues.put("Q1004a", ind.getQ1004a());
        hhValues.put("Q1004b", ind.getQ1004b());
        hhValues.put("Q1004bOther", ind.getQ1004bOther());


        hhValues.put("Q1005", ind.getQ1005());

        hhValues.put("Q1005a", ind.getQ1005a());
        hhValues.put("Q1006", ind.getQ1006());

        hhValues.put("Q1007", ind.getQ1007());
        hhValues.put("Q1007a", ind.getQ1007a());

        hhValues.put("Q1008", ind.getQ1008());
        hhValues.put("Q1008a", ind.getQ1008a());
        hhValues.put("Q1008aOther", ind.getQ1008aOther());


        hhValues.put("Q1009", ind.getQ1009());
        hhValues.put("Q1009a", ind.getQ1009a());

        hhValues.put("Q1010", ind.getQ1010());
        hhValues.put("Q1010Other", ind.getQ1010Other());

        hhValues.put("Q1011", ind.getQ1011());
        hhValues.put("Q1011Other", ind.getQ1011_Other());

        tempMonth = "00";
        tempYear = "0000";
        tempDay = "00";

        if (ind.getQ1012_Week() != null) {
            tempDay = ind.getQ1012_Week();
        }
        if (ind.getQ1012_Month() != null) {
            tempMonth = ind.getQ1012_Month();
        }
        if (ind.getQ1012_Year() != null) {
            tempYear = ind.getQ1012_Year();
        }
        hhValues.put("Q1012", tempDay + tempMonth + tempYear);
        hhValues.put("Q1012Still",ind.getQ1012Still());

        hhValues.put("Q1013", ind.getQ1013());
        hhValues.put("Q1014", ind.getQ1014());
        hhValues.put("Q1014a", ind.getQ1014a());
        hhValues.put("Q1014b", ind.getQ1014b());

        hhValues.put("Q1015", ind.getQ1015());
        hhValues.put("Q1015a", ind.getQ1015a());
        hhValues.put("Q1015b", ind.getQ1015b());
        hhValues.put("Q1016", ind.getQ1016());
        hhValues.put("Q1017", ind.getQ1017());


        hhValues.put("Q1101", ind.getQ1101());
        hhValues.put("Q1101a", ind.getQ1101a());
        hhValues.put("Q1101aOther", ind.getQ1101aOther());
        hhValues.put("Q1102", ind.getQ1102());
        hhValues.put("Q1102a", ind.getQ1102a());
        hhValues.put("Q1103", ind.getQ1103());

        String dd = "00";
        String wks = "00";

        if (ind.getQ1103aDD() != null) {
            dd = ind.getQ1103aDD();
        }

        if (ind.getQ1103aWks() != null) {
            wks = ind.getQ1103aWks();
        }

        hhValues.put("Q1103aDD", dd);
        hhValues.put("Q1103aWks", wks);


        hhValues.put("Q1103aDontKnow", ind.getQ1103aDontKnow());

        hhValues.put("Q1104", ind.getQ1104());
        hhValues.put("Q1105", ind.getQ1105());
        hhValues.put("Q1106", ind.getQ1106());
        hhValues.put("Q1106a", ind.getQ1106a());
        hhValues.put("Q1106b", ind.getQ1106b());
        hhValues.put("Q1106bOther", ind.getQ1106bOther());
        hhValues.put("Q1107", ind.getQ1107());

//        String ddd = "";
//        String wk = "";
//
//        if (ind.getQ1107aDD() != null) {
//            if (ind.getQ1107aDD().length() == 1) {
//                ddd = "0" + ind.getQ1107aDD();
//            }
//        }
//        if (ind.getQ1107aWks() != null) {
//            if (ind.getQ1107aWks().length() == 1) {
//                wk = "0" + ind.getQ1107aWks();
//            }
//        }
        hhValues.put("Q1107aDD", ind.getQ1107aDD());
        hhValues.put("Q1107aWks", ind.getQ1107aWks());



        hhValues.put("Q1107aDontKnow", ind.getQ1107aDontKnow());

        hhValues.put("Q1108", ind.getQ1108());

        hhValues.put("Q1108aDD", ind.getQ1108aDD());
        hhValues.put("Q1108aWks", ind.getQ1108aWks());


        hhValues.put("Q1108aDontKnow", ind.getQ1108aDontKnow());


        hhValues.put("Q1109", ind.getQ1109());
        hhValues.put("Q1110", ind.getQ1110());
        hhValues.put("Q1111", ind.getQ1111());
        hhValues.put("Q1111Other", ind.getQ1111Other());

        hhValues.put("Q1112", ind.getQ1112());
        hhValues.put("Q1112Other", ind.getQ1112_Other());

        hhValues.put("Q1113", ind.getQ1113());
        hhValues.put("Q1113Other", ind.getQ1113Other());

        hhValues.put("Q1114", ind.getQ1114());

        // hhValues.put("B8_Yes_No",ind.getB8_Yes_No());

        // hhValues.put("B8_Date",ind.getB8_Date());


        hhValues.put("IndvQuestionnairePConsent15_17", ind.getIndvQuestionnairePConsent15_17());
        hhValues.put("IndvQuestionnaireConsent", ind.getIndvQuestionnaireConsent());
        hhValues.put("IndvBloodDraw", ind.getIndvBloodDraw());
        hhValues.put("BloodVol_1", ind.getBloodVol_1());
        hhValues.put("BloodVol_4", ind.getBloodVol_4());
        hhValues.put("BloodVol_6", ind.getBloodVol_6());
        hhValues.put("BloodVol_10", ind.getBloodVol_10());
        hhValues.put("BloodVolComment", ind.getBloodVolComment());
        hhValues.put("B8_O15_Rapid", ind.getB8_O15_Rapid());
        hhValues.put("IndRapidResults", ind.getIndRapidResults());
        hhValues.put("IndBloodLabTest", ind.getIndBloodLabTest());
        hhValues.put("IndBloodStore", ind.getIndBloodStore());
        hhValues.put("IndRapidDate", ind.getIndRapidDate());
        hhValues.put("IndBloodSampleCollected", ind.getIndBloodSampleCollected());
        hhValues.put("PrntlConsentBloodDraw", ind.getPrntlConsentBloodDraw());
        hhValues.put("PrntlConsentRHT", ind.getPrntlConsentRHT());
        hhValues.put("PrntlConsentLabTest", ind.getPrntlConsentLabTest());
        hhValues.put("PrntlConsentBloodStore", ind.getPrntlConsentBloodStore());
        hhValues.put("PrntlParentID", ind.getPrntlParentID());
        hhValues.put("PrntlConsentDate", ind.getPrntlConsentDate());
        hhValues.put(VISIT1, ind.getVISIT1());
        hhValues.put(VISIT2, ind.getVISIT2());
        hhValues.put(VISIT3, ind.getVISIT3());
        hhValues.put(INDDATE1, ind.getDATE1());
        hhValues.put(INDDATE2, ind.getDATE2());
        hhValues.put(INDDATE3, ind.getDATE3());
        hhValues.put(Sync, ind.getSync());


        //hhValues.put("Q801f",ind.getQ801f());

        //  hhValues.put("Rapid_Comment",ind.getIndRapid_Comment());


        //INSERT INDIVIDUALS FROM THIS HOUSE
        int ii = db1.update
                (tblindividual, // table
                        hhValues, // column/value
                        "Assignment_ID = ? and BatchNumber = ? and SRNO = ?", // selections
                        new String[]{String.valueOf(ind.getAssignmentID()), String.valueOf(ind.getBatch()), String.valueOf(ind.getSRNO())}
                );

        if (ii > 0) {
            Log.d("Individual updated: ", "individual has been updated");
        }

        db1.close();


    }


    //Deleting data from the
    public void cleardata(SQLiteDatabase db, HouseHold thisHouse) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();

        int i = db1.delete
                (tblindividual,
                        "Assignment_ID = ? and BatchNumber = ?", // where clause
                        new String[]{String.valueOf(thisHouse.getAssignment_ID()), String.valueOf(thisHouse.getBatchNumber())}
                );

        int ii = db1.delete
                (tblhhroster,
                        "EA_Assignment_ID = ? and BatchNumber = ?", // where clause
                        new String[]{String.valueOf(thisHouse.getAssignment_ID()), String.valueOf(thisHouse.getBatchNumber())}
                );

        int iii = db1.delete
                (tblhousehold,
                        "EA_Assignment_ID = ? and BatchNumber = ?", // where clause
                        new String[]{String.valueOf(thisHouse.getAssignment_ID()), String.valueOf(thisHouse.getBatchNumber())}
                );


        db1.close();


    }


    /***
     * GET STARTED HOUSEHOLD FROM DB
     * @return
     */
    public List<HouseHold> getCompleted() {
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tblhousehold, null);

        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {

            if(cursor.getString(cursor.getColumnIndexOrThrow("Clear"))!=null){
            if ((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                    (cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("10")) || cursor.getString(cursor.getColumnIndexOrThrow("Clear")).equals("3"))) {

                dataModel = new HouseHold();
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
                dataModel.setClear(cursor.getString(cursor.getColumnIndexOrThrow("Clear")));

                dataModel.setH01(cursor.getString(cursor.getColumnIndexOrThrow("H01")));
                dataModel.setH02(cursor.getString(cursor.getColumnIndexOrThrow("H02")));
                dataModel.setH03(cursor.getString(cursor.getColumnIndexOrThrow("H03")));
                dataModel.setH03Other(cursor.getString(cursor.getColumnIndexOrThrow("H03Other")));
                dataModel.setH04(cursor.getString(cursor.getColumnIndexOrThrow("H04")));
                dataModel.setH04Other(cursor.getString(cursor.getColumnIndexOrThrow("H04Other")));
                dataModel.setH05(cursor.getString(cursor.getColumnIndexOrThrow("H05")));
                dataModel.setH05Other(cursor.getString(cursor.getColumnIndexOrThrow("H05Other")));
                dataModel.setH06(cursor.getString(cursor.getColumnIndexOrThrow("H06")));
                dataModel.setH07(cursor.getString(cursor.getColumnIndexOrThrow("H07")));
                dataModel.setH08(cursor.getString(cursor.getColumnIndexOrThrow("H08")));
                dataModel.setH08Other(cursor.getString(cursor.getColumnIndexOrThrow("H08Other")));
                dataModel.setH09(cursor.getString(cursor.getColumnIndexOrThrow("H09")));
                dataModel.setH09Other(cursor.getString(cursor.getColumnIndexOrThrow("H09Other")));
                dataModel.setH10(cursor.getString(cursor.getColumnIndexOrThrow("H10")));
                dataModel.setH11(cursor.getString(cursor.getColumnIndexOrThrow("H11")));
                dataModel.setH11Other(cursor.getString(cursor.getColumnIndexOrThrow("H11Other")));

                dataModel.setH12(cursor.getString(cursor.getColumnIndexOrThrow("H12Radio")));
                dataModel.setH12TV(cursor.getString(cursor.getColumnIndexOrThrow("H12TV")));
                dataModel.setH12Telephone(cursor.getString(cursor.getColumnIndexOrThrow("H12Telephone")));
                dataModel.setH12CellPhone(cursor.getString(cursor.getColumnIndexOrThrow("H12CellPhone")));
                dataModel.setH12PrintMedia(cursor.getString(cursor.getColumnIndexOrThrow("H12PrintMedia")));
                dataModel.setH12ElecMedia(cursor.getString(cursor.getColumnIndexOrThrow("H12ElecMedia")));
                dataModel.setH12PerfomArts(cursor.getString(cursor.getColumnIndexOrThrow("H12PerformArts")));

                dataModel.setH13(cursor.getString(cursor.getColumnIndexOrThrow("H13Vehicle")));
                dataModel.setH13Tractor(cursor.getString(cursor.getColumnIndexOrThrow("H13Tractor")));
                dataModel.setH13Motorcycle(cursor.getString(cursor.getColumnIndexOrThrow("H13Motorcycle")));
                dataModel.setH13Bicycle(cursor.getString(cursor.getColumnIndexOrThrow("H13Bicycle")));
                dataModel.setH13DonkeyCart(cursor.getString(cursor.getColumnIndexOrThrow("H13DonkeyCart")));
                dataModel.setH13DonkeyHorse(cursor.getString(cursor.getColumnIndexOrThrow("H13DonkeyHorse")));
                dataModel.setH13Camels(cursor.getString(cursor.getColumnIndexOrThrow("H13Camels")));


                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);


            }
        }}


        for (int i = 0; i < hhDetails.size(); i++) {

            Cursor cursor1 = db.rawQuery("select * from " + tblhhroster + " where EA_Assignment_ID = " + hhDetails.get(i).getAssignment_ID() + " and BatchNumber = " + hhDetails.get(i).getBatchNumber(), null);
            PersonRoster dataModel1 = null;
            PersonRoster[] HouseHoldeMembers = new PersonRoster[cursor1.getCount()];
            while (cursor1.moveToNext()) {
                dataModel1 = new PersonRoster();
                String SRNO = cursor1.getString(cursor1.getColumnIndexOrThrow("SRNO"));
                String P01 = cursor1.getString(cursor1.getColumnIndexOrThrow("P01"));
                String P02 = cursor1.getString(cursor1.getColumnIndexOrThrow("P02"));
                String P03 = cursor1.getString(cursor1.getColumnIndexOrThrow("P03"));
                String P04_YY = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_YY"));
                String P04_MM = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_MM"));
                String P04_WKS = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_WKS"));
                String P05 = cursor1.getString(cursor1.getColumnIndexOrThrow("P05"));
                String P06 = cursor1.getString(cursor1.getColumnIndexOrThrow("P06"));
                String P07 = cursor1.getString(cursor1.getColumnIndexOrThrow("P07"));
                String P08 = cursor1.getString(cursor1.getColumnIndexOrThrow("P08"));
                String P09 = cursor1.getString(cursor1.getColumnIndexOrThrow("P09"));
                String P10 = cursor1.getString(cursor1.getColumnIndexOrThrow("P10"));
                String P11 = cursor1.getString(cursor1.getColumnIndexOrThrow("P11"));
                String P12 = cursor1.getString(cursor1.getColumnIndexOrThrow("P12"));
                String P13 = cursor1.getString(cursor1.getColumnIndexOrThrow("P13"));
                String P13Other = cursor1.getString(cursor1.getColumnIndexOrThrow("P13Other"));
                String P14 = cursor1.getString(cursor1.getColumnIndexOrThrow("P14"));
                String P15 = cursor1.getString(cursor1.getColumnIndexOrThrow("P15"));
                String P16 = cursor1.getString(cursor1.getColumnIndexOrThrow("P16"));

                String P17 = cursor1.getString(cursor1.getColumnIndexOrThrow("P17"));
                String P18 = cursor1.getString(cursor1.getColumnIndexOrThrow("P18"));
                String P19 = cursor1.getString(cursor1.getColumnIndexOrThrow("P19"));
                String P20 = cursor1.getString(cursor1.getColumnIndexOrThrow("P20"));
                String P21 = cursor1.getString(cursor1.getColumnIndexOrThrow("P21"));
                // String B3_RapidConsent_Yes_No = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));
                String B3_Guardian = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_Guardian"));
                //String B3_Date = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_Date"));
                String Barcode = cursor1.getString(cursor1.getColumnIndexOrThrow("Barcode"));
                String U15Rapid_Results = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidResults"));
                //String Rapid_Comment = cursor1.getString(cursor1.getColumnIndexOrThrow("Rapid_Comment"));

                String BloodDraw = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodDraw"));
                String BloodVolume_1 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_1"));
                String BloodVolume_4 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_4"));
                String BloodVolume_6 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_6"));
                String BloodVolume_10 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_10"));
                String BloodVolumeComment = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolumeComment"));
                String Rapid = cursor1.getString(cursor1.getColumnIndexOrThrow("Rapid"));
                String RapidResults = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidResults"));
                String BloodLabTest = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodLabTest"));
                String BloodStore = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodStore"));

                String RapidDate = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidDate"));

                String BloodSampleCollected = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodSampleCollected"));
                String ChPrntlConsentBloodDraw = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentBloodDraw"));
                String ChPrntlConsentRHT = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentRHT"));
                String ChPrntlConsentLabTest = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentLabTest"));
                String ChPrntlConsentBloodStore = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentBloodStore"));
                String ChPrntlConsentDate = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentDate"));
                String PrntlConsentX_Ray = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentX_Ray"));
                String PrntlConsentX_RayReview = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentX_RayReview"));
                String PrntlConsentX_RayStore = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentX_RayStore"));
                String PrntlConsentSP_Collect = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentSP_Collect"));
                String PrntlParentSP_AddTests = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlParentSP_AddTests"));
                String PrntlConsentSP_LabTest = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentSP_LabTest"));
                String PrntlConsentTBDate = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentTBDate"));
                String IndTB_X_Ray = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_X_Ray"));
                String IndTB_X_RayReview = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_X_RayReview"));
                String IndTB_X_RayStore = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_X_RayStore"));
                String IndSP_Collect = cursor1.getString(cursor1.getColumnIndexOrThrow("IndSP_Collect"));
                String IndSP_AddTests = cursor1.getString(cursor1.getColumnIndexOrThrow("IndSP_AddTests"));
                String IndSP_LabTests = cursor1.getString(cursor1.getColumnIndexOrThrow("IndSP_LabTests"));
                String IndTB_ConsentDate = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_ConsentDate"));

                dataModel1.setAssignmentID(cursor1.getString(cursor1.getColumnIndexOrThrow("EA_Assignment_ID")));
                dataModel1.setBatch(cursor1.getString(cursor1.getColumnIndexOrThrow("BatchNumber")));

                dataModel1.setSRNO(Integer.valueOf(SRNO));
                dataModel1.setP01(P01);
                dataModel1.setP02(P02);
                dataModel1.setP03(P03);
                dataModel1.setP04YY(P04_YY);
                dataModel1.setP04MM(P04_MM);
                dataModel1.setP04WKS(P04_WKS);
                dataModel1.setP05(P05);
                dataModel1.setP06(P06);
                dataModel1.setP07(P07);
                dataModel1.setP08(P08);
                dataModel1.setP09(P09);
                dataModel1.setP10(P10);
                dataModel1.setP11(P11);
                dataModel1.setP12(P12);
                dataModel1.setP13(P13);
                dataModel1.setP13Other(P13Other);
                dataModel1.setP14(P14);
                dataModel1.setP15(P15);
                dataModel1.setP16(P16);
                dataModel1.setP17(P17);
                dataModel1.setP18(P18);
                dataModel1.setP19(P19);
                dataModel1.setP20(P20);
                dataModel1.setP21(P21);
                //dataModel1.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);
                dataModel1.setB3_Guardian(B3_Guardian);
                //dataModel1.setB3_Date(B3_Date);
                dataModel1.setBarcode(Barcode);
                dataModel1.setU15Rapid_Results(U15Rapid_Results);
                // dataModel1.setRapid_Comment(Rapid_Comment);

                dataModel1.setBloodDraw(BloodDraw);
                dataModel1.setBloodVolume_1(BloodVolume_1);
                dataModel1.setBloodVolume_4(BloodVolume_4);
                dataModel1.setBloodVolume_6(BloodVolume_6);
                dataModel1.setBloodVolume_10(BloodVolume_10);
                dataModel1.setBloodVolumeComment(BloodVolumeComment);
                dataModel1.setRapid(Rapid);
                dataModel1.setRapidResults(RapidResults);
                dataModel1.setBloodLabTest(BloodLabTest);
                dataModel1.setBloodStore(BloodStore);

                dataModel1.setRapidDate(RapidDate);

                dataModel1.setBloodSampleCollected(BloodSampleCollected);
                dataModel1.setChPrntlConsentBloodDraw(ChPrntlConsentBloodDraw);
                dataModel1.setChPrntlConsentRHT(ChPrntlConsentRHT);
                dataModel1.setChPrntlConsentLabTest(ChPrntlConsentLabTest);
                dataModel1.setChPrntlConsentBloodStore(ChPrntlConsentBloodStore);
                dataModel1.setChPrntlConsentDate(ChPrntlConsentDate);
                dataModel1.setPrntlConsentX_Ray(PrntlConsentX_Ray);
                dataModel1.setPrntlConsentX_RayReview(PrntlConsentX_RayReview);
                dataModel1.setPrntlConsentX_RayStore(PrntlConsentX_RayStore);
                dataModel1.setPrntlConsentSP_Collect(PrntlConsentSP_Collect);
                dataModel1.setPrntlParentSP_AddTests(PrntlParentSP_AddTests);
                dataModel1.setPrntlConsentSP_LabTest(PrntlConsentSP_LabTest);
                dataModel1.setPrntlConsentTBDate(PrntlConsentTBDate);
                dataModel1.setIndTB_X_Ray(IndTB_X_Ray);
                dataModel1.setIndTB_X_RayReview(IndTB_X_RayReview);
                dataModel1.setIndTB_X_RayStore(IndTB_X_RayStore);
                dataModel1.setIndSP_Collect(IndSP_Collect);
                dataModel1.setIndSP_AddTests(IndSP_AddTests);
                dataModel1.setIndSP_LabTests(IndSP_LabTests);
                dataModel1.setIndTB_ConsentDate(IndTB_ConsentDate);


                HouseHoldeMembers[cursor1.getPosition()] = (dataModel1);


            }

            hhDetails.get(i).setHouseHoldeMembers(HouseHoldeMembers);
            Log.d("Members: ", String.valueOf(hhDetails.get(i).getPersons().length));

            Cursor cursor2 = db.rawQuery("select * from " + tblindividual + " where Assignment_ID = " + hhDetails.get(i).getAssignment_ID() + " and BatchNumber = " + hhDetails.get(i).getBatchNumber(), null);
            PersonRoster dataModel2 = null;
            Individual[] individuals = new Individual[cursor2.getCount()];

            while (cursor2.moveToNext()) {

                //Invdividual
                Individual ind = new Individual();
                ind.setAssignmentID(cursor2.getString(cursor2.getColumnIndexOrThrow("Assignment_ID")));
                ind.setBatch(cursor2.getString(cursor2.getColumnIndexOrThrow("BatchNumber")));

                ind.setSRNO(Integer.parseInt(cursor2.getString(cursor2.getColumnIndexOrThrow("SRNO"))));
                ind.setIndBarcode(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBarcode")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q102")));
                ind.setQ103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q103")));

                ind.setQ104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104")));
                ind.setQ104a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104a")));
                ind.setQ104b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104b")));
                ind.setQ104c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104c")));
                //ind.setQ104cBISCED(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104cBISCED")));
                ind.setQ105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105")));
                ind.setQ105Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105Other")));
                ind.setQ105a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105a")));

                ind.setQ105b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105b")));

                ind.setQ106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106")));
                ind.setQ106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106a")));
                ind.setQ106aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106aOther")));

                ind.setQ106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106b")));
                ind.setQ106c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106c")));

                ind.setQ106d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106d")));

                ind.setQ107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107")));

                String s = cursor2.getString(cursor2.getColumnIndexOrThrow("Q107a"));

                if (s != null) {
                    ind.setQ107aMnth(s.substring(0, 2));
                    ind.setQ107aYY(s.substring(2, 4));
                }


                ind.setQ107b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107b")));
                ind.setQ107bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107bOther")));
                ind.setQ107c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107c")));
                ind.setQ107cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107cOther")));

                ind.setQ201(cursor2.getString(cursor2.getColumnIndexOrThrow("Q201")));
                ind.setQ202(cursor2.getString(cursor2.getColumnIndexOrThrow("Q202")));
                ind.setQ203(cursor2.getString(cursor2.getColumnIndexOrThrow("Q203")));
                ind.setQ204(cursor2.getString(cursor2.getColumnIndexOrThrow("Q204")));
                ind.setQ205(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205")));
                ind.setQ205a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205a")));

                ind.setQ301(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ301a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ302(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ303(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ303a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ304(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ304a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ305_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Smoking")));
                ind.setQ305_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Sniffing")));
                ind.setQ305_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Chewing")));
                ind.setQ305_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305None")));

                ind.setQ306(cursor2.getString(cursor2.getColumnIndexOrThrow("Q306")));
                ind.setQ307(cursor2.getString(cursor2.getColumnIndexOrThrow("Q307")));

                ind.setQ401(cursor2.getString(cursor2.getColumnIndexOrThrow("Q401")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ402(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402")));
                ind.setQ402a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402a")));
                ind.setQ402b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402b")));
                ind.setQ403(cursor2.getString(cursor2.getColumnIndexOrThrow("Q403")));

                ind.setQ404_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Vaginal")));
                ind.setQ404_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Anal")));
                ind.setQ404_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Oral")));

                ind.setQ404a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404a")));

                ind.setQ405(cursor2.getString(cursor2.getColumnIndexOrThrow("Q405")));
                ind.setQ406(cursor2.getString(cursor2.getColumnIndexOrThrow("Q406")));
                ind.setQ407(cursor2.getString(cursor2.getColumnIndexOrThrow("Q407")));

                ind.setQ408(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408")));
                ind.setQ408a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408a")));

                ind.setQ410Slapped(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Slapped")));
                ind.setQ410Pushed(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Pushed")));
                ind.setQ410Choked(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Chocked")));
                ind.setQ410Threatened(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Threatened")));
                ind.setQ410Physical(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Physical")));
                ind.setQ410Forced(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Forced")));
                ind.setQ410MadeAfraid(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410MadeAfraid")));

                ind.setQ501(cursor2.getString(cursor2.getColumnIndexOrThrow("Q501")));
                ind.setQ502(cursor2.getString(cursor2.getColumnIndexOrThrow("Q502")));
                ind.setQ503(cursor2.getString(cursor2.getColumnIndexOrThrow("Q503")));

                ind.setQ504_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Pain")));
                ind.setQ504_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Reduced")));
                ind.setQ504_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Fear")));
                ind.setQ504_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Culture")));
                ind.setQ504_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Religion")));
                ind.setQ504_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Spouse")));
                ind.setQ504_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Parental")));
                ind.setQ504_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Long")));
                ind.setQ504_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_FearHIV")));

                ind.setQ504_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Other")));

                ind.setQ601(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601")));
                ind.setQ601a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601a")));

                ind.setQ602_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Youth")));
                ind.setQ602_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602TV")));
                ind.setQ602_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Radio")));
                ind.setQ602_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Newspaper")));
                ind.setQ602_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Hospital")));
                ind.setQ602_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Posters")));
                ind.setQ602_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Traditional")));
                ind.setQ602_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ602_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Individual")));
                ind.setQ602_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Church")));
                ind.setQ602_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Kgotla")));
                ind.setQ602_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workplace")));
                ind.setQ602_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Peer")));
                ind.setQ602_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602School")));
                ind.setQ602_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602_Other")));

                ind.setQ603_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Condom")));
                ind.setQ603_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603FewerP")));
                ind.setQ603_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Both")));
                ind.setQ603_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCasual")));
                ind.setQ603_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Abstain")));
                ind.setQ603_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCommercial")));
                ind.setQ603_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Injection")));
                ind.setQ603_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Blood")));
                ind.setQ603_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603DontKnow")));
                ind.setQ603_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Other")));


                ind.setQ604(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604")));
                ind.setQ604a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604a")));
                ind.setQ604b_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bYouth")));
                ind.setQ604b_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTV")));
                ind.setQ604b_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bRadio")));
                ind.setQ604b_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bNewspaper")));
                ind.setQ604b_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bHospital")));
                ind.setQ604b_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPoster")));
                ind.setQ604b_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTraditional")));
                ind.setQ604b_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ604b_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bIndividual")));
                ind.setQ604b_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bChurch")));
                ind.setQ604b_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bKgotla")));
                ind.setQ604b_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bWorkplace")));
                ind.setQ604b_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPeer")));
                ind.setQ604b_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bSchool")));
                ind.setQ604b_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bOther")));

                ind.setQ605_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Windows")));
                ind.setQ605_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Mouth")));
                ind.setQ605_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Hands")));
                ind.setQ605_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Nutrition")));
                ind.setQ605_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Praying")));
                ind.setQ605_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605DontKnow")));
                ind.setQ605_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Other")));

                ind.setQ606(cursor2.getString(cursor2.getColumnIndexOrThrow("Q606")));
                ind.setQ607(cursor2.getString(cursor2.getColumnIndexOrThrow("Q607")));
                ind.setQ608(cursor2.getString(cursor2.getColumnIndexOrThrow("Q608")));
                ind.setQ609(cursor2.getString(cursor2.getColumnIndexOrThrow("Q609")));
                ind.setQ610(cursor2.getString(cursor2.getColumnIndexOrThrow("Q610")));

                ind.setQ611a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611a")));
                ind.setQ611b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611b")));
                ind.setQ611c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611c")));

                ind.setQ612(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612")));
                ind.setQ612a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612a")));
                ind.setQ612aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612Other")));

                ind.setQ613(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613")));
                ind.setQ613a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613a")));
                ind.setQ613aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613aOther")));
                ind.setQ614(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614")));
                ind.setQ614Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614Other")));
                ind.setQ615(cursor2.getString(cursor2.getColumnIndexOrThrow("Q615")));

                ind.setQ616_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Anybody")));
                ind.setQ616_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Poor")));
                ind.setQ616_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Homeless")));
                ind.setQ616_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Alcoholics")));
                ind.setQ616_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Drugs")));
                ind.setQ616_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopHIV")));
                ind.setQ616_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopPrison")));
                ind.setQ616_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Smokers")));
                ind.setQ616_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616DntKnow")));
                ind.setQ616_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Other")));


                ind.setQ617a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Meal")));
                ind.setQ617b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Clothes")));
                ind.setQ617c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Miscarried")));
                ind.setQ617d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Widow")));
                ind.setQ617e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617FamilyHIV")));
                ind.setQ617f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Sejeso")));
                ind.setQ617g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Touching")));
                ind.setQ617h(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Someone")));
                ind.setQ617_0ther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Other")));
                ind.setQ618(cursor2.getString(cursor2.getColumnIndexOrThrow("Q618")));

                ind.setQ619_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Rash")));
                ind.setQ619_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Cough")));
                ind.setQ619_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619LongCough")));
                ind.setQ619_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Blood")));
                ind.setQ619_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Headache")));
                ind.setQ619_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Nausea")));
                ind.setQ619_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Weight")));
                ind.setQ619_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever")));
                ind.setQ619_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever7Days")));
                ind.setQ619_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619ChestPain")));
                ind.setQ619_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Breath")));
                ind.setQ619_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fatigue")));
                ind.setQ619_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Sweats")));
                ind.setQ619_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619DontKnow")));
                ind.setQ619_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Other")));
                ind.setQ620(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620")));
                ind.setQ620_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620Other")));

                ind.setQ621(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621")));
                ind.setQ621a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aSpouse")));
                ind.setQ621a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aPartner")));
                ind.setQ621a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFriend")));
                ind.setQ621a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFamily")));
                ind.setQ621a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aRelative")));
                ind.setQ621a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aHCWorker")));
                ind.setQ621a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aCoWorker")));
                ind.setQ621a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aOther")));
                ind.setQ621b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621b")));

                ind.setQ621bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621bOther")));

                ind.setQ622(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622")));

                ind.setQ622a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622a")));
                ind.setQ622aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622aOther")));

                ind.setQ622b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622b")));
                ind.setQ622bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622bOther")));


                ind.setQ623(cursor2.getString(cursor2.getColumnIndexOrThrow("Q623")));
                ind.setQ624(cursor2.getString(cursor2.getColumnIndexOrThrow("Q624")));
                ind.setQ625(cursor2.getString(cursor2.getColumnIndexOrThrow("Q625")));
                ind.setQ701(cursor2.getString(cursor2.getColumnIndexOrThrow("Q701")));
                ind.setQ702(cursor2.getString(cursor2.getColumnIndexOrThrow("Q702")));
                ind.setQ703(cursor2.getString(cursor2.getColumnIndexOrThrow("Q703")));
                ind.setQ704(cursor2.getString(cursor2.getColumnIndexOrThrow("Q704")));
                ind.setQ705(cursor2.getString(cursor2.getColumnIndexOrThrow("Q705")));
                ind.setQ801(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801")));


                ind.setQ801a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801a")));
                ind.setQ801b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801b")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")) != null) {
                    ind.setQ801cMonth(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(0, 2));
                    ind.setQ801cYear(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(2, 6));
                }


                ind.setQ801d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801d")));
                ind.setQ801dOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801dOther")));

                ind.setQ801e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801e")));
                ind.setQ801eOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801eOther")));


                ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));

                ind.setQ802(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802")));
                ind.setQ802a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802a")));
                ind.setQ802aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802aOther")));


                ind.setQ803(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803")));
                ind.setQ803Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803Other")));

                ind.setQ804(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804")));
                ind.setQ804Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804Other")));

                ind.setQ901(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901")));
                ind.setQ901a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901a")));
                ind.setQ901aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901aOther")));

                String sg = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));
                String ss = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));

                if (sg != null) {
                    ind.setQ902Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(0, 2));
                }
                if (ss != null) {
                    ind.setQ902Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(2, 6));
                }


                ind.setQ903a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903DenyCare")));
                ind.setQ903b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903Gossip")));
                ind.setQ903c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoSex")));
                ind.setQ903d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903VerbalAbuse")));
                ind.setQ903e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903PhysicalAbuse")));
                ind.setQ903f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoContact")));
                ind.setQ903g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903SharingStatus")));


                ind.setQ904(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904")));
                ind.setQ904a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904a")));
                ind.setQ904aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904aOther")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")) != null) {
                    ind.setQ904bMM(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(0, 2) + cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(2, 6));
                }


                ind.setQ904c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904c")));
                ind.setQ904cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904cOther")));


                ind.setQ905(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905")));
                ind.setQ905a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905a")));
                ind.setQ905aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905aOther")));

                ind.setQ1001(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1001")));
                ind.setQ1002(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002")));

                ind.setQ1002a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMCondom")));
                ind.setQ1002a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFCondom")));
                ind.setQ1002a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aInjectContra")));
                ind.setQ1002a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOralContra")));
                ind.setQ1002a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aUID")));
                ind.setQ1002a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aBTL")));
                ind.setQ1002a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFSterilization")));
                ind.setQ1002a_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMSterilization")));
                ind.setQ1002a_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aImplants")));
                ind.setQ1002a_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aEContra")));
                ind.setQ1002a_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSafePeriod")));
                ind.setQ1002a_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aLAM")));
                ind.setQ1002a_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aDiagraphm")));
                ind.setQ1002a_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpermicides")));
                ind.setQ1002a_16(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aNatural")));
                ind.setQ1002a_17(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aTraditional")));
                ind.setQ1002a_18(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpiritual")));
                ind.setQ1002a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOther")));

                ind.setQ1002b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002b")));
                ind.setQ1002bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002bOther")));

                ind.setQ1003(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1003")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null  && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 8  ) {
                    ind.setQ1004_Day(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(0, 2));
                    ind.setQ1004_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(2, 4));
                    ind.setQ1004_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(4, 8));
                }
                else
                {
                    if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 4)
                    {

                        ind.setQ1004_Day("00");
                        ind.setQ1004_Month("00");
                        ind.setQ1004_Year("0000");
                    }
                }


                ind.setQ1004a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004a")));
                ind.setQ1004b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004b")));
                ind.setQ1004bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004bOther")));


                ind.setQ1005(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005")));
                ind.setQ1005a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005a")));
                ind.setQ1006(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1006")));

                ind.setQ1007(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007")));
                ind.setQ1007a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007a")));

                ind.setQ1008(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008")));
                ind.setQ1008a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008a")));
                ind.setQ1008aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008aOther")));


                ind.setQ1009(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009")));
                ind.setQ1009a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009a")));

                ind.setQ1010(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010")));
                ind.setQ1010Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010Other")));

                ind.setQ1011(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011")));
                ind.setQ1011_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011Other")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")) != null) {
                    ind.setQ1012_Week(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(0, 2));
                    ind.setQ1012_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(2, 4));
                    ind.setQ1012_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(4, 6));
                }
                ind.setQ1012Still(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012Still")));

                ind.setQ1013(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1013")));
                ind.setQ1014(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014")));
                ind.setQ1014a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014a")));
                ind.setQ1014b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014b")));

                ind.setQ1015(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015")));
                ind.setQ1015a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015a")));
                ind.setQ1015b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015b")));
                ind.setQ1016(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1016")));
                ind.setQ1017(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1017")));


                ind.setQ1101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101")));
                ind.setQ1101a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101a")));
                ind.setQ1101aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101aOther")));
                ind.setQ1102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102")));
                ind.setQ1102a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102a")));
                ind.setQ1103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103")));

                ind.setQ1103aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDD")));
                ind.setQ1103aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aWks")));


                ind.setQ1103aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDontKnow")));

                ind.setQ1104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1104")));
                ind.setQ1105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1105")));
                ind.setQ1106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106")));
                ind.setQ1106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106a")));
                ind.setQ1106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106b")));
                ind.setQ1106bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106bOther")));
                ind.setQ1107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107")));


                ind.setQ1107aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDD")));
                ind.setQ1107aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aWks")));


                ind.setQ1107aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDontKnow")));

                ind.setQ1108(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108")));

                ind.setQ1108aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDD")));
                ind.setQ1108aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aWks")));

                ind.setQ1108aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDontKnow")));


                ind.setQ1109(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1109")));
                ind.setQ1110(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1110")));
                ind.setQ1111(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111")));
                ind.setQ1111Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111Other")));

                ind.setQ1112(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112")));
                ind.setQ1112_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112Other")));

                ind.setQ1113(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113")));
                ind.setQ1113Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113Other")));

                ind.setQ1114(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1114")));

                ind.setIndvQuestionnairePConsent15_17(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnairePConsent15_17")));
                ind.setIndvQuestionnaireConsent(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnaireConsent")));
                ind.setIndvBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvBloodDraw")));
                ind.setBloodVol_1(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_1")));
                ind.setBloodVol_4(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_4")));
                ind.setBloodVol_6(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_6")));
                ind.setBloodVol_10(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_10")));
                ind.setBloodVolComment(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVolComment")));
                ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_O15_Rapid")));
                ind.setIndRapidResults(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
                ind.setIndBloodLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodLabTest")));
                ind.setIndBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodStore")));
                ind.setIndRapidDate(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidDate")));
                ind.setIndBloodSampleCollected(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodSampleCollected")));
                ind.setPrntlConsentBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodDraw")));
                ind.setPrntlConsentRHT(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentRHT")));
                ind.setPrntlConsentLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentLabTest")));
                ind.setPrntlConsentBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodStore")));
                ind.setPrntlParentID(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlParentID")));
                ind.setPrntlConsentDate(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentDate")));

                ind.setVISIT1(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT1")));
                ind.setVISIT2(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT2")));
                ind.setVISIT3(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT3")));
                ind.setDATE1(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE1")));
                ind.setDATE2(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE2")));
                ind.setDATE3(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE3")));
                ind.setSync(cursor2.getString(cursor2.getColumnIndexOrThrow("Sync")));

                // ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));

                individuals[cursor2.getPosition()] = (ind);
            }
            hhDetails.get(i).setIndividualQuestionaire(individuals);
            Log.d("Individual: ", String.valueOf(hhDetails.get(i).getIndividual().length));
        }

        return hhDetails;
    }


    /***
     * GET STARTED HOUSEHOLD FROM DB
     * @return
     */
    public List<HouseHold> getCompletedFinal() {
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tblhousehold, null);

        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {
            if ((cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")) != null &&
                    (cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")).equals("10")))) {

                dataModel = new HouseHold();
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
                dataModel.setClear(cursor.getString(cursor.getColumnIndexOrThrow("Clear")));

                dataModel.setH01(cursor.getString(cursor.getColumnIndexOrThrow("H01")));
                dataModel.setH02(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")));
                dataModel.setH03(cursor.getString(cursor.getColumnIndexOrThrow("H02")));
                dataModel.setH03Other(cursor.getString(cursor.getColumnIndexOrThrow("H03Other")));
                dataModel.setH04(cursor.getString(cursor.getColumnIndexOrThrow("H04")));
                dataModel.setH04Other(cursor.getString(cursor.getColumnIndexOrThrow("H04Other")));
                dataModel.setH05(cursor.getString(cursor.getColumnIndexOrThrow("H05")));
                dataModel.setH05Other(cursor.getString(cursor.getColumnIndexOrThrow("H05Other")));
                dataModel.setH06(cursor.getString(cursor.getColumnIndexOrThrow("H06")));
                dataModel.setH07(cursor.getString(cursor.getColumnIndexOrThrow("H07")));
                dataModel.setH08(cursor.getString(cursor.getColumnIndexOrThrow("H08")));
                dataModel.setH08Other(cursor.getString(cursor.getColumnIndexOrThrow("H08Other")));
                dataModel.setH09(cursor.getString(cursor.getColumnIndexOrThrow("H09")));
                dataModel.setH09Other(cursor.getString(cursor.getColumnIndexOrThrow("H09Other")));
                dataModel.setH10(cursor.getString(cursor.getColumnIndexOrThrow("H10")));
                dataModel.setH11(cursor.getString(cursor.getColumnIndexOrThrow("H11")));
                dataModel.setH11Other(cursor.getString(cursor.getColumnIndexOrThrow("H11Other")));

                dataModel.setH12(cursor.getString(cursor.getColumnIndexOrThrow("H12Radio")));
                dataModel.setH12TV(cursor.getString(cursor.getColumnIndexOrThrow("H12TV")));
                dataModel.setH12Telephone(cursor.getString(cursor.getColumnIndexOrThrow("H12Telephone")));
                dataModel.setH12CellPhone(cursor.getString(cursor.getColumnIndexOrThrow("H12CellPhone")));
                dataModel.setH12PrintMedia(cursor.getString(cursor.getColumnIndexOrThrow("H12PrintMedia")));
                dataModel.setH12ElecMedia(cursor.getString(cursor.getColumnIndexOrThrow("H12ElecMedia")));
                dataModel.setH12PerfomArts(cursor.getString(cursor.getColumnIndexOrThrow("H12PerformArts")));

                dataModel.setH13(cursor.getString(cursor.getColumnIndexOrThrow("H13Vehicle")));
                dataModel.setH13Tractor(cursor.getString(cursor.getColumnIndexOrThrow("H13Tractor")));
                dataModel.setH13Motorcycle(cursor.getString(cursor.getColumnIndexOrThrow("H13Motorcycle")));
                dataModel.setH13Bicycle(cursor.getString(cursor.getColumnIndexOrThrow("H13Bicycle")));
                dataModel.setH13DonkeyCart(cursor.getString(cursor.getColumnIndexOrThrow("H13DonkeyCart")));
                dataModel.setH13DonkeyHorse(cursor.getString(cursor.getColumnIndexOrThrow("H13DonkeyHorse")));
                dataModel.setH13Camels(cursor.getString(cursor.getColumnIndexOrThrow("H13Camels")));


                stringBuffer.append(dataModel);
                // stringBuffer.append(dataModel);
                hhDetails.add(dataModel);


            }
        }


        for (int i = 0; i < hhDetails.size(); i++) {

            Cursor cursor1 = db.rawQuery("select * from " + tblhhroster + " where EA_Assignment_ID = " + hhDetails.get(i).getAssignment_ID() + " and BatchNumber = " + hhDetails.get(i).getBatchNumber(), null);
            PersonRoster dataModel1 = null;
            PersonRoster[] HouseHoldeMembers = new PersonRoster[cursor1.getCount()];
            while (cursor1.moveToNext()) {
                dataModel1 = new PersonRoster();
                String SRNO = cursor1.getString(cursor1.getColumnIndexOrThrow("SRNO"));
                String P01 = cursor1.getString(cursor1.getColumnIndexOrThrow("P01"));
                String P02 = cursor1.getString(cursor1.getColumnIndexOrThrow("P02"));
                String P03 = cursor1.getString(cursor1.getColumnIndexOrThrow("P03"));
                String P04_YY = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_YY"));
                String P04_MM = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_MM"));
                String P04_WKS = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_WKS"));
                String P05 = cursor1.getString(cursor1.getColumnIndexOrThrow("P05"));
                String P06 = cursor1.getString(cursor1.getColumnIndexOrThrow("P06"));
                String P07 = cursor1.getString(cursor1.getColumnIndexOrThrow("P07"));
                String P08 = cursor1.getString(cursor1.getColumnIndexOrThrow("P08"));
                String P09 = cursor1.getString(cursor1.getColumnIndexOrThrow("P09"));
                String P10 = cursor1.getString(cursor1.getColumnIndexOrThrow("P10"));
                String P11 = cursor1.getString(cursor1.getColumnIndexOrThrow("P11"));
                String P12 = cursor1.getString(cursor1.getColumnIndexOrThrow("P12"));
                String P13 = cursor1.getString(cursor1.getColumnIndexOrThrow("P13"));
                String P14 = cursor1.getString(cursor1.getColumnIndexOrThrow("P14"));
                String P15 = cursor1.getString(cursor1.getColumnIndexOrThrow("P15"));
                String P16 = cursor1.getString(cursor1.getColumnIndexOrThrow("P16"));

                String P17 = cursor1.getString(cursor1.getColumnIndexOrThrow("P17"));
                String P18 = cursor1.getString(cursor1.getColumnIndexOrThrow("P18"));
                String P19 = cursor1.getString(cursor1.getColumnIndexOrThrow("P19"));
                String P20 = cursor1.getString(cursor1.getColumnIndexOrThrow("P20"));
                String P21 = cursor1.getString(cursor1.getColumnIndexOrThrow("P21"));
                // String B3_RapidConsent_Yes_No = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));
                String B3_Guardian = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_Guardian"));
                //String B3_Date = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_Date"));
                String Barcode = cursor1.getString(cursor1.getColumnIndexOrThrow("Barcode"));
                String U15Rapid_Results = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidResults"));
                //String Rapid_Comment = cursor1.getString(cursor1.getColumnIndexOrThrow("Rapid_Comment"));

                String BloodDraw = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodDraw"));
                String BloodVolume_1 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_1"));
                String BloodVolume_4 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_4"));
                String BloodVolume_6 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_6"));
                String BloodVolume_10 = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolume_10"));
                String BloodVolumeComment = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodVolumeComment"));
                String Rapid = cursor1.getString(cursor1.getColumnIndexOrThrow("Rapid"));
                String RapidResults = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidResults"));
                String BloodLabTest = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodLabTest"));
                String BloodStore = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodStore"));

                String RapidDate = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidDate"));

                String BloodSampleCollected = cursor1.getString(cursor1.getColumnIndexOrThrow("BloodSampleCollected"));
                String ChPrntlConsentBloodDraw = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentBloodDraw"));
                String ChPrntlConsentRHT = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentRHT"));
                String ChPrntlConsentLabTest = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentLabTest"));
                String ChPrntlConsentBloodStore = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentBloodStore"));
                String ChPrntlConsentDate = cursor1.getString(cursor1.getColumnIndexOrThrow("ChPrntlConsentDate"));
                String PrntlConsentX_Ray = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentX_Ray"));
                String PrntlConsentX_RayReview = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentX_RayReview"));
                String PrntlConsentX_RayStore = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentX_RayStore"));
                String PrntlConsentSP_Collect = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentSP_Collect"));
                String PrntlParentSP_AddTests = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlParentSP_AddTests"));
                String PrntlConsentSP_LabTest = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentSP_LabTest"));
                String PrntlConsentTBDate = cursor1.getString(cursor1.getColumnIndexOrThrow("PrntlConsentTBDate"));
                String IndTB_X_Ray = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_X_Ray"));
                String IndTB_X_RayReview = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_X_RayReview"));
                String IndTB_X_RayStore = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_X_RayStore"));
                String IndSP_Collect = cursor1.getString(cursor1.getColumnIndexOrThrow("IndSP_Collect"));
                String IndSP_AddTests = cursor1.getString(cursor1.getColumnIndexOrThrow("IndSP_AddTests"));
                String IndSP_LabTests = cursor1.getString(cursor1.getColumnIndexOrThrow("IndSP_LabTests"));
                String IndTB_ConsentDate = cursor1.getString(cursor1.getColumnIndexOrThrow("IndTB_ConsentDate"));


                dataModel1.setAssignmentID(cursor1.getString(cursor1.getColumnIndexOrThrow("EA_Assignment_ID")));
                dataModel1.setBatch(cursor1.getString(cursor1.getColumnIndexOrThrow("BatchNumber")));


                dataModel1.setSRNO(Integer.valueOf(SRNO));
                dataModel1.setP01(P01);
                dataModel1.setP02(P02);
                dataModel1.setP03(P03);
                dataModel1.setP04YY(P04_YY);
                dataModel1.setP04MM(P04_MM);
                dataModel1.setP04WKS(P04_WKS);
                dataModel1.setP05(P05);
                dataModel1.setP06(P06);
                dataModel1.setP07(P07);
                dataModel1.setP08(P08);
                dataModel1.setP09(P09);
                dataModel1.setP10(P10);
                dataModel1.setP11(P11);
                dataModel1.setP12(P12);
                dataModel1.setP13(P13);
                dataModel1.setP14(P14);
                dataModel1.setP15(P15);
                dataModel1.setP16(P16);
                dataModel1.setP17(P17);
                dataModel1.setP18(P18);
                dataModel1.setP19(P19);
                dataModel1.setP20(P20);
                dataModel1.setP21(P21);
                //dataModel1.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);
                dataModel1.setB3_Guardian(B3_Guardian);
                //dataModel1.setB3_Date(B3_Date);
                dataModel1.setBarcode(Barcode);
                dataModel1.setU15Rapid_Results(U15Rapid_Results);
                // dataModel1.setRapid_Comment(Rapid_Comment);

                dataModel1.setBloodDraw(BloodDraw);
                dataModel1.setBloodVolume_1(BloodVolume_1);
                dataModel1.setBloodVolume_4(BloodVolume_4);
                dataModel1.setBloodVolume_6(BloodVolume_6);
                dataModel1.setBloodVolume_10(BloodVolume_10);
                dataModel1.setBloodVolumeComment(BloodVolumeComment);
                dataModel1.setRapid(Rapid);
                dataModel1.setRapidResults(RapidResults);
                dataModel1.setBloodLabTest(BloodLabTest);
                dataModel1.setBloodStore(BloodStore);

                dataModel1.setRapidDate(RapidDate);

                dataModel1.setBloodSampleCollected(BloodSampleCollected);
                dataModel1.setChPrntlConsentBloodDraw(ChPrntlConsentBloodDraw);
                dataModel1.setChPrntlConsentRHT(ChPrntlConsentRHT);
                dataModel1.setChPrntlConsentLabTest(ChPrntlConsentLabTest);
                dataModel1.setChPrntlConsentBloodStore(ChPrntlConsentBloodStore);
                dataModel1.setChPrntlConsentDate(ChPrntlConsentDate);
                dataModel1.setPrntlConsentX_Ray(PrntlConsentX_Ray);
                dataModel1.setPrntlConsentX_RayReview(PrntlConsentX_RayReview);
                dataModel1.setPrntlConsentX_RayStore(PrntlConsentX_RayStore);
                dataModel1.setPrntlConsentSP_Collect(PrntlConsentSP_Collect);
                dataModel1.setPrntlParentSP_AddTests(PrntlParentSP_AddTests);
                dataModel1.setPrntlConsentSP_LabTest(PrntlConsentSP_LabTest);
                dataModel1.setPrntlConsentTBDate(PrntlConsentTBDate);
                dataModel1.setIndTB_X_Ray(IndTB_X_Ray);
                dataModel1.setIndTB_X_RayReview(IndTB_X_RayReview);
                dataModel1.setIndTB_X_RayStore(IndTB_X_RayStore);
                dataModel1.setIndSP_Collect(IndSP_Collect);
                dataModel1.setIndSP_AddTests(IndSP_AddTests);
                dataModel1.setIndSP_LabTests(IndSP_LabTests);
                dataModel1.setIndTB_ConsentDate(IndTB_ConsentDate);


                HouseHoldeMembers[cursor1.getPosition()] = (dataModel1);


            }

            hhDetails.get(i).setHouseHoldeMembers(HouseHoldeMembers);
            Log.d("Members: ", String.valueOf(hhDetails.get(i).getPersons().length));

            Cursor cursor2 = db.rawQuery("select * from " + tblindividual + " where Assignment_ID = " + hhDetails.get(i).getAssignment_ID() + " and BatchNumber = " + hhDetails.get(i).getBatchNumber(), null);
            PersonRoster dataModel2 = null;
            Individual[] individuals = new Individual[cursor2.getCount()];

            while (cursor2.moveToNext()) {

                //Invdividual
                Individual ind = new Individual();
                ind.setAssignmentID(cursor2.getString(cursor2.getColumnIndexOrThrow("Assignment_ID")));
                ind.setBatch(cursor2.getString(cursor2.getColumnIndexOrThrow("BatchNumber")));

                ind.setSRNO(Integer.parseInt(cursor2.getString(cursor2.getColumnIndexOrThrow("SRNO"))));
                ind.setIndBarcode(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBarcode")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q102")));
                ind.setQ103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q103")));

                ind.setQ104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104")));
                ind.setQ104a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104a")));
                ind.setQ104b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104b")));
                ind.setQ104c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104c")));
                //ind.setQ104cBISCED(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104cBISCED")));
                ind.setQ105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105")));
                ind.setQ105Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105Other")));
                ind.setQ105a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105a")));

                ind.setQ105b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105b")));

                ind.setQ106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106")));
                ind.setQ106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106a")));
                ind.setQ106aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106aOther")));

                ind.setQ106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106b")));
                ind.setQ106c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106c")));

                ind.setQ106d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106d")));

                ind.setQ107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107")));

                String s = cursor2.getString(cursor2.getColumnIndexOrThrow("Q107a"));

                if (s != null) {
                    ind.setQ107aMnth(s.substring(0, 2));
                    ind.setQ107aYY(s.substring(2, 4));
                }


                ind.setQ107b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107b")));
                ind.setQ107bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107bOther")));
                ind.setQ107c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107c")));
                ind.setQ107cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107cOther")));

                ind.setQ201(cursor2.getString(cursor2.getColumnIndexOrThrow("Q201")));
                ind.setQ202(cursor2.getString(cursor2.getColumnIndexOrThrow("Q202")));
                ind.setQ203(cursor2.getString(cursor2.getColumnIndexOrThrow("Q203")));
                ind.setQ204(cursor2.getString(cursor2.getColumnIndexOrThrow("Q204")));
                ind.setQ205(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205")));
                ind.setQ205a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205a")));

                ind.setQ301(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ301a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ302(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ303(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ303a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ304(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ304a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ305_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Smoking")));
                ind.setQ305_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Sniffing")));
                ind.setQ305_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Chewing")));
                ind.setQ305_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305None")));

                ind.setQ306(cursor2.getString(cursor2.getColumnIndexOrThrow("Q306")));
                ind.setQ307(cursor2.getString(cursor2.getColumnIndexOrThrow("Q307")));

                ind.setQ401(cursor2.getString(cursor2.getColumnIndexOrThrow("Q401")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ402(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402")));
                ind.setQ402a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402a")));
                ind.setQ402b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402b")));
                ind.setQ403(cursor2.getString(cursor2.getColumnIndexOrThrow("Q403")));

                ind.setQ404_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Vaginal")));
                ind.setQ404_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Anal")));
                ind.setQ404_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Oral")));

                ind.setQ404a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404a")));

                ind.setQ405(cursor2.getString(cursor2.getColumnIndexOrThrow("Q405")));
                ind.setQ406(cursor2.getString(cursor2.getColumnIndexOrThrow("Q406")));
                ind.setQ407(cursor2.getString(cursor2.getColumnIndexOrThrow("Q407")));

                ind.setQ408(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408")));
                ind.setQ408a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408a")));

                ind.setQ410Slapped(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Slapped")));
                ind.setQ410Pushed(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Pushed")));
                ind.setQ410Choked(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Chocked")));
                ind.setQ410Threatened(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Threatened")));
                ind.setQ410Physical(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Physical")));
                ind.setQ410Forced(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Forced")));
                ind.setQ410MadeAfraid(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410MadeAfraid")));

                ind.setQ501(cursor2.getString(cursor2.getColumnIndexOrThrow("Q501")));
                ind.setQ502(cursor2.getString(cursor2.getColumnIndexOrThrow("Q502")));
                ind.setQ503(cursor2.getString(cursor2.getColumnIndexOrThrow("Q503")));

                ind.setQ504_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Pain")));
                ind.setQ504_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Reduced")));
                ind.setQ504_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Fear")));
                ind.setQ504_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Culture")));
                ind.setQ504_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Religion")));
                ind.setQ504_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Spouse")));
                ind.setQ504_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Parental")));
                ind.setQ504_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Long")));
                ind.setQ504_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_FearHIV")));
                ind.setQ504_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Other")));

                ind.setQ601(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601")));
                ind.setQ601a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601a")));

                ind.setQ602_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Youth")));
                ind.setQ602_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602TV")));
                ind.setQ602_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Radio")));
                ind.setQ602_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Newspaper")));
                ind.setQ602_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Hospital")));
                ind.setQ602_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Posters")));
                ind.setQ602_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Traditional")));
                ind.setQ602_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ602_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Individual")));
                ind.setQ602_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Church")));
                ind.setQ602_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Kgotla")));
                ind.setQ602_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workplace")));
                ind.setQ602_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Peer")));
                ind.setQ602_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602School")));
                ind.setQ602_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602_Other")));

                ind.setQ603_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Condom")));
                ind.setQ603_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603FewerP")));
                ind.setQ603_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Both")));
                ind.setQ603_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCasual")));
                ind.setQ603_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Abstain")));
                ind.setQ603_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCommercial")));
                ind.setQ603_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Injection")));
                ind.setQ603_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Blood")));
                ind.setQ603_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603DontKnow")));
                ind.setQ603_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Other")));


                ind.setQ604(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604")));
                ind.setQ604a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604a")));
                ind.setQ604b_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bYouth")));
                ind.setQ604b_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTV")));
                ind.setQ604b_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bRadio")));
                ind.setQ604b_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bNewspaper")));
                ind.setQ604b_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bHospital")));
                ind.setQ604b_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPoster")));
                ind.setQ604b_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTraditional")));
                ind.setQ604b_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ604b_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bIndividual")));
                ind.setQ604b_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bChurch")));
                ind.setQ604b_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bKgotla")));
                ind.setQ604b_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bWorkplace")));
                ind.setQ604b_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPeer")));
                ind.setQ604b_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bSchool")));
                ind.setQ604b_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bOther")));

                ind.setQ605_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Windows")));
                ind.setQ605_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Mouth")));
                ind.setQ605_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Hands")));
                ind.setQ605_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Nutrition")));
                ind.setQ605_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Praying")));
                ind.setQ605_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605DontKnow")));
                ind.setQ605_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Other")));

                ind.setQ606(cursor2.getString(cursor2.getColumnIndexOrThrow("Q606")));
                ind.setQ607(cursor2.getString(cursor2.getColumnIndexOrThrow("Q607")));
                ind.setQ608(cursor2.getString(cursor2.getColumnIndexOrThrow("Q608")));
                ind.setQ609(cursor2.getString(cursor2.getColumnIndexOrThrow("Q609")));
                ind.setQ610(cursor2.getString(cursor2.getColumnIndexOrThrow("Q610")));

                ind.setQ611a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611a")));
                ind.setQ611b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611b")));
                ind.setQ611c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611c")));

                ind.setQ612(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612")));
                ind.setQ612a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612a")));
                ind.setQ612aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612Other")));

                ind.setQ613(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613")));
                ind.setQ613a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613a")));
                ind.setQ613aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613aOther")));
                ind.setQ614(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614")));
                ind.setQ614Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614Other")));
                ind.setQ615(cursor2.getString(cursor2.getColumnIndexOrThrow("Q615")));

                ind.setQ616_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Anybody")));
                ind.setQ616_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Poor")));
                ind.setQ616_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Homeless")));
                ind.setQ616_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Alcoholics")));
                ind.setQ616_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Drugs")));
                ind.setQ616_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopHIV")));
                ind.setQ616_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopPrison")));
                ind.setQ616_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Smokers")));
                ind.setQ616_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616DntKnow")));
                ind.setQ616_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Other")));


                ind.setQ617a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Meal")));
                ind.setQ617b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Clothes")));
                ind.setQ617c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Miscarried")));
                ind.setQ617d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Widow")));
                ind.setQ617e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617FamilyHIV")));
                ind.setQ617f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Sejeso")));
                ind.setQ617g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Touching")));
                ind.setQ617h(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Someone")));
                ind.setQ617_0ther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Other")));
                ind.setQ618(cursor2.getString(cursor2.getColumnIndexOrThrow("Q618")));

                ind.setQ619_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Rash")));
                ind.setQ619_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Cough")));
                ind.setQ619_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619LongCough")));
                ind.setQ619_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Blood")));
                ind.setQ619_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Headache")));
                ind.setQ619_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Nausea")));
                ind.setQ619_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Weight")));
                ind.setQ619_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever")));
                ind.setQ619_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever7Days")));
                ind.setQ619_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619ChestPain")));
                ind.setQ619_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Breath")));
                ind.setQ619_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fatigue")));
                ind.setQ619_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Sweats")));
                ind.setQ619_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619DontKnow")));
                ind.setQ619_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Other")));
                ind.setQ620(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620")));
                ind.setQ620_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620Other")));

                ind.setQ621(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621")));
                ind.setQ621a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aSpouse")));
                ind.setQ621a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aPartner")));
                ind.setQ621a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFriend")));
                ind.setQ621a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFamily")));
                ind.setQ621a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aRelative")));
                ind.setQ621a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aHCWorker")));
                ind.setQ621a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aCoWorker")));
                ind.setQ621a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aOther")));
                ind.setQ621b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621b")));

                ind.setQ621bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621bOther")));

                ind.setQ622(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622")));

                ind.setQ622a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622a")));
                ind.setQ622aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622aOther")));

                ind.setQ622b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622b")));
                ind.setQ622bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622bOther")));


                ind.setQ623(cursor2.getString(cursor2.getColumnIndexOrThrow("Q623")));
                ind.setQ624(cursor2.getString(cursor2.getColumnIndexOrThrow("Q624")));
                ind.setQ625(cursor2.getString(cursor2.getColumnIndexOrThrow("Q625")));
                ind.setQ701(cursor2.getString(cursor2.getColumnIndexOrThrow("Q701")));
                ind.setQ702(cursor2.getString(cursor2.getColumnIndexOrThrow("Q702")));
                ind.setQ703(cursor2.getString(cursor2.getColumnIndexOrThrow("Q703")));
                ind.setQ704(cursor2.getString(cursor2.getColumnIndexOrThrow("Q704")));
                ind.setQ705(cursor2.getString(cursor2.getColumnIndexOrThrow("Q705")));
                ind.setQ801(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801")));


                ind.setQ801a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801a")));
                ind.setQ801b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801b")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")) != null) {
                    ind.setQ801cMonth(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(0, 2));
                    ind.setQ801cYear(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(2, 6));
                }


                ind.setQ801d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801d")));
                ind.setQ801dOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801dOther")));

                ind.setQ801e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801e")));
                ind.setQ801eOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801eOther")));


                ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));

                ind.setQ802(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802")));
                ind.setQ802a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802a")));
                ind.setQ802aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802aOther")));


                ind.setQ803(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803")));
                ind.setQ803Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803Other")));

                ind.setQ804(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804")));
                ind.setQ804Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804Other")));

                ind.setQ901(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901")));
                ind.setQ901a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901a")));
                ind.setQ901aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901aOther")));

                String sg = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));
                String ss = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));

                if (sg != null) {
                    ind.setQ902Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(0, 2));
                }
                if (ss != null) {
                    ind.setQ902Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(2, 6));
                }


                ind.setQ903a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903DenyCare")));
                ind.setQ903b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903Gossip")));
                ind.setQ903c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoSex")));
                ind.setQ903d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903VerbalAbuse")));
                ind.setQ903e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903PhysicalAbuse")));
                ind.setQ903f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoContact")));
                ind.setQ903g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903SharingStatus")));


                ind.setQ904(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904")));
                ind.setQ904a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904a")));
                ind.setQ904aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904aOther")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")) != null) {
                    ind.setQ904bMM(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(0, 2) + cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(2, 6));
                }


                ind.setQ904c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904c")));
                ind.setQ904cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904cOther")));


                ind.setQ905(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905")));
                ind.setQ905a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905a")));
                ind.setQ905aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905aOther")));

                ind.setQ1001(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1001")));
                ind.setQ1002(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002")));

                ind.setQ1002a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMCondom")));
                ind.setQ1002a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFCondom")));
                ind.setQ1002a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aInjectContra")));
                ind.setQ1002a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOralContra")));
                ind.setQ1002a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aUID")));
                ind.setQ1002a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aBTL")));
                ind.setQ1002a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFSterilization")));
                ind.setQ1002a_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMSterilization")));
                ind.setQ1002a_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aImplants")));
                ind.setQ1002a_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aEContra")));
                ind.setQ1002a_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSafePeriod")));
                ind.setQ1002a_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aLAM")));
                ind.setQ1002a_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aDiagraphm")));
                ind.setQ1002a_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpermicides")));
                ind.setQ1002a_16(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aNatural")));
                ind.setQ1002a_17(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aTraditional")));
                ind.setQ1002a_18(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpiritual")));
                ind.setQ1002a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOther")));

                ind.setQ1002b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002b")));
                ind.setQ1002bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002bOther")));

                ind.setQ1003(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1003")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 8) {
                    ind.setQ1004_Day(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(0, 2));
                    ind.setQ1004_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(2, 4));
                    ind.setQ1004_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(4, 8));
                }
                else
                {
                    if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 4)
                    {

                        ind.setQ1004_Day("00");
                        ind.setQ1004_Month("00");
                        ind.setQ1004_Year("0000");
                    }
                }


                ind.setQ1004a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004a")));
                ind.setQ1004b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004b")));
                ind.setQ1004bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004bOther")));


                ind.setQ1005(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005")));
                ind.setQ1005a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005a")));
                ind.setQ1006(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1006")));

                ind.setQ1007(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007")));
                ind.setQ1007a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007a")));

                ind.setQ1008(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008")));
                ind.setQ1008a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008a")));
                ind.setQ1008aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008aOther")));


                ind.setQ1009(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009")));
                ind.setQ1009a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009a")));

                ind.setQ1010(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010")));
                ind.setQ1010Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010Other")));

                ind.setQ1011(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011")));
                ind.setQ1011_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011Other")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")) != null) {
                    ind.setQ1012_Week(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(0, 2));
                    ind.setQ1012_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(2, 4));
                    ind.setQ1012_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(4, 6));
                }
                ind.setQ1012Still(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012Still")));

                ind.setQ1013(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1013")));
                ind.setQ1014(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014")));
                ind.setQ1014a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014a")));
                ind.setQ1014b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014b")));

                ind.setQ1015(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015")));
                ind.setQ1015a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015a")));
                ind.setQ1015b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015b")));
                ind.setQ1016(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1016")));
                ind.setQ1017(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1017")));


                ind.setQ1101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101")));
                ind.setQ1101a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101a")));
                ind.setQ1101aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101aOther")));
                ind.setQ1102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102")));
                ind.setQ1102a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102a")));
                ind.setQ1103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103")));

                ind.setQ1103aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDD")));
                ind.setQ1103aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aWks")));


                ind.setQ1103aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDontKnow")));

                ind.setQ1104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1104")));
                ind.setQ1105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1105")));
                ind.setQ1106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106")));
                ind.setQ1106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106a")));
                ind.setQ1106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106b")));
                ind.setQ1106bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106bOther")));
                ind.setQ1107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107")));


                ind.setQ1107aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDD")));
                ind.setQ1107aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aWks")));


                ind.setQ1107aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDontKnow")));

                ind.setQ1108(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108")));

                ind.setQ1108aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDD")));
                ind.setQ1108aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aWks")));

                ind.setQ1108aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDontKnow")));


                ind.setQ1109(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1109")));
                ind.setQ1110(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1110")));
                ind.setQ1111(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111")));
                ind.setQ1111Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111Other")));

                ind.setQ1112(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112")));
                ind.setQ1112_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112Other")));

                ind.setQ1113(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113")));
                ind.setQ1113Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113Other")));

                ind.setQ1114(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1114")));

                ind.setIndvQuestionnairePConsent15_17(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnairePConsent15_17")));
                ind.setIndvQuestionnaireConsent(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnaireConsent")));
                ind.setIndvBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvBloodDraw")));
                ind.setBloodVol_1(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_1")));
                ind.setBloodVol_4(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_4")));
                ind.setBloodVol_6(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_6")));
                ind.setBloodVol_10(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_10")));
                ind.setBloodVolComment(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVolComment")));
                ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_O15_Rapid")));
                ind.setIndRapidResults(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
                ind.setIndBloodLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodLabTest")));
                ind.setIndBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodStore")));
                ind.setIndRapidDate(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidDate")));
                ind.setIndBloodSampleCollected(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodSampleCollected")));
                ind.setPrntlConsentBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodDraw")));
                ind.setPrntlConsentRHT(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentRHT")));
                ind.setPrntlConsentLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentLabTest")));
                ind.setPrntlConsentBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodStore")));
                ind.setPrntlParentID(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlParentID")));
                ind.setPrntlConsentDate(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentDate")));

                ind.setVISIT1(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT1")));
                ind.setVISIT2(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT2")));
                ind.setVISIT3(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT3")));
                ind.setDATE1(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE1")));
                ind.setDATE2(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE2")));
                ind.setDATE3(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE3")));
                ind.setSync(cursor2.getString(cursor2.getColumnIndexOrThrow("Sync")));

                // ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));

                individuals[cursor2.getPosition()] = (ind);
            }
            hhDetails.get(i).setIndividualQuestionaire(individuals);
            Log.d("Individual: ", String.valueOf(hhDetails.get(i).getIndividual().length));
        }

        return hhDetails;
    }


    public List<HouseHold> getHouseForUpdate(String AssignId, String BatchNO) {
        // DataModel dataModel = new DataModel();
        List<HouseHold> hhDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tblhousehold + " where EA_Assignment_ID=" + AssignId + " and BatchNumber=" + BatchNO, null);

        StringBuffer stringBuffer = new StringBuffer();
        HouseHold dataModel = null;
        while (cursor.moveToNext()) {

            dataModel = new HouseHold();
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
            String H01 = cursor.getString(cursor.getColumnIndexOrThrow("H01"));
            String H02 = cursor.getString(cursor.getColumnIndexOrThrow("H02"));
            String H03 = cursor.getString(cursor.getColumnIndexOrThrow("H03"));
            String H03Other = cursor.getString(cursor.getColumnIndexOrThrow("H03Other"));
            String H04 = cursor.getString(cursor.getColumnIndexOrThrow("H04"));
            String H04Other = cursor.getString(cursor.getColumnIndexOrThrow("H04Other"));
            String H05 = cursor.getString(cursor.getColumnIndexOrThrow("H05"));
            String H05Other = cursor.getString(cursor.getColumnIndexOrThrow("H05Other"));
            String H06 = cursor.getString(cursor.getColumnIndexOrThrow("H06"));
            String H07 = cursor.getString(cursor.getColumnIndexOrThrow("H07"));
            String H08 = cursor.getString(cursor.getColumnIndexOrThrow("H08"));
            String H08Other = cursor.getString(cursor.getColumnIndexOrThrow("H08Other"));
            String H09Other = cursor.getString(cursor.getColumnIndexOrThrow("H09Other"));
            String H09 = cursor.getString(cursor.getColumnIndexOrThrow("H09"));
            String H10 = cursor.getString(cursor.getColumnIndexOrThrow("H10"));
            String H11 = cursor.getString(cursor.getColumnIndexOrThrow("H11"));
            String H11Other = cursor.getString(cursor.getColumnIndexOrThrow("H11Other"));
            String H12Radio = cursor.getString(cursor.getColumnIndexOrThrow("H12Radio"));
            String H12TV = cursor.getString(cursor.getColumnIndexOrThrow("H12TV"));
            String H12Telephone = cursor.getString(cursor.getColumnIndexOrThrow("H12Telephone"));
            String H12CellPhone = cursor.getString(cursor.getColumnIndexOrThrow("H12CellPhone"));
            String H12PrintMedia = cursor.getString(cursor.getColumnIndexOrThrow("H12PrintMedia"));
            String H12ElecMedia = cursor.getString(cursor.getColumnIndexOrThrow("H12ElecMedia"));
            String H12PerformArts = cursor.getString(cursor.getColumnIndexOrThrow("H12PerformArts"));
            String H13Vehicle = cursor.getString(cursor.getColumnIndexOrThrow("H13Vehicle"));
            String H13Tractor = cursor.getString(cursor.getColumnIndexOrThrow("H13Tractor"));
            String H13Motorcycle = cursor.getString(cursor.getColumnIndexOrThrow("H13Motorcycle"));
            String H13Bicycle = cursor.getString(cursor.getColumnIndexOrThrow("H13Bicycle"));
            String H13DonkeyCart = cursor.getString(cursor.getColumnIndexOrThrow("H13DonkeyCart"));
            String H13DonkeyHorse = cursor.getString(cursor.getColumnIndexOrThrow("H13DonkeyHorse"));
            String H13Camels = cursor.getString(cursor.getColumnIndexOrThrow("H13Camels"));
            String HIVTB40 = cursor.getString(cursor.getColumnIndexOrThrow("HIVTB40"));


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
            dataModel.setHIVTB40(HIVTB40);
            dataModel.setInterview_Status(cursor.getString(cursor.getColumnIndexOrThrow("Interview_Status")));
            dataModel.setClear(cursor.getString(cursor.getColumnIndexOrThrow("Clear")));
            dataModel.setSuperComment(cursor.getString(cursor.getColumnIndexOrThrow("SuperComment")));



            dataModel.setH01(H01);

            dataModel.setH02(H02);

            dataModel.setH03(H03);

            dataModel.setH03Other(H03Other);
            dataModel.setH04(H04);
            dataModel.setH04Other(H04Other);
            dataModel.setH05(H05);
            dataModel.setH05Other(H05Other);
            dataModel.setH06(H06);
            dataModel.setH07(H07);
            dataModel.setH08(H08);
            dataModel.setH08Other(H08Other);
            dataModel.setH09(H09);
            dataModel.setH09Other(H09Other);
            dataModel.setH10(H10);
            dataModel.setH11(H11);
            dataModel.setH11Other(H11Other);

            dataModel.setH12(H12Radio);
            dataModel.setH12TV(H12TV);
            dataModel.setH12Telephone(H12Telephone);
            dataModel.setH12CellPhone(H12CellPhone);
            dataModel.setH12PrintMedia(H12PrintMedia);
            dataModel.setH12ElecMedia(H12ElecMedia);
            dataModel.setH12PerfomArts(H12PerformArts);

            dataModel.setH13(H13Vehicle);
            dataModel.setH13Tractor(H13Tractor);
            dataModel.setH13Motorcycle(H13Motorcycle);
            dataModel.setH13Bicycle(H13Bicycle);
            dataModel.setH13DonkeyCart(H13DonkeyCart);
            dataModel.setH13DonkeyHorse(H13DonkeyHorse);
            dataModel.setH13Camels(H13Camels);


            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            hhDetails.add(dataModel);


        }


        for (int i = 0; i < hhDetails.size(); i++) {

            Cursor cursor1 = db.rawQuery("select * from " + tblhhroster + " where EA_Assignment_ID = " + hhDetails.get(i).getAssignment_ID() + " and BatchNumber = " + hhDetails.get(i).getBatchNumber(), null);
            PersonRoster dataModel1 = null;
            PersonRoster[] HouseHoldeMembers = new PersonRoster[cursor1.getCount()];
            while (cursor1.moveToNext()) {
                dataModel1 = new PersonRoster();
                String SRNO = cursor1.getString(cursor1.getColumnIndexOrThrow("SRNO"));
                String P01 = cursor1.getString(cursor1.getColumnIndexOrThrow("P01"));
                String P02 = cursor1.getString(cursor1.getColumnIndexOrThrow("P02"));
                String P03 = cursor1.getString(cursor1.getColumnIndexOrThrow("P03"));
                String P04_YY = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_YY"));
                String P04_MM = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_MM"));
                String P04_WKS = cursor1.getString(cursor1.getColumnIndexOrThrow("P04_WKS"));
                String P05 = cursor1.getString(cursor1.getColumnIndexOrThrow("P05"));
                String P06 = cursor1.getString(cursor1.getColumnIndexOrThrow("P06"));
                String P07 = cursor1.getString(cursor1.getColumnIndexOrThrow("P07"));
                String P08 = cursor1.getString(cursor1.getColumnIndexOrThrow("P08"));
                String P09 = cursor1.getString(cursor1.getColumnIndexOrThrow("P09"));
                String P10 = cursor1.getString(cursor1.getColumnIndexOrThrow("P10"));
                String P11 = cursor1.getString(cursor1.getColumnIndexOrThrow("P11"));
                String P12 = cursor1.getString(cursor1.getColumnIndexOrThrow("P12"));
                String P13 = cursor1.getString(cursor1.getColumnIndexOrThrow("P13"));
                String P14 = cursor1.getString(cursor1.getColumnIndexOrThrow("P14"));
                String P15 = cursor1.getString(cursor1.getColumnIndexOrThrow("P15"));
                String P16 = cursor1.getString(cursor1.getColumnIndexOrThrow("P16"));

                String P17 = cursor1.getString(cursor1.getColumnIndexOrThrow("P17"));
                String P18 = cursor1.getString(cursor1.getColumnIndexOrThrow("P18"));
                String P19 = cursor1.getString(cursor1.getColumnIndexOrThrow("P19"));
                String P20 = cursor1.getString(cursor1.getColumnIndexOrThrow("P20"));
                String P21 = cursor1.getString(cursor1.getColumnIndexOrThrow("P21"));
                // String B3_RapidConsent_Yes_No = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_RapidConsent_Yes_No"));
                String B3_Guardian = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_Guardian"));
                //String B3_Date = cursor1.getString(cursor1.getColumnIndexOrThrow("B3_Date"));
                String Barcode = cursor1.getString(cursor1.getColumnIndexOrThrow("Barcode"));
                String U15Rapid_Results = cursor1.getString(cursor1.getColumnIndexOrThrow("RapidResults"));
                //String Rapid_Comment = cursor1.getString(cursor1.getColumnIndexOrThrow("Rapid_Comment"));

                dataModel1.setAssignmentID(cursor1.getString(cursor1.getColumnIndexOrThrow("EA_Assignment_ID")));
                dataModel1.setBatch(cursor1.getString(cursor1.getColumnIndexOrThrow("BatchNumber")));

                dataModel1.setSRNO(Integer.valueOf(SRNO));
                dataModel1.setP01(P01);
                dataModel1.setP02(P02);
                dataModel1.setP03(P03);
                dataModel1.setP04YY(P04_YY);
                dataModel1.setP04MM(P04_MM);
                dataModel1.setP04WKS(P04_WKS);
                dataModel1.setP05(P05);
                dataModel1.setP06(P06);
                dataModel1.setP07(P07);
                dataModel1.setP08(P08);
                dataModel1.setP09(P09);
                dataModel1.setP10(P10);
                dataModel1.setP11(P11);
                dataModel1.setP12(P12);
                dataModel1.setP13(P13);
                dataModel1.setP14(P14);
                dataModel1.setP15(P15);
                dataModel1.setP16(P16);
                dataModel1.setP17(P17);
                dataModel1.setP18(P18);
                dataModel1.setP19(P19);
                dataModel1.setP20(P20);
                dataModel1.setP21(P21);
                //dataModel1.setB3_RapidConsent_Yes_No(B3_RapidConsent_Yes_No);
                dataModel1.setB3_Guardian(B3_Guardian);
                //dataModel1.setB3_Date(B3_Date);
                dataModel1.setBarcode(Barcode);
                dataModel1.setU15Rapid_Results(U15Rapid_Results);
                // dataModel1.setRapid_Comment(Rapid_Comment);
                HouseHoldeMembers[cursor1.getPosition()] = (dataModel1);
            }

            hhDetails.get(i).setHouseHoldeMembers(HouseHoldeMembers);
            Log.d("Members: ", String.valueOf(hhDetails.get(i).getPersons().length));

            Cursor cursor2 = db.rawQuery("select * from " + tblindividual + " where Assignment_ID = " + hhDetails.get(i).getAssignment_ID() + " and BatchNumber = " + hhDetails.get(i).getBatchNumber(), null);
            PersonRoster dataModel2 = null;
            Individual[] individuals = new Individual[cursor2.getCount()];

            while (cursor2.moveToNext()) {

                //Invdividual
                Individual ind = new Individual();
                ind.setAssignmentID(cursor2.getString(cursor2.getColumnIndexOrThrow("Assignment_ID")));
                ind.setBatch(cursor2.getString(cursor2.getColumnIndexOrThrow("BatchNumber")));

                ind.setSRNO(Integer.parseInt(cursor2.getString(cursor2.getColumnIndexOrThrow("SRNO"))));
                ind.setIndBarcode(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBarcode")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q102")));
                ind.setQ103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q103")));

                ind.setQ104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104")));
                ind.setQ104a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104a")));
                ind.setQ104b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104b")));
                ind.setQ104c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104c")));
                //ind.setQ104cBISCED(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104cBISCED")));
                ind.setQ105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105")));
                ind.setQ105Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105Other")));
                ind.setQ105a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105a")));

                ind.setQ105b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105b")));

                ind.setQ106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106")));
                ind.setQ106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106a")));
                ind.setQ106aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106aOther")));

                ind.setQ106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106b")));
                ind.setQ106c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106c")));

                ind.setQ106d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106d")));

                ind.setQ107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107")));

                String s = cursor2.getString(cursor2.getColumnIndexOrThrow("Q107a"));

                if (s != null ) {
                    if(s.trim().equals("")){

                    }else{
                        ind.setQ107aYY(s.substring(0, 2));
                        ind.setQ107aMnth(s.substring(2, 4));
                    }

                }


                ind.setQ107b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107b")));
                ind.setQ107bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107bOther")));
                ind.setQ107c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107c")));
                ind.setQ107cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107cOther")));

                ind.setQ201(cursor2.getString(cursor2.getColumnIndexOrThrow("Q201")));
                ind.setQ202(cursor2.getString(cursor2.getColumnIndexOrThrow("Q202")));
                ind.setQ203(cursor2.getString(cursor2.getColumnIndexOrThrow("Q203")));
                ind.setQ204(cursor2.getString(cursor2.getColumnIndexOrThrow("Q204")));
                ind.setQ205(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205")));
                ind.setQ205a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205a")));

                ind.setQ301(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ301a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ302(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ303(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ303a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ304(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ304a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));

                ind.setQ305_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Smoking")));
                ind.setQ305_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Sniffing")));
                ind.setQ305_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Chewing")));
                ind.setQ305_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305None")));

                ind.setQ306(cursor2.getString(cursor2.getColumnIndexOrThrow("Q306")));
                ind.setQ307(cursor2.getString(cursor2.getColumnIndexOrThrow("Q307")));

                ind.setQ401(cursor2.getString(cursor2.getColumnIndexOrThrow("Q401")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ402(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402")));
                ind.setQ402a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402a")));
                ind.setQ402b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402b")));
                ind.setQ403(cursor2.getString(cursor2.getColumnIndexOrThrow("Q403")));

                ind.setQ404_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Vaginal")));
                ind.setQ404_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Anal")));
                ind.setQ404_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Oral")));

                ind.setQ404a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404a")));

                ind.setQ405(cursor2.getString(cursor2.getColumnIndexOrThrow("Q405")));
                ind.setQ406(cursor2.getString(cursor2.getColumnIndexOrThrow("Q406")));
                ind.setQ407(cursor2.getString(cursor2.getColumnIndexOrThrow("Q407")));

                ind.setQ408(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408")));
                ind.setQ408a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408a")));

                ind.setQ410Slapped(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Slapped")));
                ind.setQ410Pushed(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Pushed")));
                ind.setQ410Choked(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Chocked")));
                ind.setQ410Threatened(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Threatened")));
                ind.setQ410Physical(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Physical")));
                ind.setQ410Forced(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Forced")));
                ind.setQ410MadeAfraid(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410MadeAfraid")));

                ind.setQ501(cursor2.getString(cursor2.getColumnIndexOrThrow("Q501")));
                ind.setQ502(cursor2.getString(cursor2.getColumnIndexOrThrow("Q502")));
                ind.setQ503(cursor2.getString(cursor2.getColumnIndexOrThrow("Q503")));

                ind.setQ504_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Pain")));
                ind.setQ504_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Reduced")));
                ind.setQ504_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Fear")));
                ind.setQ504_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Culture")));
                ind.setQ504_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Religion")));
                ind.setQ504_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Spouse")));
                ind.setQ504_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Parental")));
                ind.setQ504_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Long")));
                ind.setQ504_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_FearHIV")));

                ind.setQ504_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Other")));

                ind.setQ601(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601")));
                ind.setQ601a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601a")));

                ind.setQ602_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Youth")));
                ind.setQ602_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602TV")));
                ind.setQ602_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Radio")));
                ind.setQ602_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Newspaper")));
                ind.setQ602_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Hospital")));
                ind.setQ602_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Posters")));
                ind.setQ602_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Traditional")));
                ind.setQ602_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ602_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Individual")));
                ind.setQ602_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Church")));
                ind.setQ602_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Kgotla")));
                ind.setQ602_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workplace")));
                ind.setQ602_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Peer")));
                ind.setQ602_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602School")));
                ind.setQ602_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602_Other")));

                ind.setQ603_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Condom")));
                ind.setQ603_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603FewerP")));
                ind.setQ603_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Both")));
                ind.setQ603_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCasual")));
                ind.setQ603_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Abstain")));
                ind.setQ603_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCommercial")));
                ind.setQ603_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Injection")));
                ind.setQ603_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Blood")));
                ind.setQ603_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603DontKnow")));
                ind.setQ603_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Other")));


                ind.setQ604(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604")));
                ind.setQ604a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604a")));
                ind.setQ604b_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bYouth")));
                ind.setQ604b_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTV")));
                ind.setQ604b_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bRadio")));
                ind.setQ604b_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bNewspaper")));
                ind.setQ604b_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bHospital")));
                ind.setQ604b_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPoster")));
                ind.setQ604b_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTraditional")));
                ind.setQ604b_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ604b_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bIndividual")));
                ind.setQ604b_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bChurch")));
                ind.setQ604b_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bKgotla")));
                ind.setQ604b_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bWorkplace")));
                ind.setQ604b_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPeer")));
                ind.setQ604b_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bSchool")));
                ind.setQ604b_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bOther")));

                ind.setQ605_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Windows")));
                ind.setQ605_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Mouth")));
                ind.setQ605_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Hands")));
                ind.setQ605_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Nutrition")));
                ind.setQ605_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Praying")));
                ind.setQ605_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605DontKnow")));
                ind.setQ605_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Other")));

                ind.setQ606(cursor2.getString(cursor2.getColumnIndexOrThrow("Q606")));
                ind.setQ607(cursor2.getString(cursor2.getColumnIndexOrThrow("Q607")));
                ind.setQ608(cursor2.getString(cursor2.getColumnIndexOrThrow("Q608")));
                ind.setQ609(cursor2.getString(cursor2.getColumnIndexOrThrow("Q609")));
                ind.setQ610(cursor2.getString(cursor2.getColumnIndexOrThrow("Q610")));

                ind.setQ611a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611a")));
                ind.setQ611b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611b")));
                ind.setQ611c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611c")));

                ind.setQ612(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612")));
                ind.setQ612a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612a")));
                ind.setQ612aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612Other")));

                ind.setQ613(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613")));
                ind.setQ613a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613a")));
                ind.setQ613aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613aOther")));
                ind.setQ614(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614")));
                ind.setQ614Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614Other")));
                ind.setQ615(cursor2.getString(cursor2.getColumnIndexOrThrow("Q615")));

                ind.setQ616_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Anybody")));
                ind.setQ616_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Poor")));
                ind.setQ616_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Homeless")));
                ind.setQ616_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Alcoholics")));
                ind.setQ616_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Drugs")));
                ind.setQ616_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopHIV")));
                ind.setQ616_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopPrison")));
                ind.setQ616_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Smokers")));
                ind.setQ616_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616DntKnow")));
                ind.setQ616_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Other")));


                ind.setQ617a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Meal")));
                ind.setQ617b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Clothes")));
                ind.setQ617c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Miscarried")));
                ind.setQ617d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Widow")));
                ind.setQ617e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617FamilyHIV")));
                ind.setQ617f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Sejeso")));
                ind.setQ617g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Touching")));
                ind.setQ617h(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Someone")));
                ind.setQ617_0ther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Other")));
                ind.setQ618(cursor2.getString(cursor2.getColumnIndexOrThrow("Q618")));

                ind.setQ619_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Rash")));
                ind.setQ619_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Cough")));
                ind.setQ619_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619LongCough")));
                ind.setQ619_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Blood")));
                ind.setQ619_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Headache")));
                ind.setQ619_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Nausea")));
                ind.setQ619_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Weight")));
                ind.setQ619_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever")));
                ind.setQ619_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever7Days")));
                ind.setQ619_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619ChestPain")));
                ind.setQ619_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Breath")));
                ind.setQ619_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fatigue")));
                ind.setQ619_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Sweats")));
                ind.setQ619_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619DontKnow")));
                ind.setQ619_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Other")));
                ind.setQ620(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620")));
                ind.setQ620_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620Other")));

                ind.setQ621(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621")));
                ind.setQ621a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aSpouse")));
                ind.setQ621a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aPartner")));
                ind.setQ621a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFriend")));
                ind.setQ621a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFamily")));
                ind.setQ621a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aRelative")));
                ind.setQ621a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aHCWorker")));
                ind.setQ621a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aCoWorker")));
                ind.setQ621a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aOther")));
                ind.setQ621b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621b")));

                ind.setQ621bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621bOther")));

                ind.setQ622(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622")));

                ind.setQ622a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622a")));
                ind.setQ622aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622aOther")));

                ind.setQ622b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622b")));
                ind.setQ622bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622bOther")));


                ind.setQ623(cursor2.getString(cursor2.getColumnIndexOrThrow("Q623")));
                ind.setQ624(cursor2.getString(cursor2.getColumnIndexOrThrow("Q624")));
                ind.setQ625(cursor2.getString(cursor2.getColumnIndexOrThrow("Q625")));
                ind.setQ701(cursor2.getString(cursor2.getColumnIndexOrThrow("Q701")));
                ind.setQ702(cursor2.getString(cursor2.getColumnIndexOrThrow("Q702")));
                ind.setQ703(cursor2.getString(cursor2.getColumnIndexOrThrow("Q703")));
                ind.setQ704(cursor2.getString(cursor2.getColumnIndexOrThrow("Q704")));
                ind.setQ705(cursor2.getString(cursor2.getColumnIndexOrThrow("Q705")));
                ind.setQ801(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801")));


                ind.setQ801a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801a")));
                ind.setQ801b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801b")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")) != null) {
                    ind.setQ801cMonth(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(0, 2));
                    ind.setQ801cYear(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(2, 6));
                }


                ind.setQ801d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801d")));
                ind.setQ801dOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801dOther")));

                ind.setQ801e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801e")));
                ind.setQ801eOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801eOther")));


                ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));

                ind.setQ802(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802")));
                ind.setQ802a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802a")));
                ind.setQ802aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802aOther")));


                ind.setQ803(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803")));
                ind.setQ803Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803Other")));

                ind.setQ804(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804")));
                ind.setQ804Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804Other")));

                ind.setQ901(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901")));
                ind.setQ901a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901a")));
                ind.setQ901aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901aOther")));

                String sg = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));
                String ss = cursor2.getString(cursor2.getColumnIndexOrThrow("Q902"));

                if (sg != null) {
                    ind.setQ902Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(0, 2));
                }
                if (ss != null) {
                    ind.setQ902Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(2, 6));
                }


                ind.setQ903a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903DenyCare")));
                ind.setQ903b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903Gossip")));
                ind.setQ903c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoSex")));
                ind.setQ903d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903VerbalAbuse")));
                ind.setQ903e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903PhysicalAbuse")));
                ind.setQ903f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoContact")));
                ind.setQ903g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903SharingStatus")));


                ind.setQ904(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904")));
                ind.setQ904a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904a")));
                ind.setQ904aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904aOther")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")) != null) {
                    ind.setQ904bMM(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(0, 2) + cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(2, 6));
                }


                ind.setQ904c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904c")));
                ind.setQ904cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904cOther")));


                ind.setQ905(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905")));
                ind.setQ905a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905a")));
                ind.setQ905aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905aOther")));

                ind.setQ1001(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1001")));
                ind.setQ1002(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002")));

                ind.setQ1002a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMCondom")));
                ind.setQ1002a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFCondom")));
                ind.setQ1002a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aInjectContra")));
                ind.setQ1002a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOralContra")));
                ind.setQ1002a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aUID")));
                ind.setQ1002a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aBTL")));
                ind.setQ1002a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFSterilization")));
                ind.setQ1002a_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMSterilization")));
                ind.setQ1002a_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aImplants")));
                ind.setQ1002a_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aEContra")));
                ind.setQ1002a_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSafePeriod")));
                ind.setQ1002a_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aLAM")));
                ind.setQ1002a_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aDiagraphm")));
                ind.setQ1002a_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpermicides")));
                ind.setQ1002a_16(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aNatural")));
                ind.setQ1002a_17(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aTraditional")));
                ind.setQ1002a_18(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpiritual")));
                ind.setQ1002a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOther")));

                ind.setQ1002b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002b")));
                ind.setQ1002bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002bOther")));

                ind.setQ1003(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1003")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 8) {
                    ind.setQ1004_Day(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(0, 2));
                    ind.setQ1004_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(2, 4));
                    ind.setQ1004_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(4, 8));
                }
                else
                {
                    if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 4)
                    {

                        ind.setQ1004_Day("00");
                        ind.setQ1004_Month("00");
                        ind.setQ1004_Year("0000");
                    }
                }


                ind.setQ1004a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004a")));
                ind.setQ1004b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004b")));
                ind.setQ1004bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004bOther")));


                ind.setQ1005(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005")));
                ind.setQ1005a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005a")));
                ind.setQ1006(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1006")));

                ind.setQ1007(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007")));
                ind.setQ1007a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007a")));

                ind.setQ1008(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008")));
                ind.setQ1008a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008a")));
                ind.setQ1008aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008aOther")));


                ind.setQ1009(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009")));
                ind.setQ1009a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009a")));

                ind.setQ1010(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010")));
                ind.setQ1010Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010Other")));

                ind.setQ1011(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011")));
                ind.setQ1011_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011Other")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")) != null) {
                    ind.setQ1012_Week(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(0, 2));
                    ind.setQ1012_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(2, 4));
                    ind.setQ1012_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(4, 6));
                }
                ind.setQ1012Still(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012Still")));

                ind.setQ1013(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1013")));
                ind.setQ1014(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014")));
                ind.setQ1014a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014a")));
                ind.setQ1014b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014b")));

                ind.setQ1015(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015")));
                ind.setQ1015a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015a")));
                ind.setQ1015b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015b")));
                ind.setQ1016(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1016")));
                ind.setQ1017(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1017")));


                ind.setQ1101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101")));
                ind.setQ1101a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101a")));
                ind.setQ1101aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101aOther")));
                ind.setQ1102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102")));
                ind.setQ1102a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102a")));
                ind.setQ1103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103")));

                ind.setQ1103aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDD")));
                ind.setQ1103aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aWks")));


                ind.setQ1103aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDontKnow")));

                ind.setQ1104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1104")));
                ind.setQ1105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1105")));
                ind.setQ1106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106")));
                ind.setQ1106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106a")));
                ind.setQ1106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106b")));
                ind.setQ1106bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106bOther")));
                ind.setQ1107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107")));


                ind.setQ1107aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDD")));
                ind.setQ1107aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aWks")));


                ind.setQ1107aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDontKnow")));

                ind.setQ1108(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108")));

                ind.setQ1108aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDD")));
                ind.setQ1108aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aWks")));

                ind.setQ1108aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDontKnow")));


                ind.setQ1109(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1109")));
                ind.setQ1110(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1110")));
                ind.setQ1111(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111")));
                ind.setQ1111Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111Other")));

                ind.setQ1112(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112")));
                ind.setQ1112_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112Other")));

                ind.setQ1113(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113")));
                ind.setQ1113Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113Other")));

                ind.setQ1114(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1114")));

                ind.setIndvQuestionnairePConsent15_17(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnairePConsent15_17")));
                ind.setIndvQuestionnaireConsent(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnaireConsent")));
                ind.setIndvBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvBloodDraw")));
                ind.setBloodVol_1(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_1")));
                ind.setBloodVol_4(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_4")));
                ind.setBloodVol_6(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_6")));
                ind.setBloodVol_10(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_10")));
                ind.setBloodVolComment(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVolComment")));
                ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_O15_Rapid")));
                ind.setIndRapidResults(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
                ind.setIndBloodLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodLabTest")));
                ind.setIndBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodStore")));
                ind.setIndRapidDate(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidDate")));
                ind.setIndBloodSampleCollected(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodSampleCollected")));
                ind.setPrntlConsentBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodDraw")));
                ind.setPrntlConsentRHT(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentRHT")));
                ind.setPrntlConsentLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentLabTest")));
                ind.setPrntlConsentBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodStore")));
                ind.setPrntlParentID(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlParentID")));
                ind.setPrntlConsentDate(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentDate")));
                ind.setVISIT1(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT1")));
                ind.setVISIT2(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT2")));
                ind.setVISIT3(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT3")));
                ind.setDATE1(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE1")));
                ind.setDATE2(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE2")));
                ind.setDATE3(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE3")));
                ind.setSync(cursor2.getString(cursor2.getColumnIndexOrThrow("Sync")));


                //ind.setB8_Yes_No(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_Yes_No")));
                //ind.setB8_Date(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_Date")));
               // ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
                //ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));
                // ind.setIndRapid_Comment(cursor2.getString(cursor2.getColumnIndexOrThrow("Rapid_Comment")));


                individuals[cursor2.getPosition()] = (ind);
            }
            hhDetails.get(i).setIndividualQuestionaire(individuals);

        }


        return hhDetails;
    }


    public List<Individual> getIndividualdt() {
        // DataModel dataModel = new DataModel();
        List<Individual> indDetails = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db = this.getReadableDatabase();


        Cursor cursor2 = db.rawQuery("select * from " + tblindividual + " where Assignment_ID = " + Assignment_ID + " and BatchNumber = " + BatchNumber + " and SRNO = " + SRNO, null);
        StringBuffer stringBuffer = new StringBuffer();
        // HouseHold dataModel = null;
        while (cursor2.moveToNext()) {

            Individual[] individuals = new Individual[cursor2.getCount()];

            while (cursor2.moveToNext()) {

                //Invdividual
                Individual ind = new Individual();
                ind.setAssignmentID(cursor2.getString(cursor2.getColumnIndexOrThrow("Assignment_ID")));
                ind.setBatch(cursor2.getString(cursor2.getColumnIndexOrThrow("BatchNumber")));

                ind.setSRNO(Integer.parseInt(cursor2.getString(cursor2.getColumnIndexOrThrow("SRNO"))));
                ind.setIndBarcode(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBarcode")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q102")));
                ind.setQ103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q103")));
                ind.setQ104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104")));
                ind.setQ104a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104a")));
                ind.setQ104b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104b")));
                ind.setQ104c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104c")));
                //ind.setQ104cBISCED(cursor2.getString(cursor2.getColumnIndexOrThrow("Q104cBISCED")));
                ind.setQ105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105")));
                ind.setQ105Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105Other")));
                ind.setQ105a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105a")));
                ind.setQ105b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q105b")));
                ind.setQ106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106")));
                ind.setQ106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106a")));
                ind.setQ106aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106aOther")));
                ind.setQ106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106b")));
                ind.setQ106c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106c")));
                ind.setQ106d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q106d")));
                ind.setQ107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107")));
                String s = cursor2.getString(cursor2.getColumnIndexOrThrow("Q107a"));
                ind.setQ107aMnth(s.substring(0, 2));
                ind.setQ107aYY(s.substring(2, 4));
                ind.setQ107b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107b")));
                ind.setQ107bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107bOther")));
                ind.setQ107c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107c")));
                ind.setQ107cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q107cOther")));
                ind.setQ201(cursor2.getString(cursor2.getColumnIndexOrThrow("Q201")));
                ind.setQ202(cursor2.getString(cursor2.getColumnIndexOrThrow("Q202")));
                ind.setQ203(cursor2.getString(cursor2.getColumnIndexOrThrow("Q203")));
                ind.setQ204(cursor2.getString(cursor2.getColumnIndexOrThrow("Q204")));
                ind.setQ205(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205")));
                ind.setQ205a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q205a")));
                ind.setQ301(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ301a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ302(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ303(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ303a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ304(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ304a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q301")));
                ind.setQ305_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Smoking")));
                ind.setQ305_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Sniffing")));
                ind.setQ305_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305Chewing")));
                ind.setQ305_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q305None")));
                ind.setQ306(cursor2.getString(cursor2.getColumnIndexOrThrow("Q306")));
                ind.setQ307(cursor2.getString(cursor2.getColumnIndexOrThrow("Q307")));
                ind.setQ401(cursor2.getString(cursor2.getColumnIndexOrThrow("Q401")));
                ind.setQ101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q101")));
                ind.setQ402(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402")));
                ind.setQ402a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402a")));
                ind.setQ402b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q402b")));
                ind.setQ403(cursor2.getString(cursor2.getColumnIndexOrThrow("Q403")));
                ind.setQ404_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Vaginal")));
                ind.setQ404_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Anal")));
                ind.setQ404_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404Oral")));
                ind.setQ404a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q404a")));
                ind.setQ405(cursor2.getString(cursor2.getColumnIndexOrThrow("Q405")));
                ind.setQ406(cursor2.getString(cursor2.getColumnIndexOrThrow("Q406")));
                ind.setQ407(cursor2.getString(cursor2.getColumnIndexOrThrow("Q407")));
                ind.setQ408(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408")));
                ind.setQ408a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q408a")));
                ind.setQ410Slapped(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Slapped")));
                ind.setQ410Pushed(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Pushed")));
                ind.setQ410Choked(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Chocked")));
                ind.setQ410Threatened(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Threatened")));
                ind.setQ410Physical(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Physical")));
                ind.setQ410Forced(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410Forced")));
                ind.setQ410MadeAfraid(cursor2.getString(cursor2.getColumnIndexOrThrow("Q410MadeAfraid")));
                ind.setQ501(cursor2.getString(cursor2.getColumnIndexOrThrow("Q501")));
                ind.setQ502(cursor2.getString(cursor2.getColumnIndexOrThrow("Q502")));
                ind.setQ503(cursor2.getString(cursor2.getColumnIndexOrThrow("Q503")));
                ind.setQ504_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Pain")));
                ind.setQ504_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Reduced")));
                ind.setQ504_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Fear")));
                ind.setQ504_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Culture")));
                ind.setQ504_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Religion")));
                ind.setQ504_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Spouse")));
                ind.setQ504_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Parental")));
                ind.setQ504_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Long")));
                ind.setQ504_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_FearHIV")));
                ind.setQ504_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q504_Other")));
                ind.setQ601(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601")));
                ind.setQ601a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q601a")));
                ind.setQ602_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Youth")));
                ind.setQ602_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602TV")));
                ind.setQ602_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Radio")));
                ind.setQ602_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Newspaper")));
                ind.setQ602_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Hospital")));
                ind.setQ602_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Posters")));
                ind.setQ602_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Traditional")));
                ind.setQ602_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ602_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Individual")));
                ind.setQ602_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Church")));
                ind.setQ602_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Kgotla")));
                ind.setQ602_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workplace")));
                ind.setQ602_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Peer")));
                ind.setQ602_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602School")));
                ind.setQ602_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602_Other")));
                ind.setQ603_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Condom")));
                ind.setQ603_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603FewerP")));
                ind.setQ603_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Both")));
                ind.setQ603_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCasual")));
                ind.setQ603_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Abstain")));
                ind.setQ603_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603NoCommercial")));
                ind.setQ603_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Injection")));
                ind.setQ603_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Blood")));
                ind.setQ603_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603DontKnow")));
                ind.setQ603_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q603Other")));
                ind.setQ604(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604")));
                ind.setQ604a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604a")));
                ind.setQ604b_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bYouth")));
                ind.setQ604b_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTV")));
                ind.setQ604b_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bRadio")));
                ind.setQ604b_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bNewspaper")));
                ind.setQ604b_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bHospital")));
                ind.setQ604b_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPoster")));
                ind.setQ604b_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bTraditional")));
                ind.setQ604b_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q602Workshop")));
                ind.setQ604b_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bIndividual")));
                ind.setQ604b_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bChurch")));
                ind.setQ604b_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bKgotla")));
                ind.setQ604b_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bWorkplace")));
                ind.setQ604b_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bPeer")));
                ind.setQ604b_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bSchool")));
                ind.setQ604b_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q604bOther")));
                ind.setQ605_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Windows")));
                ind.setQ605_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Mouth")));
                ind.setQ605_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Hands")));
                ind.setQ605_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Nutrition")));
                ind.setQ605_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Praying")));
                ind.setQ605_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605DontKnow")));
                ind.setQ605_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q605Other")));
                ind.setQ606(cursor2.getString(cursor2.getColumnIndexOrThrow("Q606")));
                ind.setQ607(cursor2.getString(cursor2.getColumnIndexOrThrow("Q607")));
                ind.setQ608(cursor2.getString(cursor2.getColumnIndexOrThrow("Q608")));
                ind.setQ609(cursor2.getString(cursor2.getColumnIndexOrThrow("Q609")));
                ind.setQ610(cursor2.getString(cursor2.getColumnIndexOrThrow("Q610")));
                ind.setQ611a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611a")));
                ind.setQ611b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611b")));
                ind.setQ611c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q611c")));
                ind.setQ612(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612")));
                ind.setQ612a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612a")));
                ind.setQ612aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q612Other")));
                ind.setQ613(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613")));
                ind.setQ613a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613a")));
                ind.setQ613aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q613aOther")));
                ind.setQ614(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614")));
                ind.setQ614Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q614Other")));
                ind.setQ615(cursor2.getString(cursor2.getColumnIndexOrThrow("Q615")));
                ind.setQ616_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Anybody")));
                ind.setQ616_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Poor")));
                ind.setQ616_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Homeless")));
                ind.setQ616_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Alcoholics")));
                ind.setQ616_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Drugs")));
                ind.setQ616_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopHIV")));
                ind.setQ616_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616PeopPrison")));
                ind.setQ616_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Smokers")));
                ind.setQ616_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616DntKnow")));
                ind.setQ616_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q616Other")));
                ind.setQ617a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Meal")));
                ind.setQ617b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Clothes")));
                ind.setQ617c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Miscarried")));
                ind.setQ617d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Widow")));
                ind.setQ617e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617FamilyHIV")));
                ind.setQ617f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Sejeso")));
                ind.setQ617g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Touching")));
                ind.setQ617h(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Someone")));
                ind.setQ617_0ther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q617Other")));
                ind.setQ618(cursor2.getString(cursor2.getColumnIndexOrThrow("Q618")));
                ind.setQ619_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Rash")));
                ind.setQ619_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Cough")));
                ind.setQ619_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619LongCough")));
                ind.setQ619_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Blood")));
                ind.setQ619_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Headache")));
                ind.setQ619_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Nausea")));
                ind.setQ619_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Weight")));
                ind.setQ619_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever")));
                ind.setQ619_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fever7Days")));
                ind.setQ619_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619ChestPain")));
                ind.setQ619_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Breath")));
                ind.setQ619_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Fatigue")));
                ind.setQ619_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Sweats")));
                ind.setQ619_9(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619DontKnow")));
                ind.setQ619_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q619Other")));
                ind.setQ620(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620")));
                ind.setQ620_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q620Other")));
                ind.setQ621(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621")));
                ind.setQ621a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aSpouse")));
                ind.setQ621a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aPartner")));
                ind.setQ621a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFriend")));
                ind.setQ621a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aFamily")));
                ind.setQ621a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aRelative")));
                ind.setQ621a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aHCWorker")));
                ind.setQ621a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aCoWorker")));
                ind.setQ621a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621aOther")));
                ind.setQ621b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621b")));
                ind.setQ621bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q621bOther")));
                ind.setQ622(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622")));
                ind.setQ622a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622a")));
                ind.setQ622aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622aOther")));
                ind.setQ622b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622b")));
                ind.setQ622bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q622bOther")));
                ind.setQ623(cursor2.getString(cursor2.getColumnIndexOrThrow("Q623")));
                ind.setQ624(cursor2.getString(cursor2.getColumnIndexOrThrow("Q624")));
                ind.setQ625(cursor2.getString(cursor2.getColumnIndexOrThrow("Q625")));
                ind.setQ701(cursor2.getString(cursor2.getColumnIndexOrThrow("Q701")));
                ind.setQ702(cursor2.getString(cursor2.getColumnIndexOrThrow("Q702")));
                ind.setQ703(cursor2.getString(cursor2.getColumnIndexOrThrow("Q703")));
                ind.setQ704(cursor2.getString(cursor2.getColumnIndexOrThrow("Q704")));
                ind.setQ705(cursor2.getString(cursor2.getColumnIndexOrThrow("Q705")));
                ind.setQ801(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801")));
                ind.setQ801a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801a")));
                ind.setQ801b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801b")));
                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")) != null) {
                    ind.setQ801cMonth(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(0, 2));
                    ind.setQ801cYear(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801c")).substring(2, 4));
                }

                ind.setQ801d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801d")));
                ind.setQ801dOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801dOther")));

                ind.setQ801e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801e")));
                ind.setQ801eOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801eOther")));


                ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));

                ind.setQ802(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802")));
                ind.setQ802a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802a")));
                ind.setQ802aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q802aOther")));


                ind.setQ803(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803")));
                ind.setQ803Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q803Other")));

                ind.setQ804(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804")));
                ind.setQ804Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q804Other")));

                ind.setQ901(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901")));
                ind.setQ901a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901a")));
                ind.setQ901aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q901aOther")));

                ind.setQ902Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(0, 2));
                ind.setQ902Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q902")).substring(2, 6));

                ind.setQ903a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903DenyCare")));
                ind.setQ903b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903Gossip")));
                ind.setQ903c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoSex")));
                ind.setQ903d(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903VerbalAbuse")));
                ind.setQ903e(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903PhysicalAbuse")));
                ind.setQ903f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903NoContact")));
                ind.setQ903g(cursor2.getString(cursor2.getColumnIndexOrThrow("Q903SharingStatus")));


                ind.setQ904(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904")));
                ind.setQ904a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904a")));
                ind.setQ904aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904aOther")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")) != null) {
                    ind.setQ904bMM(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(0, 2) + cursor2.getString(cursor2.getColumnIndexOrThrow("Q904b")).substring(2, 6));
                }


                ind.setQ904c(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904c")));
                ind.setQ904cOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q904cOther")));


                ind.setQ905(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905")));
                ind.setQ905a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905a")));
                ind.setQ905aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q905aOther")));

                ind.setQ1001(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1001")));
                ind.setQ1002(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002")));

                ind.setQ1002a_1(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMCondom")));
                ind.setQ1002a_2(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFCondom")));
                ind.setQ1002a_3(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aInjectContra")));
                ind.setQ1002a_4(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOralContra")));
                ind.setQ1002a_5(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aUID")));
                ind.setQ1002a_6(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aBTL")));
                ind.setQ1002a_7(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aFSterilization")));
                ind.setQ1002a_8(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aMSterilization")));
                ind.setQ1002a_10(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aImplants")));
                ind.setQ1002a_11(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aEContra")));
                ind.setQ1002a_12(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSafePeriod")));
                ind.setQ1002a_13(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aLAM")));
                ind.setQ1002a_14(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aDiagraphm")));
                ind.setQ1002a_15(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpermicides")));
                ind.setQ1002a_16(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aNatural")));
                ind.setQ1002a_17(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aTraditional")));
                ind.setQ1002a_18(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aSpiritual")));
                ind.setQ1002a_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002aOther")));

                ind.setQ1002b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002b")));
                ind.setQ1002bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1002bOther")));

                ind.setQ1003(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1003")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 8) {
                    ind.setQ1004_Day(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(0, 2));
                    ind.setQ1004_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(2, 4));
                    ind.setQ1004_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).substring(4, 8));
                }
else
                {
                    if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")) != null && cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004")).length() == 4)
                    {

                        ind.setQ1004_Day("00");
                        ind.setQ1004_Month("00");
                        ind.setQ1004_Year("0000");
                    }
                }

                ind.setQ1004a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004a")));
                ind.setQ1004b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004b")));
                ind.setQ1004bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1004bOther")));


                ind.setQ1005(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005")));
                ind.setQ1005a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1005a")));
                ind.setQ1006(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1006")));

                ind.setQ1007(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007")));
                ind.setQ1007a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1007a")));

                ind.setQ1008(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008")));
                ind.setQ1008a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008a")));
                ind.setQ1008aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1008aOther")));


                ind.setQ1009(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009")));
                ind.setQ1009a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1009a")));

                ind.setQ1010(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010")));
                ind.setQ1010Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1010Other")));

                ind.setQ1011(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011")));
                ind.setQ1011_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1011Other")));

                if (cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")) != null) {
                    ind.setQ1012_Week(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(0, 2));
                    ind.setQ1012_Month(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(2, 4));
                    ind.setQ1012_Year(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012")).substring(4, 6));
                }
                ind.setQ1012Still(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1012Still")));


                ind.setQ1013(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1013")));
                ind.setQ1014(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014")));
                ind.setQ1014a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014a")));
                ind.setQ1014b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1014b")));

                ind.setQ1015(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015")));
                ind.setQ1015a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015a")));
                ind.setQ1015b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1015b")));
                ind.setQ1016(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1016")));
                ind.setQ1017(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1017")));


                ind.setQ1101(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101")));
                ind.setQ1101a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101a")));
                ind.setQ1101aOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1101aOther")));
                ind.setQ1102(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102")));
                ind.setQ1102a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1102a")));
                ind.setQ1103(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103")));

                ind.setQ1103aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDD")));
                ind.setQ1103aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aWks")));


                ind.setQ1103aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1103aDontKnow")));

                ind.setQ1104(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1104")));
                ind.setQ1105(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1105")));
                ind.setQ1106(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106")));
                ind.setQ1106a(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106a")));
                ind.setQ1106b(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106b")));
                ind.setQ1106bOther(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1106bOther")));
                ind.setQ1107(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107")));
                ind.setQ1107aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDD")));
                ind.setQ1107aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aWks")));
                ind.setQ1107aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1107aDontKnow")));
                ind.setQ1108(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108")));
                ind.setQ1108aDD(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDD")));
                ind.setQ1108aWks(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aWks")));
                ind.setQ1108aDontKnow(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1108aDontKnow")));
                ind.setQ1109(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1109")));
                ind.setQ1110(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1110")));
                ind.setQ1111(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111")));
                ind.setQ1111Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1111Other")));
                ind.setQ1112(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112")));
                ind.setQ1112_Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1112Other")));
                ind.setQ1113(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113")));
                ind.setQ1113Other(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1113Other")));
                ind.setQ1114(cursor2.getString(cursor2.getColumnIndexOrThrow("Q1114")));

                ind.setIndvQuestionnairePConsent15_17(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnairePConsent15_17")));
                ind.setIndvQuestionnaireConsent(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvQuestionnaireConsent")));
                ind.setIndvBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("IndvBloodDraw")));
                ind.setBloodVol_1(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_1")));
                ind.setBloodVol_4(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_4")));
                ind.setBloodVol_6(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_6")));
                ind.setBloodVol_10(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVol_10")));
                ind.setBloodVolComment(cursor2.getString(cursor2.getColumnIndexOrThrow("BloodVolComment")));
                ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_O15_Rapid")));
                ind.setIndRapidResults(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
                ind.setIndBloodLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodLabTest")));
                ind.setIndBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodStore")));
                ind.setIndRapidDate(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidDate")));
                ind.setIndBloodSampleCollected(cursor2.getString(cursor2.getColumnIndexOrThrow("IndBloodSampleCollected")));
                ind.setPrntlConsentBloodDraw(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodDraw")));
                ind.setPrntlConsentRHT(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentRHT")));
                ind.setPrntlConsentLabTest(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentLabTest")));
                ind.setPrntlConsentBloodStore(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentBloodStore")));
                ind.setPrntlParentID(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlParentID")));
                ind.setPrntlConsentDate(cursor2.getString(cursor2.getColumnIndexOrThrow("PrntlConsentDate")));

                ind.setVISIT1(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT1")));
                ind.setVISIT2(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT2")));
                ind.setVISIT3(cursor2.getString(cursor2.getColumnIndexOrThrow("VISIT3")));
                ind.setDATE1(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE1")));
                ind.setDATE2(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE2")));
                ind.setDATE3(cursor2.getString(cursor2.getColumnIndexOrThrow("DATE3")));
                ind.setSync(cursor2.getString(cursor2.getColumnIndexOrThrow("Sync")));

                // ind.setB8_Yes_No(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_Yes_No")));
                // ind.setB8_Date(cursor2.getString(cursor2.getColumnIndexOrThrow("B8_Date")));
                ind.setB8_O15_Rapid(cursor2.getString(cursor2.getColumnIndexOrThrow("IndRapidResults")));
                //ind.setQ801f(cursor2.getString(cursor2.getColumnIndexOrThrow("Q801f")));
                // ind.setIndRapid_Comment(cursor2.getString(cursor2.getColumnIndexOrThrow("Rapid_Comment")));


                individuals[cursor2.getPosition()] = (ind);
            }
            //indDetails();
            // Log.d("Individual: ",String.valueOf(indDetails.get(i).getIndividual().length));
        }

        return indDetails;
    }

    //UPDATE HOUSEHOLD COLUMNS EACH
    public void updateConsents(String columnName, String Assignment_ID, String Batch_no, String value, String srno) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        hhValues.put(columnName, value);

        int i = db1.update
                (tblhhroster, // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                        new String[]{Assignment_ID, Batch_no, srno}
                );



    }


    //UPDATE HOUSEHOLD COLUMNS EACH
    public void updateInd(String columnName, String Assignment_ID, String Batch_no, String value, String srno) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        hhValues.put(columnName, value);

        int i = db1.update
                (tblindividual, // table
                        hhValues, // column/value
                        "Assignment_ID = ? and BatchNumber = ? and SRNO=?", // selections
                        new String[]{Assignment_ID, Batch_no, srno}
                );



    }

    /*****************GET HOUSEHOULD assignment COLUMN NUMBERS*******************/
    public String getValue(String colname, String Assignment_ID, String Batch_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + colname + " from " + tblhousehold + " where EA_Assignment_ID=" + Assignment_ID + " and BatchNumber=" + Batch_no, null);

        StringBuffer stringBuffer = new StringBuffer();
        String Value = null;
        while (cursor.moveToNext()) {
            Value = cursor.getString(cursor.getColumnIndexOrThrow(colname));
        }
        cursor.close();
        db.close();
        return Value;
    }


    public void gethhBarcode(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM " + tblhhroster, null);

        if(cur.getCount() != 0){
            cur.moveToFirst();

            do{
                String row_values = "";

                for(int i = 0 ; i < cur.getColumnCount(); i++){
                    row_values = row_values + " || " + cur.getString(i);
                }

               // Log.d("LOG_TAG_HERE", row_values);

            }while (cur.moveToNext());
        }
    }



    public ArrayList<PersonRoster> getAllHhProster() {

        ArrayList<PersonRoster> list = new ArrayList<PersonRoster>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + tblhhroster;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        PersonRoster obj = new PersonRoster();
                        //only one column
                        obj.setBarcode(cursor.getString(0));

                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        return list;
    }

    /*****************GET ROSTER COLUMN NUMBERS*******************/
    public String getValue1(String colname, String Assignment_ID, String Batch_no, int srno) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select " + colname + " from " + tblhhroster + " where EA_Assignment_ID=" + Assignment_ID + " and BatchNumber=" + Batch_no+" and SRNO="+srno, null);

        StringBuffer stringBuffer = new StringBuffer();
        String Value = null;
        while (cursor.moveToNext()) {
            Value = cursor.getString(cursor.getColumnIndexOrThrow(colname));
        }
        cursor.close();
        db.close();
        return Value;
    }


    public void UpdateRejectedWork(HouseHold j){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues hhValues = new ContentValues();
        hhValues.put("SuperComment",j.getSuperComment());

        if(Integer.parseInt(j.getInterview_Status())==2) {
            hhValues.put("Interview_Status", j.getInterview_Status());
        }
        hhValues.put("Clear",j.getClear());


        int i = db.update
                (   "House_Hold_Assignments", // table
                        hhValues, // column/value
                        "EA_Assignment_ID = ? and BatchNumber = ?", // selections
                        new String[]{ String.valueOf(j.getAssignment_ID()),String.valueOf(j.getBatchNumber()) }
                );


    }



    /*****************GET INDIVIDUAL COLUMN NUMBERS*******************/
    public String getValue2(String colname, String Assignment_ID, String Batch_no, int srno) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Value = null;
        Cursor cursor=null;
        try{
             cursor = db.rawQuery("select " + colname + " from " + tblindividual + " where Assignment_ID=" + Assignment_ID + " and BatchNumber=" + Batch_no+" and SRNO="+srno, null);

            StringBuffer stringBuffer = new StringBuffer();

            while (cursor.moveToNext()) {
                Value = cursor.getString(cursor.getColumnIndexOrThrow(colname));
            }
        }catch (Exception t){
            t.printStackTrace();
        }finally {
            cursor.close();
            db.close();
        }


        return Value;
    }

    public String[] getTableColumns(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dbCursor = db.query(tblhousehold, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        Log.d("TOtal columns ",columnNames.length+"");

        return columnNames;
    }


    public String[] RosterColumns(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dbCursor = db.query(tblhhroster, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        dbCursor.close();
        db.close();
        return columnNames;
    }

    public String[] IndColumns(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dbCursor = db.query(tblindividual, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        dbCursor.close();
        db.close();
        return columnNames;
    }

}



//Cursor resS = db.rawQuery("select * from " + tblAssignments + "tblhousehold.BATCHNUMBER" + "tblhousehold.ENUMERATOR" + "tblhousehold.SUPERVISOR" + "tblhousehold.QUALITY_CONTROLLER+ "+ "INNER JOIN"+"tblhousehold"+"ON"+"tblhousehold.EA_Assignment_ID = tblAssignments.EA_Assignment_ID", null);