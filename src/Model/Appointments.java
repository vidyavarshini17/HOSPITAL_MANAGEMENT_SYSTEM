package Model;

public class Appointments {
    //appointment object attributes
    public int appointmentID;
    public int doctorID;
    public int patientID;

    //constructor instantiate object once and used to set values to the object's attributes
    public Appointments(int appointmentID,int doctorID,int patientID){
        this.appointmentID=appointmentID;
        this.doctorID=doctorID;
        this.patientID=patientID;
    }
    
}
