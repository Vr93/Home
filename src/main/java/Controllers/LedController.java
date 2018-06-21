package Controllers;

import Model.Digital_Output_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.io.IOException;

@RestController
public class LedController {

    @Autowired
    private Digital_Output_1 digital_output_1;



   /* @RequestMapping(value="/digital_output_1_state", method= RequestMethod.GET)
    public String getDigitalOutput1State(ModelMap map) {
        // TODO: retrieve the new value here so you can add it to model map
        map.addAttribute("digital_output_1_state",digital_output_1.getState());

        // change "myview" to the name of your view
        return "index :: #digital_output_1_state";
    }

    @RequestMapping(value = "/digital_output_1_value", method = RequestMethod.POST)
    public void uploadingPost(@RequestParam("digital_output_1_value") String inputValue) throws IOException {
        int pinState = Integer.parseInt(inputValue);
        if(pinState == 0){
            digital_output_1.setPinLow();
        }
        else if(pinState == 1){
            digital_output_1.setPinHIGH();
        }
    }*/

    @GetMapping(value = "/state")
    public String getState(){
        return String.valueOf(digital_output_1.getState());
    }

    @PostMapping(value="/changeState")
    public String postLedValue(@RequestBody String input){
        String status = "";
        if(input.equalsIgnoreCase("on")){
            digital_output_1.setPinHIGH();
            status = "high";
        }
        else if(input.equalsIgnoreCase("off")){
            digital_output_1.setPinLow();
            status = "low";
        }
        return status;
    }

}
