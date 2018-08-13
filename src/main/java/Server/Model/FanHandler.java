package Server.Model;

import GPIO.Template.Digital_Output;
import Server.SQL.Fan_SQL;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FanHandler {
    private Digital_Output fan;
    private Fan_SQL fanSQL;

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
        /* If there is no values in SQL, insert fallback value of 50*C */
        if(getFanSQL().selectSetpointFromSQL() == 0){
            getFanSQL().insertValuesServerFan(50);
        }
        return getFanSQL().selectSetpointFromSQL();
    }

    public boolean setSetpoint(float setpoint) {
        boolean updateSQL = getFanSQL().updateSetpointSQL(setpoint);
        return updateSQL;
    }


}