package bw.org.statsbots.bias;

public class LoginValidator {
    private String username,password;
    public LoginValidator(String username, String password){
        this.username = username;
        this.password = password;
    }


    public String preLogin(){
        String loginMessage = "";

        //Validate
        if(Validator.isNull(this.username))
        {
            loginMessage="Please enter Username";
        }else{

            if (Validator.isNull(password))
            {
                loginMessage="Please enter Password";
            }
            else
            {
                loginMessage="";
            }

        }
        return loginMessage;
    }




}
