package Devices.A02.Controller;


import Devices.A02.SQL.A02_SQL;
import Devices.Com_Master.Model.SerialHandler;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class A02_Controller {

    @GetMapping(value = "/A02/dataDay")
    public String[] A02_DataDay(){
        A02_SQL sql = new A02_SQL();
        ArrayList<String> arrayList = sql.selectA02Data(0);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A02/dataWeek")
    public String[] A02_DataWeek(){
        A02_SQL sql = new A02_SQL();
        ArrayList<String> arrayList = sql.selectA02Data(7);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A02/dataMonth")
    public String[] A02_DataMonth(){
        A02_SQL sql = new A02_SQL();
        ArrayList<String> arrayList = sql.selectA02Data(31);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A02/dataYear")
    public String[] A02_DataYear(){
        A02_SQL sql = new A02_SQL();
        ArrayList<String> arrayList = sql.selectA02Data(365);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A02/database_interval")
    public long[] A02_Interval_Setting(){
        A02_SQL sql = new A02_SQL();
        return sql.selectDatabaseIntervalA02();
    }

    @GetMapping(value = "/A02/status")
    public String A02_Status(){
        return SerialHandler.getA01().getStatus();
    }

    @GetMapping(value = "/A02/isonline")
    public boolean A02_isOnline(){
        boolean isDeviceOnline = false;
        A02_SQL sql = new A02_SQL();
        ArrayList<String> arrayList = sql.selectA02Data(1);
        /* Get last value stored in database for the past day, if there is data */
        if(arrayList.size() > 0){
        String lastValue = arrayList.get(arrayList.size() - 1);
        /* Parse the last values to json. */
        JSONObject inputJson = new JSONObject(lastValue);
        /* Check if the json object has timestamp. */
        if(inputJson.has("time")){
            try{
            /* Convert last time value in database to Timestamp format. */
            Timestamp lastValueTimestamp = sql.parseTimeStamp(inputJson.get("time").toString());
            /* Get the current timestamp, one hour ago. */
            Timestamp oneHourAgo = new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000));
            /* Check if the value in database is after the timestamp one hour ago, then the device is online. */
            if(lastValueTimestamp.after(oneHourAgo)){
                isDeviceOnline = true;
            }
            }
            catch(ParseException ex){
                ex.printStackTrace();
            }
        }
        }
        return isDeviceOnline;
    }

    @GetMapping(value = "/A02/lastvalues")
    public ResponseEntity<String> A02_lastValues(){
        A02_SQL sql = new A02_SQL();
        ArrayList<String> arrayList = sql.selectA02Data(1);
        if(arrayList.size() > 0){
            String data = arrayList.get(arrayList.size() - 1);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        else{
            String data = "<p class=\"text-center text-danger\"> Error, no data received </p>";
            return new ResponseEntity<String>(data, HttpStatus.CONFLICT);
        }
    }



    @PostMapping(path="/A02/database_interval_update")
    public ResponseEntity<?> save(@RequestBody String input) {
        /* Get input and turn this String to JSON */
        JSONObject inputJson = new JSONObject(input);;
        /* Get login name and password from user. */
        String valueString = inputJson.get("value").toString();
        /* Check if input value(String) is parseable to Integer. */
        boolean isIntegerValue = false;
        try {
            Integer.parseInt(valueString);
            isIntegerValue = true;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();

        }
        if(isIntegerValue) {
               int value = Integer.parseInt(valueString);
               if( value > 0 && value < Integer.MAX_VALUE) {
                   A02_SQL sql = new A02_SQL();
                   /* Try to update value for A01 in SQL. */
                   if (sql.updateDatabaseIntervalA02(value)) {
                       SerialHandler.getA02().setUpdateInterval(value); // Set update timer for A01.
                       String data = "<p class=\"text-center text-success\"> Value changed to " + valueString + " minutes! </p>";
                       return new ResponseEntity<>(data, HttpStatus.OK);
                   } else {
                       String data = "<p class=\"text-center text-danger\"> Error, could not update value! </p>";
                       return new ResponseEntity<>(data, HttpStatus.CONFLICT);
                   }
               }
               else{
                   String data = "<p class=\"text-center text-danger\"> Error, Value is negative or too high! </p>";
                   return new ResponseEntity<>(data, HttpStatus.CONFLICT);
               }
           }
           else{
               String data = "<p class=\"text-center text-danger\"> Invalid input, not a number </p>";
               return new ResponseEntity<>(data, HttpStatus.CONFLICT);
           }

    }
}
