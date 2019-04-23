/**
 *
 * @author Kuric
 */
import java.sql.Connection;
import java.sql.DriverManager;
public class Connections_database {
    static Connection con;
    
    public static Connection getConnection(){
     try{     
      con = DriverManager.getConnection("jdbc:mysql://localhost/bank?serverTimezone=Europe/Riga&useSSL=false","root","");                                                                  
     }catch(Exception ex){
         System.out.println(""+ex);
     }
     return con;
    }    
}

