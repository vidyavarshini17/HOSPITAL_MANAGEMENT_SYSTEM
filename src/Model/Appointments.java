package Model;

public class Appointments {
    //appointment object attributes
    int appointmentID;
    int doctorID;
    int patientID;

    //constructor instantiate object once and used to set values to the object's attributes
    public Appointments(int appointmentID,int doctorID,int patientID){
        this.appointmentID=appointmentID;
        this.doctorID=doctorID;
        this.patientID=patientID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }
    public int getDoctorID() {
        return doctorID;
    }
    public int getPatientID() {
        return patientID;
    }
}
