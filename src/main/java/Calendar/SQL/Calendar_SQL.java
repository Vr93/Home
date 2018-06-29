package Calendar.SQL;

import java.sql.*;

public class Calendar_SQL {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Home";

    //  Database credentials
    static final String USER = "Vegard";
    static final String PASS = "Vegard";
    private Connection conn;
    private Statement stmt;

    public Calendar_SQL(){
        this.conn = null;
        this.stmt = null;
    }


    public String[] selectLogin(String loginName) {
        String[] loginInformation = new String[2];
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "select * from Login WHERE `name`='" + loginName  + "'";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                loginInformation[0] = rs.getString("name");
                loginInformation[1] = rs.getString("password");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
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
        /* If there is no data from SQL, fill array with blank. */
        for(int i = 0; i < loginInformation.length; i++){
            if(loginInformation[i] == null){
                loginInformation[i] = "";
            }
        }
        return loginInformation;
    }
}
