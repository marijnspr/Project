package applicatie7a;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Michelle on 29-3-2017.
 */
public class DatabaseConnectie {
    public void connectie(String header,ArrayList<ORF> forward, ArrayList<ORF> reverse, String refseq) throws SQLException {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@cytosine.nl:1521:xe", "owe7_pg5", "blaat1234");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            Statement stmt = connection.createStatement();
            int count = 0;
            
            String sql;
            sql = "SELECT MAX (TO_CHAR(ID_SEQUENTIE)) FROM INGEVOERDE_SEQUENTIE";
            
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//            Calendar calendar = Calendar.getInstance();
////            Date date = (Date) calendar.getTime();
//            java.util.Date utiDate = new java.util.Date();
//            java.sql.Date sqlDate = new java.sql.Date(utiDate.getTime());
             

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //count = rs.getInt("ID_Sequentie");
                count = rs.getInt(1);
            }
            System.out.println(count);
            count += 1;
//invullen database ingevoerde sequentie
            String query = "INSERT INTO INGEVOERDE_SEQUENTIE (ID_SEQUENTIE, NAAM, DATUM, SEQUENTIE) VALUES (?,?,?,?)"; 
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,Integer.toString(count));
            preparedStmt.setString(2, header);
            preparedStmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStmt.setString(4, refseq.substring(0, 4000));

//            stmt.executeUpdate(query);
            preparedStmt.execute();
 // forward sequentie in ORF          
              for(int i = 0; i<forward.size();i++){
                String query1 = "INSERT INTO ORF (START_POSITIE, STOP_POSITIE, READING_FRAME, SEQ_ID, SEQUENTIE_ORF)" + 
                    "VALUES ("+ forward.get(i).getStartPos() +","+ forward.get(i).getStopPos()  +","+  forward.get(i).getORFseq() +","+count +","+forward.get(i).getReadingFrame()+");";
                stmt.executeUpdate(query1);
              }
 // reverse sequentie in oRF
            for(int i= 0;i<reverse.size();i++){
                String query2 = "INSERT INTO ORF (START_POSITIE, STOP_POSITIE, READING_FRAME, SEQ_ID, SEQUENTIE_ORF)" +
                        "VALUES ("+ reverse.get(i).getStartPos()  +","+ reverse.get(i).getStopPos()  +","+ reverse.get(i).getReadingFrame() +","+ count +","+ reverse.get(i).getORFseq()+");";
            stmt.executeUpdate(query2);
            }
            rs.close();
            stmt.close();
            connection.close();
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
