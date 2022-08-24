package DBController;

import Model.*;
//class that consists of all the sql queries required in the program in methods
public class DBQuery {

    //method returning the query to check if admin username and password are valid
    public static String adminCheck(String usernameInput,String passwordInput,String usertype){
        return "select count(*) AS C from users where username='"+usernameInput+"' AND password='"+passwordInput+"' AND position='"+usertype+"'";
    }

    //method returning query to check if doctor username and password are valid
    public static String doctorCheck(String usernameInput,String passwordInput,String usertype){
        return "select count(*) AS C from users where username='" + usernameInput + "' AND password='" + passwordInput + "' AND position='"+usertype+"'";
    }

    //method returning query to check if patient username and password are valid
    public static String patientCheck(String usernameInput,String passwordInput,String usertype){
        return "select count(*) AS C from users where username='" + usernameInput + "' AND password='" + passwordInput+ "' AND position='"+usertype+"'";
    }

    //method returning query to register new users
    public static String registerUser(String username,String password,String usertype){
        return "insert into users(username,password,position) values('" +username+ "','" +password+ "','" +usertype+ "')";
    }

    //method returning query to add new doctor to the doctor table
    public static String addDoctorQuery(String new_name,String doctorSpecialization){
        return "insert into doctors(name,specialization)values('"+new_name+"','"+doctorSpecialization+"')";
    }

    //method returning the query to add new patient to the patient table
    public static String addPatientQuery(String new_name){
        return "insert into patients(name)values('"+new_name+"')";
    }

    //method returning the query to remove a particular doctor from the doctor table using doctorID
    public static String removeDoctorQuery(int id){
        return "delete from doctors where d_id='"+id+"'";
    }

    //method returning the query to remove a particular patient from the patient table using patientID
    public static String removePatientQuery(int id){
        return "delete from patients where p_id='"+id+"'";
    }

    //method returning the query to update the doctor name using doctorID
    public static String updateDoctorNameQuery(int id,String newName){
        return "update doctors set name='"+newName+"' where d_id='"+id+"'";
    }

    //method returning the query to update the specialization of the doctor using doctorID
    public static String updateDoctorSpecializationQuery(int id,String specialization){
        return "update doctors set specialization='"+specialization+"' where d_id='"+id+"'";
    }

    //method returning the query to update the patient name using patientID
    public static String updatePatientNameQuery(int id,String newName){
        return "update patients set name='"+newName+"' where p_id='"+id+"'";
    }

    //method returning the query to view a particular doctor using doctorID
    public static String viewDoctorQuery(Doctors doctor){
        return "select * from doctors where d_id='"+doctor.getDoctorID()+"'";
    }

    //method returning the query to view a particular patient using patientID
    public static String viewPatientQuery(Patients patient){
        return "select * from patients where p_id='"+patient.getPatientID()+"'";
    }

    static final String sql1="select * from doctors";
    //method to return the query to view all doctors
    public static String viewAllDoctorsQuery(){
        return sql1 ;
    }

    static final String sql2="select * from patients";
    //method to return the query to view all patients
    public static String viewAllPatientsQuery(){
        return sql2;
    }

    //method returning the query change the password of the user
    public static String changePasswordQuery(String userNameInput,String newPassword){
        return "update users set password='"+newPassword+"' where username='"+userNameInput+"'";
    }

    //method returning query to select the details of appointments
    public static String viewAppointmentQuery(Doctors doctor){
        String slotStatus="booked";
        return "select appointments.appointment_id,appointments.d_id,availability.p_id,appointments.day,appointments.slot from appointments inner join availability ON appointments.appointment_id=availability.appointment_id where d_id='"+doctor.getDoctorID()+"' AND appointments.status='"+slotStatus+"'";
    }

    //method returning query to select the details of all appointments
    public static String viewAllAppointmentsQuery(){
        String slotStatus="booked";
        return "select appointments.appointment_id,appointments.d_id,availability.p_id,appointments.day,appointments.slot from appointments inner join availability ON appointments.appointment_id=availability.appointment_id where appointments.status='"+slotStatus+"'";
    }

    //method to add new slot
    public static String addSlot(int id,String newDay, String chosenSlot,String newStatus){
        return "insert into availability(d_id,day,slot,status) values('"+id+"','"+newDay+"','"+chosenSlot+"','"+newStatus+"')";
    }

    //method to update slot timings
    public static String slotUpdate(String newSlot,int id,String oldDay,String oldSlot){
        return "update availability set slot='"+newSlot+"' where d_id='"+id+"' AND day='"+oldDay+"' AND slot='"+oldSlot+"'";
    }

    //method to update day
    public static String dayUpdate(String newDay,int id,String oldDay,String chosenSlot){
        return "update availability set day='"+newDay+"' where d_id='"+id+"' AND day='"+oldDay+"' AND slot='"+chosenSlot+"'";
    }

    //check from here

    //method returning query to check whether doctors exist with the preferred specializations
    public static String checkSpecialization(String requiredSpecialization){
        return "select count(*) as C from doctors where specialization='"+requiredSpecialization+"'";
    }
    
    //method returning query to display doctors with the preferred specialization
    public static String viewSpecialists(Doctors doctor){
        return "select * from doctors where specialization='"+doctor.getSpecialization()+"'";
    }

    //method returning query to display doctors with the preferred specialization
    public static String updateStatus(String slotStatus,String chosenDay,String chosenSlot,int id){
        return "update availability set status='"+slotStatus+"' where d_id='"+id+"' AND day='"+chosenDay+"' AND slot='"+chosenSlot+"'";
    }

    //method returning query to display all the available slots
    public static String displayAvailableSlots(String bookingStatus,Doctors doctor1){
        return "select day,slot from availability where d_id='"+doctor1.getDoctorID()+"' AND status='"+bookingStatus+"'";
    }

    //method returning query to insert new appointment details
    public static String insertAppointmentDetails(int doctorID,int patientID){
        return "insert into appointments(d_id,p_id) values ('"+doctorID+"','"+patientID+"')";
    }

    //method returning query retrieve appointment id from appointment table
    public static String selectAppointmentIdQuery(Appointments appointment){
        return "select appointment_id from appointments where d_id='"+appointment.getDoctorID()+"' AND p_id='"+appointment.getPatientID()+"'";
    }

    //method returning query update the appointment id in the newly booked appointment in availability table 
    public static String updateAppID(int newAppointmentId,int doctor_id,String slotStatus,String chosenDay,String chosenSlot){
        return "update availability set appointment_id='"+newAppointmentId+"' where d_id='"+doctor_id+"' AND status='"+slotStatus+"' AND day='"+chosenDay+"' AND slot='"+chosenSlot+"'";
    }
    




}
