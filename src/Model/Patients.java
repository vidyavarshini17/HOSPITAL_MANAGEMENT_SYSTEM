package Model;

public class Patients {
    //patient object attributes
    int patientID;
    String patientName;
    int appointmentID;

    //constructor to instantiate object once and set values to its attributes
    public Patients(int patientID,String patientName,int appointmentID){
        this.patientID=patientID;
        this.patientName=patientName;
        this.appointmentID=appointmentID;
    }
    public int getPatientID() {
        return patientID;
    }
    public String getPatientName() {
        return patientName;
    }
    public int getAppointmentID() {
        return appointmentID;
    }   
    
}
