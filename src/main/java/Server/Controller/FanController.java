package Server.Controller;

import Server.Model.FanHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FanController {

    @Autowired
    private FanHandler fanHandler;

    @RequestMapping(value="/serverFan/output", method = RequestMethod.GET)
    @ResponseBody
    public String getFanState() {
        return String.valueOf(fanHandler.getFanState());
    }

    @RequestMapping(value="/serverFan/setpoint", method = RequestMethod.GET)
    @ResponseBody
    public String getFanSetpoint() {
        return String.valueOf(fanHandler.getSetpoint());
    }

    @PostMapping(path="/serverfan/updateSetpoint")
    public ResponseEntity<?> save(@RequestBody String input) {
        /* Get input and turn this String to JSON */
        JSONObject inputJson = new JSONObject(input);;
        /* Get setpoint from user. */
        String valueString = inputJson.get("setpoint").toString();
        /* Check if input value(String) is parseable to Integer. */
        boolean isFloatValue = false;
        try {
            Float.parseFloat(valueString);
            isFloatValue = true;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            String data = "<p class=\"text-center text-danger\"> Invalid input, not a number </p>";
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);

        }
        if(isFloatValue) {
            float value = Float.parseFloat(valueString);
            if( value > 35 && value < 60) {

                /* Try to update value for server fan in SQL. */
                if (fanHandler.setSetpoint(value)) {
                    String data = "<p class=\"text-center text-success\"> Value changed to " + valueString + " minutes! </p>";
                    return new ResponseEntity<>(data, HttpStatus.OK);
                } else {
                    String data = "<p class=\"text-center text-danger\"> Error, could not update value! </p>";
                    return new ResponseEntity<>(data, HttpStatus.CONFLICT);
                }
            }
            else{
                String data = "<p class=\"text-center text-danger\"> Error, Value most be between 35 and 60! </p>";
                return new ResponseEntity<>(data, HttpStatus.CONFLICT);
            }
        }
        else{
            String data = "<p class=\"text-center text-danger\"> Invalid input, not a number </p>";
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);
        }

    }
}
