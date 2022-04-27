package View;
import Model.*;
import DBController.*;

import java.sql.*;
import java.util.*;

public class Basic extends adminCases{ //Basic class extends the adminCases class
    
    public static void adminLogin() throws Exception{
        DBInitializer.createNewConnection(); //establish connection
        Scanner sc=new Scanner(System.in);

        System.out.println("ENTER USERNAME");
        String usernameInput=sc.nextLine(); //input username from the admin

        System.out.println("ENTER PASSWORD");
        String passwordInput=sc.nextLine(); //input password from the admin

        String sql =DBQuery.adminCheck(usernameInput,passwordInput); //returns query to validate input username and password

        ResultSet rs=DBInitializer.s.executeQuery(sql); 
        rs.next();
        int check=rs.getInt("C"); //returns count of records with the given username and password

        if(check==1){ //corresponding username and password exists
            System.out.println("LOGGED IN SUCCESSFULLY");
            adminCases.adminOperations(user.userName,sc); //choose from the list of admin operations
        }
        else{
            System.out.println("INVALID LOGIN"); 
            App.signInOrSignUp(); //try logging in again or register new user //create credentials alone
        }
        sc.close();
    }
    public static void doctorLogin() throws Exception {
        DBInitializer.createNewConnection(); //establish connection
        Scanner sc = new Scanner(System.in);

        System.out.println("ENTER USERNAME");
        String usernameInput= sc.next(); //get username as input from the doctor

        System.out.println("ENTER PASSWORD");
        String passwordInput = sc.next(); //get password as input from the doctor
        
        Users user=new Users(usernameInput,passwordInput,"doctor");
        String sql=DBQuery.doctorCheck(user);

        ResultSet rs = DBInitializer.s.executeQuery(sql);
        rs.next();
        int check = rs.getInt("C");

        if (check == 1)
        {
            System.out.println("LOGGED IN SUCCESSFULLY");
            doctorCases.doctorOperations(user.userName,sc);
        }
        else {
            System.out.println("INVALID LOGIN");
            doctorLogin();
        }
        sc.close();
    }
    public static void patientLogin() throws Exception {
        DBInitializer.createNewConnection();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("ENTER USERNAME");
        String usernameInput= sc.next();

        System.out.println("ENTER PASSWORD");
        String passwordInput = sc.next();

        Users user=new Users(usernameInput,passwordInput,"patient");
        
        String sql=DBQuery.patientCheck(user);
        ResultSet rs = DBInitializer.s.executeQuery(sql);
        rs.next();
        int check = rs.getInt("C");

        if (check == 1)
        {
            System.out.println("LOGGED IN SUCCESSFULLY");
            patientCases.patientOperations(user.userName,sc);
        }

        else {
            System.out.println("INVALID LOGIN");
            patientLogin();
        }
        sc.close();
    }

    public static void register(String str) throws Exception {

        DBInitializer.createNewConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER A USERNAME");
        String usernameInput = sc.nextLine();

        System.out.println("ENTER A DEFAULT PASSWORD");
        String passwordInput = sc.nextLine();
        
        Users user=new Users(usernameInput,passwordInput,str);

        String sql=DBQuery.registerUser(user);
        int i = DBInitializer.s.executeUpdate(sql);
        if (i == 1) {
            System.out.println("REGISTERED SUCCESSFULLY\nLOGIN NOW -- >");
            if("admin".equals(user.userType))
                Basic.adminLogin();
            else if("doctor".equals(user.userType))
                Basic.doctorLogin();
            else
                Basic.patientLogin();
        }
        sc.close(); 
    }
}
