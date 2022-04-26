package DBController;
import java.sql.*;
public class DBInitializer{

    public static Connection conn;
    public static Statement s;

    public static void createNewConnection() throws Exception{
    Class.forName("com.mysql.cj.jdbc.Driver"); //register and load the driver class
    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/console","root","Vidya@2001"); //establish database connection 
    s=conn.createStatement(); //create statement object
    }
}
