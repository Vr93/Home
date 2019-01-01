package Devices.TT.Controller;

import Devices.TT.Database.TT_Database;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
            if(obj.has("name") && obj.has("device")){
                try {
                    String name = obj.get("name").getAsString();
                    int device = obj.get("device").getAsInt();
                    /* Create a dropdown item for each element in the database. */
                    navbar = navbar + "<a class=\"dropdown-item\"";
                    navbar = navbar + "onclick=\"temperaturDevice('" + name + "'," + device + ")\">" + name + "</a >";
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
     * @param input, JSON Object, "id" the device id, "dateFrom" and "dateTo", as format yyyy-mm-dd.
     * @return String[], each string is an JSON object.
     */
    @PostMapping(path="/TT/data")
    public ResponseEntity<?> TT_data(@RequestBody(required = true) String input) {
        try{
            /* Get input and turn this String to JSON */
            JSONObject inputJson = new JSONObject(input);
            /* Parse JSON from client, and fetch id and interval time. */
            int deviceId = inputJson.getInt("id");
            String dateFrom = inputJson.getString("dateFrom");
            String dateTo = inputJson.getString("dateTo");
            /* Get the data from database for given device and the interval of how many days to go back for data. */
            TT_Database sql = new TT_Database();
            ArrayList<String> list = sql.selectDataTT(deviceId,dateFrom,dateTo);
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
            int deviceId = inputJson.getInt("id");
            /* Get the min and max date for all data for the given device. */
            TT_Database sql = new TT_Database();
            String[] data = sql.selectMinMaxDate(deviceId);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (NumberFormatException ex){
            String data = "<p class=\"text-center text-danger\"> Error, could not load data! </p>";
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }
    }


}
