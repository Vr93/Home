/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPIO;


import GPIO.Template.Digital_Output;
import GPIO.Template.IO_PWM;
import org.springframework.stereotype.Component;

/**
 *
 * @author vegard
 */
@Component
public class GPIO_Handler {

    private Digital_Output led1;
    private IO_PWM led2;

    public GPIO_Handler() {
        led1 = new Digital_Output(2);
        led2 = new IO_PWM(1);
        //blinkLED();
        ///pwmLED();
    }

    private void blinkLED() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);

                        System.out.println("LED1 -> HIGH");
                        Thread.sleep(1000);
                        led1.setPinLow();
                        System.out.println("LED1 -> LOW");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public void setPinHIGH(){
        led1.setPinHIGH();
    }

    public void setPinLow(){
        led1.setPinLow();
    }

    private void pwmLED() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        if (led2.getPwmPercent() < 100) {
                            int value = led2.getPwmPercent();
                            led2.setPwm(value + 1);
                            System.out.println("PWM(%) -> " + value);
                        } else {
                            led2.setPwm(0);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

}
