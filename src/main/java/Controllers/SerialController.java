package Controllers;

import Model.Digital_Output_1;
import Model.SerialPortTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerialController {

    @Autowired
    private SerialPortTest serialPortTest;

}
