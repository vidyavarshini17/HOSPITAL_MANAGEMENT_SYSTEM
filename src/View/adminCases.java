package View;
import java.util.*;
import Model.*;
import DBController.*;

import java.sql.*;

public class adminCases {
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
                adminLogout(sc);
                break;
            
                default:
                break;
            }
            System.out.println("CONTINUE-->Y");
        }while(sc.next().charAt(0)=='y'||sc.next().charAt(0)=='Y');
    }
    protected static void addUser(String username,Scanner sc) throws Exception{
        char choice;
        do{
            System.out.println("1.DOCTOR\n2.PATIENT");
            int a=sc.nextInt();
            switch(a){
                case 1:
                    addDoctor(sc); //call method to add doctor
                break;

                case 2:
                    addPatient(sc); //call method to add patient
                break;

                default:
                break;
            }
            System.out.println("CONTINUE SAME OPERATION-->Y\nBACK TO OPERATIONS-->N");
            choice=sc.next().charAt(0);
            if(choice=='n'||choice=='N')
                Basic.adminOperations(username,sc); //go back to main menu of admin login

        }while(choice=='y'||choice=='Y'); //loop to perform same operation
    }
    protected static void addDoctor(Scanner sc) throws SQLException{

        System.out.println("NAME--> ");
        String new_name=sc.next();

        System.out.println("SPECIALIZATION--> ");
        String doctorSpecialization=sc.next(); 

        Doctors doctor=new Doctors(0, new_name, doctorSpecialization);

        //call method having query to add doctor to the database and store query in the string sql
        String sql=DBQuery.addDoctorQuery(doctor);

        int insertionCheck=DBInitializer.s.executeUpdate(sql);
        
        if(insertionCheck==1)
            System.out.println("DATA INSERTED");
        else
            System.out.println("DATA INSERTION FAILED");
    }
    protected static void addPatient(Scanner sc) throws SQLException{

        System.out.println("NAME--> ");
        String newName=sc.next();

        Patients patient=new Patients(0,newName,0);
        //call method having query to add patient into the database and store the query in string sql
        String sql=DBQuery.addPatientQuery(patient);

        int insertionCheck=DBInitializer.s.executeUpdate(sql);

        if(insertionCheck==1)
            System.out.println("DATA INSERTED");
        else
            System.out.println("DATA INSERTION FAILED");
    }
    protected static void removeUser(String username,Scanner sc) throws Exception{
        char choice;
        do{
            System.out.println("1.DOCTOR\n2.PATIENT");
            int r=sc.nextInt();
            System.out.println("ENTER ID TO DELETE");
            int id=sc.nextInt();
            
            switch(r){
                case 1:
                    removeDoctor(id); //remove a particular doctor using doctorID
                break;

                case 2:
                    removePatient(id); //remove a particular patient using patientID
                break;

                default:
                break;
            
            }
            System.out.println("CONTINUE SAME OPERATION-->Y\nBACK TO OPERATIONS-->N");
            choice=sc.next().charAt(0);
            if(choice=='n'||choice=='N')
                adminOperations(username,sc); //returns to the main menu of the admin login

        }while(choice=='y'||choice=='Y'); //loop to perform the same operation again
    }

    protected static void removeDoctor(int id) throws SQLException{

        Doctors doctor=new Doctors(id,null,null);
        //instantiate doctor object and set values to its attributes
        String sql=DBQuery.removeDoctorQuery(doctor);
    
        int deletionCheck=DBInitializer.s.executeUpdate(sql);

        if(deletionCheck==1)
            System.out.println("DATA DELETED SUCCESSFULLY");
        else
            System.out.println("DATA DELETION FAILED");

    }
    protected static void removePatient(int id) throws SQLException{

        Patients patient=new Patients(id,null, 0);
        //instantiate patient object and set values to its attributes
        String sql=DBQuery.removePatientQuery(patient);
        int deletionCheck=DBInitializer.s.executeUpdate(sql);

        if(deletionCheck==1)
            System.out.println("DATA DELETED SUCCESSFULLY");
        else
            System.out.println("DATA DELETION FAILED");

    }
    protected static void updateUser(String username,Scanner sc) throws Exception{
        char choice;
        do{
            System.out.println("--------------------------\n1.UPDATE DOCTOR NAME\n2.UPDATE DOCTOR SPECIALIZATION\n3.UPDATE PATIENT NAME\n--------------------------");
            int u=sc.nextInt();
            System.out.println("ENTER THE ID");
            int id=sc.nextInt();
            switch(u){
                case 1:
                updateDoctorName(id,sc);
                break;

                case 2:
                updateDoctorSpecialization(id,sc);
                break;

                case 3:
                updatePatientName(id,sc);
                break;

                default:
                break;
            }
            System.out.println("CONTINUE SAME OPERATION-->Y\nBACK TO OPERATIONS-->N");
            choice=sc.next().charAt(0);
            if(choice=='n'||choice=='N')
                Basic.adminOperations(username,sc); //go back to main menu of admin login

        }while(choice=='y'||choice=='Y'); //loop to perform the same operation
    }
    private static void updateDoctorName (int id,Scanner sc) throws SQLException{
        System.out.println("ENTER NEW NAME TO UPDATE");
        String newName=sc.next();

        Doctors doctor=new Doctors(id,newName,null);
        String sql=DBQuery.updateDoctorNameQuery(doctor);

        int check=DBInitializer.s.executeUpdate(sql);

        if(check==1)
            System.out.println("DATA IS UPDATED SUCCESSFULLY");
    }
    private static void updateDoctorSpecialization(int id,Scanner sc) throws SQLException{

        System.out.println("ENTER SPECIALIZATION");
        String newSpecialization=sc.next();
        
        Doctors doctor=new Doctors(id,null,newSpecialization);
        String sql=DBQuery.updateDoctorSpecializationQuery(doctor);

        int check=DBInitializer.s.executeUpdate(sql);
        if(check==1)
            System.out.println("DATA UPDATED SUCCESSFULLY");
    }
    private static void updatePatientName(int id,Scanner sc) throws SQLException{
        System.out.println("ENTER NEW NAME TO UPDATE");
        String newName1=sc.next();

        Patients patient=new Patients(id, newName1,0);
        String sql=DBQuery.updatePatientNameQuery(patient);
            
        int check=DBInitializer.s.executeUpdate(sql);
        if(check==1)
            System.out.println("DATA UPDATED SUCCESSFULLY");
}
    protected static void viewUser(String username,Scanner sc) throws Exception{
        char choice;
        do{
            System.out.println("1.DOCTOR\n2.PATIENT");
            int n=sc.nextInt();
            System.out.println("ENTER ID TO VIEW");
            int id=sc.nextInt();

            switch(n){
                case 1:
                    viewDoctor(id); //view particular doctor using doctorID
                break;
                    
                case 2:
                    viewPatient(id); //view particular patient using patientID
                break;

                default:
                break;
            }
            System.out.println("CONTINUE SAME OPERATION-->Y\nBACK TO OPERATIONS-->N");
            choice=sc.next().charAt(0);
            if(choice=='n'||choice=='N')
                Basic.adminOperations(username,sc); //go back to the main menu of the admin login

        }while(choice=='y'||choice=='Y');
    }
    protected static void viewDoctor(int id) throws SQLException{
        Doctors doctor=new Doctors(id,null,null);
        //instantiate doctor object and pass to the method with the query
        String sql=DBQuery.viewDoctorQuery(doctor);

        ResultSet rs=DBInitializer.s.executeQuery(sql);
        rs.next();
        System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
    }
    protected static void viewPatient(int id) throws SQLException{
        Patients patient=new Patients(id, null,0);
        //instantiate patient object and pass to the method with the query
        String sql=DBQuery.viewPatientQuery(patient);

        ResultSet rs=DBInitializer.s.executeQuery(sql);
        rs.next();
        System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
    }
    protected static void viewDoctors() throws SQLException{
        System.out.println("-------------------------------------------------------------\nDOCTOR ID : \tDOCTOR NAME : \tDOCTOR SPECIALIZATION :\n-------------------------------------------------------------");

        String sql=DBQuery.viewAllDoctorsQuery();
        ResultSet rs=DBInitializer.s.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
        }
    }
    protected static void viewPatients() throws SQLException{
        System.out.println("-------------------------------------------------------------\nPATIENT ID : \tPATIENT NAME: \tNEXT APPOINTMENT ID :\n-------------------------------------------------------------");
        
        String sql=DBQuery.viewAllPatientsQuery();
        ResultSet rs=DBInitializer.s.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
        }
    }
    protected static void changePassword(String username,Scanner sc) throws SQLException{
        System.out.println("ENTER A NEW PASSWORD");
        String newPassword=sc.next();

        Users user=new Users(username,newPassword,null);
        //instantiate new user object and pass it to the method with the corresponding query

        String sql=DBQuery.changePasswordQuery(user);
        
        int x=DBInitializer.s.executeUpdate(sql);
        if(x==1)
            System.out.println("PASSWORD UPDATED");
        else
            System.out.println("PASSWORD UPDATION FAILED");
    }   
    protected static void adminLogout(Scanner sc) throws Exception{
        System.out.println("LOGGED OUT SUCCESSFULLY");
        System.out.println("LOGIN OR REGISTER --> 'L' GO TO MAIN MENU --> 'M'");
        char loop=sc.next().charAt(0);
        if(loop=='L'||loop=='l')
            App.signInOrSignUp();
        else
            App.displayMainMenu(sc);
    }
}
