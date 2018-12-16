package GPIO;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;



/**
 *
 * @author vegard
 */
public class GPIO_DI {
    private GpioController gpio;
    private GpioPinDigitalInput pin;

    public GPIO_DI(int pinNumber,int resistorOptions){
        /* Initialize the GPIO on the Raspberry pi. */
        this.gpio = GpioFactory.getInstance();
        /* User can select which IO pin to use. */
        Pin pinNumberPin = getSelectedPin(pinNumber);
        /* Option for the pin, 1 -> pullup resistor, 2 -> pulldown resistor, all other will set it to none. */
        PinPullResistance pinOptions = getSelectedOptions(resistorOptions);
        /* Set pin number and pin option. */
        this.pin = gpio.provisionDigitalInputPin(pinNumberPin, pinOptions);
        /* Set shutdown options for the pin. */
        this.pin.setShutdownOptions(true);
    }

    /**
     * The input state of the digital IO Pin.
     * @return boolean, state of the pin (HIGH/LOW).
     */
    public boolean getPinState(){
        return getPin().getState().isHigh();
    }

    /**
     * The Gpio instance of the pin.
     * @return GpioPinDigitalInput.
     */
    private GpioPinDigitalInput getPin() {
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

    /**
     * User can select which IO pin to use as a number, returns a object of this pin.
     * @param option, input number of the option for the pin.
     * @return PinPullResistance, Object for the selected option for pin.
     */
    private PinPullResistance getSelectedOptions(int option){
        PinPullResistance pinOption = null;
        /* If option is 1, set the pin to pull down. */
        if(option == 1){
            pinOption = PinPullResistance.PULL_DOWN;
        }
        /* If option is 2, set the pin to pull up. */
        else if(option == 2){
            pinOption = PinPullResistance.PULL_UP;
        }
        /* If unknown option is selected, set the pull resistances for the pin off. */
        else{
            pinOption = PinPullResistance.OFF;
        }
        return pinOption;
    }

}