package GPIO;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;



/**
 *
 * @author vegard
 */
public class GPIO_AnalogIn {
    private GpioController gpio;
    private GpioPinAnalogInput pin;

    public GPIO_AnalogIn(int pinNumber,int resistorOptions){
        /* Initialize the GPIO on the Raspberry pi. */
        this.gpio = GpioFactory.getInstance();
        /* User can select which IO pin to use. */
        Pin pinNumberPin = getSelectedPin(pinNumber);
        /* Set pin number and pin option. */
        this.pin = gpio.provisionAnalogInputPin(pinNumberPin);
        /* Set shutdown options for the pin. */
        this.pin.setShutdownOptions(true);
    }

    /**
     * The input value of the analog IO Pin.
     * @return double, value of the pin (HIGH/LOW).
     */
    public double getPinValue(){
        return getPin().getValue();
    }

    /**
     * The Gpio instance of the pin.
     * @return GpioPinAnalogInput.
     */
    private GpioPinAnalogInput getPin() {
        return pin;
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