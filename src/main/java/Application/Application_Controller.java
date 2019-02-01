package Application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Application_Controller {



    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value="/server",method = RequestMethod.GET)
    public String server(){
        return "Server";
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index_empty(){
        return "index";
    }


    @Value("${version.number}")
    private String versionNumber;
    @RequestMapping(value="/version", method = RequestMethod.GET)
    @ResponseBody
    public String getVersion() {
        return versionNumber;
    }
}
