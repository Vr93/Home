package Devices.TT.Controller;

import Devices.TT.Database.TT_Database;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class TT_Controller {

    /**
     * Fetches all temperature devices configured in the database and create html structured dropdown menu for the navbar.
     * @return HTML for navbar menu with dropdown for all temperature devices.
     */
    @RequestMapping(value="/navbar/temperature/devices", method = RequestMethod.GET)
    @ResponseBody
    public String getTTDevices() {
        /* Fetch device id and name from sql, as json object */
        TT_Database sql = new TT_Database();
        ArrayList<JsonObject> list  = sql.getDevices();

        /* Setup basic navbar dropdown menu for temperature devices. */
        String navbar = "<li class=\"nav-item dropdown\">";
        navbar = navbar + "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"temperatureNavDropdown\" " +
                "role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"> Temperature </a>";
        navbar = navbar + "<div class=\"dropdown-menu\" aria-labelledby=\"temperatureNavDropdown\">";

        /* Iterate the json object*/
        for(JsonObject obj: list){
            /* Check if json object has both device name and id. */
            if(obj.has("name") && obj.has("uid")){
                try {
                    String name = obj.get("name").getAsString();
                    String uid = obj.get("uid").getAsString();
                    /* Create a dropdown item for each element in the database. */
                    navbar = navbar + "<a class=\"dropdown-item\"";
                    navbar = navbar + "onclick=\"temperaturDevice('" + name + "','" + uid + "')\">" + name + "</a >";
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        }
        /* Finish the basic navbar dropdown for temperature devices. */
        navbar = navbar + "</div> </li>";

        return navbar;
    }

    /**
     * Returns the data for the given device from database.
     * @param input, JSON Object, "uid" the device uid, "dateFrom" and "dateTo", as format yyyy-mm-dd.
     * @return String[], each string is an JSON object.
     */
    @PostMapping(path="/TT/data")
    public ResponseEntity<?> TT_data(@RequestBody(required = true) String input) {
        try{
            /* Get input and turn this String to JSON */
            JSONObject inputJson = new JSONObject(input);
            /* Parse JSON from client, and fetch id and interval time. */
            String uid = inputJson.getString("uid");
            String dateFrom = inputJson.getString("dateFrom");
            String dateTo = inputJson.getString("dateTo");
            /* Get the data from database for given device and the interval of how many days to go back for data. */
            TT_Database sql = new TT_Database();
            ArrayList<String> list = sql.selectDataTT(uid,dateFrom,dateTo);
            /* Iterate the values fetched from database, each string is an json object. */
            String[] data = new String[list.size()];
            for(int i = 0; i < list.size(); i++){
                data[i] = list.get(i);
            }
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (NumberFormatException ex){
            String data = "<p class=\"text-center text-danger\"> Error, could not load data! </p>";
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
    }

    /**
     * Returns the latest data for the given device from database.
     * @param input, JSON Object, "uid" the device uid.
     * @return String, string is an JSON object.
     */
    @PostMapping(path="/TT/data/latest")
    public ResponseEntity<?> TT_data_Latest(@RequestBody(required = true) String input) {
        try{
            /* Get input and turn this String to JSON */
            JSONObject inputJson = new JSONObject(input);
            /* Parse JSON from client, and fetch id and interval time. */
            String uid = inputJson.getString("uid");
            /* Get the data from database for given device and the interval of how many days to go back for data. */
            TT_Database sql = new TT_Database();
            String jsonObj = sql.selectDataTTLatest(uid);

            return new ResponseEntity<>(jsonObj, HttpStatus.OK);
        }
        catch (NumberFormatException | JsonSyntaxException ex){
            String data = "<p class=\"text-center text-danger\"> Error, could not load data! </p>";
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
    }

    /**
     * Returns the min and max dates for given device for all data in the database.
     * @param input, input as json object, device id is required.
     * @return String[], min and max date, date formatted as string, yyyy-mm-dd.
     */
    @PostMapping(path="/TT/date/minmax")
    public ResponseEntity<?> TT_data_minmax(@RequestBody(required = true) String input) {
        try{
            /* Get input and turn this String to JSON */
            JSONObject inputJson = new JSONObject(input);
            /* Parse JSON from client, and fetch device id. */
            String uid = inputJson.getString("uid");
            /* Get the min and max date for all data for the given device. */
            TT_Database sql = new TT_Database();
            String[] data = sql.selectMinMaxDate(uid);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (NumberFormatException ex){
            String data = "<p class=\"text-center text-danger\"> Error, could not load data! </p>";
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
    }


}
