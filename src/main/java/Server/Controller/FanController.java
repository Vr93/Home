package Server.Controller;

import Server.Model.FanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FanController {

    @Autowired
    private FanHandler fanHandler;

    @RequestMapping(value="/server/fanOutput", method = RequestMethod.GET)
    @ResponseBody
    public String getFanState() {
        return String.valueOf(fanHandler.getFanState());
    }
}
