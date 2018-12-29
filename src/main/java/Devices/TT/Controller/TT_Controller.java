package Devices.TT.Controller;

import Devices.TT.Database.TT_Database;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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


}
