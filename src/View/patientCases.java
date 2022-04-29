package View;

import java.util.*;
import DBController.*;
import Model.*;

public class patientCases {
    //method having the main menu for patient login
    public static void patientOperations(String username,Scanner sc){
        do {
            System.out.println("1.CHANGE PASSWORD\n2.SCHEDULE APPOINTMENT\n3.RESCHEDULE APPOINTMENT\n4.CANCEL APPOINTMENT\n5.LOGOUT");
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    adminCases.changePassword(username,sc);
                break;

                case 2:
                    patientCases.scheduleAppointment(username,sc);
                break;

                case 3:
                    patientCases.rescheduleAppointment(sc);
                break;
                
                case 4:
                    patientCases.cancelAppointment(sc);
                break;
                
                case 5:
                    patientLogout(sc);
                break;

                default:
                break;
            }
            System.out.println("CONTINUE-->Y");
        } while (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y');
    }

    public static void scheduleAppointment(String username,Scanner sc) {
        char choice;
        do{
        System.out.println("ENTER THE SPECIALIZATION");
        String requiredSpecialization=sc.next();

        checkSpecialistAvailability(username,sc,requiredSpecialization);

        System.out.println("CONTINUE SAME OPERATION-->Y\nBACK TO OPERATIONS-->N");
        choice=sc.next().charAt(0);
        if(choice=='n'||choice=='N')
            patientOperations(username, sc);
    }while(choice=='y'||choice=='Y');

    }
    private static void checkSpecialistAvailability(String username,Scanner sc,String requiredSpecialization){
        try{
        //if required specialist(s) is/are available display their details for patient to choose
        if(DBHandler.returnNumberOfSpecialists(requiredSpecialization)>=1){
            Doctors doctor=new Doctors(0, null, requiredSpecialization);

            System.out.println("DOCTOR ID : \tDOCTOR NAME : \tDOCTOR SPECIALIZATION :");

            DBHandler.returnSpecialists(doctor);
            
            System.out.println("CHOOSE DOCTOR ID");
            int chosenDoctorID=sc.nextInt(); 

            displayAvailability(chosenDoctorID,requiredSpecialization); //check the available slots and days for the particular doctor
            
            System.out.println("CHOOSE THE DAY");
            String chosenDay=sc.next();
            System.out.println("CHOOSE THE SLOT");
            String chosenSlot=sc.next();
            System.out.println("ENTER THE PATIENT ID");
            int patientId=sc.nextInt();
            String slotStatus="booked";       

            if(DBHandler.statusUpdate(slotStatus,chosenDay,chosenSlot,chosenDoctorID)==1){
                System.out.println("APPOINTMENT BOOKED");
                addAppointment(chosenDoctorID,patientId,slotStatus,chosenDay,chosenSlot);
            }
            else
                System.out.println("UNABLE ADD APPOINTMENT");
        }
        else{
                System.out.println("BOOKING FAILED");
                System.out.println("CHOOSE SPECIALIZATION --> Y");
                scheduleAppointment(username,sc);
            }
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }
    //method to display all available days and slots of a particular doctor
    private static void displayAvailability(int id,String chosenSpecialization){

        try{
        System.out.println("DOCTOR ID : \tDAY : \tAVAILABLE SLOT : ");
        String bookingStatus="unbooked";

        Doctors doctor=new Doctors(id,null, chosenSpecialization);
        DBHandler.slotdisplay(bookingStatus,doctor);
    }
    catch(Exception e){
        System.out.println("UNABLE TO DISPLAY SLOTS");
        }
    }
    private static void addAppointment(int doctor_ID,int patient_ID,String slotStatus,String chosenDay,String chosenSlot){
        try{
            if(DBHandler.addNewAppointment(doctor_ID,patient_ID, chosenSlot, chosenSlot, chosenSlot)==1){            
                System.out.println("NEW APPOINTMENT CREATED");
            }
            else{
                System.out.println("FAILED TO ADD APPOINTMENT");
            }
        }
        catch(Exception e){
            System.out.println("TRY AGAIN");
        }
    }

    public static void rescheduleAppointment(Scanner sc){
        //YET TO COMPLETE
    }

    public static void cancelAppointment(Scanner sc){
        //YET TO COMPLETE
    }

    public static void patientLogout(Scanner sc){
        System.out.println("LOGGED OUT SUCCESSFULLY");
        System.out.println("LOGIN AGAIN --> 'L' GO TO MAIN MENU --> 'M'");
        char loop=sc.next().charAt(0);
        if(loop=='L'||loop=='l')
            Basic.patientLogin();
        else
            App.displayMainMenu(sc);
    }
}
