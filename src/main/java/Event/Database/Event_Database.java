package Event.Database;

import java.sql.*;

public class Event_Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Home";
    //  Database credentials
    static final String USER = "Home";
    static final String PASS = "Home";
    private Connection conn;
    private Statement stmt;

    public Event_Database(){
        this.conn = null;
        this.stmt = null;
    }


    /**
     * Insert a event message to the database.
     * @param message
     */
    public void insertEvent(String message) {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "INSERT INTO Event (message) VALUES (?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, message);
            preparedStmt.execute();

            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

}
