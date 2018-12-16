package Devices.TT.Model;

import Devices.TT.Database.TT_Database;

public class TT_device {

    private float temperature;      // Current temperature value.
    private float humidity;         // Current humidity value.
    private float pressure;         // Current pressure value.
    private float voltage;          // Current battery voltage value.

    private float temperatureOLD;   // Temperature value last time it was posted to the database.
    private float humidityOLD;      // Humidity value last time it was posted to the database.
    private float pressureOLD;      // Pressure value last time it was posted to the database.
    private float voltageOLD;       // Voltage value last time it was posted to the database.

    private int updateInterval; // Interval time to send values to SQL.
    private int device;         // The device ID.

    public TT_device(int device){
        this.updateInterval = 10;   // Minutes
        this.device = device;
        startThread();
    }

    /**
     * Checks if it should post values to the database, with the given time by update interval time.
     */
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

    /**
     * Checks received values and compare them to old ones.
     * If the fields of significant has changed, it will post these values to the database.
     */
    private void checkValuesAndPostSQL(){
            /* Check if there is updated values, post these to SQL if there is. */
            if ((getHumidity() != getHumidityOLD()) || (getPressure() != getPressureOLD()) || (getTemperature() != getTemperatureOLD())) {
                TT_Database sql = new TT_Database();
                sql.insertValues(getDevice(),getTemperature(), getHumidity(), getPressure(),getVoltage());  //Insert to SQL, Home - > Temperature.
                /* After posing value, set the last values to the last received. */
                setTemperatureOLD(getTemperature());
                setHumidityOLD(getHumidity());
                setPressureOLD(getPressure());
                setVoltageOLD(getVoltage());
            } else {
                //Do nothing
            }
        }

    /**
     * Returns the last received temperature reading.
     * @return Temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * Sets the last temperature reading.
     * @param temperature
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     * Returns the last received humidity reading.
     * @return Humidity
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     * Set the last humidity reading.
     * @param humidity
     */
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    /**
     * Returns the last received pressure reading.
     * @return Pressure
     */
    public float getPressure() {
        return pressure;
    }

    /**
     * Set the last pressure reading.
     * @param pressure
     */
    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    /**
     * Returns the last received voltage reading.
     * @return Voltage
     */
    public float getVoltage() {
        return voltage;
    }

    /**
     * Set the last voltage reading.
     * @param voltage
     */
    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    /**
     * Returns the update interval for logging to database, in minutes.
     * @return UpdateInterval
     */
    public int getUpdateInterval() {
        return updateInterval;
    }

    /**
     * Sets the time for logging to database, in minutes.
     * @param updateInterval
     */
    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    /**
     * Returns the number of the device.
     * @return DeviceNumber
     */
    public int getDevice(){
        return this.device;
    }

    private float getTemperatureOLD() {
        return temperatureOLD;
    }

    private void setTemperatureOLD(float temperatureOLD) {
        this.temperatureOLD = temperatureOLD;
    }

    private float getHumidityOLD() {
        return humidityOLD;
    }

    private void setHumidityOLD(float humidityOLD) {
        this.humidityOLD = humidityOLD;
    }

    private float getPressureOLD() {
        return pressureOLD;
    }

    private void setPressureOLD(float pressureOLD) {
        this.pressureOLD = pressureOLD;
    }

    private float getVoltageOLD() {
        return voltageOLD;
    }

    private void setVoltageOLD(float voltageOLD) {
        this.voltageOLD = voltageOLD;
    }



}
