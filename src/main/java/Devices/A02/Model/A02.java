package Devices.A02.Model;

import Devices.A02.SQL.A02_SQL;

import java.util.BitSet;

public class A02 {

    private float temperature;      // Current temperature value.
    private float humidity;         // Current humidity value.
    private float pressure;         // Current pressure value.
    private int error;              // error code for A02, bit 0 = Temp sensor error.

    private float temperatureOLD;   // Temperature value last time it was posted to SQL.
    private float humidityOLD;      // Humidity value last time it was posted to SQL.
    private float pressureOLD;      // Pressure value last time it was posted to SQL.

    private int updateInterval; // Interval time to send values to SQL.

    public A02(){
        this.updateInterval = getUpdateIntervalFromSQL();   // Minutes
        startThread();
        }

    private int getUpdateIntervalFromSQL(){
        int value = 30; // If no value is found, set to 30 mins.
        A02_SQL sql = new A02_SQL();
        /* Check if there is value from SQL */
        long sqlData[] = sql.selectDatabaseIntervalA02();
        if(sqlData[0] > 0 && sqlData[0] < Integer.MAX_VALUE){
            value = (int)sqlData[0];
        }
        return value;
    }

    private void startThread(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(getUpdateInterval() * 60000); // Millis to minutes.
                        checkValuesAndPostSQL();
                    }
                    catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }

    private void checkValuesAndPostSQL(){
        /* If there is no error, proceed. */
        if(getError() == 0) {
            /* Check if there is updated values, post these to SQL if there is. */
            if ((getHumidity() != getHumidityOLD()) || (getPressure() != getPressureOLD()) || (getTemperature() != getTemperatureOLD())) {
                A02_SQL sql = new A02_SQL();
                sql.insertValuesA02(getTemperature(), getHumidity(), getPressure());  //Insert to SQL, Home - > Temperature.
                setTemperatureOLD(getTemperature());
                setHumidityOLD(getHumidity());
                setPressureOLD(getPressure());
            } else {
                //Do nothing
            }
        }
    }

    public String getStatus(){
        String status = "";
        BitSet bit = new BitSet(getError());
        if(getError() == 0) {
            status = "<p class\"text-success\"> No errors </p> <br>";
        }
        else{
            if (bit.get(0)) {
                status =  status  + "<p class=\"text-danger\"> Temperature sensor (BMP280) is not working! </p> <br>";
            }
        }
        return status;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemperatureOLD() {
        return temperatureOLD;
    }

    public void setTemperatureOLD(float temperatureOLD) {
        this.temperatureOLD = temperatureOLD;
    }

    public float getHumidityOLD() {
        return humidityOLD;
    }

    public void setHumidityOLD(float humidityOLD) {
        this.humidityOLD = humidityOLD;
    }

    public float getPressureOLD() {
        return pressureOLD;
    }

    public void setPressureOLD(float pressureOLD) {
        this.pressureOLD = pressureOLD;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }
}
