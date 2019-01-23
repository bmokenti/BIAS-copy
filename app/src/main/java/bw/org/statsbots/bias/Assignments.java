package bw.org.statsbots.bias;

public class Assignments {

    private  String stratum_code;
    private String district_code;
    private  String village_code;
    private  String EA_code;
    private  String locality_code;
    private  String block_number;
    private String Assignment_ID;
    private String EA_Status;
    private  String Sample_FK;
    private  String Supervisor;
    private  String ROUND_NUMBER;

    public String getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(String supervisor) {
        Supervisor = supervisor;
    }

    public String getROUND_NUMBER() {
        return ROUND_NUMBER;
    }

    public void setROUND_NUMBER(String ROUND_NUMBER) {
        this.ROUND_NUMBER = ROUND_NUMBER;
    }



    public String getStratum_code() {
        return stratum_code;
    }

    public void setStratum_code(String stratum_code) {
        this.stratum_code = stratum_code;
    }



    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }


    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    public String getEA_code() {
        return EA_code;
    }

    public void setEA_code(String EA_code) {
        this.EA_code = EA_code;
    }


    public String getLocality_code() {
        return locality_code;
    }

    public void setLocality_code(String locality_code) {
        this.locality_code = locality_code;
    }

    public String getBlock_number() {
        return block_number;
    }

    public void setBlock_number(String block_number) {
        this.block_number = block_number;
    }
    public String getLOCALITY_NO() {
        return locality_code;
    }

    public void setLOCALITY_NO(String Locality_code) {
        this.locality_code = Locality_code;
    }
    public String getAssignment_ID() {
        return Assignment_ID;
    }

    public void setAssignment_ID(String assignment_ID) {
        Assignment_ID = assignment_ID;
    }


    public String getDISTRCIT_NO() {
        return district_code;
    }

    public void setDISTRCIT_NO(String DISTRCIT_NO) {
        this.district_code = DISTRCIT_NO;
    }
    public String getEA_Status() {
        return EA_Status;
    }

    public void setEA_Status(String EA_Status) {
        this.EA_Status = EA_Status;
    }

    public String getSample_FK() {
        return Sample_FK;
    }

    public void setSample_FK(String sample_FK) {
        Sample_FK = sample_FK;
    }

}
