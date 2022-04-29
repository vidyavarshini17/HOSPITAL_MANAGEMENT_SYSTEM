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
            String displaySpecialization=DBQuery.viewSpecialization(doctor);
            ResultSet rs=DBInitializer.s.executeQuery(displaySpecialization);
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
            }    
        }
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY SPECIALISTS");;
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
    public static void slotdisplay(String bookingStatus,Doctors doctor){
        try{
            DBInitializer.createNewConnection();
            String sql=DBQuery.displayAvailableSlots(bookingStatus,doctor);
            ResultSet rs=DBInitializer.s.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
        }   
        catch(Exception e){
            System.out.println("UNABLE TO DISPLAY SLOTS");
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


}
