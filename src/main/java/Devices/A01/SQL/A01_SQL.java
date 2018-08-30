package Devices.A01.SQL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class A01_SQL {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Home";

    //  Database credentials
    static final String USER = "Vegard";
    static final String PASS = "Vegard";
    private Connection conn;
    private Statement stmt;

    public A01_SQL() {
        this.conn = null;
        this.stmt = null;
    }

    public void insertValuesA01(float temperature, float humidity, float pressure) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "INSERT INTO Temperature (id,temp,humidity,pressure) VALUES (?,?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, "A01");
            preparedStmt.setFloat (2, temperature);
            preparedStmt.setFloat (3, humidity);
            preparedStmt.setFloat (4, pressure);
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


    public long[] selectDatabaseIntervalA01() {
        long[] databaseInterval = new long[3];
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "select * from Device_database_interval WHERE `id`='A01'";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            int num = 0;
            while (rs.next()) {
                //Retrieve by column name
                databaseInterval[num] = rs.getInt("interval_sec");
                num++;
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
        return databaseInterval;
    }

    public boolean updateDatabaseIntervalA01(int inputValue) {
        boolean valueUpdated = false;       // Returns true if the data in sql has updated.
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "UPDATE Device_database_interval SET";
            query = query + " interval_sec='" + Integer.toString(inputValue) + "'";
            query = query + " WHERE `id`='A01'";
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

    public ArrayList<String> selectA01Data(int amountOfDatesBeforeToday) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "select * from Temperature WHERE `id`='A01' AND `timestamp` >= DATE_SUB(CURDATE(), INTERVAL "
            + amountOfDatesBeforeToday + " DAY)";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set

            while (rs.next()) {
                JsonObject obj = new JsonObject();
                float temperature = rs.getFloat("temp");
                obj.addProperty("temp",temperature);
                float humidity = rs.getFloat("humidity");
                obj.addProperty("humidity",humidity);
                float pressure = rs.getFloat("pressure");
                obj.addProperty("pressure",pressure);
                String timestamp = rs.getString("timestamp");
                obj.addProperty("timestamp",timestamp);
                arrayList.add(obj.toString());
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
        return arrayList;
    }


    public Timestamp parseTimeStamp(String sTimestamp) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(sTimestamp);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        return timestamp;
    }


}
