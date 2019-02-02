package Devices.Com_Master.Model;

import GPIO.GPIO_DO;
import org.springframework.stereotype.Service;

@Service
public class Serial_LED {
    private GPIO_DO serial_LED;
    private int blinkNum;


    public Serial_LED() {
        this.serial_LED = new GPIO_DO(1);
        this.blinkNum = 0;
        blinkLed();
    }


    private GPIO_DO getSerialLED() {
        return this.serial_LED;
    }

    private void blinkLed(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        int blinkNum = getBlinkNum();
                        if (blinkNum > 0) {
                            getSerialLED().setPinHIGH();
                            Thread.sleep(100);
                            getSerialLED().setPinLow();
                            setBlinkNum(blinkNum - 1);
                            Thread.sleep(100);
                        }
                    }
                    catch (InterruptedException ex){
                        ex.printStackTrace();
                    }

                }
            }
        });
        t.start();
    }

    public void blink(){
        int currentValue = getBlinkNum();
        if(currentValue < 10){
            setBlinkNum(currentValue + 1);
        }
    }

    private int getBlinkNum() {
        return blinkNum;
    }

    private void setBlinkNum(int blinkNum) {
            this.blinkNum = blinkNum;
    }
}
