package View;
import java.util.*;
import Model.*;
import DBController.*;

public class adminCases {
    //method having the main menu of the admin login
    public static void adminOperations(String username,Scanner sc){
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
    protected static void addUser(String username,Scanner sc){
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
    protected static void addDoctor(Scanner sc){
        try{
        System.out.println("NAME--> ");
        String new_name=sc.next();

        System.out.println("SPECIALIZATION--> ");
        String doctorSpecialization=sc.next(); 
        
        if(DBHandler.doctorInsertion(new_name,doctorSpecialization)==1)
            System.out.println("Dr."+new_name+" "+doctorSpecialization+"ADDED ");
        else
            System.out.println("FAILED TO ADD Dr."+new_name+" "+doctorSpecialization);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    protected static void addPatient(Scanner sc){
        try{
        System.out.println("NAME--> ");
        String newName=sc.next();
        if(DBHandler.patientInsertion(newName)==1)
            System.out.println("NEW PATIENT "+newName+" ADDED");
        else
            System.out.println("FAILED TO ADD NEW PATIENT "+newName);

        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    protected static void removeUser(String username,Scanner sc){
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

    protected static void removeDoctor(int id){
        try{
        if(DBHandler.doctorDeletion(id)==1)
            System.out.println("DOCTOR "+id+" IS REMOVED");
        else
            System.out.println("FAILED TO REMOVE DOCTOR "+id);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    protected static void removePatient(int id){
        try{
            if(DBHandler.patientDeletion(id)==1)        
            System.out.println("PATIENT "+id+" IS REMOVED");
        else
            System.out.println("FAILED TO REMOVE DOCTOR "+id);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    protected static void updateUser(String username,Scanner sc){
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
    private static void updateDoctorName (int id,Scanner sc){
        try{
        System.out.println("ENTER NEW NAME TO UPDATE");
        String newName=sc.next();
        if(DBHandler.updateNameOfDoctor(id,newName)==1)
            System.out.println("UPDATED DOCTOR "+id+ "'s NAME TO"+newName);
        else
            System.out.println("FAILED TO UPDATE DOCTOR "+id+ "'s NAME TO"+newName);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    private static void updateDoctorSpecialization(int id,Scanner sc){
        try{
        System.out.println("ENTER SPECIALIZATION");
        String newSpecialization=sc.next();
        if(DBHandler.updateSpecializationOfDoctor(id, newSpecialization)==1)
            System.out.println("UPDATED DOCTOR "+id+ "'s SPECIALIZATION TO"+newSpecialization);
        else
            System.out.println("FAILED TO UPDATE DOCTOR "+id+ "'s SPECIALIZATION TO"+newSpecialization);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
     
        }
    }
    private static void updatePatientName(int id,Scanner sc){
        try{
        System.out.println("ENTER NEW NAME TO UPDATE");
        String newName=sc.next();
        if(DBHandler.updateNameOfPatient(id,newName)==1)
            System.out.println("UPDATED PATIENT "+id+ "'s NAME TO"+newName);
        else
            System.out.println("FAILED TO UPDATE PATIENT "+id+ "'s NAME TO"+newName);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    protected static void viewUser(String username,Scanner sc){
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
    protected static void viewDoctor(int id){
        try{
        Doctors doctor=new Doctors(id, null, null);
        DBHandler.displayDoctor(doctor);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    protected static void viewPatient(int id){
        try{
        Patients patient=new Patients(id, null,0);
        DBHandler.displayPatient(patient);
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }        
    }
    protected static void viewDoctors(){
        System.out.println("-------------------------------------------------------------\nDOCTOR ID : \tDOCTOR NAME : \tDOCTOR SPECIALIZATION :\n-------------------------------------------------------------");
        DBHandler.displayAllDoctors();
    }
    protected static void viewPatients(){
        System.out.println("-------------------------------------------------------------\nPATIENT ID : \tPATIENT NAME: \tNEXT APPOINTMENT ID :\n-------------------------------------------------------------");
        DBHandler.displayAllPatients();
    }
    protected static void changePassword(String username,Scanner sc){
        try{
        System.out.println("ENTER A NEW PASSWORD");
        String newPassword=sc.next();

        if(DBHandler.changeOfPassword(username, newPassword)==1)
            System.out.println("PASSWORD FOR USERNAME "+username+" IS UPDATED");
        else
            System.out.println("PASSWORD FOR USERNAME "+username+" IS NOT UPDATED");
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }   
    protected static void adminLogout(Scanner sc){
        System.out.println("LOGGED OUT SUCCESSFULLY");
        System.out.println("LOGIN OR REGISTER --> 'L' GO TO MAIN MENU --> 'M'");
        char loop=sc.next().charAt(0);
        if(loop=='L'||loop=='l')
            App.signInOrSignUp();
        else
            App.displayMainMenu(sc);
    }
}
