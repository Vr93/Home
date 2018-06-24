package Devices.A01.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class A01_Controller {


    @RequestMapping(value="/A01",method = RequestMethod.GET)
    public String A01(){
        return "A01";
    }

}
