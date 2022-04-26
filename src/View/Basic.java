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

        Users user=new Users(usernameInput,passwordInput,"admin");
        //instantiate user object and set the values to its attributes

        String sql =DBQuery.adminCheck(user); //returns query to validate input username and password

        ResultSet rs=DBInitializer.s.executeQuery(sql); 
        rs.next();
        int check=rs.getInt("C"); //returns count of records with the given username and password

        if(check==1){ //corresponding username and password exists
            System.out.println("LOGGED IN SUCCESSFULLY");
            Basic.adminOperations(user.userName,sc); //choose from the list of admin operations
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
            //doctorOperations(user.userName,sc);
        }
        else {
            System.out.println("INVALID LOGIN");
            Basic.doctorLogin();
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
            //patientOperations(user.userName,sc);
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
    //method having the main menu of the admin login
    public static void adminOperations(String username,Scanner sc) throws Exception{
        do{
            System.out.println("------------------\n1.CHANGE PASSWORD\n2.ADD USER\n3.REMOVE USER\n4.UPDATE USER\n5.VIEW USER\n6.VIEW PATIENTS\n7.VIEW DOCTORS\n8.LOGOUT\n------------------");
            int action=sc.nextInt();
            switch(action){
                case 1:
                changePassword(username,sc);
                break;

                case 2:
                addUser(username,sc);
                break;

                case 3:
                removeUser(username,sc);
                break;

                case 4:
                updateUser(username,sc);
                break;

                case 5:
                viewUser(username,sc);
                break;

                case 6:
                viewPatients();
                break;

                case 7:
                viewDoctors();
                break;

                case 8:
                adminLogout();
                break;
            
                default:
                break;
            }
            System.out.println("CONTINUE-->Y");
        }while(sc.next().charAt(0)=='y'||sc.next().charAt(0)=='Y');
    }
    /*public static void patientOperations(String username,Scanner sc) throws Exception{
        do {
            System.out.println(
                    "1.CHANGE PASSWORD\n2.CANCEL APPOINTMENT\n3.RESCHEDULE APPOINTMENT\n4.VIEW APPOINTMENTS\n5.UPDATE AVAILABILITY");
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    changePassword(username);
                break;

                case 2:
                    scheduleAppointment(username);
                break;

                
                case 3:
                    cancelAppointment(username);
                break;

                case 4:
                    rescheduleAppointment(username);
                break;

                default:
                break;
            }
            System.out.println("CONTINUE-->Y\nEXIT-->N");
        } while (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y');

    }
    public static void doctorOperations(String username,Scanner sc) throws Exception{
        do {
            System.out.println("1.CHANGE PASSWORD\n2.CANCEL APPOINTMENT\n3.RESCHEDULE APPOINTMENT\n4.VIEW APPOINTMENTS\n5.UPDATE AVAILABILITY");
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    changePassword(username);
                    break;

                case 2:
                    cancelAppointment();
                    break;

                case 3:
                    rescheduleAppointment();
                break;
                
                case 4:
                    viewAppointments();
                break;
                
                case 5:
                    updateAvailability();
                break;

                default:
                break;
            }
            System.out.println("CONTINUE-->Y\nEXIT-->N");
        } while (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y');
    }*/

}
