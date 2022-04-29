package View;
import DBController.*;

import java.util.*;

public class Basic extends adminCases{ //Basic class extends the adminCases class
    
    public static void adminLogin(){
        try{
        Scanner sc=new Scanner(System.in);

        System.out.println("ENTER USERNAME");
        String usernameInput=sc.nextLine(); //input username from the admin

        System.out.println("ENTER PASSWORD");
        String passwordInput=sc.nextLine(); //input password from the admin

        if(DBHandler.checkAdminValidity(usernameInput,passwordInput,"admin")==1){
            System.out.println("LOGGED IN SUCCESSFULLY");
            adminCases.adminOperations(usernameInput,sc); //choose from the list of admin operations
        }
        else{
            System.out.println("INVALID LOGIN"); 
            App.signInOrSignUp(); //try logging in again or register new user //create credentials alone
        }
        sc.close();
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    public static void doctorLogin(){
        try{
        Scanner sc = new Scanner(System.in);

        System.out.println("ENTER USERNAME");
        String usernameInput= sc.next(); //get username as input from the doctor

        System.out.println("ENTER PASSWORD");
        String passwordInput = sc.next(); //get password as input from the doctor

        if (DBHandler.checkDoctorValidity(usernameInput, passwordInput,"doctor")==1)
        {
            System.out.println("LOGGED IN SUCCESSFULLY");
            doctorCases.doctorOperations(usernameInput,sc);
        }
        else{
            System.out.println("INVALID LOGIN");
            doctorLogin();
        }
        sc.close();
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }

    }
    public static void patientLogin(){
        try{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("ENTER USERNAME");
        String usernameInput= sc.next();

        System.out.println("ENTER PASSWORD");
        String passwordInput = sc.next();

        if (DBHandler.checkPatientValidity(usernameInput, passwordInput,"patient") == 1)
        {
            System.out.println("LOGGED IN SUCCESSFULLY");
            patientCases.patientOperations(usernameInput,sc);
        }

        else {
            System.out.println("INVALID LOGIN");
            patientLogin();
        }
        sc.close();
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }

    }

    public static void register(String str){
        try{
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER A USERNAME");
        String usernameInput = sc.nextLine();

        System.out.println("ENTER A DEFAULT PASSWORD");
        String passwordInput = sc.nextLine();
        
        if (DBHandler.userRegistration(usernameInput, passwordInput, str)==1){
            System.out.println("REGISTERED SUCCESSFULLY\nLOGIN NOW -- >");
            if("admin".equals(str))
                Basic.adminLogin();
            else if("doctor".equals(str))
                Basic.doctorLogin();
            else
                Basic.patientLogin();
        }
        else
            System.out.println("USER "+usernameInput+" NOT REGISTERED ... TRY AGAIN");
        sc.close();
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
}
