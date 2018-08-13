package Server.Model;

import GPIO.Template.Digital_Output;
import Server.SQL.Fan_SQL;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FanHandler {
    private Digital_Output fan;
    private Fan_SQL fanSQL;
    private float setpoint;

    public FanHandler() {
        this.fan = new Digital_Output(1);
        this.fanSQL = new Fan_SQL();
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
                        if(serverTemperature > getSetpoint()){
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

    private Fan_SQL getFanSQL(){
        return this.fanSQL;
    }

    public float getSetpoint() {
        return getFanSQL().selectSetpointFromSQL();
    }

    public boolean setSetpoint(float setpoint) {
        boolean updateSQL = getFanSQL().updateSetpointSQL(setpoint);
        return updateSQL;
    }


}
