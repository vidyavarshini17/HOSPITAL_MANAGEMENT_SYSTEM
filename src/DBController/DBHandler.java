package DBController;
import Model.*;
import java.sql.*;

public class DBHandler {
    public static int checkAdminValidity(String usernameInput,String passwordInput,String typeOfUser){
        
        try{
        DBInitializer.createNewConnection();
        String sql =DBQuery.adminCheck(usernameInput,passwordInput,typeOfUser); 
        //returns query to validate input username and password

        ResultSet rs=DBInitializer.s.executeQuery(sql); 
        rs.next();
        int check=rs.getInt("C"); //returns count of records with the given username and password
        if(check==1)
            return 1;
        else
            return 0;
        }
        catch(Exception e){
            return 0;
        }
    }

    public static int checkDoctorValidity(String usernameInput,String passwordInput,String typeOfUser){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.doctorCheck(usernameInput,passwordInput,typeOfUser);
            ResultSet rs = DBInitializer.s.executeQuery(sql);
            rs.next();
            int check = rs.getInt("C"); //returns count of records with the given username and password
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }

    }
    public static int checkPatientValidity(String usernameInput,String passwordInput,String typeOfUser){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.patientCheck(usernameInput,passwordInput,typeOfUser);
            ResultSet rs = DBInitializer.s.executeQuery(sql);
            rs.next();
            int check = rs.getInt("C");//returns count of records with the given username and password
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }
    public static int userRegistration(String usernameInput,String passwordInput,String str){
        try{
        DBInitializer.createNewConnection();
        String sql=DBQuery.registerUser(usernameInput,passwordInput,str);
        int i = DBInitializer.s.executeUpdate(sql);
        if(i==1)
            return 1;
        else
            return 0;
        }
        catch(Exception e){
            return 0;
        }
    }

    public static int changeOfPassword(String username,String newPassword){
        try{   
            DBInitializer.createNewConnection();             
            String sql=DBQuery.changePasswordQuery(username,newPassword);
            int x=DBInitializer.s.executeUpdate(sql);
            if(x==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }

    public static int doctorInsertion(String new_name,String doctorSpecialization){
        try{
        DBInitializer.createNewConnection();
        String sql=DBQuery.addDoctorQuery(new_name,doctorSpecialization);
        int insertionCheck=DBInitializer.s.executeUpdate(sql);
        if(insertionCheck==1)
            return 1;
        else
            return 0;
        }
        catch(Exception e){
            return 0;
        }
    }
    public static int patientInsertion(String new_name){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.addPatientQuery(new_name);
            int insertionCheck=DBInitializer.s.executeUpdate(sql);
            if(insertionCheck==1)
                return 1;
            else
                return 0;
        }
        catch(Exception e){
            return 0;
        }
    }
    public static int doctorDeletion(int id){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.removeDoctorQuery(id);
            int deletionCheck=DBInitializer.s.executeUpdate(sql);
            if(deletionCheck==1)
                return 1;
            else
                return 0;
        }
        catch(Exception e){
            return 0;
        }
    }
    public static int patientDeletion(int id){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.removePatientQuery(id);
            int deletionCheck=DBInitializer.s.executeUpdate(sql);
            if(deletionCheck==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }

    public static int updateNameOfDoctor(int id,String newName){
        try{      
            DBInitializer.createNewConnection();          
            String sql=DBQuery.updateDoctorNameQuery(id,newName);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }
    public static int updateSpecializationOfDoctor(int id,String newSpecialization){
        try{                
            DBInitializer.createNewConnection();
            String sql=DBQuery.updateDoctorSpecializationQuery(id,newSpecialization);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }
    public static int updateNameOfPatient(int id,String newName){
        try{                
            DBInitializer.createNewConnection();
            String sql=DBQuery.updatePatientNameQuery(id,newName);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }
    public static void displayDoctor(Doctors doctor){
        try{      
            DBInitializer.createNewConnection();          
            String sql=DBQuery.viewDoctorQuery(doctor);
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            rs.next();
            System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
        }
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY");
        }
    }
    public static void displayPatient(Patients patient){
        try{      
            DBInitializer.createNewConnection();          
            String sql=DBQuery.viewPatientQuery(patient);
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            rs.next();
            System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
        }
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY");
        }
    }
    public static void displayAllPatients(){
        try{     
            DBInitializer.createNewConnection();           
            String sql=DBQuery.viewAllPatientsQuery();
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getInt(3));
            }
        }
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY");
        }
    }
    public static void displayAllDoctors(){
        try{  
            DBInitializer.createNewConnection();              
            String sql=DBQuery.viewAllDoctorsQuery();
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3));
            }
        }
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY");
        }
    }

    public static void viewAppointmentsOfDoctor(Doctors doctor){
        try{
            String sql=DBQuery.viewAppointmentQuery(doctor);
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5));
            }
        }
        catch(Exception e){
            System.out.println("UNABLE TO VIEW THE APPOINTMENTS OF DOCTOR "+doctor.getDoctorID());
        }
    }
    public static void viewAppointments(){
        try{
            String sql=DBQuery.viewAllAppointmentsQuery();
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5));
            }
        }
        catch(Exception e){
            System.out.println("UNABLE TO VIEW APPOINTMENTS\n");
        }
    }

    public static String findSlot(int newSlotChoice){
        switch(newSlotChoice){
            case 1:
                return "10:00am-11:00am";

            case 2:
                return "1:00pm-2:00pm";

            case 3:
                return "5:00pm-6:00pm";

            default:
                return null;
        }
    }

    public static int addNewAppointmentSlot(int id,String newDay,int newSlotChoice,String newStatus){
        try{
            DBInitializer.createNewConnection();
            String chosenSlot=findSlot(newSlotChoice);
            
            String sql=DBQuery.addSlot(id,newDay,chosenSlot,newStatus);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1)
                return 1;
            else
                return 0;
            }
        catch(Exception e){
            return 0;
        }
    }

    public static int updateSlot(int id,String oldDay,int oldSlotChoice,int newSlotChoice){
        try{
            DBInitializer.createNewConnection();
            String newSlot=findSlot(newSlotChoice);
            String oldSlot=findSlot(oldSlotChoice);
    
            String sql=DBQuery.slotUpdate(newSlot,id,oldDay,oldSlot);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }
        public static int updateDay(int id,String oldDay,String newDay,int chosenSlotChoice){
            try{
                DBInitializer.createNewConnection();
                String chosenSlot=findSlot(chosenSlotChoice);
    
                String sql=DBQuery.dayUpdate(newDay, id, oldDay, chosenSlot);
                int check=DBInitializer.s.executeUpdate(sql);
                if(check==1)
                    return 1;
                else
                    return 0;
            }
            catch(Exception e){
                return 0;
            }     
        }
    //check from this
        
    public static int returnNumberOfSpecialists(String requiredSpecialization){
        try{
        DBInitializer.createNewConnection();
        String checkRequiredSpecialization=DBQuery.checkSpecialization(requiredSpecialization);
        ResultSet r=DBInitializer.s.executeQuery(checkRequiredSpecialization);
        r.next();  
        return r.getInt("C");     
        }
        catch(Exception e){
            return 0;
        }
    }

    public static void returnSpecialists(Doctors doctor){
        try{
            DBInitializer.createNewConnection();
            String displaySpecialization=DBQuery.viewSpecialists(doctor);
            ResultSet rs=DBInitializer.s.executeQuery(displaySpecialization);
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
            }    
        }
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY SPECIALISTS");;
        }
    }

    //BEGIN EDIT HERE
    public static void slotdisplay(String bookingStatus,Doctors doctor){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.displayAvailableSlots(bookingStatus,doctor);
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            System.out.println("AVAILABLE DAY : \tAVAILABLE SLOT : ");
            while(rs.next()){
                System.out.println(rs.getString(2)+"\t"+rs.getString(3));
            }
        }   
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY SLOTS");
        }
    }
    
    public static int statusUpdate(String slotStatus,String chosenDay,String chosenSlot,int chosenDoctorID){
        try{      
            DBInitializer.createNewConnection();          
            String sql=DBQuery.updateStatus(slotStatus,chosenDay,chosenSlot,chosenDoctorID);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1)
                return 1;
            else
                return 0;
            }
            catch(Exception e){
                return 0;
            }
        }
    
    public static int addNewAppointment(int doctor_ID,int patient_ID,String slotStatus,String chosenSlot,String chosenDay){
        try{   
            DBInitializer.createNewConnection();
            //
            String sql=DBQuery.insertAppointmentDetails(doctor_ID,patient_ID);
            int check=DBInitializer.s.executeUpdate(sql);
            if(check==1){
                if(appointmentUpdate(doctor_ID, patient_ID, slotStatus, chosenSlot, chosenDay)==1)
                    return 1;
                else
                    return 0;
                }
            else
                return 0;
            }
            catch(Exception e){
                System.out.println("NEW APPOINTMENT ADDED");
                return 0;
            }
        }
    public static int appointmentUpdate(int doctor_ID,int patient_ID,String slotStatus,String chosenSlot,String chosenDay){
            try{   
                DBInitializer.createNewConnection();
                String sql=DBQuery.insertAppointmentDetails(doctor_ID,patient_ID);
                int check=DBInitializer.s.executeUpdate(sql);
                if(check==1){
                    Appointments appointment=new Appointments(0, doctor_ID, patient_ID);
                    int newAppID=selectID(appointment);
                    try{
                        String sql3=DBQuery.updateAppID(newAppID,doctor_ID,slotStatus,chosenDay,chosenSlot);             
                        int check2=DBInitializer.s.executeUpdate(sql3);
                        if(check2==1){
                            System.out.println("AVAILABILITY IS UPDATED\n");
                            return 1;
                        }
                        else{
                            System.out.println("AVAILABILITY IS NOT UPDATED\n");
                            return 0;
                        }
                    }
                    catch(Exception e){
                        System.out.println("AVAILABILITY IS NOT UPDATED");
                        return 0;
                    }
                }
                else{
                    return 0;
                }
            }
                catch(Exception e){
                    return 0;
                }
            }
    public static int selectID(Appointments appointment){
        try{   
            DBInitializer.createNewConnection();
            String sql2=DBQuery.selectAppointmentIdQuery(appointment);
            ResultSet rs1=DBInitializer.s.executeQuery(sql2);
            rs1.next();
            int newAppointmentId2=rs1.getInt(1);
            return newAppointmentId2;
        }
        catch(Exception e){
            return 0;
        }
    }
    //END EDIT HERE
    public static int cancelAppointmentHandler(int id){
        try{      
            DBInitializer.createNewConnection();
            String slotStatus="unbooked";
            String updateStatus="update availability set status='"+slotStatus+"' where appointment_id='"+id+"'";
            int check1=DBInitializer.s.executeUpdate(updateStatus);
            if(check1==1){
                String removeAppointment="delete from appointments where appointment_id='"+id+"'";
                int check2=DBInitializer.s.executeUpdate(removeAppointment);
                if(check2==1)
                    return 1;
                else
                    return 0;
            }
            else
                return 0;
        }
        catch(Exception e){
            return 0;
        }
    }

    public static int returnDoctorId(int id){
        try{
            DBInitializer.createNewConnection();

            Appointments appointment=new Appointments(id,0, 0);
            String sql="select d_id from appointments where appointment_id='"+appointment.getAppointmentID()+"'";
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        }
        catch(Exception e){
            return 0;
        }
    }
    public static String returnSpecialization(int id){
        try{
            DBInitializer.createNewConnection();

            Doctors doc=new Doctors(id, null, null);
            String sql="select specialization from doctors where d_id='"+doc.getDoctorID()+"'";
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            rs.next();
            return rs.getString(1);
        }
        catch(Exception e){
            return null;
        }
    }
    public static int reschedule(int id,String chosenDay,String chosenSlot,int doctor_id){
        try{
            String newStatusOfOldAppointment="unbooked";
            String oldStatus="booked";
            int newIDOfOldAppointment=0;
            String updateOldAppointment="update availability set status='"+newStatusOfOldAppointment+"',appointment_id='"+newIDOfOldAppointment+"' where appointment_id='"+id+"' AND status='"+oldStatus+"'";
            int check1=DBInitializer.s.executeUpdate(updateOldAppointment);
            if(check1==1){
                System.out.println("OLD APPOINTMENT "+id+"REMOVED");
                String newStatusOfNewAppointment="booked";
                String updateNewAppointment="update availability set status='"+newStatusOfNewAppointment+"',appointment_id='"+id+"' where day='"+chosenDay+"',slot='"+chosenSlot+"',d_id='"+doctor_id+"'";
                int check2=DBInitializer.s.executeUpdate(updateNewAppointment);
                if(check2==1){
                    System.out.println("NEW APPOINTMENT "+id+"IS UPDATED");
                    return 1;
                }
                else{
                    System.out.println("UNABLE TO UPDATE NEW APPOINTMENT "+id);
                    return 0;
                }
            }
            else{
                System.out.println("UNABLE TO REMOVE OLD APPOINTMENT "+id);
                return 0;
            }
            
            }
        catch(Exception e){
            System.out.println("FAILED TO RESCHEDULE APPOINTMENT");
            return 0;
        }
    } 

}
