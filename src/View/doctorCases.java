package View;

import java.util.*;
import DBController.*;
import Model.*;

public class doctorCases {
    //method having main menu for doctor login
    public static void doctorOperations(String username,Scanner sc){
        do {
            System.out.println("1.CHANGE PASSWORD\n2.CANCEL APPOINTMENT\n3.RESCHEDULE APPOINTMENT\n4.VIEW APPOINTMENT\n5.VIEW ALL APPOINTMENTS\n6.ADD NEW SLOT\n7.UPDATE AVAILABILITY\n8.LOGOUT");
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    adminCases.changePassword(username,sc);
                break;

                case 2:
                    patientCases.cancelAppointment(sc);
                break;

                case 3:
                    patientCases.rescheduleAppointment(sc);
                break;

                case 4:
                    doctorCases.viewAppointment(sc);
                break;

                case 5:
                    doctorCases.viewAllAppointments();
                break;

                case 6:
                    doctorCases.addNewSlot(sc);
                break;

                case 7:
                    doctorCases.updateAvailability(username,sc);
                break;

                case 8:
                    doctorLogout(sc);
                break;

                default:
                break;
            }
            System.out.println("CONTINUE WITH SAME OPERATION-->Y");
        } while (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y');
    }
    //view the appointments of a particular doctor
    public static void viewAppointment(Scanner sc){
    try{
        System.out.println("ENTER DOCTOR ID ");
        int id=sc.nextInt();

        Doctors doctor=new Doctors(id, null,null);
        DBHandler.viewAppointmentsOfDoctor(doctor);        
    }
    catch(Exception e){
        System.out.println("UNABLE TO DISPLAY APPOINTMENTS OF DOCTOR ");
    }
    }
    //view all the appointments so far
    public static void viewAllAppointments(){
        try{
            DBHandler.viewAppointments();        
        }
        catch(Exception e){
            System.out.println("UNABLE TO VIEW APPOINTMENTS");
        }
    }
    public static void updateAvailability(String username,Scanner sc){
        try{
            System.out.println("CHOOSE AN OPTION\n1.UPDATE SLOT\n2.UPDATE DAY");
            int choice=sc.nextInt();

            System.out.println("ENTER THE DOCTOR ID");
            int id=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("ENTER THE DAY");
                    String oldDay=sc.nextLine();

                    System.out.println("ENTER OLD SLOT\n\n1.10:00am-11:00am\n2.1:00pm-2:00pm\n3.5:00pm-6:00pm");
                    int oldSlotChoice=sc.nextInt();

                    System.out.println("ENTER NEW SLOT\n\n1.10:00am-11:00am\n2.1:00pm-2:00pm\n3.5:00pm-6:00pm");
                    int newSlotChoice=sc.nextInt();

                    if(DBHandler.updateSlot(id, oldDay, oldSlotChoice, newSlotChoice)==1)
                        System.out.println("SLOT UPDATED");
                    else 
                        System.out.println("UNABLE TO UPDATE SLOT");

                break;

                case 2:
                    System.out.println("ENTER THE OLD DAY");
                    String oldDay1=sc.nextLine();

                    System.out.println("ENTER THE NEW DAY");
                    String newDay1=sc.nextLine();

                    System.out.println("ENTER CHOSEN SLOT\n1.10:00am-11:00am\n2.1:00pm-2:00pm\n3.5:00pm-6:00pm");
                    int chosenSlotChoice=sc.nextInt();

                    if(DBHandler.updateDay(id,oldDay1,newDay1,chosenSlotChoice)==1)
                        System.out.println("DAY UPDATED");
                    else
                        System.out.println("UNABLE TO UPDATE DAY");

                    default:
                        doctorOperations(username,sc);
                    break;
                }
            }
            catch(Exception e){
                System.out.println("UNABLE TO UPDATE THE AVAILABILITY");
            }
        }

    public static void addNewSlot(Scanner sc){
        try{
            System.out.println("ENTER THE DOCTOR ID");
            int id=sc.nextInt();

            System.out.println("ENTER THE DAY (type in small letters)");
            String newDay=sc.next();

            System.out.println("CHOOSE THE SLOT\n 1.10:00am-11:00am\n2.1:00pm-2:00pm\n3.5:00pm-6:00pm");
            int slotChoice=sc.nextInt();

            if(DBHandler.addNewAppointmentSlot(id,newDay,slotChoice,"unbooked")==1)
                System.out.println("NEW APPOINTMENT SLOT "+slotChoice+" ON "+newDay+" IS ADDED FOR DOCTOR "+id);
            else
                System.out.println("FAILED TO ADD NEW APPOINTMENT SLOT "+slotChoice+" ON "+newDay+" FOR DOCTOR "+id);
        }
        catch(Exception e){
            System.out.println("NEW SLOT NOT ADDED");
        }
    }
    public static void doctorLogout(Scanner sc){
        System.out.println("LOGGED OUT SUCCESSFULLY");
        System.out.println("LOGIN AGAIN --> 'L' GO TO MAIN MENU --> 'M'");
        char loop=sc.next().charAt(0);
        if(loop=='L'||loop=='l')
            Basic.doctorLogin();
        else
            App.displayMainMenu(sc);
    }
}
