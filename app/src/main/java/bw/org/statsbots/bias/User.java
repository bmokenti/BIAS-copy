package bw.org.statsbots.bias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String password ;
    private String Name ;
    private String SName ;
    private String Contact ;
    private String QC_Code ;
    private String Super_Code;
    private String Role;
    private String HQ_Code ;
    @JsonProperty("bw.org.statsbots.bias.code")
    private String Code ;
    private String Is_Active;


    public String getIs_Active() {
        return Is_Active;
    }

    public void setIs_Active(String is_Active) {
        Is_Active = is_Active;
    }


    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getQC_Code() {
        return QC_Code;
    }

    public void setQC_Code(String QC_Code) {
        this.QC_Code = QC_Code;
    }

    public String getSuper_Code() {
        return Super_Code;
    }

    public void setSuper_Code(String super_Code) {
        Super_Code = super_Code;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getHQ_Code() {
        return HQ_Code;
    }

    public void setHQ_Code(String HQ_Code) {
        this.HQ_Code = HQ_Code;
    }



}
