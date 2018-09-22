package Devices.Com_Master.Model;



import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This example code demonstrates how to perform serial communications using the Raspberry Pi.
 *
 * @author Robert Savage
 */
@Service
public class SerialPort_Master {
    private static SerialPort serialPort;
    public SerialPort_Master(){
        connect();
    }

    private void connect(){
        serialPort = new SerialPort("/dev/ttyAMA0");
        try {
            serialPort.openPort();
            System.out.println("Serial port is opened: " + serialPort.getPortName());
            serialPort.setParams(9600, 8, 1, 0);
            //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
            //Well, for example, we need to know what came some data, thus in the mask must have the
            //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states
            //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
            int mask = SerialPort.MASK_RXCHAR;
            //Set the prepared mask
            serialPort.setEventsMask(mask);
            //Add an interface through which we will receive information about events
            serialPort.addEventListener(new SerialPortReader());

            //serialPort.writeString("HelloWorld");
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }


    static class SerialPortReader implements SerialPortEventListener {
        @Override
        public void serialEvent(SerialPortEvent event) {
            //Object type SerialPortEvent carries information about which event occurred and a value.
            //For example, if the data came a method event.getEventValue() returns us the number of bytes in the input buffer.
            System.out.println(event.getEventType());
            if(event.isRXCHAR()){
                if(event.getEventValue() == 10){
                    try {
                        String data= serialPort.readString();
                        System.out.println(data);
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                }
            }
            //If the CTS line status has changed, then the method event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
            else if(event.isCTS()){
                if(event.getEventValue() == 1){
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if(event.isDSR()){
                if(event.getEventValue() == 1){
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }





}
