/**
 *
 * @author 62dnkurilovics
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
public class Admin {
    Connection con;
    Main_menu menu  = null;
    Admin_page ad = null;
    PreparedStatement pst;
    ResultSet rs;
    

    
      public void addNewUser(String name, String surname, String email, String password) {
        try {
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO `useri`(`ID`, `Vards`, `Uzvards`, `E_pasts`, `Parole`, `Conditions`) VALUES (0,?,?,?,?,'User')");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setString(4,password);
            stmt.executeUpdate();
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
      
    public void updateUser(String name,String id, String surname, String email, String password){
        try{
            con = Connections_database.getConnection();
            
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "UPDATE useri SET ID=?,Vards=?,Uzvards=?,E_pasts=?,Parole=? where Conditions = 'User' and ID=? ");
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setString(4, email);
            stmt.setString(5,password);
            stmt.executeUpdate();
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Tada id nav!", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }
      
public void deleteUser(String id){ 
    try { 
        con = Connections_database.getConnection(); 
        java.sql.PreparedStatement stmt = con.prepareStatement( 
        "DELETE FROM useri where ID=? and Conditions = 'User'"); 
        stmt.setInt(1,Integer.parseInt(id)); 
        stmt.executeUpdate(); 
    } catch (Exception ex) { 
         JOptionPane.showMessageDialog(null, ex); 
    } 
}
      
public boolean check_Information_about_user(String temp){ 
    try{ 
        con = DriverManager.getConnection("jdbc:mysql://localhost/bank?serverTimezone=Europe/Riga&useSSL=false","root",""); 
        
        Statement st = con.createStatement(); 
        String check =temp ; 
        
        String sql = "SELECT * FROM useri WHERE ID=? and Conditions = 'User'"; 
        PreparedStatement preparedStatement =con.prepareStatement(sql); 
        preparedStatement.setString(1, check); 
        ResultSet rs = preparedStatement.executeQuery(); 
        return rs.next(); 
       }catch(SQLException ex){ 
            ex.printStackTrace(); 
        } 
return false; 
} 

public boolean check_email(String email){
    if (!(email.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$"))){
        return false;
    }else{
         return true; 
    }
}


public boolean check_name(String name){
    if (!(name.matches("[a-zA-Z ,]+"))){
        return false;
    }else{
        return true; 
    }
}


public boolean ckeck_surname(String surname){
    if (!(surname.matches("[a-zA-Z ,]+"))){
        return false;
    }else{
        return true; 
    }
}


public String string_Upercase_name(String name){
    if(name == null || name.isEmpty()) return ""; //или return word;
    return name.substring(0, 1).toUpperCase() + name.substring(1);

}

public String  string_Upercase_surname(String surname){
    if(surname == null || surname.isEmpty()) return ""; //или return word;
    return surname.substring(0, 1).toUpperCase() + surname.substring(1);   
}

public void deleteKonts(String id){
    try { 
        con = Connections_database.getConnection(); 
        java.sql.PreparedStatement stmt = con.prepareStatement( 
        "DELETE FROM konti where ID=? "); 
        stmt.setInt(1,Integer.parseInt(id)); 
        stmt.executeUpdate(); 
    } catch (Exception ex) { 
         JOptionPane.showMessageDialog(null, ex); 
    } 
}

public void deletelog(String id){ 
    try { 
        con = Connections_database.getConnection(); 
        java.sql.PreparedStatement stmt = con.prepareStatement( 
        "DELETE FROM log_file where ID=?"); 
        stmt.setInt(1,Integer.parseInt(id)); 
        stmt.executeUpdate(); 
    } catch (Exception ex) { 
         JOptionPane.showMessageDialog(null, ex); 
    } 
}
}