package Model;

public class Availability {
    //availability object attributes
    public int doctorID;
    public String day;
    public String slot;
    public String statusOfSlot;
    public int appointmentID;

    //constructor - used to instantiate object once and set values for the object's attributes
    public Availability(int doctorID,String day,String slot,String statusOfSlot,int appointmentID){
        this.doctorID=doctorID;
        this.day=day;
        this.slot=slot;
        this.statusOfSlot=statusOfSlot;
        this.appointmentID=appointmentID;
    }


    
}