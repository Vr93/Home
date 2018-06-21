/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vegard
 */
@Service
public class I2C_Smoke_Detector {
    private static final int MQ2_Sensor = 0x48; // address pin not connected (FLOATING)
    private I2CBus i2c;
    private I2CDevice device;

    public I2C_Smoke_Detector(){
        try {
            i2c = I2CFactory.getInstance(I2CBus.BUS_1);
            this.device = i2c.getDevice(MQ2_Sensor);
        } catch (I2CFactory.UnsupportedBusNumberException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAnalogValue0(){ 
        int value = 0;
        try {
            byte[] buffer = new byte[2];
            device.write((byte)0);
            value = device.read();
            //int amountBytes = device.read(1, buffer, 0, 2);
            System.out.println("i2c value -> " + value);
            //System.out.println("buffer: -> " + amountBytes  + " :: " + " Data -> "   + Arrays.toString(buffer) );
            //value = buffer[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /*public int I2CScanner(){
        List<Integer> validAddresses = new ArrayList<Integer>();

        for (int i = 1; i < 128; i++) {
            try {
                I2CDevice device  = i2c.getDevice(i);
                device.write((byte)0);
                validAddresses.add(i);
            } catch (Exception ignore) { }
        }

        System.out.println("Found: ---");
        for (int a : validAddresses) {
            System.out.println("Address: " + Integer.toHexString(a));
        }
        System.out.println("----------");
        return 100;
    }*/
    
}
