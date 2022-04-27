package View;
import java.sql.ResultSet;
import java.util.*;

import DBController.DBInitializer;
import Model.Doctors;

public class doctorCases {
    //method having main menu for doctor login
    public static void doctorOperations(String username,Scanner sc) throws Exception{
        do {
            System.out.println("1.CHANGE PASSWORD\n2.CANCEL APPOINTMENT\n3.RESCHEDULE APPOINTMENT\n4.VIEW APPOINTMENT\n5.VIEW ALL APPOINTMENTS\n6.UPDATE AVAILABILITY\n7.LOGOUT");
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
                    doctorCases.updateAvailability();
                break;

                case 7:
                    doctorLogout(sc);
                break;

                default:
                break;
            }
            System.out.println("CONTINUE WITH SAME OPERATION-->Y");
        } while (sc.next().charAt(0) == 'y' || sc.next().charAt(0) == 'Y');
    }
    //view the appointments of a particular doctor
    public static void viewAppointment(Scanner sc) throws Exception {
        System.out.println("ENTER ID ");
        int id=sc.nextInt();

        Doctors doctor=new Doctors(id, null,null);
        String sql=DBQuery.viewAppointmentQuery(doctor);
        ResultSet rs=DBInitializer.s.executeQuery(sql);
        while(rs.next()){
            System.out.println();
        }
        //YET TO COMPLETE
    }
    //view all the appointments so far
    public static void viewAllAppointments(){
        String sql="select * from appointments inner join availability ON appointments.appointment_id=availability.appointment_id";
        ResultSet rs=DBInitializer.s.executeQuery(sql);
        while()
    }

    public static void updateAvailability(){
        //YET TO COMPLETE
    }
    public static void doctorLogout(Scanner sc) throws Exception{
        System.out.println("LOGGED OUT SUCCESSFULLY");
        System.out.println("LOGIN AGAIN --> 'L' GO TO MAIN MENU --> 'M'");
        char loop=sc.next().charAt(0);
        if(loop=='L'||loop=='l')
            Basic.doctorLogin();
        else
            App.displayMainMenu(sc);
    }
}
