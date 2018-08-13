package Server.Model;

import GPIO.Template.Digital_Output;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FanHandler {
    private Digital_Output fan;
    private float setpoint;

    public FanHandler() {
        this.fan = new Digital_Output(1);
        this.setpoint = 40.0f;
        startFan();
    }

    private void startFan() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000*30);
                        float serverTemperature = ServerInformation.getServerCPUTemperature();
                        System.out.println("server temp: " + serverTemperature);
                        if(serverTemperature > setpoint){
                            fan.setPinHIGH();
                            System.out.println("Fan is on");
                        }
                        else{
                            fan.setPinLow();
                            System.out.println("Fan is off");
                        }

                    }
                    catch(IOException | InterruptedException ex){
                        ex.printStackTrace();
                    }

                }
            }
        });
        t.start();
    }

    public boolean getFanState(){
        return fan.getState();
    }

}
