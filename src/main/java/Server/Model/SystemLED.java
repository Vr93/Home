package Server.Model;

        import GPIO.GPIO_DO;
        import org.springframework.stereotype.Service;

@Service
public class SystemLED {
    private GPIO_DO greenLED;

    public SystemLED(){
        this.greenLED = new GPIO_DO(4);
        turnOnLED();
    }

    /**
     * Turns on the green LED whenever the program starts.
     */
    private void turnOnLED(){
        getSystemLED().setPinHIGH();
    }


    private GPIO_DO getSystemLED(){
        return this.greenLED;
    }
}
