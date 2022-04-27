package Model;

public class Doctors {
    //doctor object attributes
    int doctorID;
    String doctorName;
    String specialization;

    //constructor to instantiate object once and set values to its attributes
    public Doctors(int doctorID,String doctorName,String specialization){
        this.doctorID=doctorID;
        this.doctorName=doctorName;
        this.specialization=specialization;
    }
    
}
