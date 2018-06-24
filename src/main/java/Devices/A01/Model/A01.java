package Devices.A01.Model;

public class A01 {

    private float temperature;      // Current temperature value.
    private float humidity;         // Current humidity value.
    private float pressure;         // Current pressure value.

    private float temperatureOLD;   // Temperature value last time it was posted to SQL.
    private float humidityOLD;      // Humidity value last time it was posted to SQL.
    private float pressureOLD;      // Pressure value last time it was posted to SQL.

    private int updateInterval; // Interval time to send values to SQL.

    public A01(){
        this.updateInterval = 30;   // Minutes
        startThread();

        }


    private void startThread(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(getUpdateInterval() * 1000 * 1000);
                    System.out.println("StartThread A01");
                    checkValuesAndPostSQL();
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });
        t.start();

    }

    private void checkValuesAndPostSQL(){
        /* Check if there is updated values, post these to SQL if there is. */
        if( (getHumidity() != getHumidityOLD()) ||
                (getPressure() != getHumidityOLD()) ||
                (getTemperature() != getTemperatureOLD()) ){
            A01_SQL sql = new A01_SQL();
            sql.insertValuesA01(getTemperature(),getHumidity(),getPressure());  //Insert to SQL, Home - > Temperature.
            System.out.println("Updated Values A01");
            setTemperatureOLD(getTemperature());
            setHumidityOLD(getHumidity());
            setPressureOLD(getPressure());
        }
        else{
            System.out.println("NO Updated Values A01");
        }
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
