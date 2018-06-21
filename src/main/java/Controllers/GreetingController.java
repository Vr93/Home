package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

@ResponseBody
@RequestMapping(value="/greeting",method = RequestMethod.GET)
public String Greeting(){
    return "Message From SpringBoot Service - Hello World!";
}
/*
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){
        return "index";
    }*/

}