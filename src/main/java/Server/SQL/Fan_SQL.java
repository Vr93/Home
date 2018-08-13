package Server.SQL;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;


import java.sql.*;
import java.util.ArrayList;

public class Fan_SQL {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Home";

    //  Database credentials
    static final String USER = "Vegard";
    static final String PASS = "Vegard";
    private Connection conn;
    private Statement stmt;

    public Fan_SQL() {
        this.conn = null;
        this.stmt = null;
    }

    public void insertValuesServerFan(float setpoint) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "INSERT INTO ServerFan (setpoint) VALUES (?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setFloat (1, setpoint);
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


    public float selectSetpointFromSQL() {
        float setpoint = 50;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "select * from ServerFan WHERE 1";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            int num  = 0;
            while (rs.next()) {
                //Retrieve by column name
                setpoint = rs.getFloat("setpoint");
                num++;
            }
            /* If there is several data points in SQL, reset the setpoint to 50. */
            if(num > 2){
                setpoint = 50;
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
        return setpoint;
    }

    public boolean updateSetpointSQL(float inputValue) {
        boolean valueUpdated = false;       // Returns true if the data in sql has updated.
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "UPDATE ServerFan SET";
            query = query + " setpoint='" + Float.toString(inputValue) + "'";
            query = query + " WHERE 1";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            conn.close();
            valueUpdated = true;
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
        return valueUpdated;
    }

}

