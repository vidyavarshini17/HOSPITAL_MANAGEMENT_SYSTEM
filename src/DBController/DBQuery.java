package DBController;

import Model.*;
//class that consists of all the sql queries required in the program in methods
public class DBQuery {

    //method returning query to add new doctor to the doctor table
    public static String addDoctorQuery(Doctors doctor){
        return "insert into doctors(name,specialization)values('"+doctor.doctorName+"','"+doctor.specialization+"')";
    }

    //method returning the query to add new patient to the patient table
    public static String addPatientQuery(Patients patient){
        return "insert into patients(name)values('"+patient.patientName+"')";
    }

    //method returning the query to remove a particular doctor from the doctor table using doctorID
    public static String removeDoctorQuery(Doctors doctor){
        return "delete from doctors where d_id='"+doctor.doctorID+"'";
    }

    //method returning the query to remove a particular patient from the patient table using patientID
    public static String removePatientQuery(Patients patient){
        return "delete from patients where p_id='"+patient.patientID+"'";
    }

    //method returning the query to update the doctor name using doctorID
    public static String updateDoctorNameQuery(Doctors doctor){
        return "update doctors set name='"+doctor.doctorName+"' where d_id='"+doctor.doctorID+"'";
    }

    //method returning the query to update the specialization of the doctor using doctorID
    public static String updateDoctorSpecializationQuery(Doctors doctor){
        return "update doctors set specialization='"+doctor.specialization+"' where d_id='"+doctor.doctorID+"'";
    }

    //method returning the query to update the patient name using patientID
    public static String updatePatientNameQuery(Patients patient){
        return "update patients set name='"+patient.patientName+"' where p_id='"+patient.patientID+"'";
    }

    //method returning the query to view a particular doctor using doctorID
    public static String viewDoctorQuery(Doctors doctor){
        return "select * from doctors where d_id='"+doctor.doctorID+"'";
    }

    //method returning the query to view a particular patient using patientID
    public static String viewPatientQuery(Patients patient){
        return "select * from patients where p_id='"+patient.patientID+"'";
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
    public static String changePasswordQuery(Users user){
        return "update users set password='"+user.passWord+"' where username='"+user.userName+"'";
    }

    //method returning the query to check if admin username and password are valid
    public static String adminCheck(Users user){
        return "select count(*) AS C from users where username='"+user.userName+"' AND password='"+user.passWord+"' AND position='"+user.userType+"'";
    }

    //method returning query to check if doctor username and password are valid
    public static String doctorCheck(Users user){
        return "select count(*) AS C from users where username='" + user.userName + "' AND password='" + user.passWord + "' AND position='"+user.userType+"'";
    }

    //method returning query to check if patient username and password are valid
    public static String patientCheck(Users user){
        return "select count(*) AS C from users where username='" + user.userName + "' AND password='" + user.passWord+ "' AND position='"+user.userType+"'";
    }

    //method returning query to register new users
    public static String registerUser(Users user){
        return "insert into users(username,password,position) values('" +user.userName+ "','" +user.passWord+ "','" +user.userType+ "')";
    }

    //method returning query to check whether doctors exist with the preferred specializations
    public static String checkSpecialization(Doctors doctor){
        return "select count(*) as C from doctors where specialization='"+doctor.specialization+"'";
    }
    
    //method returning query to display doctors with the preferred specialization
    public static String viewSpecialization(Doctors doctor){
        return "select * from doctors where specialization='"+doctor.specialization+"'";
    }

    //method returning query to display doctors with the preferred specialization
    public static String updateStatus(Availability availabilityObject){
        return "update availability set status='"+availabilityObject.statusOfSlot+"' where d_id='"+availabilityObject.doctorID+"' AND day='"+availabilityObject.day+"' AND slot='"+availabilityObject.slot+"'";
    }

    //method returning query to display all the available slots
    public static String displayAvailableSlots(String bookingStatus,Doctors doctor1){
        return "select d_id,day,slot from availability where d_id='"+doctor1.doctorID+"' AND status='"+bookingStatus+"'";
    }

    //method returning query to insert new appointment details
    public static String insertAppointmentDetails(Appointments appointment){
        return "insert into appointments(d_id,p_id) values ('"+appointment.doctorID+"','"+appointment.patientID+"')";
    }

    //method returning query retrieve appointment id from appointment table
    public static String insertAppointmentId(Appointments appointment){
        return "select appointment_id from appointments where d_id='"+appointment.doctorID+"' AND p_id='"+appointment.patientID+"'";
    }

    //method returning query update the appointment id in the newly booked appointment in availability table 
    public static String updateAppID(int newAppointmentId,Appointments appointment,Availability availabilityObject){
        return "update availability set appointment_id='"+newAppointmentId+"' where d_id='"+appointment.doctorID+"' AND status='"+availabilityObject.statusOfSlot+"' AND day='"+availabilityObject.day+"' AND slot='"+availabilityObject.slot+"'";
    }
    //method returning query to select the details of appointments
    public static String viewAppointmentQuery(Doctors doctor){
        return "select * from appointments inner join availability ON appointments.appointment_id=availability.appointment_id where d_id='"+doctor.doctorID+"'";
    }
    
    //method returning query to selevt



}
