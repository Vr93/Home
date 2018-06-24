package Devices.A01.SQL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.joda.time.DateTime;


import java.sql.*;
import java.util.ArrayList;

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



    public void updateSQLTableCitect(int variable, int advalm, int trend){
       // long[] currentDayHasValues = selectCitectDBFCountDay();
        /* There should only be one col for the current date with information
        about the amount of tags generated with the citect wrapper.
        Check if there is a col for the current date, then use UPDATE. If not, INSERT with new values. */
        /*if( (currentDayHasValues[0] > 0) || (currentDayHasValues[1] > 0) || (currentDayHasValues[2] > 0)){
            long updateVariableCount = variable + currentDayHasValues[0];
            long updateAdvalmCount = advalm + currentDayHasValues[1];
            long updateTrendCount = trend + currentDayHasValues[2];
            updateDBFCountDay(updateVariableCount,updateAdvalmCount,updateTrendCount);
        }*/
        /* If there is no col for the current date, use INSERT. */
        /*else{*/
           // insertDBFCountDay(variable,advalm,trend);
        //}
    }

    private void updateDBFCountDay(long variable, long advalm, long trend) {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String query = "UPDATE Citect SET";
            query = query + " variable='" + Long.toString(variable) + "',";
            query = query + " advalm='" + Long.toString(advalm) + "',";
            query = query + " trend='" + Long.toString(trend) + "'";
            query = query + " WHERE timestamp >= CURDATE()";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
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

    public ArrayList<String> selectA01Data(int amountOfDatesBeforeToday) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] sqlData;
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
                 // String[] findIndexNumber = getDatesLastWeek();
                /*for(int i = 0; i < findIndexNumber.length; i++){
                    if(findIndexNumber[i].equalsIgnoreCase(timestamp.substring(0,10))){
                        citectWeekDBFCount[i] = obj.toString();
                    }
                }*/
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

            /* Do check to see if any data is null. If this is the case fill this data with zero value to avoid the graph
        in the index page to not show any at all.*/
           /* String[] lastWeekDates = getDatesLastWeek(amountOfDatesBeforeToday);
            for(int i = 0; i < jsonData.length; i++){
                if(jsonData[i] == null){
                    JsonObject obj = new JsonObject();
                    obj.addProperty("temp",0);
                    obj.addProperty("humidity",0);
                    obj.addProperty("pressure",0);
                    obj.addProperty("timestamp",lastWeekDates[i]);
                    jsonData[i] = obj.toString();
                }
            }*/
        }//end try
        return arrayList;
    }

    private String[] getDatesLastWeek(int amountOfDatesBeforeToday){
       // int amountOfDatesBeforeToday = 8; // Incase the function is expanded with an input number.
        String datesLastWeek[] = new String[amountOfDatesBeforeToday];
        DateTime dateTime = new DateTime();
        for(int i = 7; i >= 0; i--) {
            datesLastWeek[i] = dateTime.minusDays(amountOfDatesBeforeToday - i - 1).toString().substring(0,10);
        }
        return datesLastWeek;
    }

}
