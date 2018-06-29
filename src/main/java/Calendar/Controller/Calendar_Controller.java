package Calendar.Controller;


import Calendar.Model.Calendar;
import Calendar.SQL.Calendar_SQL;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Calendar_Controller {


    @PostMapping(path="/index/login")
    public ResponseEntity<?> save(@RequestBody String input) {
        /* Get input and turn this String to JSON */
        JSONObject inputJson = new JSONObject(input);;
        /* Get login name and password from user. */
        String name = inputJson.get("name").toString();
        String password = inputJson.get("password").toString();
        /* Check input from user with SQL database. */
        Calendar_SQL sql = new Calendar_SQL();
        String[] sqlData = sql.selectLogin(name);
        /* Check if JSON contains name and password, and this matches with SQL. */
        if( inputJson.has("name") && inputJson.has("password")
                && sqlData[0].equals(name) && sqlData[1].equals(password)
                && !sqlData[0].equals("") && !sqlData[1].equals("")) {
            String data = "<div class=\"row\"> " +
                    "<div class=\"col-lg-4 panel panel-default\">" +
                            Calendar.getCalendarIframe_Vegard() +
                        "</div>" +
                        "<div class=\"col-lg-4 panel panel-default\">" +
                            "<h3 class=\"text-center\"> Hello world </h3>" +
                        "</div>" +
                    "</div>";
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        /* If the input from client does not match, return error code. */
        else {
            String data = "<p class='text-center'> Invalid username or password! <p>";
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }


}
