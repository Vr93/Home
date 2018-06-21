/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;


/**
 *
 * @author vegard
 */
@Service
public class Digital_Output_1 {
    private GpioController gpio;
    private GpioPinDigitalOutput pin;


    public Digital_Output_1(){
        /* Initialize the GPIO on the Raspberry pi. */
        this.gpio = GpioFactory.getInstance();
        /* User can select which IO pin to use. */
        Pin pinNumberPin = getSelectedPin(2);
        /* Set pin number and pin state to LOW on startup. */
        this.pin = gpio.provisionDigitalOutputPin(pinNumberPin, PinState.LOW);
        /* Set shutdown options for the pin. */
        this.pin.setShutdownOptions(true, PinState.LOW);
        test();
    }


    private void test(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(3000);
                        System.out.println("State -> " + getState());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
    /**
     * Set the pin state HIGH. 
     */
    public void setPinHIGH(){
        /* Check if the gpio has instance. If not it will try to set instance. */
        if(getGpio() == null){
            GpioController gpio = getGpio();
            gpio = GpioFactory.getInstance();
        }
        /* Set pin to LOW. */
        if(getGpio() != null){
        getPin().high();
        }
    }
    
    /**
     * Set the pin state LOW.
     */
    public void setPinLow(){
        /* Check if the gpio has instance. If not it will try to set instance. */
        if(getGpio() == null){
            GpioController gpio = getGpio();
            gpio = GpioFactory.getInstance();
        }
        /* Set pin to LOW. */
        if(getGpio() != null){
        getPin().low();
        }
    }

    public boolean getState(){
        return getPin().getState().isHigh();
    }
    
    /**
     * Returns the pin as object for the raspberry pi GPIO.
     * @return GpioPinDigitalOutput, object of the pin.
     */
    private GpioPinDigitalOutput getPin() {
        return pin;
    }
    /**
     * Returns the gpio as object for the raspberry pi.
     * @return GpioController, object of the gpio.
     */
    private GpioController getGpio() {
        return gpio;
    }
    /**
     * 
     * @param pin, number for the pin selected.
     * @return Pin, returns a Pin object of the pin selected.
     */
    private Pin getSelectedPin(int pin){
        Pin selectedPin = null;
        switch(pin){
            case 0:
                selectedPin = RaspiPin.GPIO_00;
             break;
             case 1:
                selectedPin = RaspiPin.GPIO_01;
             break;
             case 2:
                selectedPin = RaspiPin.GPIO_02;
             break;
             case 3:
                selectedPin = RaspiPin.GPIO_03;
             break;
             
             case 4:
                selectedPin = RaspiPin.GPIO_04;
             break;
             case 5:
                selectedPin = RaspiPin.GPIO_05;
             break;
             case 6:
                selectedPin = RaspiPin.GPIO_06;
             break;
             case 7:
                selectedPin = RaspiPin.GPIO_07;
             break;
             case 8:
                selectedPin = RaspiPin.GPIO_08;
             break;
             case 9:
                selectedPin = RaspiPin.GPIO_09;
             break;
             case 10:
                selectedPin = RaspiPin.GPIO_10;
             break;
             case 11:
                selectedPin = RaspiPin.GPIO_11;
             break;
             case 12:
                selectedPin = RaspiPin.GPIO_12;
             break;
             case 13:
                selectedPin = RaspiPin.GPIO_13;
             break;
             case 14:
                selectedPin = RaspiPin.GPIO_14;
             break;
              case 15:
                selectedPin = RaspiPin.GPIO_15;
             break;
              case 16:
                selectedPin = RaspiPin.GPIO_16;
             break;
              case 17:
                selectedPin = RaspiPin.GPIO_17;
             break;
              case 18:
                selectedPin = RaspiPin.GPIO_18;
             break;
              case 19:
                selectedPin = RaspiPin.GPIO_19;
             break;
              case 20:
                selectedPin = RaspiPin.GPIO_20;
             break;
              case 21:
                selectedPin = RaspiPin.GPIO_21;
             break;
              case 22:
                selectedPin = RaspiPin.GPIO_22;
             break;
              case 23:
                selectedPin = RaspiPin.GPIO_23;
             break;
              case 24:
                selectedPin = RaspiPin.GPIO_24;
             break;
              case 25:
                selectedPin = RaspiPin.GPIO_25;
             break;
              case 26:
                selectedPin = RaspiPin.GPIO_26;
             break;
              case 27:
                selectedPin = RaspiPin.GPIO_27;
             break;
              case 28:
                selectedPin = RaspiPin.GPIO_28;
             break;
              case 29:
                selectedPin = RaspiPin.GPIO_29;
             break;
              case 30:
                selectedPin = RaspiPin.GPIO_30;
             break;
              case 31:
                selectedPin = RaspiPin.GPIO_31;
             break;
        }
        return selectedPin;
    }
      
    
}
   
   
    
    

