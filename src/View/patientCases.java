package View;

import java.util.*;
import java.sql.*;
import DBController.*;
import Model.*;

public class patientCases {
    //method having the main menu for patient login
    public static void patientOperations(String username,Scanner sc) throws Exception{
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

    public static void scheduleAppointment(String username,Scanner sc) throws Exception {
        char choice;
        do{
        System.out.println("ENTER THE SPECIALIZATION");
        String requiredSpecialization=sc.next();
        Doctors doctor=new Doctors(0,null, requiredSpecialization);

        checkSpecialistAvailability(username,sc,doctor);

        System.out.println("CONTINUE SAME OPERATION-->Y\nBACK TO OPERATIONS-->N");
        choice=sc.next().charAt(0);
        if(choice=='n'||choice=='N')
            patientOperations(username, sc);
    }while(choice=='y'||choice=='Y');

    }
    private static void checkSpecialistAvailability(String username,Scanner sc,Doctors doctor) throws Exception{
        String checkRequiredSpecialization=DBQuery.checkSpecialization(doctor);
        ResultSet r=DBInitializer.s.executeQuery(checkRequiredSpecialization);
        r.next();

        //if required specialist(s) is/are available display their details for patient to choose
        if(r.getInt("C")>=1){
            System.out.println("DOCTOR ID : \tDOCTOR NAME : \tDOCTOR SPECIALIZATION :");
            String displaySpecialization=DBQuery.viewSpecialization(doctor);
            ResultSet rs=DBInitializer.s.executeQuery(displaySpecialization);
            while(rs.next()){
                System.out.println(r.getInt(1)+"\t"+r.getString(2)+"\t"+r.getInt(3));
            }
            System.out.println("CHOOSE DOCTOR ID");
            int chosenDoctorID=sc.nextInt(); 

            Doctors doctor1=new Doctors(chosenDoctorID,null,doctor.specialization);

            displayAvailability(doctor1); //check the available slots and days for the particular doctor
            
            System.out.println("CHOOSE THE DAY");
            String chosenDay=sc.next();
            System.out.println("CHOOSE THE SLOT");
            String chosenSlot=sc.next();
            System.out.println("ENTER THE PATIENT ID");
            int patientId=sc.nextInt();

            Availability availabilityObject=new Availability(doctor1.doctorID,chosenDay,chosenSlot,"booked",0);
            Patients patient=new Patients(patientId, null, 0);
            
            String sql=DBQuery.updateStatus(availabilityObject);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1){
                System.out.println("APPOINTMENT BOOKED");
                Appointments appointment=new Appointments(0, availabilityObject.doctorID,patient.patientID);
                addAppointment(appointment,availabilityObject);
            }
            else{
                System.out.println("BOOKING FAILED");
            }
        }
        else{
            System.out.println("CHOOSE ANOTHER SPECIALIZATION --> Y");
            scheduleAppointment(username,sc);
        }
    }

    //method to display all available days and slots of a particular doctor
    private static void displayAvailability(Doctors doctor1) throws Exception{
        System.out.println("DOCTOR ID : \tDAY : \tAVAILABLE SLOT : ");
        String bookingStatus="unbooked";
        String sql=DBQuery.displayAvailableSlots(bookingStatus,doctor1);
        ResultSet rs=DBInitializer.s.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
        }
    }
    private static void addAppointment(Appointments appointment,Availability availabilityObject) throws Exception{
        String sql1=DBQuery.insertAppointmentDetails(appointment);
        int check1=DBInitializer.s.executeUpdate(sql1);
        if(check1==1){
        String sql2=DBQuery.insertAppointmentId(appointment);
        ResultSet rs1=DBInitializer.s.executeQuery(sql2);
        rs1.next();
        int newAppointmentId=rs1.getInt(1);
        String sql3=DBQuery.updateAppID(newAppointmentId,appointment,availabilityObject);             
        int check2=DBInitializer.s.executeUpdate(sql3);
        if(check2==1)
            System.out.println("LOADING...\n");
        else
            System.out.println("FAILED...\n");
        }
        else
            System.out.println("FAILED...\n");
    }

    public static void rescheduleAppointment(Scanner sc){
        //YET TO COMPLETE
    }

    public static void cancelAppointment(Scanner sc){
        //YET TO COMPLETE
    }

    public static void patientLogout(Scanner sc) throws Exception{
        System.out.println("LOGGED OUT SUCCESSFULLY");
        System.out.println("LOGIN AGAIN --> 'L' GO TO MAIN MENU --> 'M'");
        char loop=sc.next().charAt(0);
        if(loop=='L'||loop=='l')
            Basic.patientLogin();
        else
            App.displayMainMenu(sc);
    }
}
