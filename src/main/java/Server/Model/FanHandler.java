package Server.Model;


import GPIO.GPIO_DO;
import Server.SQL.Fan_SQL;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FanHandler {
    private GPIO_DO fan;
    private Fan_SQL fanSQL;

    public FanHandler() {
        this.fan = new GPIO_DO(1);
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
                        float serverTemperature = Float.parseFloat(ServerInformation.getServerCPUTemperature());
                        if(serverTemperature > getSetpoint()){
                            fan.setPinHIGH();
                        }
                        else{
                            fan.setPinLow();
                        }

                    }
                    catch(Exception ex){
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
        boolean updateSQL = getFanSQL().insertValuesServerFan(setpoint);
        return updateSQL;
    }


}
