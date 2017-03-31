package applicatie7a;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Created by Michelle on 29-3-2017.
 */
public class DatabaseConnectie {

    /**
     *
     * @param header
     * @param forward
     * @param reverse
     * @param refseq
     * @throws SQLException
     * @throws applicatie7a.geenVerbinding
     * @throws applicatie7a.geenDriver
     */
    public void connectie(String header,ArrayList<ORF> forward, ArrayList<ORF> reverse, String refseq) throws SQLException, geenVerbinding, geenDriver {
       try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            throw new geenDriver();
        }
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@cytosine.nl:1521:xe", "owe7_pg5", "blaat1234");

        } catch (SQLException e) {
            throw new geenVerbinding();
        }

        if (connection != null) {
            Statement stmt = connection.createStatement();
            int count = 0;
            
            String sql;
            sql = "SELECT MAX (TO_CHAR(ID_SEQUENTIE)) FROM INGEVOERDE_SEQUENTIE";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
            count += 1;
            
//invullen database ingevoerde sequentie
            String query = "INSERT INTO INGEVOERDE_SEQUENTIE (ID_SEQUENTIE, NAAM, DATUM, SEQUENTIE) VALUES (?,?,?,?)"; 
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,Integer.toString(count));
            preparedStmt.setString(2, header);
            preparedStmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStmt.setString(4, refseq);
            preparedStmt.execute();
            
 // forward sequentie in ORF 
              for(int i = 0; i<forward.size();i++){
                String query1 = "INSERT INTO ORF (START_POSITIE, STOP_POSITIE, READING_FRAME, SEQ_ID, SEQUENTIE_ORF) VALUES (?,?,?,?,?)"; 
                PreparedStatement ps = connection.prepareStatement(query1);
                ps.setInt(1, forward.get(i).getStartPos());
                ps.setInt(2, forward.get(i).getStopPos());
                ps.setString(3, forward.get(i).getReadingFrame());
                ps.setInt(4, count);
                ps.setString(5, forward.get(i).getORFseq());
                ps.execute();
              }
              
 // reverse sequentie in oRF
            for(int i= 0;i<reverse.size();i++){
                String query2 = "INSERT INTO ORF (START_POSITIE, STOP_POSITIE, READING_FRAME, SEQ_ID, SEQUENTIE_ORF) VALUES(?,?,?,?,?)"; 
                PreparedStatement ps = connection.prepareStatement(query2);
                ps.setInt(1, reverse.get(i).getStartPos());
                ps.setInt(2, reverse.get(i).getStopPos());
                ps.setString(3, reverse.get(i).getReadingFrame());
                ps.setInt(4, count);
                ps.setString(5, reverse.get(i).getORFseq());
                ps.execute();
            }
            
            rs.close();
            stmt.close();
            connection.close();
                    
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
class geenVerbinding extends Exception{
    JFrame j1;
    public geenVerbinding(){
        super();
        JOptionPane.showMessageDialog(j1, "Er is geen verbinding met de database", "Connection error", JOptionPane.ERROR_MESSAGE);
    }
}
 
class geenDriver extends Exception{
    JFrame j1;
    public geenDriver(){
    super();
    JOptionPane.showMessageDialog(j1,"Er is geen driver voor een Oracle database geinstalleerd","Driver Error",JOptionPane.WARNING_MESSAGE);
            
    }

}