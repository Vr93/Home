package Devices.Com_Master.Controller;


import Devices.A01.Model.A01;
import Devices.Com_Master.Model.SerialPort_Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerialController {


    @Autowired
    private SerialPort_Master serialPort;


}