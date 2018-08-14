package Server.Controller;

import Server.Model.ServerInformation;
import com.mysql.fabric.Server;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ServerController {

    @RequestMapping(value="/server/cputemperature", method = RequestMethod.GET)
    @ResponseBody
    public String getCPUTemperature() {
        String retVal;
        try {
            retVal = Float.toString(ServerInformation.getServerCPUTemperature());
        }
        catch(IOException | InterruptedException ex){
            retVal = "Error, could not get CPU Temp!";
        }
        return retVal;
    }
}
