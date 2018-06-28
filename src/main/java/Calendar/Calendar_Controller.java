package Calendar;


import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class Calendar_Controller {

    @PostMapping(path="/index/login", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public String save(@RequestBody String data) {
        System.out.println(data);
        //JSONObject inputJson = new JSONObject(data);;
        String retunValue = "";
        //System.out.println("login -> "  +inputJson.toString());
        /*if(inputJson.has("name") && (inputJson.has("password"))) {*/
            /* Get login name and password from user. */
           /* String name = inputJson.get("name").toString();
            String password = inputJson.get("password").toString();*/

            /* Check input from user with SQL database. */
           /* Calendar_SQL sql = new Calendar_SQL();
            String[] sqlData = sql.selectLogin(name);
            System.out.println("from sql.." + sqlData[0] + "   " + sqlData[1]);
            if( sqlData[0].equals(name) && sqlData[1].equals(password) && !sqlData[2].equals(""))
                retunValue = "<iframe src=\"https://calendar.google.com/calendar/" +
                    "embed?title=Vegard&amp;showDate=0&amp;showPrint=0&amp;" +
                    "showCalendars=0&amp;showTz=0&amp;mode=WEEK&amp;height=500" +
                    "&amp;wkst=2&amp;hl=no&amp;bgcolor=%23ffffff&amp;src=vegardrognee" +
                    "%40gmail.com&amp;color=%23711616&amp;ctz=Europe%2FOslo\" style=\"border-width:0\"" +
                    " width=\"500\" height=\"500\" frameborder=\"0\" scrolling=\"no\"></iframe>";
        }
        else {
            retunValue = "error";
        }*/
        return retunValue;
    }


}
