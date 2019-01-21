package Devices.TT.Database;


import com.google.gson.JsonObject;
import org.joda.time.DateTime;
import org.json.JSONObject;

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
     * @param dateFrom, start date for the data, in format yyyy-mm-dd.
     * @param dateTo, end date for the data, in format yyyy-mm-dd.
     * @return ArrayList<String>, each string is an json object with data.
     */
    public ArrayList<String> selectDataTT(int deviceID, String dateFrom, String dateTo) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "select * from TT WHERE `device`='" + deviceID + "' AND date(timestamp) between date('"
                    + dateFrom + "') and date('" + dateTo + "')";
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
     * Returns the latest data for the given device.
     * @param deviceID, the number of the device.
     * @return String, each string is an json object with data.
     */
    public String selectDataTTLatest(int deviceID) {
        String data = "";
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "select * from TT WHERE timestamp=(SELECT MAX(timestamp) FROM TT WHERE `device`='"+ deviceID +"')";
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
                 /* Get the timestamp and create text color from it. If the latest value in SQL is more than 1 hour old, notify
                    that the device is offline */
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date sqlTimeStamp = dateFormat.parse(timestamp);
                    Date currentTimestamp = new Date();
                    /* Set the currentTimestamp and subtract one hour. */
                    currentTimestamp.setTime(currentTimestamp.getTime() - 60*60*1000);
                    if(sqlTimeStamp.before(currentTimestamp)){
                        obj.addProperty("online",false);
                    }
                    else{
                        obj.addProperty("online",true);
                    }

                data = obj.toString();
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
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
        return data;
    }


    /**
     * Returns the min and max dates for a given device for all data in the database.
     * @param deviceID, the device number.
     * @return DateInString (String array [2]), date formatted as string, yyyy-mm-dd.
     */
    public String[] selectMinMaxDate(int deviceID) {
        String[] dates = new String[2];
        dates[0] = "";
        dates[1] = "";

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "Select min(timestamp),max(timestamp) from TT WHERE `device`='" +deviceID  + "'";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set

            while (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                String sDateMin = rs.getString("min(timestamp)");
                String sDateMax = rs.getString("max(timestamp)");
                if(sDateMin != null && sDateMax != null) {
                    Date dateMin = sdf.parse(sDateMin);
                    dates[0] = sdf.format(dateMin);
                    Date dateMax = sdf.parse(sDateMax);
                    dates[1] = sdf.format(dateMax);
                }
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

        /* Check if the data is received from the database. In cases where there is no data in the database, values are null.
            if this is the case, fill the data with todays date.
         */
        for(int i = 0; i < dates.length; i++){
            if(dates[i].equalsIgnoreCase("")){
                DateTime dt = new DateTime();
                dates[i] = String.valueOf(dt.getYear()) + "-" + String.valueOf(dt.getMonthOfYear()) + "-" + String.valueOf(dt.getDayOfMonth());
            }
        }

        return dates;
    }


}
