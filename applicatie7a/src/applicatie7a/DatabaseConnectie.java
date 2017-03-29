package applicatie7a;

import java.sql.*;

/**
 * Created by Michelle on 29-3-2017.
 */
public class DatabaseConnectie {
    public void connectie() throws SQLException {
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

            String sql;
            sql = "SELECT * FROM INGEVOERDE_SEQUENTIE";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID_SEQUENTIE");
                String seq = rs.getString("SEQUENTIE");

                System.out.println("id: " + id);
                System.out.println("seq: " + seq);
            }
            rs.close();
            stmt.close();
            connection.close();
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
