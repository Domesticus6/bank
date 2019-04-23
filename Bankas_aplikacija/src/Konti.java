import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
/**
 *
 * @author Kuric
 */
public class Konti {
    Admin_page ad;
    public double summa;
    Connection con;  
    PreparedStatement pst;
    ResultSet rs;
 
    public String create_kont_number(String number){
        Random rand = new Random();
        int id = rand.nextInt(100000) + 1999999;
        String.valueOf(id);
        number ="LV"+id;
        return number;
    }

    public boolean check_Information_about_cont(String temp,String condition){ 
    try{ 
        con = DriverManager.getConnection("jdbc:mysql://localhost/bank?serverTimezone=Europe/Riga&useSSL=false","root",""); 
        Statement st = con.createStatement(); 
        String check =temp ; 
        String check2 =condition ;
        String sql = "SELECT * FROM konti WHERE ID=? and Konta_veids = ?"; 
        PreparedStatement preparedStatement =con.prepareStatement(sql); 
        preparedStatement.setString(1, check);
        preparedStatement.setString(2,check2); 
        ResultSet rs = preparedStatement.executeQuery(); 
        return rs.next(); 
       }catch(SQLException ex){ 
            ex.printStackTrace(); 
        } 
     return false; 
    }
    
    public void create_credit_kont(String id,String number,int summa){
        try {
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO `konti`(`ID`, `Konta_veids`, `Konta_numurs`, `Summa`) VALUES (?,'Kredita',?,?)");
            stmt.setString(1, id);
            stmt.setString(2, number);
            stmt.setInt(3, summa);
            stmt.executeUpdate();
            
            //System.out.println("Adding account for new user");
            //addAccount(getUser(name,surname,email).getUserID(),AccountTemplate.ALGAS_KONTS,(float)0.00,"EUR");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void create_nakop_kont(String id,String number,int summa){
        try {
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO `konti`(`ID`, `Konta_veids`, `Konta_numurs`, `Summa`) VALUES (?,'Noguldijuma',?,?)");
            stmt.setString(1, id);
            stmt.setString(2, number);
            stmt.setInt(3, summa);
            stmt.executeUpdate();
            
            //System.out.println("Adding account for new user");
            //addAccount(getUser(name,surname,email).getUserID(),AccountTemplate.ALGAS_KONTS,(float)0.00,"EUR");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void create_salary_kont(String id,String number,int summa){
        try {
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO `konti`(`ID`, `Konta_veids`, `Konta_numurs`, `Summa`) VALUES (?,'Algas',?,?)");
            stmt.setString(1, id);
            stmt.setString(2, number);
            stmt.setInt(3, summa);
            stmt.executeUpdate();
            
            //System.out.println("Adding account for new user");
            //addAccount(getUser(name,surname,email).getUserID(),AccountTemplate.ALGAS_KONTS,(float)0.00,"EUR");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void log_konti(String id, String apraksts){
        try {
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO `log_file`(`ID`, `Apraksts`) VALUES (?,?)");
            stmt.setString(1, id);
            stmt.setString(2, apraksts);
            stmt.executeUpdate();
            
            //System.out.println("Adding account for new user");
            //addAccount(getUser(name,surname,email).getUserID(),AccountTemplate.ALGAS_KONTS,(float)0.00,"EUR");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public int GetKonti(String number,String condition,int summa){
        try {
            con = Connections_database.getConnection();
            pst = con.prepareStatement("SELECT * FROM konti WHERE Konta_veids=? and Konta_numurs = ?");
            pst.setString(1, condition);
            pst.setString(2, number); 
            rs = pst.executeQuery();
        if(rs.next()){
            summa = rs.getInt(4);
            System.out.println(summa);
        }
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return summa;
    }
    
    public void update_Kont(String number,String condition,int kopa){
        try{
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "UPDATE konti SET Summa=? where Konta_veids=? and Konta_numurs = ?");
            stmt.setInt(1, kopa);
            stmt.setString(2, condition);
            stmt.setString(3, number);
            stmt.executeUpdate();

        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Tada id nav!", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean check_kont(String temp){ 
    try{ 
        con = DriverManager.getConnection("jdbc:mysql://localhost/bank?serverTimezone=Europe/Riga&useSSL=false","root",""); 
        Statement st = con.createStatement(); 
        String check =temp ; 
        String sql = "SELECT * FROM konti WHERE Konta_numurs =?"; 
        PreparedStatement preparedStatement =con.prepareStatement(sql); 
        preparedStatement.setString(1, check); 
        ResultSet rs = preparedStatement.executeQuery(); 
        return rs.next(); 
       }catch(SQLException ex){ 
            ex.printStackTrace(); 
        } 
        return false; 
    } 
    
    public int GetKonti2(String number1,int summa){
        try {
            con = Connections_database.getConnection();
            pst = con.prepareStatement("SELECT * FROM konti WHERE Konta_numurs = ?");
            pst.setString(1, number1); 
            rs = pst.executeQuery();
        if(rs.next()){
            summa = rs.getInt(4);
            System.out.println(summa);
        }
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return summa;
    }
    
    public void update_Kont2(String number1,int kopa1){
        try{
            con = Connections_database.getConnection();
            java.sql.PreparedStatement stmt = con.prepareStatement(
            "UPDATE konti SET Summa=? where Konta_numurs = ?");
            stmt.setInt(1, kopa1);
            stmt.setString(2, number1);
            stmt.executeUpdate();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Tada id nav!", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }
}
