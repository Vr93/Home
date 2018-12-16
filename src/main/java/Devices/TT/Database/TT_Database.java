package Devices.TT.Database;


import java.sql.*;

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
     * Returns the device number configuration stored in database. Used at startup of the application.
     * @return DeviceNumbers
     */
    public int[] getDevices() {
        int[] deviceNumbers = new int[128];
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();
            String sql = "select device from TT_config";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            int num = 0;
            while (rs.next()) {
                //Retrieve by column name
                boolean deviceAlreadyExists = false;
                int device = rs.getInt("device");
                for(int i = 0; i < deviceNumbers.length; i++){
                    if(device == deviceNumbers[i]){
                        deviceAlreadyExists = true;
                        break;
                    }
                }
                if(!deviceAlreadyExists) {
                    deviceNumbers[num] = device;
                }
                num++;
                if(num > 127) break;
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
        return deviceNumbers;
    }


}
