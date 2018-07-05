package Devices.A01.Controller;


import Devices.A01.SQL.A01_SQL;
import Devices.Com_Master.Model.SerialHandler;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class A01_Controller {

    @GetMapping(value = "/A01/dataDay")
    public String[] A01_DataDay(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(0);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/dataWeek")
    public String[] A01_DataWeek(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(7);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/dataMonth")
    public String[] A01_DataMonth(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(31);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/dataYear")
    public String[] A01_DataYear(){
        A01_SQL sql = new A01_SQL();
        ArrayList<String> arrayList = sql.selectA01Data(365);
        String[] data = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++){
            String jsonString = arrayList.get(i);
            data[i] = jsonString;
        }
        return data;
    }

    @GetMapping(value = "/A01/database_interval")
    public long[] A01_Interval_Setting(){
        A01_SQL sql = new A01_SQL();
        return sql.selectDatabaseIntervalA01();
    }

    @GetMapping(value = "/A01/status")
    public String A01_Status(){
        return SerialHandler.getA01().getStatus();
    }

    @PostMapping(path="/A01/database_interval_update")
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
                   A01_SQL sql = new A01_SQL();
                   /* Try to update value for A01 in SQL. */
                   if (sql.updateDatabaseIntervalA01(value)) {
                       SerialHandler.getA01().setUpdateInterval(value); // Set update timer for A01.
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
