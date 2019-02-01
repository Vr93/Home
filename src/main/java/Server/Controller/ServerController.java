package Server.Controller;

import Server.Model.ServerInformation;
import Server.Model.SystemLED;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServerController {

    @Autowired
    private SystemLED systemLED;

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
