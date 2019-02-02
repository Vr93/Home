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
    private AverageFilter averageFilter;

    public FanHandler() {
        this.fan = new GPIO_DO(26);
        this.fanSQL = new Fan_SQL();
        this.averageFilter = new AverageFilter(60);
        startFan();
    }



    private void startFan() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        getAverageFilter().insertValue(Float.parseFloat(ServerInformation.getServerCPUTemperature()));
                        float averageValue = getAverageFilter().getAverageValue();
                        /* If average temperature is above setpoint, start fan. */
                        if(averageValue > getSetpoint()){
                            fan.setPinHIGH();
                            setFanIsRunning(true);
                        }
                        /* If average temperature is below setpoint and a threshold for hysteresis, stop fan. */
                        if(averageValue < getSetpoint() - 2.0f) {
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

    public AverageFilter getAverageFilter() {
        return averageFilter;
    }


}
