package Model;

import com.pi4j.io.serial.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * This example code demonstrates how to perform serial communications using the Raspberry Pi.
 *
 * @author Robert Savage
 */
@Service
public class SerialPortTest {
    private Serial serial;

    public SerialPortTest() {
        this.serial = SerialFactory.createInstance();
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
            config.device(SerialPort.getDefaultPort())
                    .baud(Baud._9600)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);
            // open the default serial device/port with the configuration settings
            serial.open(config);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        receiveData();
    }

    private void receiveData(){
        // create and register the serial data listener
        serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {

                // NOTE! - It is extremely important to read the data received from the
                // serial port.  If it does not get read from the receive buffer, the
                // buffer will continue to grow and consume memory.

                // print out the data received to the console
                try {
                    System.out.println("Serial Receive: " + event.getHexByteString());
                    System.out.println("Serial Receive: " + event.getAsciiString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
