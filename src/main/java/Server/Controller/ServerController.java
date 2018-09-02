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

    @RequestMapping(value="/server/platformname", method = RequestMethod.GET)
    @ResponseBody
    public String getPlatformName() {
        return ServerInformation.getPlatformName();
    }

    @RequestMapping(value="/server/serialnumber", method = RequestMethod.GET)
    @ResponseBody
    public String getSerialNumber() {
        try {
            return ServerInformation.getSerialNumber();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get serial number!";
        }
    }

    @RequestMapping(value="/server/cpurevision", method = RequestMethod.GET)
    @ResponseBody
    public String getCPURevision() {
        try {
            return ServerInformation.getCPURevision();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get cpu revision!";
        }
    }

    @RequestMapping(value="/server/cpuarchitecture", method = RequestMethod.GET)
    @ResponseBody
    public String getCPUArchitecture() {
        try {
            return ServerInformation.getCPUArchitecture();
        }
        catch(IOException | InterruptedException ex){
            return "Error, could not get cpu architecture!";
        }
    }
}
