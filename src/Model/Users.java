package Model;

public class Users {
    //user object attributes
    public String userName;
    public String passWord;
    public String userType;

    //constructor to instantiate the object once and set values to its attributes
    public Users(String userName,String passWord,String userType){
        this.userName=userName;
        this.passWord=passWord;
        this.userType=userType;
   }
    
}