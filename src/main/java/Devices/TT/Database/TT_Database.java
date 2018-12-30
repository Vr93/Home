package Devices.TT.Database;


import com.google.gson.JsonObject;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TT_Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Home";
    //  Database credentials
    static final String USER = "Home";
    static final String PASS = "Home";
    private Connection conn;
    private Statement stmt;

    public TT_Database(){
            this.conn = null;
            this.stmt = null;
    }

    /**
     * Insert values into database for TT-devices.
     * @param device
     * @param temperature
     * @param humidity
     * @param pressure
     * @param voltage
     */
    public void insertValues(int device, float temperature, float humidity, float pressure, float voltage) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "INSERT INTO TT (device,temperature,humidity,pressure,voltage) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, device);
            preparedStmt.setFloat (2, temperature);
            preparedStmt.setFloat (3, humidity);
            preparedStmt.setFloat (4, pressure);
            preparedStmt.setFloat(5,voltage);
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


    /**
     * Returns the name and device configuration stored in database.
     * @return ArrayList<JsonObject>
     */
    public ArrayList<JsonObject> getDevices() {
        ArrayList<JsonObject> list = new ArrayList<>();
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "select * from TT_config";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                JsonObject obj = new JsonObject();
                int device = rs.getInt("device");
                String name = rs.getString("name");
                obj.addProperty("device",device);
                obj.addProperty("name",name);
                list.add(obj);

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
        return list;
    }

    /**
     * Returns all data for the given device within the time limit input.
     * @param deviceID, the number of the device.
     * @param howManyDays, how many days back in time to fetch data.
     * @return
     */
    public ArrayList<String> selectDataTT(int deviceID, int howManyDays) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "select * from TT WHERE `device`='" + deviceID + "' AND `timestamp` >= DATE_SUB(CURDATE(), INTERVAL "
                    + howManyDays + " DAY)";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set

            while (rs.next()) {
                JsonObject obj = new JsonObject();
                float temperature = rs.getFloat("temperature");
                obj.addProperty("temperature",temperature);
                float humidity = rs.getFloat("humidity");
                obj.addProperty("humidity",humidity);
                float pressure = rs.getFloat("pressure");
                obj.addProperty("pressure",pressure);
                float voltage = rs.getFloat("voltage");
                obj.addProperty("voltage",voltage);
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


    /**
     * Returns the newest date a given device has data in the database.
     * @param deviceID, the device number.
     * @return DateInString, date formatted as string, yyyy-mm-dd.
     */
    public String selectNewestDate(int deviceID) {
        String dateInString = "";

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "select timestamp from TT WHERE `device`='" +deviceID  + "' ORDER BY timestamp DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set

            while (rs.next()) {
               String timestamp = rs.getString("timestamp");
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
               Date date = dateFormat.parse(timestamp);
               dateInString = date.toString();
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
        /* Check whether the return value is empty before sending, if it is. Set it to current date. */
        if(dateInString.equalsIgnoreCase("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date = new Date();
            dateInString = date.toString();
        }
        return dateInString;
    }


}
