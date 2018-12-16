package Server.Model;


import GPIO.GPIO_DO;
import Server.SQL.Fan_SQL;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FanHandler {
    private GPIO_DO fan;
    private Fan_SQL fanSQL;
    private boolean fanIsRunning;

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
                            setFanIsRunning(true);
                        }
                        else{
                            fan.setPinLow();
                            setFanIsRunning(false);

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

    public boolean isFanIsRunning() {
        return fanIsRunning;
    }

    public void setFanIsRunning(boolean fanIsRunning) {
        this.fanIsRunning = fanIsRunning;
    }
}
