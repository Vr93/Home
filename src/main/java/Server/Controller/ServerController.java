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
            return ServerInformation.getServerCPUTemperature();
    }

    @RequestMapping(value="/server/cpuinformation", method = RequestMethod.GET)
    @ResponseBody
    public String[] getCPUInformation() {
        return ServerInformation.getServerInformation();
    }

    @RequestMapping(value="/server/storage", method = RequestMethod.GET)
    @ResponseBody
    public String[] getStorage() {
        return ServerInformation.getServerStorage();
    }
}
