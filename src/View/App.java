package View;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("------------------------------------\nHOSPITAL DATABASE MANAGEMENT SYSTEM\n------------------------------------\nCHOOSE USER TYPE\n1.ADMIN\n2.DOCTOR\n3.PATIENT");
        int option=sc.nextInt(); //choose user type -- > admin/doctor/patient
        switch(option){
            case 1:
            signInOrSignUp();
            break;

            case 2:
            Basic.doctorLogin(); //login to doctor account
            break;

            case 3:
            Basic.patientLogin(); //login to patient account
            break;

            default:
            break;
        }
        sc.close();
    }
    public static void signInOrSignUp() throws Exception{
            Scanner sc=new Scanner(System.in);
            System.out.println("---------------\n1.ADMIN LOGIN\n2.ADMIN SIGNUP\n3.PATIENT SIGNUP\n4.DOCTOR SIGNUP\n---------------");
            int choice=sc.nextInt(); 
            switch(choice){
                case 1:
                    Basic.adminLogin(); //admin account login  
                break;

                case 2:
                    String str1="admin";
                    Basic.register(str1); //register new admin
                break;

                case 3:
                    String str2="patient";
                    Basic.register(str2); //register new patient
                break;

                case 4:
                    String str3="doctor";
                    Basic.register(str3); //register new doctor
                break;

                default:
                    System.exit(0);
                break;
            }
        }
    }

