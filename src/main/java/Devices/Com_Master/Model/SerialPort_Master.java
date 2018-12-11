package Devices.Com_Master.Model;

import com.pi4j.io.serial.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SerialPort_Master {
    private Serial serial;
    //private SerialHandler serialHandler;

    public SerialPort_Master() {
        this.serial = SerialFactory.createInstance();
        //this.serialHandler = new SerialHandler();
        System.out.println("Starting serial!");
        try {
            // create serial config object
            SerialConfig config = new SerialConfig();

            // set default serial settings (device, baud rate, flow control, etc)
            //
            // by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
            // NOTE: this utility method will determine the default serial port for the
            //       detected platform and board/model.  For all Raspberry Pi models
            //       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
            //       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
            //       environment configuration.

            System.out.println("Default Port: " + SerialPort.getDefaultPort());
            config.device(SerialPort.getDefaultPort())
                    .baud(Baud._9600)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);
            // open the default serial device/port with the configuration settings
            serial.open(config);
            receiveData();
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receiveData(){
        // create and register the serial data listener
        System.out.println("Started serial reading!");
        serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {

                // NOTE! - It is extremely important to read the data received from the
                // serial port.  If it does not get read from the receive buffer, the
                // buffer will continue to grow and consume memory.

                // print out the data received to the console
                try {
                    //System.out.println("Serial Receive: " + event.getHexByteString());
                    System.out.println("Serial Receive: " + event.getAsciiString());

                    //serialHandler.handleSerialInput(event.getAsciiString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}